package com.css.pos.service.company;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.company.CompanyDal;
import com.css.pos.dto.company.CompanyDto;
@Component
public class CompanyServiceImpl implements CompanyService {
	private CompanyDal companyDal;
	
	public CompanyDal getCompanyDal() {
		return companyDal;
	}
	@Autowired
	public void setCompanyDal(CompanyDal companyDal) {
		this.companyDal = companyDal;
	}
	@Override
	public int save(CompanyDto company) {
		try {
			if(company.getId() == null)
				company.setId(UUID.randomUUID().toString());
			return companyDal.save(company);
			
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return companyDal.delete(id);
	}

	public CompanyDto findCompany(String id) {
		// TODO Auto-generated method stub
		return companyDal.findCompany(id);
	}

	
	@Override
	public List<CompanyDto> list(String bline) {
		List<CompanyDto> co = companyDal.list(bline);
		if(co!=null)
		for(int i = 0; i<co.size();i++) {
			co.get(i).setBusinessLine(bline);
		}
			
		return co;
	}
	

}
