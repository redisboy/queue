package com.suntendy.queue.lzxx.service.impl;

import java.util.List;

import com.suntendy.queue.lzxx.dao.ILzxxDao;
import com.suntendy.queue.lzxx.domain.Lzxx;
import com.suntendy.queue.lzxx.service.ILzxxService;
import com.suntendy.queue.window.dao.ISetWindowDao;
import com.suntendy.queue.window.domain.Screen;

public class LzxxServiceImpl implements ILzxxService {
	
	private ILzxxDao lzxxDao;
	private ISetWindowDao setWindowDao;
	
	public ILzxxDao getLzxxDao() {
		return lzxxDao;
	}
	public void setLzxxDao(ILzxxDao lzxxDao) {
		this.lzxxDao = lzxxDao;
	}

	public ISetWindowDao getSetWindowDao() {
		return setWindowDao;
	}
	public void setSetWindowDao(ISetWindowDao setWindowDao) {
		this.setWindowDao = setWindowDao;
	}
	
	public void insertLzxx(Lzxx lzxx) throws Exception {
		lzxxDao.insertLzxx(lzxx);
	}

	public List<Lzxx> queryAllLzxx(Lzxx lzxx) throws Exception {
		return lzxxDao.queryAllLzxx(lzxx);
	}
	
	public List<Lzxx> queryAllLzxxBysxh(Lzxx lzxx) throws Exception {
		return lzxxDao.queryAllLzxxBysxh(lzxx);
	}
	
	public void delLzck(Lzxx lzxx) throws Exception {
		lzxxDao.delLzck(lzxx);
	}
	
	public void insertLzck(Lzxx lzxx) throws Exception {
		lzxxDao.insertLzck(lzxx);
	}
	
	public List<Lzxx> queryLzckByZllx(Lzxx lzxx) throws Exception {
		return lzxxDao.queryLzckByZllx(lzxx);
	}
	
	public void updateLzck(Lzxx lzxx) throws Exception {
		lzxxDao.updateLzck(lzxx);
	}
	
	public void updateStatus(Lzxx lzxx) throws Exception {
		lzxxDao.updateStatus(lzxx);
	}
	@Override
	public List<Screen> querybarid(Screen screen) throws Exception {
		return setWindowDao.querybarid(screen);
	}
	@Override
	public void insertLzxxLS(Lzxx lzxx) throws Exception {
		lzxxDao.insertLzxxLS(lzxx);
	}
	@Override
	public List<Lzxx> querySxhByLzxx(Lzxx lzxx) throws Exception {
		return lzxxDao.querySxhByLzxx(lzxx);
	}

}
