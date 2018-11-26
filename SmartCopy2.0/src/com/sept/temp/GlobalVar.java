package com.sept.stringformat.global;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sept.support.data.basic.DataObject;
import com.sept.support.exception.BusinessException;

public class GlobalVar{
	public static HashMap<String, String> hmVars = new HashMap<String, String>();// 数组以名字+序号的方式存储
	public static HashMap<String, String> hmVarTypes = new HashMap<String, String>();
	public static String TYPE_STRING = "s";
	public static String TYPE_INT = "i";
	public static String TYPE_DOUBLE = "d";
	public static String TYPE_DATE = "dt";
	public static String TYPE_DATE_TOSTRING = "dts";
	public static String TYPE_BIGDECIMAL = "bd";
	public static String TYPE_DATASTORE = "ds";

	private static StringBuffer sbPrint = new StringBuffer();

	public static final String INPUT_VAR_NAME = "_input__var_";

	/**
	 * @author 张超
	 * @date 创建时间 2017年9月5日
	 * @since V1.0
	 */
	public static void putVar(String key, String value, String type) {
		if (null == type) {
			type = GlobalVar.TYPE_STRING;
		}
		// System.out.println("所有变量"+hmVars);
		// System.out.println("put前：["+key+"]["+hmVars.get(key)+"]");
		hmVars.put(key, value);
		hmVarTypes.put(key, type);
		// System.out.println("put后：["+key+"]["+hmVars.get(key)+"]");

	}

	public static void putVar(String key, String[] values) throws BusinessException {
		String string = "";
		for (int i = 0; i < values.length; i++) {
			String[] value = values[i].split(":");
			if (value.length == 1) {
				putVar(key + i, value[0], GlobalVar.TYPE_STRING);
			} else if (value.length == 2) {
				putVar(key + i, value[0], value[1]);
			} else {
				throw new BusinessException("put参数错误【" + value + "】");
			}
			string += value[0] + ",";
		}
		if (string.length() > 1) {
			string = string.substring(0, string.length() - 1);
		}
		putVar(key, string, GlobalVar.TYPE_STRING);
	}

	public static String getVar(String key) {
		key = key.trim();
		if (key.startsWith("$")) {
			key = key.substring(2, key.length() - 1);
		}
		if (hmVars.containsKey(key)) {
			return hmVars.get(key);
		}
		return key;

	}

	public static String getVarType(String key) {
		key = key.trim();
		if (key.startsWith("$")) {
			key = key.substring(2, key.length() - 1);
		}
		if (hmVarTypes.containsKey(key)) {
			return hmVarTypes.get(key);
		}
		return "s";

	}

	/**
	 * 格式化字符串，把含有动态变量的地方替换成实际的值
	 * 
	 * @author 张超
	 * @date 创建时间 2017年9月1日
	 * @since V1.0
	 */
	public static String insertVarToString(String formatString) {
		// 获取其中需要转换的变量key
		String regx4LogicalOperator = "(?<=\\$\\{)[^\\}]*(?=\\})";
		Pattern pattern4LogicalOperator = Pattern.compile(regx4LogicalOperator);
		Matcher matcher4LogicalOperator = pattern4LogicalOperator.matcher(formatString);
		HashMap<String, String> hmNowParams = new HashMap<String, String>();
		while (matcher4LogicalOperator.find()) {
			String key = matcher4LogicalOperator.group();
			if (hmVars.containsKey(key)) {
				hmNowParams.put("\\$\\{" + key + "\\}", hmVars.get(key)
					.toString());
			} else {
				hmNowParams.put("\\$\\{" + key + "\\}", "\\$\\{" + key + "\\}");
			}
		}
		// System.out.println(hmNowParams);
		// 替换字符串的变量
		for (String param : hmNowParams.keySet()) {
			// System.out.println(hmNowParams.get(param));
			formatString = formatString.replaceAll(param, hmNowParams.get(param));
		}
		return formatString;
	}

	/**
	 * 单纯往输出sb中输出字符串，不加换行符 <br>
	 * 以${}包起来的数据需要取hmPrintStore中的数据
	 * 
	 * @author 张超
	 * @date 创建时间 2017年9月1日
	 * @since V1.0
	 */
	public static void print(String printStr, String index) {
		try {
			int indexInt = Integer.parseInt(index);
			sbPrint.insert(indexInt, insertVarToString(printStr));
		} catch (Exception e) {
			sbPrint.append(insertVarToString(printStr));
		}

	}

	/**
	 * 单纯往输出sb中输出字符串，加换行符 <br>
	 * 以${}包起来的数据需要取hmPrintStore中的数据
	 * 
	 * @author 张超
	 * @date 创建时间 2017年9月1日
	 * @since V1.0
	 */
	public static void println(String printStr, String index) {
		try {
			int indexInt = Integer.parseInt(index);
			sbPrint.insert(indexInt, "\n" + insertVarToString(printStr));
		} catch (Exception e) {
			sbPrint.append("\n" + insertVarToString(printStr));
		}
	}

	public static void clear() {
		hmVars.clear();
		hmVars = new HashMap<String, String>();
		hmVarTypes.clear();
		hmVarTypes = new HashMap<String, String>();
		sbPrint.setLength(0);
	}

	public static DataObject getAllVarsToDataObject() {
		DataObject para = new DataObject();
		para.putAll(hmVars);
		return para;
	}

	public static StringBuffer getSbPrint() {
		return sbPrint;
	}

	public static void setSbPrint(StringBuffer sbPrint) {
		GlobalVar.sbPrint = sbPrint;
	}
	// public static void main(String[] args) {
	// StringBuffer sBuffer = new StringBuffer("111");
	// sBuffer.insert(0, "123");
	// System.out.println(sBuffer.toString());
	// }

}
