package com.suntendy.queue.advertise.service;

import java.util.List;
import com.suntendy.queue.advertise.domain.QuHaoMsg;

public interface IShowQuHaoSumService {

	/**
	 * 获取所有的信息
	 * @return List
	 */
	public List<QuHaoMsg> getAllQuHaoMsg(QuHaoMsg qhVo) throws Exception;
	/**
	 * 更改取号数量显示信息列表
	 * @return
	 */
	public int updageSHowQuHaoContent(QuHaoMsg qhVo) throws Exception;

	/**
	 * 添加取号数量显示信息列表
	 * @return
	 */
	public Integer addSHowQuHaoContent(QuHaoMsg qhVo) throws Exception;

}
