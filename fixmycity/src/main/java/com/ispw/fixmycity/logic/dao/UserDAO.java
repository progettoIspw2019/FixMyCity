package com.ispw.fixmycity.logic.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class UserDAO {
	
	private static final String PARAM_USRNAME = "input_username";
	private static final String PARAM_PASSW = "input_pwd";
	

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public UserDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public CitizenUser findAllCitizensFromCredentials(BaseUserBean user) {
		entityManager = entityManagerFactory.createEntityManager();

		return (CitizenUser) entityManager.createNamedQuery("CitizenUser.findAllFromCredentials")
				.setParameter(PARAM_USRNAME, user.getUsername()).setParameter(PARAM_PASSW, user.getPassword())
				.getSingleResult();
	}

	public CompanyUser findAllCompanyUserFromCredentials(BaseUserBean user) {
		return (CompanyUser) entityManager.createNamedQuery("CompanyUser.findAllFromCredentials")
				.setParameter(PARAM_USRNAME, user.getUsername()).setParameter(PARAM_PASSW, user.getPassword())
				.getSingleResult();
	}

	public boolean userExists(String username) {

		entityManager = entityManagerFactory.createEntityManager();
		int count = 0;

		count += ((Number) entityManager.createNamedQuery("CompanyUser.countFromUsername")
				.setParameter(PARAM_USRNAME, username).getSingleResult()).intValue();

		count += ((Number) entityManager.createNamedQuery("CitizenUser.countFromUsername")
				.setParameter(PARAM_USRNAME, username).getSingleResult()).intValue();

		return (count != 0);
	}

	public void insertCitizenUser(CitizenUserBean user) {
		CitizenUser citizenUser = new CitizenUser();

		citizenUser.setFromBean(user);
		citizenUser.setCommunityReports(new ArrayList<CommunityReport>());
		citizenUser.setCompanyReports(new ArrayList<CompanyReport>());
		citizenUser.setVolunteeringEvents(new ArrayList<VolunteeringEvent>());

		entityManager.getTransaction().begin();
		entityManager.persist(citizenUser);
		entityManager.getTransaction().commit();

	}

	public void insertCompanyUser(CompanyUserBean user) {
		CompanyUser companyUser = new CompanyUser();
		companyUser.setFromBean(user);

		entityManager.getTransaction().begin();
		entityManager.persist(companyUser);
		entityManager.getTransaction().commit();
	}

}
