package com.suntendy.queue.window.service;

import java.util.List;

import com.suntendy.queue.window.domain.Screen;

public interface ISetWindowService {
	/**
	 * 获取窗口信息详细
	 * @param deptCode 部门编号
	 * @param deptHall 大厅号
	 * @param id 记录编号
	 * @param ip 窗口对应IP
	 * @param type 窗口对应IP 1:查父窗口 2查子窗口 ""查所有
	 * @return
	 */
	public List<Screen> getWindowList(String deptCode, String deptHall,
			String id, String ip, String type,String businessid,String ckip) throws Exception;
	public List<Screen> getckxx(String deptCode, String deptHall,String ckip) throws Exception;

	/**
	 * 更改窗口信息列表
	 * @return
	 */
	public void updateScreenByCode(Screen screen) throws Exception;

	/**
	 * 删除窗口信息列表
	 * @return
	 */
	public void delScreen(String id, String ip) throws Exception;
	/**
	 * 添加窗口信息列表
	 * @return
	 */
	public void addScreen(Screen screen) throws Exception;

	/**
	 * 获取父窗口的address
	 * @return
	 */
	public List<Screen> getAddress()throws Exception;
	
	/**
	 *查询窗口和状态
	 * @param null
	 * @return
	 */
	public List<Screen> getAddressAndStatuss() throws Exception;
	/**
	 *查询当天取号情况
	 * @param null
	 * @return
	 */
	public List<Screen> getCountShul(String deptCode,String deptHall) throws Exception;
	
	/**
	 *查询窗口
	 * @param null
	 * @return
	 */
	public List<Screen> searchBar(Screen screen) throws Exception;
	/**
	 * 查询每个窗口的业务量
	 * @param deptHall 
	 * @param deptCode 
	 * @return
	 */
	public List<Screen> queryEveryScreenYWL(String deptCode, String deptHall);
	/**
	 * 根据ip查询窗口
	 */
	public List<Screen> querybarid(Screen screen) throws Exception;
	
	public int updateCallerByIp(Screen screen) throws Exception;
}
