package com.suntendy.queue.evaluation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.evaluation.domain.EmployeeEvaluation;
import com.suntendy.queue.evaluation.service.IEvaluationService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.SendPJXXEvent;
import com.suntendy.queue.window.domain.WindowCount;

public class EvaluationAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IEvaluationService evaluationService;
	private DeptService deptService;
	private Publisher publisher;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	/*
	 * 员工评价统计
	 */
	public String ygpjtj() throws Exception {
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
		List<EmployeeEvaluation> list = evaluationService.getEmployeeEvaluate(tjms, ksrq, jsrq,deptCode, deptHall);
		for (int i = 0; i < list.size(); i++) {
			if(!"0".equals(list.get(i).getA5())){
				list.get(i).setA5("<a href='badList.action?code=" + list.get(i).getCode() + "'><font color='red'>"+list.get(i).getA5()+"</font></a>");
			}
		}
		
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
				for(EmployeeEvaluation employeeEvaluation :list){
					deptAndHallName=infoDeptAndHall.get(employeeEvaluation.getDeptCode()+employeeEvaluation.getDeptHall());
					employeeEvaluation.setDeptCodeName(deptAndHallName.get(0));
					employeeEvaluation.setDeptname(deptAndHallName.get(1));
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
				for(EmployeeEvaluation employeeEvaluation :list){
					deptAndHallName=infoDeptAndHall.get(employeeEvaluation.getDeptCode()+employeeEvaluation.getDeptHall());
					employeeEvaluation.setDeptCodeName(deptAndHallName.get(0));
					employeeEvaluation.setDeptname(deptAndHallName.get(1));
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
		String moduleType="员工评价统计";
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
	public String ygpjmyl()throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode=cacheManager.getOfDeptCache("deptCode");
		String deptHall=cacheManager.getOfDeptCache("deptHall");
		Integer countAll;
		Integer a3;
		Integer a2;
		Integer a1;
		Integer manyiliang;
		Integer manyilv;
		
		StringBuffer result = new StringBuffer();
		List<EmployeeEvaluation> list = evaluationService.getEmployeeEvaluate("1","","",deptCode, deptHall);
		if (!list.isEmpty()) {
			for(EmployeeEvaluation employeeEvaluation:list){
				a3 = Integer.parseInt(employeeEvaluation.getA3());
				a2 = Integer.parseInt(employeeEvaluation.getA2());
				a1 = Integer.parseInt(employeeEvaluation.getA1());
				manyiliang = a1+a2+a3;
				if (!(manyiliang==0)) {
					countAll = Integer.parseInt(employeeEvaluation.getCountall());
					manyilv=((manyiliang*100)/countAll);
					result.append(employeeEvaluation.getName())
						.append(":")
						.append(manyilv)
						.append(":")
						.append(countAll)
						.append("@");
				}
				
			}
			
			publisher.publish(new SendPJXXEvent(result));
			
			System.out.println("-----------有评价信息-------------");
		}
		System.out.println("------------未有评价信息--------------");
		return null;
	}

	
	/**
	 * 贵港差评窗口推送
	 * 
	 */
	public String guigangcp()throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode=cacheManager.getOfDeptCache("deptCode");
		String deptHall=cacheManager.getOfDeptCache("deptHall");
		Integer a5;
		
		StringBuffer result = new StringBuffer();
		List<EmployeeEvaluation> list = evaluationService.getEmployeeEvaluate("1","","",deptCode, deptHall);
		if (!list.isEmpty()) {
			for(EmployeeEvaluation employeeEvaluation:list){
				a5 = Integer.parseInt(employeeEvaluation.getA5());
					if(a5!=0){
						result.append(employeeEvaluation.getBarid()+"号")
						.append(":")
						.append(employeeEvaluation.getA5())
						.append(":")
						.append(0)
						.append("@");
					}
				}
					publisher.publish(new SendPJXXEvent(result));
				System.out.println("-----------有评价信息-------------");
			}else{
				System.out.println("------------未有评价信息--------------");
			}
		
		
		return null;
	}
	
	
	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}
}