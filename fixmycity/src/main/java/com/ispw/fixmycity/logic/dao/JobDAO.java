package com.ispw.fixmycity.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.JobBean;
import com.ispw.fixmycity.logic.model.Job;

public class JobDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public JobDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public List<Job> findAll(){
		return entityManager.createNamedQuery("Job.findAll", Job.class).getResultList();
	}
	
	public Job findByPrimaryKey(int id) {
		return entityManager.find(Job.class, id);
	}
	
	public Job add(JobBean jobBean) {
		Job job = new Job();
		job.setFromBean(jobBean);
		
		entityManager.getTransaction().begin();
		entityManager.persist(job);
		entityManager.getTransaction().commit();

		return job; // must return entity, auto-generated id might be useful
	}
	
	public void update(Job job) {
		entityManager.getTransaction().begin();
		entityManager.persist(job);
		entityManager.getTransaction().commit();
	}
	
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		Job job = entityManager.getReference(Job.class, id);
		
		job.getCompanyReport().removeJob(job);
		new CompanyReportDAO().update(job.getCompanyReport());
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		entityManager.remove(job);
		entityManager.getTransaction().commit();
	}
}
