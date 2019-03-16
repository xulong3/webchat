package com.asiainfo.entity;

import java.io.Serializable;

public class Config implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3415657796159602995L;
	private int id;
	private String key;
	private String value;
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
