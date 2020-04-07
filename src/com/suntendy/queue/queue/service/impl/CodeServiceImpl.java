package com.suntendy.queue.queue.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.suntendy.queue.queue.dao.ICodeDao;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.service.ICodeService;

public class CodeServiceImpl implements ICodeService {

	private ICodeDao codeDao;

	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public List<Code> getAllGredentials(String dmlb,String deptCode, String deptHall) {
		try {
			return codeDao.getAllGredentials(dmlb,deptCode,deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Code>();
	}
	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz,String zt,String deptCode, String deptHall) {
		try {
			return codeDao.getCodeByDmlbAndDmz(dmlb, dmz,zt,deptCode,deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Code>();
	}
	public List<Code> getCodeByDmlbAndSxh(String dmlb, String dmz,String zt,String deptCode, String deptHall) {
		try {
			return codeDao.getCodeByDmlbAndSxh(dmlb, dmz, zt, deptCode, deptHall);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Code>();
	}
	
	/**
	 * 添加窗口差评原因
	 * @return List
	 */
	public Integer addGredentialsEvaluaReason(Code code)throws Exception {
		return codeDao.addGredentialsEvaluaReason(code);
	}
	/**
	 * 进入差评原因修改
	 * @return
	 * @throws Exception
	 */
	public Code toGredentialsEvaluaReason(Code code) throws Exception {
		List<Code> list = codeDao.toGredentialsEvaluaReason(code);
		Code cd=new Code();
		if(list.size()>0){
				cd = list.get(0);
		}
		return cd;
	}
	/**
	 * 修改窗口差评原因
	 * @return List
	 */
	public int editGredentialsEvaluaReason(Code code)throws Exception {
		return codeDao.editGredentialsEvaluaReason(code);
	}
	
	public String jm(String jym) throws Exception {
		return codeDao.jm(jym);
	}
	public List<Code> querySQXX(Code code) throws Exception{
		return codeDao.querySQXX(code);
	}
	/**
	 * 添加大厅
	 * @return
	 * @throws Exception
	 */
	public Integer addDepthall(Code code) throws Exception {
		return codeDao.addDepthall(code);
	}

	public void delCode(String id) throws Exception {
		// TODO Auto-generated method stub
		codeDao.delCode(id);
	}

	public static String YZFLAG ="0";
	public String yzspxx(String flag) {
		YZFLAG = flag;
		return flag;
	}

	@Override
	public void addkhdsq(Code code) throws Exception {
		codeDao.addkhdsq(code);
	}
}