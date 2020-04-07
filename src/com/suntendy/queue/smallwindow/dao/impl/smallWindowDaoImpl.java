package com.suntendy.queue.smallwindow.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.smallwindow.dao.ISmallWindowDao;
import com.suntendy.queue.smallwindow.domain.SmallWindow;

public class smallWindowDaoImpl extends BaseDao<SmallWindow,String> implements ISmallWindowDao {
	

	public List<SmallWindow> showBtn(SmallWindow smallWindow) throws Exception {
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "showBtn");
	}

	public void updateBtnById(SmallWindow smallWindow) throws Exception {
		String[] properties = {"id","btnName","status","event"};
		String[] propertyValues = {smallWindow.getId(),smallWindow.getBtnName(),smallWindow.getStatus(),smallWindow.getEvent()};
		this.updateByMap(smallWindow.getId(), properties, propertyValues, "updateBtnById");
		
	}


}
