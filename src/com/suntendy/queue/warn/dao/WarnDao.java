package com.suntendy.queue.warn.dao;

import java.util.List;

import com.suntendy.queue.warn.domain.Warn;

public interface WarnDao {
	
	//查询pd_valuerecord数据
	public List<Warn> searchWarn(Warn warn) throws Exception;
	
	//查询警告人员pd_warn_log
	public List<Warn> selectWarn(Warn warn) throws Exception;
	
	//添加警告人员
	public String saveWarn(Warn warn) throws Exception;
	
	//查询间隔时间
	public List<Warn> searchSjTime() throws Exception;
	
	 //查询当前时间	 
	public List<Warn> searchNowTime() throws Exception;
}
