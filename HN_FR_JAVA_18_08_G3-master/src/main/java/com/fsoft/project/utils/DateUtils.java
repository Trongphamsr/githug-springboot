package com.fsoft.project.utils;

import java.sql.Date;
import java.util.Calendar;


public class DateUtils {
	public static Date getDate() {
		Calendar c = Calendar.getInstance();
		java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
		return startDate;
	}
}
