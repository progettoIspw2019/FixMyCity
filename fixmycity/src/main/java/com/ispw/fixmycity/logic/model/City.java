package com.ispw.fixmycity.logic.model;

import java.util.List;

public interface City {
	Double getLatitude();

	Double getLongitude();

	Double[][] getBorderShape();

	List<String> getAllCategories();

	boolean isForCommunity(String cat);

	boolean isForCompany(String cat);

	List<String> getCompaniesCategories();

	List<String> getCommunityCategories();
}