package com.suntendy.queue.agent.dao;

import java.util.List;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.WorkVO;

public interface IWorkDao {
	public List<WorkVO> searchWork(WorkVO workVO) throws Exception;
	public Integer updWork(AgentVO agent) throws Exception;
	public Integer addWork(WorkVO work) throws Exception;
	public void addWork(List<WorkVO> datas) throws Exception;
	public void deleteWork(String agentId)throws Exception;
}
