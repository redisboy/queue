package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.IClientWaitCountDao;
import com.suntendy.queue.count.domain.ClientWaitCount;
import com.suntendy.queue.count.service.IClientWaitCountService;

public class ClientWaitCountServiceImpl implements IClientWaitCountService{
	
	private IClientWaitCountDao clientWaitCountDao;

	public List<ClientWaitCount> clientWaitCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall) throws Exception {
		return clientWaitCountDao.clientWaitCount( tjms,  ksrq,  jsrq, deptCode, deptHall);
	}

	public IClientWaitCountDao getClientWaitCountDao() {
		return clientWaitCountDao;
	}

	public void setClientWaitCountDao(IClientWaitCountDao iClientWaitCountDao) {
		this.clientWaitCountDao = iClientWaitCountDao;
	}
}
