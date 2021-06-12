package com.css.pos.dal.customer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.pos.common.util.POSConstants;
import com.css.pos.domain.Category;
import com.css.pos.domain.City;
import com.css.pos.domain.Company;
import com.css.pos.domain.Country;
import com.css.pos.domain.Customer;
import com.css.pos.domain.State;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.dto.security.UserDetailsDto;
@Repository
public class CustomerDalImpl implements CustomerDal{
	
	@Autowired
	private SessionFactory sessionfactory;
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	@Override
	@Transactional
	public int save(CustomerDto viewCust) {
		try {
			Customer entity= new Customer();
			entity.setId(viewCust.getId());
			Company co = new Company();
			co.setId(viewCust.getCompanyId());
			entity.setCompanyBean(co);
			
			entity.setFirstname(viewCust.getFirstname());
			entity.setLastname(viewCust.getLastname());
			entity.setName(viewCust.getName());
			entity.setSearchkey(viewCust.getName());
			entity.setEmail(viewCust.getEmail());
			entity.setNotes(viewCust.getNotes());
			entity.setPhone(viewCust.getPhone());
			entity.setPhone2(viewCust.getPhone2());
			entity.setPostal(viewCust.getPostal());
			entity.setFax(viewCust.getFax());
			entity.setCard(viewCust.getCard());
			entity.setMaxdebt(viewCust.getMaxdebt());
			entity.setCurdebt(viewCust.getCurdebt());
			entity.setVisible(POSConstants.VISIBLE_STATUS_ON);
			if(viewCust.getCountry() != null)
				entity.setCountryBean(new Country(viewCust.getCountry()));
			if(viewCust.getState() != null)
				entity.setStateBean(new State(viewCust.getState()));
			if(viewCust.getCity() != null)
				entity.setCityBean(new City(viewCust.getCity()));
			entity.setAddress(viewCust.getAddress());
			entity.setAddress2(viewCust.getAddress2());
			sessionfactory.getCurrentSession().saveOrUpdate(entity);
			return 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String customerId) {
		// TODO Auto-generated method stub
		try {
			sessionfactory.getCurrentSession().delete(new Customer(customerId));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public List<CustomerDto> list(String selectedCompany) {
		List<CustomerDto> customers = null;
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Customer.class)
			.add(Restrictions.eq("companyBean.id", selectedCompany));//where companyBean.id = selectedCompany
			List<Customer> entities = criteria.list();
			CustomerDto cust; 
			if(entities != null && !entities.isEmpty())
				for (Customer c : entities) {
					cust = new CustomerDto();
					if(customers == null) customers = new ArrayList<>();
					cust.setId(c.getId());
					cust.setAddress(c.getAddress());
					cust.setAddress2(c.getAddress2());
					cust.setCard(c.getCard());
					cust.setCompanyId(selectedCompany);
					cust.setCountry((c.getCountryBean() != null)?c.getCountryBean().getId():null);
					cust.setState((c.getStateBean() != null)?c.getStateBean().getId():null);
					cust.setCity((c.getCityBean() != null)?c.getCityBean().getId():null);
					cust.setCurdebt(c.getCurdebt());
					cust.setEmail(c.getEmail());
					cust.setFax(c.getFax());
					cust.setFirstname(c.getFirstname());
					cust.setLastname(c.getLastname());
					cust.setMaxdebt(c.getMaxdebt());
					cust.setName(c.getName());
					cust.setNotes(c.getNotes());
					cust.setPhone(c.getPhone());
					cust.setPhone2(c.getPhone2());
					cust.setPostal(c.getPostal());

					cust.setSearchkey(c.getSearchkey());
					cust.setVisible(c.getVisible());
					customers.add(cust);
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	@Transactional
	public boolean isCustomerMailExists(String email, String emailOlString) {
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Customer.class)
					.add(Restrictions.eq("email", email)).add(Restrictions.ne("email", emailOlString));
			List<Customer> entities = criteria.list();
			if(entities != null && !entities.isEmpty())
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public List<CustomerDto> list(String selectedCompany, String searchName) {
		List<CustomerDto> customers = null;
		try {
			Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Customer.class)
			.add(Restrictions.eq("companyBean.id", selectedCompany))
			.add(Restrictions.like("name", "%"+searchName+"%"));//where companyBean.id = selectedCompany
			List<Customer> entities = criteria.list();
			CustomerDto cust; 
			if(entities != null && !entities.isEmpty())
				for (Customer c : entities) {
					cust = new CustomerDto();
					if(customers == null) customers = new ArrayList<>();
					cust.setId(c.getId());
					cust.setAddress(c.getAddress());
					cust.setAddress2(c.getAddress2());
					cust.setCard(c.getCard());
					cust.setCompanyId(selectedCompany);
					cust.setCountry((c.getCountryBean() != null)?c.getCountryBean().getId():null);
					cust.setState((c.getStateBean() != null)?c.getStateBean().getId():null);
					cust.setCity((c.getCityBean() != null)?c.getCityBean().getId():null);
					cust.setCurdebt(c.getCurdebt());
					cust.setEmail(c.getEmail());
					cust.setFax(c.getFax());
					cust.setFirstname(c.getFirstname());
					cust.setLastname(c.getLastname());
					cust.setMaxdebt(c.getMaxdebt());
					cust.setName(c.getName());
					cust.setNotes(c.getNotes());
					cust.setPhone(c.getPhone());
					cust.setPhone2(c.getPhone2());
					cust.setPostal(c.getPostal());

					cust.setSearchkey(c.getSearchkey());
					cust.setVisible(c.getVisible());
					customers.add(cust);
				} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customers;
	}
	
	
}
