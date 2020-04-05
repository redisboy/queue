package com.suntendy.queue.smallwindow.service.impl;

import java.util.List;

import com.suntendy.queue.smallwindow.dao.ISmallWindowDao;
import com.suntendy.queue.smallwindow.domain.SmallWindow;
import com.suntendy.queue.smallwindow.service.ISmallWindowService;

public class smallWindowServiceImpl implements ISmallWindowService {

	private ISmallWindowDao smallWindowDao;
	
	public ISmallWindowDao getSmallWindowDao() {
		return smallWindowDao;
	}

	public void setSmallWindowDao(ISmallWindowDao smallWindowDao) {
		this.smallWindowDao = smallWindowDao;
	}


	public List<SmallWindow> showBtn(SmallWindow smallWindow) throws Exception {
		return smallWindowDao.showBtn(smallWindow);
	}

	
	public void updateBtnById(SmallWindow smallWindow) throws Exception {
		String[] ids = smallWindow.getSxh();
		String[] names = smallWindow.getMc();
		String[] codes = smallWindow.getCode();
		String[] events = smallWindow.getEventAll();
		for(int i=0;i<codes.length;i++){
			smallWindow.setId(ids[i]);
			smallWindow.setBtnName(names[i]);
			smallWindow.setStatus(codes[i]);
			smallWindow.setEvent(events[i]);
			smallWindowDao.updateBtnById(smallWindow);
		}
		
	}

}
