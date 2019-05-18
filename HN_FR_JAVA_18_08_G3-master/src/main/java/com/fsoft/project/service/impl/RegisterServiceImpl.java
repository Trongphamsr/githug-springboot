package com.fsoft.project.service.impl;

import java.sql.SQLException;

import com.fsoft.project.dao.RegisterDao;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.RegisterService;

public class RegisterServiceImpl implements RegisterService{

	RegisterDao registerDao;
	
	
	public RegisterServiceImpl(RegisterDao registerDao) {
		super();
		this.registerDao = registerDao;
	}


	@Override
	public int registerMember(Member member){
		return registerDao.registerMember(member);
	}


	@Override
	public boolean checkEmail(Member member){
		return registerDao.checkEmail(member);
	}

}
