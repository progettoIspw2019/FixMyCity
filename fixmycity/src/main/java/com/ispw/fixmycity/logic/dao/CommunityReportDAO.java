package com.ispw.fixmycity.logic.dao;

import java.util.List;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.model.CommunityReport;

public interface CommunityReportDAO {

	public CommunityReport save(CommunityReport entity);

	public CommunityReport findByPrimaryKey(Long id);

	public List<CommunityReportBean> findAll();

	void delete(CommunityReport entity);
}
