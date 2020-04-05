package com.suntendy.queue.yygn.service.impl;

import java.util.List;

import com.suntendy.queue.yygn.dao.IYyxzDao;
import com.suntendy.queue.yygn.domain.Yyxz;
import com.suntendy.queue.yygn.service.IYyxzService;

public class YyxzServiceImpl implements IYyxzService {
	
	private IYyxzDao yyxzDao;
	
	
	public void setYyxzDao(IYyxzDao yyxzDao) {
		this.yyxzDao = yyxzDao;
	}

	@Override
	public Integer addYyxz(Yyxz yyxz) throws Exception {
		// TODO Auto-generated method stub
		return yyxzDao.addYyxz(yyxz);
	}

	@Override
	public Integer updateYyxz(Yyxz yyxz) throws Exception {
		// TODO Auto-generated method stub
		return yyxzDao.updateYyxz(yyxz);
	}

	@Override
	public List<Yyxz> getYyxzList() throws Exception {
		// TODO Auto-generated method stub
		return yyxzDao.getYyxzList();
	}

}
