package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.IQueueEvaluationDao;
import com.suntendy.queue.evaluation.domain.QueueEvaluation;
import com.suntendy.queue.evaluation.service.IQueueEvaluationService;

public class QueueEvaluationServiceImpl implements IQueueEvaluationService {
	
	private IQueueEvaluationDao queueEvaluationDao;

	public List<QueueEvaluation> getQueueEvaluate(String tjms, String startTime, String EndTime,String deptCode,String deptHall) throws Exception {
		return queueEvaluationDao.getQueueEvaluate(tjms, startTime, EndTime, deptCode, deptHall);
	}

	public IQueueEvaluationDao getQueueEvaluationDao() {
		return queueEvaluationDao;
	}

	public void setQueueEvaluationDao(IQueueEvaluationDao queueEvaluationDao) {
		this.queueEvaluationDao = queueEvaluationDao;
	}
}
