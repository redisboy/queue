package com.suntendy.queue.reservation.dao;

import java.util.List;

import com.suntendy.queue.reservation.domain.Reservation;

public interface IReservationDao {

	
	public String saveReservation(Reservation res) throws Exception;
	
	public void updateReservation(Reservation res) throws Exception;
	
	public void delReservation(String sfzmhm) throws Exception;
	
	public List<Reservation> queryReservation(Reservation res) throws Exception;
	
	public void updateReservationStatus(Reservation res) throws Exception;
}
