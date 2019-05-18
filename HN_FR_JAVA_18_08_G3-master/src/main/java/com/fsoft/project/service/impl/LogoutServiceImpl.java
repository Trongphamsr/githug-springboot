package com.fsoft.project.service.impl;

import java.util.Map;

import com.fsoft.project.entity.Member;
import com.fsoft.project.service.LogoutService;
import com.fsoft.project.utils.constants.Constants;

public class LogoutServiceImpl implements LogoutService{
	
	@Override
	public void removeMember(Map<String, Object> session) {
		session.remove(Constants.MEMBER);
	}
}
