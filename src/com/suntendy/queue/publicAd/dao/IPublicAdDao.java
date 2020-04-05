package com.suntendy.queue.publicAd.dao;

import java.util.List;

import com.suntendy.queue.publicAd.domain.PublicAd;

public interface IPublicAdDao {
	/**
	 * 获取所有的宣传材料
	 * @return List
	 */
	public  List<PublicAd> getPublicAd(String id, String status,String deptCode,String deptHall)throws Exception;

	/**
	 * 添加宣传材料
	 * @return Integer
	 */
	public Integer addPublicAd(PublicAd publicAd )throws Exception;
	
	/**
	 * 更改宣传材料
	 * @return int
	 */
	public  int updateByCode(String id,String message,String msg_state) throws Exception;

	/**
	 * 删除宣传材料
	 * @return int
	 */
	public int delPublicAd(String id)throws Exception;
	/**
	 * 更改宣传材料的启用状态
	 * @return int
	 */
	public  void updateByCode(PublicAd publicAd) throws Exception;
	/**
	 * 把所有operator=0的都设置为不启用
	 * @return int
	 */
	public void updateAdAllUsing() throws Exception;
}
