package com.css.pos.service.company;

import java.util.List;

import com.css.pos.dto.company.BranchDto;
import com.css.pos.service.common.CommonService;

public interface BranchService extends CommonService<BranchDto, String>{
	
	public BranchDto find(String id);
	public List<BranchDto> list();
	
}
