package com.asiainfo.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -8365457553059801415L;
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户账号，具有唯一性
	 */
	private String account;
	/**
	 * 用户昵称，可重复
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否可用，0代表不可用，1代表可用
	 */
	private int available;
	/**
	 * 账号注册时间
	 */
	private Date regTime;
	/**
	 * 账号激活时间
	 */
	private Date actTime;
	/**
	 * email
	 */
	private String email;
	
	
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
}
