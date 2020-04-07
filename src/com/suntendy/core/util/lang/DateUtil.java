package com.suntendy.core.util.lang;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

public class DateUtil {
	

	private static final Log logger = LogFactory.getLog(DateUtil.class); 

	final static public String FULL_ST_FORMAT = "yyyy-MM-dd HH:mm:ss";
    final static public String FULL_J_FORMAT = "yyyy/MM/dd HH:mm:ss";
    final static public String CURRENCY_ST_FORMAT = "yyyy-MM-dd HH:mm";
    final static public String CURRENCY_J_FORMAT = "yyyy/MM/dd HH:mm";
    final static public String DATA_FORMAT = "yyyyMMddHHmmss";
    final static public String ST_FORMAT = "yyyy-MM-dd HH:mm";
    final static public String ST_CN_FORMAT = "yyyy年MM月dd日 HH:mm";
    final static public String CN_FORMAT = "yy年MM月dd日HH:mm";
    final static public String DAY_FORMAT = "yyyy-MM-dd";
    final static public String SHORT_DATE_FORMAT = "yy-MM-dd";
    final static public String YEAR_FORMAT = "yyyy";
    final static public String YEAR_MONTH_FORMAT = "yyyy-MM";
    final static public String MONTH_DAY_FORMAT = "MM-dd";
	/**
	 * 比较两个日期 返回值为两个日期相差的天数
	 * 
	 * @return int
	 * @param sDate1
	 *            java.lang.String
	 * @param sDate2
	 *            java.lang.String
	 */
	public  static  int compareDate(String sDate1, String sDate2,String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(sDate1);
			date2 = df.parse(sDate2);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		long dif = 0;
		if (date2.after(date1))
			dif = (date2.getTime() - date1.getTime()) / 1000 / 60 / 60 / 24;
		else
			dif = (date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24;

		return (int) dif;
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param aDate
	 *            java.util.Date
	 * @param formatString
	 *            格式化字符串，如：yyyy-MM-dd
	 * @return String
	 */
	public  static  String formatDate(java.util.Date aDate, String formatString) {
		if (aDate == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		String s = df.format(aDate);
		return s;
	}

	/**
	 * 获取当前日期字符串 格式为YYYY-MM-DD
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(new Date());
		return s;
	}
	/**
	 * 返回 ****年**月**日
	 * @return
	 */
	public  static  String getCurrentDateStr() {
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(new Date());
		return s;
	}
	
	/**
	 * 获取当前日期及时间字符串 格式为YYYY-MM-DD HH:mm:ss
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentDateTime() {
		return getCurrentYear()+"年"+getCurrentMonth()+"月"+getCurrentDay()+"日";		 
	}

	/**
	 * 获取当前时间字符串 格式为HH:mm:ss
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 获取当前时间字符串 格式为yyyy-mm-dd HH:mm:ss
	 * 
	 * @return java.lang.String
	 */
	public  static  String getNow() {
		return getCurrentDate() + " " +getCurrentTime();
	}
	
	/**
	 * 获取当前日期中的日
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentDay() {
		String day;
		SimpleDateFormat df = new SimpleDateFormat("d");
		day = df.format(new Date());
		return day;
	}

	/**
	 * 获取当前日期中的月
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentMonth() {
		String month;
		SimpleDateFormat df = new SimpleDateFormat("MM");
		month = df.format(new Date());
		return month;
	}

	/**
	 * 获取当前日期中的年
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentYear() {
		SimpleDateFormat df = new SimpleDateFormat(YEAR_FORMAT);
		return df.format(new Date());
	}

	/**
	 * 获取当前日期中的年与月
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentYearAndMonth() {
		SimpleDateFormat df = new SimpleDateFormat(YEAR_MONTH_FORMAT);
		return df.format(new Date());
	}
	
	/**
	 * 将字符串格式日期转换成long型
	 * 
	 * @param strDate
	 *            type:YYYY-MM-DD
	 * @return java.lang.Long
	 */
	public  static  Long getLongDate(String strDate) {
		Date date = java.sql.Date.valueOf(strDate);
		Long lDate = new Long(date.getTime());
		return (lDate);
	}

	/**
	 * 将字符串格式日期转换成long型 *
	 * 
	 * @param iType
	 *            value strDate type 0 :YYYY-MM-DD 1 :YYYY-MM-DD hh:mm:ss
	 * @return java.lang.Long
	 */
	public  static  Long getLongDate(String strDate, int iType) {
		Long retDate = null;
		switch (iType) {
		case 0:
			retDate = getLongDate(strDate);
			break;
		case 1:
			retDate = new Long(java.sql.Timestamp.valueOf(strDate).getTime());
			break;
		}
		return retDate;
	}

	/**
	 * 将Long格式日期转换成字符串型
	 * 
	 * @param lDate
	 *            iType value output 0 YYYY-MM-DD 1 YYYY-MM-DD hh 2 YYYY-MM-DD
	 *            hh:ss 3 YYYY-MM-DD hh:ss:mm
	 * @return java.lang.String
	 */

	public  static  String getStrDate(java.lang.Long lDate, int iType) {
		Date date = new Date(lDate.longValue());
		SimpleDateFormat simpleDateFormat = null;
		if (lDate == null) {
			return "";
		}
		switch (iType) {
		case 0:
			simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
			break;
		case 1:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			break;
		case 2:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		case 3:
			simpleDateFormat = new SimpleDateFormat(FULL_ST_FORMAT);
			break;
		}

		String strDate = simpleDateFormat.format(date);
		return (strDate);
	}

	/**
	 * 将字符型日期加入指定天数(add in 2003.11.21
	 * 
	 * @author windy)
	 *         <p>
	 *         for example:
	 *         </p>
	 *         <p>
	 *         getDate("1970-1-1",2),so this will return "1970-1-3"
	 *         </p>
	 * @param String
	 *            aDate
	 * @param int
	 *            dif
	 * @return java.lang.String
	 */
	public  static  String getDate(String aDate, int dif) {
		java.sql.Date date = null;
		try {
			date = java.sql.Date.valueOf(aDate);
		} catch (Exception e) {
			logger.error("Application log:Catch Exception in getDate()");
			logger.error("aDate:" + aDate);
			logger.error(e.getMessage());
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, dif);
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(calendar.getTime());
		return s;
	}// eof getDate(String,int)

	/**
	 * 获取字符型日期的下一个月时间(add in 2003.11.21
	 * 
	 * @author windy)
	 *         <p>
	 *         for example:
	 *         </p>
	 *         <p>
	 *         getDateAfterMonth("1970-1-1"),so this will return "1970-2-1"
	 *         </p>
	 * @param String
	 *            aDate
	 * @return java.lang.String
	 */
	public  static  String getDateAfterMonth(String aDate) {
		java.sql.Date date1 = null;
		try {
			date1 = java.sql.Date.valueOf(aDate);
		} catch (Exception e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(2, 1);
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(calendar.getTime());
		return s;
	}// eof getDateAfterMonth(aDate)

	/**
	 * 获取字符型日期的下一个（年，月，日时间） 比如：getAfterOrBeforDate("1981-05-09",1,5) 返回
	 * "1986-05-09" getAfterOrBeforDate("1981-05-09",2,5) 返回 "1981-10-09"
	 * getAfterOrBeforDate("1981-05-09",5,5) 返回 "1981-05-14"
	 * 
	 * @param aDate
	 *            "1979-02-05"
	 * @param type
	 *            值为 1:增加或减少年 2: 增加或减少月 5: 增加或减少天
	 * @param num
	 *            增加或减少年的数量
	 * @return "yyyy-MM-dd"
	 */
	public  static  String getAfterOrBeforDate(String aDate, int type, int num) {
		java.sql.Date date1 = null;
		try {
			date1 = java.sql.Date.valueOf(aDate);
		} catch (Exception e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(type, num);
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(calendar.getTime());
		return s;
	}// eof getDateAfterMonth(aDate)

	/**
	 * 获取字符型日期的下一个月的指定时间(add in 2003.11.21
	 * 
	 * @author windy)
	 *         <p>
	 *         for example:
	 *         </p>
	 *         <p>
	 *         getDateAfterMonth("1970-1-1",12),so this will return "1970-2-12"
	 *         </p>
	 * @param String
	 *            aDate
	 * @return java.lang.String
	 */
	public  static  String getDateAfterMonth(String aDate, int n) {
		SimpleDateFormat df1 = new SimpleDateFormat(DAY_FORMAT);
		Date date1 = null;
		try {
			date1 = df1.parse(aDate);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		
		calendar.add(Calendar.MONTH, n);
		//calendar.set(5, n);
		SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
		String s = df.format(calendar.getTime());
		return s;
	}// eof getDateAfterMonth(String,int)

	/**
	 * 获取字符型日期的当月的最后一天(add in 2003.11.21
	 * 
	 * @author windy)
	 *         <p>
	 *         for example:
	 *         </p>
	 *         <p>
	 *         getLastDate("1970-1-1"),so this will return 31
	 *         </p>
	 * @param String
	 *            aDate
	 * @return int 当月最后一天的号码
	 */
	public  static  int getLastDate(String selectDate) {
		int dates = 0;
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat df = new SimpleDateFormat(DAY_FORMAT);
			calendar.setTime(df.parse(selectDate));
		} catch (ParseException e) {
		}
		int year = calendar.get(1);
		switch (calendar.get(2) + 1) {
		default:
			break;

		case 1: // '\001'
			dates = 31;
			break;

		case 2: // '\002'
			if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0)
				dates = 29;
			else
				dates = 28;
			break;

		case 3: // '\003'
			dates = 31;
			break;

		case 4: // '\004'
			dates = 30;
			break;

		case 5: // '\005'
			dates = 31;
			break;

		case 6: // '\006'
			dates = 30;
			break;

		case 7: // '\007'
			dates = 31;
			break;

		case 8: // '\b'
			dates = 31;
			break;

		case 9: // '\t'
			dates = 30;
			break;

		case 10: // '\n'
			dates = 31;
			break;

		case 11: // '\013'
			dates = 30;
			break;

		case 12: // '\f'
			dates = 31;
			break;
		}
		return dates;
	}// eof getLastDate(String)

	/*
	 * 获取ORACLE的默认日期格式时间(add in 2003-11-21 @author:windy) @param String aDate
	 * 如："2003-1-3" @return String 如："03-一月-2003 12:00:00 AM"
	 */
	public  static  String getOracleDefaultDate(String aDate) {
		String returnDate = new String();
		Date date1 = null;
		try {
			String[] dateArray = aDate.split("-");
			String thisYear = dateArray[0];
			int date1Month = Integer.parseInt(dateArray[1]);
			int date1Date = Integer.parseInt(dateArray[2]);
			String thisDate = new String();
			if (date1Date < 10) {
				thisDate = "0".concat(String.valueOf(date1Date));
			} else {
				thisDate = String.valueOf(date1Date);
			}
			String thisMonth = new String();
			switch (date1Month) {
			case 1:
				thisMonth = "一月";
				break;
			case 2:
				thisMonth = "二月";
				break;
			case 3:
				thisMonth = "三月";
				break;
			case 4:
				thisMonth = "四月";
				break;
			case 5:
				thisMonth = "五月";
				break;
			case 6:
				thisMonth = "六月";
				break;
			case 7:
				thisMonth = "七月";
				break;
			case 8:
				thisMonth = "八月";
				break;
			case 9:
				thisMonth = "九月";
				break;
			case 10:
				thisMonth = "十月";
				break;
			case 11:
				thisMonth = "十一月";
				break;
			case 12:
				thisMonth = "十二月";
				break;
			}
			returnDate = thisDate.concat("-").concat(thisMonth).concat("-")
					.concat(thisYear).concat(" 12:00:00 AM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDate;
	}// eof getOracleDefaultDate(String)

	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

	/*
	 * 获取中国地区的星期几(add in 2004-1-13 @author:windy) @param int intNum @return
	 * String 如："星期一"
	 */
	public  static  String getDayDesc(int intNum) {
		String rn = "";
		if (intNum < 0 || intNum > 6) {
			rn = "";
		}
		switch (intNum) {
		case 0:
			rn = "星期一";
			break;
		case 1:
			rn = "星期二";
			break;
		case 2:
			rn = "星期三";
			break;
		case 3:
			rn = "星期四";
			break;
		case 4:
			rn = "星期五";
			break;
		case 5:
			rn = "星期六";
			break;
		case 6:
			rn = "星期日";
			break;
		}
		return rn;
	}

	/**
	 * 将日期由字符串转成日期型
	 * 
	 * @param s
	 *            yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public  static  Date getDateD(String s) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		return dateFormatter.parse(s, pos);
	}

	/**
	 * 比较两个日期 返回值为第一个日期是否在第二个日期之后
	 * 1 第一个日期大于第二个日期
	 * 0 第一个日期等于第二个日期
	 * -1 第一个日期小于第二个日期
	 */
	public  static  String afterDate(String sDate1, String sDate2,String dateFormatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = dateFormat.parse(sDate1);
			date2 = dateFormat.parse(sDate2);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		String afterThis = "1";
		if (date2.after(date1))
			afterThis = "-1";
		if(date2.equals(date1))
			afterThis = "0";
			
		return afterThis;
	}

	/**
	 * 将一个毫秒表示的两个日期之间的天数转换成天数
	 * 
	 * @param str
	 *            毫秒表示的天数
	 * @return 天数
	 */
	public  static  int shixian(String str) {
		String days = "0";
		int dayNums = 0;
		float dayNum = 0f;

		if (str == null || "".equals(str))
			return 0;
		try {
			dayNum = Float.parseFloat(str) / 1000 / 60 / 60 / 24;
			if (dayNum < 1) {
				days = "0.0";
			} else {
				days = String.valueOf(dayNum);
			}
			if (days.indexOf(".") != -1) {
				days = days.substring(0, days.indexOf("."));
				days = String.valueOf(Integer.parseInt(days) + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dayNums = Integer.parseInt(days);
		return dayNums;
	}

	/**
	 * 将一个毫秒表示的两个日期之间的天数转换成天数
	 * 
	 * @param str
	 *            毫秒表示的天数
	 * @return 天数
	 */
	public  static  String shixianPoint(String str) {
		String days = "0";
		int dayNums = 0;
		float dayNum = 0f;
		float minValue = Float.parseFloat("0.001");
		if (str == null || "".equals(str))
			return "0.00";
		try {
			dayNum = Float.parseFloat(str) / 1000 / 60 / 60 / 24;
			dayNum = Math.max(dayNum, minValue);
			days = String.valueOf(dayNum);
			if (days.indexOf(".") != -1) {
				if (days.length() >= (days.indexOf(".") + 5)) {
					int qs = Integer.parseInt(days.substring(
							(days.indexOf(".") + 4), (days.indexOf(".") + 5)));
					if (qs >= 5) {
						days = (Float.parseFloat(days) + 0.001) + "";
					}
				}
				days = days.substring(0, (days.indexOf(".") + 4));
				if (days.equals("0.000")) {
					days = "0.001";
				}
				// days = String.valueOf(Integer.parseInt(days)+1);
			} else {
				days = days + ".000";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// dayNums = Integer.parseInt(days);
		return days;
	}
	
	/**
	 * 得到一个月有多少天
	 * @param month	月
	 * @return
	 */
	public static int getMonthDays(String month){
		  
		Calendar   cal   =   Calendar.getInstance();   
	    cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));   
	    cal.set(Calendar.MONTH,Integer.parseInt(month.substring(month.indexOf("-")+1,month.length()))-1);//7月   
	    int   maxDate   =   cal.getActualMaximum(Calendar.DATE);//当前月最大天数
	    return maxDate;
	    
	}

	/***
	 *  得到指定日期后的日期
	 * @param d
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date addDate(Date d,int day) throws ParseException { 
		  long time = d.getTime(); 
		  day = day*24*60*60*1000; 
		  time+=day; 
		  return new Date(time); 
	} 

	/**
	 * 获取当前日期中的月
	 * 
	 * @return java.lang.String
	 */
	public  static  String getCurrentHour() {
		String month;
		SimpleDateFormat df = new SimpleDateFormat("HH");
		month = df.format(new Date());
		return month;
	}
	
	/**
	 * 计算当月最后一天,返回字符串
	 * @return
	 */
	public static String getDefaultDay(){  
	   String str = "";
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    

	   Calendar lastDate = Calendar.getInstance();
	   lastDate.set(Calendar.DATE,1);//设为当前月的1号
	   lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号
	   lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
	   
	   str=sdf.format(lastDate.getTime());
	   return str;  
	}
	
	/**
	 * 获取当月第一天
	 * @return
	 */
	public static String getFirstDayOfMonth(){  
	   String str = "";
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    

	   Calendar lastDate = Calendar.getInstance();
	   lastDate.set(Calendar.DATE,1);//设为当前月的1号
	   str=sdf.format(lastDate.getTime());
	   return str;  
	}
	
	/**
	 * 指定日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthStart(String aDate) {
		
		
		
		java.sql.Date date = null;
		try {
			date = java.sql.Date.valueOf(aDate);
		} catch (Exception e) {
			logger.error("Application log:Catch Exception in getDate()");
			logger.error("aDate:" + aDate);
			logger.error(e.getMessage());
			return null;
		}

		String str = "";
		Calendar cdate = dateToCalendar(date);
		cdate.set(Calendar.DAY_OF_MONTH, 1);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		str=sdf.format(cdate.getTime());
		return str;  
//		System.out.println("指定日期所在月的第一天:"+toShortDate(cdate.getTime()));
//		return toShortDate(cdate.getTime());
	}
	
	
	/**
	 * 指定日期所在月最后一天
	 */
	public static String getLastMonthEndDate(String aDate) {
		java.sql.Date date = null;
		try {
			date = java.sql.Date.valueOf(aDate);
		} catch (Exception e) {
			logger.error("Application log:Catch Exception in getDate()");
			logger.error("aDate:" + aDate);
			logger.error(e.getMessage());
			return null;
		}

		String str = "";
//		Calendar cdate = dateToCalendar(date);
		Calendar cdate = Calendar.getInstance();
		cdate.setTime(date);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    

		   cdate.set(Calendar.DATE,1);//设为当前月的1号
		   cdate.add(Calendar.MONTH,1);//加一个月，变为下月的1号
		   cdate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		   
		   str=sdf.format(cdate.getTime());
		   System.out.println("指定日期的月份的最后一天："+str);
		   return str; 
		
		
	}
	
	public static void main(String args[]){
		getLastMonthEndDate("2014-09-08");
	}
	
	
	/**
	 * 将java.util.Date类型转换成java.util.Calendar类型
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cdate = Calendar.getInstance();
		cdate.setTime(date);
		return cdate;
	}
}