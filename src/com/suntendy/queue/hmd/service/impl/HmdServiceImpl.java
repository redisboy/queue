package com.suntendy.queue.hmd.service.impl;

import java.util.List;

import com.suntendy.queue.hmd.dao.IHmdLogDao;
import com.suntendy.queue.hmd.dao.IhmdDao;
import com.suntendy.queue.hmd.domain.Hmd;
import com.suntendy.queue.hmd.domain.HmdLog;
import com.suntendy.queue.hmd.service.IhmdService;

public class HmdServiceImpl implements IhmdService {

	private IhmdDao hmdDao;
	private IHmdLogDao hmdLogDao;
	
	public IhmdDao getHmdDao() {
		return hmdDao;
	}
	public void setHmdDao(IhmdDao hmdDao) {
		this.hmdDao = hmdDao;
	}
	public IHmdLogDao getHmdLogDao() {
		return hmdLogDao;
	}
	public void setHmdLogDao(IHmdLogDao hmdLogDao) {
		this.hmdLogDao = hmdLogDao;
	}
	
	
	/**
	 * 添加黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public String saveHmd(Hmd hmd) throws Exception {
		return hmdDao.saveHmd(hmd);
	}
	/**
	 * 查询黑名单
	 * @param hmd
	 * @return
	 * @throws Exception
	 */
	public List<Hmd> queryAllHmd(Hmd hmd) throws Exception {
		return hmdDao.queryAllHmd(hmd);
	}
	/**
	 * 根据ID删除黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delHmd(String id) throws Exception {
		return hmdDao.delHmd(id);
	}
	/**
	 * 根据ID查询黑名单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hmd queryById(String id) throws Exception {
		return hmdDao.queryById(id);
	}
	/**
	 * 增加黑名单记录
	 * @param hlog
	 * @return
	 * @throws Exception
	 */
	public String saveHmdLog(HmdLog hlog) throws Exception {
		return hmdLogDao.saveHmdLog(hlog);
	}
	public void addAllHmd(List<Hmd> listHmd) throws Exception {
		hmdDao.addAllHmd(listHmd);
	}
	/**
	 * 查询黑名单限制取号数
	 * @param listHmd
	 * @throws Exception
	 */
	public int queryHmd(String id) throws Exception {
		return hmdDao.queryHmd(id);
	}

}
