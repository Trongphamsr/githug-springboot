package com.fsoft.project.action;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.MemberDao;
import com.fsoft.project.dao.impl.MemberDaoImpl;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.MemberService;
import com.fsoft.project.service.impl.MemberServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

public class ProfileAction extends BaseAction implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143654270566044162L;
	private Member member;
	private String json;
	private MemberDao memberDao;
	private MemberService memberService;
	private boolean message;
	@Override
	public void prepare() throws Exception {
		memberDao = new MemberDaoImpl();
		memberService = new MemberServiceImpl(memberDao);
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {
		member =  (Member) getSession().get(Constants.MEMBER);
		return Action.SUCCESS;
	}
	
	public String updateProfile() {
		int affected = 0;
		Gson gson = new Gson();
		member = gson.fromJson(json, Member.class);
		affected = memberService.updateMember(member);
		if(affected != 0) {
			message = true;
			getSession().put(Constants.MEMBER, member);
		}
		else
			message = false;
		return Action.SUCCESS;
	}
	
	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

}
