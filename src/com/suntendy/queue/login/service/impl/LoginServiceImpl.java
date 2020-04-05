package com.suntendy.queue.login.service.impl;

import java.util.List;

import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.login.dao.ILoginDao;
import com.suntendy.queue.login.service.ILoginService;

@SuppressWarnings("unchecked")
public class LoginServiceImpl implements ILoginService {
	
	private ILoginDao loginDAOInstance;

	public ILoginDao getLoginDAOInstance() {
		return loginDAOInstance;
	}

	public void setLoginDAOInstance(ILoginDao loginDAOInstance) {
		this.loginDAOInstance = loginDAOInstance;
	}

	public List<Employee> login(String yhdh, String mm,String deptCode,String deptHall) {
		return this.loginDAOInstance.login(yhdh, mm,deptCode,deptHall);
	}

	public List getMenuidAllByLevelid(String levelid,String id) {
		return this.loginDAOInstance.getMenuidAllByLevelid(levelid,id);
	}

	public List getMenurootidByLevelid(String levelid) {
		return this.loginDAOInstance.getMenurootidByLevelid(levelid);
	}

	@Override
	public void addLoginLog(Employee employee) throws Exception{
		this.loginDAOInstance.addLoginLog(employee);
	}

	@Override
	public List<Employee> countLogin(String yhdh, String dlip) throws Exception {
		return this.loginDAOInstance.countLogin(yhdh, dlip);
	}
}