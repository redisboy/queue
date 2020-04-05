package com.suntendy.queue.queue.dao;

import java.util.List;

import com.suntendy.queue.queue.domain.Zhpcallnum;

public interface IZhpcallnumDao {
	
	/**
	 * 添加综合屏叫号信息
	 * @param callnum
	 * @throws Exception
	 */
	public void addZhpcallnum(Zhpcallnum callnum) throws Exception;
	
	/**
	 * 删除综合屏叫号信息
	 * @param id
	 * @throws Exception
	 */
	public void delZhpcallnum(String id) throws Exception;
	
	/**
	 * 查询综合屏叫号信息
	 * @return
	 * @throws Exception
	 */
	public List<Zhpcallnum> queryAllcallnum() throws Exception;
	
}
