package com.css.pos.service.customer;


import com.css.pos.dto.customer.SupplierDto;
import com.css.pos.service.common.CommonService;

public interface SupplierService extends CommonService<SupplierDto, String>{
	
	public boolean isCustomerMailExists(String email, String dbEmailId);
}
