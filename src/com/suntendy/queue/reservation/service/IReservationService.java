package com.suntendy.queue.reservation.service;

import java.util.List;

import com.suntendy.queue.reservation.domain.Reservation;

public interface IReservationService {

	
	public String saveReservation(Reservation res) throws Exception;
	
	public void updateReservation(Reservation res) throws Exception;
	
	public void delReservation(String sfzmhm) throws Exception;
	
	public List<Reservation> queryReservation(Reservation res) throws Exception;
	
	public void updateReservationStatus(Reservation res) throws Exception;
}
