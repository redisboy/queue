package com.suntendy.queue.login.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.login.dao.ILoginDao;

@SuppressWarnings("unchecked")
public class LoginDaoImpl extends BaseDao<Employee, Integer> implements ILoginDao {

	//登陆验证
	public List<Employee> login(String code, String password,String deptCode,String deptHall) {
		String[] properties = { "code","password","deptCode","deptHall"};
		String[] propertyValues = { code ,password,deptCode,deptHall};
		try {
			return this.findByMap(properties, propertyValues, "", "", "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//通过levelid获得根节点menuid
	public List getMenurootidByLevelid(String levelid) {
		String[] properties = { "levelid"};
		String[] propertyValues = {levelid};
		try {
			return this.findByMap(properties, propertyValues, "", "", "getMenurootid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//通过levelid获得全部节点
	public List getMenuidAllByLevelid(String levelid,String id) {
		String[] properties = { "levelid","flagid"};
		String[] propertyValues = {levelid,id};
		try {
			return this.findByMap(properties, propertyValues, "", "", "getMenuidAllByLevelid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addLoginLog(Employee employee) throws Exception{
		this.insert(employee, "addLoginLog");
	}

	@Override
	public List<Employee> countLogin(String yhdh,String dlip) throws Exception {
		String[] properties = { "yhdh","dlip"};
		String[] propertyValues = {yhdh,dlip};
			return this.findByMap(properties, propertyValues, "", "", "countLogin");
	}
}