package com.suntendy.queue.agent.services;

import java.util.List;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.WorkVO;

public interface IWorkService {
	public List<WorkVO> searchWork(WorkVO workVO) throws Exception;
	public abstract int delWork(AgentVO agent)throws Exception;
	public abstract Integer addWork(WorkVO work)throws Exception;
}
