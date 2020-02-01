package com.ispw.fixmycity.logic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface BaseDAO {

	EntityManagerFactory getEntityManagerFactory();

	EntityManager getEntityManager();

}
