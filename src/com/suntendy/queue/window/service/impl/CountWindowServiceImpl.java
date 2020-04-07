package com.suntendy.queue.window.service.impl;

import java.util.List;
import com.suntendy.queue.window.dao.ICountWindowDao;
import com.suntendy.queue.window.domain.WindowCount;
import com.suntendy.queue.window.service.ICountWindowService;


public class CountWindowServiceImpl implements ICountWindowService {
	private ICountWindowDao countWindowDao;

	public List<WindowCount> getWindowCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall,String barid) {
		try {
			List<WindowCount> datas = countWindowDao.getWindowCount( tjms,  ksrq,  jsrq,deptCode,deptHall,barid);
			for (WindowCount detail : datas) {
				if (detail.getAve_waittime().startsWith(".")) {
					detail.setAve_waittime("0" + detail.getAve_waittime());
				}
				if (detail.getAve_worktime().startsWith(".")) {
					detail.setAve_worktime("0" + detail.getAve_worktime());
				}
			}
			return datas;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ICountWindowDao getCountWindowDao() {
		return countWindowDao;
	}

	public void setCountWindowDao(ICountWindowDao countWindowDao) {
		this.countWindowDao = countWindowDao;
	}
}
