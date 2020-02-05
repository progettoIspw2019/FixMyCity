package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.util.UserMode;

public class CitizenUserSessionBean extends UserSessionBean {

	private static final CitizenUserSessionBean instance = new CitizenUserSessionBean();

	private CitizenUserSessionBean() {

	}

	public static CitizenUserSessionBean getInstance() {
		return instance;
	}

	private CitizenUserBean activeUser;

	public CitizenUserBean getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(CitizenUserBean activeUser) {
		this.activeUser = activeUser;
	}

	@Override
	public UserMode getSessionType() {
		return UserMode.CITIZEN;
	}

}
