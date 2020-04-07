package com.suntendy.queue.evaluation.dao;

import java.util.List;

import com.suntendy.queue.evaluation.domain.WindowEvaluation;


public interface IWindowEvaluationDao {

	/**
	 * 窗口评价统计
	 * @return List 评价信息
	 */
	public  List<WindowEvaluation> getWindowEvaluate(String tjms,String StartTime,String EndTime,String deptCode,String deptHall,String address,String xm) throws Exception ;
}