package com.suntendy.queue.hmd.dao;

import com.suntendy.queue.hmd.domain.HmdLog;

public interface IHmdLogDao {
	/**
	 * 增加黑名单记录
	 * @param hlog
	 * @return
	 * @throws Exception
	 */
	public String saveHmdLog(HmdLog hlog) throws Exception;
}
