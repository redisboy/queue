package com.suntendy.queue.count.service.impl;

import java.util.List;
import com.suntendy.queue.count.dao.IEmployeeWaitCountDao;
import com.suntendy.queue.count.domain.EmployeeWaitCount;
import com.suntendy.queue.count.service.IEmployeeWaitCountService;

public class EmployeeWaitCountServiceImpl implements IEmployeeWaitCountService{
	private IEmployeeWaitCountDao employeeWaitCountDao;
	
	public List<EmployeeWaitCount> employeeWaitCount(String tjms, String ksrq, String jsrq,String deptCode,String deptHall) throws Exception {
		List<EmployeeWaitCount> datas = employeeWaitCountDao.employeeWaitCount(tjms, ksrq, jsrq,deptCode,deptHall);
		for (EmployeeWaitCount detail : datas) {
			if (detail.getAve_waittime().startsWith(".")) {
				detail.setAve_waittime("0" + detail.getAve_waittime());
			}
			if (detail.getAve_worktime().startsWith(".")) {
				detail.setAve_worktime("0" + detail.getAve_worktime());
			}
		}
		return datas;
	}
	
	@Override
	public List<EmployeeWaitCount> employeeCount() throws Exception {
		return this.employeeWaitCountDao.employeeCount();
	}

	public IEmployeeWaitCountDao getEmployeeWaitCountDao() {
		return employeeWaitCountDao;
	}

	public void setEmployeeWaitCountDao(IEmployeeWaitCountDao iEmployeeWaitCountDao) {
		this.employeeWaitCountDao = iEmployeeWaitCountDao;
	}

}
