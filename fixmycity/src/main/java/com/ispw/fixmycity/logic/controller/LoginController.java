package com.ispw.fixmycity.logic.controller;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.dao.UserDAO;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.ConverterUtil;
import com.ispw.fixmycity.logic.util.UserMode;

public class LoginController {

	private static final LoginController instance = new LoginController();

	private LoginController() {

	}

	public static LoginController getInstance() {
		return instance;
	}

	private CitizenUser activeCitizenUser;
	private CompanyUser activeCompanyUser;
	private UserMode userMode = UserMode.GUEST;

	public boolean checkCredentials(BaseUserBean user) {

		UserDAO dao = new UserDAO();

		CitizenUser citizenUser = dao.findAllCitizensFromCredentials(user);

		if (citizenUser != null) {
			setActiveCitizenUser(citizenUser);
			setUserMode(UserMode.CITIZEN);
			return true;
		}

		CompanyUser companyUser = dao.findAllCompanyUserFromCredentials(user);

		if (companyUser != null) {
			setActiveCompanyUser(companyUser);
			setUserMode(UserMode.COMPANY);
			return true;
		}

		return false;

	}

	public boolean signupCitizenUser(CitizenUserBean bean) {

		UserDAO dao = new UserDAO();

		if (dao.userExists(bean.getUsername()))
			return false;

		dao.insertCitizenUser(bean);

		return true;

	}

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
