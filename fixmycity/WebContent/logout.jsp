<%@page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>


<%
	if (SessionView.getMode() != UserMode.GUEST) {
		new SystemFacade().logout();
	}
	response.sendRedirect("login.jsp");
%>

</head>
<body>

</body>
</html>