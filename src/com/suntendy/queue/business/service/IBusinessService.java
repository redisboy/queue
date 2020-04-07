package com.suntendy.queue.business.service;

import java.util.List;

import com.suntendy.queue.business.domain.Business;

public interface IBusinessService {

	/**
	 * 获取业务类型
	 * @return
	 */
	public List<Business> getBusinessList(String id,String preIndex,String business,String queueCode,String deptCode,String deptHall)throws Exception;
	/**
	 * 查询等待区域
	 * @return
	 */
	public List<Business> getGroupByWaitSrea()throws Exception;
	
	/**
	 * 添加业务类型
	 * @param yyywmc 
	 * @return
	 */
	public String addBusiness(String business, String queueCode,
			String preindex,String isOpenTztd,String isOpenZhiWen,
			String bkbl,String outflag, String deptCode,String deptHall,
			String ywl,String help_info,String managemin,String biaodan,
			String yyywmc,String bdywmc,String liuzhuan,String isautolz)throws Exception;

	/**
	 * 删除业务类型
	 * @return
	 */
	public int delBusiness(String id)throws Exception;
	/**
	 * 更改业务类型
	 * @param yyywmc 
	 * @return
	 */
	public int updateBusinessByID(String id, String business, String queueCode,
			String preindex,String isOpenTztd, String isOpenZhiWen,String bkbl,
			String outflag,String ywl,String help_info,String managemin,
			String biaodan, String yyywmc,String bdywmc,String liuzhuan,String isautolz) throws Exception;
	/**
	 * 更改等待区域
	 * @return
	 */
	public int updatewaitingareaByID(String id, String waitingarea) throws Exception;
	/**
	 * 根据预约业务的名称返回叫号系统业务的信息
	 * @param yyywmc
	 * @param deptHall 
	 * @param deptCode 
	 */
	public List<Business> selectByYYYWMC(String yyywmc, String deptCode, String deptHall) throws Exception;
	/**
	 * 删除业务时查询当天是否存在该业务
	 */
	public Business queryBusiness(String id,String deptCode, String deptHall) throws Exception;
}