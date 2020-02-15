<!doctype html>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="java.util.Base64"%>

<html lang="en">
<%
	if (SessionView.getMode() != UserMode.COMPANY) {
		response.sendRedirect("index.jsp");
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
	<nav aria-label="navbar" class="navbar navbar-dark bg-dark">
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
				<a href="#" class="list-group-item list-group-item-action bg-light">Pending
					reports</a> <a href="jobs.jsp"
					class="list-group-item list-group-item-action bg-light">Jobs</a><a
					href="logout.jsp"
					class="list-group-item list-group-item-action bg-light logout-btn">Logout</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav aria-label="navbar"
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom ">
				<!-- 		<button class="btn btn-primary" id="menu-toggle">Toggle
                  Menu</button>
                  -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

			</nav>
			<div class="container-fluid p-0">
				<ul class="list-group">


					<!-- Single Row for JSP -->
					<li class="list-group-item ">
						<div class="container ml-0">
							<div class="row  ">
								<div class="col-sm-auto  ">
									<img
										src="https://www.ilmessaggero.it/photos/MED/46/29/3844629_0924_buche.jpg"
										width="150" height="auto" alt="" />
								</div>
								<div class="col-lg-auto">
									<div class="row">
										<h5>Buche viale palmiro togliatti</h5>
									</div>
									<div class="row">Per favore intervenite prima che
										qualcuno si faccia male</div>
									<div class="row">Viale palmiro togliatti</div>
									<div class="row">8/02/2020</div>
								</div>

								<div class="col">
									<div class="row justify-content-end mt-2">
										<!-- label-company if company report -->
										<div class="row justify-content-end mt-2">
											<button type="button" class="btn btn-danger ">Refuse</button>

											<button type="button" class="btn btn-join ml-2">Accept</button>
										</div>

									</div>

								</div>

							</div>

						</div>
					</li>
					<!-- End of Single Row for JSP  -->

				</ul>
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
</script>
</body>
</html>