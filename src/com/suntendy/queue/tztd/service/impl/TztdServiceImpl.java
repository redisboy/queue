package com.suntendy.queue.tztd.service.impl;

import java.util.List;

import com.suntendy.queue.tztd.dao.ItztdDao;
import com.suntendy.queue.tztd.domain.TztdVO;
import com.suntendy.queue.tztd.service.ItztdService;

public class TztdServiceImpl implements ItztdService {

	private ItztdDao tztdDao;
	
	public ItztdDao getTztdDao() {
		return tztdDao;
	}
	public void setTztdDao(ItztdDao tztdDao) {
		this.tztdDao = tztdDao;
	}
	
	public void delTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		tztdDao.delTztd(tztd);
	}
	
	public List<TztdVO> queryAllTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		return tztdDao.queryAllTztd(tztd);
	}
	
	public String saveTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		return tztdDao.saveTztd(tztd);
	}
	
	public void updateTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		tztdDao.updateTztd(tztd);
	}
	
	public TztdVO queryMaxId(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		return tztdDao.queryMaxId(tztd).get(0);
	}
	@Override
	public List<TztdVO> queryTztd(TztdVO tztd) throws Exception {
		return tztdDao.queryTztd(tztd);
	}

	
}
