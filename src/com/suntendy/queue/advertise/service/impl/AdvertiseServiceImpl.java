package com.suntendy.queue.advertise.service.impl;

import java.util.List;

import com.suntendy.queue.advertise.dao.IAdvertiseDao;
import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.service.IAdvertiseService;

public class AdvertiseServiceImpl implements IAdvertiseService {
	private IAdvertiseDao advertiseDao;

	public void setAdvertiseDao(IAdvertiseDao advertiseDao) {
		this.advertiseDao = advertiseDao;
	}

	public List<Advertise> getAlladvertise(String id, String status,String deptCode,String deptHall) {
		try {
			return advertiseDao.getAlladvertise(id, status,deptCode,deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer addAdvertise(Advertise advertiseVo) throws Exception {
		return advertiseDao.addAdvertise(advertiseVo);
	}

	public int updateByCode(String id, String message,String msg_state) throws Exception {
		return advertiseDao.updateByCode(id,message,msg_state);
	}

	public int delAdvertise(String id) {
		try {
			return advertiseDao.delAdvertise(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Advertise> getTVadvertise(String deptCode, String deptHall) throws Exception {
		return advertiseDao.getTVadvertise(deptCode, deptHall);
	}

	public Integer addComprehensiveScreen(Advertise advertiseVo)
			throws Exception {
		// TODO Auto-generated method stub
		return advertiseDao.addComprehensiveScreen(advertiseVo);
	}

	public int delComprehensiveScreen(String id) throws Exception {
		// TODO Auto-generated method stub
		return advertiseDao.delComprehensiveScreen(id);
	}

	public List<Advertise> getComprehensiveScreen(Advertise advertise,
			String deptCode, String deptHall) throws Exception {
		// TODO Auto-generated method stub
		return advertiseDao.getComprehensiveScreen(advertise, deptCode, deptHall);
	}

	public int updateComprehensiveScreenById(String id, String message,
			String msg_state) throws Exception {
		// TODO Auto-generated method stub
		return advertiseDao.updateComprehensiveScreenById(id, message, msg_state);
	}
}