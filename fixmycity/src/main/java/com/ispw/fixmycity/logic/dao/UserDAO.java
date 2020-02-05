package com.ispw.fixmycity.logic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;

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
}
