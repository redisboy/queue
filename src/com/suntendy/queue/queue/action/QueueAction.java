package com.suntendy.queue.queue.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.IQueueService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

public class QueueAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IQueueService queueService;
	private IBusinessService businessService;
	private ISystemLogService systemLogService;

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	/*
	 * 队列设置
	 */
	public String dlsz() {
		HttpServletRequest request = getRequest();
		List<Queue> list1 = new ArrayList<Queue>();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serialNumType = cacheManager.getSystemConfig("serialNumType");
		
		try {
			list1 = queueService.getAllQueue(deptCode, deptHall);
			
			if ("2".equals(serialNumType) && null != list1 && !list1.isEmpty()) {
				for (int i = 0; i < list1.size(); i++) {
					Queue queueVo = list1.get(i);
					String operate = "<a onclick=updateQueue('" + queueVo.getId()
							+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand'></a>"
							+ "&nbsp;" + "<a onclick=delQueue('" + queueVo.getId() + "','" + queueVo.getCode()
							+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
					queueVo.setOperation(operate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list1", list1);
		request.setAttribute("serialNumType", serialNumType);
		return SUCCESS;
	}

	/*
	 * 初始化修改队列
	 */
	public String updateQueue() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String id = request.getParameter("id");
		List<Queue> queueList = queueService.getByCode(id);
		String queueCode="";
		if (!queueList.isEmpty()) {
			Queue queueVo = queueList.get(0);
			String name = queueVo.getName();
			String code2 = queueVo.getCode();
			String preNum = queueVo.getPrenum();
			String nextQueueName = queueVo.getNextQueueName();
			queueCode = nextQueueName;
			String nextType = queueVo.getNextType();
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("code", code2);
			request.setAttribute("preNum", preNum);
			request.setAttribute("nextQueueName", nextQueueName);
			request.setAttribute("nextType", nextType);
		}
			List<Business> businessList = businessService.getBusinessList("", "", "",queueCode, deptCode, deptHall);
			request.setAttribute("businessList", businessList);
			List list = queueService.getAllQueue(deptCode, deptHall);
			request.setAttribute("list", list);
		return SUCCESS;
	}

	/*
	 * 修改队列
	 */
	public String updateByCode() throws Exception{
		HttpServletRequest request = getRequest();
		Queue queue = new Queue();
		queue.setId(request.getParameter("id"));
		queue.setCode(request.getParameter("code"));
		queue.setName(request.getParameter("name"));
		queue.setPrenum(request.getParameter("preNum"));
		queue.setNextQueueName(request.getParameter("nextQueueName"));
		queue.setNextType(request.getParameter("nextType"));
		
		int updateflag = 0;
		try {
			updateflag = queueService.updateByCode(queue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (updateflag == 1) {
			// 队列信息修改成功
			request.setAttribute("msg", "队列信息修改成功！");
			
			// 完成 修改操作进入日志功能 开始
			
			String event="修改";
			String module="基础设置";
			String moduleType="队列设置";
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
				systemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 修改操作进入日志功能代码块结束
			

		} else {
			request.setAttribute("msg", "队列信息修改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "dlsz.action");
		return SUCCESS;
	}

	/*
	 * 删除队列
	 */
	public String delQueue() {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		int delflag = 0;
		
		try {
		Queue queue=queueService.queryQueue(code, deptCode, deptHall);
			if(queue==null){
				delflag = queueService.delQueue(id, code);
			}else{
				delflag=2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (1 == delflag) {
			request.setAttribute("msg", "队列删除成功！");
			
			// 完成 删除操作进入日志功能 开始
			
			String event="删除";
			String module="基础设置";
			String moduleType="队列设置";
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
			
			try {
				Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
				operate.setResult(result);
				systemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 删除操作进入日志功能代码块结束
			
			
			
		}else if(2==delflag){
			request.setAttribute("msg", "队列删除失败，<br>请确保业务类型设置中不存在该队列！");
		}
		else {
			request.setAttribute("msg", "队列删除失败，<br>在执行过程中发生异常！");
		}

		request.setAttribute("action", "dlsz.action");
		return SUCCESS;
	}
	
	
	/*
	 * 根据队列编号获取业务类型
	 */
	public String getBuseinessByQueueCode() throws Exception {
		HttpServletRequest request = getRequest();
		String queueCode = request.getParameter("queueCode");
		String ID = "", preIndex = "", business = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList(ID, preIndex, business,queueCode, deptCode, deptHall);
		JSONObject datas = new JSONObject();
		if (null != businessList && !businessList.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (Business bunes : businessList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("businessId", bunes.getId());
					obj.put("businessName", bunes.getName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "获取大厅信息失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}

	/*
	 * 添加队列
	 */
	public String into_CreateQueue() throws Exception {
		
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List list = queueService.getAllQueue(deptCode, deptHall);
		request.setAttribute("list", list);
		return SUCCESS;
	}

	public String addQueue() {
		HttpServletRequest request = getRequest();
		String name = request.getParameter("name");
		CacheManager cacheManager = CacheManager.getInstance();
		
		Queue queue = new Queue();
		queue.setName(name);
		queue.setBeginNum("1");
		queue.setCallname(name);
		queue.setCode(request.getParameter("code"));
		queue.setPrenum(request.getParameter("prenum"));
		queue.setNextQueueName(request.getParameter("nextQueueName"));
		queue.setNextType(request.getParameter("nextType"));
		queue.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		queue.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		String updateflag = "";
		try {
			queueService.addQueue(queue);
			updateflag = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (updateflag.equals("1")) {
			request.setAttribute("msg", "队列添加成功！");
			
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="基础设置";
			String moduleType="队列设置";
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

			try {
				Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
				operate.setResult(result);
				systemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 新增操作进入日志功能代码块结束
			
			
			
			
			
	
			
			
		} else {
			request.setAttribute("msg", "队列添加失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "dlsz.action");
		return SUCCESS;
	}
	
	
	public void setQueueService(IQueueService queueService) {
		this.queueService = queueService;
	}
}
