package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.GetNumberInfoCount;

public interface IGetNumberInforCountService {

	/*
	 * 取号信息查询
	 */
	List<GetNumberInfoCount> GetNumberInforCount(String ksrq,String jsrq,String tjms,String deptCode,String deptHall,String xm) throws Exception;
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