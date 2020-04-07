package com.suntendy.queue.system.dao.impl;

import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.system.dao.ISystemDao;
import com.suntendy.queue.system.domain.System;

public class SystemDaoImpl extends BaseDao<System, Integer> implements ISystemDao {

	public List<System> getAllSystem(String deptCode, String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall" };
		String[] propertyValues = { deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public void batchUpdate(List<System> datas) throws Exception {
		this.batchUpdate(datas, BaseDao.UPDATE);
	}

	public void updateClearCache(Map<String, String> map) throws Exception {
		String isOpenIndexCamera = map.get("isOpenIndexCamera");
		String autoOrManualCapture = map.get("autoOrManualCapture");
		String deptCode = map.get("deptCode");
		String deptHall = map.get("deptHall");
		try {
			System system = new System();
			system.setContent(isOpenIndexCamera);
			system.setName("isOpenIndexCamera");
			system.setDeptCode(deptCode);
			system.setDeptHall(deptHall);
			this.getSqlMapClientTemplate().update("System.updateClearCache", system);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System system = new System();
			system.setContent(autoOrManualCapture);
			system.setName("autoOrManualCapture");
			system.setDeptCode(deptCode);
			system.setDeptHall(deptHall);
			this.getSqlMapClientTemplate().update("System.updateClearCache", system);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}