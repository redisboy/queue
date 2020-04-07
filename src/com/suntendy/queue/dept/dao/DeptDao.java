package com.suntendy.queue.dept.dao;

import java.util.List;
import java.util.Map;

import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.exception.SaveException;
import com.suntendy.queue.util.exception.SearchException;
import com.suntendy.queue.util.exception.UpdateException;
import com.suntendy.queue.window.domain.Screen;

public interface DeptDao {
	
//		查询大厅基础信息
	public List<Dept> searchDept(Dept dept) throws Exception;
	
//	 添加大厅基础信息
	public String saveDept(Dept dept) throws Exception;
	
//	 进入修改大厅基础信息页面
	public List<Dept> toUpdateDept(Dept dept) throws Exception;
	
//	 修改大厅基础信息
	public int updateDept(Dept dept) throws Exception;
	
//	 修改大厅基础信息
	public int updateDeptByIp(Dept dept) throws Exception;
	
//	 删除大厅基础信息
	public int deleteDept(String id) throws Exception;
	
	public List<Map<String, String>> findAllDeptcode()throws Exception;
	
	public List<Map<String, String>> findDepthallbyDeptcode(String deptCode)throws Exception;
}

