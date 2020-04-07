package com.suntendy.queue.safety.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.safety.dao.SafetyDao;
import com.suntendy.queue.safety.domain.Safety;

public class SafetyDaoImpl extends BaseDao<Safety, String> implements SafetyDao {

	@Override
	public String addSafe(Safety safety) throws Exception {
		return (String) this.getSqlMapClientTemplate().insert("Safety.addSafe",
				safety);
	}

	@Override
	public List<Safety> searchSafe() throws Exception {
		return this.findByMap(null, null, "", "", "searchSafety");
	}

	@Override
	public int updateSafe(Safety safety) throws Exception {
		String[] properties = { "gdhour", "gdminute", "gdhour1", "gdminute1",
				"timelimit", "visits", "ipSum", "userSum", "sessionSum",
				"logSum" };
		Object[] propertyValues = { safety.getGdhour(), safety.getGdminute(),
				safety.getGdhour1(), safety.getGdminute1(),
				safety.getTimelimit(), safety.getVisits(), safety.getIpSum(),
				safety.getUserSum(), safety.getSessionSum(), safety.getLogSum() };
		return updateByMap("1", properties, propertyValues, "updateSafety");
	}
	
	@Override
	public int delLogin(String logSum) throws Exception {
		this.deleteById(logSum, "delSecurity");
		this.deleteById(logSum, "delOperate");
		return this.deleteById(logSum, BaseDao.DELETEBYID);
	}
	
}
