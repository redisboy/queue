package com.suntendy.queue.tuiban.dao;

import java.util.List;

import com.suntendy.queue.tuiban.domain.TuiBan;

public interface ITuiBanDao {
	
	public List<TuiBan> queryTuiBan(TuiBan tuiban) throws Exception;
	
	public String insertTuiBan(TuiBan tuiban) throws Exception;

}
