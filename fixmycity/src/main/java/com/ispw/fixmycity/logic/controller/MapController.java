package com.ispw.fixmycity.logic.controller;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.dao.CommunityReportDAOImpl;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.MapBoundary;

import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.Circle;
import net.java.html.leaflet.LatLng;
import net.java.html.leaflet.Map;
import net.java.html.leaflet.PathOptions;
import net.java.html.leaflet.Polygon;
import net.java.html.leaflet.TileLayer;
import net.java.html.leaflet.TileLayerOptions;

public class MapController {

	private WebView mapView;

	public MapController(WebView mapView) {
		this.setMapView(mapView);
	}

	public void loadMap(List<ReportFilter> filters) {

		FXBrowsers.load(mapView, MapBoundary.class.getResource("index.html"), new Runnable() {

			@Override
			public void run() {
				Map map;

				map = new Map("map");
				map.addLayer(new TileLayer(
						"http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png?apikey=f1a0893344e045899384882196dacff3",
						new TileLayerOptions().setAttribution(
								"Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
										+ "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
										+ "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>")
								.setMaxZoom(18).setId("eppleton.ia9c2p12")));
				// from here we just use the Leaflet API to show some stuff on the map
				map.setView(new LatLng(41.902782, 12.496365), 13);

				for (ReportFilter filter : filters) {
					if (filter == ReportFilter.ALL_COMMUNITY_REPORT)
						addEveryCommunityReport(map);
					if (filter == ReportFilter.ALL_COMPANY_REPORT)
						addEveryCommunityReport(map);
				}

			}
		});
	}

	public void addEveryCompanyReport(Map map) {

		// sample code showing how to use the Java API
		map.addLayer(new Circle(new LatLng(41.902782, 12.496365), 500,
				new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)).bindPopup("I am a Circle"));
		map.addLayer(new Polygon(
				new LatLng[] { new LatLng(51.509, -0.08), new LatLng(51.503, -0.06), new LatLng(51.51, -0.047) })
						.bindPopup("I am a Polygon"));
	}

	public void addEveryCommunityReport(Map map) {

		CommunityReportDAOImpl dao = new CommunityReportDAOImpl();

		List<CommunityReportBean> reports = new ArrayList<>();
		reports = dao.findAll();
		
		for (CommunityReportBean report : reports) {
			map.addLayer(new Circle(new LatLng(report.getLatitude().floatValue(), report.getLongitude().floatValue()),
					500, new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5))
							.bindPopup(report.getDescription()));
//			map.addLayer(new Circle(new LatLng(40.902782, 12.496365), 500,
//					new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5))
//							.bindPopup("I am a Circle 2"));
		}

		// sample code showing how to use the Java API

		map.addLayer(new Polygon(
				new LatLng[] { new LatLng(51.509, -0.08), new LatLng(51.503, -0.06), new LatLng(51.51, -0.047) })
						.bindPopup("I am a Polygon 2"));
	}

	public WebView getMapView() {
		return mapView;
	}

	public void setMapView(WebView mapView) {
		this.mapView = mapView;
	}
}
