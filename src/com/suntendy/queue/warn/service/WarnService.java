package com.suntendy.queue.warn.service;

import java.util.List;

import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.warn.domain.Warn;

public interface WarnService {
	/**
	 * 添加警告人员
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	public String saveWarn(Warn warn) throws Exception;
	
	/**
	 * 从pd_valuerecord查询数据
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	public List<Warn> getWarnList(Warn warn) throws Exception;
	
	/**
	 * 查询警告人员
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	public List<Warn> selectWarn(Warn warn) throws Exception;
	
	/**
	 * 查询间隔时间
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	public String searchJgTime() throws Exception;
	
	/**
	 * 查询当前时间
	 * @param warn
	 * @return
	 * @throws Exception
	 */
	public List<Warn> searchNowTime() throws Exception;
}
