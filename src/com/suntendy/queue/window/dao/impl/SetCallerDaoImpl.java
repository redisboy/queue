package com.suntendy.queue.window.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.window.dao.ISetCallerDao;
import com.suntendy.queue.window.domain.Caller;

public class SetCallerDaoImpl extends BaseDao<Caller, Integer> implements ISetCallerDao{

	public List<Caller> getIP(String ipaddress) throws Exception {
		String[] properties = { "ipaddress"};
	    String[] propertyValues = { ipaddress };
	    return this.findByMap(properties, propertyValues, "", "", "byIP");
	}
}
