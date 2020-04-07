package com.suntendy.queue.count.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.count.domain.GetNumberInfoCount;
import com.suntendy.queue.count.service.IGetNumberInforCountService;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.util.cache.CacheManager;

public class GetNumberInforCountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IGetNumberInforCountService getNumberInforCountService;

	/*
	 * 取号信息查询
	 */
	public String getNumberInforCount() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String xm=StringUtils.trimToEmpty(request.getParameter("xm"));
		List<GetNumberInfoCount> list = getNumberInforCountService.GetNumberInforCount(ksrq, jsrq, tjms,deptCode, deptHall,xm);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				GetNumberInfoCount numberVo = (GetNumberInfoCount) list.get(i);

				if (numberVo.getIsagent().equals("0")) {
					numberVo.setIsagent("否");
				} else {
					numberVo.setIsagent("是");
				}
			}
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	/*
	 * 取号信息预警
	 */
	public String getDoubleNumberInfo() throws Exception{
		HttpServletRequest request = getRequest();
		List<GetNumberInfoCount> list = getNumberInforCountService.getDoubleNumberInfo();
		for (int i = 0; i < list.size(); i++) {
			GetNumberInfoCount GetNumberInfoCountVO = list.get(i);
			String idnumber = GetNumberInfoCountVO.getIdnumber();
			String operate = "<a onclick=detailAll('" + idnumber
			+ "')><img src='/queue/images/button_ck.jpg' style='cursor:hand'></a>";
			GetNumberInfoCountVO.setOperation(operate);
			}
		request.setAttribute("list", list);
		return "doubleNumList";
	}
	/*
	 * 取号信息预警详情
	 */
	public String getDetailDoubleNumber() throws Exception{
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		List<GetNumberInfoCount> list = getNumberInforCountService.getDetailDoubleNumber(idnumber);
		request.setAttribute("list", list);
		return "doubleNumDetail";
	}
	
	public IGetNumberInforCountService getGetNumberInforCountService() {
		return getNumberInforCountService;
	}

	public void setGetNumberInforCountService(
			IGetNumberInforCountService getNumberInforCountService) {
		this.getNumberInforCountService = getNumberInforCountService;
	}
}