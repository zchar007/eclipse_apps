package com.sept.apps.smartcopy.xmlstep;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sept.apps.smartcopy.beanfactory.BeanNames;
import com.sept.exception.AppException;

public class ScMemory {
	private static final HashMap<String, Object> memory = new HashMap<>();
	private static final HashMap<String, Object> disk = new HashMap<>();
	private static final StringBuilder outStream = new StringBuilder();

	/**
	 * put String
	 * 
	 * @param key
	 * @param value
	 * @throws AppException
	 */
	public static final void put(String key, String value) throws AppException {
		memory.put(key, insertVariable(value));
	}

	/**
	 * put String[]
	 * 
	 * @param key
	 * @param value
	 * @throws AppException
	 */
	public static final void put(String key, String[] value) throws AppException {
		for (int i = 0; i < value.length; i++) {
			value[i] = ScMemory.insertVariable(value[i]);
		}
		memory.put(key, value);
	}

	/**
	 * put String[]
	 * 
	 * @param key
	 * @param value
	 */
	public static final Object get(String key) {
		key = null == key ? "" : key;
		String nowKey = key;
		if (key.startsWith(BeanNames.VARIABLE_HEAD) && key.endsWith(BeanNames.VARIABLE_END)) {
			nowKey = key.substring(BeanNames.VARIABLE_HEAD.length(), key.length() - BeanNames.VARIABLE_END.length());
		}
		if (memory.containsKey(nowKey)) {
			return memory.get(nowKey);
		}
		return null;
	}

	/**
	 * 初始化硬盘
	 * 
	 * @param hmDisk
	 */
	public static final void initDisk(HashMap<String, Object> hmDisk) {
		if (null == hmDisk) {
			return;
		}
		for (Entry<String, Object> key_value : hmDisk.entrySet()) {
			String key = key_value.getKey();
			Object value = key_value.getValue();
			disk.put(key, value);
		}
	}

	/**
	 * 添加String到输出流上
	 * 
	 * @param message
	 * @throws AppException
	 */
	public static final void appendOutStream(String message) throws AppException {
		message = null == message ? "" : message;
		message = insertVariable(message);
		outStream.append(message);
	}

	/**
	 * 把变量插入到message中
	 * 
	 * @param message
	 * @return
	 * @throws AppException
	 */
	public static final String insertVariable(String message) throws AppException {
		Pattern pattern = Pattern.compile(BeanNames.VARIABLE_REGX);
		Pattern pattern_subscript = Pattern.compile(BeanNames.VARIABLE_SUBSCRIPT_REGX);

		Matcher matcher = pattern.matcher(message);
		HashMap<String, String> hmMatchers = new HashMap<String, String>();
		while (matcher.find()) {
			String key = matcher.group();
			Matcher matcher_subscript = pattern_subscript.matcher(key);
			int count = 0;
			String indexStr = null;
			int index = -1;
			while (matcher_subscript.find()) {
				indexStr = matcher_subscript.group();
				try {
					index = Integer.parseInt(indexStr);
				} catch (Exception e) {
					index = Integer.parseInt((String) get(indexStr));
				}
				count++;
			}
			if (count == 0) {
				String nowKey = key;
				if (memory.containsKey(nowKey)) {
					if (null != memory.get(nowKey)) {
						if (memory.get(nowKey) instanceof String[]) {
							hmMatchers.put("\\$\\{" + key + "\\}", arrayToString(((String[]) memory.get(nowKey)), ","));
						} else if (memory.get(nowKey) instanceof String) {
							hmMatchers.put("\\$\\{" + key + "\\}", memory.get(nowKey).toString());
						} else {
							throw new AppException("内存中key为[" + nowKey + "]的变量不是String[]类型,也不是，无法以下标方式读取！");
						}
					} else {
						throw new AppException("内存中key为[" + nowKey + "]的变量为空！");
					}
				} else {
					throw new AppException("内存中不包含key为[" + nowKey + "]的变量！");
				}
			} else {
				String nowKey = key.substring(0, key.length() - 2 - indexStr.length());
				// System.out.println(nowKey + ":" + memory.get(nowKey));
				if (memory.containsKey(nowKey)) {
					if (null != memory.get(nowKey)) {
						if (memory.get(nowKey) instanceof String[]) {
							if (((String[]) memory.get(nowKey)).length > index) {
								hmMatchers.put("\\$\\{" + nowKey + "\\[" + indexStr + "\\]\\}",
										((String[]) memory.get(nowKey))[index]);
							} else {
								throw new AppException("内存中key为[" + nowKey + "]的变量数组长度为["
										+ ((String[]) memory.get(nowKey)).length + "]下标[" + index + "]越界！");
							}
						} else {
							throw new AppException("内存中key为[" + nowKey + "]的变量不是String[]类型，无法以下标方式读取！");
						}
					} else {
						throw new AppException("内存中key为[" + nowKey + "]的变量为空！");
					}
				} else {
					throw new AppException("内存中不包含key为[" + nowKey + "]的变量！");
				}
			}
		}
		hmMatchers.put("@input", (String)memory.get("@input"));
		// 替换字符串的变量
		for (String param : hmMatchers.keySet()) {
			message = message.replaceAll(param, hmMatchers.get(param));
		}
		return message;
	}

	public static final String arrayToString(String[] array, String connect) {
		connect = null == connect ? "" : connect;
		array = null == array ? new String[] {} : array;
		String returnStr = "";
		for (int i = 0; i < array.length; i++) {
			returnStr += array[i] + (i < array.length - 1 ? connect : "");
		}
		return returnStr;

	}

	public static void main(String[] args) throws AppException {
		ScMemory.put("aaa", "AAA");
		ScMemory.put("bbb", "BBB");
		ScMemory.put("index", "2");

		ScMemory.put("ccc", new String[] { "C", "C", "C" });
		ScMemory.put("ddd", new String[] { "D", "D", "D" });

		String str = "这是${aaa}一${bbb}个测${ccc[index]}试的字${ddd}符串";
		String message = insertVariable(str);
		System.out.println(message);
	}

	/**
	 * 获取输出流
	 * 
	 * @return
	 */
	public static final StringBuilder getOutStream() {
		return new StringBuilder(outStream.toString());
	}

	/**
	 * 回收
	 */
	public static final void gc() {
		memory.clear();
		outStream.setLength(0);
	}
}
