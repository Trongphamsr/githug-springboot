package com.fsoft.project.dao;

import java.sql.SQLException;

import com.fsoft.project.entity.Member;

public interface RegisterDao {

	public int registerMember(Member member);
	
	public boolean checkEmail(Member member);
}
