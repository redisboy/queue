package com.suntendy.core.ibatis.entity;

/**
 * 静态导入日期转换方法
 */
import com.suntendy.core.util.convert.DateConvertUtils;


public class BaseEntity implements java.io.Serializable {

	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String TIME_FORMAT = "HH24:mm:ss";

	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
	public static String date2String(java.util.Date date, String dateFormat) {
		return DateConvertUtils.format(date, dateFormat);
	}

	public static java.util.Date string2Date(String dateString,
			String dateFormat, Class targetResultType) {
		return DateConvertUtils.parse(dateString, dateFormat, targetResultType);
	}

}
