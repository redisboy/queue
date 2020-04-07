package com.suntendy.queue.count.service;

import java.util.List;

import com.suntendy.queue.count.domain.EmployeeWaitCount;

public interface IEmployeeWaitCountService {
    
	/*
	 * 员工排队统计
	 */
	List<EmployeeWaitCount> employeeWaitCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall)throws Exception;
	
	
	/**
	 * 员工叫号量、工作量、满意量评价
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeWaitCount> employeeCount() throws Exception;
}