package com.suntendy.queue.advertise.service.impl;

import java.util.List;

import com.suntendy.queue.advertise.dao.IShowQuHaoSumDao;
import com.suntendy.queue.advertise.domain.QuHaoMsg;
import com.suntendy.queue.advertise.service.IShowQuHaoSumService;


public class ShowQuHaoSumServiceImpl implements IShowQuHaoSumService {
	private IShowQuHaoSumDao showQuHaoSumDao;

	public IShowQuHaoSumDao getShowQuHaoSumDao() {
		return showQuHaoSumDao;
	}

	public void setShowQuHaoSumDao(IShowQuHaoSumDao showQuHaoSumDao) {
		this.showQuHaoSumDao = showQuHaoSumDao;
	}

	/**
	 * 添加取号数量显示信息列表
	 */
	public Integer addSHowQuHaoContent(QuHaoMsg qhVo) throws Exception {
		// TODO Auto-generated method stub
		return showQuHaoSumDao.addSHowQuHaoContent(qhVo);
	}

	/**
	 * 更改取号数量显示信息列表
	 */
	public int updageSHowQuHaoContent(QuHaoMsg qhVo) throws Exception {
		// TODO Auto-generated method stub
		return showQuHaoSumDao.updageSHowQuHaoContent(qhVo);
	}

	/**
	 * 获取所有的信息
	 * @return List
	 */
	public List<QuHaoMsg> getAllQuHaoMsg(QuHaoMsg qhVo) throws Exception {
		// TODO Auto-generated method stub
		return showQuHaoSumDao.getAllQuHaoMsg(qhVo);
	}
	
	

}
