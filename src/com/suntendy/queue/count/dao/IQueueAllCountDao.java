package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.QueueAllCount;
import com.suntendy.queue.count.domain.QueueWaitCount;

public interface IQueueAllCountDao {
	
	/*
	 * 统计所有
	 */
	List<QueueAllCount> queueAllCount(QueueAllCount queueAllCount)throws Exception;
}