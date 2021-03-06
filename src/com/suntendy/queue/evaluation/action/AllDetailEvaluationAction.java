package com.suntendy.queue.evaluation.action;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.evaluation.domain.AllDetail;
import com.suntendy.queue.evaluation.service.IAllDetailEvaluationService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

public class AllDetailEvaluationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IAllDetailEvaluationService allDetailEvaluationService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	/*
	 * 详细评价统计
	 */
	public String xxpjjl() throws Exception {
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
		String jbr = StringUtils.trimToEmpty(request.getParameter("jbr"));
		List<AllDetail> list = allDetailEvaluationService.getAllDetailEvaluation( tjms, ksrq, jsrq,jbr,"",deptCode, deptHall);
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				AllDetail allDetailVo =  (AllDetail) list.get(i) ;
				String code = allDetailVo.getId();
				String valueName ="<a onclick=\"showDetail('"+code+"') ;\">"+allDetailVo.getValueName()+"</a> ";
				allDetailVo.setValueName(valueName);
				request.setAttribute("AllDetail", allDetailVo);
			}
			
		}
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("deptHall",deptHall);
	    request.setAttribute("list", list);
	    
	 // 完成 查询操作进入日志功能 开始
		
	String event="查询";
	String module="统计报表";
	String moduleType="详细评价统计";
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
	 * 点击"评价结果"一列跳转到详细评价记录页面
	 */
	public String xxpjjl2() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq=request.getParameter("ksrq")==null?"":request.getParameter("ksrq");
		String jsrq=request.getParameter("jsrq")==null?"":request.getParameter("jsrq");
		String tjms=request.getParameter("tjms")==null?"":request.getParameter("tjms");
		String jbr = request.getParameter("jbr");
		String code=request.getParameter("code");
		List<AllDetail> list =allDetailEvaluationService.getAllDetailEvaluation( tjms, ksrq, jsrq,jbr,code,deptCode, deptHall);
		if(!list.isEmpty()){
				AllDetail allDetailVo = list.get(0) ;
				request.setAttribute("AllDetail", allDetailVo);
		}
	    request.setAttribute("list", list);
	    request.setAttribute("id", code);
		return SUCCESS;
	  }
	/*
	 * 详细评价记录页面页面中显示图片
	 */
	public String getPhoto() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletResponse response=getResponse("image/jpeg");
		String ksrq=request.getParameter("ksrq")==null?"":request.getParameter("ksrq");
		String jsrq=request.getParameter("jsrq")==null?"":request.getParameter("jsrq");
		String tjms=request.getParameter("tjms")==null?"":request.getParameter("tjms");
		String jbr = request.getParameter("jbr");
		String code=request.getParameter("id");
		List<AllDetail> list =allDetailEvaluationService.getAllDetailEvaluation( tjms, ksrq, jsrq,jbr,code,deptCode, deptHall);
		if(null!=list && !list.isEmpty()){
			AllDetail allDetail = (AllDetail) list.get(0);
			if(allDetail.getPhoto()!=null){
				ServletOutputStream out=response.getOutputStream();
				out.write(allDetail.getPhoto());
				out.flush();
				out.close();
			}
		}
		return null;
	  }

	public IAllDetailEvaluationService getAllDetailEvaluationService() {
		return allDetailEvaluationService;
	}

	public void setAllDetailEvaluationService(IAllDetailEvaluationService allDetailEvaluationService) {
		this.allDetailEvaluationService = allDetailEvaluationService;
	}
}