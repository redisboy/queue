package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.CallNumTimeCount;


public interface ICallNumTimeCountService {
	/*
	 * 查询员工工号与姓名
	 */
	List<CallNumTimeCount> queryCodeCount(String deptCode,String deptHall) throws Exception;
	/*
	 * 根据工号查询员工信息与间隔时间
	 */
	public CallNumTimeCount queryAllCount(String tjms, String ksrq,
			String jsrq, String code, String deptCode, String deptHall)
			throws Exception;
}
