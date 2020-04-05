package com.suntendy.queue.lzxx.dao;

import java.util.List;

import com.suntendy.queue.lzxx.domain.Lzxx;

public interface ILzxxDao {
	/**
	 * 查询所有领证信息
	 * @param lzxx
	 * @return
	 * @throws Exception
	 */
	public List<Lzxx> queryAllLzxx(Lzxx lzxx) throws Exception;
	
	/**
	 * 查询领证信息对应顺序号
	 * @param lzxx
	 * @return
	 * @throws Exception
	 */
	public List<Lzxx> querySxhByLzxx(Lzxx lzxx) throws Exception;

	/**
	 * 查询所有领证信息Bysxh
	 * @param lzxx
	 * @return
	 * @throws Exception
	 */
	public List<Lzxx> queryAllLzxxBysxh(Lzxx lzxx) throws Exception;
	
	/**
	 * 添加领证信息
	 * @param lzxx
	 * @throws Exception
	 */
	public void insertLzxx(Lzxx lzxx)throws Exception;
	
	/**
	 * 添加领证信息临时
	 * @param lzxx
	 * @throws Exception
	 */
	public void insertLzxxLS(Lzxx lzxx)throws Exception;
	
	/**
	 * 添加领证窗口
	 * @param lzxx
	 * @throws Exception
	 */
	public void insertLzck(Lzxx lzxx)throws Exception;
	
	/**
	 * 根据资料类型查询领证窗口
	 * @param lzxx
	 * @return
	 * @throws Exception
	 */
	public List<Lzxx> queryLzckByZllx(Lzxx lzxx) throws Exception;
	
	/**
	 * 修改领证窗口
	 * @param lzxx
	 * @throws Exception
	 */
	public void updateLzck(Lzxx lzxx) throws Exception;
	
	/**
	 * 删除领证窗口
	 * @param lzxx
	 * @throws Exception
	 */
	public void delLzck(Lzxx lzxx) throws Exception;
	
	/**
	 * 更新领证信息状态
	 * @param lzxx
	 * @throws Exception
	 */
	public void updateStatus(Lzxx lzxx)throws Exception;
}
