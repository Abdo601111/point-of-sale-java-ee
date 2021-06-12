package com.css.pos.dal.company;

import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.company.CompanyDto;

public interface CompanyDal extends CommonDal<CompanyDto, String>{
	public CompanyDto findCompany(String id);
	public List<CompanyDto> listCompanies();
	
}
