package com.suntendy.queue.statistics.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.statistics.domain.HandlerLCount;
import com.suntendy.queue.statistics.domain.SaveLCount;
import com.suntendy.queue.statistics.domain.UserLCount;
import com.suntendy.queue.statistics.service.IStatisticsLogService;
import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Security;
import com.suntendy.queue.util.cache.CacheManager;

public class StatisticsLogAction extends BaseAction {
	private IStatisticsLogService iStatisticsLogService;

	public void setiStatisticsLogService(
			IStatisticsLogService iStatisticsLogService) {
		this.iStatisticsLogService = iStatisticsLogService;
	}

	/**
	 * 查询登录日志统计
	 */
	public String queryLoginCount() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();

		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");

		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String loginIP = StringUtils.trimToEmpty(request
				.getParameter("loginIP"));

		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		System.out.println((new StringBuilder("tjms")).append(tjms).toString());
		System.out.println((new StringBuilder("ksrq")).append(ksrq).toString());
		System.out.println((new StringBuilder("jsrq")).append(jsrq).toString());

		UserLCount userLCount = new UserLCount();
		userLCount.setDeptCode(deptCode);
		userLCount.setDeptHall(deptHall);
		userLCount.setUserName(userName);
		userLCount.setLoginIP(loginIP);

		List userCountList = iStatisticsLogService.queryLoginCount(tjms, ksrq,
				jsrq, userLCount);// 根结点
		request.setAttribute("list", userCountList);// 总数
		return "success";

	}

	/**
	 * 查询安全日志统计
	 */
	public String querySaveLCount() throws Exception {

		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String saveCel = request.getParameter("securityContext");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		System.out.println((new StringBuilder("tjms")).append(tjms).toString());
		System.out.println((new StringBuilder("ksrq")).append(ksrq).toString());
		System.out.println((new StringBuilder("jsrq")).append(jsrq).toString());
		
		SaveLCount saveLCount = new SaveLCount();
		saveLCount.setDeptCode(deptCode);
		saveLCount.setDeptHall(deptHall);
		saveLCount.setSaveCel(saveCel);
		System.out.println(saveCel);
		saveLCount.setUserName(userName);
		List saveCountList = iStatisticsLogService.querySaveLCount(tjms, ksrq,
				jsrq, saveLCount);// 根结点
		saveLCount.setResult("0");
		request.setAttribute("list", saveCountList);// 总数
		
		return "success";
	}

	/**
	 * 操作日志统计
	 * 
	 * @param hcounts
	 * @param systemName
	 * @param functionName
	 * @param handlerCount
	 */

	public String queryHandlerLCount() throws Exception {

		CacheManager cacheManager = CacheManager.getInstance();

		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");

		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String coreBusiness = StringUtils.trimToEmpty(request
				.getParameter("core"));

		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		System.out.println((new StringBuilder("tjms")).append(tjms).toString());
		System.out.println((new StringBuilder("ksrq")).append(ksrq).toString());
		System.out.println((new StringBuilder("jsrq")).append(jsrq).toString());

		HandlerLCount handlerLCount = new HandlerLCount();
		handlerLCount.setDeptCode(deptCode);
		handlerLCount.setDeptHall(deptHall);
		handlerLCount.setUserName(userName);
		handlerLCount.setCoreBusiness(coreBusiness);

		List handlerCountList = iStatisticsLogService.queryHandlerLCount(tjms,
				ksrq, jsrq, handlerLCount);// 根结点
		request.setAttribute("list", handlerCountList);// 总数
		return "success";
	}

}
