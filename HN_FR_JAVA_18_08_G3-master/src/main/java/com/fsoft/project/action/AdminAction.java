package com.fsoft.project.action;

import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	public String admin_home() throws Exception {
		
		return WebConstants.ADMIN_HOME;
	}
	
}

