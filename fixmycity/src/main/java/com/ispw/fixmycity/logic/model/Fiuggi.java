package com.ispw.fixmycity.logic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fiuggi implements City {

	private List<String> companiesCategories;
	private List<String> communityCategories;

	@Override
	public Double getLatitude() {
		return 41.7978;
	}

	@Override
	public Double getLongitude() {
		return 13.22386;
	}

	public Fiuggi() {
		companiesCategories = new ArrayList<>();
		companiesCategories.add("Water loss");
		companiesCategories.add("Bus too far");
		companiesCategories.add("Generic proposal");

		communityCategories = new ArrayList<>();
		communityCategories.add("Urban decor");
		communityCategories.add("Dirty green area");
	}

	@Override
	public List<String> getCompaniesCategories() {
		return companiesCategories;
	}

	@Override
	public List<String> getCommunityCategories() {
		return communityCategories;
	}

	@Override
	public Double[][] getBorderShape() {

		return new Double[][] { { 41.79441633834603, 13.208999633789062 }, { 41.80030317495048, 13.2015323638916 },
				{ 41.811627415722505, 13.204622268676758 }, { 41.81674505984588, 13.215179443359373 },
				{ 41.81450614085628, 13.228912353515623 }, { 41.80766096027984, 13.240327835083008 },
				{ 41.79774374721723, 13.237409591674805 }, { 41.792176638954075, 13.242645263671875 },
				{ 41.775664440219124, 13.235177993774414 }, { 41.77982489099643, 13.220071792602537 },
				{ 41.78948889638049, 13.204965591430664 }, { 41.79441633834603, 13.208999633789062 } };
	}

	@Override
	public List<String> getAllCategories() {
		List<String> categories = new ArrayList<>();
		categories.addAll(companiesCategories);
		categories.addAll(communityCategories);
		Collections.sort(categories);

		return categories;
	}

	@Override
	public boolean isForCommunity(String cat) {
		return communityCategories.contains(cat);
	}

	@Override
	public boolean isForCompany(String cat) {
		return companiesCategories.contains(cat);
	}

}
