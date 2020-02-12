package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.model.CompanyUser;

public class CompanyReportBean extends ReportBean {
	private CompanyUser companyId;

	public CompanyUser getCompany() {
		return companyId;
	}

	public void setCompany(CompanyUser compUser) {
		this.companyId = compUser;
	}
}
