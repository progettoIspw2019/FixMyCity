<!doctype html>
<%@page import="com.ispw.fixmycity.logic.bean.BaseUserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ispw.fixmycity.logic.view.LoginForm"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>

<html lang="en">

<%
	String username = new String();
	String password = new String();
	username = (String) request.getParameter("inputUsername");
	password = (String) request.getParameter("inputPassword");
	BaseUserBean userBean = new BaseUserBean();

	if (username != null && password != null && !username.isBlank() && !password.isBlank()) {
		userBean.setUsername(username);
		userBean.setPassword(password);
		SessionView.setUsername(username);
		BaseUserBean responseFromSystem = new SystemFacade().isSignedUp(userBean);
		if (responseFromSystem.getMode() == UserMode.CITIZEN) {
			SessionView.setMode(UserMode.CITIZEN);
			response.sendRedirect("home_citizen.jsp");
	
		} else if (responseFromSystem.getMode() == UserMode.COMPANY) {
			SessionView.setMode(UserMode.COMPANY);
			response.sendRedirect("home_company.jsp");
		}
	}

%>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" href="style/style.css">

<title>FixMyCity</title>
</head>

<body>
	<div class="container-fluid h-100" id="login-bg">
		<div class="container w-25 p-3 valign-center" id="login-box">
			<div class="container  ">
				<div class="row ">
					<div class="col-sm">
						<p id="welcome">Welcome to FixMyCity</p>
					</div>
				</div>
				<form action="login.jsp" method="GET">
					<div class="row">
						<div class="col-sm">
							<div class="form-group">
								<input type="text" class="form-control" name="inputUsername"
									placeholder="Username">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm">
							<div class="form-group">
								<input type="password" class="form-control" name="inputPassword"
									placeholder="Password">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm">
							<button type="submit" class="btn btn-primary w-100">Login</button>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-sm">
						<button class="btn btn-secondary w-100 mt15">Sign up</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>

</html>