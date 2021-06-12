package com.css.pos.service.company;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.company.BranchDal;
import com.css.pos.dto.company.BranchDto;
@Component
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchDal branchDal;
	public BranchDal getBranchDal() {
		return branchDal;
	}
	public void setBranchDal(BranchDal branchDal) {
		this.branchDal = branchDal;
	}
	@Override
	public int save(BranchDto branch) {
		if(branch.getId() == null)
			branch.setId(UUID.randomUUID().toString());
		return branchDal.save(branch);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return branchDal.delete(id);
	}
	@Override
	public BranchDto find(String id) {
		// TODO Auto-generated method stub
		return branchDal.find(id);
	}
	@Override
	public List<BranchDto> list() {
		// TODO Auto-generated method stub
		return branchDal.list();
	}
	@Override
	public List<BranchDto> list(String company) {
		// TODO Auto-generated method stub
		return branchDal.list(company);
	}

}
