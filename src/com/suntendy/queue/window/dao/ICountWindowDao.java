package com.suntendy.queue.window.dao;

import java.util.List;

import com.suntendy.queue.window.domain.WindowCount;

public interface ICountWindowDao {

	/*
	 * 窗口排队统计
	 */
	public abstract List<WindowCount> getWindowCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String barid)throws Exception;
}
