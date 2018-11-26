package com.sept.stringformat.step;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;

public class PutlnStep extends StepModel{
	private final String putlnStr;
	private final String index;

	public PutlnStep(String putlnStr, String index) {
		super();
		this.putlnStr = putlnStr;
		this.index = index;
	}

	@Override
	public boolean entry() {
		GlobalVar.println(putlnStr, index);
		return false;
	}

	@Override
	public String toString() {
		return "PutlnStep [putlnStr=" + putlnStr + ", index=" + index + "]";
	}

}
