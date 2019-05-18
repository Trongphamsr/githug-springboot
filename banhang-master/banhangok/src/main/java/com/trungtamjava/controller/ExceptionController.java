package com.trungtamjava.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	private static Logger logger=Logger.getLogger(ExceptionController.class);
	
	
	@ExceptionHandler(value={NoHandlerFoundException.class})
	
	public String exceptionHandler(Exception exception){
		logger.error(exception);
		//System.err.println(exception);
		return "error404";
	}
	
	@ExceptionHandler(value={Exception.class})
	public String exceptionAll(Exception exception){
		//System.err.println(exception);
		System.err.println(exception);
		return "error";
	}

}
