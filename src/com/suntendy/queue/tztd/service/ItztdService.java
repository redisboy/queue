package com.suntendy.queue.tztd.service;

import java.util.List;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.hmd.domain.Hmd;
import com.suntendy.queue.tztd.domain.TztdVO;


public interface ItztdService {

	
	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	public List<TztdVO> queryAllTztd(TztdVO tztd) throws Exception;
	/**
	 * 查询最大ID
	 * @return
	 * @throws Exception
	 */
	public TztdVO queryMaxId(TztdVO tztd) throws Exception;
	
	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	public String saveTztd(TztdVO tztd) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void delTztd(TztdVO tztd) throws Exception;
	
	/**
	 * 修改
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void updateTztd(TztdVO tztd) throws Exception;
	
	
	public List<TztdVO> queryTztd(TztdVO tztd) throws Exception;
}
