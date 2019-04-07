package com.asiainfo.vo;

import java.io.Serializable;

public class MessageVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sendTime;
	private String sender;
	private String message;
	private String filePath;
	
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
