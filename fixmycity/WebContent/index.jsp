<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>

<%
	if (SessionView.getMode() == UserMode.CITIZEN) {
		response.sendRedirect("home_citizen.jsp");
	} else if (SessionView.getMode() == UserMode.COMPANY) {
		response.sendRedirect("home_company.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
%>
