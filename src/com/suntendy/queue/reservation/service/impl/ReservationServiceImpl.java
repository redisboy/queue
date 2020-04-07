package com.suntendy.queue.reservation.service.impl;

import java.util.List;

import com.suntendy.queue.reservation.dao.IReservationDao;
import com.suntendy.queue.reservation.domain.Reservation;
import com.suntendy.queue.reservation.service.IReservationService;

public class ReservationServiceImpl implements IReservationService {
	
	private IReservationDao reservationDao;
	
	public IReservationDao getReservationDao() {
		return reservationDao;
	}
	public void setReservationDao(IReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public void delReservation(String sfzmhm) throws Exception {
		reservationDao.delReservation(sfzmhm);

	}

	@Override
	public List<Reservation> queryReservation(Reservation res) throws Exception {
		return reservationDao.queryReservation(res);
	}

	@Override
	public String saveReservation(Reservation res) throws Exception {
		return reservationDao.saveReservation(res);
	}

	@Override
	public void updateReservation(Reservation res) throws Exception {
		reservationDao.updateReservation(res);
	}
	@Override
	public void updateReservationStatus(Reservation res) throws Exception {
		reservationDao.updateReservationStatus(res);
		
	}

}
