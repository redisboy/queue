package com.suntendy.queue.login.service;

import java.util.List;

import com.suntendy.queue.employee.domain.Employee;

@SuppressWarnings("unchecked")
public interface ILoginService {
	
	/**
	 * 登陆验证
	 */
	public abstract List<Employee> login(String yhdh, String mm,String deptCode,String deptHall);
	
	/**
	 * 登录日志
	 * @param yhdh
	 * @param mm
	 * @param deptCode
	 * @param deptHall
	 * @param ip
	 */
	public void addLoginLog(Employee employee) throws Exception;
	
	public List<Employee> countLogin(String yhdh,String dlip) throws Exception;
	
	/**
	 * 通过levelid获得根节点menuid
	 */
	public abstract List getMenurootidByLevelid(String levelid);
	
	/**
	 * 通过levelid获得全部节点
	 */
	public abstract List getMenuidAllByLevelid(String levelid,String id);	
}