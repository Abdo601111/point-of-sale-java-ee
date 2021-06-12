package com.css.pos.service.company;


import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.service.common.CommonService;

public interface BusinessLineService extends CommonService<BusinessLineDto, String>{
	public BusinessLineDto find(String id);
	
	
}
