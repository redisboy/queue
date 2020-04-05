package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.IAgentWaitCountDao;
import com.suntendy.queue.count.domain.AgentWaitCount;
import com.suntendy.queue.count.service.IAgentWaitCountService;

public class AgentWaitCountServiceImpl implements IAgentWaitCountService {
	
	private IAgentWaitCountDao agentDao;
	
	public IAgentWaitCountDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentWaitCountDao agentDao) {
		this.agentDao = agentDao;
	}

	public List<AgentWaitCount> agentCount(String ksrq,String jsrq,String tjms,String deptCode, String deptHall){
		List<AgentWaitCount> list=null;
		try {
			list=agentDao.agentCount(ksrq,jsrq,tjms, deptCode, deptHall);
		} catch (Exception e) {
		}
		return list;
	}
}