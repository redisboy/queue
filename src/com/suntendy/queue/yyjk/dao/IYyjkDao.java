package com.suntendy.queue.yyjk.dao;

import java.util.List;

import com.suntendy.queue.yyjk.domain.FoShanCLCYXX;
import com.suntendy.queue.yyjk.domain.GuangZhouYYXX;
import com.suntendy.queue.yyjk.domain.NanNingYYXX;
import com.suntendy.queue.yyjk.domain.ZhongShanYYXX;

public interface IYyjkDao {
	/**
	 * 查询南宁预约信息表数据
	 * @param nanNingYYXX 南宁预约信息实体类
	 * @return
	 * @throws Exception
	 */
	public List<NanNingYYXX> findNanNingYYXX(NanNingYYXX nanNingYYXX) throws Exception;
	/**
	 * 取号成功修改南宁预约信息表状态字段
	 * @param nanNingYYXX 南宁预约信息实体类
	 * @throws Exception
	 */
	public void updateNanNingYYXX(NanNingYYXX nanNingYYXX)throws Exception;
	/**
	 * 查询中山预约信息表数据
	 * @param zhongShanYYXX 中山预约信息实体类
	 * @return
	 * @throws Exception
	 */
	public List<ZhongShanYYXX> findZhongShanYYXX(ZhongShanYYXX zhongShanYYXX) throws Exception;
	/**
	 * 取号成功修改中山预约信息表code字段
	 * @param zhongShanYYXX 中山预约信息实体类
	 * @throws Exception
	 */
	public void updateZhongShanYYXX(ZhongShanYYXX zhongShanYYXX)throws Exception;
	/**
	 * 查询广州预约信息表数据
	 * @param GuangZhouYYXX 广州预约信息实体类
	 * @return
	 * @throws Exception
	 */
	public List<GuangZhouYYXX> findGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX) throws Exception;
	/**
	 * 取号成功修改广州预约信息表code字段
	 * @param zhongShanYYXX 广州预约信息实体类
	 * @throws Exception
	 */
	public void updateGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX)throws Exception;
	/**
	 * 添加预约信息到预约信息表
	 * @param guangZhouYYXX
	 * @throws Exception
	 */
	public void addGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX) throws Exception;
	/**
	 * 删除预约信息表内不是当天的数据
	 * @throws Exception
	 */
	public void deleteYYXXWithoutToday() throws Exception;
	
	/**
	 * 通过六合一分发库查询佛山车辆查验信息结果
	 */
	public List<FoShanCLCYXX> selectFoShanCLCYXX(FoShanCLCYXX foShanCLCYXX) throws Exception;
	
}
