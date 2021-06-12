package com.css.pos.service.common;

import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSUtil;

@Component
public class GeneralPurposeServiceImpl implements GeneralPurposeService {

	@Override
	public boolean isEmailValid(String email) {
		return POSUtil.isItAvalidEmail(email);
	}

}
