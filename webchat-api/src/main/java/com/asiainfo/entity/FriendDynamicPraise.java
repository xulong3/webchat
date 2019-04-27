package com.asiainfo.entity;

import java.io.Serializable;
import java.util.Date;

public class FriendDynamicPraise implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8365457553059801415L;
	private String praiseId;
	private String dynamicId;
	private String praiseAccount;
	private Date praiseTime;
	public String getPraiseId() {
		return praiseId;
	}
	public void setPraiseId(String praiseId) {
		this.praiseId = praiseId;
	}
	public String getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getPraiseAccount() {
		return praiseAccount;
	}
	public void setPraiseAccount(String praiseAccount) {
		this.praiseAccount = praiseAccount;
	}
	public Date getPraiseTime() {
		return praiseTime;
	}
	public void setPraiseTime(Date praiseTime) {
		this.praiseTime = praiseTime;
	}
	
	
}
