package com.sept.stringformat.step;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;

public class PutStep extends StepModel{
	private final String putStr;
	private final String index;

	public PutStep(String putStr,String index) {
		super();
		this.putStr = putStr;
		this.index = index;
	}

	@Override
	public boolean entry() {
		GlobalVar.print(putStr,index);
		return false;
	}
	@Override
	public String toString() {
		return "PutStep [putStr=" + putStr + ", index=" + index + "]";
	}

}
