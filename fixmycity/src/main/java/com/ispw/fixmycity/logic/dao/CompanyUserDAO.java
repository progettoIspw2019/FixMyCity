package com.ispw.fixmycity.logic.dao;

import com.ispw.fixmycity.logic.model.CompanyUser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class CompanyUserDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public CompanyUserDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<CompanyUser> findAll() {
		return entityManager.createNamedQuery("CompanyUser.findAll", CompanyUser.class).getResultList();
	}

	public CompanyUser findByPrimaryKey(String username) {
		return entityManager.createNamedQuery("CompanyUser.find", CompanyUser.class)
				.setParameter("input_username", username).getSingleResult();
	}

	public CompanyUser findByCategoryAndCity(String category, String city) {
		try {
			return entityManager.createNamedQuery("CompanyUser.findByCategoryAndCity", CompanyUser.class)
					.setParameter("input_category", category).setParameter("input_city", city).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
