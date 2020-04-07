package com.suntendy.queue.nextwindow.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.suntendy.queue.nextwindow.dao.INextWindowDao;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.nextwindow.service.InextWindowService;
import com.suntendy.queue.queue.dao.ICodeDao;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.SaveException;

public class nextWindowServiceImpl implements InextWindowService {

	
	private INextWindowDao nextWindowDao;
	private ICodeDao codeDao;
	
	public ICodeDao getCodeDao() {
		return codeDao;
	}
	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
	}
	/**
	 * 保存下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public String saveNWindow(NextWindow nwindow) throws Exception {
		return nextWindowDao.saveNWindow(nwindow);
	}
	/**
	 * 查询下一步提示信息
	 * @return
	 * @throws Exception
	 */
	public List<NextWindow> queryAllNWindow(NextWindow nWindow)throws Exception {
		return nextWindowDao.queryAllNWindow(nWindow);
	}
	/**
	 * 根据ID查询下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryById(NextWindow nwindow)throws Exception {
		return nextWindowDao.queryById(nwindow);
	}
	/**
	 * 根据ID修改下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public void updateById(NextWindow nwindow)throws Exception {
		nextWindowDao.updateById(nwindow);
	}
	/**
	 * 根据ID删除下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public void deleteById(NextWindow nwindow)throws Exception {
		nextWindowDao.deleteById(nwindow);
	}
	/**
	 * 根据系统类型查询业务类型
	 * @param dmlb
	 * @return
	 * @throws Exception
	 */
	public List<Code> getAllGredentials(String dmlb) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		try {
			return codeDao.getAllGredentials(dmlb,deptCode,deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Code>();
	}
	
	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz,String zt,String deptCode, String deptHall) {
		try {
			return codeDao.getCodeByDmlbAndDmz(dmlb, dmz,zt,deptCode,deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Code>();
	}
	/**
	 * 根据系统类型、业务类型、业务原因查询，是否存在重复数据
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheck(NextWindow nwindow) throws Exception {
		return nextWindowDao.queryForCheck(nwindow);
	}
	/**
	 * 为窗口提示信息查询
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheckResult(NextWindow nwindow) throws Exception {
		return nextWindowDao.queryForCheckResult(nwindow);
	}
	/**
	 * 根据系统类型和业务类型查询业务原因
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<Code> queryForYwyy(Code code) throws Exception {
		return codeDao.queryForYwyy(code);
	}
	public INextWindowDao getNextWindowDao() {
		return nextWindowDao;
	}
	public void setNextWindowDao(INextWindowDao nextWindowDao) {
		this.nextWindowDao = nextWindowDao;
	}

}
