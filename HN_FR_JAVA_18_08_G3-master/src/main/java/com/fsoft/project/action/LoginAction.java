package com.fsoft.project.action;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.MemberDao;
import com.fsoft.project.dao.impl.MemberDaoImpl;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.LoginService;
import com.fsoft.project.service.MemberService;
import com.fsoft.project.service.impl.LoginServiceImpl;
import com.fsoft.project.service.impl.MemberServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

public class LoginAction extends BaseAction implements  Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8216386044271370788L;
	private String email;
	private String password;

	/*
	 * */
	private boolean message;
	private Member member;
	private String fullName;
	private int index;
	private boolean notification;
	private int productId;
	private MemberDao memberDao;
	private MemberService memberService;
	private String redirectURL;
	private LoginServiceImpl loginService;

	@Override
	public void prepare() throws Exception {
		memberDao = new MemberDaoImpl();
		memberService = new MemberServiceImpl(memberDao);
		loginService = new LoginServiceImpl();
	}
	
	@Override
	public String execute() throws Exception {
		member = memberService.getMember(email, password);
		if (member == null) {
			notification = false;
			return WebConstants.FAIL;
		} else {
			notification = true;
			getSession().put(Constants.MEMBER, member);
			if (member.isRole()) {
				return WebConstants.SUCCESS_ADMIN;
			}
			Object object = getSession().get(Constants.PAGE_INDEX);
			if (object != null) {
				index = (int) object;
			}
			return loginService.loginPage(getSession(), index);
		}
		
	}

	public String getMember() {
		member = (Member) getSession().get(Constants.MEMBER);
		if (member == null) {
			message = false;
		} else {
			fullName = member.getFirstName() + member.getLastName();
			message = true;
		}
		return Action.SUCCESS;
	}

	public int getProductId() {
		return loginService.getProductId();
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public void setProductId(int productId) {
		loginService.setProductId(productId);
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void validate() {
		if (member != null) {
			addActionMessage("Account not exist!");
		}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String savePageIndex() {
		return Action.SUCCESS;
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
}
