<!doctype html>
<%@page
	import="com.ispw.fixmycity.logic.controller.AcceptOrRefuseAJobController"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="com.ispw.fixmycity.logic.bean.JobBeanView"%>
<%@ page import="java.util.Base64"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>
<%@page import="com.ispw.fixmycity.logic.bean.CompanyReportBeanView"%>
<%@ page import="java.util.List"%>
<%@page
	import="com.ispw.fixmycity.logic.exceptions.EmptyResultListException"%>
<html lang="en">
<%
	if (SessionView.getMode() != UserMode.COMPANY) {
		response.sendRedirect("index.jsp");
	}
	List<CompanyReportBeanView> compReports = new ArrayList<CompanyReportBeanView>();

	String endDate = request.getParameter("acceptReportEndDate");
	String jobInfoBase64 = request.getParameter("acceptReportInfoFileBase64");
	String startDate = request.getParameter("acceptReportStartDate");
	String rejectingMotivation = request.getParameter("refuseReportMotivation");

	// Accept job case
	if (endDate != null && startDate != null) {

		JobBeanView jobBean = new JobBeanView();

		jobBean.setEndDate(endDate);
		jobBean.setStartDate(startDate);
		jobBean.setRelatedReport(Integer.parseInt(request.getParameter("acceptReleatedReport")));
		if (jobInfoBase64 != null) {
			jobBean.setJobInfo(Base64.getDecoder().decode(jobInfoBase64));
		} else {
			jobBean.setJobInfo(null);
		}
		new SystemFacade().jobCreation(jobBean);
	}

	// Refuse job case
	if (rejectingMotivation != null) {

		JobBeanView jobBean = new JobBeanView();
		jobBean.setRelatedReport(Integer.parseInt(request.getParameter("refuseReleatedReport")));

		jobBean.setRejectingMotivation(rejectingMotivation);
		new SystemFacade().rejectReport(jobBean);
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
	<nav aria-label="navbar" class="navbar navbar-dark bg-dark">
		<ul class="nav navbar-nav navbar-left">
			<li><span class="navbar-brand mb-0 h1">FixMyCity</span></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand mb-0 h1"> <%
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
				<a href="#" class="list-group-item list-group-item-action bg-light">My
					reports</a><a href="logout.jsp"
					class="list-group-item list-group-item-action bg-light logout-btn">Logout</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav aria-label="navbar"
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom ">

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</nav>
			<div class="container-fluid p-0">
				<div id="errorPrinterId"></div>
				<div id="errorPrinterId"></div>
				<%
					String errTemplate = "<script>printError('{0}');</script>";
					String errMessage = "";
					try {
						compReports = new SystemFacade().loadMyCompanyReports();
					} catch (Exception e) {
						errMessage += e.getMessage() + "<br\\>";
					}

					if (!errMessage.isBlank()) {
						String err = errTemplate.replace("{0}", errMessage);
						out.println(err);
					}
				%>
				<ul class="list-group">
					<%
						for (CompanyReportBeanView rep : compReports) {
					%>
					<!-- Single Row for JSP -->
					<li class="list-group-item ">
						<div class="container ml-0">
							<div class="row  ">
								<div class="col-sm-auto  ">
									<img
										src="data:image/jpeg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(rep.getImage())));%>"
										width="150" height="auto" alt="">
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
									<div class="row">
										Status:
										<%
										out.print(" " + rep.getStatus());
									%>
									</div>
								</div>
								<div class="col">
									<div class="row justify-content-end mt-2">
										<!-- label-company if company report -->
										<div class="row justify-content-end mt-2">
											<button type="button" data-toggle="modal"
												data-target="#refuseDialog" class="btn btn-danger"
												onclick='setReportId(<%out.println(rep.getId());%>)'>Refuse</button>
											<button type="button" data-toggle="modal"
												data-target="#acceptDialog" class="btn btn-join ml-2"
												onclick='setReportId(<%out.println(rep.getId());%>)'>Accept</button>
										</div>
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



	<!-- Form modals -->
	<!-- Accept Report -->
	<div class="modal" id="acceptDialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Accept Report</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<form action="home_company.jsp" method="GET" id="acceptJobFormId">
					<input type="hidden" value="" id="acceptReleatedReportId"
						name="acceptReleatedReport"> <input type="hidden" value=""
						id="acceptReportInfoFileBase64Id"
						name="acceptReportInfoFileBase64" value="">
					<div class="modal-body">
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputDateEvent">Start of the Job</label> <input
										type="date" required class="form-control"
										name="acceptReportStartDate">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="acceptReportEndDate">Expected end of the
										Job</label> <input required type="date" class="form-control"
										name="acceptReportEndDate">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="acceptReportInfoFileId">(Optional) PDF file
										with additional info</label> <br> <input type="file"
										accept="application/pdf" id="acceptReportInfoFileId"
										name="acceptReportInfoFile">
								</div>
							</div>
						</div>



					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Accept</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Refuse -->
	<div class="modal" id="refuseDialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Refuse Report</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<form action="home_company.jsp" method="GET">
					<div class="modal-body">
						<input type="hidden" value="" id="refuseReleatedReportId" required
							name="refuseReleatedReport">
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="refuseReportMotivation">Refusal motivation</label>
									<textarea class="form-control" name="refuseReportMotivation"
										rows="3"></textarea>
								</div>
							</div>
						</div>

					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Refuse</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>



</body>
<!-- 
      <div class="container-flex">
      	<div id="commands" class="container-flex">Crea report</div>
      </div>
      -->
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

	function setReportId(reportId) {
		document.getElementById('acceptReleatedReportId').value = reportId;
		document.getElementById('refuseReleatedReportId').value = reportId;
	}
	
	   
	
	   
	function getBase64(file) {
		var reader = new FileReader();
		reader.readAsBinaryString(file);
		reader.onload = function() {
			console.log(reader.result);
			document.getElementById('acceptReportInfoFileBase64Id').value = btoa(reader.result);
		
		alert(document.getElementById('acceptReportInfoFileBase64Id').value);
		};
		reader.onerror = function(error) {
			alert("Invalid file");
		};
	}

	$("#acceptReportInfoFileId").change(function() {
		var file = document.getElementById('acceptReportInfoFileId').files[0];
		getBase64(file);
	});
</script>
</body>
</html>