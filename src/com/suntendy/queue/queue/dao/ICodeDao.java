package com.suntendy.queue.queue.dao;

import java.util.List;

import com.suntendy.queue.queue.domain.Code;

public interface ICodeDao {

	/**
	 * 根据代码类别获取数据
	 * @param dmlb
	 * @return
	 */
	public List<Code> getAllGredentials(String dmlb,String deptCode, String deptHall) throws Exception;

	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz, String zt,String deptCode, String deptHall) throws Exception;
	
	public List<Code> getCodeByDmlbAndSxh(String dmlb, String dmz,String zt,String deptCode, String deptHall) throws Exception;

	/**
	 * 添加窗口差评原因
	 * @return List
	 */
	public Integer addGredentialsEvaluaReason(Code code) throws Exception;

	/**
	 * 进入差评原因修改
	 * @return
	 * @throws Exception
	 */
	public List<Code> toGredentialsEvaluaReason(Code code) throws Exception;

	/**
	 * 修改窗口差评原因
	 * @return List
	 */
	public int editGredentialsEvaluaReason(Code code) throws Exception;
	
	public String jm(String jym) throws Exception;
	
	public List<Code> querySQXX(Code code) throws Exception;
	
	public void addkhdsq(Code code) throws Exception;
	
	public int delCode(String id) throws Exception;
	/**
	 * 下一个窗口业务类型
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<Code> queryForYwyy(Code code)throws Exception;
	/**
	 * 添加大厅
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Integer addDepthall(Code code) throws Exception;
	
	
	
	
	
}