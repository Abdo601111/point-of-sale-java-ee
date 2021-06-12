package com.css.pos.service.security;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dal.security.RoleDal;
import com.css.pos.dto.security.RoleDto;
@Component
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDal roleDal; 
	public RoleDal getRoleDal() {
		return roleDal;
	}

	public void setRoleDal(RoleDal roleDal) {
		this.roleDal = roleDal;
	}

	@Override
	public int save(RoleDto role) {
		if(role.getId() ==null)
			role.setId(UUID.randomUUID().toString());
		return roleDal.save(role);
	}

	@Override
	public int delete(String roleId) {
		// TODO Auto-generated method stub
		return roleDal.delete(roleId);
	}

	@Override
	public List<RoleDto> list(String companyId) {
		// TODO Auto-generated method stub
		return roleDal.list(companyId);
	}

}
