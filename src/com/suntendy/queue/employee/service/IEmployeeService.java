package com.suntendy.queue.employee.service;

import java.io.File;
import java.util.List;

import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.employee.domain.Role;
import com.suntendy.queue.util.exception.SearchException;

/*******************************************************************************
 * 描述: 员工相关操作业务逻辑接口 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-24 17:32:54 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public interface IEmployeeService {

	/**
	 * 根据员工编号获取员工信息
	 * @param operNum 员工编号
	 * @return 员工信息
	 */
	public Employee getEmployeeByCode(String operNum);

	/**
	 * 保存员工信息
	 * @param employee 员工信息
	 * @throws Exception
	 */
	public void save(Employee employee) throws Exception;
	
	public String savestaff(File file,Employee employee) throws Exception;
	
	/**
	 * 获取用户信息
	 * @param employee 用户信息
	 * @return
	 */
	public List<Employee> getEmpList(Employee employee);

	/**
	 * 更新用户信息
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateEmp(File file, Employee employee);
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	public int deleteEmp(String id);

	/**
	 * 校验用户工号是否已经存在
	 * @param code
	 * @return
	 */
	public List<Employee> checkCode(Employee employee);
	
	/**
	 * 保存用户信息
	 * @param employee
	 * @return
	 */
	public String saveEmployee(Employee employee) throws Exception;
	
	/**
	 * 查询员工登录情况
	 * @param employee 查询条件
	 * @return 员工信息
	 * @throws SearchException
	 */
	public List<Employee> getLoginStateByCode(Employee employee) throws SearchException;
	
	/**
	 * 查询用户信息不包括图片
	 * @param employee
	 * @return
	 */
	public List<Employee> searchWithoutPhoto(Employee employee) throws Exception;
	
	public List<Employee> searchEmployeeByIp(Employee employee) throws SearchException;
	
	/**
	 * 校验IdNumber是否存在
	 * @param IdNumber
	 * @return
	 */
	public List<Employee> check( Employee employee) throws Exception;
	/**
	 * 删除锁定用户操作
	 * @param name
	 * @return
	 */
	public int deleteSuoEmp(String name) throws Exception;
	/**
	 * 保存角色信息
	 * @param role
	 * @return
	 */
	public String saveRole(Role role) throws Exception;
	/**
	 * 获取角色信息
	 * @param role 
	 * @return
	 */
	public List<Role> getRoleList(Role role)throws Exception;
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	public int deleteRole(String id) throws Exception;
	/**
	 * 更新用户信息
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateRole(Role role)throws Exception;
}