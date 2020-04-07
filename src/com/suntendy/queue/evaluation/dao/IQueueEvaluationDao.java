package com.suntendy.queue.evaluation.dao;

import java.util.List;

import com.suntendy.queue.evaluation.domain.QueueEvaluation;


public interface IQueueEvaluationDao {

	/**
	 * 队列评价统计
	 * @return List 评价信息
	 * @throws Exception 
	 */
	public abstract List<QueueEvaluation> getQueueEvaluate(String tjms,String StartTime,String EndTime,String deptCode,String deptHall) throws Exception ;
}