package com.suntendy.queue.safety.service;

import java.util.List;

import com.suntendy.queue.safety.domain.Safety;

public interface ISafetyService {
	
	/**
	 * 添加安全策略设置
	 * @throws Exception
	 */
	public String addSafety(Safety safety)throws Exception;
	
	/**
	 * 查询安全策略设置
	 * @throws Exception
	 */
	public List<Safety> searchSafety()throws Exception;
	
	/**
	 * 修改安全策略设置
	 * @throws Exception
	 */
	public int updateSafety(Safety safety)throws Exception;
	
	public int delLogin(String logSum) throws Exception;
}
