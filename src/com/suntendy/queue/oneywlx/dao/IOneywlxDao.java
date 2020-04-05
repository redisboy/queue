package com.suntendy.queue.oneywlx.dao;

import java.util.List;

import com.suntendy.queue.oneywlx.domain.OneYwlx;



public interface IOneywlxDao {

	/**
	 *   插入数据到一级业务类型表中
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addOneywlx(OneYwlx oneYwlx) throws Exception;
	
	/**
	 *   修改一级业务类型表中数据
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer updateOneywlx(OneYwlx oneYwlx) throws Exception;
	
	
	/**
	 *   查询一级业务类型表中的数据
	 * @param oneYwlx  字段对象
	 * @return         数据
	 * @throws Exception
	 */
	public List<OneYwlx> getOneYwlxList() throws Exception;
	
	
	/**
	 *   通过id获取一级业务类型表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<OneYwlx> getOneywlxListById(String id) throws Exception;
	

	/**
	 *   通过id删除一级业务类型表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer delOneywlx(String id) throws Exception;
	
	
	
	/**
	 *   获取一级业务类型表中所有包含二级业务的数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<OneYwlx> getOneywlxListByEjywzt() throws Exception;
	
	
	/**
	 *   方法用于预约二级业务模块中 
	 * @return         数据
	 * @throws Exception
	 */
	public List<OneYwlx> getFormTwo(String id) throws Exception;

	/**
	 *   通过一级业务类型id删除二级业务类型表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer delAllFormOneTwoywlx(String id) throws Exception;
	
}
