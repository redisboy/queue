package com.suntendy.queue.window.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.dao.ISetWindowDao;
import com.suntendy.queue.window.domain.Screen;
import com.suntendy.queue.window.service.ISetWindowService;

public class SetWindowServiceImpl implements ISetWindowService {
	private ISetWindowDao setWindowDao;

	public void setSetWindowDao(ISetWindowDao setWindowDao) {
		this.setWindowDao = setWindowDao;
	}

	public List<Screen> getWindowList(String deptCode, String deptHall,
			String id, String ip, String type,String businessid,String ckip) throws Exception {
		return setWindowDao.getWindowList(deptCode, deptHall, id, ip, type, businessid,ckip);
	}
	public List<Screen> getckxx(String deptCode, String deptHall,String ckip) throws Exception {
		return setWindowDao.getckxx(deptCode, deptHall,ckip);
	}

	public void updateScreenByCode(Screen screen) throws Exception {
		setWindowDao.updateScreen(screen);
		if (StringUtils.isNotEmpty(screen.getAddress())) {
			setWindowDao.updateBar(screen);
			setWindowDao.updateCaller(screen);
			setWindowDao.updateValue(screen);
		} else if (StringUtils.isNotEmpty(screen.getOldIP())) {//当此窗口变为主窗口时，删除其配置信息
			String barid = screen.getId().intValue() + "";
			setWindowDao.delBar(barid);
			setWindowDao.delCaller(barid);
			setWindowDao.delValue(barid);
		}
		
		updateScreenCache(String.valueOf(screen.getId()));
	}

	public void delScreen(String id, String ip) throws Exception {
		List<Screen> datas = getWindowList("", "", id, "", "","","");
		setWindowDao.delScreen(id);
		setWindowDao.delBar(id);
		setWindowDao.delCaller(id);
		setWindowDao.delValue(id);
		
		//更新缓存
		if (null != datas && !datas.isEmpty()) {
			Screen srn = datas.get(0);
			CacheManager cacheManager = CacheManager.getInstance();
			if (StringUtils.isNotEmpty(srn.getBarip())) {
				cacheManager.removeWindow(srn.getBarip());
			} else {
				cacheManager.removeWindow(srn.getWindowId());
			}
		}
	}

	public void addScreen(Screen screen) throws Exception {
		Integer id = setWindowDao.addScreen(screen);
		screen.setId(id.intValue());
		if (StringUtils.isNotEmpty(screen.getAddress())) {
			setWindowDao.addBar(screen);
			setWindowDao.addCaller(screen);
			setWindowDao.addValue(screen);
		}
		
		updateScreenCache(String.valueOf(screen.getId()));
	}
	
	private void updateScreenCache(String id) throws Exception {
		List<Screen> datas = getWindowList("", "", id, "", "","","");
		if (null != datas && !datas.isEmpty()) {
			Screen srn = datas.get(0);
			CacheManager cacheManager = CacheManager.getInstance();
			String deptCode = cacheManager.getOfDeptCache("deptCode");
			String deptHall = cacheManager.getOfDeptCache("deptHall");
			if (StringUtils.isNotEmpty(srn.getBarip())) {
				if (StringUtils.isNotEmpty(srn.getMenuAddress())) {
					String windowId = srn.getWindowId();
					srn.setWindowId(srn.getMenuAddress());
					srn.setMenuAddress(windowId);
				}
				
				if ("1".equals(srn.getShowNum())) {
					srn.setContent2(srn.getContent());
					srn.setContent("");
				} else {
					srn.setContent2(" ");
				}
				
				srn.setLed(cacheManager.getLedCache(deptCode + deptHall));
				cacheManager.addOfWindowCache(srn.getBarip(), srn);
			} else {
				cacheManager.addOfWindowCache(srn.getWindowId(), srn);
			}
		}
	}

	public List<Screen> getAddress() throws Exception {
		return setWindowDao.getAddress();
	}

	public List<Screen> getAddressAndStatuss() throws Exception {
		return setWindowDao.getAddressAndStatuss();
	}

	@Override
	public List<Screen> getCountShul(String deptCode,String deptHall) throws Exception {
		return setWindowDao.getCountShul(deptCode,deptHall);
	}

	@Override
	public List<Screen> searchBar(Screen screen) throws Exception {
		return setWindowDao.searchBar(screen);
	}

	@Override
	public List<Screen> queryEveryScreenYWL(String deptCode,String deptHall) {
		return setWindowDao.queryEveryScreenYWL(deptCode,deptHall);
	}

	@Override
	public List<Screen> querybarid(Screen screen) throws Exception {
		return setWindowDao.querybarid(screen);
	}

	@Override
	public int updateCallerByIp(Screen screen) throws Exception {
		return setWindowDao.updateCallerByIp(screen);
	}
}