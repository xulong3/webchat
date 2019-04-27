package com.asiainfo.entity;

import java.io.Serializable;

public class ChatList implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8365457553059801415L;
	
	private int id;
	private String account;
	private String friendAccount;
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
	public String getFriendAccount() {
		return friendAccount;
	}
	public void setFriendAccount(String friendAccount) {
		this.friendAccount = friendAccount;
	}
	

	
}
