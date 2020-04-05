package com.suntendy.queue.evaluation.service;

import java.util.List;

import com.suntendy.queue.evaluation.domain.BadEvaluation;

public interface IBadEvaluationService {

	/*
	 * 差评详细
	 */
	public List<BadEvaluation> queryBadEvaluationByCondition(String deptCode,String deptHall,String code,String barid,String tjms, String ksrq, String jsrq) throws Exception;
	
	public List<BadEvaluation> queryBadEvaluationById(String id) throws Exception;
	
	public void addBadEvaluationSeason(BadEvaluation badEvaluation) throws Exception;
	
	public void updateBadEvaluationSeason(BadEvaluation badEvaluation) throws Exception;
}
