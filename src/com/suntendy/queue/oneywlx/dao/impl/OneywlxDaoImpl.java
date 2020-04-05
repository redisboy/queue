package com.suntendy.queue.oneywlx.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.oneywlx.dao.IOneywlxDao;
import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.yyywzl.domain.Yyywzl;

public class OneywlxDaoImpl extends BaseDao<OneYwlx, String>  implements IOneywlxDao{

	@Override
	public List<OneYwlx> getOneYwlxList() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public Integer addOneywlx(OneYwlx oneYwlx) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("OneYwlx.addOneywlx",oneYwlx);
		return 0;
	}

	@Override
	public Integer updateOneywlx(OneYwlx oneYwlx) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("OneYwlx.updateOneywlx",oneYwlx);
		return 0;
	}

	@Override
	public List<OneYwlx> getOneywlxListById(String id) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"id"};
		String[] propertyValues = {id};
		return this.findByMap(properties, propertyValues, "", "", "getOneywlxListById");
	}

	@Override
	public Integer delOneywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id,"delOneywlx");
	}
	

	@Override
	public List<OneYwlx> getFormTwo(String id) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"id"};
		String[] propertyValues = {id};
		return this.findByMap(properties, propertyValues, "", "", "getFormTwo");
	}

	@Override
	public List<OneYwlx> getOneywlxListByEjywzt() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "getOneywlxListByEjywzt");
	}

	@Override
	public Integer delAllFormOneTwoywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id,"delAllFormOneTwoywlx");
	}

	


}
