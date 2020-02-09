package com.ispw.fixmycity.logic.bean;

import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CompanyUser;

public class CompanyReportBean extends ReportBean {
	private CompanyUser companyId;
	private CitizenUser citUser;

	public CompanyUser getCompany() {
		return companyId;
	}

	public void setCompany(CompanyUser compUser) {
		this.companyId = compUser;
	}

	public CitizenUser getCitizen() {
		return citUser;
	}

	public void setCitizen(CitizenUser citUser) {
		this.citUser = citUser;
	}
}
