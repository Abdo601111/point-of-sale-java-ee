package com.css.pos.dal.common;

import java.util.List;

import com.css.pos.dto.common.LookupDto;

public interface LookupDataDal extends CommonDal<LookupDto, String>{
	public List<LookupDto> listAllLookupElements(Integer type, String companyId);
}
