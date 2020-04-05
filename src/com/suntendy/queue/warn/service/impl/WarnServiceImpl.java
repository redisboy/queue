package com.suntendy.queue.warn.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.warn.dao.WarnDao;
import com.suntendy.queue.warn.domain.Warn;
import com.suntendy.queue.warn.service.WarnService;

public class WarnServiceImpl implements WarnService{

	private WarnDao warnDao;

	public void setWarnDao(WarnDao warnDao) {
		this.warnDao = warnDao;
	}

	/**
	 * 添加警告人员
	 */
	@Override
	public String saveWarn(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		return warnDao.saveWarn(warn);
	}
	
	/**
	 * pd_valuerecord查询数据
	 */
	@Override
	public List<Warn> getWarnList(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		return warnDao.searchWarn(warn);
	}

	/**
	 * 查询间隔时间
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	@Override
	public String searchJgTime() throws Exception {
		// TODO Auto-generated method stub
		List<Warn> jgtime = warnDao.searchSjTime();
		return jgtime.get(0).getJgsj();
	}

	/**
	 * 查询警告人员
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Warn> selectWarn(Warn warn) throws Exception {
		// TODO Auto-generated method stub
		return warnDao.selectWarn(warn);
	}
	
	/**
	 * 查询当前时间
	 * 
	 */
	@Override
	public List<Warn> searchNowTime() throws Exception {
		// TODO Auto-generated method stub
		List<Warn> nowTime = warnDao.searchNowTime();
		return nowTime;
		}
}
