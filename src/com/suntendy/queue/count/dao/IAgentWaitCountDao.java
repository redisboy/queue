package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.AgentWaitCount;

public interface IAgentWaitCountDao {

	List<AgentWaitCount> agentCount(String ksrq,String jsrq,String tjms,String deptCode, String deptHall)throws Exception;
}