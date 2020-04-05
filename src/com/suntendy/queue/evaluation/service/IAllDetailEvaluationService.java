package com.suntendy.queue.evaluation.service;

import java.util.List;

import com.suntendy.queue.evaluation.domain.AllDetail;

public interface IAllDetailEvaluationService {

	/**
	 * 详细评价记录统计
	 * @return List 评价信息
	 */
	public List<AllDetail> getAllDetailEvaluation(String tjms, String startTime, String EndTime,String jbr,String code,String deptCode,String deptHall) throws Exception;
}