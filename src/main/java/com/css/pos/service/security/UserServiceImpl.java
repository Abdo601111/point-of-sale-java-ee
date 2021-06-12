package com.css.pos.service.security;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dal.security.UserDal;
import com.css.pos.dto.security.UserDetailsDto;

@Component
public class UserServiceImpl implements UserService {
	private UserDal userdal;
	
	public UserDal getUserdal() {
		return userdal;
	}
	@Autowired
	public void setUserdal(UserDal userdal) {
		this.userdal = userdal;
	}

	public UserDetailsDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetailsDto authenticate(String userName, String password, String companyId,String branchId) {
		
		return userdal.authenticate(userName.toUpperCase(), password, companyId, branchId);
	}
	@Override
	public List<UserDetailsDto> list(String id) {
		// TODO Auto-generated method stub
		return userdal.list(id);
	}
	@Override
	public int save(UserDetailsDto user) {
		return userdal.save(user);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return userdal.delete(id);
	}
	@Override
	public boolean isUserMailExists(String email, String dbEmail) {
		// TODO Auto-generated method stub
		return userdal.isUserMailExists(email, dbEmail);
	}
	@Override
	public int save(UserDetailsDto user, int passtype) {
		if(user.getId() == null)
			user.setId(UUID.randomUUID().toString());
		switch(passtype) {
		case 0: // use default password
			user.setPassword(POSConstants.DEFAULT_PASSWORD);
			break;
		case 1:// generate random password
			user.setPassword(UUID.randomUUID().toString().substring(0, 10));
			
		}
		return userdal.save(user);
	}
	@Override
	public boolean changePassword(String userId, String password) {
		// TODO Auto-generated method stub
		return userdal.changePassword(userId, password);
	}

	

}
