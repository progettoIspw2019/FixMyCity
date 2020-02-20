package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.util.CityEnum;

public class CompanyUserBean {
	private String usernameBean;

	private String passwordBean;

	private String companyNameBean;

	private String categoryBean;

	private CityEnum cityBean;

	private byte[] imageBean;

	public String getUsername() {
		return usernameBean;
	}

	public void setUsername(String username) {
		this.usernameBean = username;
	}

	public String getPassword() {
		return passwordBean;
	}

	public void setPassword(String password) {
		this.passwordBean = password;
	}

	public String getCompanyName() {
		return companyNameBean;
	}

	public void setCompanyName(String companyName) {
		this.companyNameBean = companyName;
	}

	public String getCategory() {
		return categoryBean;
	}

	public void setCategory(String category) {
		this.categoryBean = category;
	}

	public CityEnum getCity() {
		return cityBean;
	}

	public void setCity(CityEnum city) {
		this.cityBean = city;
	}

	public byte[] getImage() {
		return imageBean;
	}

	public void setImage(byte[] image) {
		this.imageBean = image;
	}

}
