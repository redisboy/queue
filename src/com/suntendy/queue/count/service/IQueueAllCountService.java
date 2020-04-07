package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.QueueAllCount;

public interface IQueueAllCountService {

	/*
	 * 统计所有
	 */
	List<QueueAllCount> queueAllCount(QueueAllCount queueAllCount)throws Exception;
}