package com.suntendy.queue.led.service.impl;

import java.util.List;

import com.suntendy.queue.led.dao.LedDao;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.util.cache.CacheManager;

public class LedServiceImpl implements LedService {
	private LedDao ledDao;
	
	public void setLedDao(LedDao ledDao) {
		this.ledDao = ledDao;
	}
	public List<LED> getLedInfo(String deptCode, String deptHall) throws Exception {
		return ledDao.getLedInfo(deptCode,deptHall);
	}
	
	@Override
	public void add(LED led) throws Exception {
		ledDao.add(led);
		
		//更新缓存
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		cacheManager.addOfLedCache(deptCode + deptHall, led);
	}

	public void updateLED(LED led) throws Exception {
		ledDao.updateLED(led);
		
		//更新缓存
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		cacheManager.addOfLedCache(deptCode + deptHall, led);
	}


	public void addLED_TV(LED led) throws Exception {
		// TODO Auto-generated method stub
		ledDao.addLED_TV(led);
		//更新缓存
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		cacheManager.addOfLed_tvCache(deptCode + deptHall, led);
	}


	public List<LED> getLedInfo_TV(LED led)throws Exception {
		// TODO Auto-generated method stub
		return ledDao.getLedInfo_TV(led);
	}


	public void updateLED_TV(LED led) throws Exception {
		// TODO Auto-generated method stub
		ledDao.updateLED_TV(led);
		//更新缓存
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		cacheManager.addOfLed_tvCache(deptCode + deptHall, led);
	}

	public List<LED> getLED_Content(LED ledVo) throws Exception {
		// TODO Auto-generated method stub
		return ledDao.getLED_Content(ledVo);
	}

	public void addLED_Content(LED ledVo) throws Exception {
		// TODO Auto-generated method stub
		ledDao.addLED_Content(ledVo);
	}

	public void updateLED_Content(LED ledVo) throws Exception {
		// TODO Auto-generated method stub
		ledDao.updateLED_Content(ledVo);
	}
	
	public Integer delLED_TV(LED ledVo) throws Exception {
		return ledDao.delLED_TV(ledVo);
	}
	
	public LED getMaxId() throws Exception {
		// TODO Auto-generated method stub
		List<LED> list = ledDao.getMaxId();
		LED led =new LED();
		if(null == list && list.size()==0){
			if(null == list.get(0).getId()){
				led.setId("1");
			}
		}else{
			if(null == list.get(0).getId()){
				led.setId("1");
			}else{
				led.setId(String.valueOf(Integer.parseInt(list.get(0).getId())+1));
			}
		}
		return led;
	}
	@Override
	public void delLED_Content(LED ledVo) throws Exception {
		// TODO Auto-generated method stub
		ledDao.delLED_Content(ledVo);
	}

}