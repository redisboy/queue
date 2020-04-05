package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.IEvaluationDao;
import com.suntendy.queue.evaluation.domain.EmployeeEvaluation;
import com.suntendy.queue.evaluation.service.IEvaluationService;

public class EvaluationServiceImpl implements IEvaluationService {
	
	private IEvaluationDao evaluationDao;
	public IEvaluationDao getEvaluationDao() {
		return evaluationDao;
	}

	public void setEvaluationDao(IEvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}

	public List<EmployeeEvaluation> getEmployeeEvaluate(String tjms, String startTime,
			String EndTime,String deptCode,String deptHall) throws Exception {
		return evaluationDao.getEmployeeEvaluate( tjms,startTime, EndTime, deptCode, deptHall)  ;
	}
}