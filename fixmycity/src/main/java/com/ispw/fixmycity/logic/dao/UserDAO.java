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

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public UserDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public CitizenUser findAllCitizensFromCredentials(BaseUserBean user) {
		entityManager = entityManagerFactory.createEntityManager();

		return (CitizenUser) entityManager.createNamedQuery("CitizenUser.findAllFromCredentials")
				.setParameter("input_username", user.getUsername()).setParameter("input_pwd", user.getPassword())
				.getSingleResult();
	}

	public CompanyUser findAllCompanyUserFromCredentials(BaseUserBean user) {
		return (CompanyUser) entityManager.createNamedQuery("CompanyUser.findAllFromCredentials")
				.setParameter("input_username", user.getUsername()).setParameter("input_pwd", user.getPassword())
				.getSingleResult();
	}

	public boolean userExists(String username) {
		return false;
	}

	public void insertCitizenUser(CitizenUserBean user) {
		CitizenUser citizenUser = new CitizenUser();

		citizenUser.setFromBean(user);
		citizenUser.setCommunityReports(new ArrayList<CommunityReport>());
		citizenUser.setCompanyReports(new ArrayList<CompanyReport>());
		citizenUser.setVolunteeringEvents(new ArrayList<VolunteeringEvent>());
		System.out.println("FDSKAJSDKJLFAKSDJFHAKSDFJHAKSJDFHAKJSDHF >>>>>>>>>>>>>>>>>>>>>>>>>> "
				+ citizenUser.getProfilePicture().length);

		entityManager.getTransaction().begin();
		entityManager.persist(citizenUser);
		entityManager.getTransaction().commit();

	}

	public void insertCompanyUser(CompanyUserBean user) {

	}

}
