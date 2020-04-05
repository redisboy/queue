package com.suntendy.queue.evaluation.action;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.evaluation.domain.BadEvaluation;
import com.suntendy.queue.evaluation.service.IBadEvaluationService;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

public class BadEvaluationAction extends BaseAction {

	private IBadEvaluationService badEvaluationService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	public IBadEvaluationService getBadEvaluationService() {
		return badEvaluationService;
	}
	public void setBadEvaluationService(IBadEvaluationService badEvaluationService) {
		this.badEvaluationService = badEvaluationService;
	}
	
	public String badList() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String code = getRequest().getParameter("code");
		String barid = getRequest().getParameter("barid");
		List<BadEvaluation> list = badEvaluationService.queryBadEvaluationByCondition(deptCode,deptHall,code, barid,"","","");
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i).getId();
			String operate = "<a onclick=detailBad('" + id
			+ "')><img src='/queue/images/button_ck.jpg' style='cursor:hand'></a>&nbsp;";
			list.get(i).setOperation(operate);
		}
		getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String detailBad() throws Exception{
		String id = getRequest().getParameter("id");
		List<BadEvaluation> list = badEvaluationService.queryBadEvaluationById(id);
		BadEvaluation badevalua = new BadEvaluation();
		if (list.size()>0) {
			badevalua = list.get(0);
		}
		String clientno = badevalua.getClientno().substring(13);
		String entertime = badevalua.getEntertime().substring(0, 19);
		badevalua.setClientno(clientno);
		badevalua.setEntertime(entertime);
		getRequest().setAttribute("badevalua", badevalua);
		return "detail";
	}
	
	public String getImg() throws Exception{
		HttpServletResponse response=getResponse("image/jpeg");
		String id = getRequest().getParameter("id");
		List<BadEvaluation> list = badEvaluationService.queryBadEvaluationById(id);
		BadEvaluation badevalua = new BadEvaluation();
		if (list.size()>0) {
			badevalua = list.get(0);
		}
		if (badevalua.getEvaluphoto() != null) {
			ServletOutputStream out=response.getOutputStream();
			out.write(badevalua.getEvaluphoto());
			out.flush();
			out.close();
		}
		return null;
	}
	
	public String badCount() throws Exception{
		HttpServletRequest request=getRequest();
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
		String code = getRequest().getParameter("code");
		String barid = getRequest().getParameter("barid");
		String ksrq = StringUtils.trimToEmpty(getRequest().getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(getRequest().getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(getRequest().getParameter("tjms"));
		List<BadEvaluation> list = badEvaluationService.queryBadEvaluationByCondition(deptCode,deptHall,code, barid, tjms, ksrq, jsrq);
		for (int i = 0; i < list.size(); i++) {
			BadEvaluation badNum = list.get(i);
			badNum.setClientno(badNum.getClientno().substring(13, badNum.getClientno().length()));
			if (null==badNum.getSeason() || "".equals(badNum.getSeason())) {
				badNum.setIsHF("未回访");
			}else{
				badNum.setIsHF("已回访");
			}
			String id = list.get(i).getId();
			String operate = "<a onclick=detailBad('" + id
			+ "')><img src='/queue/images/button_ck.jpg' style='cursor:hand'></a>&nbsp;";
			list.get(i).setOperation(operate);
		}
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("deptHall",deptHall);
		getRequest().setAttribute("list", list);
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="统计报表";
		String moduleType="差评信息查询";
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
		
		
		
		
		
		return "count";
	}
	
	public String badCountDetail() throws Exception{
		String id = getRequest().getParameter("id");
		List<BadEvaluation> list = badEvaluationService.queryBadEvaluationById(id);
		BadEvaluation badevalua = new BadEvaluation();
		if (list.size()>0) {
			badevalua = list.get(0);
		}
		String clientno = badevalua.getClientno().substring(13);
		String entertime = badevalua.getEntertime().substring(0, 19);
		badevalua.setClientno(clientno);
		badevalua.setEntertime(entertime);
		getRequest().setAttribute("badevalua", badevalua);
		return "detail";
	}
	
	public String updateBadEvaluationSeason(){
		String id = getRequest().getParameter("id");
		String season = getRequest().getParameter("season");
		Employee user = (Employee) getHttpSession().getAttribute("user");
		BadEvaluation badEvaluationSeason = new BadEvaluation();
		badEvaluationSeason.setId(id);
		badEvaluationSeason.setSeason(season);
		try {
			badEvaluationSeason.setHfUser(user.getCode());
//			BadEvaluation badevalua = badEvaluationService.queryBadEvaluationById(id);
//			if ("".equals(badevalua.getSeason()) || null ==badevalua.getSeason()) {
				badEvaluationService.addBadEvaluationSeason(badEvaluationSeason);
//			}else {
//				badEvaluationService.updateBadEvaluationSeason(badEvaluationSeason);
//			}
			getRequest().setAttribute("msg", "差评原因设置成功！");
		} catch (Exception e) {
			e.printStackTrace();
			getRequest().setAttribute("msg", "差评原因设置失败，<br>在执行过程中发生异常！");
		}
		getRequest().setAttribute("action", "badCount.action");
		return "update";
	}
}
