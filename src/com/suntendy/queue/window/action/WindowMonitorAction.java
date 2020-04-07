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
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.WindowMonitor;
import com.suntendy.queue.window.service.IWindowMonitorService;

public class WindowMonitorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IWindowMonitorService windowMonitorService;
	private DeptService deptService;

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	/**
	 * 窗口监控统计
	 * @throws Exception 
	 */
	public String getWindowMonitor() throws Exception {
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
		String xm=StringUtils.trimToEmpty(request.getParameter("xm"));
		List<WindowMonitor> list = windowMonitorService.getWindowMonitor(tjms, ksrq, jsrq,deptCode, deptHall,barid,xm);
		
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
				for(WindowMonitor windowMonitor :list){
					//deptAndHallName=infoDeptAndHall.get(windowMonitor.getDeptCode()+windowMonitor.getDeptHall());
					deptAndHallName=infoDeptAndHall.get(windowMonitor.getDeptCode()+windowMonitor.getDeptHall());
					windowMonitor.setDeptCodeName(deptAndHallName.get(0));
					windowMonitor.setDeptname(deptAndHallName.get(1));
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
				for(WindowMonitor windowMonitor :list){
					deptAndHallName=infoDeptAndHall.get(windowMonitor.getDeptCode()+windowMonitor.getDeptHall());
					windowMonitor.setDeptCodeName(deptAndHallName.get(0));
					windowMonitor.setDeptname(deptAndHallName.get(1));
				}
			}
		}
		
		request.setAttribute("role", role);
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("deptHall",deptHall);
		request.setAttribute("list", list);
		
		
		return SUCCESS;
	}

	public IWindowMonitorService getWindowMonitorService() {
		return windowMonitorService;
	}

	public void setWindowMonitorService(IWindowMonitorService windowMonitorService) {
		this.windowMonitorService = windowMonitorService;
	}

	
}