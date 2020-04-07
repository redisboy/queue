package com.suntendy.queue.yyywzl.service.impl;

import java.util.List;

import com.suntendy.queue.yyywzl.dao.IYyywzlDao;
import com.suntendy.queue.yyywzl.domain.Yyywzl;
import com.suntendy.queue.yyywzl.service.IYyywzlService;

public class YyywzlServiceImpl implements IYyywzlService {

	private IYyywzlDao yyywzlDao;

	public void setYyywzlDao(IYyywzlDao yyywzlDao) {
		this.yyywzlDao = yyywzlDao;
	}




	@Override
	public List<Yyywzl> getYyywzlList() throws Exception {
		// TODO Auto-generated method stub
		return yyywzlDao.getYyywzlList();
	}




	@Override
	public Integer addYyywzl(Yyywzl yyywzl) throws Exception {
		// TODO Auto-generated method stub
		return yyywzlDao.addYyywzl(yyywzl);
	}




	@Override
	public List<Yyywzl> getYyywzlListById(String id) throws Exception {
		// TODO Auto-generated method stub
		return yyywzlDao.getYyywzlListById(id);
	}




	@Override
	public Integer updateYyywzl(Yyywzl yyywzl) throws Exception {
		// TODO Auto-generated method stub
		return yyywzlDao.updateYyywzl(yyywzl);
	}




	@Override
	public Integer delYyywzl(String id) throws Exception {
		// TODO Auto-generated method stub
		return yyywzlDao.delYyywzl(id);
	}
	
	
	
	
}
