package com.suntendy.queue.dept.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

public class DeptAction extends BaseAction{
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
	
	public String toAddDept(){
		return "toAddDept";
	}
	
	/**
	 * 添加大厅信息
	 * @return
	 */
	public String addDept(){
		HttpServletRequest request=getRequest();
		String deptname=StringUtils.trimToEmpty(request.getParameter("deptname"));
		String depthall=StringUtils.trimToEmpty(request.getParameter("depthall"));
		String deptcode=StringUtils.trimToEmpty(request.getParameter("deptcode"));
		String deptcodename=StringUtils.trimToEmpty(request.getParameter("deptcodename"));
		String serversip=StringUtils.trimToEmpty(request.getParameter("serversip"));
		String website=StringUtils.trimToEmpty(request.getParameter("website"));
		String ak=StringUtils.trimToEmpty(request.getParameter("ak"));
		String yydd=StringUtils.trimToEmpty(request.getParameter("yydd"));
		Dept dept=new Dept();
		dept.setDeptname(deptname);
		dept.setDepthall(depthall);
		dept.setDeptcode(deptcode);
		dept.setDeptcodename(deptcodename);
		dept.setServersip(serversip);
		dept.setWebsite(website);
		dept.setAk(ak);
		dept.setYydd(yydd);
		try {
			deptService.saveDept(dept);
			request.setAttribute("msg", "大厅信息添加成功!");
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="基础设置";
			String moduleType="大厅基础信息设置";
			String eventType="增";
			String coreBusiness="0";
			String result="0";
			String resultDepict="新增成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
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
			
			// 新增操作进入日志功能代码块结束
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "大厅信息添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "dept/deptList.action");
		return "success";
	}

	/**
	 * 查询大厅信息
	 * @return
	 * @throws Exception
	 */
	public String deptList() throws Exception{
		HttpServletRequest request = getRequest();
		String deptname=StringUtils.trimToEmpty(request.getParameter("deptname"));
		String deptcode=StringUtils.trimToEmpty(request.getParameter("deptcode"));
		String deptcodename=StringUtils.trimToEmpty(request.getParameter("deptcodename"));
		Dept dept=new Dept();
		dept.setDeptname(deptname);
		dept.setDeptcode(deptcode);
		dept.setDeptcodename(deptcodename);
		List<Dept> list= deptService.getDeptList(dept);
		for(int i=0;i<list.size();i++){
			Dept dept1=list.get(i);
			String id1=dept1.getId();
			String website1=dept1.getWebsite();
			String operate="<a onclick=updateDept('" + id1 + "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"
				+ "&nbsp;"+ "<a onclick=deleteDept('" + id1 + "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>"
				+ "&nbsp;"+ "<a onclick=window.open('" + website1 + "')><img src='/queue/images/button_fw.jpg' style='cursor:hand'/></a>";
			dept1.setOperation(operate);
			request.setAttribute(deptname,dept1.getDeptname());
			request.setAttribute(deptcode,dept1.getDeptcode());
			request.setAttribute(deptcodename,dept1.getDeptcodename());
		}
		request.setAttribute("list", list);
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="基础设置";
		String moduleType="大厅基础信息设置";
		String eventType="查";
		String coreBusiness="0";
		String result="0";
		String resultDepict="查询成功";
		//用户名 
		//String userName = request.getParameter("yhdh");  --这种方式取不到
		HttpSession session = getHttpSession();
		Employee employee = new Employee();
		Employee user = (Employee) session.getAttribute("user");
		String userName =user.getName();
		
		/*Employee user1 = (Employee) getHttpSession().getAttribute("user");
		String role = user.getRole();*/
		
		
		OperateLog operateLog = new OperateLog();
		Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
		operate.setResult(result);
		try {
			iSystemLogService.addOperate(operate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 新增操作进入日志功能代码块结束

		
		
		return "list";
	}
	
	/**
	 * 删除大厅信息
	 * @return
	 * @throws Exception
	 */
	public String  deleteDept()  throws Exception{
		HttpServletRequest request = getRequest();
		String id= StringUtils.trimToEmpty(request.getParameter("id"));
		try {
			deptService.deleteDept(id);
			request.setAttribute("msg", "大厅信息删除成功！");
			
			// 完成 删除操作进入日志功能 开始
			
			String event="删除";
			String module="基础设置";
			String moduleType="大厅基础信息设置";
			String eventType="删";
			String coreBusiness="0";
			String result="0";
			String resultDepict="删除成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
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
			
			// 删除操作进入日志功能代码块结束
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "大厅信息删除失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "dept/deptList.action");
		return "success";
	}
	
		/**
		 * 进入大厅基础信息修改页面
		 * @return
		 * @throws Exception
		 */
	public String toUpdateDept() throws Exception {
		HttpServletRequest request = this.getRequest();
		String deptname=request.getParameter("deptname");
		String depthall=request.getParameter("depthall");
		String deptcode=request.getParameter("deptcode");
		String deptcodename=request.getParameter("deptcodename");
		String serversip=request.getParameter("serversip");
		String website=request.getParameter("website");
		String id = request.getParameter("id");
		String ak = request.getParameter("ak");
		String yydd = request.getParameter("yydd");
		Dept dept=new Dept();
		dept.setId(id);
		dept.setDeptname(deptname);
		dept.setDepthall(depthall);
		dept.setDeptcode(deptcode);
		dept.setDeptcodename(deptcodename);
		dept.setServersip(serversip);
		dept.setWebsite(website);
		dept.setAk(ak);
		dept.setYydd(yydd);
		request.setAttribute("dept", deptService.toUpdateDept(dept));
		return "toUpdate";
	}
	
		/**
		 * 修改大厅基础信息
		 * @return
		 * @throws Exception
		 */
	public String updateDept() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id=StringUtils.trimToEmpty(request.getParameter("id"));
		String deptname=StringUtils.trimToEmpty(request.getParameter("deptname"));
		String depthall=StringUtils.trimToEmpty(request.getParameter("depthall"));
		String deptcode=StringUtils.trimToEmpty(request.getParameter("deptcode"));
		String deptcodename=StringUtils.trimToEmpty(request.getParameter("deptcodename"));
		String serversip=StringUtils.trimToEmpty(request.getParameter("serversip"));
		String website=StringUtils.trimToEmpty(request.getParameter("website")); 
		String ak=StringUtils.trimToEmpty(request.getParameter("ak")); 
		String yydd=StringUtils.trimToEmpty(request.getParameter("yydd")); 
		Dept dept=new Dept();
		dept.setId(id);
		dept.setDeptname(deptname);
		dept.setDepthall(depthall);
		dept.setDeptcode(deptcode);
		dept.setDeptcodename(deptcodename);
		dept.setServersip(serversip);
		dept.setWebsite(website);
		dept.setAk(ak);
		dept.setYydd(yydd);
		try {
			deptService.updateDept(dept);
			request.setAttribute("msg", "大厅信息修改成功!");
			
			// 完成 修改操作进入日志功能 开始
			
			String event="修改";
			String module="基础设置";
			String moduleType="大厅基础信息设置";
			String eventType="改";
			String coreBusiness="0";
			String result="0";
			String resultDepict="修改成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
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
			
			// 修改操作进入日志功能代码块结束
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "大厅信息修改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "dept/deptList.action");
		return "success";
	}
	
	/**
	 * 根据用户角色判断用户所能统计的范围(包含统计模式:按照用户统计,按照部门统计)
	 * 
	 */
	public String selectTJByUserRoleAndCodeOrHall(){
		CacheManager cacheManager = CacheManager.getInstance();
		Employee user = (Employee) getHttpSession().getAttribute("user");
		String role = user.getRole();
		HttpServletRequest request=getRequest();
		HashMap<String, String> deptCodeInfoMap = new HashMap<String, String>();
		HashMap<String, String> deptHallInfoMap = new HashMap<String, String>();
		String deptCode;
		String deptCodeName;
		String deptHall;
		String deptHallName;
		request.setAttribute("role", role);
		String toFlag = request.getParameter("flag");
		request.setAttribute("urlFlag", toFlag);
		if("0".equals(role)){
			try {
				List<Map<String, String>> list = deptService.findAllDeptcode();
				for(Map<String, String> itemDeptcode:list){
					deptCode=itemDeptcode.get("DEPTCODE");
					deptCodeName=itemDeptcode.get("DEPTCODENAME");
					deptCodeInfoMap.put(deptCode, deptCodeName);
				}
				request.setAttribute("deptCodeInfoMap", deptCodeInfoMap);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("1".equals(role)){
			
			try {
				deptCode=cacheManager.getOfDeptCache("deptCode");
				List<Map<String, String>> list = deptService.findDepthallbyDeptcode(deptCode);
				for(Map<String, String> itemDeptHall:list){
					deptHall=itemDeptHall.get("DEPTHALL");
					deptHallName=itemDeptHall.get("DEPTNAME");
					deptHallInfoMap.put(deptHall, deptHallName);
				}
				request.setAttribute("deptHallInfoMap", deptHallInfoMap);
				
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("2".equals(role)){
			return SUCCESS;
		}
		
		return null;
	}
	/**
	 * 根据用户角色判断用户所能统计的范围(不包含统计模式)
	 */
	public String findTJCodeAndHallByRole(){
		CacheManager cacheManager = CacheManager.getInstance();
		Employee user = (Employee) getHttpSession().getAttribute("user");
		String role = user.getRole();
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		HashMap<String, String> deptCodeInfoMap = new HashMap<String, String>();
		HashMap<String, String> deptHallInfoMap = new HashMap<String, String>();
		String deptCode;
		String deptCodeName;
		String deptHall;
		String deptHallName;
		request.setAttribute("role", role);
		String urlFlag = request.getParameter("flag");
		request.setAttribute("urlFlag", urlFlag);
		String urlPath="";
		if("0".equals(role)){
			try {
				List<Map<String, String>> list = deptService.findAllDeptcode();
				for(Map<String, String> itemDeptcode:list){
					deptCode=itemDeptcode.get("DEPTCODE");
					deptCodeName=itemDeptcode.get("DEPTCODENAME");
					deptCodeInfoMap.put(deptCode, deptCodeName);
				}
				request.setAttribute("deptCodeInfoMap", deptCodeInfoMap);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("1".equals(role)){
			
			try {
				deptCode=cacheManager.getOfDeptCache("deptCode");
				List<Map<String, String>> list = deptService.findDepthallbyDeptcode(deptCode);
				for(Map<String, String> itemDeptHall:list){
					deptHall=itemDeptHall.get("DEPTHALL");
					deptHallName=itemDeptHall.get("DEPTNAME");
					deptHallInfoMap.put(deptHall, deptHallName);
				}
				request.setAttribute("deptHallInfoMap", deptHallInfoMap);
				
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("2".equals(role)){
				if("0".equals(urlFlag)){// 窗口排队统计
				  urlPath = "ckpjtj.action?tjms=1";
				  }
				if("1".equals(urlFlag)){// 详细评价统计
					urlPath = "xxpjjl.action?tjms=1";
				  }
				if("2".equals(urlFlag)){// 窗口评价统计
					urlPath = "ckpdtj.action?tjms=1";
				  }
				if("3".equals(urlFlag)){// 员工评价统计
					urlPath = "ygpjtj.action?tjms=1";
				  }
				if("4".equals(urlFlag)){// 队列评价统计
					urlPath = "dlpjtj.action?tjms=1";
				  }
				if("5".equals(urlFlag)){// 挂起信息统计
					urlPath = "guaqitj.action?tjms=1";
				  }
				if("6".equals(urlFlag)){// 取号次数统计
					urlPath = "countIdNumber.action";
				  }
				if("7".equals(urlFlag)){// 身份证异常预警
					urlPath = "queryIdnumberDifference.action";
				  }
				if("8".equals(urlFlag)){// 员工排队统计
					urlPath = "ygpdtj.action?tjms=1";
				  }
				if("9".equals(urlFlag)){// 业务类型排队统计
					urlPath = "dlpdtj2.action?tjms=1";
				  }
				if("10".equals(urlFlag)){// 客户等待统计
					urlPath = "khddtj.action?tjms=1";
				  }
				if("11".equals(urlFlag)){// 差评信息查询
					urlPath = "badCount.action";
				  }
				if("12".equals(urlFlag)){// 代理人排队统计
					urlPath = "agentpd.action";
				}
				if("13".equals(urlFlag)){// 代理人信息查询
					urlPath = "agentmsg.action";
				}
				if("14".equals(urlFlag)){// 代理人排队情况统计
					urlPath = "agenttj.action";
				}
				if("15".equals(urlFlag)){// 部门等候时间预警
					urlPath = "getDepartWaitTimeWarning.action?flag=1&tjms=1";
				}
				if("16".equals(urlFlag)){// 部门等候时间考核
					urlPath = "getDepartWaitTimeOrder.action?flag=1&tjms=1";
				}
				if("18".equals(urlFlag)){// 窗口监控情况统计
					urlPath = "ckjktj.action?tjms=1";
				}
			try {
				response.sendRedirect(request.getContextPath()+"//"+urlPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 根据用户角色判断用户所能统计的范围(不包含统计模式,且必须选择具体大厅)
	 */
	public String findTJCodeAndHallByRole2(){
		CacheManager cacheManager = CacheManager.getInstance();
		Employee user = (Employee) getHttpSession().getAttribute("user");
		String role = user.getRole();
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		HashMap<String, String> deptCodeInfoMap = new HashMap<String, String>();
		HashMap<String, String> deptHallInfoMap = new HashMap<String, String>();
		String deptCode;
		String deptCodeName;
		String deptHall;
		String deptHallName;
		request.setAttribute("role", role);
		String urlFlag = request.getParameter("flag");
		request.setAttribute("urlFlag", urlFlag);
		String urlPath="";
		if("0".equals(role)){
			try {
				List<Map<String, String>> list = deptService.findAllDeptcode();
				for(Map<String, String> itemDeptcode:list){
					deptCode=itemDeptcode.get("DEPTCODE");
					deptCodeName=itemDeptcode.get("DEPTCODENAME");
					deptCodeInfoMap.put(deptCode, deptCodeName);
				}
				request.setAttribute("deptCodeInfoMap", deptCodeInfoMap);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("1".equals(role)){
			
			try {
				deptCode=cacheManager.getOfDeptCache("deptCode");
				List<Map<String, String>> list = deptService.findDepthallbyDeptcode(deptCode);
				for(Map<String, String> itemDeptHall:list){
					deptHall=itemDeptHall.get("DEPTHALL");
					deptHallName=itemDeptHall.get("DEPTNAME");
					deptHallInfoMap.put(deptHall, deptHallName);
				}
				request.setAttribute("deptHallInfoMap", deptHallInfoMap);
				
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("2".equals(role)){
				if("0".equals(urlFlag)){// 窗口排队统计
				  urlPath = "ckpjtj.action?tjms=1";
				  }
				if("1".equals(urlFlag)){// 详细评价统计
					urlPath = "xxpjjl.action?tjms=1";
				  }
				if("2".equals(urlFlag)){// 窗口评价统计
					urlPath = "ckpdtj.action?tjms=1";
				  }
				if("3".equals(urlFlag)){// 员工评价统计
					urlPath = "ygpjtj.action?tjms=1";
				  }
				if("4".equals(urlFlag)){// 队列评价统计
					urlPath = "dlpjtj.action?tjms=1";
				  }
				if("5".equals(urlFlag)){// 挂起信息统计
					urlPath = "guaqitj.action?tjms=1";
				  }
				if("6".equals(urlFlag)){// 取号次数统计
					urlPath = "countIdNumber.action";
				  }
				if("7".equals(urlFlag)){// 身份证异常预警
					urlPath = "queryIdnumberDifference.action";
				  }
				if("8".equals(urlFlag)){// 员工排队统计
					urlPath = "ygpdtj.action?tjms=1";
				  }
				if("9".equals(urlFlag)){// 业务类型排队统计
					urlPath = "dlpdtj2.action?tjms=1";
				  }
				if("10".equals(urlFlag)){// 客户等待统计
					urlPath = "khddtj.action?tjms=1";
				  }
				if("11".equals(urlFlag)){// 差评信息查询
					urlPath = "badCount.action";
				  }
				if("12".equals(urlFlag)){// 代理人排队统计
					urlPath = "agentpd.action";
				}
				if("13".equals(urlFlag)){// 代理人信息查询
					urlPath = "agentmsg.action";
				}
				if("14".equals(urlFlag)){// 代理人排队情况统计
					urlPath = "agenttj.action";
				}
				if("15".equals(urlFlag)){// 部门等候时间预警
					urlPath = "getDepartWaitTimeWarning.action?flag=1&tjms=1";
				}
				if("16".equals(urlFlag)){// 部门等候时间考核
					urlPath = "getDepartWaitTimeOrder.action?flag=1&tjms=1";
				}
				if("17".equals(urlFlag)){// 叫号时间间隔统计
					urlPath = "jhsjtj.action?tjms=1";
				}
				if("18".equals(urlFlag)){// 窗口评价统计
					urlPath = "ckjktj.action?tjms=1";
				}
			try {
				response.sendRedirect(request.getContextPath()+"//"+urlPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 根据部门编号查询部门下的所有大厅
	 */
	public String findDepthallbyDeptcode(){
		String deptCode = getRequest().getParameter("deptCode");
		HashMap<String, String> deptHallInfoMap =new HashMap<String, String>();
		StringBuffer html= new StringBuffer();
		HttpServletResponse response=getResponse();
		try {
			List<Map<String, String>> depthallList = deptService.findDepthallbyDeptcode(deptCode);
			if(depthallList!=null){
				html.append("<select name='deptHall' id='deptHall'>");
				html.append("<option value=''  >全部大厅</option>");
				for(Map<String, String> map:depthallList){
					deptHallInfoMap.put(map.get("DEPTHALL"), map.get("DEPTNAME"));
					html.append("<option value='"+map.get("DEPTHALL")+"'  >"+map.get("DEPTNAME")+"</option>");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String htmlString=html.toString();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(htmlString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据部门编号查询部门下的所有大厅
	 */
	public String findDepthallbyDeptcode2(){
		String deptCode = getRequest().getParameter("deptCode");
		HashMap<String, String> deptHallInfoMap =new HashMap<String, String>();
		StringBuffer html= new StringBuffer();
		HttpServletResponse response=getResponse();
		try {
			List<Map<String, String>> depthallList = deptService.findDepthallbyDeptcode(deptCode);
			if(depthallList!=null){
				html.append("<select name='deptHall' id='deptHall'>");
				html.append("<option value=''  >请选择大厅</option>");
				for(Map<String, String> map:depthallList){
					deptHallInfoMap.put(map.get("DEPTHALL"), map.get("DEPTNAME"));
					html.append("<option value='"+map.get("DEPTHALL")+"'  >"+map.get("DEPTNAME")+"</option>");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String htmlString=html.toString();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(htmlString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
