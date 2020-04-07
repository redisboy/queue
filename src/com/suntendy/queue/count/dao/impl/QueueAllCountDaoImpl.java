package com.suntendy.queue.count.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.count.dao.IQueueAllCountDao;
import com.suntendy.queue.count.domain.QueueAllCount;
import com.suntendy.queue.util.DateUtils;

public class QueueAllCountDaoImpl extends BaseDao<QueueAllCount, Integer> implements IQueueAllCountDao{

	public List<QueueAllCount> queueAllCount(QueueAllCount queueAllCount) throws Exception {
		if (queueAllCount.getTjms().equals("0")) {
			if (queueAllCount.getKsrq() != "" && queueAllCount.getJsrq() != "") {
				queueAllCount.setKsrq("to_date('" + queueAllCount.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				queueAllCount.setJsrq("to_date('" + queueAllCount.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (queueAllCount.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			queueAllCount.setKsrq("to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			queueAllCount.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (queueAllCount.getTjms().equals("2")) {
			queueAllCount.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			queueAllCount.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (queueAllCount.getTjms().equals("3")) {
			queueAllCount.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			queueAllCount.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (queueAllCount.getTjms().equals("4")) {
			queueAllCount.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			queueAllCount.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = {"ksrq", "jsrq" ,"deptCode","deptHall" };
		String[] propertyValues = {queueAllCount.getKsrq(),queueAllCount.getJsrq(),queueAllCount.getDeptCode(),queueAllCount.getDeptHall() };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}
}