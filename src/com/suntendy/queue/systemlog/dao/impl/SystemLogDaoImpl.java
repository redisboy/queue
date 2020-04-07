package com.suntendy.queue.systemlog.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.systemlog.dao.ISystemLogDao;
import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;

public class SystemLogDaoImpl extends BaseDao<Operate, String> implements ISystemLogDao{

	@Override
	public List<Operate> queryOperate(String tjms,String ksrq, String jsrq,Operate operate) throws Exception {
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
		String properties[] = {"userName", "originIP", "module", "eventType", "coreBusiness","deptCode","deptHall","jsrq","ksrq" };
	    String propertyValues[] = {operate.getUserName(), operate.getOriginIp(), operate.getModule(), operate.getEventType(),  operate.getCoreBusiness(), operate.getDeptCode(), operate.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
	    return this.getSqlMapClientTemplate().queryForList("SystemLog.queryOperate",map);
	}

	@Override
	public List<Loginls> querylogin(String tjms, String ksrq, String jsrq,
			Loginls loginls) throws Exception {
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
		String properties[] = {"userName", "result", "deptCode","deptHall","jsrq","ksrq" };
	    String propertyValues[] = {loginls.getUserName(), loginls.getResult(), loginls.getDeptCode(), loginls.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.queryLoginls",map);
	}

	@Override
	public List<Security> querySecurity(String tjms, String ksrq, String jsrq,
			Security security) throws Exception {
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
		String properties[] = {"userName", "originIP","deptCode","deptHall","jsrq","ksrq" };
	    String propertyValues[] = {security.getUserName(), security.getOriginIp(), security.getDeptCode(), security.getDeptHall(),jsrq,ksrq};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.querySecurity",map);
	}
	/**
	 *   插入登录信息到日志
	 * @param loginls  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	@Override
	public Integer addLoginls(Loginls loginls) throws Exception {
		this.getSqlMapClientTemplate().insert("SystemLog.addLoginls",loginls);
		return 0;
	}

	@Override
	public Integer updateLoginlsExit(Date date) throws Exception {
		this.getSqlMapClientTemplate().update("SystemLog.addLoginlsExit",date);
		return 0;
	}

	@Override
	public Integer addOperate(Operate operate) throws Exception {
		if(operate.getOpName()==null){
			operate.setOpName("无");
		}
		this.getSqlMapClientTemplate().insert("SystemLog.addOperate",operate);
		return 0;
	}

	@Override
	public Integer addSecurity(Security security) throws Exception {
		if(security.getOpName()==null){
			security.setOpName("无");
		}
		this.getSqlMapClientTemplate().insert("SystemLog.addSecurity",security);
		return 0;
	}

	@Override
	public List<Loginls> queryLoginErr(Loginls loginls) throws Exception {
		String properties[] = {"userName" };
	    String propertyValues[] = {loginls.getUserName()};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.queryLoginErr",map);
	}

	@Override
	public List<Loginls> queryLoginSuu(Loginls loginls) throws Exception {
		String properties[] = {"userName" };
	    String propertyValues[] = {loginls.getUserName()};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.queryLoginSuu",map);
	}

	@Override
	public String queryLoginCount(Loginls loginls) throws Exception {
		String userName=loginls.userName;
		return (String) this.getSqlMapClientTemplate().queryForObject("SystemLog.queryLoginCount",userName);
	}

	@Override
	public List<Operate> qureyDetailed(String id) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String properties[] = {"id" ,"deptCode","deptHall"};
	    String propertyValues[] = {id,deptCode,deptHall};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.queryOperateId",map);
	}

	@Override
	public List<Security> qureyDetailedSe(String id) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String properties[] = {"id" ,"deptCode","deptHall"};
	    String propertyValues[] = {id,deptCode,deptHall};
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
			map.put("orderBy", "");
			map.put("order", "");
		return this.getSqlMapClientTemplate().queryForList("SystemLog.querySecurityId",map);
	}
}
