package com.suntendy.queue.statistics.dao;

import java.util.List;

import com.suntendy.queue.statistics.domain.HandlerLCount;
import com.suntendy.queue.statistics.domain.SaveLCount;
import com.suntendy.queue.statistics.domain.UserLCount;


public interface IStatisticsLogDao {
	/**
	 * 查询用户登录统计
	 * @throws Exception
	 */
	public List queryLoginCount(String tjms,String ksrq, String jsrq,UserLCount userLCount)throws Exception;
	
	
	/**
	 * 查询操作日志统计
	 * @throws Exception
	 */
	public List queryHandlerLCount(String tjms,String ksrq, String jsrq,HandlerLCount handlerLCount)throws Exception;
	
	
	
	
	
	/**
	 * 查询安全日志统计
	 * @throws Exception
	 */
	public List querySaveLCount(String tjms,String ksrq, String jsrq,SaveLCount saveLCount)throws Exception;


}
