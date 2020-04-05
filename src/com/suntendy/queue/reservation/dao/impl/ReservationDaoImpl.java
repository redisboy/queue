package com.suntendy.queue.reservation.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.reservation.dao.IReservationDao;
import com.suntendy.queue.reservation.domain.Reservation;

public class ReservationDaoImpl extends BaseDao<Reservation, String> implements
		IReservationDao {

	@Override
	public void delReservation(String sfzmhm) throws Exception {
		this.getSqlMapClientTemplate().delete("Reservation.delRes", sfzmhm);
	}

	@Override
	public List<Reservation> queryReservation(Reservation res) throws Exception {
		String[] properties = {"sfzmhm","yykssj","yyjssj","flag","deptCode","deptHall"};
		Object[] propertyValues = {res.getSfzmhm(),res.getYykssj(),res.getYyjssj(),res.getFlag(),res.getDeptCode(),res.getDeptHall()};
		return this.findByMap(properties, propertyValues, "", "",BaseDao.SELECTBYMAP);
	}

	@Override
	public String saveReservation(Reservation res) throws Exception {
		return this.insert(res, "saveReservation");
	}

	@Override
	public void updateReservation(Reservation res) throws Exception {
		this.getSqlMapClientTemplate().update("Reservation.updRes", res);
	}

	@Override
	public void updateReservationStatus(Reservation res) throws Exception {
		this.getSqlMapClientTemplate().update("Reservation.updStatus", res);
		
	}

}
