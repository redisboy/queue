package com.suntendy.queue.evaluation.dao.impl;

import java.util.List;
import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.evaluation.dao.IWindowEvaluationDao;
import com.suntendy.queue.evaluation.domain.WindowEvaluation;
import com.suntendy.queue.util.DateUtils;

public class WindowEvaluationDaoImpl extends BaseDao<WindowEvaluation, Integer>  implements IWindowEvaluationDao {

	public List<WindowEvaluation> getWindowEvaluate(String tjms, String ksrq,
			String jsrq ,String deptCode,String deptHall,String address,String xm) throws Exception   {

		if (tjms.equals("0")) {
			if (ksrq != "" && jsrq != "") {
				/*ksrq = "to_date('" + ksrq + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				jsrq = "to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";*/
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
		String[] properties = { "ksrq", "jsrq" ,"deptCode","deptHall","address","xm"};
		String[] propertyValues = { ksrq, jsrq,deptCode,deptHall,address,xm };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}
}