package com.suntendy.queue.count.service.impl;

import java.util.List;

import com.suntendy.queue.count.dao.IAgentPdCountDao;
import com.suntendy.queue.count.domain.AgentPdCount;
import com.suntendy.queue.count.service.IAgentPdCountService;

public class AgentPdCountServiceImpl implements IAgentPdCountService {
	
	private IAgentPdCountDao agentPdCountDao;
	
	public IAgentPdCountDao getAgentPdCountDao() {
		return agentPdCountDao;
	}

	public void setAgentPdCountDao(IAgentPdCountDao agentPdCountDao) {
		this.agentPdCountDao = agentPdCountDao;
	}

	public List<AgentPdCount> agentPdCount(String ksrq, String jsrq, String tjms,String deptCode, String deptHall) {
		List<AgentPdCount> list=null;
		try {
			list=agentPdCountDao.agentPdCount(ksrq, jsrq, tjms, deptCode, deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
