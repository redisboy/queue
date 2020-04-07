package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.AgentWaitCount;

public interface IAgentWaitCountService {

	List<AgentWaitCount> agentCount(String ksrq,String jsrq,String tjms,String deptCode, String deptHall) throws Exception;
}