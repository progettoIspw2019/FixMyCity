package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.util.CityEnum;

public class CitizenUserBean extends BaseUserBean {

	private String firstName;

	private byte[] profilePicture;

	private String lastName;

	private CityEnum city;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
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
