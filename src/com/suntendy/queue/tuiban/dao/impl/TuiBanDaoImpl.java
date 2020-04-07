package com.suntendy.queue.tuiban.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.tuiban.dao.ITuiBanDao;
import com.suntendy.queue.tuiban.domain.TuiBan;
import com.suntendy.queue.util.cache.CacheManager;

public class TuiBanDaoImpl extends BaseDao<TuiBan, String> implements ITuiBanDao {

	/**
	 * 增加退办
	 */
	public String insertTuiBan(TuiBan tuiban) throws Exception {
		return this.insert(tuiban, "savetuiban");
	}

	/**
	 * 查询退办
	 */
	public List<TuiBan> queryTuiBan(TuiBan tuiban) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String tbnum = cacheManager.getSystemConfig("tbnum");
		if ("".equals(tbnum) || tbnum == null) {
			tbnum ="0";
		}
		String[] properties = {"idnumber","tbnum"};
		Object[] propertyValues = {tuiban.getIdnumber(),tbnum};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

}
