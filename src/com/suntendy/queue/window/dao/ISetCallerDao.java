package com.suntendy.queue.window.dao;

import java.util.List;

import com.suntendy.queue.window.domain.Caller;

public interface ISetCallerDao {
	
	/**
	 * 查找IP地址是否被占用
	 * @param str
	 * @return
	 */
	public List<Caller> getIP(String ipaddress)throws Exception;

}
