package com.suntendy.queue.window.service.impl;

import java.util.List;

import com.suntendy.queue.window.dao.ISetCallerDao;
import com.suntendy.queue.window.domain.Caller;
import com.suntendy.queue.window.service.ISetCallerService;

public class SetCallerServiceImpl implements ISetCallerService{
	
	private ISetCallerDao setCallerDao;

	public void setSetCallerDao(ISetCallerDao setCallerDao) {
		this.setCallerDao = setCallerDao;
	}

	public List<Caller> getIP(String ipaddress) throws Exception {
		return setCallerDao.getIP(ipaddress);
	}
}
