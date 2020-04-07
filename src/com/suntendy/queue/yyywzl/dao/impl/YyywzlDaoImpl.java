package com.suntendy.queue.yyywzl.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.yyywzl.dao.IYyywzlDao;
import com.suntendy.queue.yyywzl.domain.Yyywzl;

public class YyywzlDaoImpl extends BaseDao<Yyywzl, String> implements IYyywzlDao {

	@Override
	public List<Yyywzl> getYyywzlList() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public Integer addYyywzl(Yyywzl yyywzl) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("Yyywzl.addyyywzl",yyywzl);
		return 0;
	}

	@Override
	public List<Yyywzl> getYyywzlListById(String id) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"id"};
		String[] propertyValues = {id};
		return this.findByMap(properties, propertyValues, "", "", "getYyywzlListById");
	}

	@Override
	public Integer updateYyywzl(Yyywzl yyywzl) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("Yyywzl.updateYyywzl", yyywzl);
		return 0;
	}

	@Override
	public Integer delYyywzl(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id,"delYyywzl");
	}



	

}
