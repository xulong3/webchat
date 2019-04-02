package com.asiainfo.entity;

import java.io.Serializable;

public class ConfigLabel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1583750714996643385L;
	private int id;
	private String account;
	private int validateWay;
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
	public int getValidateWay() {
		return validateWay;
	}
	public void setValidateWay(int validateWay) {
		this.validateWay = validateWay;
	}
	
	

}
