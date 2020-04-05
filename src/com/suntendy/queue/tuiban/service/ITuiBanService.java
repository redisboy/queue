package com.suntendy.queue.tuiban.service;

import java.util.List;

import com.suntendy.queue.tuiban.domain.TuiBan;

public interface ITuiBanService {
	
	public List<TuiBan> queryTuiBan(TuiBan tuiban) throws Exception;
	
	public String insertTuiBan(TuiBan tuiban) throws Exception;

}
