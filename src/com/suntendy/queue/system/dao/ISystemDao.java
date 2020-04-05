package com.suntendy.queue.system.dao;

import java.util.List;
import java.util.Map;

import com.suntendy.queue.system.domain.System;

public interface ISystemDao {
	
	/**
	 * 获取所有系统设置
	 * @param deptCode 部门编号
	 * @param deptHall 大厅编号
	 * @return
	 * @throws Exception
	 */
	public List<System> getAllSystem(String deptCode, String deptHall) throws Exception;

	/**
	 * 更新系统设置
	 * @param datas 批量更新数据 
	 * @throws Exception
	 */
	public void batchUpdate(List<System> datas) throws Exception;
	/**
	 * 更新内存设置
	 * @param dates
	 * @throws Exception
	 */
	public void updateClearCache(Map<String, String> map) throws Exception;
}