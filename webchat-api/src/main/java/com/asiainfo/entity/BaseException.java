package com.asiainfo.entity;

import java.util.Date;

public abstract class BaseException extends RuntimeException{
	
	
	/**
	 * 序列化id
	 */
	protected static final long serialVersionUID = 2646231434807684314L;
	/**
	 * 主键
	 */
	protected int id;
	
	/**
	 * 异常代码
	 */
	protected int code;
	
	/**
	 * 异常名称
	 */
	protected String name;
	/**
	 * 发生异常的操作用户
	 */
	protected String account;
	/**
	 * 异常的具体信息
	 */
	protected String message;
	/**
	 * 异常的发生时间
	 */
	protected Date time;
	/**
	 * 异常的解决时间，如果为null则表示还未解决
	 */
	protected Date solvedTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getSolvedTime() {
		return solvedTime;
	}
	public void setSolvedTime(Date solvedTime) {
		this.solvedTime = solvedTime;
	}
	
	
	protected BaseException(String account,String message){
		this.account=account;
		this.message=message;
	}
	
	
	
	
}
