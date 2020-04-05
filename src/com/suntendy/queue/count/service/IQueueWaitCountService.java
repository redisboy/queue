package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.QueueWaitCount;
import com.suntendy.queue.window.domain.Screen;

public interface IQueueWaitCountService {

	/*
	 * 队列排队统计
	 */
	List<QueueWaitCount> queueWaitCount(String barid,String tjms, String ksrq, String jsrq,String deptCode,String deptHall)throws Exception;
	/*
	 * 获取所有窗口号
	 */
	List<QueueWaitCount> queryBar(String deptCode,String deptHall)throws Exception;

}