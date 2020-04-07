package com.suntendy.queue.queue.dao;

import java.util.List;

import com.suntendy.queue.queue.domain.Queue;

public interface IQueueDao {
	
	/**
	 * 获取所有的队列信息
	 * @param deptCode 部门编号
	 * @param deptHall 大厅编号
	 * @return
	 * @throws Exception
	 */
	public List<Queue> getAllQueue(String deptCode, String deptHall) throws Exception;
	
	/**
	 * 获取要更改的队列信息
	 * @return List 队列信息
	 * @throws Exception 
	 */
	public List<Queue> getByCode(String str) throws Exception;
	
	
	/**
	 * 修改队列
	 * @return String 是否成功
	 * @throws Exception 
	 */
	public int updateByCode(Queue queue) throws Exception;
	
	/**
	 * 添加队列
	 * @return int
	 */
	public void addQueue(Queue queue)throws Exception;
	
	public int delQueue(String id) throws Exception;
	/**
	 * 是否存在上一级
	 */
	public Queue getQueueByCode(String queueCode,String deptCode, String deptHall) throws Exception;
	/**
	 * 删除队列时查该队列是否存在业务
	 */
	public Queue queryQueue(String code,String deptcode,String depthall) throws Exception;
	
}