package com.suntendy.queue.yydt.service;

import java.util.List;

import com.suntendy.queue.yydt.domain.Yydt;

public interface IYydtService {
		
	/**
	 *  预约大厅信息表中的数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<Yydt> getYydtList() throws Exception;
	
	/**
	 *   添加数据到预约大厅信息表中
	 * @return         数据
	 * @throws Exception
	 */
	public Integer addYydt(Yydt yydt) throws Exception;
	
	/**
	 *   通过id获取预约大厅信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<Yydt> getYydtListById(String id) throws Exception;
	
	/**
	 *   修改预约大厅信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer updateYydt(Yydt yydt) throws Exception;
	
	/**
	 *   通过id删除预约大厅信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer delYydt(String id) throws Exception;
	
}
