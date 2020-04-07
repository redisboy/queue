package com.suntendy.queue.messageSet.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.messageSet.dao.IMessageSetDao;
import com.suntendy.queue.messageSet.domain.Message;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.exception.SaveException;

public class MessageSetDaoImpl extends BaseDao<Message, Integer> implements
		IMessageSetDao {

	public Integer MessageSet(Message Message) throws Exception {
		return update(Message, BaseDao.UPDATE);
	}

	public List<Message> getMessage(String deptCode, String deptHall)
			throws Exception {
		String[] properties = { "deptCode", "deptHall" };
		String[] propertyValues = { deptCode, deptHall };
		return findByMap(properties, propertyValues, "", "",
				BaseDao.SELECTBYMAP);
	}

	
	public void saveMesDate(String name, String waittime, String phone,
			String deptHall, String deptCode) throws Exception {
			String[] properties = { "typename", "waittime", "phonenumber",
					"deptHall", "deptCode" };
			String[] propertyValues = { name, waittime, phone, deptHall,
					deptCode };
			HashMap<String, String> parameterMap = new HashMap<String, String>();
			for (int i = 0; i < properties.length; i++) {
				parameterMap.put(properties[i], propertyValues[i]);
			}
			this.getSqlMapClientTemplate().insert("Message.saveMesDate",
					parameterMap);
  	}
	
	public void resetDate() throws Exception {
		getSqlMapClientTemplate().delete("Message.reset");
	}

}
