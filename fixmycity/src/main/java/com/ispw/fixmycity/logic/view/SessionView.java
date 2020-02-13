package com.ispw.fixmycity.logic.view;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.util.UserMode;

public class SessionView {
	private static BigDecimal longitudeSetOnMap;
	private static BigDecimal latitudeSetOnMap;
	private static AddressBean addressSetOnMap;
	private static String username;
	private static byte[] imageProfile;
	private static UserMode mode;
	
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

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SessionView.username = username;
	}

	public static byte[] getImageProfile() {
		return imageProfile;
	}

	public static void setImageProfile(byte[] imageProfile) {
		Logger.getLogger("fixmycity").log(Level.INFO, "image set in session!!!\n\n");
		SessionView.imageProfile = imageProfile;
	}

	public static UserMode getMode() {
		return mode;
	}

	public static void setMode(UserMode mode) {
		SessionView.mode = mode;
	}
}
