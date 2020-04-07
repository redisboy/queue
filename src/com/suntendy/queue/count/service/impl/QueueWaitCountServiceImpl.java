package com.suntendy.queue.count.service.impl;

import java.util.List;
import com.suntendy.queue.count.dao.IQueueWaitCountDao;
import com.suntendy.queue.count.domain.QueueWaitCount;
import com.suntendy.queue.count.service.IQueueWaitCountService;
import com.suntendy.queue.window.domain.Screen;

public class QueueWaitCountServiceImpl implements IQueueWaitCountService{
	private IQueueWaitCountDao queueWaitCountDao;
	
	public List<QueueWaitCount> queueWaitCount(String barid,String tjms, String ksrq, String jsrq,String deptCode,String deptHall) throws Exception {
		List<QueueWaitCount> datas = queueWaitCountDao.queueWaitCount(barid,tjms, ksrq, jsrq,deptCode,deptHall);
		for (QueueWaitCount detail : datas) {
			if (detail.getAve_waittime().startsWith(".")) {
				detail.setAve_waittime("0" + detail.getAve_waittime());
			}
			if (detail.getAve_worktime().startsWith(".")) {
				detail.setAve_worktime("0" + detail.getAve_worktime());
			}
		}
		return datas;
	}

	public IQueueWaitCountDao getQueueWaitCountDao() {
		return queueWaitCountDao;
	}

	public void setQueueWaitCountDao(IQueueWaitCountDao iQueueWaitCountDao) {
		this.queueWaitCountDao = iQueueWaitCountDao;
	}

	@Override
	public List<QueueWaitCount> queryBar(String deptCode, String deptHall)
			throws Exception {
		List<QueueWaitCount> barid = queueWaitCountDao.queryBar(deptCode, deptHall);
		return barid;
	}
}
