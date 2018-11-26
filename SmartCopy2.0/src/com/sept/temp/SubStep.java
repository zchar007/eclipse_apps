package com.sept.stringformat.step;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;
import com.sept.support.exception.BusinessException;

public class SubStep extends StepModel{
	private final String start;
	private final String end;
	private final String value;
	private final String toName;

	public SubStep(String start, String end, String value, String toName) {
		super();
		this.start = start;
		this.end = end;
		this.value = value;
		this.toName = toName;
	}

	@Override
	protected boolean entry() throws BusinessException {
		String vStart = start, vEnd = end, vValue = value, vToName = toName;
		vValue = GlobalVar.insertVarToString(vValue);

		int startInt, endInt;
		try {
			startInt = Integer.parseInt(vStart.trim());
		} catch (Exception e) {
			startInt = 0;
		}
		if (!"N".equals(vEnd.trim().toUpperCase()) && null != vEnd
				&& !vEnd.trim().isEmpty()) {
			try {
				endInt = Integer.parseInt(vEnd.trim());
			} catch (Exception e) {
				endInt = -1;
			}

		} else {
			endInt = vValue.length();
		}
		if(endInt < 0){
			endInt = vValue.length()+endInt;
		}
		if (endInt > 0 && startInt >= 0) {
			if (endInt <= startInt) {
				throw new BusinessException("截取时，开始位置不能大于等于结束位置！");
			}
		}

		if (endInt > 0 && endInt > startInt) {
			vValue = vValue.substring(startInt, endInt);
		}
		if (null == vToName || vToName.trim().isEmpty()) {
			GlobalVar.putVar(value, vValue, null);
		} else {
			GlobalVar.putVar(vToName, vValue, null);

		}

		return false;
	}
}
