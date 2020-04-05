package com.suntendy.queue.passreason.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.passreason.dao.IPassReasonDao;
import com.suntendy.queue.passreason.domain.PassReason;


public class PassReasonDao extends BaseDao<PassReason, Number> implements IPassReasonDao{
	public List selectPassReason(PassReason passReason)throws Exception{
		String properties[] = {"id","reason","code","tjrq","deptCode","deptHall"};
		String propertyValues[] = {passReason.getId(),passReason.getReason(),passReason.getCode(),passReason.getTjrq(),passReason.getDeptCode(),passReason.getDeptHall()};
		return this.findByMap(properties, propertyValues, "", "", "selectPassReason");
	}

	@Override
	public void savePassReason(PassReason passReason) throws Exception {
		
		this.insert(passReason,"savePassReason");
	}

	@Override
	public void deletePassReason(PassReason passReason) throws Exception {
		
		this.getSqlMapClientTemplate().delete("PassReason.deletePassReason",passReason.getId());
	}

	@Override
	public void updatePassReasonById(PassReason passReason) throws Exception {
		this.update(passReason, BaseDao.UPDATE);
	}
}
