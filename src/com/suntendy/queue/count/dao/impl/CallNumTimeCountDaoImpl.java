package com.suntendy.queue.count.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.count.dao.ICallNumTimeCountDao;
import com.suntendy.queue.count.domain.CallNumTimeCount;
import com.suntendy.queue.count.domain.ClientWaitCount;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.util.DateUtils;

public class CallNumTimeCountDaoImpl extends BaseDao<Number, String> implements ICallNumTimeCountDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CallNumTimeCount> queryCodeCount(String deptCode,
			String deptHall) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptCode", deptCode);
		map.put("deptHall", deptHall);
		return this.getSqlMapClientTemplate().queryForList("CallNumTimeCount.queryCode", map);
	}

	@Override
	public CallNumTimeCount queryAllCount(String tjms, String ksrq,
			String jsrq, String code, String deptCode, String deptHall)
			throws Exception {
		String boo = "true";   //初始     
		String ave=null;
		String window = null;
		Date begintime=null;
		Date endtime=null;
		Date endtimess=null;
		int time=0;
		int i=0;
		CallNumTimeCount antc=new CallNumTimeCount();
		SimpleDateFormat inputFormat1=new SimpleDateFormat("yyyy-mm-dd");//将字符型转换成日期型(天)
		SimpleDateFormat inputFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); //将字符型转换成日期型(秒)
		
		if (tjms.equals("0")) {
			if (ksrq != "" && jsrq != "") {
				ksrq = "to_date('" + ksrq + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				jsrq = "to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			}
		} else if (tjms.equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			ksrq = "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')";
			jsrq = "to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')";
		} else if (tjms.equals("2")) {
			ksrq = "to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')";
			jsrq = "to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		} else if (tjms.equals("3")) {
			ksrq = "to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			jsrq = "to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
		} else if (tjms.equals("4")) {
			ksrq = "to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			jsrq = "to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		map.put("code", code);
		map.put("deptCode", deptCode);
		map.put("deptHall", deptHall);
		List<CallNumTimeCount> callNumTimeCounts= this.getSqlMapClientTemplate().queryForList("CallNumTimeCount.queryAll", map);
		if(!callNumTimeCounts.isEmpty() && callNumTimeCounts!=null){
		for(CallNumTimeCount all:callNumTimeCounts){
			window=all.getBarid();
			if(null!=all.getBegintime() && all.getEndtime()!=null){
			if(boo!="true"){
				 try { 
					 begintime = inputFormat1.parse(all.getBegintime()); 
					 if(begintime.equals(endtime)){
						 begintime = inputFormat2.parse(all.getBegintime()); 
						 long s=begintime.getTime()- endtimess.getTime();
						 if(s>=0){
							 int t=(int) (s/60000)+time;
							 time=t;
							 i++;
						 }
					 }
					     boo="false";
						 endtime = inputFormat1.parse(all.getEndtime()); 
						 endtimess = inputFormat2.parse(all.getEndtime());		
					 
					} catch (Exception e) { 
						 e.printStackTrace(); 
					} 
			}else{
				 boo="false";
				 try { 
					 	endtime = inputFormat1.parse(all.getEndtime()); 
					 	endtimess = inputFormat2.parse(all.getEndtime());	
					  } catch (Exception e) { 
						e.printStackTrace(); 
					  } 
			}
		  }else{
			  boo="true";
		  }
		}
		if(i!=0){
			  ave=time/i+"";
			}
			antc.setBarid(window);
			antc.setAve(ave);
		}
		
		return antc;
	}

	

}
