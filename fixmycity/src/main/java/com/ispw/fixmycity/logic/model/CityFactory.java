package com.ispw.fixmycity.logic.model;

import com.ispw.fixmycity.logic.util.CityEnum;

public class CityFactory {

	public City getCity(CityEnum cityEnum) {
		if (cityEnum == CityEnum.ROMA) {
			return getCityRoma();
		} else if (cityEnum == CityEnum.FIUGGI) {
			return getCityFiuggi();
		}
		return null;
	}

	public Roma getCityRoma() {
		return new Roma();
	}

	public Fiuggi getCityFiuggi() {
		return new Fiuggi();
	}

}
