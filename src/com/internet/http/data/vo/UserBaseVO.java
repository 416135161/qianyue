package com.internet.http.data.vo;

import java.io.Serializable;

public class UserBaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long adminPkid;
	private String username;
	private String avatar;
	public Long getAdminPkid() {
		return adminPkid;
	}
	public void setAdminPkid(Long adminPkid) {
		this.adminPkid = adminPkid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	 
	
}
