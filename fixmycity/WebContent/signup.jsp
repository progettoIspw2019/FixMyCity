<!doctype html>

<%@page import="com.ispw.fixmycity.logic.bean.BaseUserBean"%>

<%@ page import="com.ispw.fixmycity.logic.bean.CitizenUserBean"%>
<%@ page import="com.ispw.fixmycity.logic.bean.CompanyUserBean"%>

<%@ page import="com.ispw.fixmycity.logic.util.CityEnum"%>

<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.exceptions.UserNotFoundException"%>

<%@ page import="java.util.Base64"%>


<%
	String base64pictureCit = request.getParameter("base64PicCit");
	String firstNameCit = request.getParameter("inputFirstNameCit");
	String lastNameCit = request.getParameter("inputLastNameCit");
	String cityCit = request.getParameter("inputCityCit");
	String usernameCit = request.getParameter("inputUsernameCit");
	String passwordCit = request.getParameter("inputPasswordCit");

	boolean registerSuccess = false;

	if (base64pictureCit != null && firstNameCit != null && lastNameCit != null && cityCit != null
			&& usernameCit != null && passwordCit != null) {
		CitizenUserBean user = new CitizenUserBean();
		user.setFirstName(firstNameCit);
		user.setLastName(lastNameCit);
		user.setUsername(usernameCit);
		user.setPassword(passwordCit);
		user.setProfilePicture(Base64.getDecoder().decode(base64pictureCit));
		user.setCity(CityEnum.valueOf(cityCit.toUpperCase()));

		if (new SystemFacade().signupCitizenUser(user)) {
			BaseUserBean userBean = new BaseUserBean();
			userBean.setUsername(usernameCit);
			userBean.setPassword(passwordCit);
			SessionView.setUsername(usernameCit);
			try {
				BaseUserBean responseFromSystem = new SystemFacade().isSignedUp(userBean);
				if (responseFromSystem.getMode() == UserMode.CITIZEN) {
					SessionView.setMode(UserMode.CITIZEN);
					SessionView.setCityEnum(responseFromSystem.getCity());
					response.sendRedirect("home_citizen.jsp");
				}
			} catch (UserNotFoundException e) {
				//TODO : throw some error
			}
		}
	}

	String base64pictureComp = request.getParameter("base64PicComp");
	String nameComp = request.getParameter("inputCompanyNameComp");
	String usernameComp = request.getParameter("inputUsernameComp");
	String categoryComp = request.getParameter("inputCategoryComp");
	String passwordComp = request.getParameter("inputPasswordComp");
	String cityComp = request.getParameter("inputCityComp");

	if (base64pictureComp != null && nameComp != null && usernameComp != null && categoryComp != null
			&& passwordComp != null && cityComp != null) {
		CompanyUserBean user = new CompanyUserBean();
		user.setCategory(categoryComp);
		user.setCompanyName(nameComp);
		user.setImage(Base64.getDecoder().decode(base64pictureComp));

		user.setPassword(passwordComp);
		user.setUsername(usernameComp);
		user.setCity(CityEnum.valueOf(cityComp.toUpperCase()));

		if (new SystemFacade().signupCompanyUser(user)) {
			BaseUserBean userBean = new BaseUserBean();
			userBean.setUsername(usernameComp);
			userBean.setPassword(passwordComp);
			SessionView.setUsername(usernameComp);
			try {
				BaseUserBean responseFromSystem = new SystemFacade().isSignedUp(userBean);
				if (responseFromSystem.getMode() == UserMode.COMPANY) {
					SessionView.setMode(UserMode.COMPANY);
					SessionView.setCityEnum(responseFromSystem.getCity());
					response.sendRedirect("home_company.jsp");

				}
			} catch (UserNotFoundException e) {
				//TODO : throw some error
			}
		}
	} 
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
	<div class="container-fluid h-100" id="login-bg">
		<div class="container w-25 p-3 valign-center" id="login-box">
			<nav>
				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<a class="nav-item nav-link active" id="nav-home-tab"
						data-toggle="tab" href="#nav-home" role="tab"
						aria-controls="nav-home" aria-selected="true">Citizen</a> <a
						class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
						href="#nav-profile" role="tab" aria-controls="nav-profile"
						aria-selected="false">Company</a>
				</div>
			</nav>
			<div class="container  ">
				<div class="row ">
					<div class="col-sm">
						<br />
						<p id="welcome" style="text-align: center;">Welcome to
							FixMyCity</p>
					</div>
				</div>
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="nav-home"
						role="tabpanel" aria-labelledby="nav-home-tab">
						<form action="signup.jsp" method="GET">
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<div class="signupProfileContainer halign-center "></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="hidden" id="base64PicCitId" name="base64PicCit">
										<input type="file" accept="image/png, image/jpeg"
											id="inputCitizenPicId" name="inputCitizenPic"
											class="btn btn-info halign-center" value="Select a picture" />
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											name="inputFirstNameCit" id="inputFirstNameCitId"
											placeholder="First Name">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											id="inputLastNameCitId" name="inputLastNameCit"
											placeholder="Last Name">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<label for="inputCityId">City</label> <select
											class="form-control" name="inputCityCit" id="inputCityCitId"
											name="inputCityCit">
											<option>Roma</option>
											<option>Fiuggi</option>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											id="inputUsernameCitId" name="inputUsernameCit"
											placeholder="Username">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="password" class="form-control"
											name="inputPasswordCit" id="inputPasswordCitId"
											placeholder="Password">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<button type="submit" class="btn btn-secondary w-100">Clear</button>
								</div>
								<div class="col-sm">
									<button type="submit" class="btn btn-primary w-100">Sign
										up</button>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane fade" id="nav-profile" role="tabpanel"
						aria-labelledby="nav-profile-tab">
						<form action="signup.jsp" method="GET">

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<div class="signupProfileContainer halign-center "></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="hidden" value="" id="base64PicCompId"
											name="base64PicComp" value=""> <input type="file"
											accept="image/png, image/jpeg" id="inputCompanyPicId"
											name="inputCompanyPic" class="btn btn-info halign-center"
											value="Select a picture" />
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											id="inputCompanyNameCompId" name="inputCompanyNameComp"
											placeholder="Company Name">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<label for="inputCityId">City</label> <select
											class="form-control" name="inputCityComp"
											id="inputCityCompId">
											<option>Roma</option>
											<option>Fiuggi</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											id="inputUsernameCompId" name="inputUsernameComp"
											placeholder="Username">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="password" class="form-control"
											id="inputPasswordCompId" name="inputPasswordComp"
											placeholder="Password">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control"
											id="inputCategoryCompId" name="inputCategoryComp"
											placeholder="Category">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<button type="submit" class="btn btn-secondary w-100">Clear</button>
								</div>
								<div class="col-sm">
									<button type="submit" class="btn btn-primary w-100">Sign
										up</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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
	<script type="text/javascript">
		$('#myTab a').on('click', function(e) {
			e.preventDefault()
			$(this).tab('show')
		})

		$("#inputCitizenPicId")
				.change(
						function() {
							var file = document
									.getElementById('inputCitizenPicId').files[0];
							var reader = new FileReader();
							reader.readAsBinaryString(file);
							reader.onload = function() {
								document.getElementById('base64PicCitId').value = btoa(reader.result);
							};
							reader.onerror = function(error) {
								alert("Invalid file");
							};

						});

		$("#inputCompanyPicId")
				.change(
						function() {
							var file = document
									.getElementById('inputCompanyPicId').files[0];
							var reader = new FileReader();
							reader.readAsBinaryString(file);
							reader.onload = function() {
								document.getElementById('base64PicCompId').value = btoa(reader.result);
							};
							reader.onerror = function(error) {
								alert("Invalid file");
							};
						});
	</script>

</body>
</html>