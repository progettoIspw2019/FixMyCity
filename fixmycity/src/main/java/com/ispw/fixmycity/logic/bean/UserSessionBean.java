package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.UserMode;

public class UserSessionBean {

	//TODO questo singleton deve essere uno per ogni istanza di JVM nel server web
	private static UserSessionBean instance = new UserSessionBean();

	private UserSessionBean() {

	}

	public static synchronized UserSessionBean getInstance() {
		return instance;
	}

	private CitizenUser activeCitizenUser;
	private CompanyUser activeCompanyUser;
	private UserMode userMode = UserMode.GUEST;

	public CitizenUser getActiveCitizenUser() {
		return activeCitizenUser;
	}

	public void setActiveCitizenUser(CitizenUser activeCitizenUser) {
		this.activeCitizenUser = activeCitizenUser;
	}

	public CompanyUser getActiveCompanyUser() {
		return activeCompanyUser;
	}

	public void setActiveCompanyUser(CompanyUser activeCompanyUser) {
		this.activeCompanyUser = activeCompanyUser;
	}

	public UserMode getUserMode() {
		return userMode;
	}

	public void setUserMode(UserMode userMode) {
		this.userMode = userMode;
	}

}
