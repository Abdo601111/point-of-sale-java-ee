package com.css.pos.service.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.customer.CustomerDal;
import com.css.pos.dto.customer.CustomerDto;

@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDal customerDal;

	public CustomerDal getCustomerDal() {
		return customerDal;
	}

	public void setCustomerDal(CustomerDal customerDal) {
		this.customerDal = customerDal;
	}

	@Override
	public int save(CustomerDto customer) {
		if(customer.getId() == null)
			customer.setId(UUID.randomUUID().toString());
		return customerDal.save(customer);
	}

	@Override
	public int delete(String customerId) {
		// TODO Auto-generated method stub
		return customerDal.delete(customerId);
	}

	@Override
	public List<CustomerDto> list(String selectedCompany) {
		return customerDal.list(selectedCompany);
	}

	@Override
	public boolean isCustomerMailExists(String email, String oldEmail) {
		// TODO Auto-generated method stub
		return customerDal.isCustomerMailExists(email, oldEmail);
	}

	@Override
	public List<CustomerDto> list(String companyId, String searchName) {
		// TODO Auto-generated method stub
		return customerDal.list(companyId, searchName);
	}


}
