package com.ispw.fixmycity.logic.view;

import java.math.BigDecimal;
import java.util.List;

import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.javafx.MapBoundary;

import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.Circle;
import net.java.html.leaflet.LatLng;
import net.java.html.leaflet.Map;
import net.java.html.leaflet.PathOptions;
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

	public MapController(WebView mapView) {
		this.setMapView(mapView);

		mouseListener = (MouseEvent ev) -> {
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

	public void loadMap(List<ReportFilter> filters) {

		FXBrowsers.load(mapView, MapBoundary.class.getResource("index.html"), () -> {
			map = new Map("map");

			TileLayer tileLayer = new TileLayer(
					"http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png?apikey=f1a0893344e045899384882196dacff3",
					new TileLayerOptions().setAttribution(
							"Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
									+ "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
									+ "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>")
							.setMaxZoom(18).setId("eppleton.ia9c2p12"));
			map.addLayer(tileLayer);
			// from here we just use the Leaflet API to show some stuff on the map
			map.setView(new LatLng(41.902782, 12.496365), 13);

			map.addMouseListener(MouseEvent.Type.CLICK, mouseListener);

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
			map.addLayer(new Circle(new LatLng(report.getLatitude().floatValue(), report.getLongitude().floatValue()),
					500, new PathOptions().setColor("cyan").setFillColor("#f05").setOpacity(0.5))
							.bindPopup(report.getTitle()));
		}
	}

	public void addEveryCommunityReport(Map map) {
		List<CommunityReportBeanView> reports = new SystemFacade().getCommunityReports();

		for (var report : reports) {
			map.addLayer(new Circle(new LatLng(report.getLatitude().floatValue(), report.getLongitude().floatValue()),
					500, new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5))
							.bindPopup(report.getTitle()));
		}
	}

	public WebView getMapView() {
		return mapView;
	}

	public void setMapView(WebView mapView) {
		this.mapView = mapView;
	}
}
