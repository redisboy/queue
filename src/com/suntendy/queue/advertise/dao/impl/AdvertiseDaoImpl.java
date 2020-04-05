package com.suntendy.queue.advertise.dao.impl;

import java.util.List;

import com.suntendy.queue.advertise.dao.IAdvertiseDao;
import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.base.BaseDao;

public class AdvertiseDaoImpl extends BaseDao<Advertise, Integer> implements IAdvertiseDao{

	public List<Advertise> getAlladvertise(String id, String status,String deptCode,String deptHall) throws Exception {
		String[] properties = { "id", "status","deptcode","depthall"};
		String[] propertyValues = { id, status,deptCode,deptHall };
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public Integer addAdvertise(Advertise dvertiseVo)throws Exception {
		return this.insert(dvertiseVo, BaseDao.INSERT);
	}

	public int updateByCode(String id, String message, String msg_state) throws Exception {
		String[] properties = { "id", "message", "msg_state" };
		String[] propertyValues = { id, message, msg_state };
		return this.updateByMap(Integer.valueOf(id), properties, propertyValues, BaseDao.UPDATEBYMAP);
	}

	public int delAdvertise(String id) throws Exception {
		return this.deleteById(Integer.valueOf(id), BaseDao.DELETEBYID);
	}
	
	public List<Advertise> getTVadvertise(String deptCode,String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall"};
		String[] propertyValues = { deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "", "", "getTVadvertise");
	}

	public Integer addComprehensiveScreen(Advertise advertise)
			throws Exception {
		// TODO Auto-generated method stub
		return this.insert(advertise, "addComprehensiveScreen");
	}

	public int delComprehensiveScreen(String id) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		return this.deleteById(Integer.valueOf(id), "delComprehensiveScreen");
	}

	public List<Advertise> getComprehensiveScreen(Advertise advertise,String deptCode, String deptHall) throws Exception {
		String[] properties = { "id", "status","deptcode", "depthall"};
		String[] propertyValues = { advertise.getId(),advertise.getMsg_state(),deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "", "", "getComprehensiveScreen");
	}

	@Override
	public int updateComprehensiveScreenById(String id, String message,
			String msg_state) throws Exception {
		String[] properties = { "id", "message", "msg_state" };
		String[] propertyValues = { id, message, msg_state };
		return this.updateByMap(Integer.valueOf(id), properties, propertyValues, "updateComprehensiveScreenById");
	}
}
