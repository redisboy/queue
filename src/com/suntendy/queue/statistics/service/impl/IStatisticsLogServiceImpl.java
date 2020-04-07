package com.suntendy.queue.statistics.service.impl;

import java.util.List;

import com.suntendy.queue.statistics.dao.IStatisticsLogDao;
import com.suntendy.queue.statistics.domain.HandlerLCount;
import com.suntendy.queue.statistics.domain.SaveLCount;
import com.suntendy.queue.statistics.domain.UserLCount;
import com.suntendy.queue.statistics.service.IStatisticsLogService;
import com.suntendy.queue.systemlog.dao.ISystemLogDao;

public class IStatisticsLogServiceImpl implements IStatisticsLogService {
	
	private IStatisticsLogDao statisticsLogDao;
	
	public void setStatisticsLogDao(IStatisticsLogDao statisticsLogDao) {
		this.statisticsLogDao = statisticsLogDao;
	}

	@Override
	public List queryLoginCount(String tjms, String ksrq,
			String jsrq, UserLCount userLCount) throws Exception {
		
		return statisticsLogDao.queryLoginCount(tjms, ksrq, jsrq, userLCount);
	}

	@Override
	public List queryHandlerLCount(String tjms, String ksrq,
			String jsrq, HandlerLCount handlerLCount) throws Exception {
		return statisticsLogDao.queryHandlerLCount(tjms, ksrq, jsrq, handlerLCount); 
	}

	@Override
	public List querySaveLCount(String tjms, String ksrq,
			String jsrq, SaveLCount saveLCount) throws Exception {
		
		return statisticsLogDao.querySaveLCount(tjms, ksrq, jsrq, saveLCount);
	}

}
