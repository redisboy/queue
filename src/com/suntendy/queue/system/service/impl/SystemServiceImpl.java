package com.suntendy.queue.system.service.impl;

import java.util.List;
import java.util.Map;

import com.suntendy.queue.system.dao.ISystemDao;
import com.suntendy.queue.system.domain.System;
import com.suntendy.queue.system.service.ISystemService;
import com.suntendy.queue.util.cache.CacheManager;

public class SystemServiceImpl implements ISystemService {
	private ISystemDao systemDao;

	public void setSystemDao(ISystemDao systemDao) {
		this.systemDao = systemDao;
	}

	public List<System> getAllSystem(String deptCode, String deptHall) throws Exception {
		return systemDao.getAllSystem(deptCode, deptHall);
	}

	public void batchUpdate(List<System> datas) throws Exception {
		systemDao.batchUpdate(datas);
		String deptCode = datas.get(0).getDeptCode();
		String deptHall = datas.get(0).getDeptHall();
		CacheManager cacheManager = CacheManager.getInstance();
		String localDeptCode = cacheManager.getOfDeptCache("deptCode");
		String localDeptHall = cacheManager.getOfDeptCache("deptHall");
		if (deptCode.equals(localDeptCode)&&deptHall.equals(localDeptHall)) {
			List<System> dataList = getAllSystem(deptCode, deptHall);
			if (null != dataList && !dataList.isEmpty()) {
				for (System sys : dataList) {
					cacheManager.addOfSystemConfig(sys.getName(), sys.getContent());
				}
			}
		}
	}

	public void updateClearCache(Map<String, String> map) throws Exception {
		systemDao.updateClearCache(map);
		
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<System> dataList = getAllSystem(deptCode, deptHall);
		if (null != dataList && !dataList.isEmpty()) {
			for (System sys : dataList) {
				cacheManager.addOfSystemConfig(sys.getName(), sys.getContent());
			}
		}
	}
}