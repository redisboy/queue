package com.suntendy.queue.queue.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.queue.dao.ICodeDao;
import com.suntendy.queue.queue.domain.Code;

public class CodeDaoImpl extends BaseDao<Code, Integer> implements ICodeDao {

	public List<Code> getAllGredentials(String dmlb,String deptCode, String deptHall) throws Exception {
		String[] properties = { "dmlb", "deptCode", "deptHall" };
		String[] propertyValues = { dmlb,deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "t.dmz", "ASC", BaseDao.SELECTBYMAP);
	}
	
	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz,String zt,String deptCode, String deptHall) throws Exception {
		String[] properties = { "dmlb", "dmz", "zt", "deptCode", "deptHall" };
		String[] propertyValues = { dmlb, dmz, zt,deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "t.dmz", "ASC", BaseDao.SELECTBYMAP);
	}
	
	public List<Code> getCodeByDmlbAndSxh(String dmlb, String dmz,String zt,String deptCode, String deptHall) throws Exception {
		String[] properties = { "dmlb", "sxh", "zt", "deptCode", "deptHall" };
		String[] propertyValues = { dmlb, dmz, zt,deptCode, deptHall };
		return this.findByMap(properties, propertyValues, "t.dmz", "ASC", BaseDao.SELECTBYMAP);
	}

	/**
	 * 添加窗口差评原因
	 * @return List
	 */
	public Integer addGredentialsEvaluaReason(Code code) throws Exception {
		return this.insert(code, "addGredentialsEvaluaReason");
	}

	/**
	 * 进入差评原因修改
	 * @return
	 * @throws Exception
	 */
	public List<Code> toGredentialsEvaluaReason(Code code) throws Exception {
		String[] properties = { "dmlb", "dmz", "deptCode", "deptHall" };
		String[] propertyValues = { code.getDmlb(), code.getDmz(),code.getDeptcode(),code.getDepthall() };
		return this.findByMap(properties, propertyValues, "t.dmz", "ASC", BaseDao.SELECTBYMAP);
	}
	/**
	 * 修改窗口差评原因
	 * @return
	 * @throws Exception
	 */
	public int editGredentialsEvaluaReason(Code code) throws Exception {
		String[] properties = { "dmlb", "dmsm1", "dmz", "zt" , "deptcode", "depthall"};
		String[] propertyValues = { code.getDmlb(), code.getDmsm1(), code.getDmz(), code.getZt(),code.getDeptcode(),code.getDepthall() };
		return this.updateByMap(Integer.valueOf(code.getDmz()), properties, propertyValues, "editGredentialsEvaluaReason");
	}
	
	public String jm(String jym) throws Exception {
		String[] properties = { "jym" };
		String[] propertyValues = { jym };
		List<Code> datas = this.findByMap(properties, propertyValues, "", "", "jm");
		if (null != datas && !datas.isEmpty()) {
			return datas.get(0).getDm();
		}
		return "";
	}
	
	public List<Code> querySQXX(Code code) throws Exception{
		String[] properties = { "ip","deptcode","depthall"};
		String[] propertyValues = { code.getIp(),code.getDeptcode(),code.getDepthall() };
		return this.findByMap(properties, propertyValues,"","","querySQXX");
	}
	
	public List<Code> queryForYwyy(Code code) throws Exception {
		String[] properties = { "dmlb", "dmsm1", "dmz", "zt" , "deptcode", "depthall"};
		String[] propertyValues = { code.getDmlb(), code.getDmsm1(), code.getDmz(), code.getZt(),code.getDeptcode(),code.getDepthall() };
		return this.findByMap(properties, propertyValues,"","","queryForYwyy");
	}

	@Override
	public Integer addDepthall(Code code) throws Exception {
		return this.insert(code, "addDepthall");
	}

	public int delCode(String id) throws Exception {
		return this.deleteById(Integer.parseInt(id), "delCode");
	}

	@Override
	public void addkhdsq(Code code) throws Exception {
		this.insert(code, "addkhdsq");
	}
}