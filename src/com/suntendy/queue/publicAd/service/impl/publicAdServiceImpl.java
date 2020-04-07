package com.suntendy.queue.publicAd.service.impl;

import java.util.List;

import com.suntendy.queue.publicAd.dao.IPublicAdDao;
import com.suntendy.queue.publicAd.domain.PublicAd;
import com.suntendy.queue.publicAd.service.IPublicAdService;

public class publicAdServiceImpl implements IPublicAdService {
	private IPublicAdDao publicAdDao;

	public IPublicAdDao getPublicAdDao() {
		return publicAdDao;
	}

	public void setPublicAdDao(IPublicAdDao publicAdDao) {
		this.publicAdDao = publicAdDao;
	}

	public Integer addPublicAd(PublicAd publicAdVo) throws Exception {
		return publicAdDao.addPublicAd(publicAdVo);
	}

	public int delPublicAd(String id) throws Exception {
		return publicAdDao.delPublicAd(id);
	}

	public List<PublicAd> getPublicAd(String id, String status,String deptCode,String deptHall)
			throws Exception {
		return publicAdDao.getPublicAd(id, status,deptCode,deptHall);
	}

	public int updateByCode(String id, String message, String msg_state)
			throws Exception {
		return publicAdDao.updateByCode(id, message, msg_state);
	}

	@Override
	public void updateByCode(PublicAd publicAd) throws Exception {
		
		publicAdDao.updateByCode(publicAd);
	}
	@Override
	public void  updateAdAllUsing() throws Exception{
		
		publicAdDao.updateAdAllUsing();
	}
	
}