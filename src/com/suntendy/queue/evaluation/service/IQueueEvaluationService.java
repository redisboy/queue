package com.suntendy.queue.evaluation.service;

import java.util.List;

import com.suntendy.queue.evaluation.domain.QueueEvaluation;

public interface IQueueEvaluationService {
	
	/**
	 * 队列评价统计
	 * @return List
	 */
	public List<QueueEvaluation> getQueueEvaluate(String tjms, String startTime, String EndTime,String deptCode,String deptHall) throws Exception;
}