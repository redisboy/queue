package com.suntendy.queue.dept.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import  com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.hmd.domain.Hmd;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.exception.SearchException;
import com.suntendy.queue.window.domain.Screen;

public interface DeptService {

	/**
	 * 添加大厅信息
	 * @param dept 大厅信息
	 * @throws Exception
	 */
	public String saveDept(Dept dept) throws Exception;
	
	/**
	 * 获取大厅信息
	 * @param dept 大厅信息
	 * @return
	 */
	public List<Dept> getDeptList(Dept dept) throws Exception;
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	public int deleteDept(String id) throws Exception;
	
	/**
	 * 进入大厅基础信息修改
	 * @return
	 * @throws Exception
	 */
	public Dept toUpdateDept(Dept dept) throws Exception;
	
	/**
	 * 修改大厅基础信息
	 * @return List
	 */
	public int updateDept(Dept dept) throws Exception; 
	
//	 修改大厅基础信息
	public int updateDeptByIp(Dept dept) throws Exception;
	
	/**
	 *  查询所有部门和部门名称的集合 
	 */
	public List<Map<String,String>> findAllDeptcode() throws Exception;
	/**
	 *  根据部门(deptcode)查询大厅(depthall)及大厅名称集合
	 */
	public List<Map<String,String>> findDepthallbyDeptcode(String deptCode) throws Exception;
}