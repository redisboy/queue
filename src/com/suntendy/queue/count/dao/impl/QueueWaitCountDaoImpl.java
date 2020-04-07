package com.suntendy.queue.count.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.count.dao.IQueueWaitCountDao;
import com.suntendy.queue.count.domain.QueueWaitCount;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.window.domain.Screen;

public class QueueWaitCountDaoImpl extends BaseDao<QueueWaitCount, Integer> implements IQueueWaitCountDao{

	public List<QueueWaitCount> queueWaitCount(String barid,String tjms, String ksrq, String jsrq,String deptCode,String deptHall) throws Exception {

		if (tjms.equals("0")) {
			if (ksrq != "" && jsrq != "") {
//				ksrq = "to_date('" + ksrq + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
//				jsrq = "to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				ksrq = "to_date('" + ksrq +":00','yyyy-mm-dd hh24:mi:ss')";
				jsrq = "to_date('" + jsrq +":59','yyyy-mm-dd hh24:mi:ss')";
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
		String[] properties = { "barid","ksrq", "jsrq" ,"deptCode","deptHall"};
		String[] propertyValues = { barid,ksrq, jsrq,deptCode,deptHall };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public List<QueueWaitCount> queryBar(String deptCode, String deptHall) throws Exception {
		String[] properties = {"deptCode","deptHall"};
		String[] propertyValues = { deptCode,deptHall };
		return this.findByMap(properties, propertyValues, "", "", "queryBarid");
	}
}