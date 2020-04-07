package com.suntendy.queue.yyywzl.dao;

import java.util.List;

import com.suntendy.queue.yyywzl.domain.Yyywzl;

public interface IYyywzlDao {
	
	/**
	 *   预约业务所需资料信息表中的数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<Yyywzl> getYyywzlList() throws Exception;
	
	/**
	 *   添加数据到预约业务所需资料信息表中
	 * @return         数据
	 * @throws Exception
	 */
	public Integer addYyywzl(Yyywzl yyywzl) throws Exception;
	
	/**
	 *   通过id获取预约业务所需资料信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<Yyywzl> getYyywzlListById(String id) throws Exception;
	
	/**
	 *   修改预约业务所需资料信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer updateYyywzl(Yyywzl yyywzl) throws Exception;
	
	/**
	 *   通过id删除预约业务所需资料信息表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer delYyywzl(String id) throws Exception;
	
}
