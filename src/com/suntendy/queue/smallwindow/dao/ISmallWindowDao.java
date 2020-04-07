package com.suntendy.queue.smallwindow.dao;

import java.util.List;

import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.smallwindow.domain.SmallWindow;
import com.suntendy.queue.util.exception.SaveException;



public interface ISmallWindowDao {

	/**
	 * 查询按钮信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<SmallWindow> showBtn(SmallWindow smallWindow)throws Exception;
	
	/**
	 * 根据ID修改按钮信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public void updateBtnById(SmallWindow smallWindow)throws Exception;
}
