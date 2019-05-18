package com.fsoft.project.utils;

import org.apache.log4j.Logger;

public class LogUtils {
	static Logger LOGGER;

	public static Logger getLogger(String nameClass) {
		LOGGER = Logger.getLogger(nameClass);		
		return LOGGER;
	}
}
