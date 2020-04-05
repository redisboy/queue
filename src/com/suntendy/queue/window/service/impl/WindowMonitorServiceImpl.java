package com.suntendy.queue.window.service.impl;

import java.util.List;

import com.suntendy.queue.window.dao.IWindowMonitorDao;
import com.suntendy.queue.window.domain.WindowMonitor;
import com.suntendy.queue.window.service.IWindowMonitorService;

public class WindowMonitorServiceImpl implements IWindowMonitorService {

	private IWindowMonitorDao windowMonitordao;

	@Override
	public List<WindowMonitor> getWindowMonitor(String tjms, String ksrq,
			String jsrq, String deptCode, String deptHall, String barid,
			String xm) throws Exception {
		// TODO Auto-generated method stub
		return windowMonitordao.getWindowMonitor(tjms, ksrq, jsrq, deptCode, deptHall, barid, xm);
	}

	public IWindowMonitorDao getWindowMonitordao() {
		return windowMonitordao;
	}

	public void setWindowMonitordao(IWindowMonitorDao windowMonitordao) {
		this.windowMonitordao = windowMonitordao;
	}
	
	

	
}
