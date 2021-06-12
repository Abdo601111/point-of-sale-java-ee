package com.css.pos.dal.customer;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.customer.SupplierDto;

public interface SupplierDal extends CommonDal<SupplierDto, String>{
	public boolean isCustomerMailExists(String email, String dbEmailId);
}
