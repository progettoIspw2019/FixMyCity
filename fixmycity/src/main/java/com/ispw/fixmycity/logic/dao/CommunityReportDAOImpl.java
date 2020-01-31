package com.ispw.fixmycity.logic.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.model.CommunityReport;

public class CommunityReportDAOImpl implements CommunityReportDAO {
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public CommunityReportDAOImpl() {
	}

	public CommunityReportDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<CommunityReportBean> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<CommunityReport> queryResult = session.createQuery("from CommunityReport");

		List<CommunityReport> result = new ArrayList<CommunityReport>();
		result = queryResult.list();

		List<CommunityReportBean> resultBean = new ArrayList<CommunityReportBean>();

		for (CommunityReport cp : result) {
			
			//TODO il bean ha solo pochi dati in questo momento
			CommunityReportBean report = new CommunityReportBean();
			report.setLatitude(cp.getLatitude());
			report.setLongitude(cp.getLongitude());
			report.setDescription(cp.getFullDescription());
			resultBean.add(report);

		}

		return resultBean;
	}

	@Override
	public CommunityReport findByPrimaryKey(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Object o = session.load(CommunityReport.class, id);
		return (CommunityReport) o;
	}

	@Override
	public CommunityReport save(CommunityReport entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(CommunityReport entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
}
