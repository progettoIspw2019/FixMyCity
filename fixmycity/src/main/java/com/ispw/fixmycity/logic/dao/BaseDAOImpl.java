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
		return entityManagerFactory.createEntityManager();
	}

	public BaseDAOImpl() {
		entityManagerFactory = Persistence.createEntityManagerFactory("com.ispw.logic.fixmycitydb");
	}

}
