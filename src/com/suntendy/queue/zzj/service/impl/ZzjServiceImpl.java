package com.suntendy.queue.zzj.service.impl;

import java.util.List;

import com.suntendy.queue.zzj.dao.IZzjDao;
import com.suntendy.queue.zzj.domain.Zzj;
import com.suntendy.queue.zzj.service.IZzjService;

public class ZzjServiceImpl implements IZzjService {
	
	private IZzjDao zzjDao;
	

	public IZzjDao getZzjDao() {
		return zzjDao;
	}
	public void setZzjDao(IZzjDao zzjDao) {
		this.zzjDao = zzjDao;
	}

	
	
	@Override
	public int delZzj(Zzj zzj) throws Exception {
		return zzjDao.delZzj(zzj);
	}

	@Override
	public List<Zzj> queryZzj(Zzj zzj) throws Exception {
		return zzjDao.queryZzj(zzj);
	}

	@Override
	public String saveZzj(Zzj zzj) throws Exception {
		return zzjDao.saveZzj(zzj);
	}

	@Override
	public int updateZzj(Zzj zzj) throws Exception {
		return zzjDao.updateZzj(zzj);
	}
	@Override
	public int czZzj(Zzj zzj) throws Exception {
		return zzjDao.czZzj(zzj);
	}

}
