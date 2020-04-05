package com.suntendy.queue.count.service.impl;

import java.util.List;
import com.suntendy.queue.count.dao.IGetNumberInfoCountDao;
import com.suntendy.queue.count.domain.GetNumberInfoCount;
import com.suntendy.queue.count.service.IGetNumberInforCountService;

public class GetNumberInfoCountServiceImpl implements IGetNumberInforCountService {
	
	private IGetNumberInfoCountDao getNumberInfoCountDao;

	public List<GetNumberInfoCount> GetNumberInforCount(String ksrq, String jsrq, String tjms,String deptCode,String deptHall,String xm)
			throws Exception {
		return getNumberInfoCountDao.getNumberInfoCount(tjms, ksrq, jsrq, deptCode, deptHall,xm);
	}

	public IGetNumberInfoCountDao getGetNumberInfoCountDao() {
		return getNumberInfoCountDao;
	}

	public void setGetNumberInfoCountDao(
			IGetNumberInfoCountDao getNumberInfoCountDao) {
		this.getNumberInfoCountDao = getNumberInfoCountDao;
	}

	/**
	 * 取号信息预警
	 */
	public List<GetNumberInfoCount> getDoubleNumberInfo() throws Exception {
		return getNumberInfoCountDao.getDoubleNumberInfo();
	}

	/**
	 * 取号信息预警详情
	 */
	public List<GetNumberInfoCount> getDetailDoubleNumber(String idnumber)
			throws Exception {
		return getNumberInfoCountDao.getDetailDoubleNumber(idnumber);
	}
}
