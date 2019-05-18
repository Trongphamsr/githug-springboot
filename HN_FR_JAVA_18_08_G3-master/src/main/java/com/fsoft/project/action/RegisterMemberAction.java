package com.fsoft.project.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.fsoft.project.dao.RegisterDao;
import com.fsoft.project.dao.impl.RegisterDaoImpl;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.RegisterService;
import com.fsoft.project.service.impl.RegisterServiceImpl;
import com.fsoft.project.utils.constants.QueryConstants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterMemberAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -7290440235992115844L;

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private boolean role;
	private String email;
	private String password;
	private String msg;
	private int result;
	private Member member;
	private Map<String, Object> session;
	private RegisterDao registerDao; 
	private RegisterService registerService;
	private String lang;

	public String registerMember() throws SQLException {
		member = new Member();
		member.setEmail(email);
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setPassword(password);
		member.setPhone(phone);
		member.setAddress(address);

		registerDao = new RegisterDaoImpl();
		registerService = new RegisterServiceImpl(registerDao);

		if(registerService.checkEmail(member)) {
			
		}
		else {

			result = registerService.registerMember(member);

			if(result>0) {
				msg = "Registration Successfull";
				session.put(WebConstants.REGISTER_MEMBER, member);
			}else {
				msg = "Registration False";
			}
		}
		return Action.SUCCESS;

	}



	@Override
	public void validate() {
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}
	
	

	public RegisterMemberAction() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RegisterMemberAction(String lang) {
		super();
		this.lang = lang;
	}



	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
