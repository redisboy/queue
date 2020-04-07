package com.suntendy.queue.business.dao;

import java.util.List;

import com.suntendy.queue.business.domain.Business;

public interface IBusinessDao {
       
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
			String bkbl,String outflag,String deptCode,String deptHall,
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
	 * @returns
	 */
	public int updatewaitingareaByID(String id, String waitingarea) throws Exception;
	/**
	 * 根据业务类型获取等待区域
	 * @param business
	 * @return
	 * @throws Exception
	 */
	public List<Business> queryWaitingareaByID(Business business) throws Exception;
	/**
	 * 根据顺序号获取等待区域
	 * @param business
	 * @return
	 * @throws Exception
	 */
	public List<Business> queryWaitingareaByNo(String number) throws Exception;
	/**
	 * 根据业务类型查询未叫号量
	 * @param businessType
	 * @return
	 */
	public String queryYWNoCallNumber(String businessType);
	/**
	 * 根据队列查询未叫号量
	 * @param queueCode
	 * @return
	 */
	public String queryDLNoCallNumber(String queueCode);
	/**
	 * 根据预约业务的名称返回叫号系统业务的信息
	 * @param businessName
	 * @param deptHall 
	 * @param deptCode 
	 */
	public List<Business> selectByYYYWMC(String businessName, String deptCode, String deptHall)throws Exception;
	/**
	 * 删除业务时查询当天是否存在该业务
	 */
	public Business queryBusiness(String id,String deptCode, String deptHall) throws Exception;
}