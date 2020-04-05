package com.suntendy.queue.window.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.suntendy.core.util.lang.StringUtil;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.evaluation.domain.ValueRecord;
import com.suntendy.queue.evaluation.service.ISetEvaluationService;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.service.IQueueService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.Screen;
import com.suntendy.queue.window.service.ISetWindowService;

public class SetWindowAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISetWindowService setWindowService;
	private ISetEvaluationService setEvaluationService;
	private IBusinessService businessService;
	private INumberService numberService;
	private DeptService deptService;
	private ISystemLogService systemLogService;

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public void setSetEvaluationService(ISetEvaluationService setEvaluationService) {
		this.setEvaluationService = setEvaluationService;
	}

	/*
	 * 获取窗口信息
	 */
	public String getWindow() {
		HttpServletRequest request = getRequest();
		//判断查询的窗口类型 1：除子窗口外的所有窗口  2：子窗口  3：除主窗口外的所有窗口
		String type = request.getParameter("type");
		List<Screen> list = new ArrayList<Screen>();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		try {
			list = setWindowService.getWindowList(deptCode, deptHall, "", "", type,"","");
			
			if (null != list && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Screen screenVo = list.get(i) ;
					String allowNoValue = screenVo.getAllownovalue();
					if (StringUtils.isNotEmpty(allowNoValue)) {
						if(allowNoValue.equals("1")){
							screenVo.setAllownovalue("不强制评价");
						} else{
							screenVo.setAllownovalue("强制评价");
						}
					}
					
					if(StringUtils.isEmpty(screenVo.getQueueId()) || screenVo.getQueueId().startsWith(",")){
						screenVo.setQueueId("");
					}
					Integer id = screenVo.getId();
					String operate = "<a onclick=updateWindow('" + id.intValue()
							+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand'></a>&nbsp;"
							//刘东 modify
							+ "<a onclick=delWindow('" + id.intValue() + "','"
							+ (StringUtils.isEmpty(screenVo.getBarip()) ? screenVo.getWindowId() : screenVo.getBarip())
							+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
					screenVo.setOperation(operate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list); //将查询到窗口信息list返回页面
		return SUCCESS;
	}

	/**
	 * 初始化更改(子)窗口信息
	 * @return
	 * @throws Exception 
	 */
	public String init_updateWindow() throws Exception{
		HttpServletRequest request = getRequest();
		Employee currentUser = (Employee)getHttpSession().getAttribute("user");
		String id=request.getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		List<Screen> addressList= setWindowService.getAddress();
		List<ValueRecord> list = setEvaluationService.getValidPj(deptCode,deptHall);
		List<Screen> screenList = setWindowService.getWindowList(deptCode, deptHall, id, "", "","","");
		List<Business> businessList = businessService.getBusinessList("", "", "", "", deptCode, deptHall);
		
		request.setAttribute("list", list);
		request.setAttribute("addressList", addressList);   //子窗口中select标签中的父窗口地址
		request.setAttribute("screen", screenList.get(0));
		request.setAttribute("businessList", businessList);   //添加页面中可办理业务
		//判断是否是suntendy用户，用于显示是否启用接口项
		String userCode = "1";
		if("suntendy".equals(currentUser.getCode())){
			userCode ="0";
		}
		request.setAttribute("userCode", userCode);
		request.setAttribute("isUseOldDevice", cacheManager.getSystemConfig("isUseOldDevice"));
		return SUCCESS;
	}
	
	/*
	 * 删除窗口
	 * @return
	 */
	public String delWindow(){
		HttpServletRequest request = getRequest();
		//屏幕信息
		String id = request.getParameter("id");
		String ip = request.getParameter("ip");
		
		try {
			setWindowService.delScreen(id, ip);
			request.setAttribute("msg", "窗口删除成功！");
			
			// 删除功能日志 代码块开始
			
			
			String event="删除";
			String module="基础设置";
			String moduleType="窗口设置";
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
				systemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// 删除功能日志 代码块开始
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "窗口删除失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "cksz.action?type=1");		
		return SUCCESS;
	}
	
	/*
	 * 删除子窗口
	 * @return
	 */
	public String delLevelWindow(){
		HttpServletRequest request = getRequest();
		//屏幕信息
		String id = request.getParameter("id");
		String ip = request.getParameter("ip");
		
		try {
			setWindowService.delScreen(id, ip);
			request.setAttribute("msg", "窗口删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "窗口删除失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "zcksz.action?type=2");		
		return SUCCESS;
		
	}
	
	/**
	 * 更改(子)窗口信息
	 * @return
	 * @throws Exception 
	 */
	public String updateWindowByCode() throws Exception{
		HttpServletRequest request = getRequest();
		String[] business = request.getParameterValues("queue");
		String menuAddress = request.getParameter("menuAddress");
		String businessid = "";
		if (null != business) {
			for (int i = 0; i < business.length; i++) {
				businessid += "," + business[i];
			}
			businessid = businessid.substring(1);
		}
		
		Screen screenVo = new Screen();
		screenVo.setBusinessid(businessid);
		screenVo.setContent(request.getParameter("content"));
		screenVo.setContent3(request.getParameter("content3"));
		screenVo.setContent4(request.getParameter("content4"));
		screenVo.setId(new Integer(request.getParameter("id")));
		screenVo.setMenuAddress(StringUtils.trimToEmpty(menuAddress));
		String ledWindowHeight = request.getParameter("ledWindowHeight");
		if(null == ledWindowHeight || ledWindowHeight.equals("")){
			ledWindowHeight = "0";
		}
		screenVo.setLedWindowHeight(ledWindowHeight);
		String ledWindowWidth = request.getParameter("ledWindowWidth");
		if(null == ledWindowWidth || ledWindowWidth.equals("")){
			ledWindowWidth = "0";
		}
		screenVo.setLedWindowWidth(ledWindowWidth);
		screenVo.setCom2(StringUtils.trimToEmpty(request.getParameter("com2")));
	    screenVo.setWord(StringUtils.trimToEmpty(request.getParameter("word")));
	    screenVo.setOldIP(StringUtils.trimToEmpty(request.getParameter("oldIP")));
		screenVo.setColor(StringUtils.trimToEmpty(request.getParameter("color")));
		screenVo.setAlign(StringUtils.trimToEmpty(request.getParameter("align")));
		screenVo.setSpeed(StringUtils.trimToEmpty(request.getParameter("speed")));
	    screenVo.setComnum(StringUtils.trimToEmpty(request.getParameter("com1")));
	    screenVo.setColor1(StringUtils.trimToEmpty(request.getParameter("color1")));
	    screenVo.setColor2(StringUtils.trimToEmpty(request.getParameter("color2")));
	    screenVo.setColor3(StringUtils.trimToEmpty(request.getParameter("color3")));
	    screenVo.setShowNum(StringUtils.trimToEmpty(request.getParameter("showNum")));
		screenVo.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
		screenVo.setBarip(StringUtils.trimToEmpty(request.getParameter("ipaddress")));
		screenVo.setCkip(StringUtils.trimToEmpty(request.getParameter("ckip")));
		screenVo.setAllownovalue(StringUtils.trimToEmpty(request.getParameter("pj")));
		screenVo.setStoptime(StringUtils.trimToEmpty(request.getParameter("stoptime")));
		screenVo.setInterval(StringUtils.trimToEmpty(request.getParameter("interval")));
		screenVo.setPriority(StringUtils.trimToEmpty(request.getParameter("priority")));
		screenVo.setWindowId(StringUtils.trimToEmpty(request.getParameter("windowId")));
		screenVo.setNextWindow(StringUtils.trimToEmpty(request.getParameter("nextWindow")));
		screenVo.setValuemust(StringUtils.trimToEmpty(request.getParameter("defaultvalue")));
		screenVo.setLattice(StringUtils.trimToEmpty(request.getParameter("lattice")));
		String openInterFace = StringUtils.trimToEmpty(request.getParameter("openInterFace"));
		if(null == openInterFace || openInterFace.equals("")){
			openInterFace = "0";
		}
		screenVo.setOpenInterFace(openInterFace);
		screenVo.setIsOpenOldDevice(StringUtils.trimToEmpty(request.getParameter("isOpenOldDevice")));
		screenVo.setIsOpenInformation(StringUtils.trimToEmpty(request.getParameter("isOpenInformation")));
		screenVo.setWindowGDContent(StringUtils.trimToEmpty(request.getParameter("windowGDContent")));
		screenVo.setIsgd(StringUtils.trimToEmpty(request.getParameter("isgd")));
		screenVo.setThreegd(StringUtils.trimToEmpty(request.getParameter("threegd")));
		screenVo.setFourgd(StringUtils.trimToEmpty(request.getParameter("fourgd")));
	
		try {
			setWindowService.updateScreenByCode(screenVo);
			request.setAttribute("msg", "窗口更改成功！");
			
			
			// 修改功能 日志代码块开始
			String event="修改";
			String module="基础设置";
			String moduleType="窗口设置";
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
			
			//修改 功能日志 代码块结束
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "窗口更改失败，<br>在执行过程中发生异常！");
		}
		if (StringUtils.isNotEmpty(menuAddress)) {
			request.setAttribute("action", "zcksz.action?type=2");
		} else {
			request.setAttribute("action", "cksz.action?type=1");
		}
		return SUCCESS;
	}
	
	/*
	 * 跳转添加子窗口
	 */
	public String add() throws Exception{
		HttpServletRequest request = getRequest();
		Employee currentUser = (Employee)getHttpSession().getAttribute("user");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		List<Screen> addressList= setWindowService.getAddress();
		List<ValueRecord> valuelist = setEvaluationService.getValidPj(deptCode, deptHall);
		List<Business> businessList = businessService.getBusinessList("", "", "", "", deptCode, deptHall);
		
		request.setAttribute("list1", businessList); //添加页面中可办理业务
		request.setAttribute("valuelist", valuelist);
		request.setAttribute("addressList", addressList);
		//判断是否是suntendy用户，用于显示是否启用接口项
		String userCode = "1";
		if("suntendy".equals(currentUser.getCode())){
			userCode ="0";
		}
		request.setAttribute("userCode", userCode);
		request.setAttribute("isUseOldDevice", cacheManager.getSystemConfig("isUseOldDevice"));
		return SUCCESS;
	}
	
	/**
	 * 添加(子)窗口信息
	 * @return
	 * @throws Exception 
	 */
	public String addWindow() throws Exception {
		HttpServletRequest request = getRequest();
		String menuAddress = request.getParameter("menuAddress");
		String[] business = request.getParameterValues("business");
		String businessid = "";
		if (business != null) {
			for (int i = 0; i < business.length; i++) {
				businessid += "," + business[i];
			}
			businessid = businessid.substring(1);
		}
		
		Screen screenVo = new Screen();
		screenVo.setBusinessid(businessid);
		screenVo.setContent(request.getParameter("content"));
		screenVo.setContent3(request.getParameter("content3"));
		screenVo.setContent4(request.getParameter("content4"));
		screenVo.setMenuAddress(StringUtils.trimToEmpty(menuAddress));
		String ledWindowHeight = request.getParameter("ledWindowHeight");
		if(null == ledWindowHeight || ledWindowHeight.equals("")){
			ledWindowHeight = "0";
		}
		screenVo.setLedWindowHeight(ledWindowHeight);
		String ledWindowWidth = request.getParameter("ledWindowWidth");
		if(null == ledWindowWidth || ledWindowWidth.equals("")){
			ledWindowWidth = "0";
		}
		screenVo.setLedWindowWidth(ledWindowWidth);
		screenVo.setCom2(StringUtils.trimToEmpty(request.getParameter("com2")));
	    screenVo.setWord(StringUtils.trimToEmpty(request.getParameter("word")));
		screenVo.setColor(StringUtils.trimToEmpty(request.getParameter("color")));
		screenVo.setAlign(StringUtils.trimToEmpty(request.getParameter("align")));
		screenVo.setSpeed(StringUtils.trimToEmpty(request.getParameter("speed")));
	    screenVo.setComnum(StringUtils.trimToEmpty(request.getParameter("com1")));
	    screenVo.setColor1(StringUtils.trimToEmpty(request.getParameter("color1")));
	    screenVo.setColor2(StringUtils.trimToEmpty(request.getParameter("color2")));
	    screenVo.setColor3(StringUtils.trimToEmpty(request.getParameter("color3")));
	    screenVo.setShowNum(StringUtils.trimToEmpty(request.getParameter("showNum")));
		screenVo.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
		screenVo.setBarip(StringUtils.trimToEmpty(request.getParameter("ipaddress")));
		screenVo.setCkip(StringUtils.trimToEmpty(request.getParameter("ckip")));
		screenVo.setAllownovalue(StringUtils.trimToEmpty(request.getParameter("pj")));
		screenVo.setStoptime(StringUtils.trimToEmpty(request.getParameter("stoptime")));
		screenVo.setInterval(StringUtils.trimToEmpty(request.getParameter("interval")));
		screenVo.setPriority(StringUtils.trimToEmpty(request.getParameter("priority")));
		screenVo.setWindowId(StringUtils.trimToEmpty(request.getParameter("windowId")));
		screenVo.setNextWindow(StringUtils.trimToEmpty(request.getParameter("nextWindow")));
		screenVo.setValuemust(StringUtils.trimToEmpty(request.getParameter("defaultvalue")));
		screenVo.setLattice(StringUtils.trimToEmpty(request.getParameter("lattice")));
		String openInterFace = StringUtils.trimToEmpty(request.getParameter("openInterFace"));
		if(null == openInterFace || openInterFace.equals("")){
			openInterFace = "0";
		}
		screenVo.setOpenInterFace(openInterFace);
		screenVo.setIsOpenOldDevice(StringUtils.trimToEmpty(request.getParameter("isOpenOldDevice")));
		screenVo.setIsOpenInformation(StringUtils.trimToEmpty(request.getParameter("isOpenInformation")));
		screenVo.setWindowGDContent(StringUtils.trimToEmpty(request.getParameter("windowGDContent")));
		screenVo.setIsgd(StringUtils.trimToEmpty(request.getParameter("isgd")));
		screenVo.setThreegd(StringUtils.trimToEmpty(request.getParameter("threegd")));
		screenVo.setFourgd(StringUtils.trimToEmpty(request.getParameter("fourgd")));
		
		try {
			setWindowService.addScreen(screenVo); // 添加窗口
			request.setAttribute("msg", "窗口添加成功！");
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="基础设置";
			String moduleType="窗口设置";
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
				systemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 新增操作进入日志功能代码块结束
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "窗口添加失败，<br>在执行过程中发生异常！");
		}
		if (StringUtils.isNotEmpty(menuAddress)) {
			request.setAttribute("action", "zcksz.action?type=2");
		} else {
			request.setAttribute("action", "cksz.action?type=1");
		}
		return SUCCESS;
	}
	
	/*
	 * 查询窗口当前状态及业务办理数量
	 */
	public String getAddressAndStatuss() throws Exception {
		JSONObject obj = new JSONObject();
		Number countnum = new Number();
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		String deptCode = "";
		String tempDeptCode="";
		String deptCodeName = "";
		String deptHall = "";
		String deptHallName = "";
		int rowSpan =0;
		String qhrs,yjhrs,wjhrs,ckInfo;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		if("0".equals(role)){//超级管理员
			List<Map<String,String>> codeList = deptService.findAllDeptcode(); //查询有多少管理部门
			
			for(Map<String, String> itemDeptCode:codeList){
				deptCode=itemDeptCode.get("DEPTCODE");
				deptCodeName=itemDeptCode.get("DEPTCODENAME");
				if(!tempDeptCode.equals(deptCode)){
					tempDeptCode=deptCode;
					rowSpan=0;
					ArrayList<JSONObject> jsonList2 = new ArrayList<JSONObject>();
					List<Map<String,String>> hallList = deptService.findDepthallbyDeptcode(deptCode);  //根据部门ID查询有多少下属大厅
					for(Map<String,String> itemDeptHall:hallList){
						deptHall=itemDeptHall.get("DEPTHALL");
						deptHallName=itemDeptHall.get("DEPTNAME");
						countnum.setDeptCode(deptCode);
						countnum.setDeptHall(deptHall);
						String numberTime=numberService.showyisj(deptCode, deptHall);//预计办理时间
						StringBuffer infoStringBuffer=new StringBuffer(); //接收取号,已叫号,未叫号,业务类型及对应人数的信息
						qhrs="";yjhrs="";wjhrs="";ckInfo="";     //每次写入前都清空数据
						List<Number> numbersCountList = numberService.showWaitRs(countnum);//这个才是查询业务类型及所对应的等待人数
						List<Screen> shulList = setWindowService.getCountShul(deptCode,deptHall);//查询取号总共人数，叫号人数，未叫号人数
						if(null != shulList.get(0).getQhrs() && !"".equals(shulList.get(0).getQhrs())){
							qhrs ="总取号人数:"+shulList.get(0).getQhrs()+"@";
							
						}else {
							qhrs ="总取号人数:0@";
						}
						if(null != shulList.get(0).getJhsl() && !"".equals(shulList.get(0).getJhsl())){
							yjhrs ="已叫号人数:"+shulList.get(0).getJhsl()+"@";
						}else {
							yjhrs ="已叫号人数:0@";
						}
						if(null != shulList.get(0).getWjhsl() && !"".equals(shulList.get(0).getWjhsl())){
							wjhrs ="未叫号人数:"+shulList.get(0).getWjhsl()+"@";
						}else {
							wjhrs ="未叫号人数:0@";
						}
						
						infoStringBuffer.append(qhrs).append(yjhrs).append(wjhrs);
						if(numberTime!=null){
						String[] unmT=numberTime.split("\\.");
							if(unmT[0].isEmpty()){
								String numTime="预计办结时间:0"+numberTime+"小时"+"@";
								infoStringBuffer.append(numTime);
								rowSpan+=1;
							}else{
								String numTime="预计办结时间:"+numberTime+"小时"+"@";
								infoStringBuffer.append(numTime);
								rowSpan+=1;
							}							
						}
						rowSpan+=3;
						if(null != numbersCountList && numbersCountList.size()>0){
							for (Number nu : numbersCountList) {
								infoStringBuffer.append(nu.getTypeName()+":"+nu.getTypeCount()+"@");
								rowSpan+=1;
							}
						}else {
							infoStringBuffer.append("未有号码需要办理:@");
							rowSpan+=1;
						}
						
						
						JSONObject obj3 = new JSONObject();
						obj3.put("deptHall", deptHall);
						obj3.put("deptHallName", deptHallName);
						obj3.put("info", infoStringBuffer.toString());
						jsonList2.add(obj3);
					}
					JSONObject obj2 = new JSONObject();
					obj2.put("rowSpan", rowSpan);
					obj2.put("deptCode", deptCode);
					obj2.put("deptCodeName", deptCodeName);
					obj2.put("hallInfo", jsonList2);
					jsonList.add(obj2);
				}
				
				
			}
			obj.put("result", jsonList);
		}else if("1".equals(role)) {//部门系统管理员
			deptCode=cacheManager.getOfDeptCache("deptCode");
			List<Map<String, String>> hallList = deptService.findDepthallbyDeptcode(deptCode);
			
			for(Map<String, String> itemDeptHall:hallList){
				deptHall=itemDeptHall.get("DEPTHALL");
				deptHallName=itemDeptHall.get("DEPTNAME");
				countnum.setDeptCode(deptCode);
				countnum.setDeptHall(deptHall);				
				StringBuffer infoStringBuffer=new StringBuffer(); //接收取号,已叫号,未叫号,业务类型及对应人数的信息
				qhrs="";yjhrs="";wjhrs="";ckInfo="";     //每次写入前都清空数据
				String numberTime=numberService.showyisj(deptCode, deptHall);//预计办理时间
				List<Number> numbersCountList = numberService.showWaitRs(countnum);//这个才是查询业务类型及所对应的等待人数
				List<Screen> shulList = setWindowService.getCountShul(deptCode,deptHall);//查询取号总共人数，叫号人数，未叫号人数
				if(null != shulList.get(0).getQhrs() && !"".equals(shulList.get(0).getQhrs())){
					qhrs ="总取号人数:"+shulList.get(0).getQhrs()+"@";
				}else {
					qhrs ="总取号人数:0@";
				}
				if(null != shulList.get(0).getJhsl() && !"".equals(shulList.get(0).getJhsl())){
					yjhrs ="已叫号人数:"+shulList.get(0).getJhsl()+"@";
				}else {
					yjhrs ="已叫号人数:0@";
				}
				if(null != shulList.get(0).getWjhsl() && !"".equals(shulList.get(0).getWjhsl())){
					wjhrs ="未叫号人数:"+shulList.get(0).getWjhsl()+"@";
				}else {
					wjhrs ="未叫号人数:0@";
				}
				infoStringBuffer.append(qhrs).append(yjhrs).append(wjhrs);
				if(numberTime!=null){
					String[] unmT=numberTime.split("\\.");
						if(unmT[0].isEmpty()){
							String numTime="预计办结时间:0"+numberTime+"小时"+"@";
							infoStringBuffer.append(numTime);
						}else{
							String numTime="预计办结时间:"+numberTime+"小时"+"@";
							infoStringBuffer.append(numTime);
						}
					}
				if(null != numbersCountList && numbersCountList.size()>0){
					for (Number nu : numbersCountList) {
						
						infoStringBuffer.append(nu.getTypeName()+":"+nu.getTypeCount()+"@");
					}
				}else {
					infoStringBuffer.append("未有号码需要办理:@");
				}
				JSONObject obj2 = new JSONObject();
				obj2.put("deptHall", deptHall);
				obj2.put("deptHallName", deptHallName);
				obj2.put("info", infoStringBuffer.toString());
				jsonList.add(obj2);
			}
			obj.put("result", jsonList);
			
		}else if ("2".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
			countnum.setDeptCode(deptCode);
			countnum.setDeptHall(deptHall);
			StringBuffer infoStringBuffer=new StringBuffer(); //接收取号,已叫号,未叫号,业务类型及对应人数的信息
			qhrs="";yjhrs="";wjhrs="";ckInfo="";     //每次写入前都清空数据
			String numberTime=numberService.showyisj(deptCode, deptHall);//预计办理时间
			List<Number> numbersCountList = numberService.showWaitRs(countnum);//这个才是查询业务类型及所对应的等待人数
			List<Screen> shulList = setWindowService.getCountShul(deptCode,deptHall);//查询取号总共人数，叫号人数，未叫号人数
			List<Screen> ckList = setWindowService.getAddressAndStatuss();//查询每个业务窗口的叫号办理信息
			if(null != shulList.get(0).getQhrs() && !"".equals(shulList.get(0).getQhrs())){
				qhrs ="总取号人数:"+shulList.get(0).getQhrs()+"@";
			}else {
				qhrs ="总取号人数:0@";
			}
			if(null != shulList.get(0).getJhsl() && !"".equals(shulList.get(0).getJhsl())){
				yjhrs ="已叫号人数:"+shulList.get(0).getJhsl()+"@";
			}else {
				yjhrs ="已叫号人数:0@";
			}
			if(null != shulList.get(0).getWjhsl() && !"".equals(shulList.get(0).getWjhsl())){
				wjhrs ="未叫号人数:"+shulList.get(0).getWjhsl()+"@";
			}else {
				wjhrs ="未叫号人数:0@";
			}
			infoStringBuffer.append(qhrs).append(yjhrs).append(wjhrs);
			if(numberTime!=null){
				String[] unmT=numberTime.split("\\.");
				if(unmT[0].isEmpty()){
						String numTime="预计办结时间:0"+numberTime+"小时"+"@";
						infoStringBuffer.append(numTime);
				}else{
					String numTime="预计办结时间:"+numberTime+"小时"+"@";
					infoStringBuffer.append(numTime);
				}
			}
			if(null != numbersCountList && numbersCountList.size()>0){
				for (Number nu : numbersCountList) {
					
					infoStringBuffer.append(nu.getTypeName()+":"+nu.getTypeCount()+"@");
				}
			}else {
				infoStringBuffer.append("未有号码需要办理:@");
			}
			if(null != ckList && ckList.size()>0){
				for (Screen screen : ckList) {
					ckInfo += screen.getAddress()+":"+screen.getCode()+":"+screen.getXm()+":"+screen.getJhsl()+":"+screen.getGuoh()+":"+screen.getGuaq()+":"+screen.getPjsl()+"@";
				}
			}
			
			JSONObject obj2 = new JSONObject();
			obj2.put("info", infoStringBuffer.toString());
			obj2.put("ckInfo", ckInfo);
			jsonList.add(obj2);
			obj.put("result", jsonList);
		}
		obj.put("role",role);
		this.getResponse("application/json").getWriter().print(obj);
		return null;
	}

	public void setSetWindowService(ISetWindowService setWindowService) {
		this.setWindowService = setWindowService;
	}
	
	
	
}
