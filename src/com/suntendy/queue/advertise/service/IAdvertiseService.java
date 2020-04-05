package com.suntendy.queue.advertise.service;

import java.util.List;

import com.suntendy.queue.advertise.domain.Advertise;

public interface IAdvertiseService {
	/**
	 * 获取所有的广告信息
	 * @return List
	 */
	public List<Advertise> getAlladvertise(String id, String status,String deptCode,String deptHall);
	/**
	 * 添加广告信息
	 * @return Integer
	 */
	public Integer addAdvertise(Advertise advertiseVo) throws Exception;
	/**
	 * 更改广告信息
	 * @return int
	 */
	public int updateByCode(String id,String message,String msg_state) throws Exception;
	/**
	 * 删除广告信息
	 * @return int
	 */
	public int delAdvertise(String id);
	
	/**
	 * 获取电视广告信息
	 * @param deptCode
	 * @param deptHall
	 * @return
	 * @throws Exception
	 */
	public List<Advertise> getTVadvertise(String deptCode,String deptHall) throws Exception;
	/**
	 *  综合屏查询
	 * @return List
	 */
	public List<Advertise> getComprehensiveScreen(Advertise advertise,String deptCode,String deptHall) throws Exception;
	/**
	 * 添加综合屏信息
	 * @return Integer
	 */
	public Integer addComprehensiveScreen(Advertise advertiseVo) throws Exception;
	/**
	 * 更改综合屏信息
	 * @return int
	 */
	public int updateComprehensiveScreenById(String id,String message,String msg_state) throws Exception;
	/**
	 * 删除综合屏信息
	 * @return int
	 */
	public int delComprehensiveScreen(String id)throws Exception;;
}