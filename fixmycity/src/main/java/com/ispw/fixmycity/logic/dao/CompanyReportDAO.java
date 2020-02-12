package com.ispw.fixmycity.logic.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.model.CompanyReport;

public class CompanyReportDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public CompanyReportDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public List<CompanyReport> findAll() {
		return entityManager.createNamedQuery("CompanyReport.findAll", CompanyReport.class).getResultList();
	}

	public CompanyReport findByPrimaryKey(Integer id) {
		return entityManager.find(CompanyReport.class, id);
	}

	public CompanyReport add(CompanyReportBean compRepBean) {
		CompanyReport compReport = new CompanyReport();
		compReport.setFromBean(compRepBean);
		Logger.getLogger("fixmycity").log(Level.INFO, compReport.getTitle() + "\n" + compReport.getCitizenUser());
		entityManager.getTransaction().begin();
		entityManager.persist(compReport);
		entityManager.getTransaction().commit();
		
		return compReport; // must return entity, auto-generated id might be useful
	}

	// Versione in cui i controller usano le entity
	public void update(CompanyReport compReport) {
		entityManager.getTransaction().begin();
		entityManager.persist(compReport);
		entityManager.getTransaction().commit();
	}

	public void delete(Integer id) {
		CompanyReport repRef = entityManager.getReference(CompanyReport.class, id);
		entityManager.remove(repRef);
	}
}
