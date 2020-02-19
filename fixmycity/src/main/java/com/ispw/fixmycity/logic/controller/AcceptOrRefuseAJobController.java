package com.ispw.fixmycity.logic.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.bean.JobBean;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.JobDAO;
import com.ispw.fixmycity.logic.exceptions.CompanyReportIsAcceptedException;
import com.ispw.fixmycity.logic.exceptions.InvalidDateIntervalException;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.Job;
import com.ispw.fixmycity.logic.util.Status;

public class AcceptOrRefuseAJobController {

	public boolean jobCreation(JobBeanView bean) throws CompanyReportIsAcceptedException, InvalidDateIntervalException {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		CompanyReport compRep = compRepDAO.findByPrimaryKey(bean.getRelatedReport());

		if (compRep.getJobs() != null && !compRep.getJobs().isEmpty())
			throw new CompanyReportIsAcceptedException();

		JobBean jobBean = new JobBean();

		Date currDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

		if (bean.getStartDate().before(currDate)) {
			throw new InvalidDateIntervalException();
		}
		if (bean.getEndDate().before(bean.getStartDate())) {
			throw new InvalidDateIntervalException();
		}

		jobBean.setEndDate(bean.getEndDate());
		jobBean.setJobInfo(bean.getJobInfo());
		jobBean.setRelatedReport(compRep);

		jobBean.setStartDate(bean.getStartDate());
		compRep.setStatus(Status.ACCEPTED.toString());

		Job job = new JobDAO().add(jobBean);

		compRep.addJob(job);
		compRepDAO.update(compRep);
		return true;
	}

	public int rejectReport(JobBeanView bean) throws CompanyReportIsAcceptedException {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		CompanyReport compRep = compRepDAO.findByPrimaryKey(bean.getRelatedReport());
		if (compRep.getJobs() == null || compRep.getJobs().isEmpty()) {
			if (compRep.getRefuseCounter() < 3) {
				compRep.setRefuseDescription(bean.getRejectingMotivation());
				compRep.increaseRefuseCounter();
				compRep.setStatus(Status.REJECTED.toString());
				compRepDAO.update(compRep);
				return 1;

			} else {
				compRepDAO.delete(compRep.getIdReport());
				return -1;
			}
		} else {
			throw new CompanyReportIsAcceptedException();
		}
	}

	public List<CompanyReportBeanView> loadCompanyReports(String compUsername) {
		List<CompanyReport> reports = new CompanyReportDAO().findAllMyCompany(compUsername);
		List<CompanyReportBeanView> repBeanList = new ArrayList<>();

		reports.stream().forEach(rep -> {
			CompanyReportBeanView repBean = new CompanyReportBeanView();
			repBean.setAddress(rep.getAddress());
			repBean.setCategory(rep.getCategory());
			repBean.setCity(rep.getCity());
			repBean.setDateSubmission(rep.getDateSubmission());
			repBean.setDescription(rep.getFullDescription());
			repBean.setImage(rep.getImage());
			repBean.setLatitude(rep.getLatitude());
			repBean.setLongitude(rep.getLongitude());
			repBean.setSubmitter(rep.getCitizenUser().getUsername());
			repBean.setTitle(rep.getTitle());
			repBean.setStatus(rep.getStatus());
			repBean.setId(rep.getIdReport());
			repBeanList.add(repBean);
		});

		return repBeanList;

	}

}
