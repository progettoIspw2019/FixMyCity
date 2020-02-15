package com.ispw.fixmycity.logic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.dao.UserDAO;
import com.ispw.fixmycity.logic.exceptions.UserNotFoundException;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

public class LoginController {

	public BaseUserBean checkCredentials(BaseUserBean user) throws UserNotFoundException {

		UserDAO dao = new UserDAO();
		try {
			CitizenUser citizenUser = dao.findAllCitizensFromCredentials(user);

			if (citizenUser != null) {
				user.setMode(UserMode.CITIZEN);
				user.setImage(citizenUser.getProfilePicture());
				user.setCity(CityEnum.valueOf(citizenUser.getCity().toUpperCase()));
				return user;
			}
		} catch(NoResultException e) {
			// ignore first exception
		}
		try {
			CompanyUser companyUser = dao.findAllCompanyUserFromCredentials(user);

			if (companyUser != null) {
				user.setMode(UserMode.COMPANY);
				user.setImage(companyUser.getImage());
				user.setCity(CityEnum.valueOf(companyUser.getCity().toUpperCase()));
				return user;
			}
		} catch (NoResultException e) {
			throw new UserNotFoundException("No user found with given username and password!", e);
		}
		user.setMode(UserMode.GUEST);
		return user;
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
	
	public void logout() {
		SessionView.setMode(UserMode.GUEST);
		SessionView.setCityEnum(null);
		SessionView.setImageProfile(null);
		SessionView.setLatitudeSetOnMap(null);
		SessionView.setLongitudeSetOnMap(null);
		SessionView.setUsername(null);
		SessionView.setAddressSetOnMap(null);
	}

}
