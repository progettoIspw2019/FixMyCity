package com.ispw.fixmycity.logic.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.Status;

public class CompanyReportDAO {

	private EntityManagerFactory entityManagerFactory;

	public CompanyReportDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<CompanyReport> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<CompanyReport> result = entityManager.createNamedQuery("CompanyReport.findAll", CompanyReport.class)
				.getResultList();
		entityManager.close();
		return result;
	}

	public CompanyReport findByPrimaryKey(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CompanyReport result = entityManager.find(CompanyReport.class, id);
		entityManager.close();
		return result;
	}

	public CompanyReport add(CompanyReportBean compRepBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CompanyReport compReport = new CompanyReport();
		compReport.setFromBean(compRepBean);
		compReport.setStatus(Status.PENDING.toString());
		compReport.initRejectCounter();
		Logger.getLogger("fixmycity").log(Level.INFO, compReport.getTitle() + "\n" + compReport.getCitizenUser());
		entityManager.getTransaction().begin();
		entityManager.persist(compReport);
		entityManager.getTransaction().commit();
		entityManager.close();
		return compReport; // must return entity, auto-generated id might be useful
	}

	// Versione in cui i controller usano le entity
	public void update(CompanyReport compReport) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(compReport);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void delete(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CompanyReport repRef = entityManager.getReference(CompanyReport.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(repRef);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<CompanyReport> findAllMyCompany(String compUsername) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CompanyUser compUser = new CompanyUserDAO().findByPrimaryKey(compUsername);
		List<CompanyReport> result = entityManager
				.createNamedQuery("CompanyReport.findAllFromCompanyUsername", CompanyReport.class)
				.setParameter("input_company", compUser).getResultList();
		entityManager.close();
		return result;
	}
}
