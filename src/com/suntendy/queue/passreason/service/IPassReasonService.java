package com.suntendy.queue.passreason.service;

import java.util.List;

import com.suntendy.queue.passreason.domain.PassReason;



public interface IPassReasonService {
	public List selectPassReason(PassReason passReason) throws Exception;
	
	public void savePassReason(PassReason passReason) throws Exception;
	
	public void deletePassReason(PassReason passReason) throws Exception;
	
	public void updatePassReasonById(PassReason passReason) throws Exception;
}
