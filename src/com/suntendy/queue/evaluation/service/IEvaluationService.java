package com.suntendy.queue.evaluation.service;

import java.util.List;

import com.suntendy.queue.evaluation.domain.EmployeeEvaluation;

public interface IEvaluationService {
	
	/**
	 * 员工评价统计
	 * @return List
	 */
	public List<EmployeeEvaluation> getEmployeeEvaluate(String tjms, String startTime, String EndTime,String deptCode,String deptHall) throws Exception;
}