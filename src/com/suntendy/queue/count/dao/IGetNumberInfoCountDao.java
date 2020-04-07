package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.GetNumberInfoCount;

public interface IGetNumberInfoCountDao {
	
	/*
	 * 取号信息查询
	 */
	List<GetNumberInfoCount> getNumberInfoCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String xm) throws Exception;
	/**
	 * 取号信息预警
	 * @return
	 * @throws Exception
	 */
	public List<GetNumberInfoCount> getDoubleNumberInfo() throws Exception;
	/**
	 * 取号信息预警详情
	 * @return
	 * @throws Exception
	 */
	public List<GetNumberInfoCount> getDetailDoubleNumber(String idnumber) throws Exception;
}