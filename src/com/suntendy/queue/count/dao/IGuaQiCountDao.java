package com.suntendy.queue.count.dao;

import java.util.List;

import com.suntendy.queue.count.domain.GuaQiCount;

public interface IGuaQiCountDao {
	/**
	 * 挂起信息统计
	 * @param tjms
	 * @param ksrq
	 * @param jsrq
	 * @return
	 * @throws Exception
	 */
	public List<GuaQiCount> gqInfoCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall) throws Exception;

}
