package com.suntendy.queue.evaluation.dao;

import java.util.List;
import com.suntendy.queue.evaluation.domain.EmployeeEvaluation;


public interface IEvaluationDao {

	/**
	 * 员工评价统计
	 * @return List 评价信息
	 * @throws Exception 
	 */
	public abstract List<EmployeeEvaluation> getEmployeeEvaluate(String tjms,String StartTime,String EndTime,String deptCode,String deptHall) throws Exception;

}