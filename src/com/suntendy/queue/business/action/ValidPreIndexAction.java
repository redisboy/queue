package com.suntendy.queue.business.action;

import java.util.Collection;
import java.util.List;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.util.cache.CacheManager;

/*****************************************************************
 * 描述: ajxa验证preindex是否可用 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 施海波 <br>
 * 创建日期: 2013-10-15 14:53:08 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
******************************************************************/
public class ValidPreIndexAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IBusinessService businessService;
	private String preindex;     //ajax 传过来的preindex参数
	private String  business;    //ajax 传过来的business参数
	private String  queuecode;    //ajax 传过来的code参数(队列编号)
	
	/**
	 * 验证前缀
	 * @return
	 * @throws Exception
	 */
	public String checkPreIndex() throws Exception{
		String ok = "0";
		Collection<Queue> queueList = CacheManager.getInstance().getQueueCache().values();
		for (Queue queue : queueList) {
			if (preindex.equals(queue.getPrenum())) {
				ok = "1";
				break;
			}
		}
		
		this.getResponse().getWriter().print(ok);
		return null;
	}
	/**
	 * 验证code
	 * @return
	 * @throws Exception
	 */
	public String checkCode() throws Exception{
		String ok = "0";
		Collection<Queue> queueList = CacheManager.getInstance().getQueueCache().values();
		for (Queue queue : queueList) {
			if (queuecode.equals(queue.getCode())) {
				ok = "1";
				break;
			}
		}
		
		this.getResponse().getWriter().print(ok);
		return null;
	}
	/**
	 * 验证业务类型
	 * @return
	 * @throws Exception
	 */
	public String checkBusiness() throws Exception{
		String ok = "0";
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList("", "",business.trim(),"",deptCode, deptHall);
		if(null!=businessList && !businessList.isEmpty()){
			ok = "1";  //存在business不可用
		}
		this.getResponse().getWriter().print(ok);
		return ok;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public String getPreindex() {
		return preindex;
	}

	public void setPreindex(String preindex) {
		this.preindex = preindex;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	public String getQueuecode() {
		return queuecode;
	}
	public void setQueuecode(String queuecode) {
		this.queuecode = queuecode;
	}
}