package com.sept.stringformat.step;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;
import com.sept.support.exception.BusinessException;

public class VarStep extends StepModel{
	private final String name;
	private final String value;
	private final String valueType;

	public VarStep(String name, String value, String valueType) {
		super();
		this.name = name;
		this.value = value;
		//System.out.println("设置了变量：["+name+"]["+value+"]"+this);
		this.valueType = valueType;
	}

	@Override
	public boolean entry() throws BusinessException {
		String valueTemp = value,ValueTypeTemp = valueType;
		if ("@VAR".equals(valueTemp.toUpperCase())) {
			valueTemp = GlobalVar.getVar(GlobalVar.INPUT_VAR_NAME);
			ValueTypeTemp = GlobalVar.getVarType(GlobalVar.INPUT_VAR_NAME);
			if (null == valueTemp || null == ValueTypeTemp
					|| ValueTypeTemp.trim().isEmpty()) {
				throw new BusinessException("还未定义入参!");
			}
		}
		//System.out.println("获取前：["+name+"]["+value+"]"+this);
		valueTemp = GlobalVar.insertVarToString(valueTemp);
		//System.out.println("获取后：["+name+"]["+value+"]"+this);

		GlobalVar.putVar(name, valueTemp, ValueTypeTemp);

		return true;
	}


	@Override
	public String toString() {
		return "VarStep [name=" + name + ", value=" + value + ", valueType="
				+ valueType + "]";
	}

}
