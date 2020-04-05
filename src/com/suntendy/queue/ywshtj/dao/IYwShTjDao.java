package com.suntendy.queue.ywshtj.dao;

import java.util.List;

import com.suntendy.queue.ywshtj.domain.YwShTj;

public interface IYwShTjDao {
	
	/**
	 * 对业务人员(管理部门)平均办理时间排名统计
	 */
	public List<YwShTj> getEmplooyTransactTimeOrder(YwShTj ywShTj) throws Exception;
	/**
	 * 对业务人员(管理部门)差评排名统计
	 */
	public List<YwShTj> getEmplooyBadReviewOrder(YwShTj ywShTj) throws Exception;
	/**
	 * 对业务人员(管理部门)暂停时间排名统计
	 */
	public List<YwShTj> getEmplooyPauseTimeOrder(YwShTj ywShTj) throws Exception;
	/**
	 * 对管理部门平均等候时间统计
	 */
	public List<YwShTj> getDepartWaitTimeOrder(YwShTj ywShTj) throws Exception;

}