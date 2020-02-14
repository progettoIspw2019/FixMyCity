<!doctype html>
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
						<form>

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
										<input type="button" class="btn btn-info halign-center"
											value="Select a picture" />
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputFirstname"
											placeholder="First Name">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputLastname"
											placeholder="Last Name">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<label for="inputCityId">City</label> <select
											class="form-control" name="inputCity" id="inputCityId">
											<option>Roma</option>
											<option>Fiuggi</option>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputUsername"
											placeholder="Username">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="password" class="form-control" id="inputPassword"
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
						<form>

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
										<input type="button" class="btn btn-info halign-center"
											value="Select a picture" />
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputCompanyName"
											placeholder="Company Name">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputLastname"
											placeholder="City">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<label for="inputCityId">City</label> <select
											class="form-control" name="inputCity" id="inputCityId">
											<option>Roma</option>
											<option>Fiuggi</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputUsername"
											placeholder="Username">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="password" class="form-control" id="inputPassword"
											placeholder="Password">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<input type="text" class="form-control" id="inputUsername"
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
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script type="text/javascript">
		$('#myTab a').on('click', function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
	</script>
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