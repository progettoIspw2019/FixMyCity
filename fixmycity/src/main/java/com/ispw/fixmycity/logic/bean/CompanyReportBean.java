package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.model.CompanyUser;

public class CompanyReportBean extends ReportBean {
	private CompanyUser companyIdBean;

	public CompanyUser getCompany() {
		return companyIdBean;
	}

	public void setCompany(CompanyUser compUser) {
		this.companyIdBean = compUser;
	}
}
