package com.suntendy.queue.led.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.led.dao.LedDao;
import com.suntendy.queue.led.domain.LED;

public class LedDaoImpl extends BaseDao<LED, Integer> implements LedDao {

	public List<LED> getLedInfo(String deptCode, String deptHall) throws Exception {
		return findByMap(new String[] {"deptcode","depthall"}, new String[] {deptCode,deptHall}, "", "", BaseDao.SELECTBYMAP);
	}
	
	public void add(LED led) throws Exception {
		insert(led, BaseDao.INSERT);
	}

	public Integer updateLED(LED ledVo) throws Exception {
		return update(ledVo, BaseDao.UPDATE);
	}

	public List<LED> getLedInfo_TV(LED led)throws Exception {
		return findByMap(new String[] {"address","deptcode","depthall","flag"}, new String[] {led.getAddress(),led.getDeptCode(),led.getDeptHall(),led.getFlag()}, "", "", "getLedInfo_TV");
	}
	
	public void addLED_TV(LED led) throws Exception {
		insert(led, "addLED_TV");
	}

	public Integer updateLED_TV(LED ledVo) throws Exception {
		return update(ledVo, "updateLED_TV");
	}

	public List<LED> getLED_Content(LED ledVo) throws Exception {
		return findByMap(new String[] {"id","deptcode","depthall"}, 
				new String[] {ledVo.getId(),ledVo.getDeptCode(),ledVo.getDeptHall()}, "", "", "getLED_Content");
	}
	public void addLED_Content(LED ledVo) throws Exception {
		insert(ledVo, "addLED_Content");
	}

	public Integer updateLED_Content(LED ledVo) throws Exception {
		return update(ledVo, "updateLED_Content");
	}

	public Integer delLED_TV(LED ledVo) throws Exception {
		return this.deleteById(Integer.parseInt(ledVo.getAddress()), "deleteById");
	}

	public List<LED> getMaxId() throws Exception {
		// TODO Auto-generated method stub
		return findByMap(new String[] {}, new String[] {}, "", "", "getMaxId");
	}

	public void delLED_Content(LED ledVo) throws Exception {
		// TODO Auto-generated method stub
		this.deleteById(Integer.valueOf(ledVo.getId()), "delLED_Content");
	}
	
}