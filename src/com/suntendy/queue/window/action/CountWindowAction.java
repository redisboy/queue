package com.suntendy.queue.window.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.WindowCount;
import com.suntendy.queue.window.service.ICountWindowService;

public class CountWindowAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ICountWindowService countWindowService;
	private DeptService deptService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	/**
	 * 窗口排队统计
	 */
	public String getWaitCount() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String deptCode = "";
		String deptHall = "";
		if("0".equals(role)){
			deptCode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String barid=StringUtils.trimToEmpty(request.getParameter("barid"));
		List<WindowCount> list = countWindowService.getWindowCount(tjms, ksrq, jsrq,deptCode, deptHall,barid);
		
		if("0".equals(role)){
			//得到管理部门及大厅名称
			List<Map<String,String>> deptAndHallList= new ArrayList<Map<String,String>>();
			try {
				deptAndHallList = getDeptService().findAllDeptcode();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//遍历deptAndHallList集合放入map以便通过部门编号和大厅编号得到相应名称
			HashMap<String,ArrayList<String>> infoDeptAndHall = new HashMap<String, ArrayList<String>>();
			
			for(Map<String,String> map:deptAndHallList ){
				ArrayList<String> infoList=new ArrayList<String>();
				infoList.add(0,map.get("DEPTCODENAME"));
				infoList.add(1, map.get("DEPTNAME"));
				infoDeptAndHall.put(map.get("DEPTCODE")+map.get("DEPTHALL"),infoList);
			}
			ArrayList<String> deptAndHallName= new ArrayList<String>();
			if (null!=list) {
				for(WindowCount windowCount :list){
					deptAndHallName=infoDeptAndHall.get(windowCount.getDeptCode()+windowCount.getDeptHall());
					windowCount.setDeptCodeName(deptAndHallName.get(0));
					windowCount.setDeptname(deptAndHallName.get(1));
				}
			}
			
		}else if("1".equals(role)) {
			List<Map<String,String>> deptAndHallList= new ArrayList<Map<String,String>>();
			//根据部门得到大厅及大厅名称
			try {
				deptAndHallList = getDeptService().findDepthallbyDeptcode(deptCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//遍历deptAndHallList集合放入map以便通过部门编号和大厅编号得到相应名称
			HashMap<String,ArrayList<String>> infoDeptAndHall = new HashMap<String, ArrayList<String>>();
			
			for(Map<String,String> map:deptAndHallList ){
				ArrayList<String> infoList=new ArrayList<String>();
				infoList.add(0,map.get("DEPTCODENAME"));
				infoList.add(1, map.get("DEPTNAME"));
				infoDeptAndHall.put(map.get("DEPTCODE")+map.get("DEPTHALL"),infoList);
			}
			ArrayList<String> deptAndHallName= new ArrayList<String>();
			if (null!=list) {
				for(WindowCount windowCount :list){
					deptAndHallName=infoDeptAndHall.get(windowCount.getDeptCode()+windowCount.getDeptHall());
					windowCount.setDeptCodeName(deptAndHallName.get(0));
					windowCount.setDeptname(deptAndHallName.get(1));
				}
			}
		}
		
		request.setAttribute("role", role);
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("deptHall",deptHall);
		request.setAttribute("list", list);
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="统计报表";
		String moduleType="窗口排队统计";
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
		try {
			Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
			operate.setResult(result);
			iSystemLogService.addOperate(operate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 查询操作进入日志功能代码块结束
		
		
		
		
		return SUCCESS;
	}

	public ICountWindowService getCountWindowService() {
		return countWindowService;
	}

	public void setCountWindowService(ICountWindowService countWindowService) {
		this.countWindowService = countWindowService;
	}
}