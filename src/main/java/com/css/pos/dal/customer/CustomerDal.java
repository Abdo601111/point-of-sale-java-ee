package com.css.pos.dal.customer;

import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.customer.CustomerDto;

public interface CustomerDal extends CommonDal<CustomerDto, String>{
	public boolean isCustomerMailExists(String email, String oldEmail);

	public List<CustomerDto> list(String companyId, String searchName);
	
}
