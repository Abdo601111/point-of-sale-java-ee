package com.css.pos.service.security;

import com.css.pos.dto.security.UserDetailsDto;
import com.css.pos.service.common.CommonService;

public interface UserService extends CommonService<UserDetailsDto,String>{
	public UserDetailsDto getUserById(Integer id);
	public UserDetailsDto authenticate(String userName, String password, String companyId,String branchId);
	public boolean isUserMailExists(String email, String dbEmail);
	/**
	 * passType:0  ==> use default Password
	 * passType:1  ==> generate password
	 * @param user
	 * @param passtype
	 * @return
	 */
	public int save(UserDetailsDto user, int passtype);
	public boolean changePassword(String userId, String password);
}
