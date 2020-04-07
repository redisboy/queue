package com.suntendy.queue.safety.dao;

import java.util.List;

import com.suntendy.queue.safety.domain.Safety;

public interface SafetyDao {
	
	/**
	 * 添加安全策略设置
	 * @return 
	 */
	public String addSafe(Safety safety) throws Exception;
	
	/*
	 * 查询安全策略设置
	 */
	public List<Safety> searchSafe()throws Exception;
	
	/*
	 *修改安全策略设置 
	 */
	public int updateSafe(Safety safety)throws Exception;
	
	public int delLogin(String logSum) throws Exception;
}
