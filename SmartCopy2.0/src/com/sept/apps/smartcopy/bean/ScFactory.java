package com.sept.apps.smartcopy.bean;

import java.util.HashMap;


public interface ScFactory {
	/**
	 * 获取可执行的bean
	 * 
	 * @param name
	 */
	InvokeAble getBean(String name) throws Exception;

	/**
	 * 获取可执行bean的id和显示名称和描述
	 * 
	 * @return
	 */
	HashMap<String, String[]> getNames();

	/**
	 * 重新加载
	 * 
	 * @return
	 */
	boolean reload() throws Exception;
}
