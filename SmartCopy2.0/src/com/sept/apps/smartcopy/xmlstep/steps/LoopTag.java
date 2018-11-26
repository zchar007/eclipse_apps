package com.sept.apps.smartcopy.xmlstep.steps;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanNames;
import com.sept.apps.smartcopy.xmlstep.ScMemory;
import com.sept.exception.AppException;

public class LoopTag extends InvokeAble {
	private String loopVar;
	private String stepVar;
	private String beginIndex;
	private String endIndex;

	public LoopTag(String loopVar, String stepVar, String beginIndex, String endIndex) {
		super();
		this.loopVar = loopVar;
		this.stepVar = stepVar;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	@Override
	public String entry(String message) throws Exception {
		String loopVar_t = this.loopVar;
		String stepVar_t = this.stepVar;
		String beginIndex_t = this.beginIndex;
		String endIndex_t = this.endIndex;
		Object value = null;
		Object obj = ScMemory.get(loopVar_t);
		if (null == obj) {
			value = loopVar_t;
		} else {
			value = obj;
		}
		beginIndex_t = beginIndex_t == null ? "" : beginIndex_t;
		endIndex_t = endIndex_t == null ? "" : endIndex_t;
		if (null == loopVar_t || "".equalsIgnoreCase(loopVar_t)) {
			int start = Integer.parseInt(beginIndex_t);
			int end = Integer.parseInt(endIndex_t);
			for (int i = start; i < end; i++) {
				ScMemory.put(stepVar_t, i + "");
				for (int j = 0; j < alSteps.size(); j++) {
					alSteps.get(j).entry(null);
				}
			}

		} else {
			if (value instanceof String) {
				String loopValue = (String) value;
				String[] loopTemp = loopValue.split(BeanNames.VARIABLE_DEFAULT_SPLIT);
				if (loopTemp.length > 1) {
					endIndex_t = endIndex_t.toLowerCase().equals("n") ? (loopTemp.length + "") : endIndex_t;
					int start = Integer.parseInt(beginIndex_t);
					int end = Integer.parseInt(endIndex_t);
					/**
					 * TODO 少了检测start,end
					 */
					for (int i = start; i < end; i++) {
						ScMemory.put(stepVar_t, ScMemory.insertVariable(loopTemp[i]));
						for (int j = 0; j < alSteps.size(); j++) {
							alSteps.get(j).entry(null);
						}
					}
				} else {
					endIndex_t = endIndex_t.toLowerCase().equals("n") ? (loopValue.length() + "") : endIndex_t;
					int start = Integer.parseInt(beginIndex_t);
					int end = Integer.parseInt(endIndex_t);
					/**
					 * TODO 少了检测start,end
					 */
					for (int i = start; i < end; i++) {
						ScMemory.put(stepVar_t, loopValue.charAt(i) + "");
						for (int j = 0; j < alSteps.size(); j++) {
							alSteps.get(j).entry(null);
						}
					}
				}

			} else if (value instanceof String[]) {
				String[] loopTemp = (String[]) value;
				endIndex_t = endIndex_t.toLowerCase().equals("n") ? (loopTemp.length + "") : endIndex_t;
				int start = Integer.parseInt(beginIndex_t);
				int end = Integer.parseInt(endIndex_t);
				/**
				 * TODO 少了检测start,end
				 */
				for (int i = start; i < end; i++) {
					ScMemory.put(stepVar_t, ScMemory.insertVariable(loopTemp[i]));
					for (int j = 0; j < alSteps.size(); j++) {
						alSteps.get(j).entry(null);
					}
				}
			} else {
				throw new AppException("不支持的value类型[" + value.getClass() + "]");
			}
		}

		return null;
	}

}
