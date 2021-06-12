package com.css.pos.dal.company;

import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.company.BranchDto;

public interface BranchDal extends CommonDal<BranchDto, String>{
	public BranchDto find(String id);
	public List<BranchDto> list();
}
