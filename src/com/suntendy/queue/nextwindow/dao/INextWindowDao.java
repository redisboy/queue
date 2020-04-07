package com.suntendy.queue.nextwindow.dao;

import java.util.List;

import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.util.exception.SaveException;



public interface INextWindowDao {

	/**
	 * 保存下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public String saveNWindow(NextWindow nWindow) throws Exception; 
	
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
	public NextWindow queryById(NextWindow nWindow)throws Exception;
	
	/**
	 * 根据ID修改下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public int updateById(NextWindow nWindow)throws Exception;
	
	/**
	 * 根据ID删除下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public int deleteById(NextWindow nWindow)throws Exception;
	
	/**
	 * 根据系统类型、业务类型、业务原因查询，是否存在重复数据
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheck(NextWindow nWindow)throws Exception;
	
	/**
	 * 为窗口提示信息查询
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheckResult(NextWindow nWindow)throws Exception;
}
