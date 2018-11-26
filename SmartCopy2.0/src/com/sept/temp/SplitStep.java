package com.sept.stringformat.step;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;
import com.sept.support.exception.BusinessException;

public class SplitStep extends StepModel{
	private final String fromvar;
	private final String split;
	private final String tovar;

	public SplitStep(String fromvar, String split, String tovar) {
		super();
		this.fromvar = fromvar;
		this.split = split;
		this.tovar = tovar;
	}

	@Override
	public boolean entry() throws BusinessException {
		String fromvarTemp = GlobalVar.getVar(fromvar);
		if (null == fromvar) {
			throw new BusinessException("fromvar为null!");
		}
		String splitTemp = split;
		if (null == splitTemp) {
			 splitTemp = ",";
		}
		String[] strs = fromvarTemp.split(splitTemp);
		GlobalVar.putVar(tovar, strs);
		return true;
	}

	@Override
	public String toString() {
		return "SplitStep [fromvar=" + fromvar + ", split=" + split
				+ ", tovar=" + tovar + "]";
	}

}
