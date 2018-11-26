package com.sept.apps.smartcopy.xmlstep;

public class StepTools {

	/**
	 * 截取
	 * 
	 * @param message
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static final String sub(String message, int beginIndex, int endIndex) {
		return message.substring(beginIndex, endIndex);
	}

	/**
	 * 分割
	 * 
	 * @param message
	 * @param regex
	 * @return
	 */
	public static final String[] split(String message, String regex) {
		return message.split(regex);
	}
	
}
