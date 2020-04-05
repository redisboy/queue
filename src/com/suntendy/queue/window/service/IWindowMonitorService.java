package com.suntendy.queue.window.service;

import java.util.List;

import com.suntendy.queue.window.domain.WindowMonitor;

public interface IWindowMonitorService {
	/**
	 * 窗口监控统计
	 * @return
	 */
	public List<WindowMonitor> getWindowMonitor(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String barid,String xm) throws Exception;
}
