package com.suntendy.queue.queue.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.suntendy.queue.queue.dao.IQueueDao;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.IQueueService;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.util.cache.CacheManager;

/*****************************************************************
* 描述: 队列相关操作业务逻辑接口实现类 <br>
* //////////////////////////////////////////////////////// <br>
* 创建者: 刘东东 <br>
* 创建日期: 2013-09-24 13:55:54 <br>
* 修改者:  <br>
* 修改日期:  <br>
* 修改说明:  <br>
******************************************************************/
public class QueueServiceImpl implements IQueueService {

	private IQueueDao queueDao;

	public void setQueueDao(IQueueDao queueDao) {
		this.queueDao = queueDao;
	}

	public List<Queue> getAllQueue(String deptCode, String deptHall) throws Exception {
		return queueDao.getAllQueue(deptCode, deptHall);
	}

	public List<Queue> getByCode(String id) {
		try {
			return queueDao.getByCode(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateByCode(Queue queue) throws Exception {
		int res = queueDao.updateByCode(queue);

		// 更新内存
		CacheManager.getInstance().addOfQueueCache(queue.getCode(), queue);
		return res;
	}

	public void addQueue(Queue queue) throws Exception {
		queueDao.addQueue(queue);

		// 添加到内存
		queue.setBeginNum("0");
		CacheManager.getInstance().addOfQueueCache(queue.getCode(), queue);
		TreeSet<Number> numberPool = new TreeSet<Number>(new Comparator<Number>() {
			@Override
			public int compare(Number num1, Number num2) {
				return num1.getEnterTime().compareTo(num2.getEnterTime());
			}
		});
		String key = queue.getCode() + queue.getPrenum();
		NumberManager.getInstance().addQueueNumberPool(key, numberPool);
	}

	public int delQueue(String id, String code) throws Exception {
		int res = queueDao.delQueue(id);

		//更新内存
		Queue queue = CacheManager.getInstance().removeQueue(code);
		String key = queue.getCode() + queue.getPrenum();
		NumberManager.getInstance().removeQueueNumberPool(key);
		return res;
	}

	@Override
	public Queue getQueueByCode(String queueCode,String deptCode, String deptHall) throws Exception {
		// TODO Auto-generated method stub
		return queueDao.getQueueByCode(queueCode,deptCode,deptHall);
	}

	@Override
	public Queue queryQueue(String code, String deptcode, String depthall)
			throws Exception {
		return queueDao.queryQueue(code,deptcode,depthall);
	}

}