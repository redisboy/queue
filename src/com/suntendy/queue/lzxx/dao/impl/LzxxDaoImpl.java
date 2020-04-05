package com.suntendy.queue.lzxx.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.lzxx.dao.ILzxxDao;
import com.suntendy.queue.lzxx.domain.Lzxx;

public class LzxxDaoImpl extends BaseDao<Lzxx, String> implements ILzxxDao {


	public void insertLzxx(Lzxx lzxx) throws Exception {
		insert(lzxx, BaseDao.INSERT);
	}
	
	public void insertLzxxLS(Lzxx lzxx) throws Exception {
		insert(lzxx, "insertLS");
	}

	public List<Lzxx> queryAllLzxx(Lzxx lzxx) throws Exception {
		String[] properties = {"xm","zllx","lsh","rksj","idnumber","deptHall","status","sxh"};
		Object[] propertyValues = {lzxx.getXm(),lzxx.getZllx(),lzxx.getLsh(),lzxx.getRksj(),
				lzxx.getIdnumber(),lzxx.getDeptHall(),lzxx.getStatus(),lzxx.getSxh()};
		return this.findByMap(properties, propertyValues, "", "",BaseDao.SELECTBYMAP);
	}
	
	public List<Lzxx> queryAllLzxxBysxh(Lzxx lzxx) throws Exception {
		String[] properties = {"xm","zllx","lsh","rksj","idnumber","deptHall","status","sxh"};
		Object[] propertyValues = {lzxx.getXm(),lzxx.getZllx(),lzxx.getLsh(),lzxx.getRksj(),
				lzxx.getIdnumber(),lzxx.getDeptHall(),lzxx.getStatus(),lzxx.getSxh()};
		return this.findByMap(properties, propertyValues, "", "","queryAllLzxxBysxh");
	}

	public void delLzck(Lzxx lzxx) throws Exception {
		this.getSqlMapClientTemplate().delete("Lzxx.delCK", lzxx);
	}

	public void insertLzck(Lzxx lzxx) throws Exception {
		this.insert(lzxx, "insertLzck");
	}

	public List<Lzxx> queryLzckByZllx(Lzxx lzxx) throws Exception {
		String[] properties = {"zllx","deptCode","deptHall"};
		Object[] propertyValues = {lzxx.getZllx(),lzxx.getDeptCode(),lzxx.getDeptHall()};
		return this.findByMap(properties, propertyValues, "", "", "queryCK");
	}

	public void updateLzck(Lzxx lzxx) throws Exception {
		this.getSqlMapClientTemplate().update("Lzxx.updateLzck", lzxx);
	}

	public void updateStatus(Lzxx lzxx) throws Exception {
		this.getSqlMapClientTemplate().update("Lzxx.updateStatus", lzxx);
//		this.update(properties, propertyValues, "", "", "updateStatus");
	}

	@Override
	public List<Lzxx> querySxhByLzxx(Lzxx lzxx) throws Exception {
		String[] properties = {"xm","zllx","lsh","rksj","idnumber","deptHall","status","sxh"};
		Object[] propertyValues = {lzxx.getXm(),lzxx.getZllx(),lzxx.getLsh(),lzxx.getRksj(),
				lzxx.getIdnumber(),lzxx.getDeptHall(),lzxx.getStatus(),lzxx.getSxh()};
		return this.findByMap(properties, propertyValues, "", "","querySxhByLzxx");
	}

}
