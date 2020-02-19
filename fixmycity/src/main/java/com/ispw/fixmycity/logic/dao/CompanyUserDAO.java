package com.ispw.fixmycity.logic.dao;

import com.ispw.fixmycity.logic.model.CompanyUser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class CompanyUserDAO {
	private EntityManagerFactory entityManagerFactory;

	public CompanyUserDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<CompanyUser> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<CompanyUser> result = entityManager.createNamedQuery("CompanyUser.findAll", CompanyUser.class)
				.getResultList();
		entityManager.close();
		return result;
	}

	public CompanyUser findByPrimaryKey(String username) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CompanyUser result = entityManager.createNamedQuery("CompanyUser.find", CompanyUser.class)
				.setParameter("input_username", username).getSingleResult();
		entityManager.close();
		return result;
	}

	public CompanyUser findByCategoryAndCity(String category, String city) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			CompanyUser result = entityManager.createNamedQuery("CompanyUser.findByCategoryAndCity", CompanyUser.class)
					.setParameter("input_category", category).setParameter("input_city", city).getSingleResult();
			entityManager.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}
}
