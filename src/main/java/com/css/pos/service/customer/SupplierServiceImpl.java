package com.css.pos.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSUtil;
import com.css.pos.dal.customer.SupplierDal;
import com.css.pos.dto.customer.SupplierDto;
@Component
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierDal supplierdal; 
	public SupplierDal getSupplierdal() {
		return supplierdal;
	}

	public void setSupplierdal(SupplierDal supplierdal) {
		this.supplierdal = supplierdal;
	}

	@Override
	public List<SupplierDto> list(String companyId) {
		// TODO Auto-generated method stub
		return supplierdal.list(companyId);
	}

	@Override
	public int save(SupplierDto supplier) {
		// TODO Auto-generated method stub
		
		return supplierdal.save(supplier);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return supplierdal.delete(id);
	}

	@Override
	public boolean isCustomerMailExists(String email, String dbEmailId) {
		// TODO Auto-generated method stub
		return supplierdal.isCustomerMailExists(email, dbEmailId) ;
	}

	

}
