package com.ispw.fixmycity.logic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class BaseDAOImpl implements BaseDAO {

	@PersistenceContext
	private static EntityManagerFactory entityManagerFactory;

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	@Override
	public EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		}
		return entityManagerFactory.createEntityManager();
	}

	public BaseDAOImpl() {
	}

}
