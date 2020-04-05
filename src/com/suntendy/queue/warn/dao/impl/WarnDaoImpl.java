package com.suntendy.queue.warn.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.warn.dao.WarnDao;
import com.suntendy.queue.warn.domain.Warn;

public class WarnDaoImpl extends BaseDao<Warn,String>implements WarnDao {

	//查询pd_valuerecord数据
	@Override
	public List<Warn> searchWarn(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"barid","depthall","deptcode"};
		Object[] propertyValues = {warn.getBarid(),warn.getDepthall(),warn.getDeptcode()};
		return this.findByMap(properties, propertyValues, "", "", "findByMap");
	}
	
	//添加警告人员
	@Override
	public String saveWarn(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		return this.insert(warn, "saveWarn");
	}

	//查询间隔时间
	@Override
	public List<Warn> searchSjTime() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		Object[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "searchJgTime");
	}

	//查询警告人员列表pd_warn_log
	@Override
	public List<Warn> selectWarn(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		if (warn.getTjms().equals("0")) {
			if (warn.getKsrq() != "" && warn.getJsrq() != "") {
				warn.setKsrq("to_date('" + warn.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				warn.setJsrq("to_date('" + warn.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (warn.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			warn.setKsrq("to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			warn.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (warn.getTjms().equals("2")) {
			warn.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			warn.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (warn.getTjms().equals("3")) {
			warn.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			warn.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (warn.getTjms().equals("4")) {
			warn.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			warn.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = {"ksrq", "jsrq","depthall","deptcode"};
		String[] propertyValues = {warn.getKsrq(),warn.getJsrq(),warn.getDepthall(),warn.getDeptcode()};
		return this.findByMap(properties, propertyValues, "", "", "selectWarn");
	}
	
	 //查询当前时间	
	public List<Warn> searchNowTime() throws Exception {
		String[] properties = {};
		Object[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "searchNowTime");
	}
}
