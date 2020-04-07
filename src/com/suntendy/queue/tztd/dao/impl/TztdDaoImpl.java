package com.suntendy.queue.tztd.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.tztd.dao.ItztdDao;
import com.suntendy.queue.tztd.domain.TztdVO;

public class TztdDaoImpl extends BaseDao<TztdVO, String> implements ItztdDao {

	public void delTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
//		return this.deleteById(tztd.getId(), "delTztd");
		this.update(tztd, "update");
	}

	public List<TztdVO> queryAllTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {"lrrq","idnumber"};
		String[] propertyValues = {tztd.getLrrq(),tztd.getIdnumber()};
		return this.findByMap(properties, propertyValues, "", "", "queryAll");
	}
	
	public List<TztdVO> queryMaxId(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "queryMaxId");
	}

	public String saveTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		return this.insert(tztd, "saveTztd");
	}

	public int updateTztd(TztdVO tztd) throws Exception {
		// TODO Auto-generated method stub
		return this.update(tztd, "updateTztd");
	}

	@Override
	public List<TztdVO> queryTztd(TztdVO tztd) throws Exception {
		String[] properties = {"lrrq","idnumber"};
		String[] propertyValues = {tztd.getLrrq(),tztd.getIdnumber()};
		return this.findByMap(properties, propertyValues, "", "", "query");
	}

}
