package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.IWindowEvaluationDao;
import com.suntendy.queue.evaluation.domain.WindowEvaluation;
import com.suntendy.queue.evaluation.service.IWindowEvaluationService;

public class WindowEvaluationServiceImpl implements IWindowEvaluationService {

	private IWindowEvaluationDao windowEvaluationdao;

	public List<WindowEvaluation> getWindowEvaluate(String tjms, String startTime, String EndTime,String deptCode,String deptHall,String address,String xm) throws Exception {
		return windowEvaluationdao.getWindowEvaluate(tjms, startTime, EndTime, deptCode, deptHall,address,xm);
	}

	public IWindowEvaluationDao getWindowEvaluationdao() {
		return windowEvaluationdao;
	}

	public void setWindowEvaluationdao(IWindowEvaluationDao windowEvaluationdao) {
		this.windowEvaluationdao = windowEvaluationdao;
	}
}
