package com.suntendy.queue.queue.service;

import java.util.List;

import com.suntendy.queue.queue.domain.Zhpcallnum;

public interface IZhpcallnumService {

	public void addZhpcallnum(Zhpcallnum callnum) throws Exception;
	
	public void delZhpcallnum(String id) throws Exception;
	
	public List<Zhpcallnum> queryAllcallnum() throws Exception;
}
