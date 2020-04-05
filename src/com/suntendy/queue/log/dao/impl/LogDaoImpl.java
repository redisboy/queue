package com.suntendy.queue.log.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.log.dao.ILogDao;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.util.DateUtils;

public class LogDaoImpl extends BaseDao<LogVo, Integer>  implements ILogDao {

	public List<LogVo> searchLog(LogVo logVo) throws Exception {
			if (logVo.getTjms().equals("0")) {
				if (logVo.getKsrq() != "" && logVo.getJsrq() != "") {
					logVo.setKsrq("to_date('" + logVo.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
					logVo.setJsrq("to_date('" + logVo.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
			} else if (logVo.getTjms().equals("1")) {
				String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
				String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
				String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
				logVo.setKsrq("to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
				logVo.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
			} else if (logVo.getTjms().equals("2")) {
				logVo.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
				logVo.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			} else if (logVo.getTjms().equals("3")) {
				logVo.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				logVo.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
			} else if (logVo.getTjms().equals("4")) {
				logVo.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				logVo.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
			String[] properties = {"code","logflag","ksrq", "jsrq"};
			Object[] propertyValues = {logVo.getCode(),logVo.getLogflag(),logVo.getKsrq(),logVo.getJsrq()};
			return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}
	
	public List<LogVo> getAllWarnCount(LogVo logVo) throws Exception {
		
		if (logVo.getTjms().equals("0")) {
			if (logVo.getKsrq() != "" && logVo.getJsrq() != "") {
				logVo.setKsrq("to_date('" + logVo.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				logVo.setJsrq("to_date('" + logVo.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (logVo.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			logVo.setKsrq("to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			logVo.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (logVo.getTjms().equals("2")) {
			logVo.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			logVo.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (logVo.getTjms().equals("3")) {
			logVo.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			logVo.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (logVo.getTjms().equals("4")) {
			logVo.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			logVo.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String[] properties = {"ksrq", "jsrq","code","logflag" };
		String[] propertyValues = {logVo.getKsrq(),logVo.getJsrq(),logVo.getCode(),logVo.getLogflag() };
		return this.findByMap(properties, propertyValues, "", "", "getAllWarnCount");
	}
	/**
	 * 添加日志信息
	 * @return List
	 */
	public void addLogContent(LogVo logVo) throws Exception {
		// TODO Auto-generated method stub
		this.insert(logVo, "addLogContent");
			
		}
	public void addUserLoginLog(LogVo log) throws Exception {
		this.insert(log, "addUserLoginLog");
	}

	public List<LogVo> searchNowDate() throws Exception {
		String[] properties = {};
		Object[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "searchNowDate");
	}

}
