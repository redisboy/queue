package com.suntendy.queue.window.dao;

import java.util.List;

import com.suntendy.queue.window.domain.WindowMonitor;

public interface IWindowMonitorDao {

	/*
	 * 窗口监控统计
	 */
	public  List<WindowMonitor> getWindowMonitor(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String barid,String xm)throws Exception;
}
