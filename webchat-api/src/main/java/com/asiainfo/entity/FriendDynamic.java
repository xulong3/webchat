package com.asiainfo.entity;

import java.io.Serializable;
import java.util.Date;

public class FriendDynamic implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8365457553059801415L;
	
	private String dynamicId;
	private String account;
	private String textContent;
	private Date publishTime;
	private int praiseCount;
	public String getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	
	
	
}
