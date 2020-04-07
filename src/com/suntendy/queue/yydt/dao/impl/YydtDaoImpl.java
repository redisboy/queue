package com.suntendy.queue.yydt.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.yydt.dao.IYydtDao;
import com.suntendy.queue.yydt.domain.Yydt;

public class YydtDaoImpl extends BaseDao<Yydt, String> implements IYydtDao{

	@Override
	public List<Yydt> getYydtList() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public Integer addYydt(Yydt yydt) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("Yydt.addyydt",yydt);
		return 0;
	}

	@Override
	public List<Yydt> getYydtListById(String id) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"id"};
		String[] propertyValues = {id};
		return this.findByMap(properties, propertyValues, "", "", "getYydtListById");
	}

	@Override
	public Integer updateYydt(Yydt yydt) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("Yydt.updateyydt", yydt);
		return 0;
	}

	@Override
	public Integer delYydt(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id,"delYydt");
	}

}
