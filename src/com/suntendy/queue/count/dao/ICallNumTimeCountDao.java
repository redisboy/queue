package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.CallNumTimeCount;

public interface ICallNumTimeCountDao {
	public List<CallNumTimeCount> queryCodeCount(String deptCode,
			String deptHall) throws Exception;
	public CallNumTimeCount queryAllCount(String tjms,
			String ksrq, String jsrq,String code,String deptCode,String deptHall) throws Exception;
}
