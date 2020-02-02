package com.ispw.fixmycity.logic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.model.CommunityReport;

public class CommunityReportDAOImpl extends BaseDAOImpl implements CommunityReportDAO {

	private EntityManager entityManager;

	@Override
	public List<CommunityReportBean> findAll() {

		entityManager = getEntityManager();
		Query<CommunityReport> queryResult = (Query<CommunityReport>) entityManager.createQuery("from CommunityReport");
		List<CommunityReport> result = new ArrayList<CommunityReport>(queryResult.list());
		List<CommunityReportBean> resultBean = new ArrayList<CommunityReportBean>();

		for (CommunityReport cp : result) {

			// TODO il bean ha solo pochi dati in questo momento
			CommunityReportBean report = new CommunityReportBean();
			report.setLatitude(cp.getLatitude());
			report.setLongitude(cp.getLongitude());
			report.setDescription(cp.getFullDescription());
			resultBean.add(report);

		}

		return resultBean;
	}

	// Questi metodi sono tutti commentati perché passeremo dall'uso di
	// sessionFactory a quello di entityManager
	@Override
	public CommunityReport findByPrimaryKey(Long id) {
//		Session session = sessionFactory.getCurrentSession();
//		Object o = session.load(CommunityReport.class, id);
//		return (CommunityReport) o;
		return null;
	}

	@Override
	public CommunityReport save(CommunityReport entity) {
//		Session session = sessionFactory.getCurrentSession();
//		session.saveOrUpdate(entity);
//		return entity;
		return null;

	}

	@Override
	public void delete(CommunityReport entity) {
//		sessionFactory.getCurrentSession().delete(entity);

	}

	@Override
	public void assignVolunteeringEvent(Long eventId) {
		// TODO Auto-generated method stub

	}
}
