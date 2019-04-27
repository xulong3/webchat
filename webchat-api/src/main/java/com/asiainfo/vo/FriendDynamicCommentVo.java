package com.asiainfo.vo;

import java.io.Serializable;
import java.util.Date;

public class FriendDynamicCommentVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commentId;
	private String dynamicId;
	private String commentAccount;
	private String commentTime;
	private String commentContent;
	
	private String nickname;

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

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
