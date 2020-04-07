package com.suntendy.queue.agent.services.impl;

import java.util.List;

import com.suntendy.queue.agent.dao.IWorkDao;
import com.suntendy.queue.agent.services.IWorkService;
import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.WorkVO;

public class WorkServicesImpl implements IWorkService {
	private IWorkDao workDao;
	
	public IWorkDao getWorkDao() {
		return workDao;
	}

	public void setWorkDao(IWorkDao workDao) {
		this.workDao = workDao;
	}

	public int delWork(AgentVO agent){
		int a=0;
		try {
			a=workDao.updWork(agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public Integer addWork(WorkVO work){
		Integer a=null;
		try {
			a=workDao.addWork(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<WorkVO> searchWork(WorkVO workVO) throws Exception {
		// TODO Auto-generated method stub
		return workDao.searchWork(workVO);
	}
}