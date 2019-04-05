package com.asiainfo.entity;

import java.io.Serializable;

public class Friend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2923916036122284842L;
	
	
	private int id;
	private String userAccount;
	private String friendAccount;
	private String userRemark;
	private String friendRemark;
	private String validateInfo;
	private int addStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getFriendAccount() {
		return friendAccount;
	}
	public void setFriendAccount(String friendAccount) {
		this.friendAccount = friendAccount;
	}
	
	public String getValidateInfo() {
		return validateInfo;
	}
	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}
	public int getAddStatus() {
		return addStatus;
	}
	public void setAddStatus(int addStatus) {
		this.addStatus = addStatus;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getFriendRemark() {
		return friendRemark;
	}
	public void setFriendRemark(String friendRemark) {
		this.friendRemark = friendRemark;
	}
	
	
	

}
