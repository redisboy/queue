package com.suntendy.queue.count.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.count.domain.AgentWaitCount;
import com.suntendy.queue.count.service.IAgentWaitCountService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

/**
 * 代理人排队情况统计
 */
public class AgentWaitCountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IAgentWaitCountService agentService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	public IAgentWaitCountService getAgentService() {
		return agentService;
	}

	public void setAgentService(IAgentWaitCountService agentService) {
		this.agentService = agentService;
	}

	public String agentWaitCount() throws Exception {
		HttpServletRequest request = getRequest();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		String deptCode = "";
		if("0".equals(role)){
			deptCode=request.getParameter("deptCode");
		}else if("1".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
			
		}else if ("2".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
		}
		List<AgentWaitCount> list = agentService.agentCount(ksrq, jsrq, tjms,deptCode, "");
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("list", list);
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="代理人统计报表";
		String moduleType="代理人排队情况统计";
		String eventType="查";
		String coreBusiness="1";
		String result="0";
		String resultDepict="查询成功";
		//用户名 
		//String userName = request.getParameter("yhdh");  --这种方式取不到
		session = getHttpSession();
		Employee employee = new Employee();
		user = (Employee) session.getAttribute("user");
		String userName =user.getName();
		
		OperateLog operateLog = new OperateLog();
		Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
		operate.setResult(result);
		try {
			iSystemLogService.addOperate(operate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 查询操作进入日志功能代码块结束

		
		return SUCCESS;
	}
}