<!doctype html>
<%@page import="com.ispw.fixmycity.logic.bean.ReportBean"%>
<%@page import="com.ispw.fixmycity.logic.bean.ReportBeanView"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ispw.fixmycity.logic.model.CityFactory"%>
<%@page import="com.ispw.fixmycity.logic.model.City"%>

<%@ page
	import="com.ispw.fixmycity.logic.controller.VolunteeringEventController"%>
<%@ page import="com.ispw.fixmycity.logic.controller.SystemFacade"%>
<%@ page import="com.ispw.fixmycity.logic.bean.VolunteeringEventBean"%>
<%@ page import="com.ispw.fixmycity.logic.bean.UserSessionBean"%>
<%@ page import="com.ispw.fixmycity.logic.bean.CommunityReportBean"%>
<%@ page import="com.ispw.fixmycity.logic.bean.CommunityReportBeanView"%>
<%@ page import="com.ispw.fixmycity.logic.bean.CompanyReportBeanView"%>
<%@ page import="com.ispw.fixmycity.logic.exceptions.InvalidFieldException"%>

<%@ page import="com.ispw.fixmycity.logic.util.UserMode"%>
<%@ page import="com.ispw.fixmycity.logic.view.SessionView"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.nio.file.Paths"%>


<html lang="en">
<%
	if (SessionView.getMode() != UserMode.CITIZEN) {
		response.sendRedirect("index.jsp");
	}

	VolunteeringEventController controller = new VolunteeringEventController();
	Map<Integer, String> reports = controller.getCommunityReportMap();

	String id = request.getParameter("reportId");
	String title = request.getParameter("inputTitleEvent");
	String description = request.getParameter("inputEventDescription");
	String date = request.getParameter("inputDateEvent");
	String time = request.getParameter("inputTimeEvent");
	
		
	
	if (id != null && !title.isBlank() && !description.isBlank() && !date.isBlank() && !time.isBlank()) {
		CommunityReportBean selectedReport = controller.getCommunityReportFromId(Integer.parseInt(id));
		VolunteeringEventBean bean = new VolunteeringEventBean();
		bean.setCommunityReport(selectedReport);
		bean.setEventDate(date);
		try{
			bean.setEventTime(time);
		} catch(InvalidFieldException e){
			//TODO throw some error
		}
		controller.createVolunteeringEvent(bean);
	}
	
	String repLatitude = (String) request.getParameter("latitudeInput");
	String repLongitude = (String) request.getParameter("longitudeInput");
	String repAddress = request.getParameter("inputReportAddress");
	String repTitle = request.getParameter("inputReportTitle");
	String repDescription = request.getParameter("inputReportDescription");
	String repCategory = request.getParameter("inputReportCategory");
	String repBase64Image = request.getParameter("base64ImageReport");

	
	
	if (repLatitude != null && repLongitude != null && repAddress != null && repTitle != null && repCategory != null && repBase64Image != null) {
		   
	
		ReportBeanView reportBeanView = new ReportBeanView();
		reportBeanView.setAddress(repAddress);
		reportBeanView.setLatitude(new BigDecimal(repLatitude));
		reportBeanView.setLongitude(new BigDecimal(repLongitude));
		reportBeanView.setSubmitter(SessionView.getUsername());
		reportBeanView.setTitle(SessionView.getUsername());
		reportBeanView.setCategory(repCategory);
		reportBeanView.setCity(SessionView.getCityEnum().toString());
		reportBeanView.setDescription(repDescription);
		reportBeanView.setImage(Base64.getDecoder().decode(repBase64Image));
		SystemFacade facade = new SystemFacade();
		facade.reportProblem(reportBeanView);
	
	} 
	
	
	List<CommunityReportBeanView> commReps = new SystemFacade().getCommunityReports();
	List<CompanyReportBeanView> compReps = new SystemFacade().getCompanyReports();
	CityFactory cityFactory = new CityFactory();
	City city = cityFactory.getCity(SessionView.getCityEnum());
	List<String> categories = new ArrayList<String>();
	categories = city.getAllCategories();
		
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
<!-- Map CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<!-- Load Esri Leaflet Geocoder from CDN -->
<link rel="stylesheet"
	href="https://unpkg.com/esri-leaflet-geocoder@2.3.2/dist/esri-leaflet-geocoder.css"
	integrity="sha512-IM3Hs+feyi40yZhDH6kV8vQMg4Fh20s9OzInIIAc4nx7aMYMfo+IenRUekoYsHZqGkREUgx0VvlEsgm7nCDW9g=="
	crossorigin="">



<link rel="stylesheet" href="lib/Control.Coordinates.css" />
<link rel="stylesheet" href="style/style.css">
<title>FixMyCity</title>
</head>
<body>
	<nav aria-label="navbar" class="navbar navbar-dark bg-dark">
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
				aria-label="navbar"
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
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav  mt-2 mt-lg-0">
						<li class="nav-item active"><button id="addRepBtn" disabled
								data-toggle="modal" data-target="#newReport" class="nav-link ">
								<img src="style/icons/Document-text.svg" alt="" width="24"
									height="24"> Add a Report
							</button></li>
						<li class="nav-item active ml-4">
							<button data-toggle="modal" data-target="#newEvent"
								class="nav-link">
								<img src="style/icons/Calendar.svg" alt="" width="24"
									height="24"> Create an Event
							</button>
						</li>
					</ul>
				</div>
			</nav>
			<div class="container-fluid p-0">
				<div id="mapid"></div>
			</div>
		</div>
	</div>
	<!-- Form modals -->
	<!-- Create Report -->
	<div class="modal" id="newReport">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Create Report</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<form action="home_citizen.jsp" method="GET" id="reportProblemFormId">
					<input type="hidden" value="" id="latitudeInputId"
						name="latitudeInput"> <input type="hidden" value=""
						id="longitudeInputId" name="longitudeInput"> <input
						type="hidden" value="" id="addressInputId" name="inputReportAddress">
					<input type="hidden" value="" id="base64ImageReportId"
						name="base64ImageReport" value="">
					<div class="modal-body">
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<input type="text" class="form-control" name="inputReportTitle"
										placeholder="Title">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<textarea class="form-control" name="inputReportDescription"
										placeholder="Description" rows="10"></textarea>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputReportCategoryId">Category</label> <select
										class="form-control" name="inputReportCategory"
										id="inputReportCategoryId">
										<% for (String cat : categories) { %>
										<option><% out.println(cat); %></option>
										<% } %>
									</select>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputReportPictureId">Add a picture</label> <br>
									<input type="file" accept="image/png, image/jpeg" id="inputReportPictureId"
										name="inputReportPicture">
								</div>
							</div>
						</div>



					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Create</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Create Event -->
	<div class="modal" id="newEvent">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Create Volunteering Event</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<form action="home_citizen.jsp" method="GET">
					<div class="modal-body">

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputReportId">Select a report</label> <select
										class="form-control" name="reportId">
										<%
											if (!reports.isEmpty()) {
												Iterator<Map.Entry<Integer, String>> iterator = reports.entrySet().iterator();
												while (iterator.hasNext()) {
													Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>) iterator.next();
													out.println("<option value='" + pair.getKey() + "'>" + pair.getValue() + "</option>");
												}
											}
										%>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputTitleEvent">Title of the event</label> <input
										type="text" class="form-control" name="inputTitleEvent"
										placeholder="Title of the Event">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputEventDescription">Describe your event</label>
									<textarea class="form-control" name="inputEventDescription"
										rows="3"></textarea>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputDateEvent">Date of the event</label> <input
										type="date" class="form-control" name="inputDateEvent">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputTimeEvent">Time of the event</label> <input
										type="time" class="form-control" name="inputTimeEvent"
										placeholder="Time of the Event">
								</div>
							</div>
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Create</button>
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
<script
	src="https://cdn.rawgit.com/hayeswise/Leaflet.PointInPolygon/v1.0.0/wise-leaflet-pip.js"></script>

<script>
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});
</script>

<!-- Map Javascript -->
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>
<script src="lib/Control.Coordinates.js"></script>


<!-- Load Esri Leaflet from CDN -->
<script src="https://unpkg.com/esri-leaflet@2.2.3/dist/esri-leaflet.js"
	integrity="sha512-YZ6b5bXRVwipfqul5krehD9qlbJzc6KOGXYsDjU9HHXW2gK57xmWl2gU6nAegiErAqFXhygKIsWPKbjLPXVb2g=="
	crossorigin=""></script>


<script
	src="https://unpkg.com/esri-leaflet-geocoder@2.2.13/dist/esri-leaflet-geocoder.js"
	integrity="sha512-zdT4Pc2tIrc6uoYly2Wp8jh6EPEWaveqqD3sT0lf5yei19BC1WulGuh5CesB0ldBKZieKGD7Qyf/G0jdSe016A=="
	crossorigin=""></script>

<script
	src="https://cdn.rawgit.com/hayeswise/Leaflet.PointInPolygon/v1.0.0/wise-leaflet-pip.js"></script>


<script type="text/javascript">


function getBase64(file) {
	   var reader = new FileReader();
	   reader.readAsDataURL(file);
	   reader.onload = function () {
	     console.log(reader.result);
	     document.getElementById('base64ImageReportId').value =btoa(reader.result);
alert(document.getElementById('base64ImageReportId').value);
	   };
	   reader.onerror = function (error) {
	     alert("Invalid file");
	   };
	}




$("#inputReportPictureId").change(function(){
	var file = document.getElementById('inputReportPictureId').files[0];
	getBase64(file);});





var cityBorder =  [
<% int len = city.getBorderShape().length;
for (int i=0; i<len; i++) 
{
	out.println(Arrays.toString(city.getBorderShape()[i]));
	if (i<len-1) { out.println(", ");  }
} 
%>];



	var mymap = L.map('mapid').setView([<% out.println(city.getLatitude()); %>, <% out.println(city.getLongitude()); %> ], 13);
	L
			.tileLayer(
					"https://1.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png8"
							+ "?apiKey=XUbEajSB94rqlnuoXCZkfMe_n3bUeeGghpHSejZkC50",
					{
						attribution : 'HERE Maps',
						maxZoom : 18,
						id : 'eppleton.ia9c2p12',
						accessToken : 'XUbEajSB94rqlnuoXCZkfMe_n3bUeeGghpHSejZkC50'
					}).addTo(mymap);
<%for (CommunityReportBeanView commRep : commReps) {
				out.println("L.marker([" + commRep.getLatitude() + ", " + commRep.getLongitude()
						+ "]).addTo(mymap).bindPopup('" + commRep.getTitle() + "');");
			}
			for (CompanyReportBeanView compRep : compReps) {
				out.println("L.marker([" + compRep.getLatitude() + ", " + compRep.getLongitude()
						+ "]).addTo(mymap).bindPopup('" + compRep.getTitle() + "');");
			}%>
	var geocodeService = L.esri.Geocoding.geocodeService();

	var polygon = L.polygon(cityBorder, {
		radius : 8,
		fillColor : "#343a40",
		color : "blue",

		opacity : 1,
		fillOpacity : 0

	}).addTo(mymap);
	
	mymap.fitBounds(polygon.getBounds());

	var selectionMarker = {};

	mymap
			.on(
					'click',
					function(e) {

						geocodeService
								.reverse()
								.latlng(e.latlng)
								.run(
										function(error, result) {

											if (polygon.contains(e.latlng)) {
												mymap
														.removeLayer(selectionMarker);
												selectionMarker = L
														.marker(result.latlng)
														.addTo(mymap)
														.bindPopup(
																result.address.Match_addr)
														.openPopup();
												document
														.getElementById("latitudeInputId").value = e.latlng.lat;
												document
														.getElementById("longitudeInputId").value = e.latlng.lng;
												document
														.getElementById("addressInputId").value = result.address.Match_addr;

												document
														.getElementById("addRepBtn").disabled = false;
											} else {
												alert("Pick an area inside the city");
											}

										});
					});

	function responsiveMap() {
		wrapperSize = $("#page-content-wrapper").height() - 140;

		document.getElementById("mapid").style.height = wrapperSize + "px";
		mymap.invalidateSize(true);

	}

	window.addEventListener("resize", responsiveMap);
	$(document).ready(function() {
		responsiveMap();
	});
</script>


</body>
</html>