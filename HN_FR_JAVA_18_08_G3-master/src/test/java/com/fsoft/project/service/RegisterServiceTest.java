package com.fsoft.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.RegisterDaoImpl;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.impl.RegisterServiceImpl;

public class RegisterServiceTest {
	private RegisterService registerService;
	private Member member;

	@Before
	public void setUp() throws Exception {
		registerService = new RegisterServiceImpl(new RegisterDaoImpl());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterMember() {
		member = new Member();
		member.setFirstName("nam");
		member.setLastName("pham");
		member.setPassword("12345678");
		member.setAddress("ha noi");
		member.setPhone("0978654342");
		member.setRole(false);
		member.setEmail("nam@gmail.com");
		assertEquals(1, registerService.registerMember(member));
	}
	
	@Test
	public void testChekEmail() {
		boolean expected = false;
		member = new Member();
		member.setEmail("xyz@gmail.com");
		boolean actual = registerService.checkEmail(member);
		assertEquals(expected, actual);
	}

}
