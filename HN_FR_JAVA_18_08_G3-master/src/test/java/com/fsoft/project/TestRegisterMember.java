package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fsoft.project.dao.RegisterDao;
import com.fsoft.project.dao.impl.RegisterDaoImpl;
import com.fsoft.project.entity.Member;

public class TestRegisterMember {

	static RegisterDao registerDao;

	static Member member;

	@BeforeClass
	public static void prepareForTest() {
		registerDao = new RegisterDaoImpl();
	}

	@Test
	public void testCreateMember() throws SQLException {
		member = new Member();
		member.setFirstName("nam");
		member.setLastName("pham");
		member.setPassword("12345678");
		member.setAddress("ha noi");
		member.setPhone("0978654342");
		member.setRole(false);
		member.setEmail("nam@gmail.com");
		assertEquals(1, registerDao.registerMember(member));
	}

	@Test
	public void checkEmail() throws SQLException {
		assertNotNull(registerDao.checkEmail(member));
	}
}
