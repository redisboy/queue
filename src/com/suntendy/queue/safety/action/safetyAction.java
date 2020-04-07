package com.suntendy.queue.safety.action;

/**
 * 安全策略设置
 * 本策略对全部大厅进行管理
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.safety.domain.Safety;
import com.suntendy.queue.safety.service.ISafetyService;

public class safetyAction extends BaseAction {
	private ISafetyService safetyService;

	public ISafetyService getSafetyService() {
		return safetyService;
	}

	public void setSafetyService(ISafetyService safetyService) {
		this.safetyService = safetyService;
	}
	
	public String savesafe(){
		HttpServletRequest request=getRequest();
		Safety safety = new Safety();
		safety.setId("1");
		String gdhour = StringUtils.trimToEmpty(request.getParameter("gdhour"));
		String gdminute  = StringUtils.trimToEmpty(request.getParameter("gdminute"));
		String gdhour1 = StringUtils.trimToEmpty(request.getParameter("gdhour1"));
		String gdminute1  = StringUtils.trimToEmpty(request.getParameter("gdminute1"));
		String timelimit = StringUtils.trimToEmpty(request.getParameter("timelimit"));
		String visits = StringUtils.trimToEmpty(request.getParameter("visits"));
		String ipSum = StringUtils.trimToEmpty(request.getParameter("ipSum"));
		String userSum = StringUtils.trimToEmpty(request.getParameter("userSum"));
		String sessionSum = StringUtils.trimToEmpty(request.getParameter("sessionSum"));
		String logSum = StringUtils.trimToEmpty(request.getParameter("logSum"));
		safety.setGdhour(gdhour);
		safety.setGdminute(gdminute);
		safety.setGdhour1(gdhour1);
		safety.setGdminute1(gdminute1);
		safety.setTimelimit(timelimit);
		safety.setVisits(visits);
		safety.setIpSum(ipSum);
		safety.setUserSum(userSum);
		safety.setSessionSum(sessionSum);
		safety.setLogSum(logSum);
		List<Safety> list = null;
		
		try {
			safetyService.delLogin(logSum);
			list = safetyService.searchSafety();
			if(null != list && list.size()>0){
				safetyService.updateSafety(safety);
			}else{
				safetyService.addSafety(safety);
			}
			request.setAttribute("msg", "安全策略保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "安全策略保存失败，在执行过程中发生异常！");
		}
		request.setAttribute("safety", safety);
		return "success";
	}
	
	
	public String searchsafe() throws Exception{
		HttpServletRequest request=getRequest();
		List<Safety> list = null;
		list = safetyService.searchSafety();
		Safety safety = null;
		if(list.size()>0){
			safety = list.get(0);
		}
		request.setAttribute("msg", "");
		request.setAttribute("safety", safety);
		return "success";
	}
}
