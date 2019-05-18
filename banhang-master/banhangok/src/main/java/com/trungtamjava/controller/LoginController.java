package com.trungtamjava.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
	@RequestMapping("/dang-nhap")
	public String login(@RequestParam(name="error", required=false)String error) {
		System.err.println(error);
		//logger.error(error);
		return "login";
	}
	
//	public String login() {
//		
//		return "login";
//	}
	
	
	@RequestMapping("/dang-xuat")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/dang-nhap";
	}
}
