package com.suntendy.queue.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
    public static String dateToString() {
    	return dateToString(new Date(), DEFAULT_PATTERN);
    }
    
	public static String dateToString(Date date) {
		return dateToString(date, DEFAULT_PATTERN);
	}
    
    public static String dateToString(String pattern) {
    	return dateToString(new Date(), pattern);
    }
	
	public static String dateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	public static String jsVer(){
		 java.util.Calendar c=java.util.Calendar.getInstance();    
	     java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyyMMddhhmmss");    
	     return f.format(c.getTime());
	}
}
