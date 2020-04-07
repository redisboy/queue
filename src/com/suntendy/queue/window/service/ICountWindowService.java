package com.suntendy.queue.window.service;

import java.util.List;

import com.suntendy.queue.window.domain.WindowCount;

public interface ICountWindowService {
	/**
	 * 窗口排队统计
	 * @return
	 */
	public List<WindowCount> getWindowCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String barid);
}
