package com.suntendy.queue.queue.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.queue.dao.IZhpcallnumDao;
import com.suntendy.queue.queue.domain.Zhpcallnum;

public class ZhpcallnumDaoImpl extends BaseDao<Zhpcallnum, String> implements IZhpcallnumDao {

	public void addZhpcallnum(Zhpcallnum callnum) throws Exception {
		this.insert(callnum, "addCallNum");
	}

	public void delZhpcallnum(String id) throws Exception {
		this.deleteById(id, "delCallNum");
	}

	public List<Zhpcallnum> queryAllcallnum() throws Exception {
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "queryAllCallNum");
	}

}
