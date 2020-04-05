package com.suntendy.queue.messageSet.service.impl;

import java.util.List;

import com.suntendy.queue.messageSet.dao.IMessageSetDao;
import com.suntendy.queue.messageSet.domain.Message;
import com.suntendy.queue.messageSet.service.IMessageSetService;

public class MessageSetServiceImpl implements IMessageSetService {

	private IMessageSetDao MessageSetDao;

	public IMessageSetDao getMessageSetDao() {
		return MessageSetDao;
	}

	public void setMessageSetDao(IMessageSetDao messageSetDao) {
		this.MessageSetDao = messageSetDao;
	}

	public void MessageSet(Message message) throws Exception {
		MessageSetDao.MessageSet(message);
	}

	public List<Message> getMessage(String deptCode, String deptHall) throws Exception {
		return MessageSetDao.getMessage(deptCode, deptHall);
	}

	
	public void saveMesDate(String name, String waittime, String phone,
			String deptHall, String deptCode) throws Exception {
		 MessageSetDao.saveMesDate(name,waittime,phone, deptHall,deptCode);
	}

	
	public void resetDate() throws Exception {
		MessageSetDao.resetDate();
		
	}

	
}
