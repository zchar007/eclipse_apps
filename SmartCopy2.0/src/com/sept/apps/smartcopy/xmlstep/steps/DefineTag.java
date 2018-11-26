package com.sept.apps.smartcopy.xmlstep.steps;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanNames;
import com.sept.apps.smartcopy.xmlstep.ScMemory;
import com.sept.exception.AppException;

/**
 * 定义值，允许时用${}进行动态创建拼接
 * 
 * @author zchar
 *
 */
public class DefineTag extends InvokeAble {
	private String name;
	private String value;
	private String type;

	public DefineTag(String name, String value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	@Override
	public String entry(String arg0) throws AppException {
		String name_t = this.name;
		String value_t = this.value;
		String type_t = this.type;
		type_t = null == type_t ? BeanNames.VARIABLE_TYPE_STRING : type_t;
		if (BeanNames.VARIABLE_TYPE_ARRAY.toLowerCase().equals(type_t.toLowerCase())) {
			String[] values = value_t.split(BeanNames.VARIABLE_DEFAULT_SPLIT);
			ScMemory.put(name_t, values);
		} else {
			ScMemory.put(name_t, value_t);
		}
		return null;
	}
}
