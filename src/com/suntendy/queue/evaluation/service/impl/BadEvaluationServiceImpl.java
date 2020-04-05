package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.IBadEvaluationDao;
import com.suntendy.queue.evaluation.domain.BadEvaluation;
import com.suntendy.queue.evaluation.service.IBadEvaluationService;

public class BadEvaluationServiceImpl implements IBadEvaluationService {

	private IBadEvaluationDao badEvaluationDao;
	
	public IBadEvaluationDao getBadEvaluationDao() {
		return badEvaluationDao;
	}
	public void setBadEvaluationDao(IBadEvaluationDao badEvaluationDao) {
		this.badEvaluationDao = badEvaluationDao;
	}

	@Override
	public List<BadEvaluation> queryBadEvaluationByCondition(String deptCode,String deptHall,
			String code,String barid,String tjms, String ksrq, String jsrq) throws Exception {
		return badEvaluationDao.queryBadEvaluationByCondition(deptCode,deptHall,code, barid,tjms,ksrq,jsrq);
	}
	@Override
	public List<BadEvaluation> queryBadEvaluationById(String id) throws Exception {
		return badEvaluationDao.queryBadEvaluationById(id);
	}
	@Override
	public void addBadEvaluationSeason(BadEvaluation badEvaluation)
			throws Exception {
		badEvaluationDao.addBadEvaluationSeason(badEvaluation);
	}
	@Override
	public void updateBadEvaluationSeason(BadEvaluation badEvaluation)
			throws Exception {
		badEvaluationDao.updateBadEvaluationSeason(badEvaluation);
	}

}
