package com.suntendy.queue.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtilFormat {
		/**
		 * 
		 * @param date
		 * @param flag 0转换到秒  1转换到天
		 * @return
		 */
		public static String timeType(Date date,int flag) {
			SimpleDateFormat sdf;
			if(flag==0){
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			if (date != null && !date.equals("")) {
				return sdf.format(date);
			} else {
				return "";
			}
		}
		
		/**
		 * 
		 * @param stateTime  当天时间
		 * @param endTime    到期时间
		 * @return
		 */
		public static long timeLong(String stateTime,String endTime){
	        long day = 0;
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date state = sdf.parse(stateTime);
	            Date end  = sdf.parse(endTime);

	            long stateTimeLong = state.getTime();
	            long endTimeLong = end.getTime();
	            // 结束时间-开始时间 = 天数
	            day = (endTimeLong-stateTimeLong)/(24*60*60*1000); 
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			return day;
		}
}
