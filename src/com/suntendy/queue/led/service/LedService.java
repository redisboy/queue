package com.suntendy.queue.led.service;

import java.util.List;

import com.suntendy.queue.led.domain.LED;


public interface LedService {
	/**
	 * 获取LED设置信息
	 * @return
	 * @throws Exception
	 */
	public List<LED> getLedInfo(String deptCode, String deptHall) throws Exception;
	
	/**
	 * 添加LED设置
	 * @param led
	 * @throws Exception
	 */
	public void add(LED led) throws Exception;
	
	/**
	 * 更改LED设置
	 * @return
	 * @throws Exception
	 */
	public void updateLED(LED ledVo)throws Exception;
	/**
	 * 获取LED队列屏设置
	 * @return
	 * @throws Exception
	 */
	public List<LED> getLedInfo_TV(LED led) throws Exception;
	
	/**
	 * 添加LED队列屏设置
	 * @param led
	 * @throws Exception
	 */
	public void addLED_TV(LED led) throws Exception;
	
	/**
	 * 更改LED队列屏设置
	 * @return
	 * @throws Exception
	 */
	public void updateLED_TV(LED ledVo)throws Exception;
	
	/**
	 * 删除LED队列屏设置
	 * @param ledVo
	 * @return
	 * @throws Exception
	 */
	public Integer delLED_TV(LED ledVo)throws Exception;
	
	/**
	 * LED屏内容设置
	 * @return
	 * @throws Exception
	 */
	public List<LED> getLED_Content(LED ledVo)throws Exception;
	
	/**
	 * LED屏内容添加
	 * @throws Exception
	 */
	public void addLED_Content(LED ledVo) throws Exception;
	
	/**
	 * LED屏内容修改
	 * @param ledVo
	 * @throws Exception
	 */
	public void updateLED_Content(LED ledVo) throws Exception;
	
	/**
	 * LED屏内容删除
	 * @param ledVo
	 * @throws Exception
	 */
	public void delLED_Content(LED ledVo) throws Exception;
	
	/**
	 * 查询最大ID
	 * @param ledVo
	 * @throws Exception
	 */
	public LED getMaxId() throws Exception;

	
}
