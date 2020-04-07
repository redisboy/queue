package com.suntendy.queue.yygn.dao;

import java.util.List;

import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.yygn.domain.Yyxz;

public interface IYyxzDao {

	/**
	 *   插入数据到预约须知表中
	 * @param yyxz  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addYyxz(Yyxz yyxz) throws Exception;
	
	/**
	 *   修改数据到预约须知表中
	 * @param yyxz  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer updateYyxz(Yyxz yyxz) throws Exception;
	
	/**
	 *   查询预约须知表
	 * @param yyxz  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public List<Yyxz> getYyxzList() throws Exception;
	
	
}
