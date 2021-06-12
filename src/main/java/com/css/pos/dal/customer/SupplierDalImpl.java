package com.css.pos.dal.customer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.domain.City;
import com.css.pos.domain.Company;
import com.css.pos.domain.Country;
import com.css.pos.domain.Customer;
import com.css.pos.domain.State;
import com.css.pos.domain.Supplier;
import com.css.pos.dto.customer.SupplierDto;
@Repository
public class SupplierDalImpl implements SupplierDal {
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessinFactory() {
		return sessionFactory;
	}

	public void setSessinFactory(SessionFactory sessinFactory) {
		this.sessionFactory = sessinFactory;
	}

	@Override
	@Transactional
	public List<SupplierDto> list(String companyId) {
		List<SupplierDto> suppliers = null;
		try {
			List<Supplier> entities = sessionFactory.getCurrentSession().createCriteria(Supplier.class).add(
					Restrictions.eq("companyBean.id", companyId)).list();
			if(entities != null && !entities.isEmpty()) {
				for(Supplier s:entities) {
					if(suppliers == null) suppliers = new ArrayList<>();
					suppliers.add(new SupplierDto(s.getId(),s.getName(),s.getCode(),s.getEmail(),s.getPhone(),s.getMobile(),companyId,s.getAddress(),
							(s.getCountryBean()!=null)?s.getCountryBean().getId():0,
							(s.getCountryBean()!=null)?s.getCountryBean().getId():0,
							(s.getCountryBean()!=null)?s.getCountryBean().getId():0));
 				}
			}
			return suppliers;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public int save(SupplierDto supplier) {
		try {
			Supplier entity = new Supplier();
			entity.setName(supplier.getName());
			entity.setAddress(supplier.getAddress());
			entity.setCode(supplier.getCode());
			entity.setEmail(supplier.getEmail());
			entity.setMobile(supplier.getMobile());
			entity.setPhone(supplier.getPhone());
			entity.setId(supplier.getId());
			entity.setCompanyBean((new Company(supplier.getCompanyId())));
			if(supplier.getCountry() != null)
				entity.setCountryBean(new Country(supplier.getCountry()));
			if(supplier.getState() != null)
				entity.setStateBean(new State(supplier.getState()));
			if(supplier.getCity() != null)
				entity.setCityBean(new City(supplier.getCity()));
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
			System.out.println("Generated Id = "+ entity.getId());
			
			return entity.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Transactional
	@Override
	public int delete(String id) {
		try {
			sessionFactory.getCurrentSession().delete(new Supplier(Integer.parseInt(id)));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public boolean isCustomerMailExists(String email, String dbEmailId) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Supplier.class)
					.add(Restrictions.eq("email", email)).add(Restrictions.ne("email", dbEmailId));
			List<Supplier> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}// TODO Auto-generated method stub
	}

}
