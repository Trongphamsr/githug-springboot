package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestRestController {

	@RequestMapping("/test/{param}")
	@ResponseBody
	public String test(@PathVariable(name="param") String param) {
		return"hello rest " + param;
	}
}
