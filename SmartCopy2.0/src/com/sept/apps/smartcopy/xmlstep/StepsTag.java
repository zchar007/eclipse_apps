package com.sept.apps.smartcopy.xmlstep;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanNames;

public class StepsTag extends InvokeAble {

	public StepsTag() {
	}

	@Override
	public String entry(String inputValue) throws Exception {
		ScMemory.gc();
		ScMemory.put(BeanNames.VARIABLE_INPUT_NAME, inputValue);
		for (int i = 0; i < this.alSteps.size(); i++) {
			this.alSteps.get(i).entry(null);
		}
		return ScMemory.getOutStream().toString();
	}

}
