package com.ispw.fixmycity.logic.bean;

public class CitizenUserBean extends BaseUserBean {

	private String firstNameBean;

	private byte[] profilePictureBean;

	private String lastNameBean;

	public String getFirstName() {
		return firstNameBean;
	}

	public void setFirstName(String firstName) {
		this.firstNameBean = firstName;
	}

	public byte[] getProfilePicture() {
		return profilePictureBean;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePictureBean = profilePicture;
	}

	public String getLastName() {
		return lastNameBean;
	}

	public void setLastName(String lastName) {
		this.lastNameBean = lastName;
	}
}
