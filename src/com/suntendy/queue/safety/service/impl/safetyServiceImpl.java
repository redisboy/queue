package com.suntendy.queue.safety.service.impl;

import java.util.List;

import com.suntendy.queue.safety.dao.SafetyDao;
import com.suntendy.queue.safety.domain.Safety;
import com.suntendy.queue.safety.service.ISafetyService;

public class safetyServiceImpl implements ISafetyService{
	private SafetyDao safeDao;
	
	public SafetyDao getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(SafetyDao safeDao) {
		this.safeDao = safeDao;
	}

	@Override
	public String addSafety(Safety safety) throws Exception {
		return safeDao.addSafe(safety);
	}

	@Override
	public List<Safety> searchSafety() throws Exception {
		return safeDao.searchSafe();
	}

	@Override
	public int updateSafety(Safety safety) throws Exception {
		return safeDao.updateSafe(safety);
	}

	@Override
	public int delLogin(String logSum) throws Exception {
		return safeDao.delLogin(logSum);
	}

}
