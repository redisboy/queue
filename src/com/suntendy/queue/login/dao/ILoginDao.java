package com.suntendy.queue.login.dao;

import java.util.List;

import com.suntendy.queue.employee.domain.Employee;

@SuppressWarnings("unchecked")
public interface ILoginDao {
	
	/**
	 * 登录
	 */
	public abstract List<Employee> login(String yhdh, String mm,String deptCode,String deptHall);
	
	public void addLoginLog(Employee employee) throws Exception;
	
	public List<Employee> countLogin(String yhdh,String dlip) throws Exception;
	
	
	/**
	 * ͨ��levelid��ø通过levelid获得根节点menuid
	 */
	public abstract List getMenurootidByLevelid(String levelid);
	
	/**
	 * ͨ��levelid���ȫ���通过levelid获得所有节点
	 */
	public abstract List getMenuidAllByLevelid(String levelid,String id);

}