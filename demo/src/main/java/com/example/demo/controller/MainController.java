package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index() {
		return "test1";
	}
	
	@RequestMapping("/xinchao")
	@ResponseBody
	public String welcome() {
		return "xin chao cac ban";
	}
	
	
}
