package com.suntendy.queue.queue.dao;


public interface ICallOutDao {

	/**
	 * 设置评价卡号状态
	 * @param yhdh
	 * @return
	 * @throws Exception
	 */
	public int updateCallOutStatus(String yhdh) throws Exception;
}
