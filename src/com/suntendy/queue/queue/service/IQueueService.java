package com.suntendy.queue.queue.service;

import java.util.List;

import com.suntendy.queue.queue.domain.Queue;

/*****************************************************************
* 描述: 队列相关操作业务逻辑接口 <br>
* //////////////////////////////////////////////////////// <br>
* 创建者: 刘东东 <br>
* 创建日期: 2013-09-24 13:55:24 <br>
* 修改者:  <br>
* 修改日期:  <br>
* 修改说明:  <br>
******************************************************************/
public interface IQueueService {

	/**
	 * 获取所有的队列信息
	 * @param deptCode 部门编号
	 * @param deptHall 大厅编号
	 * @return
	 * @throws Exception
	 */
	public List<Queue> getAllQueue(String deptCode, String deptHall) throws Exception;
	
	/**
	 * 修改队列时往页面传值
	 * @return List
	 */
	public List<Queue> getByCode(String code); 
	
	/**
	 * 修改队列
	 * @return int
	 */
	public int updateByCode(Queue queue) throws Exception;
	
	/**
	 * 添加队列
	 * @return int
	 */
	public void addQueue(Queue queue) throws Exception;
	
	public int delQueue(String id, String code) throws Exception;
	
	/**
	 * 是否存在上一级
	 */
	public Queue getQueueByCode(String queueCode,String deptCode, String deptHall) throws Exception;
	
	/**
	 * 删除队列时查该队列是否存在业务
	 */
	public Queue queryQueue(String code,String deptcode,String depthall) throws Exception;
}