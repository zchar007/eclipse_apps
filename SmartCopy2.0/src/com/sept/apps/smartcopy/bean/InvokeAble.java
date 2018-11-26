package com.sept.apps.smartcopy.bean;

import java.util.ArrayList;

public abstract class InvokeAble {
	private String id;
	private String name;
	private String description;
	protected ArrayList<InvokeAble> alSteps = new ArrayList<>();

	/**
	 * 执行
	 * 
	 * @param message
	 * @return
	 */
	public abstract String entry(String message) throws Exception;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addStep(InvokeAble step) {
		this.alSteps.add(step);
	}

}
