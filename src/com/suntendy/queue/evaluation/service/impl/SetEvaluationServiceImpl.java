package com.suntendy.queue.evaluation.service.impl;

import java.util.List;

import com.suntendy.queue.evaluation.dao.ISetEvaluationDao;
import com.suntendy.queue.evaluation.domain.ValueRecord;
import com.suntendy.queue.evaluation.service.ISetEvaluationService;

public class SetEvaluationServiceImpl implements ISetEvaluationService {
    private ISetEvaluationDao setEvaluationDao;

	public void setSetEvaluationDao(ISetEvaluationDao setEvaluationDao) {
		this.setEvaluationDao = setEvaluationDao;
	}

	@Override
	public void add(List<ValueRecord> datas) throws Exception {
		setEvaluationDao.add(datas);
	}

	public void updatePj(List<ValueRecord> datas) throws Exception {
		setEvaluationDao.updatePj(datas);
	}

	public List<ValueRecord> getPj(String deptCode,String deptHall) throws Exception {
		return setEvaluationDao.getPj(deptCode,deptHall);
	}

	public List<ValueRecord> getValidPj(String deptCode,String deptHall) throws Exception {
		return setEvaluationDao.getValidPj(deptCode,deptHall);
	}

	public List<ValueRecord> getPhotoAll(ValueRecord valueRecord)throws Exception {
		return setEvaluationDao.getPhotoAll(valueRecord);
	}
}
