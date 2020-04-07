package com.suntendy.queue.evaluation.dao;


import java.util.List;

import com.suntendy.queue.evaluation.domain.ValueRecord;


public interface ISetEvaluationDao {
	
	/**
	 * 添加评价值
	 * @param datas
	 * @throws Exception
	 */
	public void add(List<ValueRecord> datas) throws Exception;
	
	/**
	 * 修改评价值
	 * @throws Exception 
	 */
	public void updatePj(List<ValueRecord> datas) throws Exception;

	/**
	 * 获取评价值
	 * @throws Exception 
	 */
	public List<ValueRecord> getPj(String deptCode,String deptHall)throws Exception;

	/**
	 * 获取有效评价值
	 * @throws Exception 
	 */
	public List<ValueRecord> getValidPj(String deptCode,String deptHall)throws Exception;
	/**
	 * 查询摄像头及手写板图片
	 */
	public abstract List<ValueRecord> getPhotoAll(ValueRecord valueRecord)throws Exception;

}
