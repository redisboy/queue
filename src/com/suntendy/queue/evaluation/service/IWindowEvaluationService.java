package com.suntendy.queue.evaluation.service;

import java.util.List;

import com.suntendy.queue.evaluation.domain.WindowEvaluation;

public interface IWindowEvaluationService {

	/**
	 * 窗口评价统计
	 * @return List
	 */
	public List<WindowEvaluation> getWindowEvaluate(String tjms, String startTime, String EndTime,String deptCode,String deptHall,String address,String xm) throws Exception;
}