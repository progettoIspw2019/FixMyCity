package com.ispw.fixmycity.logic.bean;

import java.io.File;

public class CitizenUserBean extends BaseUserBean {

	private String firstName;

	private File profilePicture;

	private String lastName;
	
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

}
