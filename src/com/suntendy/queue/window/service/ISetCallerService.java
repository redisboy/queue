package com.suntendy.queue.window.service;

import java.util.List;

import com.suntendy.queue.window.domain.Caller;

public interface ISetCallerService {
	
	/**
	 * 获取呼叫器ip地址是否被占用
	 * @return
	 */
	public List<Caller> getIP(String ipaddress)throws Exception;

}
