package com.ispw.fixmycity.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.junit.Test;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.controller.VolunteeringEventController;
import com.ispw.fixmycity.logic.exceptions.CouldNotConnectToGeolocationServiceException;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.ispw.fixmycity.logic.exceptions.InvalidFieldException;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.exceptions.UserNotFoundException;
import com.ispw.fixmycity.logic.model.City;
import com.ispw.fixmycity.logic.model.Roma;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

public class JUnitTest {

	@Test
	public void testCreateVolunteeringEventCase() throws ParseException, NoMatchingCompanyFound,
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
	public void testLeaveVolunteeringEvent() throws EmptyResultListException, UserNotFoundException {

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

	@Test
	public void testSignupNewCitizenUser() throws UserNotFoundException {

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

	}

}
