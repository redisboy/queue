package com.suntendy.queue.queue.service.impl;

import java.util.List;

import com.suntendy.queue.queue.dao.IZhpcallnumDao;
import com.suntendy.queue.queue.domain.Zhpcallnum;
import com.suntendy.queue.queue.service.IZhpcallnumService;

public class ZhpcallnumServiceImpl implements IZhpcallnumService {
	
	private IZhpcallnumDao zhpcallnumDao;
	
	public IZhpcallnumDao getZhpcallnumDao() {
		return zhpcallnumDao;
	}
	public void setZhpcallnumDao(IZhpcallnumDao zhpcallnumDao) {
		this.zhpcallnumDao = zhpcallnumDao;
	}

	public void addZhpcallnum(Zhpcallnum callnum) throws Exception {
		zhpcallnumDao.addZhpcallnum(callnum);
	}

	public void delZhpcallnum(String id) throws Exception {
		zhpcallnumDao.delZhpcallnum(id);
	}

	public List<Zhpcallnum> queryAllcallnum() throws Exception {
		return zhpcallnumDao.queryAllcallnum();
	}

}
