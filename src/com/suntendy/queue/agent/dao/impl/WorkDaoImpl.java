package com.suntendy.queue.agent.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.agent.dao.IWorkDao;
import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.WorkVO;
import com.suntendy.queue.base.BaseDao;

public class WorkDaoImpl extends BaseDao<WorkVO, Integer> implements IWorkDao {
	/**
	 * 根据ID修改work表对应数据
	 */
	public Integer updWork(AgentVO agent) throws Exception {
		String[] properties = { "agent_workl_status", "modified_date","workid","agentid","stroke" };
		Object[] propertyValues = { agent.getStatus(), agent.getModified_date(),agent.getWorkid(),agent.getId(),agent.getYwbs()};
		//String[] properties = { "agent_workl_status", "modified_date","agentid","stroke"};
		//Object[] propertyValues = { agent.getStatus(), agent.getModified_date(),agent.getId(),agent.getStroke()};
		return this.updateByMap(agent.getId(), properties, propertyValues, BaseDao.UPDATEBYMAP);
	}

	/**
	 * 添加work表数据
	 */
	public Integer addWork(WorkVO work) throws Exception {
		return this.insert(work, BaseDao.INSERT);
	}
	
	public void addWork(List<WorkVO> datas) throws Exception {
		this.batchInsert(datas, BaseDao.INSERT);
	}

	/**
	 * 查询代理人对应的业务笔数
	 */
	public List<WorkVO> searchWork(WorkVO workVO) throws Exception {
		String[] properties = { "agent_id", "work_id"};
		Object[] propertyValues = { workVO.getAgent_id(), workVO.getWork_id()};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}
	/**
	 * 删除代理人相应的业务
	 */
	public void deleteWork(String agentId)throws Exception{
		 this.getSqlMapClientTemplate().delete("WorkVO.deleteWork", agentId);
	}
}