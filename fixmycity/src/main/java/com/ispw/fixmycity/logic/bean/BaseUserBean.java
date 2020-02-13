package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.util.UserMode;

public class BaseUserBean {

	private String username;
	private String password;
	private byte[] image;
	private UserMode mode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public UserMode getMode() {
		return mode;
	}

	public void setMode(UserMode mode) {
		this.mode = mode;
	}

}
