package com.suntendy.queue.publicAd.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.publicAd.dao.IPublicAdDao;
import com.suntendy.queue.publicAd.domain.PublicAd;

public class PublicAdDaoImpl extends BaseDao<PublicAd, Integer> implements IPublicAdDao{

	public Integer addPublicAd(PublicAd publicAd) throws Exception {
		return this.insert(publicAd, BaseDao.INSERT);
	}

	public int delPublicAd(String id) throws Exception {
		return this.deleteById(Integer.valueOf(id), BaseDao.DELETEBYID);
	}

	public List<PublicAd> getPublicAd(String id, String status,String deptCode,String deptHall)
			throws Exception {
		String[] properties = { "id", "status","deptCode","deptHall"};
		String[] propertyValues = { id, status,deptCode,deptHall };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public int updateByCode(String id, String message, String msg_state)
			throws Exception {
		String[] properties = { "id", "message","msg_state"};
	    String[] propertyValues = { id,message,msg_state};
	    return this.updateByMap(Integer.valueOf(id), properties, propertyValues, BaseDao.UPDATEBYMAP);
	}

	@Override
	public void updateByCode(PublicAd publicAd) throws Exception {
		String[] properties = { "id", "isusing"};
		String[] propertyValues = { publicAd.getId(), publicAd.getIsUsing()};
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		for (int i = 0; i < properties.length; i++) {
			parameterMap.put(properties[i], propertyValues[i]);
		}
		this.getSqlMapClientTemplate().update("PublicAd.updateAdbyId", parameterMap);
	}
	@Override
	public void updateAdAllUsing() throws Exception {
		this.getSqlMapClientTemplate().update("PublicAd.updateAdAllUsing");
	}
}
