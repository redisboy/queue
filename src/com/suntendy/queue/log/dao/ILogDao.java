package com.suntendy.queue.log.dao;

import java.util.List;

import com.suntendy.queue.log.domain.LogVo;

public interface ILogDao {

	
	public List<LogVo> searchLog(LogVo logVo) throws Exception ;
	
	public List<LogVo> getAllWarnCount(LogVo logVo) throws Exception;
	
	/**
	 * 查询当前时间
	 * @return
	 * @throws Exception
	 */
	public List<LogVo> searchNowDate() throws Exception;
	/**
	 * 添加日志信息
	 * @return List
	 */
	public void addLogContent(LogVo logVo) throws Exception;
	
	/**
	 * 添加用户登录日志
	 * @param loginlog
	 * @throws Exception
	 */
	public void addUserLoginLog(LogVo log) throws Exception;
}
