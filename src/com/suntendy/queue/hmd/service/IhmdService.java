package com.suntendy.queue.hmd.service;

import java.util.List;

import com.suntendy.queue.hmd.domain.Hmd;
import com.suntendy.queue.hmd.domain.HmdLog;

public interface IhmdService {

	/**
	 * 添加黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public String saveHmd(Hmd hmd) throws Exception;
	
	/**
	 * 查询黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public List<Hmd> queryAllHmd(Hmd hmd) throws Exception;
	
	/**
	 * 根据ID删除黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delHmd(String id) throws Exception;
	
	/**
	 * 根据ID查询黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hmd queryById(String id) throws Exception;
	
	/**
	 * 增加黑名单记录
	 * @param hlog
	 * @return
	 * @throws Exception
	 */
	public String saveHmdLog(HmdLog hlog) throws Exception;
	/**
	 * 批量添加黑名单
	 * @param listHmd
	 * @throws Exception
	 */
	public void addAllHmd(List<Hmd> listHmd) throws Exception;
	/**
	 * 根据身份证查询其当月所取号的次数
	 * @param listHmd
	 * @throws Exception
	 */
	public int queryHmd(String id) throws Exception;
}
