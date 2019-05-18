package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured/api")
public class SecureController {
	
	@GetMapping
	public String securedMethod() {
		return "Welcome";
	}

}
