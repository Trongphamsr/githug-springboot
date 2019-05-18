package com.fsoft.project.service;

import java.sql.SQLException;

import com.fsoft.project.entity.Member;

public interface RegisterService {

	public int registerMember(Member member);

	public boolean checkEmail(Member member);
}
