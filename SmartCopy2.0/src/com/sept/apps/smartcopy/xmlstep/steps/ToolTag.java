package com.sept.apps.smartcopy.xmlstep.steps;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanNames;
import com.sept.apps.smartcopy.xmlstep.ScMemory;
import com.sept.apps.smartcopy.xmlstep.StepTools;

public class ToolTag extends InvokeAble {
	private String name;
	private String value;
	private String toolName;
	private String beginIndex;
	private String endIndex;
	private String regex;

	public ToolTag(String name, String value, String toolName) {
		super();
		this.name = name;
		this.value = value;
		this.toolName = toolName;
	}

	@Override
	public String entry(String message) throws Exception {
		 String name_t = this.name;
		 String value_t = this.value;
		 String toolName_t = this.toolName;
		 String beginIndex_t = this.beginIndex;
		 String endIndex_t = this.endIndex;
		 String regex_t = this.regex;
		
		value_t = ScMemory.insertVariable(value_t);
		
		if (toolName_t.equals("sub")) {
			endIndex_t = endIndex_t.toLowerCase().equals("n")?(value_t.length()+""):endIndex_t;
			ScMemory.put(name_t, StepTools.sub(value_t, Integer.parseInt(beginIndex_t), Integer.parseInt(endIndex_t)));
		}else if (toolName_t.equals("split")) {
			ScMemory.put(name_t, StepTools.split(value_t, null == regex_t?BeanNames.VARIABLE_DEFAULT_SPLIT:regex_t));
		}
		return null;
	}

	public String getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(String beginIndex) {
		this.beginIndex = beginIndex;
	}

	public String getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
