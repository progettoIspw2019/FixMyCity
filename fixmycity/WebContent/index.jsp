<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.LoginForm"%>

<%

	String redirect = new String();

	if (UserSessionBean.getInstance().getUserMode() == UserMode.CITIZEN
			&& UserSessionBean.getInstance().getActiveCitizenUser() != null) {
		redirect = "home_citizen.jsp";
	} else if (UserSessionBean.getInstance().getUserMode() == UserMode.COMPANY
			&& UserSessionBean.getInstance().getActiveCompanyUser() != null) {
		redirect = "home_company.jsp";
	} else {
		redirect = "login.jsp";
	}
	response.sendRedirect(redirect);
%>
