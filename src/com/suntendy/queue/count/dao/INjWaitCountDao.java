package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.AgentNjCount;

public interface INjWaitCountDao {
	List<AgentNjCount> njCount(String idcard,String name,String ksrq, String jsrq, String tjms,String deptCode, String deptHall)throws Exception;
}