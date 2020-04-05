package com.suntendy.queue.evaluation.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.evaluation.dao.IBadEvaluationDao;
import com.suntendy.queue.evaluation.domain.BadEvaluation;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;

public class BadEvaluationDaoImpl extends BaseDao<BadEvaluation, String> implements
		IBadEvaluationDao {

	public List<BadEvaluation> queryBadEvaluationByCondition(String deptCode,String deptHall,
			String code,String barid,String tjms, String ksrq, String jsrq) throws Exception {
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
		String[] properties = {"deptCode","deptHall","ksrq","jsrq","code","barid"};
		Object[] propertyValues = {deptCode,deptHall,ksrq,jsrq,code,barid};
		return this.findByMap(properties, propertyValues, "t.valuetime", "desc", BaseDao.SELECTBYMAP);
	}

	public List<BadEvaluation> queryBadEvaluationById(String id) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String[] properties = {"deptCode","deptHall","id"};
		Object[] propertyValues = {deptCode,deptHall,id};
//		return this.findById(id, BaseDao.SELECTBYID);
		BadEvaluation badEvaluation = new BadEvaluation();
		badEvaluation.setId(id);
		badEvaluation.setDeptHall(deptHall);
		return this.findByMap(properties, propertyValues, "", "", "badfindById");
	}

	public void addBadEvaluationSeason(BadEvaluation badEvaluation)
			throws Exception {
		this.insert(badEvaluation, "addBadEvaluationSeason");
	}

	public void updateBadEvaluationSeason(BadEvaluation badEvaluation)
			throws Exception {
		String[] properties = {"season"};
		Object[] propertyValues = {badEvaluation.getSeason()};
		this.updateByMap(badEvaluation.getId(), properties, propertyValues, "updateEvaluationSeason");
	}

}
