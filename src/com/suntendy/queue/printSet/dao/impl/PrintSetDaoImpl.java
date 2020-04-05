package com.suntendy.queue.printSet.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.printSet.dao.IPrintSetDao;
import com.suntendy.queue.printSet.domain.Print;

public class PrintSetDaoImpl extends BaseDao<Print, Integer> implements
		IPrintSetDao {

	public Integer PrintSet(Print print) throws Exception {
		return update(print, BaseDao.UPDATE);
	}

	public List<Print> getPrint(String deptCode, String deptHall) throws Exception {
		String[] properties = { "deptcode", "depthall" };
		String[] propertyValues = { deptCode, deptHall };
		return findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

}
