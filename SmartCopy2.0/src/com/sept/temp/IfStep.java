package com.sept.stringformat.step;

import java.util.ArrayList;

import com.sept.stringformat.global.GlobalVar;
import com.sept.stringformat.model.StepModel;

public class IfStep extends StepModel{
	private final String compareVar;
	private final String compare;
	private final String compareType;
	private final ArrayList<StepModel> alSteps;

	public final static String COMPARE_EQ = "EQ";// 相同
	public final static String COMPARE_NOEQ = "NEQ";// 不同
	public final static String COMPARE_BIG = "DY";// 大于
	public final static String COMPARE_SMALL = "YX";// 小于
	public final static String COMPARE_BGEQ = "DYDY";// 大于等于
	public final static String COMPARE_SMALLEQ = "XYDY";// 小于等于
	public final static String COMPARE_TYPE = "TYPE_IS";// 类型是
	public final static String COMPARE_NOTYPE = "TYPE_NOT_IS";// 类型不是

	public IfStep(String compareVar, String compare, String compareType) {
		super();
		this.compareVar = compareVar;
		this.compare = compare;
		this.compareType = compareType;
		this.alSteps = new ArrayList<StepModel>();
	}

	@Override
	public boolean entry() throws Exception {
		if (COMPARE_TYPE.equals(compareType)) {
			String typeStr = GlobalVar.getVarType(compareVar);
			if (compare.equals(typeStr)) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_NOTYPE.equals(compareType)) {
			String typeStr = GlobalVar.getVarType(compareVar);
			if (!compare.equals(typeStr)) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_NOEQ.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (!compare.equals(value)) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_EQ.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (compare.equals(value)) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_BIG.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (value.compareTo(compare) > 0) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}

		if (COMPARE_BGEQ.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (value.compareTo(compare) >= 0) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_SMALL.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (value.compareTo(compare) < 0) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}
		if (COMPARE_SMALLEQ.equals(compareType)) {
			String value = GlobalVar.getVar(compareVar);
			if (value.compareTo(compare) <= 0) {
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).doStep();
				}
			}
		}

		return false;
	}

	public void addStep(StepModel step) {
		this.alSteps.add(step);
	}

	@Override
	public String toString() {
		return "IfStep [compareVar=" + GlobalVar.getVar(compareVar) + ", compare=" + compare
				+ ", compareType=" + compareType + "]";
	}

	public static void main(String[] args) {
		String s1 = "1";
		String s2 = "1";
		System.out.println(s1.compareTo(s2));
	}
	
	
}
