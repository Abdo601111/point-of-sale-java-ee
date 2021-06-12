package com.css.pos.dal.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.function.ClassicCountFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.City;
import com.css.pos.domain.Country;
import com.css.pos.domain.State;
@Repository
public class LocationDalImpl implements LocationDal {
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	@Override
	public Map<String, Integer> listAllCountries() {
		Map<String, Integer> countries = null;
		try {
			List<Country> allC=  sessionFactory.getCurrentSession().createCriteria(Country.class).list();
			if(allC !=null && !allC.isEmpty()) {
				for(Country c:allC) {
					if(countries == null) countries = new HashMap<>();
					countries.put(c.getName(), c.getId());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return countries;
	}
	@Transactional
	@Override
	public Map<String, Integer> listAllStates(Integer countryId) {
		Map<String, Integer> states = null;
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(State.class).add(Restrictions.eq("country.id", countryId));
			List<State> allS=  c.list();
			if(allS !=null && !allS.isEmpty()) {
				for(State s:allS) {
					if(states == null) states = new HashMap<>();
					states.put(s.getName(), s.getId());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return states;
	}
	@Transactional
	@Override
	public Map<String, Integer> listAllCities(Integer stateId) {
		Map<String, Integer> cities = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class).add(Restrictions.eq("state.id", stateId));
			List<City> allC=  criteria.list();
			if(allC !=null && !allC.isEmpty()) {
				for(City c:allC) {
					if(cities == null) cities = new HashMap<>();
					cities.put(c.getName(), c.getId());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cities;
	}

}
