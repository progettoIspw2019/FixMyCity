package com.ispw.fixmycity.logic.view;

import java.math.BigDecimal;

import com.ispw.fixmycity.logic.bean.AddressBean;

public class SessionView {
	private static BigDecimal longitudeSetOnMap;
	private static BigDecimal latitudeSetOnMap;
	private static AddressBean addressSetOnMap;
	
	private SessionView() {}
	
	public static BigDecimal getLongitudeSetOnMap() {
		return longitudeSetOnMap;
	}
	public static void setLongitudeSetOnMap(BigDecimal longitude) {
		longitudeSetOnMap = longitude;
	}
	public static BigDecimal getLatitudeSetOnMap() {
		return latitudeSetOnMap;
	}
	public static void setLatitudeSetOnMap(BigDecimal latitude) {
		latitudeSetOnMap = latitude;
	}

	public static AddressBean getAddressSetOnMap() {
		return addressSetOnMap;
	}

	public static void setAddressSetOnMap(AddressBean addressSetOnMap) {
		SessionView.addressSetOnMap = addressSetOnMap;
	}
}
