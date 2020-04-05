package com.suntendy.queue.twoywlx.dao;

import java.util.List;

import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.twoywlx.domain.Twoywlx;

public interface ITwoywlxDao {

	/**
	 *   插入数据到二级业务类型表中
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addTwoywlx(Twoywlx twoywlx) throws Exception;
	
	/**
	 *   修改二级业务类型表中数据
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer updateTwoywlx(Twoywlx twoywlx) throws Exception;
	
	
	/**
	 *   查询二级业务类型表中的数据
	 * @param oneYwlx  字段对象
	 * @return         数据
	 * @throws Exception
	 */
	public List<Twoywlx> getTwoywlxList() throws Exception;
	
	
	/**
	 *   通过id获取二级业务类型表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public List<Twoywlx> getTwoywlxListById(String id) throws Exception;
	
	
	
	

	/**
	 *   通过id删除二级业务类型表中数据
	 * @return         数据
	 * @throws Exception
	 */
	public Integer delTwoywlx(String id) throws Exception;
	
	

	
}
