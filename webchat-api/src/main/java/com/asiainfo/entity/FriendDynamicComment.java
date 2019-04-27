package com.asiainfo.entity;

import java.io.Serializable;
import java.util.Date;

public class FriendDynamicComment implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8365457553059801415L;
	private String commentId;
	private String dynamicId;
	private String commentAccount;
	private Date commentTime;
	private String commentContent;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getCommentAccount() {
		return commentAccount;
	}
	public void setCommentAccount(String commentAccount) {
		this.commentAccount = commentAccount;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	
	
}
