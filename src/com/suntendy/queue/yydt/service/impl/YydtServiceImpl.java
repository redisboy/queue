package com.suntendy.queue.yydt.service.impl;

import java.util.List;

import com.suntendy.queue.yydt.dao.IYydtDao;
import com.suntendy.queue.yydt.domain.Yydt;
import com.suntendy.queue.yydt.service.IYydtService;

public class YydtServiceImpl implements IYydtService {
	
	private IYydtDao yydtDao;
	
	
	
	public void setYydtDao(IYydtDao yydtDao) {
		this.yydtDao = yydtDao;
	}

	@Override
	public List<Yydt> getYydtList() throws Exception {
		// TODO Auto-generated method stub
		return yydtDao.getYydtList();
	}

	@Override
	public Integer addYydt(Yydt yydt) throws Exception {
		// TODO Auto-generated method stub
		return yydtDao.addYydt(yydt);
	}

	@Override
	public List<Yydt> getYydtListById(String id) throws Exception {
		// TODO Auto-generated method stub
		return yydtDao.getYydtListById(id);
	}

	@Override
	public Integer updateYydt(Yydt yydt) throws Exception {
		// TODO Auto-generated method stub
		return yydtDao.updateYydt(yydt);
	}

	@Override
	public Integer delYydt(String id) throws Exception {
		// TODO Auto-generated method stub
		return yydtDao.delYydt(id);
	}

}
