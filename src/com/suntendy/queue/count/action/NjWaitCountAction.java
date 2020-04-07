package com.suntendy.queue.count.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.count.domain.AgentNjCount;
import com.suntendy.queue.count.service.INjWaitCountService;
import com.suntendy.queue.util.cache.CacheManager;

/**
 * 代理人年检查询
 */
public class NjWaitCountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private INjWaitCountService njService;

	public INjWaitCountService getNjService() {
		return njService;
	}

	public void setNjService(INjWaitCountService njService) {
		this.njService = njService;
	}

	public String njWaitCount() throws Exception {
		HttpServletRequest request = getRequest();
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String idcard = request.getParameter("idcard");
		String name = request.getParameter("name");
		List<AgentNjCount> list = njService.njCount(idcard, name, ksrq, jsrq, tjms,deptCode,"");

		request.setAttribute("list", list);
		return SUCCESS;
	}
}