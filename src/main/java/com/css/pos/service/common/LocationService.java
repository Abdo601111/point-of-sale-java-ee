package com.css.pos.service.common;

import java.util.Map;

public interface LocationService {
	public Map<String, Integer> listAllCountries();
	public Map<String, Integer> listAllStates(Integer countryId);
	public Map<String, Integer> listAllCities(Integer stateId);
}
