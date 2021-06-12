package com.css.pos.service.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.common.LocationDal;
@Component
public class LocationServiceImp implements LocationService {
	@Autowired
	private LocationDal locationDal;
	public LocationDal getLocationDal() {
		return locationDal;
	}

	public void setLocationDal(LocationDal locationDal) {
		this.locationDal = locationDal;
	}

	@Override
	public Map<String, Integer> listAllCountries() {
		// TODO Auto-generated method stub
		return locationDal.listAllCountries();
	}

	@Override
	public Map<String, Integer> listAllStates(Integer countryId) {
		// TODO Auto-generated method stub
		return locationDal.listAllStates(countryId);
	}

	@Override
	public Map<String, Integer> listAllCities(Integer stateId) {
		// TODO Auto-generated method stub
		return locationDal.listAllCities(stateId);
	}

}
