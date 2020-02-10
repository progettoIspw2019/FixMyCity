package com.ispw.fixmycity.logic.view;

import java.util.List;

import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.controller.LoadReportsController;

public class MyReportsView {

	public List<CommunityReportBeanView> getMyCommunityReports() {

		LoadReportsController controller = new LoadReportsController();
		return controller.getMyCommunityReports();

	}

	public List<CompanyReportBeanView> getMyCompanyReports() {
		LoadReportsController controller = new LoadReportsController();
		return controller.getMyCompanyReports();
	}

}
