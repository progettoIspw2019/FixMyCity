package com.ispw.fixmycity.logic.util;

import java.util.EnumSet;

public enum Category {
	GARBAGE, FILTH, LEAK, ELECTRIC_GRID, DRAINAGE_OR_SEWERAGE;
	
	public static boolean isForCompany(Category cat){
		EnumSet<Category> compSet = EnumSet.of(Category.GARBAGE, Category.LEAK, Category.ELECTRIC_GRID, Category.DRAINAGE_OR_SEWERAGE);
		return compSet.contains(cat);
	}
	
	public static boolean isForCommunity(Category cat) {
		EnumSet<Category> communitySet = EnumSet.of(Category.FILTH);
		return communitySet.contains(cat);
	}
}
