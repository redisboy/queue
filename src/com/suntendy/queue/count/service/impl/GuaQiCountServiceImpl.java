package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.IGuaQiCountDao;
import com.suntendy.queue.count.domain.GuaQiCount;
import com.suntendy.queue.count.service.IGuaQiCountService;

public class GuaQiCountServiceImpl implements IGuaQiCountService {
	
	private IGuaQiCountDao guaQiCountDao;
	
	
	public IGuaQiCountDao getGuaQiCountDao() {
		return guaQiCountDao;
	}
	public void setGuaQiCountDao(IGuaQiCountDao guaQiCountDao) {
		this.guaQiCountDao = guaQiCountDao;
	}

	@Override
	public List<GuaQiCount> gqInfoCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall)
			throws Exception {
		return guaQiCountDao.gqInfoCount(tjms, ksrq, jsrq,deptCode,deptHall);
	}

}
