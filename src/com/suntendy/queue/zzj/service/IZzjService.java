package com.suntendy.queue.zzj.service;

import java.util.List;

import com.suntendy.queue.zzj.domain.Zzj;

public interface IZzjService {
	
	/**
	 * 添加自助打印机
	 * @param zzj
	 * @return
	 * @throws Exception
	 */
	public String saveZzj(Zzj zzj) throws Exception;

	/**
	 * 修改自助打印机
	 * @param zzj
	 * @return
	 * @throws Exception
	 */
	public int updateZzj(Zzj zzj) throws Exception;
	
	/**
	 * 删除自助打印机
	 * @param zzj
	 * @return
	 * @throws Exception
	 */
	public int delZzj(Zzj zzj) throws Exception;
	
	/**
	 * 查询自助打印机
	 * @param zzj
	 * @return
	 * @throws Exception
	 */
	public List<Zzj> queryZzj(Zzj zzj) throws Exception;

	/**
	 * 重置自助机
	 * @param zzj
	 * @return
	 * @throws Exception
	 */
	public int czZzj(Zzj zzj) throws Exception;
}
