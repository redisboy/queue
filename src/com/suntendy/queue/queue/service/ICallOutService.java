package com.suntendy.queue.queue.service;

import java.util.List;

import com.suntendy.queue.queue.domain.Number;


public interface ICallOutService {

	/**
	 * 设置评价卡号状态
	 * @param yhdh
	 * @return
	 * @throws Exception
	 */
	public int updateCallOutStatus(String yhdh) throws Exception;
	/**
	 * 异常业务处理
	 * @param yhdh
	 * @return
	 * @throws Exception
	 */
	public String exceptionHanDling(Number searchNumber) throws Exception;
	/**
	 * 根据号码查询6合1数据库最新记录
	 * @param operNum
	 * @return
	 * @throws Exception
	 */
	public List<Number> query6he1Callout(String serialNum) throws Exception;
	/**
	 * 根据号码查询6合1数据库最新记录
	 * @param operNum
	 * @return
	 * @throws Exception
	 */
	public List<Number> query6he1Valuerecord(String serialNum) throws Exception;
	/**
	 * 根据号码更改本地valuerecord状态
	 * @param num
	 * @param serialNum
	 * @return
	 * @throws Exception
	 */
	public int updateValuerecordStatus(String num,String serialNum) throws Exception;
	/**
	 * 根据号码查询窗口
	 * @param number
	 * @return
	 */
	public List<Number> getBarIdBySerialNum(Number number) throws Exception;
	
	public List<Number> getValueRecordAllById(Number number) throws Exception;
	
	public List<Number> getvaluerecordByClientno(Number number) throws Exception;
}
