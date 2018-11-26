package com.sept.apps.smartcopy.beanfactory;

public interface BeanNames {
	static final String DOM_BEAN_NAME = "bean";
	static final String DOM_BEAN_DESCRIPTION = "description";
	static final String DOM_BEAN_METHOD_IMPORT = "import";
	static final String DOM_BEAN_METHOD_CONTENT = "content";
	static final String DOM_BEAN_STEPS = "steps";
	static final String DOM_BEAN_DEFINE = "define";
	static final String DOM_BEAN_IF = "if";
	static final String DOM_BEAN_LOOP = "loop";
	static final String DOM_BEAN_TOOL = "tool";
	static final String DOM_BEAN_APPEND = "append";

	static final String BEAN_SHOW_ID = "id";
	static final String BEAN_SHOW_NAME = "name";
	static final String BEAN_CLASS_NAME = "class";

	static final String BEAN_DEFINE_NAME = "name";
	static final String BEAN_DEFINE_VALUE = "value";
	static final String BEAN_DEFINE_TYPE = "type";

	static final String BEAN_APPEND_VALUE = "value";

	static final String BEAN_TOOL_TOOLNAME = "toolName";
	static final String BEAN_TOOL_NAME = "name";
	static final String BEAN_TOOL_VALUE = "value";
	static final String BEAN_TOOL_REGEX = "regex";
	static final String BEAN_TOOL_BEGININDEX = "beginIndex";
	static final String BEAN_TOOL_ENDINDEX = "endIndex";

	static final String BEAN_LOOP_LOOPVAR = "loopVar";
	static final String BEAN_LOOP_STEPVAR = "stepVar";
	static final String BEAN_LOOP_BEGININDEX = "beginIndex";
	static final String BEAN_LOOP_ENDINDEX = "endIndex";
	
	static final String BEAN_IF_APARA = "apara";
	static final String BEAN_IF_BPARA = "bpara";
	static final String BEAN_IF_COMPARE = "compare";


	public final static String COMPARE_EQ = "EQ";// 相同
	public final static String COMPARE_NOEQ = "NEQ";// 不同
	public final static String COMPARE_BIG = "BIG";// 大于
	public final static String COMPARE_MIN = "MIN";// 小于
	public final static String COMPARE_BIGEQ = "BIGEQ";// 大于等于
	public final static String COMPARE_MINEQ = "MINEQ";// 小于等于
	public final static String COMPARE_CONTAINS = "CONTAINS";// 包含
	public final static String COMPARE_STARTWITH = "STARTWITH";// 开头是
	public final static String COMPARE_ENDWITH = "ENDWITH";// 结尾是

	static final String BEAN_INPUT_VALUE_NAME = "@input";
	static final String CLASS_NAME_HEAD = "Invoke_";

	static final String VARIABLE_HEAD = "${";

	static final String VARIABLE_END = "}";

	static final String VARIABLE_REGX = "(?<=\\$\\{)[^\\}]*(?=\\})";
	static final String VARIABLE_SUBSCRIPT_REGX = "(?<=\\[)[\\s\\S]*(?=\\])";
	static final String VARIABLE_TYPE_STRING = "string";
	static final String VARIABLE_TYPE_ARRAY = "array";
	static final String VARIABLE_DEFAULT_SPLIT = ",";

	static final String VARIABLE_INPUT_NAME = "@input";
}
