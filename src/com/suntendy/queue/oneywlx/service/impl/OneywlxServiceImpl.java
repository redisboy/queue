package com.suntendy.queue.oneywlx.service.impl;

import java.util.List;

import com.suntendy.queue.oneywlx.dao.IOneywlxDao;
import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.oneywlx.service.IOneywlxService;
import com.suntendy.queue.yygn.dao.IYyxzDao;

public class OneywlxServiceImpl implements IOneywlxService {

	private  IOneywlxDao oneywlxDao;
	
	public void setOneywlxDao(IOneywlxDao oneywlxDao) {
		this.oneywlxDao = oneywlxDao;
	}

	@Override
	public List<OneYwlx> getOneYwlxList() throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.getOneYwlxList();
	}

	@Override
	public Integer addOneywlx(OneYwlx oneYwlx) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.addOneywlx(oneYwlx);
	}

	@Override
	public Integer updateOneywlx(OneYwlx oneYwlx) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.updateOneywlx(oneYwlx);
	}

	@Override
	public List<OneYwlx> getOneywlxListById(String id) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.getOneywlxListById(id);
	}

	@Override
	public Integer delOneywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.delOneywlx(id);
	}

	@Override
	public List<OneYwlx> getFormTwo(String id) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.getFormTwo(id);
	}

	@Override
	public List<OneYwlx> getOneywlxListByEjywzt() throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.getOneywlxListByEjywzt();
	}

	@Override
	public Integer delAllFormOneTwoywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return oneywlxDao.delAllFormOneTwoywlx(id);
	}


}
