package com.ispw.fixmycity.logic.bean;

import java.io.File;

import com.ispw.fixmycity.logic.util.CityEnum;

public class CitizenUserBean extends BaseUserBean {

	private String firstName;

	private File profilePicture;

	private String lastName;

	private CityEnum city;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public File getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(File profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CityEnum getCity() {
		return city;
	}

	public void setCity(CityEnum city) {
		this.city = city;
	}

}
