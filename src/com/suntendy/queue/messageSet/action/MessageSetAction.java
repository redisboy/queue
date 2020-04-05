package com.suntendy.queue.messageSet.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.messageSet.domain.Message;
import com.suntendy.queue.messageSet.service.IMessageSetService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.Screen;
import com.suntendy.queue.window.service.ISetWindowService;
import com.suntendy.queue.queue.domain.Number;

public class MessageSetAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IMessageSetService messageSetService;
	private INumberService numberService;
	private ISetWindowService setWindowService;
	public IMessageSetService getMessageSetService() {
		return messageSetService;
	}

	public void setMessageSetService(IMessageSetService messageSetService) {
		this.messageSetService = messageSetService;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	public ISetWindowService getSetWindowService() {
		return setWindowService;
	}

	public void setSetWindowService(ISetWindowService setWindowService) {
		this.setWindowService = setWindowService;
	}

	/**
	 * 获取短信内容
	 * @return
	 * @throws Exception
	 */
	public String getMessage() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Message> messageInfo = messageSetService.getMessage(deptCode, deptHall);
		if (!messageInfo.isEmpty()) {
			Message messageVo = messageInfo.get(0);
			request.setAttribute("messageVo", messageVo);
		}
		return SUCCESS;
	}

	/**
	 * 更改短信格式
	 * @return
	 * @throws Exception
	 */
	public String MessageSet() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		Message message = new Message();
		message.setId(request.getParameter("id"));
		message.setContext(request.getParameter("code"));
		message.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		message.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			messageSetService.MessageSet(message);
			request.setAttribute("msg", "短信设置成功！");
		} catch (Exception e) {
			e.getStackTrace();
			request.setAttribute("msg", "短信设置失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "messageSet.action");
		return SUCCESS;
	}
	
	
	
	/**
	 * 重置短信数据
	 * 
	 */
	public void ResetData() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		
		Message message = new Message();
		message.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		message.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			messageSetService.resetDate();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 获取并保存短信信息
	 */
	public void MessageDate() throws Exception{
		int avetime = 0;
		Screen screen = new Screen();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		//清空表数据
		messageSetService.resetDate();
		//查询当天号码
		List<Number> numberwj = numberService.dayNumber(deptCode, deptHall);
		for(Number num:numberwj){
//			System.out.println("票"+num.getId()+"队列"+num.getQueueCode()+"编号:"+num.getBusinessType()+"手机:"+num.getPhoneNumber());
				Business bus=queryById(num.getBusinessType());
				String busname=bus.getName();
				String phonenumber=num.getPhoneNumber();
		List<Integer> dlist=new ArrayList<Integer>();
		List<Screen> screenStatus = setWindowService.searchBar(screen);
		if (null != screenStatus && screenStatus.size() > 0) {
			for (Screen sct : screenStatus) {
				if (null != sct.getQueueId()) {
					String[] businessArray = sct.getQueueId()
							.split(",");
					for (String busType : businessArray) {
						// 窗口是否存在该业务
						if (busType.equals(num.getBusinessType())) {
							// 判断优先级
							if (StringUtils.isNotEmpty(sct.getPriority())) {
								String[] busArray = sct.getPriority().split(";");
								for(int i=0;i<busArray.length;i++){
									//判断是否等于该业务
									if(busArray[i].indexOf(",")!=-1 || busArray[i].equals(num.getBusinessType())){
									int rs=waitCount(num.getId(), num.getQueueCode(), deptCode, deptHall);
									int ave=waitTime(num.getQueueCode(), deptCode, deptHall);
										avetime=ave*rs+avetime;
										dlist.add(avetime);
										break;
									}else{
									// avetime = busArray[i]的平均时间   * 对应的人数+avetime  
										Business busin = queryById(busArray[i]);
										String code=busin.getQueueCode();
										int rs=waitCount(null,code, deptCode, deptHall);
										int ave=waitTime(code, deptCode, deptHall);
										avetime=ave*rs+avetime;
									}
									}
								}else{
									//无优先级
									int rs=waitCount(num.getId(), num.getQueueCode(), deptCode, deptHall);
									int ave=waitTime(num.getQueueCode(), deptCode, deptHall);
//									System.out.println("无优先级人数:"+rs);
									avetime=ave*rs;
									dlist.add(avetime);
									break;
								}
							}
						} 
					}
				}
			}
		avetime=Collections.min(dlist);
//		for(Integer list:dlist){
//			System.out.println("该业务所有窗口的办理时间:"+list);
//		}
//		System.out.println("最小时间:"+avetime);
		int stTime =avetime*8/9;
		String former=String.valueOf(stTime);
		String latter=String.valueOf(avetime);
		String waitTime=former+"-"+latter;
		avetime=0;
		messageSetService.saveMesDate(busname, waitTime, phonenumber, deptHall, deptCode);
		} 
	}
	
	/**
	 * 根据队列 查询等待人数
	 * @param id
	 * @param code
	 * @param deptCode
	 * @param deptHall
	 * @return 
	 */
	public int waitCount(String id,String code,String deptCode,String deptHall){
	String queueCount = null;
	int rs=0;
	
	try {
		queueCount = numberService.findByWait(id,code, deptCode, deptHall);
		if(null!=queueCount && queueCount!=""){
		rs=  Integer.parseInt(queueCount);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return rs;
	}
	
	/**
	 * 根据队列 查询等待时间
	 * @param code
	 * @return 
	 */
	public int waitTime(String code,String deptCode,String deptHall){
	String atime=null;
	int at = 1;
	
	try {
		 atime=numberService.queueWaitTime(code, deptCode, deptHall);
		 if(null!=atime && atime!=""){
			 at=  Integer.parseInt(atime);
		 }		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return at;
	}
	
	/**
	 * 根据ID 查询业务信息
	 * @param id
	 * @return
	 */
	public Business queryById(String id){
	Business bus=new Business();
	bus.setId(id);
	
	try {
		List<Business> busines=numberService.queryBus(bus);
		bus.setName(busines.get(0).getName());
		bus.setQueueCode(busines.get(0).getQueueCode());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bus;
	}
}
