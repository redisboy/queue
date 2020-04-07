package com.suntendy.queue.dept.dao.impl;

import java.util.List;
import java.util.Map;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.dept.dao.DeptDao;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.exception.SaveException;
import com.suntendy.queue.util.exception.SearchException;
import com.suntendy.queue.util.exception.UpdateException;
import com.suntendy.queue.window.domain.Screen;

public class DeptDaoImpl extends BaseDao<Dept, String> implements DeptDao {

//	查询大厅基础信息
	@Override
	public List<Dept> searchDept(Dept dept) throws Exception {
		String[] properties = {"deptname","depthall","deptcode","serversip","deptcodename"};
		Object[] propertyValues = {dept.getDeptname(),dept.getDepthall(),dept.getDeptcode(),dept.getServersip(),dept.getDeptcodename()};
		return this.findByMap(properties, propertyValues, "t.id", "asc", BaseDao.SELECTBYMAP);
	}
	
//	删除大厅基础信息
	@Override
	public int deleteDept(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deleteById(id, BaseDao.DELETEBYID);
	}

//	添加大厅基础信息
	@Override
	public String saveDept(Dept dept) throws Exception {
		// TODO Auto-generated method stub
		return this.insert(dept, "saveDept");
	}

//	进入修改大厅基础信息页面
	public List<Dept> toUpdateDept(Dept dept) throws Exception {
		return (List<Dept>)this.getSqlMapClientTemplate().queryForList("Dept.findById", dept.getId());
	}

//	修改大厅基础信息
	public int updateDept(Dept dept) throws Exception {
		return this.update(dept, BaseDao.UPDATE);
	}
	
	public List<Map<String,String>> findAllDeptcode() throws Exception{
		return (List<Map<String,String>>)this.getSqlMapClientTemplate().queryForList("Dept.findAllDeptcode");
	}
	
	public List<Map<String,String>> findDepthallbyDeptcode(String deptCode) throws Exception{
		return (List<Map<String,String>>)this.getSqlMapClientTemplate().queryForList("Dept.findDepthallbyDeptcode",deptCode);
	}

	@Override
	public int updateDeptByIp(Dept dept) throws Exception {
		return this.getSqlMapClientTemplate().update("Dept.updateDeptByIp", dept);
	}
}