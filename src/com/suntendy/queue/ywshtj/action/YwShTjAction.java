package com.suntendy.queue.ywshtj.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.ywshtj.domain.YwShTj;
import com.suntendy.queue.ywshtj.service.IYwShTjService;

public class YwShTjAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IYwShTjService ywShTjService;
	private IBusinessService businessService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}
	
	public IYwShTjService getYwShTjService() {
		return ywShTjService;
	}
	public void setYwShTjService(IYwShTjService ywShTjService) {
		this.ywShTjService = ywShTjService;
	}
	public IBusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	
	/**
	 * 对业务人员(管理部门)平均办理时间排名统计
	 */
	public String getEmplooyTransactTimeOrder() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		String isOpenYj=cacheManager.getSystemConfig("isOpenYj");
		String gzl=cacheManager.getSystemConfig("gzl");
		int gzlint=Integer.parseInt(gzl);
		String deptcode="";
		String deptHall="";
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		ywShTj.setDeptcode(deptcode);
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptHall(deptHall);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> list = ywShTjService.getEmplooyTransactTimeOrder(ywShTj);
			//工作量低于设定值，则生成业务预警信息
			if(isOpenYj.equals("0")){
				if(! list.isEmpty()){
					for(int i=0;i<list.size();i++){
						int gzSum=Integer.parseInt(list.get(i).getTotal());
						if(gzSum>gzlint){
							list.remove(i);
						}
					}
				}
			}
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"",deptcode, deptHall);
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("businessList", businessList);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="员工办理业务时间考核";
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
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 对业务人员(管理部门)差评排名统计
	 */
	public String getEmplooyBadReviewOrder() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		
		CacheManager cacheManager = CacheManager.getInstance();
		String isOpenYj = cacheManager.getSystemConfig("isOpenYj");
		String myd = cacheManager.getSystemConfig("myd");
		int mydint=Integer.parseInt(myd);
		String deptcode="";
		String deptHall="";
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		ywShTj.setDeptHall(deptHall);
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptcode(deptcode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> list = ywShTjService.getEmplooyBadReviewOrder(ywShTj);
			//满意度低于设定值，则生成业务预警信息
			if(isOpenYj.equals("0")){
				if(! list.isEmpty()){
					for(int i=0;i<list.size();i++){
						int mydSum=Integer.parseInt(list.get(i).getA1())+Integer.parseInt(list.get(i).getA2());
						if(mydSum>mydint){
							list.remove(list.get(i));
						}
					}
				}
			}
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"", deptcode, deptHall);
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("businessList", businessList);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
	
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="员工差评率考核";
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
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  对业务人员(管理部门)暂停时间排名统计
	 */
	public String getEmplooyPauseTimeOrder() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		String deptcode="";
		String deptHall="";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		ywShTj.setDeptHall(deptHall);
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptcode(deptcode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> list = ywShTjService.getEmplooyPauseTimeOrder(ywShTj);
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="暂停业务时间统计";
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
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 *  对管理部门平均等候时间统计
	 */
	public String getDepartWaitTimeOrder() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		String deptCode="";
		String deptHall="";
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
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
		ywShTj.setDeptHall(deptHall);
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptcode(deptCode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> list = ywShTjService.getDepartWaitTimeOrder(ywShTj);
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"",deptCode, deptHall);
			request.setAttribute("deptCode",deptCode);
			request.setAttribute("deptHall",deptHall);
			request.setAttribute("list", list);
			request.setAttribute("businessList", businessList);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="部门等候时间考核";
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
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**********************************************预警BEGIN****************
	 * 对业务人员(管理部门)平均办理时间预警
	 */
	public String getEmplooyTransactTimeWarning() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		String deptcode = "";
		String deptHall = "";
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		ywShTj.setDeptcode(deptcode);
//		if(flag.equals("0")){
//			
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptHall(deptHall);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> listWaring = new ArrayList<YwShTj>();//用于页面图形
			List<YwShTj> list = ywShTjService.getEmplooyTransactTimeWarning(ywShTj);//有样式，用于ecside
			if (null != list && !list.isEmpty()) {
				for (YwShTj ywShTj1 : list) {
					YwShTj ywsh = new YwShTj();
					ywsh.setName(ywShTj1.getName());
					ywsh.setCode(ywShTj1.getCode());
					ywsh.setDeptcode(ywShTj1.getDeptcode());
					ywsh.setTotal(ywShTj1.getTotal());
					ywsh.setAve_waittime(ywShTj1.getAve_waittime());
					ywsh.setAve_worktime(ywShTj1.getAve_worktime());
					ywsh.setSumAvg(ywShTj1.getSumAvg());
					listWaring.add(ywsh);
					if(Float.parseFloat(ywShTj1.getAve_worktime()) > Float.parseFloat(ywShTj1.getSumAvg())){//大于平均时间预警(将时间改为红色)
						ywShTj1.setName("<font color='red'>"+ywShTj1.getName()+"</font>");
						ywShTj1.setCode("<font color='red'>"+ywShTj1.getCode()+"</font>");
						ywShTj1.setDeptcode("<font color='red'>"+ywShTj1.getDeptcode()+"</font>");
						ywShTj1.setTotal("<font color='red'>"+ywShTj1.getTotal()+"</font>");
						ywShTj1.setAve_waittime("<font color='red'>"+ywShTj1.getAve_waittime()+"</font>");
						ywShTj1.setAve_worktime("<font color='red'>"+ywShTj1.getAve_worktime()+"</font>");
					}
				}
				
			}
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"",deptcode, deptHall);
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("listWaring", listWaring);
			request.setAttribute("businessList", businessList);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="业务办理时间预警";
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
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 对业务人员(管理部门)差评预警
	 */
	public String getEmplooyBadReviewWarning() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		String isOpenYj=cacheManager.getSystemConfig("isOpenYj");
		String cps=cacheManager.getSystemConfig("cps");
		int cpsint=Integer.parseInt(cps);
		String deptcode = "";
		String deptHall = "";
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptHall(deptHall);
		ywShTj.setDeptcode(deptcode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> listWaring = new ArrayList<YwShTj>();//用于页面图形
			List<YwShTj> list = ywShTjService.getEmplooyBadReviewWarning(ywShTj);
			if (null != list && !list.isEmpty()) {
				for (YwShTj ywShTj1 : list) {
					YwShTj ywsh = new YwShTj();
					ywsh.setName(ywShTj1.getName());
					ywsh.setCode(ywShTj1.getCode());
					ywsh.setA1(ywShTj1.getA1());
					ywsh.setA2(ywShTj1.getA2());
					ywsh.setA3(ywShTj1.getA3());
					ywsh.setA4(ywShTj1.getA4());
					ywsh.setSumAvg(ywShTj1.getSumAvg());
					listWaring.add(ywsh);
					if(Float.parseFloat(ywShTj1.getA4()) > Float.parseFloat(ywShTj1.getSumAvg())){//大于平均时间预警(将时间改为红色)
						ywShTj1.setName("<font color='red'>"+ywShTj1.getName()+"</font>");
						ywShTj1.setCode("<font color='red'>"+ywShTj1.getCode()+"</font>");
						ywShTj1.setA1("<font color='red'>" + ywShTj1.getA1()+"</font>");
						ywShTj1.setA2("<font color='red'>" + ywShTj1.getA2()+"</font>");
						ywShTj1.setA3("<font color='red'>" + ywShTj1.getA3()+"</font>");
						ywShTj1.setA4("<font color='red'>" + ywShTj1.getA4()+"</font>");
						ywShTj1.setAvg_badReview("<font color='red'>" + ywShTj1.getAvg_badReview()+"</font>");
					}
				}
				
			}
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"",deptcode, deptHall);
			//差评数超过设定值，则生成业务预警信息
			if(isOpenYj.equals("0")){
				if(!list.isEmpty()){
					for(int i=0;i<list.size();i++){
						int cpl=Integer.parseInt(list.get(i).getA4());
						if(cpl<cpsint){
							list.remove(list.get(i));
						}
					}
				}
			}
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("listWaring", listWaring);
			request.setAttribute("businessList", businessList);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="差评率预警";
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

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  对业务人员(管理部门)暂停时间预警
	 */
	public String getEmplooyPauseTimeWarning() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		CacheManager cacheManager = CacheManager.getInstance();
		String deptcode = "";
		String deptHall = "";
		YwShTj ywShTj = new YwShTj();
		if("0".equals(role)){
			deptcode=request.getParameter("deptCode");
			deptHall=request.getParameter("deptHall");
			if("null".equals(deptHall)){
				deptHall="";
			}
		}else if("1".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=request.getParameter("deptHall");
			
		}else if ("2".equals(role)) {
			deptcode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptHall(deptHall);
		ywShTj.setDeptcode(deptcode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> listWaring = new ArrayList<YwShTj>();//用于页面图形
			List<YwShTj> list = ywShTjService.getEmplooyPauseTimeWarning(ywShTj);
			if (null != list && !list.isEmpty()) {
				for (YwShTj ywShTj1 : list) {
					YwShTj ywsh = new YwShTj();
					ywsh.setName(ywShTj1.getName());
					ywsh.setCode(ywShTj1.getCode());
					ywsh.setDeptcode(ywShTj1.getDeptcode());
					ywsh.setPausetime(ywShTj1.getPausetime());
					ywsh.setSumAvg(ywShTj1.getSumAvg());
					listWaring.add(ywsh);
					if(Float.parseFloat(ywShTj1.getPausetime()) > Float.parseFloat(ywShTj1.getSumAvg())){//大于平均时间预警(将时间改为红色)
						ywShTj1.setName("<font color='red'>"+ywShTj1.getName()+"</font>");
						ywShTj1.setCode("<font color='red'>"+ywShTj1.getCode()+"</font>");
						ywShTj1.setDeptcode("<font color='red'>"+ywShTj1.getDeptcode()+"</font>");
						ywShTj1.setPausetime("<font color='red'>" + ywShTj1.getPausetime()+"</font>");
					}
				}
				
			}
			request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			request.setAttribute("listWaring", listWaring);
			request.setAttribute("deptCode", deptcode);
			request.setAttribute("deptHall", deptHall);
			
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="暂停业务预警";
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 *  对管理部门平均等候时间预警
	 */
	public String getDepartWaitTimeWarning() {
		HttpServletRequest request = this.getRequest();
		//flag 0:查询大厅,1:查询管理部门
		String flag = request.getParameter("flag");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
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
		String ID = "";
		String preIndex = "";
		String business = "";
		YwShTj ywShTj = new YwShTj();
//		if(flag.equals("0")){
//			ywShTj.setDeptHall(deptHall);
//		}
		ywShTj.setDeptHall(deptHall);
		ywShTj.setDeptcode(deptCode);
		ywShTj.setKsrq(ksrq);
		ywShTj.setJsrq(jsrq);
		ywShTj.setTjms(tjms);
		ywShTj.setYwlx(ywlx);
		ywShTj.setFlag(flag);
		try {
			List<YwShTj> listWaring = new ArrayList<YwShTj>();//用于页面图形
			List<YwShTj> list = ywShTjService.getDepartWaitTimeWarning(ywShTj);
			if (null != list && !list.isEmpty()) {
				for (YwShTj ywShTj1 : list) {
					YwShTj ywsh = new YwShTj();
					ywsh.setDeptcode(ywShTj1.getDeptcode());
					ywsh.setTotal(ywShTj1.getTotal());
					ywsh.setAve_waittime(ywShTj1.getAve_waittime());
					ywsh.setSumAvg(ywShTj1.getSumAvg());
					listWaring.add(ywsh);
					if(Float.parseFloat(ywShTj1.getAve_waittime()) > Float.parseFloat(ywShTj1.getSumAvg())){//大于平均时间预警(将时间改为红色)
						ywShTj1.setDeptcode("<font color='red'>" + ywShTj1.getDeptcode()+"</font>");
						ywShTj1.setTotal("<font color='red'>" + ywShTj1.getTotal()+"</font>");
						ywShTj1.setAve_waittime("<font color='red'>" + ywShTj1.getAve_waittime()+"</font>");
					}
				}
				
			}
			List<Business> businessList = businessService.getBusinessList(ID,preIndex, business,"",deptCode, deptHall);
			request.setAttribute("deptCode",deptCode);
			request.setAttribute("deptHall",deptHall);
			request.setAttribute("list", list);
			request.setAttribute("listWaring", listWaring);
			request.setAttribute("businessList", businessList);
			
			// 完成 查询操作进入日志功能 开始
			
			String event="查询";
			String module="考核功能";
			String moduleType="部门等候时间预警";
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
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * ********************************************预警end****************
	 */
}