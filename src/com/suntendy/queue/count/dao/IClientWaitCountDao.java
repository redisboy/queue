package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.ClientWaitCount;

public interface IClientWaitCountDao {
	
	/*
	 * 客户等待统计
	 */
	List<ClientWaitCount> clientWaitCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall)throws Exception;
}