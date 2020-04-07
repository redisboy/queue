package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.AgentPdCount;

public interface IAgentPdCountService {
	List<AgentPdCount> agentPdCount(String ksrq,String jsrq,String tjms,String deptCode, String deptHall)throws Exception;
}