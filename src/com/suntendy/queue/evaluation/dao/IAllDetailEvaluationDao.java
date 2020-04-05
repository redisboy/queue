package com.suntendy.queue.evaluation.dao;

import java.util.List;

import com.suntendy.queue.evaluation.domain.AllDetail;


public interface IAllDetailEvaluationDao {
	/**
	 * 详细评价记录统计
	 * @return List 评价信息
	 */
	public  List<AllDetail> getAllDetailEvaluation(String tjms,String StartTime,String EndTime,String jbr,String code,String deptCode,String deptHall)throws Exception;
}