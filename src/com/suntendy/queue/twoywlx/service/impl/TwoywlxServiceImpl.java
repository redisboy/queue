package com.suntendy.queue.twoywlx.service.impl;

import java.util.List;

import com.suntendy.queue.twoywlx.dao.ITwoywlxDao;
import com.suntendy.queue.twoywlx.domain.Twoywlx;
import com.suntendy.queue.twoywlx.service.ITwoywlxService;

public class TwoywlxServiceImpl	implements ITwoywlxService {
	
	private ITwoywlxDao twoywlxDao;
	
	public void setTwoywlxDao(ITwoywlxDao twoywlxDao) {
		this.twoywlxDao = twoywlxDao;
	}

	@Override
	public Integer addTwoywlx(Twoywlx twoywlx) throws Exception {
		// TODO Auto-generated method stub
		return twoywlxDao.addTwoywlx(twoywlx);
	}

	@Override
	public Integer updateTwoywlx(Twoywlx twoywlx) throws Exception {
		// TODO Auto-generated method stub
		return twoywlxDao.updateTwoywlx(twoywlx);
	}

	@Override
	public List<Twoywlx> getTwoywlxList() throws Exception {
		// TODO Auto-generated method stub
		return twoywlxDao.getTwoywlxList();
	}

	@Override
	public List<Twoywlx> getTwoywlxListById(String id) throws Exception {
		// TODO Auto-generated method stub
		return twoywlxDao.getTwoywlxListById(id);
	}

	@Override
	public Integer delTwoywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return twoywlxDao.delTwoywlx(id);
	}



}
