package com.suntendy.queue.queue.service;

import java.util.List;

import com.suntendy.queue.queue.domain.Code;

public interface ICodeService {

	/**
	 * 根据代码类别获取数据
	 * @param dmlb
	 * @return
	 */
	public List<Code> getAllGredentials(String dmlb,String deptCode, String deptHall);

	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz, String zt,String deptCode, String deptHall);
	
	public List<Code> getCodeByDmlbAndSxh(String dmlb, String dmz,String zt,String deptCode, String deptHall);

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
	public Code toGredentialsEvaluaReason(Code code) throws Exception;

	/**
	 * 修改窗口差评原因
	 * @return List
	 */
	public int editGredentialsEvaluaReason(Code code) throws Exception;
	
	public String jm(String jym) throws Exception;
	
	/**
	 * 查询授权信息
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<Code> querySQXX(Code code) throws Exception;
	
	/**
	 * 添加授权信息
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public void addkhdsq(Code code) throws Exception;
	
	
	public void delCode(String id) throws Exception;
	
	/**
	 * 添加大厅
	 * @return
	 * @throws Exception
	 */
	public Integer addDepthall(Code code) throws Exception;
	/**
	 * 验证双屏信息
	 * @param flag
	 * @return
	 */
	public String yzspxx(String flag);
	
	
}