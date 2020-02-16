package com.ispw.fixmycity.logic.controller;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.JobDAO;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.Job;
import com.ispw.fixmycity.logic.view.SessionView;

public class LoadReportsController {

	public LoadReportsController() {
		// there is nothing to initialize
	}

	public List<CommunityReportBeanView> getCommunityReports() {

		CommunityReportDAO daoComm = new CommunityReportDAO();

		List<CommunityReport> reports = daoComm.findAll();

		ArrayList<CommunityReportBeanView> commRepBeanList = new ArrayList<>();

		for (var rep : reports) {
			CommunityReportBeanView commRepBean = new CommunityReportBeanView();

			commRepBean.setAddress(rep.getAddress());
			commRepBean.setCategory(rep.getCategory());
			commRepBean.setCity(rep.getCity());
			commRepBean.setDateSubmission(rep.getDateSubmission());
			commRepBean.setDescription(rep.getFullDescription());
			commRepBean.setImage(rep.getImage());
			commRepBean.setLatitude(rep.getLatitude());
			commRepBean.setLongitude(rep.getLongitude());
			commRepBean.setTitle(rep.getTitle());
			commRepBean.setSubmitter(rep.getCitizenUser().getUsername());

			ArrayList<String> eventsStr = new ArrayList<>();

			rep.getVolunteeringEvents().forEach(ev -> eventsStr.add(String.valueOf(ev.getIdEvent())));

			commRepBean.setEvents(eventsStr);

			commRepBeanList.add(commRepBean);
		}

		return commRepBeanList;
	}

	public List<CompanyReportBeanView> getCompanyReports() {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();

		List<CompanyReport> reports = compRepDAO.findAll();

		ArrayList<CompanyReportBeanView> compRepBeanList = new ArrayList<>();
		for (var rep : reports) {
			CompanyReportBeanView compRepBean = new CompanyReportBeanView();

			compRepBean.setAddress(rep.getAddress());
			compRepBean.setCategory(rep.getCategory());
			compRepBean.setCity(rep.getCity());
			compRepBean.setDateSubmission(rep.getDateSubmission());
			compRepBean.setDescription(rep.getFullDescription());
			compRepBean.setImage(rep.getImage());
			compRepBean.setLatitude(rep.getLatitude());
			compRepBean.setLongitude(rep.getLongitude());
			compRepBean.setTitle(rep.getTitle());
			compRepBean.setSubmitter(rep.getCitizenUser().getUsername());
			compRepBean.setCompanyRelated(rep.getCompanyUser().getUsername());
			ArrayList<String> jobsStr = new ArrayList<>();

			rep.getJobs().forEach(j -> jobsStr.add(String.valueOf(j.getIdJob())));

			compRepBean.setJobs(jobsStr);

			compRepBeanList.add(compRepBean);
		}

		return compRepBeanList;
	}

	public List<CommunityReportBeanView> getMyCommunityReports() throws EmptyResultListException {

		CommunityReportDAO daoComm = new CommunityReportDAO();

		List<CommunityReport> reports = daoComm.findAll();

		ArrayList<CommunityReportBeanView> commRepBeanList = new ArrayList<>();
		String username = SessionView.getUsername();
		for (var rep : reports) {
			if (rep.getCitizenUser().getUsername().equals(username)) {
				CommunityReportBeanView commRepBean = new CommunityReportBeanView();

				commRepBean.setAddress(rep.getAddress());
				commRepBean.setCategory(rep.getCategory());
				commRepBean.setCity(rep.getCity());
				commRepBean.setDateSubmission(rep.getDateSubmission());
				commRepBean.setDescription(rep.getFullDescription());
				commRepBean.setImage(rep.getImage());
				commRepBean.setLatitude(rep.getLatitude());
				commRepBean.setLongitude(rep.getLongitude());
				commRepBean.setTitle(rep.getTitle());

				ArrayList<String> eventsStr = new ArrayList<>();

				rep.getVolunteeringEvents().forEach(ev -> eventsStr.add(String.valueOf(ev.getIdEvent())));

				commRepBean.setEvents(eventsStr);

				commRepBeanList.add(commRepBean);
			}
		}
		if (commRepBeanList.isEmpty()) {
			throw new EmptyResultListException("There are no reports for the Community", null);
		}
		return commRepBeanList;

	}

	public List<CompanyReportBeanView> getMyCompanyReports() throws EmptyResultListException {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();

		List<CompanyReport> reports = compRepDAO.findAll();

		ArrayList<CompanyReportBeanView> compRepBeanList = new ArrayList<>();
		String username = SessionView.getUsername();

		for (var rep : reports) {
			if (rep.getCitizenUser().getUsername().equals(username)) {

				CompanyReportBeanView compRepBean = new CompanyReportBeanView();

				compRepBean.setAddress(rep.getAddress());
				compRepBean.setCategory(rep.getCategory());
				compRepBean.setCity(rep.getCity());
				compRepBean.setDateSubmission(rep.getDateSubmission());
				compRepBean.setDescription(rep.getFullDescription());
				compRepBean.setImage(rep.getImage());
				compRepBean.setLatitude(rep.getLatitude());
				compRepBean.setLongitude(rep.getLongitude());
				compRepBean.setTitle(rep.getTitle());
				compRepBean.setCompanyRelated(rep.getCompanyUser().getUsername());
				ArrayList<String> jobsStr = new ArrayList<>();

				rep.getJobs().forEach(j -> jobsStr.add(String.valueOf(j.getIdJob())));

				compRepBean.setJobs(jobsStr);

				compRepBeanList.add(compRepBean);
			}
		}

		if (compRepBeanList.isEmpty()) {
			throw new EmptyResultListException("There are no reports for the Companies", null);
		}

		return compRepBeanList;
	}

	public List<CompanyReportBeanView> getCompanyReportsWihoutJob() throws EmptyResultListException {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();

		List<CompanyReport> reports = compRepDAO.findAll();

		ArrayList<CompanyReportBeanView> compRepBeanList = new ArrayList<>();
		String username = SessionView.getUsername();

		for (var rep : reports) {
			if (rep.getCompanyUser().getUsername().equals(username) && rep.getJobs().isEmpty()) {

				CompanyReportBeanView compRepBean = new CompanyReportBeanView();
				compRepBean.setAddress(rep.getAddress());
				compRepBean.setCategory(rep.getCategory());
				compRepBean.setCity(rep.getCity());
				compRepBean.setDateSubmission(rep.getDateSubmission());
				compRepBean.setDescription(rep.getFullDescription());
				compRepBean.setImage(rep.getImage());
				compRepBean.setLatitude(rep.getLatitude());
				compRepBean.setLongitude(rep.getLongitude());
				compRepBean.setTitle(rep.getTitle());
				compRepBean.setCompanyRelated(rep.getCompanyUser().getUsername());

				compRepBeanList.add(compRepBean);
			}
		}
		if (compRepBeanList.isEmpty()) {
			throw new EmptyResultListException("There are no pending reports without a Job assigned", null);
		}
		return compRepBeanList;
	}

	public List<JobBeanView> getJobsByCurrentCompany() throws EmptyResultListException {
		JobDAO jobDAO = new JobDAO();

		List<Job> jobs = jobDAO.findAll();

		ArrayList<JobBeanView> jobBeanList = new ArrayList<>();
		String username = SessionView.getUsername();

		for (var job : jobs) {
			if (job.getRelatedCompany().getUsername().equals(username)) {
				JobBeanView jobBean = new JobBeanView();
				jobBean.setEndDate(job.getEndDate());
				jobBean.setIdJob(job.getIdJob());
				jobBean.setJobInfo(job.getJobInfo());
				CompanyReportBeanView compRep = new CompanyReportBeanView();
				compRep.setAddress(job.getCompanyReport().getAddress());
				compRep.setDescription(job.getCompanyReport().getFullDescription());
				compRep.setImage(job.getCompanyReport().getImage());
				jobBean.setRelatedReport(compRep);
				jobBean.setStartDate(job.getStartDate());
				jobBean.setState(job.getCompanyReport().getStatus());
			}
		}
		if (jobBeanList.isEmpty()) {
			throw new EmptyResultListException("There are no Jobs accepted", null);
		}
		return jobBeanList;
	}
}
