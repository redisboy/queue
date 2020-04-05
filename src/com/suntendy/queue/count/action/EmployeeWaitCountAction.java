package com.suntendy.queue.count.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.count.domain.EmployeeWaitCount;
import com.suntendy.queue.count.service.IEmployeeWaitCountService;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.WindowCount;

public class EmployeeWaitCountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IEmployeeWaitCountService employeeWaitCountService;
	private DeptService deptService;
	private INumberService numberService;
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
	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}
	/*
	 * 员工排队统计
	 */
	public String employeeWaitCount() throws Exception {
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
		List<EmployeeWaitCount> list = employeeWaitCountService.employeeWaitCount(tjms, ksrq, jsrq,deptCode, deptHall);
		
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
				for(EmployeeWaitCount employeeWaitCount :list){
					deptAndHallName=infoDeptAndHall.get(employeeWaitCount.getDeptCode()+employeeWaitCount.getDeptHall());
					employeeWaitCount.setDeptCodeName(deptAndHallName.get(0));
					employeeWaitCount.setDeptname(deptAndHallName.get(1));
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
				for(EmployeeWaitCount employeeWaitCount :list){
					deptAndHallName=infoDeptAndHall.get(employeeWaitCount.getDeptCode()+employeeWaitCount.getDeptHall());
					employeeWaitCount.setDeptCodeName(deptAndHallName.get(0));
					employeeWaitCount.setDeptname(deptAndHallName.get(1));
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
		String moduleType="员工排队统计 ";
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
	/*
	 * 员工叫号量、工作量、满意量评价
	 */
	public String employeeCount()throws Exception {
		HttpServletRequest request = getRequest();
		List<EmployeeWaitCount> list = employeeWaitCountService.employeeCount();
		request.setAttribute("list", list);
		return "employeeCount";
	}
	
	public String jltj()throws Exception {
		HttpServletRequest request = getRequest();
//		List<EmployeeWaitCount> list = employeeWaitCountService.employeeCount();
//		Number countnum = new Number();
//		CacheManager cacheManager = CacheManager.getInstance();
//		String deptCode = cacheManager.getOfDeptCache("deptCode");
//		String deptHall = cacheManager.getOfDeptCache("deptHall");
//		countnum.setDeptCode(deptCode);
//		countnum.setDeptHall(deptHall);
//		List<Number> numbersCountList = numberService.showWaitRs(countnum);
////		request.setAttribute("list", list);
//		String result = "";
//		if (numbersCountList.size()>0) {
//			for (int i = 0; i < numbersCountList.size(); i++) {
//				result += numbersCountList.get(i).getTypeName()+":"+numbersCountList.get(i).getTypeCount()+"@";
//			}
//		}
//		System.out.println(result);
//		request.setAttribute("result", result);
		return "jltj";
	}
	
	public String jltjyibu() throws Exception{
		System.out.println("吉林综合屏统计后台程序");
		HttpServletRequest request = getRequest();
		Number countnum = new Number();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		countnum.setDeptCode(deptCode);
		countnum.setDeptHall(deptHall);
		List<Number> numbersCountList = numberService.showWaitRs(countnum);
		List<Number> yjlist = numberService.showyjrs(countnum);
		
		
		JSONObject datas = new JSONObject();
		String result = "";
		String yjresult = "";
		if (numbersCountList.size()>0) {
			for (int i = 0; i < numbersCountList.size(); i++) {
				result += numbersCountList.get(i).getTypeName()+":"+numbersCountList.get(i).getTypeCount()+"@";
			}
		}
		if (yjlist.size()>0) {
			for (int i = 0; i < yjlist.size(); i++) {
				yjresult += yjlist.get(i).getTypeName()+":"+yjlist.get(i).getTypeCount()+"@";
			}
		}
		datas.put("result", result);
		datas.put("yjresult", yjresult);
		System.out.println("yjresult="+yjresult);
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	public String employeeCountyibu() throws Exception{
		HttpServletRequest request = getRequest();
		List<EmployeeWaitCount> list = employeeWaitCountService.employeeCount();
		System.out.println("****");
		JSONObject datas = new JSONObject();
		if (null != list && !list.isEmpty()) {
			datas.put("isSuccess", true);
			
			for (EmployeeWaitCount emp : list) {
				JSONObject obj = new JSONObject();
				//判断是否存在上一级
				obj.put("name", emp.getName());
				obj.put("jiaohaocount", emp.getJiaohaocount());
				obj.put("gongzuocount", emp.getGongzuocount());
				obj.put("manyicount", emp.getManyicount());
				datas.put("d" + emp.getName(), obj);
					
			}
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "没有可办理的业务类型");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	public IEmployeeWaitCountService getEmployeeWaitCountService() {
		return employeeWaitCountService;
	}

	public void setEmployeeWaitCountService(
			IEmployeeWaitCountService iEmployeeWaitCountService) {
		this.employeeWaitCountService = iEmployeeWaitCountService;
	}
}