package com.ispw.fixmycity.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.controller.VolunteeringEventController;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyUserDAO;
import com.ispw.fixmycity.logic.exceptions.CompanyReportIsAcceptedException;
import com.ispw.fixmycity.logic.exceptions.CouldNotConnectToGeolocationServiceException;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.ispw.fixmycity.logic.exceptions.InvalidDateIntervalException;
import com.ispw.fixmycity.logic.exceptions.InvalidFieldException;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.exceptions.UserNotFoundException;
import com.ispw.fixmycity.logic.model.City;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.Roma;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

public class JUnitFunctionalityTests {
	int id;
	private static final byte[] Null = null;

	@Test
	public void testSignupNewCitizenUser() throws UserNotFoundException { // test 1

		String username = "company";

		CitizenUserBean userBean = new CitizenUserBean();
		userBean.setPassword("pwd");
		userBean.setUsername(username);
		userBean.setImage(null);
		userBean.setMode(UserMode.CITIZEN);
		userBean.setCity(CityEnum.ROMA);
		SystemFacade facade = new SystemFacade();
		facade.signupCitizenUser(userBean);
		BaseUserBean baseUserBean = new BaseUserBean();
		baseUserBean.setUsername(username);
		baseUserBean.setPassword("pwd");
		baseUserBean = new SystemFacade().isSignedUp(baseUserBean);
		SessionView.setUsername(baseUserBean.getUsername());
		assertEquals(username, SessionView.getUsername());

	}

	@Test
	public void testSignUpCompanyUser() { // test 2
		CompanyUserDAO dao = new CompanyUserDAO();
		SystemFacade facade = new SystemFacade();
		CompanyUserBean bean = new CompanyUserBean();
		bean.setCategory("Public transport");
		bean.setCity(CityEnum.ROMA);
		bean.setCompanyName("name");
		bean.setImage(Null);
		bean.setPassword("password");
		bean.setUsername("comp");
		Boolean check = facade.signupCompanyUser(bean);
		assertEquals("name", dao.findByPrimaryKey("comp").getCompanyName());

	}

	@Test
	public void TestCreateCommunityReports()
			throws UserNotFoundException, ParseException, NoMatchingCompanyFound, InvalidReportException {
		CitizenUserBean userBean = new CitizenUserBean(); // test 9
		userBean.setPassword("pwd");
		userBean.setUsername("citizen");
		userBean.setImage(null);
		userBean.setMode(UserMode.CITIZEN);
		userBean.setCity(CityEnum.ROMA);
		SystemFacade facade = new SystemFacade();
		facade.signupCitizenUser(userBean);
		BaseUserBean baseUserBean = new BaseUserBean();
		baseUserBean.setUsername("citizen");
		baseUserBean.setPassword("pwd");
		baseUserBean = new SystemFacade().isSignedUp(baseUserBean);
		SessionView.setUsername(baseUserBean.getUsername());

		double f = 41.5;
		CommunityReportDAO commRepDAO = new CommunityReportDAO();
		int checks;
		SystemFacade facades = new SystemFacade();
		ReportBeanView bean = new ReportBeanView();
		bean.setCity("ROMA");
		bean.setSubmitter("citizen");
		CompanyReportBean compRepBean = new CompanyReportBean();
		bean.setAddress("via //");
		String sDate2 = "31/12/2028";
		bean.setCategory("Vandalism");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		bean.setDateSubmission(date);
		bean.setDescription("problema... ecc");
		bean.setLatitude(BigDecimal.valueOf(f));
		bean.setLongitude(BigDecimal.valueOf(f));
		bean.setTitle("title");
		bean.setImage(Null);
		checks = facades.reportProblem(bean);
		// f=f+10;
		int id = commRepDAO.findByPrimaryKey(checks).getIdReport();
		assertEquals(id, checks);

	}

	@Test
	public void createCompanyReports()
			throws ParseException, NoMatchingCompanyFound, InvalidReportException, UserNotFoundException { // test3
		CitizenUserBean userBean = new CitizenUserBean();
		userBean.setPassword("pwd");
		userBean.setUsername("company");
		userBean.setImage(null);
		userBean.setMode(UserMode.CITIZEN);
		userBean.setCity(CityEnum.ROMA);
		SystemFacade facade = new SystemFacade();
		facade.signupCitizenUser(userBean);
		BaseUserBean baseUserBean = new BaseUserBean();
		baseUserBean.setUsername("company");
		baseUserBean.setPassword("pwd");
		baseUserBean = new SystemFacade().isSignedUp(baseUserBean);
		SessionView.setUsername(baseUserBean.getUsername());

		CompanyUserDAO dao = new CompanyUserDAO();
		SystemFacade facade1 = new SystemFacade();
		CompanyUserBean bean1 = new CompanyUserBean();
		bean1.setCategory("Public transport");
		bean1.setCity(CityEnum.ROMA);
		bean1.setCompanyName("name");
		bean1.setImage(Null);
		bean1.setPassword("password");
		bean1.setUsername("comp");
		facade1.signupCompanyUser(bean1);

		double f = 41.5;
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		int checks;
		SystemFacade facades = new SystemFacade();
		ReportBeanView bean = new ReportBeanView();
		bean.setCity("ROMA");
		bean.setCategory("Public transport");
		bean.setAddress("via armando diaz 77");
		bean.setSubmitter("company");
		CompanyReportBean compRepBean = new CompanyReportBean();
		bean.setAddress("via //");
		String sDate2 = "31/12/2028";
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		bean.setDateSubmission(date);
		bean.setDescription("problema... ecc");
		bean.setLatitude(BigDecimal.valueOf(f));
		bean.setLongitude(BigDecimal.valueOf(f));
		bean.setTitle("title");
		bean.setImage(Null);
		checks = facades.reportProblem(bean);
		// f=f+10;
		int id = compRepDAO.findByPrimaryKey(checks).getIdReport();
		assertEquals(id, checks);
	}

	@Test
	public void TestAcceptJobCase() throws ParseException, CompanyReportIsAcceptedException,
			InvalidDateIntervalException, NoMatchingCompanyFound, InvalidReportException {
		// test 4
		CompanyUserDAO dao = new CompanyUserDAO();
		SystemFacade facade = new SystemFacade();
		CompanyUserBean bean = new CompanyUserBean();
		bean.setCategory("Damaged street");
		bean.setCity(CityEnum.ROMA);
		bean.setCompanyName("name");
		bean.setImage(Null);
		bean.setPassword("password");
		bean.setUsername("comp");
		Boolean check = facade.signupCompanyUser(bean);

		double f = 41.5;
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		int check3;
		SystemFacade facade2 = new SystemFacade();
		ReportBeanView beans = new ReportBeanView();
		beans.setCity("Roma");
		beans.setCategory("Damaged street");
		beans.setSubmitter("company");
		CompanyReportBean compRepBean = new CompanyReportBean();
		beans.setAddress("via //");
		String sDate2 = "31/12/2028";
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		beans.setDateSubmission(date);
		beans.setDescription("problema... ecc");
		beans.setLatitude(BigDecimal.valueOf(f));
		beans.setLongitude(BigDecimal.valueOf(f));
		beans.setTitle("title");
		beans.setImage(Null);
		check3 = facade2.reportProblem(beans);
		// f=f+10;
		int id = compRepDAO.findByPrimaryKey(check3).getIdReport();

		SystemFacade facade1 = new SystemFacade();
		JobBeanView beans2 = new JobBeanView();
		beans2.setRelatedReport(id);
		String sDate1 = "31/12/2021";
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		String sDate3 = "31/12/2022";
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
		beans2.setStartDate(date1);
		beans2.setEndDate(date2);
		Boolean checks = facade.jobCreation(beans2);
		assertEquals(true, check3);
	}

	@Test
	public void TestRefuseJobCaseWithoutDeletion()
			throws CompanyReportIsAcceptedException, NoMatchingCompanyFound, InvalidReportException, ParseException {

		// test 5
		CompanyUserDAO dao = new CompanyUserDAO();
		SystemFacade facade = new SystemFacade();
		CompanyUserBean bean = new CompanyUserBean();
		bean.setCategory("Abandoned building");
		bean.setCity(CityEnum.ROMA);
		bean.setCompanyName("name");
		bean.setImage(Null);
		bean.setPassword("password");
		bean.setUsername("comp");
		Boolean check = facade.signupCompanyUser(bean);

		double f = 41.5;
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		int check3;
		SystemFacade facade2 = new SystemFacade();
		ReportBeanView beans = new ReportBeanView();
		beans.setCity("Roma");
		beans.setCategory("Abandoned building");
		beans.setSubmitter("company");
		CompanyReportBean compRepBean = new CompanyReportBean();
		beans.setAddress("via //");
		String sDate2 = "31/12/2028";
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		beans.setDateSubmission(date);
		beans.setDescription("problema... ecc");
		beans.setLatitude(BigDecimal.valueOf(f));
		beans.setLongitude(BigDecimal.valueOf(f));
		beans.setTitle("title");
		beans.setImage(Null);
		check3 = facade2.reportProblem(beans);
		// f=f+10;
		int id = compRepDAO.findByPrimaryKey(check3).getIdReport();

		SystemFacade facades = new SystemFacade();
		JobBeanView bean1 = new JobBeanView();
		bean1.setRelatedReport(id);
		int checking = facades.rejectReport(bean1);
		assertEquals(-1, checking);
	}

	@Test
	public void TestRefuseJobWithDeletion()
			throws CompanyReportIsAcceptedException, NoMatchingCompanyFound, InvalidReportException, ParseException {
		// test6
		CompanyUserDAO dao = new CompanyUserDAO();
		SystemFacade facade = new SystemFacade();
		CompanyUserBean bean = new CompanyUserBean();
		bean.setCategory("Security problem");
		bean.setCity(CityEnum.ROMA);
		bean.setCompanyName("name");
		bean.setImage(Null);
		bean.setPassword("password");
		bean.setUsername("comp");
		Boolean check = facade.signupCompanyUser(bean);

		double f = 41.5;
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		int check3;
		SystemFacade facade2 = new SystemFacade();
		ReportBeanView beans = new ReportBeanView();
		beans.setCity("Roma");
		beans.setCategory("Security problem");
		beans.setSubmitter("company");
		CompanyReportBean compRepBean = new CompanyReportBean();
		beans.setAddress("via //");
		String sDate2 = "31/12/2028";
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		beans.setDateSubmission(date);
		beans.setDescription("problema... ecc");
		beans.setLatitude(BigDecimal.valueOf(f));
		beans.setLongitude(BigDecimal.valueOf(f));
		beans.setTitle("title");
		beans.setImage(Null);
		check3 = facade2.reportProblem(beans);
		// f=f+10;
		int id = compRepDAO.findByPrimaryKey(check3).getIdReport();

		SystemFacade facades = new SystemFacade();
		int result = 0;
		JobBeanView bean1 = new JobBeanView();
		CompanyReportDAO compRepDao = new CompanyReportDAO();
		bean1.setRelatedReport(id);
		CompanyReport compRep = compRepDao.findByPrimaryKey(bean1.getRelatedReport());
		for (int i = 0; i < 3; i++) {

			result = facades.rejectReport(bean1);
		}
		assertEquals(1, result);
	}

	@Test
	public void testCreateVolunteeringEventCase() throws ParseException, NoMatchingCompanyFound, // test 7
			CouldNotConnectToGeolocationServiceException, InvalidFieldException, InvalidReportException {

		SystemFacade facade = new SystemFacade();
		VolunteeringEventBean event = new VolunteeringEventBean();
		ReportBeanView reportToAdd = new ReportBeanView();
		City city = new Roma();
		reportToAdd.setCategory(city.getCommunityCategories().get(0));
		reportToAdd.setCity(CityEnum.ROMA.toString());
		reportToAdd.setDateSubmission(new SimpleDateFormat("dd/MM/yyyy").parse("15/02/2020"));
		reportToAdd.setDescription("Junit Report Test Description");
		reportToAdd.setImage(null);
		reportToAdd.setLatitude(BigDecimal.valueOf(41.87590410));
		reportToAdd.setLongitude(BigDecimal.valueOf(12.48207808));
		facade.setAddressForReport(reportToAdd.getLongitude(), reportToAdd.getLatitude());
		reportToAdd.setAddress(SessionView.getAddressSetOnMap().getRoad());

		reportToAdd.setSubmitter("asd");
		reportToAdd.setTitle("Junit Report Test Title");
		Integer reportId = facade.reportProblem(reportToAdd);

		CommunityReportBean bean = new CommunityReportBean();
		bean.setIdReport(reportId);
		event.setCommunityReport(bean);
		event.setFullDescription("JUnit volunteering event test");
		event.setCreationDate(new SimpleDateFormat("dd/MM/yyyy").parse("16/02/2020"));
		event.setEventDate(new SimpleDateFormat("dd/MM/yyyy").parse("19/04/2020"));
		event.setEventTime("18:00");
		event.setTitle("JUnit test event");
		SessionView.setUsername("asd");

		boolean result = facade.createVolunteeringEvent(event);
		assertEquals(true, result);
	}

	@Test
	public void testLeaveVolunteeringEvent() throws EmptyResultListException, UserNotFoundException {// test 8

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;

		String generatedUsername = new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		CitizenUserBean userBean = new CitizenUserBean();
		userBean.setPassword("pwd");
		userBean.setUsername(generatedUsername);
		userBean.setImage(null);
		userBean.setMode(UserMode.CITIZEN);
		userBean.setCity(CityEnum.ROMA);
		SystemFacade facade = new SystemFacade();
		facade.signupCitizenUser(userBean);
		BaseUserBean baseUserBean = new BaseUserBean();
		baseUserBean.setUsername(generatedUsername);
		baseUserBean.setPassword("pwd");
		baseUserBean = new SystemFacade().isSignedUp(baseUserBean);
		SessionView.setUsername(baseUserBean.getUsername());
		assertEquals(generatedUsername, SessionView.getUsername());

		VolunteeringEventController vec = new VolunteeringEventController();
		VolunteeringEventListElementBean event = vec.getActiveVolunteeringEvents().get(0);
		VolunteeringEventBean eventBean = new VolunteeringEventBean();
		eventBean.setEventId(event.getEventId());
		boolean result = vec.joinVolunteeringEvent(eventBean);

		assertEquals(true, result);

	}
}
