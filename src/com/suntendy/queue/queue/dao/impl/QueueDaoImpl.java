package com.suntendy.queue.queue.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.queue.dao.IQueueDao;
import com.suntendy.queue.queue.domain.Queue;

public class QueueDaoImpl extends BaseDao<Queue, Integer> implements IQueueDao {

	public List<Queue> getAllQueue(String deptCode, String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall" };
		String[] propertyValues = { deptCode, deptHall };
		return findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public List<Queue> getByCode(String id) throws Exception {
		String[] properties = { "id" };
		String[] propertyValues = { id };
		return findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public int updateByCode(Queue queue) throws Exception {
		String[] properties = { "name","nextQueueName","nextType" };
		String[] propertyValues = { queue.getName(),queue.getNextQueueName(),queue.getNextType() };
		return updateByMap(Integer.valueOf(queue.getId()), properties, propertyValues, BaseDao.UPDATEBYMAP);
	}

	public void addQueue(Queue queue) throws Exception {
		insert(queue, BaseDao.INSERT);
	}
	
	@Override
	public int delQueue(String id) throws Exception {
		return deleteById(Integer.parseInt(id), BaseDao.DELETEBYID);
	}

	@Override
	public Queue getQueueByCode(String queueCode,String deptCode, String deptHall) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"queueCode", "deptCode", "deptHall" };
		String[] propertyValues = { queueCode, deptCode, deptHall};
		List<Queue> list = findByMap(properties, propertyValues, "", "", "getQueueByCode");
		Queue queues=null;
		if(null != list && list.size()>0){
			queues = list.get(0);
		}
		return queues;
	}

	@Override
	public Queue queryQueue(String code, String deptcode, String depthall)
			throws Exception {
		String[] properties = {"code", "deptCode", "deptHall" };
		String[] propertyValues = { code, deptcode, depthall};
		List<Queue> list = findByMap(properties, propertyValues, "", "", "queryQueue");
		Queue queues=null;
		if(null != list && list.size()>0){
			queues = list.get(0);
		}
		return queues;
	}

}