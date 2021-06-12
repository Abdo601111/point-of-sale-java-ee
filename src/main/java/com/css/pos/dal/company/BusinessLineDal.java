package com.css.pos.dal.company;

import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.company.BusinessLineDto;

public interface BusinessLineDal extends CommonDal<BusinessLineDto, String>{
	public BusinessLineDto find(String id);
}
