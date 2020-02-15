<!doctype html>
<%@page import="com.ispw.fixmycity.logic.bean.VolunteeringEventBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.ActiveEvent"%>
<%@page
	import="com.ispw.fixmycity.logic.exceptions.EmptyResultListException"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page
	import="com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64"%>


<html lang="en">
<%
	if (SessionView.getMode() != UserMode.CITIZEN) {
		response.sendRedirect("login.jsp");
	}
	List<VolunteeringEventListElementBean> events = new ArrayList<>();

	String action = request.getParameter("btnAction");
	String eventId = request.getParameter("event");

	if (action != null && eventId != null) {
		SystemFacade facade = new SystemFacade();
		VolunteeringEventBean bean = new VolunteeringEventBean();
		bean.setEventId(Integer.parseInt(eventId));
		if (action.equalsIgnoreCase("JOIN")) {
			facade.joinVolunteeringEvent(bean);
		} else if (action.equalsIgnoreCase("QUIT")) {
			facade.quitVolunteeringEvent(bean);
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
<script>
	function printError(message) {
		document.getElementById("errorPrinterId").innerHTML = message;
	}
</script>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark" aria-label="">
		<ul class="nav navbar-nav navbar-left">
			<li><span class="navbar-brand mb-0 h1">FixMyCity</span></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand mb-0 h1">
					<%
						out.println(SessionView.getUsername());
					%>
			</span> <img
				src="data:image/jpeg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(SessionView.getImageProfile())));%>"
				width="40" height="40" class="rounded-circle" alt=""></li>
		</ul>
	</nav>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">Menu</div>
			<div class="list-group list-group-flush">
				<a href="home_citizen.jsp"
					class="list-group-item list-group-item-action bg-light">Map</a> <a
					href="events.jsp"
					class="list-group-item list-group-item-action bg-light">Active
					Events</a> <a href="myreports.jsp"
					class="list-group-item list-group-item-action bg-light">My
					Reports</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">

			<div class="container-fluid p-0">
				<div id="errorPrinterId"></div>
				<%
					String errTemplate = "<script>printError('{0}');</script>";
					String errMessage = "";
					try {
						events = new SystemFacade().getActiveVolunteeringEvents();
					} catch (EmptyResultListException e) {
						errMessage += e.getMessage() + "<br\\>";
					}

					if (!errMessage.isBlank()) {
						String err = errTemplate.replace("{0}", errMessage);
						out.println(err);
					}
				%>


				<form action="events.jsp" method="GET">
					<input type="hidden" name="btnAction" id="btnActionId" value="">
					<input type="hidden" name="event" id="eventId" value="">


					<ul class="list-group">
						<!-- Single Row for JSP -->



						<%
							for (VolunteeringEventListElementBean event : events) {
						%>
						<li class="list-group-item ">
							<div class="container ml-0">
								<div class="row  ">
									<div class="col-sm-auto  ">
										<img
											src="data:image/jpeg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(event.getImage())));%>"
											width="150" height="auto" alt="">
									</div>
									<div class="col-lg-auto">
										<div class="row">
											<h5>
												<%
													out.println(event.getTitle());
												%>
											</h5>
										</div>
										<div class="row">
											<%
												out.println(event.getFullDescription());
											%>
										</div>
										<div class="row">
											<%
												out.println(event.getAddress());
											%>
										</div>
										<div class="row">
											<%
												out.println(event.getEventDate());
											%>
										</div>
									</div>

									<div class="col">
										<div class="row justify-content-end mt-2">
											<%
												if (event.hasUserJoined()) {
														out.println("<input type='submit' onclick='quitEvent(" + event.getEventId()
																+ ")' class='btn btn-quit' value ='Quit'>");
													} else {
														out.println("<input type='submit' onclick='joinEvent(" + event.getEventId()
																+ ")' class='btn btn-join' value ='Join'>");
													}
											%>
										</div>
										<div class="row justify-content-end mt-2dot2">
											<img src="style/icons/people-fill.svg" alt="" width="24"
												height="24">
											<%
												out.println(event.getParticipantsNumber());
											%>
										</div>
									</div>

								</div>

							</div>
						</li>
						<%
							}
						%>
						<!-- End of Single Row for JSP  -->

					</ul>
				</form>
			</div>



		</div>
	</div>

</body>
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
<script>
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});

	function joinEvent(evId) {
		document.getElementById("btnActionId").value = "JOIN";
		document.getElementById("eventId").value = evId;
	}
	function quitEvent(evId) {
		document.getElementById("btnActionId").value = "QUIT";
		document.getElementById("eventId").value = evId;
	}
</script>
</body>
</html>