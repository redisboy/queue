package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.ICallNumTimeCountDao;
import com.suntendy.queue.count.domain.CallNumTimeCount;
import com.suntendy.queue.count.service.ICallNumTimeCountService;

public class CallNumTimeCountServiceImpl implements ICallNumTimeCountService{
	private ICallNumTimeCountDao callNumTimeDao;
	
	@Override
	public List<CallNumTimeCount> queryCodeCount(String deptCode,
			String deptHall) throws Exception {
		return callNumTimeDao.queryCodeCount(deptCode, deptHall);
	}
	
	@Override
	public CallNumTimeCount queryAllCount(String tjms, String ksrq,
			String jsrq, String code, String deptCode, String deptHall)
			throws Exception {
		return callNumTimeDao.queryAllCount(tjms, ksrq, jsrq, code, deptCode, deptHall);
	}
	
	public ICallNumTimeCountDao getCallNumTimeDao() {
		return callNumTimeDao;
	}
	public void setCallNumTimeDao(ICallNumTimeCountDao callNumTimeDao) {
		this.callNumTimeDao = callNumTimeDao;
	}
}
