package com.css.pos.service.customer;

import java.util.List;

import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.service.common.CommonService;

public interface CustomerService extends CommonService<CustomerDto, String>{
	public boolean isCustomerMailExists(String email, String oldEmail);

	public List<CustomerDto> list(String companyId, String searchName) ;
	
}
