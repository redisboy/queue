package com.suntendy.queue.ywshtj.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.ywshtj.dao.IYwShTjDao;
import com.suntendy.queue.ywshtj.domain.YwShTj;

public class YwShTjDaoImpl extends BaseDao<YwShTj, Integer> implements IYwShTjDao {

	/**
	 * 对业务人员(管理部门)平均办理时间排名统计
	 */
	public List<YwShTj> getEmplooyTransactTimeOrder(YwShTj ywShTj) throws Exception {
		if (ywShTj.getTjms().equals("0")) {
			if (ywShTj.getKsrq() != "" && ywShTj.getJsrq() != "") {
				ywShTj.setKsrq("to_date('" + ywShTj.getKsrq() + ":00','yyyy-mm-dd hh24:mi:ss')");
				ywShTj.setJsrq("to_date('" + ywShTj.getJsrq() + ":59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (ywShTj.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			ywShTj.setKsrq( "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("2")) {
			ywShTj.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("3")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("4")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = { "ksrq", "jsrq","deptCode", "deptHall","ywlx"};
		String[] propertyValues = { ywShTj.getKsrq(), ywShTj.getJsrq(),ywShTj.getDeptcode(), ywShTj.getDeptHall(),ywShTj.getYwlx() };
		if(ywShTj.getFlag().equals("0")){
			return this.findByMap(properties, propertyValues, "", "", "getEmplooyTransactTimeOrder");
		}else{
			return this.findByMap(properties, propertyValues, "", "", "getDeptcodeTransactTimeOrder");
		}
	}

	/**
	 * 对业务人员(管理部门)差评排名统计
	 */
	public List<YwShTj> getEmplooyBadReviewOrder(YwShTj ywShTj) throws Exception {
		if (ywShTj.getTjms().equals("0")) {
			if (ywShTj.getKsrq() != "" && ywShTj.getJsrq() != "") {
				ywShTj.setKsrq("to_date('" + ywShTj.getKsrq() + ":00','yyyy-mm-dd hh24:mi:ss')");
				ywShTj.setJsrq("to_date('" + ywShTj.getJsrq() + ":59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (ywShTj.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			ywShTj.setKsrq( "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("2")) {
			ywShTj.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("3")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("4")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = { "ksrq", "jsrq","deptCode", "deptHall","ywlx" };
		String[] propertyValues = { ywShTj.getKsrq(), ywShTj.getJsrq(),ywShTj.getDeptcode(), ywShTj.getDeptHall(),ywShTj.getYwlx() };
		if(ywShTj.getFlag().equals("0")){
			return this.findByMap(properties, propertyValues, "", "", "getEmplooyBadReviewOrder");
		}else{
			return this.findByMap(properties, propertyValues, "", "", "getDeptcodeBadReviewOrder");
		}
	}

	/**
	 * 对业务人员(管理部门)暂停时间排名统计
	 */
	public List<YwShTj> getEmplooyPauseTimeOrder(YwShTj ywShTj) throws Exception {
		if (ywShTj.getTjms().equals("0")) {
			if (ywShTj.getKsrq() != "" && ywShTj.getJsrq() != "") {
				ywShTj.setKsrq("to_date('" + ywShTj.getKsrq() + ":00','yyyy-mm-dd hh24:mi:ss')");
				ywShTj.setJsrq("to_date('" + ywShTj.getJsrq() + ":59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (ywShTj.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			ywShTj.setKsrq( "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("2")) {
			ywShTj.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("3")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("4")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = { "ksrq", "jsrq","deptCode", "deptHall","ywlx" };
		String[] propertyValues = { ywShTj.getKsrq(), ywShTj.getJsrq(),ywShTj.getDeptcode(), ywShTj.getDeptHall(),ywShTj.getYwlx() };
		if(ywShTj.getFlag().equals("0")){
			return this.findByMap(properties, propertyValues, "", "", "getEmplooyPauseTimeOrder");
		}else{
			return this.findByMap(properties, propertyValues, "", "", "getDeptcodePauseTimeOrder");
		}
	}

	/**
	 * 对管理部门平均等候时间统计
	 */
	public List<YwShTj> getDepartWaitTimeOrder(YwShTj ywShTj)throws Exception {
		if (ywShTj.getTjms().equals("0")) {
			if (ywShTj.getKsrq() != "" && ywShTj.getJsrq() != "") {
//				ywShTj.setKsrq("to_date('" + ywShTj.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
//				ywShTj.setJsrq("to_date('" + ywShTj.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				ywShTj.setKsrq("to_date('" + ywShTj.getKsrq() + ":00','yyyy-mm-dd hh24:mi:ss')");
				ywShTj.setJsrq("to_date('" + ywShTj.getJsrq() + ":59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (ywShTj.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			ywShTj.setKsrq( "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("2")) {
			ywShTj.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("3")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (ywShTj.getTjms().equals("4")) {
			ywShTj.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			ywShTj.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = { "ksrq", "jsrq","deptCode", "deptHall","ywlx" };
		String[] propertyValues = { ywShTj.getKsrq(), ywShTj.getJsrq(),ywShTj.getDeptcode(), ywShTj.getDeptHall(),ywShTj.getYwlx() };
		return this.findByMap(properties, propertyValues, "", "", "getEmplooyWaitTimeOrder");
	}

}