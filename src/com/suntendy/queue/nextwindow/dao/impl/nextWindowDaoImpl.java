package com.suntendy.queue.nextwindow.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.nextwindow.dao.INextWindowDao;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.util.exception.SaveException;

public class nextWindowDaoImpl extends BaseDao<NextWindow,String> implements INextWindowDao {
	
	/**
	 * 保存下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public String saveNWindow(NextWindow nWindow) throws Exception {
		return insert(nWindow, "saveNWindow");
	}

	/**
	 * 查询下一步提示信息
	 * @return
	 * @throws Exception
	 */
	public List<NextWindow> queryAllNWindow(NextWindow nWindow) throws Exception {
		String[] properties = {"deptCode","deptHall"};
		String[] propertyValues = {nWindow.getDeptCode(),nWindow.getDeptHall()};
		return this.findByMap(properties, propertyValues, "", "", "queryAll");
	}

	/**
	 * 根据ID修改下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public int updateById(NextWindow nWindow) throws Exception {
		String[] properties = {"id","nextwindowval","deptCode","deptHall"};
		String[] propertyValues = {nWindow.getId(),nWindow.getNextwindowval(),nWindow.getDeptCode(),nWindow.getDeptHall()};
		return this.updateByMap(nWindow.getId(), properties, propertyValues, "updateById");
	}

	/**
	 * 根据ID查询下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryById(NextWindow nWindow) throws Exception {
		String[] properties = {"id","deptCode","deptHall"};
		String[] propertyValues = {nWindow.getId(),nWindow.getDeptCode(),nWindow.getDeptHall()};
		List<NextWindow> list = this.findByMap(properties, propertyValues, "", "", "queryById");
		return list.get(0);
	}

	/**
	 * 根据ID删除下一步提示信息
	 * @param nWindow
	 * @return
	 * @throws Exception
	 */
	public int deleteById(NextWindow nWindow) throws Exception {
		return this.deleteById(nWindow.getId(), "deleteById");
	}

	/**
	 * 根据系统类型、业务类型、业务原因查询，是否存在重复数据
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheck(NextWindow nWindow) throws Exception {
		String[] properties = {"dmlb","dmz","ywyybh","deptCode","deptHall"};
		String[] propertyValues = {nWindow.getDmlb(),nWindow.getDmz(),nWindow.getYwyybh(),nWindow.getDeptCode(),nWindow.getDeptHall()};
		List<NextWindow> list = this.findByMap(properties, propertyValues,"","","queryForCheck");
		NextWindow nt = null; 
		if(null!= list && list.size()>0){
			nt = list.get(0);
		}
		return nt;
	}

	/**
	 * 为窗口提示信息查询
	 * @param nwindow
	 * @return
	 * @throws Exception
	 */
	public NextWindow queryForCheckResult(NextWindow nWindow) throws Exception {
		String[] properties = {"dmlb","dmz","ywyybh","deptCode","deptHall"};
		String[] propertyValues = {nWindow.getDmlb(),nWindow.getDmz(),nWindow.getYwyybh(),nWindow.getDeptCode(),nWindow.getDeptHall()};
		List<NextWindow> list = this.findByMap(properties, propertyValues, "", "", "queryForCheckResult");
		NextWindow nt = null; 
		if(null!= list && list.size()>0){
			nt = list.get(0);
		}
		return nt;
	}

}
