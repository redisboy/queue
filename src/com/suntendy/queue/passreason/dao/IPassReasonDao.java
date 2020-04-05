package com.suntendy.queue.passreason.dao;

import java.util.List;

import com.suntendy.queue.passreason.domain.PassReason;



public interface IPassReasonDao {
	public List selectPassReason(PassReason passReason) throws Exception;
	
	public void savePassReason(PassReason passReason) throws Exception;
	
	public void deletePassReason(PassReason passReason) throws Exception;
	
	public void updatePassReasonById(PassReason passReason) throws Exception;
}
