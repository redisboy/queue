package com.suntendy.queue.tuiban.service.impl;

import java.util.List;

import com.suntendy.queue.tuiban.dao.ITuiBanDao;
import com.suntendy.queue.tuiban.domain.TuiBan;
import com.suntendy.queue.tuiban.service.ITuiBanService;

public class TuiBanServiceImpl implements ITuiBanService {
	
	private ITuiBanDao tuibanDao;
	
	

	public ITuiBanDao getTuibanDao() {
		return tuibanDao;
	}

	public void setTuibanDao(ITuiBanDao tuibanDao) {
		this.tuibanDao = tuibanDao;
	}

	@Override
	public String insertTuiBan(TuiBan tuiban) throws Exception {
		return tuibanDao.insertTuiBan(tuiban);
	}

	@Override
	public List<TuiBan> queryTuiBan(TuiBan tuiban) throws Exception {
		return tuibanDao.queryTuiBan(tuiban);
	}

}
