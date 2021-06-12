package com.css.pos.service.company;


import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.common.CommonService;

public interface CompanyService extends CommonService<CompanyDto, String>{
	public CompanyDto findCompany(String id);	
}
