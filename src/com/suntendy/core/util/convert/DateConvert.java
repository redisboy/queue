package com.suntendy.core.util.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;    

public class DateConvert implements Converter{    
   
	// 实现convert方法
	public Object convert(Class type, Object value) {
		String dateFormat = "";
		if (value == null) {
			return null;
		}
		String str = (String) value;
		if ("".equals(str.trim())) {
			return null;
		}
		try {
			if(str.length()==10){//精确到日
				dateFormat = "yyyy-MM-dd";
			}else if(str.length()==19){//精确到秒
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			}else{
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			}

			DateFormat df = new SimpleDateFormat(dateFormat);
			long time = df.parse(str).getTime();
			java.util.Date t = new java.util.Date(time); 
			return t;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	} 
   
}    
