package com.suntendy.queue.statistics.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.statistics.dao.IStatisticsLogDao;
import com.suntendy.queue.statistics.domain.HandlerLCount;
import com.suntendy.queue.statistics.domain.SaveLCount;
import com.suntendy.queue.statistics.domain.UserLCount;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.util.DateUtils;

public class StatisticsLogDaoImpl extends BaseDao<Operate, String> implements IStatisticsLogDao {
	
	
	//操作登录日志统计
	@Override
	public List queryLoginCount(String tjms, String ksrq, String jsrq,
			UserLCount userLCount) throws Exception {
		if(tjms.equals("0"))
        {
            if(ksrq != "" && jsrq != "")
            {
                ksrq = (new StringBuilder("to_date('")).append(ksrq).append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')").toString();
                jsrq = (new StringBuilder("to_date('")).append(jsrq).append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')").toString();
            }
        } else
        if(tjms.equals("1"))
        {
            String currentTime = DateUtils.dateToString("yyyy-MM-dd");
            String currentTime1 = (new StringBuilder("'")).append(currentTime).append(" 00:00:00").append("'").toString();
            String currentTime2 = (new StringBuilder("'")).append(currentTime).append(" 23:59:59").append("'").toString();
            ksrq = (new StringBuilder("to_date(")).append(currentTime1).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
            jsrq = (new StringBuilder("to_date(")).append(currentTime2).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
        } else
        if(tjms.equals("2"))
        {
            ksrq = "to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')";
            jsrq = "to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("3"))
        {
            ksrq = "to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("4"))
        {
            ksrq = "to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
		String properties[] = {"userName", "loginIP", "deptCode","deptHall","jsrq","ksrq" };
		String propertyValues[] = {userLCount.getUserName(), userLCount.getLoginIP(), userLCount.getDeptCode(), userLCount.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("statistics.queryLoginCount",map);
	}

		
	
	
	
	
	

	//操作日志统计
	@Override
	public List queryHandlerLCount(String tjms, String ksrq,
			String jsrq, HandlerLCount handlerLCount) throws Exception {
		
		if(tjms.equals("0"))
        {
            if(ksrq != "" && jsrq != "")
            {
                ksrq = (new StringBuilder("to_date('")).append(ksrq).append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')").toString();
                jsrq = (new StringBuilder("to_date('")).append(jsrq).append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')").toString();
            }
        } else
        if(tjms.equals("1"))
        {
            String currentTime = DateUtils.dateToString("yyyy-MM-dd");
            String currentTime1 = (new StringBuilder("'")).append(currentTime).append(" 00:00:00").append("'").toString();
            String currentTime2 = (new StringBuilder("'")).append(currentTime).append(" 23:59:59").append("'").toString();
            ksrq = (new StringBuilder("to_date(")).append(currentTime1).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
            jsrq = (new StringBuilder("to_date(")).append(currentTime2).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
        } else
        if(tjms.equals("2"))
        {
            ksrq = "to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')";
            jsrq = "to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("3"))
        {
            ksrq = "to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("4"))
        {
            ksrq = "to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
		String properties[] = {"userName","coreBusiness", "deptCode","deptHall","jsrq","ksrq" };
	    String propertyValues[] = {handlerLCount.getUserName(), handlerLCount.getCoreBusiness(), handlerLCount.getDeptCode(), handlerLCount.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("statistics.queryHandlerLCount",map);
	}
	

	
	
	
	
	//安全日志统计
	@Override
	public List querySaveLCount(String tjms, String ksrq,
			String jsrq, SaveLCount saveLCount) throws Exception {
		if(tjms.equals("0"))
        {
            if(ksrq != "" && jsrq != "")
            {
                ksrq = (new StringBuilder("to_date('")).append(ksrq).append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')").toString();
                jsrq = (new StringBuilder("to_date('")).append(jsrq).append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')").toString();
            }
        } else
        if(tjms.equals("1"))
        {
            String currentTime = DateUtils.dateToString("yyyy-MM-dd");
            String currentTime1 = (new StringBuilder("'")).append(currentTime).append(" 00:00:00").append("'").toString();
            String currentTime2 = (new StringBuilder("'")).append(currentTime).append(" 23:59:59").append("'").toString();
            ksrq = (new StringBuilder("to_date(")).append(currentTime1).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
            jsrq = (new StringBuilder("to_date(")).append(currentTime2).append(",'yyyy-mm-dd hh24:mi:ss')").toString();
        } else
        if(tjms.equals("2"))
        {
            ksrq = "to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')";
            jsrq = "to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("3"))
        {
            ksrq = "to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
        } else
        if(tjms.equals("4"))
        {
            ksrq = "to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            jsrq = "to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
		String properties[] = {"userName", "saveCel","deptCode","deptHall","jsrq","ksrq" };
	    String propertyValues[] = {saveLCount.getUserName(), saveLCount.getSaveCel(), saveLCount.getDeptCode(), saveLCount.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("statistics.querySaveLCount",map);
	}






	

}
