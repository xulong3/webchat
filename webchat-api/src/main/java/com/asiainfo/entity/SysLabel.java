package com.asiainfo.entity;

import java.io.Serializable;

public class SysLabel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 389056371295783232L;
	private int id;
	private String labelKey;
	private String comment;
	private int isMetaData;
	private String defaultValue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIsMetaData() {
		return isMetaData;
	}
	public void setIsMetaData(int isMetaData) {
		this.isMetaData = isMetaData;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	

}
