package com.ispw.fixmycity.logic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.bean.UserSessionBean;
import com.ispw.fixmycity.logic.dao.UserDAO;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.UserMode;

public class LoginController {

	public void checkCredentials(BaseUserBean user) {

		UserDAO dao = new UserDAO();
		UserSessionBean session = UserSessionBean.getInstance();
		try {
			CitizenUser citizenUser = dao.findAllCitizensFromCredentials(user);

			if (citizenUser != null) {
				session.setActiveCitizenUser(citizenUser);
				session.setUserMode(UserMode.CITIZEN);
				return;
			}
		} catch (NoResultException e) {
		}
		try {
			CompanyUser companyUser = dao.findAllCompanyUserFromCredentials(user);

			if (companyUser != null) {
				session.setActiveCompanyUser(companyUser);
				session.setUserMode(UserMode.COMPANY);
				return;
			}
		} catch (NoResultException e) {
		}
		session.setActiveCompanyUser(null);
		session.setActiveCitizenUser(null);
		session.setUserMode(UserMode.GUEST);

	}

	public boolean signupCitizenUser(CitizenUserBean bean) {

		UserDAO dao = new UserDAO();

		if (dao.userExists(bean.getUsername()))
			return false;

		dao.insertCitizenUser(bean);
		Logger.getLogger("fixmycity").log(Level.INFO, "CitizenUser inserito");

		return true;

	}

	public boolean signupCompanyUser(CompanyUserBean bean) {

		UserDAO dao = new UserDAO();

		if (dao.userExists(bean.getUsername()))
			return false;

		dao.insertCompanyUser(bean);
		Logger.getLogger("fixmycity").log(Level.INFO, "CompanyUser inserito");

		return true;

	}

}
