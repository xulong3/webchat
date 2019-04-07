package com.asiainfo.vo;

import java.io.Serializable;

import com.asiainfo.entity.Label;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.entity.User;

public class FriendItemVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String portrait;
	private String remark;
	private String nickname;
	private String showName;
	private String account;
	private SysLabel sysLabel;
	private Label label;
	private User authLabel;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public SysLabel getSysLabel() {
		return sysLabel;
	}
	public void setSysLabel(SysLabel sysLabel) {
		this.sysLabel = sysLabel;
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public User getAuthLabel() {
		return authLabel;
	}
	public void setAuthLabel(User authLabel) {
		this.authLabel = authLabel;
	}
	
	
}
