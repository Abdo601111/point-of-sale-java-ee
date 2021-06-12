package com.css.pos.dal.security;


import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.security.UserDetailsDto;

public interface UserDal extends CommonDal<UserDetailsDto, String>{
	public UserDetailsDto getUserById(Integer id);
	public UserDetailsDto authenticate(String userName, String password, String companyId, String branchId);
	public boolean isUserMailExists(String email, String dbEmail);
	public boolean changePassword(String userId, String password);
}
