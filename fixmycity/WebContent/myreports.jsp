<!doctype html>
<%@page import="com.ispw.fixmycity.logic.model.Job"%>
<%@page import="com.ispw.fixmycity.logic.bean.CommunityReportBeanView"%>
<%@page import="com.ispw.fixmycity.logic.bean.CompanyReportBeanView"%>
<%@page import="com.ispw.fixmycity.logic.bean.ReportBeanView"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="java.util.List"%>

<%
	if (SessionView.getMode() != UserMode.CITIZEN) {
		response.sendRedirect("index.jsp");
	}

	List<CommunityReportBeanView> commReports = new SystemFacade().getMyCommunityReports();
	List<CompanyReportBeanView> compReports = new SystemFacade().getMyCompanyReports();
%>

<html lang="en">
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
	<nav class="navbar navbar-dark bg-dark">
		<ul class="nav navbar-nav navbar-left">
			<li><span class="navbar-brand mb-0 h1">FixMyCity</span></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand mb-0 h1">User_96</span> <img
				src="style/img/placeholder-profile.jpg" width="40" height="40"
				class="rounded-circle" alt=""></li>
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
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom ">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav  mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link " href="#">
								<img src="style/icons/Document-text.svg" alt="" width="24"
								height="24"> Add a Report
						</a></li>
						<li class="nav-item active ml-4"><a class="nav-link" href="#"><img
								src="style/icons/Calendar.svg" alt="" width="24" height="24">
								Create an Event</a></li>
					</ul>
				</div>
			</nav>
			<div class="container-fluid p-0">
				<ul class="list-group">
					<%
						for (CommunityReportBeanView rep : commReports) {
					%>
					<!-- Single Row for JSP -->
					<li class="list-group-item ">
						<div class="container ml-0">
							<div class="row  ">
								<div class="col-sm-auto  ">
									<img
										src="http://3.citynews-romatoday.stgy.ovh/~media/original-hi/1635172152210/disservizi-a-roma-in-piazza-irnerio-55-piazza-irnerio-degrado-disordine-e-sporcizia.jpg"
										width="150" height="auto" alt="" />
								</div>
								<div class="col-lg-auto">
									<div class="row">
										<h5>
											<%
												out.println(rep.getTitle());
											%>
										</h5>
									</div>
									<div class="row">
										<%
											out.println(rep.getDescription());
										%>
									</div>
									<div class="row">
										<%
											out.println(rep.getAddress());
										%>
									</div>
									<div class="row">
										<%
											out.println(rep.getDateSubmission());
										%>
									</div>
								</div>

								<div class="col">
									<div class="row justify-content-end mt-2">
										<div class="label-community">Community Report</div>
									</div>
									<!-- if community report -->
									<div class="row justify-content-end mt-2dot2">

										<%
											if (rep.getEvents() == null || rep.getEvents().isEmpty()) {
													out.println("No event created");
												} else {
													out.println("Event created");
												}
										%>

									</div>

								</div>

							</div>

						</div>
					</li>
					<!-- End of Single Row for JSP  -->

					<%
						}
					%>


					<%
						for (CompanyReportBeanView rep : compReports) {
					%>


					<!-- Single Row for JSP -->
					<li class="list-group-item ">
						<div class="container ml-0">
							<div class="row  ">
								<div class="col-sm-auto  ">
									<img
										src="http://3.citynews-romatoday.stgy.ovh/~media/original-hi/1635172152210/disservizi-a-roma-in-piazza-irnerio-55-piazza-irnerio-degrado-disordine-e-sporcizia.jpg"
										width="150" height="auto" alt="" />
								</div>
								<div class="col-lg-auto">
									<div class="row">
										<h5>
											<%
												out.println(rep.getTitle());
											%>
										</h5>
									</div>
									<div class="row">
										<%
											out.println(rep.getDescription());
										%>
									</div>
									<div class="row">
										<%
											out.println(rep.getAddress());
										%>
									</div>
									<div class="row">
										<%
											out.println(rep.getDateSubmission());
										%>
									</div>
								</div>

								<div class="col">
									<div class="row justify-content-end mt-2">
										<div class="label-company">
											<%
												out.println(rep.getCompanyRelated());
											%>
										</div>
									</div>
									<!-- if community report -->
									<div class="row justify-content-end mt-2dot2">

										<%
											if (!rep.getJobs().isEmpty()) {
													out.println("Job created");
												} else {
													out.println("No event created");
												}
										%>

									</div>

								</div>

							</div>

						</div>
					</li>
					<!-- End of Single Row for JSP  -->

					<%
						}
					%>


				</ul>
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
</script>
</body>
</html>