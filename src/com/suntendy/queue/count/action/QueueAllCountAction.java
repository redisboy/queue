package com.suntendy.queue.count.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.count.domain.QueueAllCount;
import com.suntendy.queue.count.service.IQueueAllCountService;
import com.suntendy.queue.util.cache.CacheManager;

public class QueueAllCountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IQueueAllCountService queueAllCountService;
	
	public IQueueAllCountService getQueueAllCountService() {
		return queueAllCountService;
	}

	public void setQueueAllCountService(IQueueAllCountService queueAllCountService) {
		this.queueAllCountService = queueAllCountService;
	}

	/*
	 * 统计所有
	 */
	public String queueAllCount() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		QueueAllCount qall =new QueueAllCount();
		qall.setDeptCode(deptCode);
		qall.setDeptHall(deptHall);
		qall.setKsrq(ksrq);
		qall.setJsrq(jsrq);
		qall.setTjms(tjms);
		List<QueueAllCount> list = queueAllCountService.queueAllCount(qall);
		request.setAttribute("ksrq", ksrq);
		request.setAttribute("jsrq", jsrq);
		request.setAttribute("list", list);
		return SUCCESS;
	}


}