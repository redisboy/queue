package com.suntendy.queue.business.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.service.IQueueService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;

public class BusinessAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IBusinessService businessService;
	private IQueueService queueService;
	private ICodeService codeService;
	private INumberService numberService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}
	

	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	/**
	 * 业务类型设置
	 * 
	 * @throws Exception
	 */
	public String businessManage() throws Exception {
		HttpServletRequest request = getRequest();
		String ID = "", preIndex = "", business = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList(ID, preIndex, business,"", deptCode, deptHall);
		
		if (null != businessList && !businessList.isEmpty()) {
			for (int i = 0; i < businessList.size(); i++) {
				Business businessVo = businessList.get(i);
				String id = businessVo.getId();
				String operate = "<a onclick=updateBusiness('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delBusiness('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				businessVo.setOperation(operate);
			}
		}
		request.setAttribute("list", businessList);
		return SUCCESS;
	}

	/**
	 * 添加业务类型初始化
	 */
	public String into_addBusiness() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		try {
			List<Queue> list = queueService.getAllQueue(deptCode, deptHall);
			List<Business> businessList = businessService.getBusinessList("", "", "","", deptCode, deptHall);
			List<Code> listjdc = codeService.getAllGredentials("2002", deptCode, deptHall);
			List<Code> listjsr = codeService.getAllGredentials("2003", deptCode, deptHall);
			request.setAttribute("list", list);
			request.setAttribute("businessList", businessList);
			request.setAttribute("listjdc", listjdc);
			request.setAttribute("listjsr", listjsr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 添加业务类型
	 */
	public String addBusiness() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String business = request.getParameter("business");
		String queueCode = request.getParameter("queueCode");
		String isOpenTztd = request.getParameter("isOpenTztd");
		String isOpenZhiWen = request.getParameter("isOpenZhiWen");
		String flag = request.getParameter("flag").trim();
		String[] bkblyw = request.getParameterValues("bkblyw");
		String outflag = request.getParameter("outflag");
		String ywl = request.getParameter("ywl");
		String yyywmc = request.getParameter("yyywmc");
		String bdywmc = request.getParameter("bdywmc");
		String help_info = request.getParameter("help_info");
		String managemin = request.getParameter("managemin");
		String[] dybd = request.getParameterValues("dybd");
		String liuzhuan = request.getParameter("liuzhuan");
		String isautolz = request.getParameter("isautolz");
		if(ywl==null){
			ywl = "0";
		}
		String bkbl = "";
		if (bkblyw != null) {
			for (int i = 0; i < bkblyw.length; i++) {
				bkbl += ","+bkblyw[i];
			}
			bkbl = bkbl.substring(1);
			System.out.println("不可办理="+bkbl);
		}
		String biaodan = "";
		if (dybd != null) {
			for (int i = 0; i < dybd.length; i++) {
				biaodan += "H"+dybd[i];
			}
			biaodan = biaodan.substring(1);
			System.out.println("打印表单="+biaodan);
		}
		
		try {
			businessService.addBusiness(business, queueCode, flag, isOpenTztd, isOpenZhiWen,bkbl,outflag,
					deptCode, deptHall,ywl,help_info,managemin,biaodan,yyywmc,bdywmc,liuzhuan,isautolz);
			request.setAttribute("msg", "业务类型添加成功！");
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="基础设置";
			String moduleType="业务类型设置";
			String eventType="增";
			String coreBusiness="0";
			String result="0";
			String resultDepict="新增成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
			String userName =user.getCode();
			
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
			request.setAttribute("msg", "业务类型添加失败，<br>在执行过程中发生异常！");
			e.printStackTrace();
		}
		request.setAttribute("action", "business.action");
		return SUCCESS;

	}

	/**
	 * 删除业务类型
	 */
	public String delBusiness() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		int updateflag=0;
		Business bus=businessService.queryBusiness(id, deptCode, deptHall);
		if(bus==null || bus.equals("")){
			updateflag = businessService.delBusiness(id);
		}else{
			updateflag=2;
		}
		if (updateflag == 1) {
			// 队列信息修改成功
			request.setAttribute("msg", "业务类型删除成功！");
			
			// 完成 删除操作进入日志功能 开始
			
			String event="删除";
			String module="基础设置";
			String moduleType="业务类型设置";
			String eventType="删";
			String coreBusiness="0";
			String result="0";
			String resultDepict="删除成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
			String userName =user.getCode();
			
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
			
			
			
			
			
			
			
		} else if(updateflag == 2){
			request.setAttribute("msg", "请确认该业务不存在未叫、在办、挂起，再作删除操作！");
		} else {
			request.setAttribute("msg", "业务类型删除失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "business.action");
		return SUCCESS;
	}

	/**
	 * 更改业务类型
	 */
	public String updateBusiness() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		List<Business> businessList = businessService.getBusinessList(id, "", "","",deptCode, deptHall);
		if (null != businessList && !businessList.isEmpty()) {
			Business businessVo = businessList.get(0);
			if (businessVo.getBkbl() != null) {
				String bkbl = businessVo.getBkbl();
				String[] bkblyw = bkbl.split(",");
				request.setAttribute("bkblyw", bkblyw);
			}
			if (businessVo.getBiaodan() != null) {
				String biaodan = businessVo.getBiaodan();
				String[] dybd = biaodan.split("H");
				request.setAttribute("dybd", dybd);
			}
			String name = businessVo.getName();
			String queueCode = businessVo.getQueueCode();
			String flag = businessVo.getFlag();
			String isOpenTztd = businessVo.getIsOpenTztd();
			String isOpenZhiWen = businessVo.getIsOpenZhiWen();
			String user_bkblyw = businessVo.getBkbl();
			String outflag = businessVo.getOutflag();
			String ywl = businessVo.getYwl();
			String help_info =  businessVo.getHelp_info();
			String managemin=businessVo.getManagemin();
			String yw_dybd = businessVo.getBiaodan();
			String yyywmc = businessVo.getYyywmc();
			String bdywmc = businessVo.getBdywmc();
			String liuzhuan = businessVo.getLiuzhuan();
			String isautolz = businessVo.getIsautolz();
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("queueCode", queueCode);
			request.setAttribute("isOpenTztd", isOpenTztd);
			request.setAttribute("isOpenZhiWen", isOpenZhiWen);
			request.setAttribute("flag", flag.trim());
			request.setAttribute("user_bkblyw", user_bkblyw);
			request.setAttribute("outflag", outflag);
			request.setAttribute("ywl", ywl);
			request.setAttribute("help_info", help_info);
			request.setAttribute("managemin", managemin);
			request.setAttribute("yw_dybd", yw_dybd);
			request.setAttribute("yyywmc", yyywmc);
			request.setAttribute("bdywmc", bdywmc);
			request.setAttribute("liuzhuan", liuzhuan);
			request.setAttribute("isautolz", isautolz);
		}
		
		try {
			List<Queue> list = queueService.getAllQueue(deptCode, deptHall);
			List<Code> listjdc = codeService.getAllGredentials("2002", deptCode, deptHall);
			List<Code> listjsr = codeService.getAllGredentials("2003", deptCode, deptHall);
			List<Business> businessListVo = businessService.getBusinessList("", "", "","", deptCode, deptHall);
			List<Business> buslist = new ArrayList<Business>();
			for (int i = 0; i < businessListVo.size(); i++) {
				Business bus = businessListVo.get(i);
				if (id != bus.getId() && !id.equals(bus.getId())) {
					buslist.add(bus);
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("listjdc", listjdc);
			request.setAttribute("listjsr", listjsr);
			request.setAttribute("businessListVo", buslist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String updateByID() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String business = request.getParameter("business");
		String queueCode = request.getParameter("queueCode");
		String flag = request.getParameter("flag");
		String isOpenTztd = request.getParameter("isOpenTztd");
		String isOpenZhiWen = request.getParameter("isOpenZhiWen");
		String[] bkblyw = request.getParameterValues("bkblyw");
		String outflag = request.getParameter("outflag");
		String ywl = request.getParameter("ywl");
		String yyywmc = request.getParameter("yyywmc");
		String bdywmc = request.getParameter("bdywmc");
		String help_info = request.getParameter("help_info");
		String managemin = request.getParameter("managemin");
		String[] dybd = request.getParameterValues("dybd");
		String liuzhuan = request.getParameter("liuzhuan");
		String isautolz = request.getParameter("isautolz");
		String bkbl = "";
		if (bkblyw != null) {
			for (int i = 0; i < bkblyw.length; i++) {
				bkbl += ","+bkblyw[i];
			}
			bkbl = bkbl.substring(1);
		}
		String biaodan = "";
		if (dybd != null) {
			for (int i = 0; i < dybd.length; i++) {
				biaodan += "H"+dybd[i];
			}
			biaodan = biaodan.substring(1);
			System.err.println("打印表单="+biaodan);
		}
		int updateflag = businessService.updateBusinessByID(id, business, queueCode, flag, isOpenTztd, isOpenZhiWen,bkbl,outflag,ywl,help_info,managemin,biaodan,yyywmc,bdywmc,liuzhuan,isautolz);
		if (updateflag == 1) {
			request.setAttribute("msg", "业务类型更改成功！");
			
			// 完成 修改操作进入日志功能 开始
			
			String event="修改";
			String module="基础设置";
			String moduleType="业务类型设置";
			String eventType="改";
			String coreBusiness="0";
			String result="0";
			String resultDepict="修改成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
			String userName =user.getCode();
			
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
			
			
			
			
		} else {
			request.setAttribute("msg", "业务类型更改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "business.action");
		return SUCCESS;
	}
	
	/**
	 * 等待区域设置
	 * 
	 * @throws Exception
	 */
	public String waitingAreaManage() throws Exception {
		HttpServletRequest request = getRequest();
		String ID = "", preIndex = "", business = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList(ID, preIndex, business,"", deptCode, deptHall);
		List<Business> businessListVO = new ArrayList<Business>();
		
		if (null != businessList && !businessList.isEmpty()) {
			for (int i = 0; i < businessList.size(); i++) {
				Business businessVo = businessList.get(i);
				if (null !=businessVo.getWaitingarea() &&!businessVo.getWaitingarea().isEmpty()) {
					businessListVO.add(businessVo);
					String id = businessVo.getId();
					String operate = "<a onclick=updateWaitingarea('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delWaitingarea('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
					businessVo.setOperation(operate);
				}
			}
		}
		request.setAttribute("list", businessListVO);
		return SUCCESS;
	}
	
	/**
	 * 添加等待区域初始化
	 * @throws Exception 
	 *
	 */
	public String into_insertWaitingarea() throws Exception {
		HttpServletRequest request = getRequest();
		String ID = "", preIndex = "", business = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList(ID, preIndex, business,"", deptCode, deptHall);
		request.setAttribute("list", businessList);
		return SUCCESS;
	}
	/**
	 * 修改等待区域初始化
	 * @throws Exception 
	 */
	public String into_updateWaitingarea() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> businessList = businessService.getBusinessList(id, "", "","", deptCode, deptHall);
		if (null != businessList && !businessList.isEmpty()) {
			Business businessVo = businessList.get(0);
			request.setAttribute("id", id);
			request.setAttribute("waitingarea", businessVo.getWaitingarea());
			request.setAttribute("name", businessVo.getName());
		}
		return SUCCESS;
	}
	/**
	 * 更改等待区域
	 * @param businessService
	 */
	public String updateWaitingarea() throws Exception{
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String waitingarea = request.getParameter("waitingarea");
		try {
			businessService.updatewaitingareaByID(id, waitingarea);
			request.setAttribute("msg", "等待区域操作成功！");
		} catch (Exception e) {
			request.setAttribute("msg", "等待区域操作失败，<br>在执行过程中发生异常！");
			e.printStackTrace();
		}
		request.setAttribute("action", "waitingarea.action");
		return SUCCESS;
	}
	/**
	 * 删除等待区域
	 */
	public String delWaitingarea() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String waitingarea="";
		try {
			businessService.updatewaitingareaByID(id, waitingarea);
			request.setAttribute("msg", "等待区域删除成功！");
		} catch (Exception e) {
			request.setAttribute("msg", "等待区域删除失败，<br>在执行过程中发生异常！");
			e.printStackTrace();
		} 
		request.setAttribute("action", "waitingarea.action");
		return SUCCESS;
	}
	/**
	 * 判断取号量是否大于设置的最大取号量
	 */
	public void pdqhl() throws Exception {
		long l = System.currentTimeMillis();
	    Date date = new Date(l);//new日期对象
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化当前时间
	    String now=dateFormat.format(date);    //获取当前时间
	    int hour = Integer.parseInt(now.substring(11, 13));    //获取当前小时
	    System.err.println(hour);
	    /*
	    GregorianCalendar  c= new GregorianCalendar();
        int apm = c.get(GregorianCalendar.AM_PM);
        String apm1=(apm==0?"上午":"下午");*/
	    
		HttpServletRequest request = getRequest();
		String ywid = request.getParameter("ywid");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String flag = "";
		try {
			List<Business> list = businessService.getBusinessList(ywid, null, null, null, deptCode, deptHall);
			if(null!=list){
				Business business = list.get(0);
				String ywl = business.getYwl();
				Number number = new Number();
				number.setId(ywid);
				number.setDeptCode(deptCode);
				number.setDeptHall(deptHall);
				
				String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
				String currentTime1 = "'" + currentTime + " 6:00:00" + "'";
				String currentTime2 = "'" + currentTime + " 12:00:00" + "'";
				String currentTime3 = "'" + currentTime + " 14:00:00" + "'";
				String currentTime4 = "'" + currentTime + " 18:00:00" + "'";
				String AmTime1 = "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')";
				String AmTime2 = "to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')";
				String PmTime3 = "to_date(" + currentTime3 + ",'yyyy-mm-dd hh24:mi:ss')";
				String PmTime4 = "to_date(" + currentTime4 + ",'yyyy-mm-dd hh24:mi:ss')";
				if(ywl.indexOf(".")!=-1){
						String[] apmywl=ywl.split("\\.");
						if(hour>6 && hour<12){
							number.setBeginTime(AmTime1);
							number.setEndTime(AmTime2);
							String count1 = numberService.findByValuerecord(number);
							if (Integer.parseInt(apmywl[0])>Integer.parseInt(count1)) {
								flag="true";
							}else {
								flag="false";
							}
						}else if(hour>=12 && hour<14){
							number.setValuetime(PmTime3);
							number.setEndTime(AmTime2);
							String count2 = numberService.findByValuerecord(number);
							if (Integer.parseInt(apmywl[1])>Integer.parseInt(count2)) {
								flag="true";
							}else {
								flag="false";
							}
						}else if(hour>=14 && hour<19){
							number.setEndtime(PmTime3);
							number.setEndTime(PmTime4);
							String count3 = numberService.findByValuerecord(number);
							if (Integer.parseInt(apmywl[2])>Integer.parseInt(count3)) {
								flag="true";
							}else {
								flag="false";
							}
						}
				}else{
					number.setRztime("1");
					String count = numberService.findByValuerecord(number);
					 if (Integer.parseInt(ywl)>Integer.parseInt(count)) {
							flag="true";
						}else {
							flag="false";
						}
				}
				getResponse().getWriter().write(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public void setQueueService(IQueueService queueService) {
		this.queueService = queueService;
	}

	public ICodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	
}