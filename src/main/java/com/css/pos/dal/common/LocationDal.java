package com.css.pos.dal.common;

import java.util.Map;

public interface LocationDal {
	public Map<String, Integer> listAllCountries();
	public Map<String, Integer> listAllStates(Integer countryId);
	public Map<String, Integer> listAllCities(Integer stateId);
}
