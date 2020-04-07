package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.INjWaitCountDao;
import com.suntendy.queue.count.domain.AgentNjCount;
import com.suntendy.queue.count.service.INjWaitCountService;

public class NjWaitCountServiceImpl implements INjWaitCountService {
	private INjWaitCountDao njDao;
	
	public INjWaitCountDao getNjDao() {
		return njDao;
	}

	public void setNjDao(INjWaitCountDao njDao) {
		this.njDao = njDao;
	}

	public List<AgentNjCount> njCount(String idcard,String name,String ksrq, String jsrq, String tjms,String deptCode, String deptHall) {
		List<AgentNjCount> list=null;
		try {
			list=njDao.njCount(idcard,name,ksrq, jsrq, tjms, deptCode, deptHall);
		} catch (Exception e) {
		}
		return list;
	}
}
