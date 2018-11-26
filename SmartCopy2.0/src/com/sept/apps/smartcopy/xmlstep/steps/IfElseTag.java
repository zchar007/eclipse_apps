package com.sept.apps.smartcopy.xmlstep.steps;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanNames;
import com.sept.apps.smartcopy.xmlstep.ScMemory;

public class IfElseTag extends InvokeAble {
	private String apara;
	private String bpara;
	private String compare;

	public IfElseTag(String apara, String bpara, String compare) {
		super();
		this.apara = apara;
		this.bpara = bpara;
		this.compare = compare;
	}

	@Override
	public String entry(String message) throws Exception {
		Object apara_temp = ScMemory.get(apara);
		Object bpara_temp = ScMemory.get(bpara);
		apara_temp = null == apara_temp ? apara : apara_temp;
		bpara_temp = null == bpara_temp ? bpara : bpara_temp;

		String apara_t = apara_temp.toString();
		String bpara_t = bpara_temp.toString();
//		public final static String COMPARE_EQ = "EQ";// 相同
		if (BeanNames.COMPARE_EQ.equals(compare)) {
			if (apara_t.equalsIgnoreCase(bpara_t)) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_NOEQ = "NEQ";// 不同
		if (BeanNames.COMPARE_NOEQ.equals(compare)) {
			if (!apara_t.equalsIgnoreCase(bpara_t)) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_BIG = "BIG";// 大于
		if (BeanNames.COMPARE_BIG.equals(compare)) {
			if (apara_t.compareTo(bpara_t) > 0) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_MIN = "MIN";// 小于
		if (BeanNames.COMPARE_MIN.equals(compare)) {
			if (apara_t.compareTo(bpara_t) < 0) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_BIGEQ = "BIGEQ";// 大于等于
		if (BeanNames.COMPARE_BIGEQ.equals(compare)) {
			if (apara_t.compareTo(bpara_t) >= 0) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_MINEQ = "MINEQ";// 小于等于
		if (BeanNames.COMPARE_MINEQ.equals(compare)) {
			if (apara_t.compareTo(bpara_t) <= 0) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_CONTAINS = "CONTAINS";// 包含
		if (BeanNames.COMPARE_CONTAINS.equals(compare)) {
			if (apara_t.indexOf(bpara_t) >= 0) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_STARTWITH = "STARTWITH";// 开头是
		if (BeanNames.COMPARE_STARTWITH.equals(compare)) {
			if (apara_t.startsWith(bpara_t)) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}
//		public final static String COMPARE_ENDWITH = "ENDWITH";// 结尾是
		if (BeanNames.COMPARE_ENDWITH.equals(compare)) {
			if (apara_t.endsWith(bpara_t)) {
				for (int i = 0; i < alSteps.size(); i++) {
					alSteps.get(i).entry(null);
				}
			}
		}

		return null;
	}

}
