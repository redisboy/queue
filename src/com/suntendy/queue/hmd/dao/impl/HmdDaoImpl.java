package com.suntendy.queue.hmd.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.hmd.dao.IhmdDao;
import com.suntendy.queue.hmd.domain.Hmd;

public class HmdDaoImpl extends BaseDao<Hmd, String> implements IhmdDao {

	/**
	 * 添加黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public String saveHmd(Hmd hmd) throws Exception {
		return this.insert(hmd, "saveHmd");
	}

	/**
	 * 查询黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public List<Hmd> queryAllHmd(Hmd hmd) throws Exception {
		String[] properties = {"name","id"};
		Object[] propertyValues = {hmd.getName(),hmd.getId()};
		return this.findByMap(properties, propertyValues, "t.rowid", "desc", BaseDao.SELECTBYMAP);
	}

	/**
	 * 根据ID删除黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delHmd(String id) throws Exception {
		return this.deleteById(id, BaseDao.DELETEBYID);
	}

	/**
	 * 根据ID查询黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hmd queryById(String id) throws Exception {
		return this.findById(id, BaseDao.SELECTBYID);
	}

	public void addAllHmd(List<Hmd> listHmd) throws Exception {
		this.batchInsert(listHmd, "addAll");
	}

	/**
	 * 根据身份证查询其当月所取号的次数
	 * @param listHmd
	 * @throws Exception
	 */
	public int queryHmd(String id) throws Exception {
		HashMap<String,String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", id);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Hmd.queryQuHaos", parameterMap);
	}

	

}
