package com.sept.apps.smartcopy.xmlstep.steps;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.xmlstep.ScMemory;

public class AppendTag extends InvokeAble {
	private String value;

	public AppendTag(String value) {
		super();
		this.value = value;
	}

	@Override
	public String entry(String message) throws Exception {
		String value_t = value;
		ScMemory.appendOutStream(value_t);
		return null;
	}

}
