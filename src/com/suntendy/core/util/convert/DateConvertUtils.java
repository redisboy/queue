package com.suntendy.core.util.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class DateConvertUtils {

	public static java.util.Date parse(String dateString, String dateFormat) {
		return parse(dateString, dateFormat, java.util.Date.class);
	}

	public static java.util.Date parse(String dateString,String dateFormat, Class targetResultType) {
		if (StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			java.util.Date t = new java.util.Date(time); 
			return t;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String format(java.util.Date date, String dateFormat) {
		if (date == null)
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}
}
