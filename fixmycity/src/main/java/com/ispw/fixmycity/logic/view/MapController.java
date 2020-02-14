package com.ispw.fixmycity.logic.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.model.City;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.javafx.MapBoundary;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.Icon;
import net.java.html.leaflet.IconOptions;
import net.java.html.leaflet.LatLng;
import net.java.html.leaflet.Map;
import net.java.html.leaflet.Marker;
import net.java.html.leaflet.MarkerOptions;
import net.java.html.leaflet.Point;
import net.java.html.leaflet.Polygon;
import net.java.html.leaflet.Popup;
import net.java.html.leaflet.PopupOptions;
import net.java.html.leaflet.TileLayer;
import net.java.html.leaflet.TileLayerOptions;
import net.java.html.leaflet.event.MouseEvent;
import net.java.html.leaflet.event.MouseListener;

public class MapController {

	private WebView mapView;
	private MouseListener mouseListener;
	private Map map;
	private Icon iconComp;
	private Icon iconComm;
	private MarkerOptions markerOptComp;
	private MarkerOptions markerOptComm;
	private City city;

	public MapController(WebView mapView) {
		this.setMapView(mapView);
	}

	public void loadMap(List<ReportFilter> filters) {

		city = new CityFactory().getCity(SessionView.getCityEnum());

		FXBrowsers.load(mapView, MapBoundary.class.getResource("index.html"), () -> {
			map = new Map("map");

			TileLayer tileLayer = new TileLayer(
					"https://1.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png8"
							+ "?apiKey=XUbEajSB94rqlnuoXCZkfMe_n3bUeeGghpHSejZkC50",
					new TileLayerOptions()
							.setAttribution(
									"Map data &copy; <a href='https://developer.here.com/products/maps/'>HERE Api</a>")
							.setMaxZoom(18).setId("fixmycity.ia9c2p12"));
			map.addLayer(tileLayer);

			LatLng[] borderArray = new LatLng[city.getBorderShape().length];
			for (int i = 0; i < borderArray.length; i++) {
				borderArray[i] = new LatLng(city.getBorderShape()[i][0], city.getBorderShape()[i][1]);
			}
			Polygon borderPolygonLayer = new Polygon(borderArray);
			int zoom = map.getBoundsZoom(borderPolygonLayer.getBounds());
			map.setView(new LatLng(city.getLatitude(), city.getLongitude()), zoom);

			map.addLayer(borderPolygonLayer);
			
			this.setMouseListener();
			
			map.addMouseListener(MouseEvent.Type.CLICK, mouseListener);
			
			
			this.iconComp = new Icon(
					new IconOptions("leaflet-0.7.2/images/marker-icon.png").setIconSize(new Point(25, 41))
							.setIconAnchor(new Point(12.5, 41)).setPopupAnchor(new Point(0, -20)));
			this.iconComm = new Icon(
					new IconOptions("leaflet-0.7.2/images/marker-icon-community.png").setIconSize(new Point(25, 41))
							.setIconAnchor(new Point(12.5, 41)).setPopupAnchor(new Point(0, -20)));
			this.markerOptComp = new MarkerOptions().setIcon(this.iconComp);
			this.markerOptComm = new MarkerOptions().setIcon(this.iconComm);

			for (ReportFilter filter : filters) {
				if (filter == ReportFilter.ALL_COMMUNITY_REPORT)
					addEveryCommunityReport(map);
				if (filter == ReportFilter.ALL_COMPANY_REPORT)
					addEveryCompanyReport(map);
			}
		});
	}

	public void addEveryCompanyReport(Map map) {
		List<CompanyReportBeanView> reports = new SystemFacade().getCompanyReports();

		for (var report : reports) {
			new Marker(new LatLng(report.getLatitude().floatValue(), report.getLongitude().floatValue()), markerOptComp)
					.bindPopup(new Popup(new PopupOptions().setMaxWidth(200)).setContent("<b>" + report.getTitle()
							+ "</b><br>" + report.getDescription() + "<br>" + "<i>submitted on "
							+ new SimpleDateFormat("dd-MM-yyyy").format(report.getDateSubmission()) + " by "
							+ report.getSubmitter() + "</i>"))
					.addTo(map);
		}
	}

	public void addEveryCommunityReport(Map map) {
		List<CommunityReportBeanView> reports = new SystemFacade().getCommunityReports();

		for (var report : reports) {
			new Marker(new LatLng(report.getLatitude().floatValue(), report.getLongitude().floatValue()), markerOptComm)
					.bindPopup(new Popup(new PopupOptions().setMaxWidth(200)).setContent("<b>" + report.getTitle()
							+ "</b><br>" + report.getDescription() + "<br>" + "<i>submitted on "
							+ new SimpleDateFormat("dd-MM-yyyy").format(report.getDateSubmission()) + " by "
							+ report.getSubmitter() + "</i>"))
					.addTo(map);
		}
	}
	
	private void setMouseListener() {
		mouseListener = (MouseEvent ev) -> {
			
			var x = ev.getLatLng().getLatitude();
			var y = ev.getLatLng().getLongitude();
			Double[][] vs = city.getBorderShape();
		    
		    var inside = false;
		    var i = 0; 
		    var j = vs.length - 1;
		    for (; i < vs.length; j = i++) {
		        var xi = vs[i][0]; 
		        var yi = vs[i][1];
		        
		        var xj = vs[j][0]; 
		        var yj = vs[j][1];
		        
		        var intersect = ((yi > y) != (yj > y))
		            && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
		        if (intersect) inside = !inside;
		    }
			
			if(!inside) {
				Alert alert = new Alert(AlertType.INFORMATION, "You can only pick locations "
						+ "that are inside the polygon surrounding your city.", ButtonType.OK);
				alert.setHeaderText("Out of city bounds!");
				alert.showAndWait();
				return;
			}
			
			BigDecimal latitude = BigDecimal.valueOf(ev.getLatLng().getLatitude());
			BigDecimal longitude = BigDecimal.valueOf(ev.getLatLng().getLongitude());
			
			

			new SystemFacade().setAddressForReport(longitude, latitude);
			AddressBean addr = SessionView.getAddressSetOnMap();

			PopupOptions popupOptions = new PopupOptions().setMaxWidth(400);
			Popup popup = new Popup(popupOptions);
			popup.setLatLng(ev.getLatLng());

			popup.setContent(addr.getRoad() + ", " + addr.getCity() + ", " + addr.getCountry());
			popup.openOn(map);
		};
	}

	public WebView getMapView() {
		return mapView;
	}

	public void setMapView(WebView mapView) {
		this.mapView = mapView;
	}
}
