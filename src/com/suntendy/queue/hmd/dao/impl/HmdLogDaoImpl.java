package com.suntendy.queue.hmd.dao.impl;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.hmd.dao.IHmdLogDao;
import com.suntendy.queue.hmd.domain.HmdLog;

public class HmdLogDaoImpl extends BaseDao<HmdLog,String> implements IHmdLogDao {

	/**
	 * 增加黑名单记录
	 * @param hlog
	 * @return
	 * @throws Exception
	 */
	public String saveHmdLog(HmdLog hlog) throws Exception {
		return this.insert(hlog, "saveHmdLog");
	}

}
