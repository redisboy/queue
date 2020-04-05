package com.suntendy.queue.employee.dao;

import java.util.List;

import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.employee.domain.Role;
import com.suntendy.queue.util.exception.SaveException;
import com.suntendy.queue.util.exception.SearchException;
import com.suntendy.queue.util.exception.UpdateException;

/*******************************************************************************
 * 描述: 员工相关操作业务数据库操作接口 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-10-09 17:10:20 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public interface IEmployeeDao {

	/**
	 * 查询员工信息
	 * @param employee 查询条件
	 * @return 员工信息
	 * @throws SearchException
	 */
	public List<Employee> searchEmployee(Employee employee) throws SearchException;
	
	/**
	 * 查询员工信息
	 * @param employee 查询条件
	 * @return 员工信息
	 * @throws SearchException
	 */
	public List<Employee> searchEmployeeByIp(Employee employee) throws SearchException;
	
	/**
	 * 保存员工信息
	 * @param employee 员工信息
	 * @return id
	 * @throws SaveException
	 */
	public String save(Employee employee) throws SaveException;
	
	public String savestaff(Employee employee) throws SaveException;
	
	/**
	 * 保存用户登录状态
	 * @param employee 员工信息
	 * @throws SaveException
	 */
	public void saveLoginStatus(Employee employee) throws SaveException;
	
	/**
	 * 更新用户信息
	 * @param employee
	 * @return
	 * @throws UpdateException
	 */
	public int updateEmp(Employee employee) throws UpdateException;
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteEmp(String id) throws Exception;

	/**
	 * 校验是否存在
	 * @param code
	 * @return
	 */
	public List<Employee> checkCode(Employee employee) throws SearchException;

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
	
	
	/**
	 * 校验是否存在
	 * @param IdNumber
	 * @return
	 */
	public List<Employee> check(Employee employee) throws Exception;
	/**
	 * 删除锁定用户操作
	 * @param name
	 * @return
	 */
	public int deleteSuoEmp(String name) throws Exception;
	/**
	 * 保存用户信息
	 * @param employee
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
