package com.ispw.fixmycity.logic.dao;

import java.util.List;

import com.ispw.fixmycity.logic.model.CommunityReport;

public interface CommunityReportDAO {

	CommunityReport save(CommunityReport entity);

	CommunityReport findByPrimaryKey(Long id);

	List findAll();

	void delete(CommunityReport entity);
}
