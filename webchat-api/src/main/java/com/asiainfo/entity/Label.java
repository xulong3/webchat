package com.asiainfo.entity;

import java.io.Serializable;

public class Label implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7288077396620319045L;
	private int id;
	private String account;
	private String labelKey;
	private String labelValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public String getLabelValue() {
		return labelValue;
	}
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}
	
	
	
	
}
