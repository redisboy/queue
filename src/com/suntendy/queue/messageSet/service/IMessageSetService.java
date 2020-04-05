package com.suntendy.queue.messageSet.service;

import java.util.List;

import com.suntendy.queue.messageSet.domain.Message;

public interface IMessageSetService {

	/**
	 * 更改短信格式
	 * @param message
	 * @throws Exception
	 */
	public void MessageSet(Message message) throws Exception;
	
	/**
	 * 获取短信内容
	 * @return
	 * @throws Exception
	 */
	public List<Message> getMessage(String deptCode,String deptHall) throws Exception;
	
	/**
	 * 存储短信数据
	 * @return
	 * @throws Exception
	 */
	public void saveMesDate(String name,String waittime,String phone,String deptHall,String deptCode) throws Exception;
	/**
	 * 重置短信数据
	 * @return
	 * @throws Exception
	 */
	public void resetDate() throws Exception;
}
