package com.suntendy.queue.twoywlx.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.twoywlx.dao.ITwoywlxDao;
import com.suntendy.queue.twoywlx.domain.Twoywlx;

public class TwoywlxDaoImpl extends BaseDao<Twoywlx, String> implements ITwoywlxDao {

	@Override
	public Integer addTwoywlx(Twoywlx twoywlx) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("Twoywlx.addTwoywlx",twoywlx);
		return 0;
	}

	@Override
	public Integer updateTwoywlx(Twoywlx twoywlx) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("Twoywlx.updateTwoywlx",twoywlx);
		return 0;
	}

	@Override
	public List<Twoywlx> getTwoywlxList() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public List<Twoywlx> getTwoywlxListById(String id) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"id"};
		String[] propertyValues = {id};
		return this.findByMap(properties, propertyValues, "", "", "getTwoywlxListById");
	}

	@Override
	public Integer delTwoywlx(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id,"delTwoywlx");
	}





}
