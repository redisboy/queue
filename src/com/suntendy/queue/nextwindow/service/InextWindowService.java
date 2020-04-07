package com.suntendy.queue.nextwindow.service;

import java.util.List;

import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.queue.domain.Code;

public interface InextWindowService {

	/**
	 * 保存下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public String saveNWindow(NextWindow nwindow)throws Exception;
	
	/**
	 * 查询下一步提示信息
	 * @return
	 * @throws Exception
	 */
	public List<NextWindow> queryAllNWindow(NextWindow nWindow)throws Exception;
	
	/**
	 * 根据ID查询下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryById(NextWindow nwindow)throws Exception;
	
	/**
	 * 根据ID修改下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public void updateById(NextWindow nwindow)throws Exception;
	
	/**
	 * 根据ID删除下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public void deleteById(NextWindow nwindow)throws Exception;
	
	/**
	 * 根据系统类型查询业务类型
	 * @param dmlb
	 * @return
	 * @throws Exception
	 */
	public List<Code> getAllGredentials(String dmlb)throws Exception;

	public List<Code> getCodeByDmlbAndDmz(String dmlb, String dmz, String zt,String deptCode, String deptHall)throws Exception;
	
	/**
	 * 根据系统类型和业务类型查询业务原因
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<Code> queryForYwyy(Code code)throws Exception;
	
	/**
	 * 根据系统类型、业务类型、业务原因查询，是否存在重复数据
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheck(NextWindow nwindow)throws Exception;
	
	/**
	 * 为窗口提示信息查询
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheckResult(NextWindow nwindow)throws Exception;
}
