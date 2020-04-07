package com.suntendy.queue.systemlog.service.impl;

import java.util.Date;
import java.util.List;

import com.suntendy.queue.systemlog.dao.ISystemLogDao;
import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;
import com.suntendy.queue.systemlog.service.ISystemLogService;

public class SystemLogServiceImpl implements ISystemLogService {
    private ISystemLogDao systemLogDao;
    
	public void setSystemLogDao(ISystemLogDao systemLogDao) {
		this.systemLogDao = systemLogDao;
	}

	@Override
	public List<Operate> queryOperate(String tjms,String ksrq, String jsrq,Operate operate) throws Exception {
		return systemLogDao.queryOperate(tjms,ksrq,jsrq,operate);
	}

	@Override
	public List<Loginls> querylogin(String tjms, String ksrq, String jsrq,
			Loginls loginls) throws Exception {
		return systemLogDao.querylogin(tjms, ksrq, jsrq, loginls);
	}

	@Override
	public List<Security> querySecurity(String tjms, String ksrq, String jsrq,
			Security security) throws Exception {
		return systemLogDao.querySecurity(tjms, ksrq, jsrq, security);
	}
	@Override
	public Integer addLoginls(Loginls loginls) throws Exception {
		return systemLogDao.addLoginls(loginls);
	}

	@Override
	public Integer updateLoginlsExit(Date date) throws Exception {
		return systemLogDao.updateLoginlsExit(date);
	}

	@Override
	public Integer addOperate(Operate operate) throws Exception {
		return systemLogDao.addOperate(operate);
	}

	@Override
	public Integer addSecurity(Security security) throws Exception {
		return systemLogDao.addSecurity(security);
	}

	@Override
	public List<Loginls> queryLoginErr(Loginls loginls) throws Exception {
		return systemLogDao.queryLoginErr(loginls);
	}

	@Override
	public List<Loginls> queryLoginSuu(Loginls loginls) throws Exception {
		return systemLogDao.queryLoginSuu(loginls);
	}

	@Override
	public String queryLoginCount(Loginls loginls) throws Exception {
		return systemLogDao.queryLoginCount(loginls);
	}

	@Override
	public List<Operate> qureyDetailed(String id) throws Exception {
		return systemLogDao.qureyDetailed(id);
	}

	@Override
	public List<Security> qureyDetailedSe(String id) throws Exception {
		return systemLogDao.qureyDetailedSe(id);
	}
}
