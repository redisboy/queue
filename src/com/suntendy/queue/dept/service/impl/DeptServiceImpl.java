package com.suntendy.queue.dept.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.dept.dao.DeptDao;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.SearchException;
import com.suntendy.queue.util.exception.UpdateException;
import com.suntendy.queue.util.scriptsession.Publisher;

public class DeptServiceImpl implements DeptService{
	private DeptDao deptDao;
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	//	删除大厅基础信息
	@Override
	public int deleteDept(String id) throws Exception {
		// TODO Auto-generated method stub\
		return deptDao.deleteDept(id);
	}

	//	查询大厅基础信息
	@Override
	public List<Dept> getDeptList(Dept dept) throws Exception {
		// TODO Auto-generated method stub\
		return deptDao.searchDept(dept);
	}

	//	添加大厅基础信息
	@Override
	public String saveDept(Dept dept) throws Exception {
		// TODO Auto-generated method stub
		return deptDao.saveDept(dept);
	}

	//	进入大厅基础信息页面
	public Dept toUpdateDept(Dept dept) throws Exception {
		List<Dept> list = deptDao.toUpdateDept(dept);
		Dept de = new Dept();
		if(list.size()>0){
			de = list.get(0);
		}
		return de;
	}

//		修改大厅基础信息
	public int updateDept(Dept dept)throws Exception {
		return deptDao.updateDept(dept);
	}

	@Override
	public List<Map<String, String>> findAllDeptcode() throws Exception {
		return deptDao.findAllDeptcode();
	}

	@Override
	public List<Map<String, String>> findDepthallbyDeptcode(String deptCode)throws Exception {
		// TODO Auto-generated method stub
		return deptDao.findDepthallbyDeptcode(deptCode);
	}

	@Override
	public int updateDeptByIp(Dept dept) throws Exception {
		// TODO Auto-generated method stub
		return deptDao.updateDeptByIp(dept);
	}
	
}