package com.ispw.fixmycity.logic.bean;

public class AddressBean {
	public AddressBean() {
		//Bean can be empty initially
	}
	
	public AddressBean(String road, String city, String country) {
		this.setCity(city);
		this.setRoad(road);
		this.setCountry(country);
	}
	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	private String road;
	private String city;
	private String country;
}
