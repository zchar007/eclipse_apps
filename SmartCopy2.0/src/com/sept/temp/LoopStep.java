package com.sept.stringformat.step;

import java.util.ArrayList;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;

public class LoopStep extends StepModel{
	private final String start;
	private final String end;
	private final String loopvar;
	private final String stepvar;
	private final ArrayList<StepModel> alSteps;

	public LoopStep(String start, String end, String loopvar, String stepvar) {
		super();
		this.start = start;
		this.end = end;
		this.loopvar = loopvar;
		this.stepvar = stepvar;
		this.alSteps = new ArrayList<StepModel>();
	}

	@Override
	public boolean entry() throws Exception {
		int startIndex = 0;
		try {
			startIndex = Integer.parseInt(start);
		} catch (Exception e) {
			startIndex = 0;
		}
		int endIndex = 0;
		try {
			endIndex = Integer.parseInt(end);
		} catch (Exception e) {
			String var = GlobalVar.getVar(loopvar);
			String[] vars = var.split(",");
			endIndex = vars.length;
		}
		for (int i = startIndex; i < endIndex; i++) {
			String varTemp = GlobalVar.getVar(loopvar.substring(2,loopvar.length()-1) + i);
			String varTypeTemp = GlobalVar.getVarType(loopvar.substring(2,loopvar.length()-1) + i);
			GlobalVar.putVar(stepvar, varTemp, varTypeTemp);
			//System.out.println(varTemp+"--"+varTypeTemp);
			for (int j = 0; j < alSteps.size(); j++) {
				alSteps.get(j).doStep();
			}
		}
		return false;
	}

	public void addStep(StepModel step) {
		this.alSteps.add(step);
	}

	@Override
	public String toString() {
		return "LoopStep [start=" + start + ", end=" + end + ", loopvar="
				+ loopvar + ", stepvar=" + stepvar + "]";
	}

}
