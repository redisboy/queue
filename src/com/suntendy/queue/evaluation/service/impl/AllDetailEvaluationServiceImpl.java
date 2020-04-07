package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.IAllDetailEvaluationDao;
import com.suntendy.queue.evaluation.domain.AllDetail;
import com.suntendy.queue.evaluation.service.IAllDetailEvaluationService;

public class AllDetailEvaluationServiceImpl implements IAllDetailEvaluationService {

	private IAllDetailEvaluationDao allDetailEvaluationDao;

	public List<AllDetail> getAllDetailEvaluation(String tjms, String startTime, String EndTime, String jbr, String code,String deptCode,String deptHall)
			throws Exception {
		List<AllDetail> datas = allDetailEvaluationDao.getAllDetailEvaluation(tjms, startTime, EndTime, jbr, code, deptCode, deptHall);
		for (AllDetail detail : datas) {
			if (detail.getWaits().startsWith(".")) {
				detail.setWaits("0" + detail.getWaits());
			}
			if (detail.getWorks().startsWith(".")) {
				detail.setWorks("0" + detail.getWorks());
			}
		}
		return datas;
	}

	public IAllDetailEvaluationDao getAllDetailEvaluationDao() {
		return allDetailEvaluationDao;
	}

	public void setAllDetailEvaluationDao(
			IAllDetailEvaluationDao allDetailEvaluationDao) {
		this.allDetailEvaluationDao = allDetailEvaluationDao;
	}
}
