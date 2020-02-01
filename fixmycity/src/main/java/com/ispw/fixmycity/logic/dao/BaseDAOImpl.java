package com.ispw.fixmycity.logic.dao;

import java.util.HashMap;
import java.util.Map;

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
		try {
			Class.forName("org.hibernate.jpa.HibernatePersistenceProvider");
			System.out.println("called class");
		} catch (Exception e) {
			e.printStackTrace();
		}

		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

}
