package com.suntendy.queue.log.service.impl;

import java.util.List;

import com.suntendy.queue.log.dao.ILogDao;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.log.service.ILogService;

public class LogServiceImpl implements ILogService {

	private ILogDao logDao;
	
	public ILogDao getLogDao() {
		return logDao;
	}
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}
	
	public List<LogVo> searchLog(LogVo logVo) throws Exception{
		return logDao.searchLog(logVo);
		
	}
	/**
	 * 添加日志信息
	 * @return List
	 */
	public void addLogContent(LogVo logVo) throws Exception {
		// TODO Auto-generated method stub
		 logDao.addLogContent(logVo);
	}

	public void addUserLoginLog(LogVo log) throws Exception {
		logDao.addUserLoginLog(log);
	}

	public List<LogVo> getAllWarnCount(LogVo logVo) throws Exception {
		// TODO Auto-generated method stub
		return logDao.getAllWarnCount(logVo);
	}
	
	/**
	 * 查询当前时间
	 */
	public String searchNowDate() throws Exception {
		// TODO Auto-generated method stub
		List<LogVo> logtime = logDao.searchNowDate();
		return logtime.get(0).getNowDate();
		
	}
	

}
