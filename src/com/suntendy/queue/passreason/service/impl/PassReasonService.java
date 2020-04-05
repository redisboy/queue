package com.suntendy.queue.passreason.service.impl;

import java.util.List;


import com.suntendy.queue.passreason.dao.IPassReasonDao;
import com.suntendy.queue.passreason.domain.PassReason;
import com.suntendy.queue.passreason.service.IPassReasonService;

public class PassReasonService implements IPassReasonService {
	private IPassReasonDao passReasonDao;
	
	public List selectPassReason(PassReason passReason) throws Exception{
		return passReasonDao.selectPassReason(passReason);
	}

	public IPassReasonDao getPassReasonDao() {
		return passReasonDao;
	}

	public void setPassReasonDao(IPassReasonDao passReasonDao) {
		this.passReasonDao = passReasonDao;
	}

	@Override
	public void savePassReason(PassReason passReason) throws Exception {
		passReasonDao.savePassReason(passReason);
	}

	@Override
	public void deletePassReason(PassReason passReason) throws Exception {
		passReasonDao.deletePassReason(passReason);
		
	}

	@Override
	public void updatePassReasonById(PassReason passReason) throws Exception {
		passReasonDao.updatePassReasonById(passReason);
		
	}
}
