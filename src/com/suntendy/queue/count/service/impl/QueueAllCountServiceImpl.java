package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.IQueueAllCountDao;
import com.suntendy.queue.count.domain.QueueAllCount;
import com.suntendy.queue.count.service.IQueueAllCountService;

public class QueueAllCountServiceImpl implements IQueueAllCountService{
	private IQueueAllCountDao queueAllCountDao;
	
	public IQueueAllCountDao getQueueAllCountDao() {
		return queueAllCountDao;
	}

	public void setQueueAllCountDao(IQueueAllCountDao queueAllCountDao) {
		this.queueAllCountDao = queueAllCountDao;
	}
	
	public List<QueueAllCount> queueAllCount(QueueAllCount queueAllCount) throws Exception {
		List<QueueAllCount> datas = queueAllCountDao.queueAllCount(queueAllCount);
		return datas;
	}

	

}
