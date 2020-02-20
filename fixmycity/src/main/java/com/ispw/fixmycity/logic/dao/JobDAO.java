package com.ispw.fixmycity.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.JobBean;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.Job;
import com.ispw.fixmycity.logic.util.Status;

public class JobDAO {
	private EntityManagerFactory entityManagerFactory;

	public JobDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<Job> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Job> result = entityManager.createNamedQuery("Job.findAll", Job.class).getResultList();
		entityManager.close();
		return result;
	}

	public Job findByPrimaryKey(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Job result = entityManager.find(Job.class, id);
		entityManager.close();
		return result;
	}

	public Job add(JobBean jobBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Job job = new Job();
		job.setFromBean(jobBean);

		entityManager.getTransaction().begin();
		entityManager.persist(job);
		entityManager.getTransaction().commit();
		entityManager.close();

		return job; // must return entity, auto-generated id might be useful
	}

	public Job activateJob(JobBean jobBean, int crId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Job job = new Job();
		job.setFromBean(jobBean);
		CompanyReport tempReport = entityManager.getReference(CompanyReport.class, crId);
		tempReport.setStatus(Status.ACCEPTED.toString());
		tempReport.getJobs().add(job);
		entityManager.merge(tempReport);
		entityManager.persist(job);
		entityManager.getTransaction().commit();
		entityManager.close();

		return job;
	}

	public void update(Job job) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(job);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void delete(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Job job = entityManager.getReference(Job.class, id);

		job.getCompanyReport().removeJob(job);
		new CompanyReportDAO().update(job.getCompanyReport());
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		entityManager.remove(job);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
