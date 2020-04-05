package com.suntendy.queue.system.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.lzxx.action.LzckAction;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.impl.NumberServiceImpl;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.system.domain.System;
import com.suntendy.queue.system.service.ISystemService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.cache.InitCacheManager;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.window.domain.Screen;

public class SystemAction extends BaseAction implements ApplicationContextAware {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext ctx;
	private ISystemService systemService;
	private LedService ledService;
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

	public LedService getLedService() {
		return ledService;
	}

	public void setLedService(LedService ledService) {
		this.ledService = ledService;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}
	//验证取号时间是否在所设置的时间内
	public void yzqhsj(){
		CacheManager cacheManager = CacheManager.getInstance();
		String islimitqhtime = cacheManager.getSystemConfig("islimitqhtime");
		String qhtime1 = cacheManager.getSystemConfig("qhtime1");
		String flag ="false";
		if (islimitqhtime.equals("0")) {
		//if (!(Boolean.valueOf(islimitqhtime))) {
			Date now = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			String hourAndSecond = simpleDateFormat.format(now);
			String[] slot = qhtime1.split(",");
			String timefont;
			String timeafter;
			Date nowdate;
			Date fontDate;
			Date afterDate;
			try {
				nowdate = simpleDateFormat.parse(hourAndSecond);
				for (int i = 0; i < slot.length; i++) {
					timeafter = slot[i].split("-")[1];
					timefont = slot[i].split("-")[0];
					fontDate =simpleDateFormat.parse(timefont);
					afterDate =simpleDateFormat.parse(timeafter);
					if(fontDate.getTime()<nowdate.getTime()&&afterDate.getTime()>nowdate.getTime()){
						flag = "true";
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			flag = "true";
		}
		try {
			this.getResponse().getWriter().write(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//根据deptCode deptHall查询相应的系统设置并把值回传给页面(不会更新缓存中的系统设置)
	public void getSystemSetInfo(){
		HttpServletRequest request = this.getRequest();
		String deptCode = request.getParameter("deptCode");
		String deptHall = request.getParameter("deptHall");
		String flag = "true";
		JSONObject data = new JSONObject();
		
		try {
			List<System> datas = systemService.getAllSystem(deptCode, deptHall);
			
			if (null != datas && !datas.isEmpty()) {
				for (System sys : datas) {
					//request.setAttribute(sys.getName(), sys.getContent());
					data.put(sys.getName(), sys.getContent());
				}
			}else {
				flag="false";
			}
			data.put("flag", flag);
			this.getResponse().getWriter().print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//重载系统设置到缓存同时修改系统设置页面的数据
	public void resetSystemSetInfo(){
		HttpServletRequest request = this.getRequest();
		String deptCode = request.getParameter("deptCode");
		String deptHall = request.getParameter("deptHall");
		String flag = "true";
		JSONObject data = new JSONObject();
		CacheManager cacheManager = CacheManager.getInstance();
		try {
			List<System> datas = systemService.getAllSystem(deptCode, deptHall);
			
			if (null != datas && !datas.isEmpty()) {
				for (System sys : datas) {
					//request.setAttribute(sys.getName(), sys.getContent());
					data.put(sys.getName(), sys.getContent());
					cacheManager.addOfSystemConfig(sys.getName(), sys.getContent());
				}
			}else {
				flag="false";
			}
			data.put("flag", flag);
			data.put("resetMsg","success");
			this.getResponse().getWriter().print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getAllSystem() {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		Employee user =(Employee) this.getHttpSession().getAttribute("user");
		String role = user.getRole();
		String deptCode;
		String deptHall;
		if(role.equals("0")){
			try {
				List<Map<String, String>> list = deptService.findAllDeptcode();
				HashMap<String, String> deptInfo = new HashMap<String, String>();
				//ArrayList<HashMap<String, String>> deptList = new ArrayList<HashMap<String, String>>();
				for(Map<String, String> item:list){
					String codeAndHall = item.get("DEPTCODE")+"-"+item.get("DEPTHALL");
					String hallName = item.get("DEPTNAME");
					deptInfo.put(codeAndHall, hallName);
				}
				request.setAttribute("deptInfo",deptInfo);
				request.setAttribute("role", role);		
			} catch (Exception e) {
				// TODO Auto-generated catch block-+
				e.printStackTrace();
			}
		}
		deptCode = cacheManager.getOfDeptCache("deptCode");
		deptHall = cacheManager.getOfDeptCache("deptHall");			
		request.setAttribute("currentDept",deptCode+"-"+deptHall);
		
		try {
			List<System> datas = systemService.getAllSystem(deptCode, deptHall);
			
			if (null != datas && !datas.isEmpty()) {
				for (System sys : datas) {
					request.setAttribute(sys.getName(), sys.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String clearCache() {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		try {
			List<System> datas = systemService.getAllSystem(deptCode, deptHall);
			if (null != datas && !datas.isEmpty()) {
				for (System sys : datas) {
					request.setAttribute(sys.getName(), sys.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("currentDept", deptCode+"-"+deptHall);
		return SUCCESS;
	}
	/**
	 * 获取读卡器类型
	 * @return
	 * @throws Exception
	 */
	public String getSystemById() throws Exception {
		String sysCont=CacheManager.getInstance().getSystemConfig("readID");
		this.getResponse("text/html").getWriter().println(sysCont);
		
		return null;
	}
	
	public String updateSystem() throws Exception {
		HttpServletRequest request = this.getRequest();
		//系统设置值，顺序与数据库表的数据顺序一致
		List<String> columns = new ArrayList<String>();
		columns.add(request.getParameter("ip"));
		columns.add(request.getParameter("systemId"));
		columns.add(request.getParameter("interfaceId"));
		columns.add(request.getParameter("isCheckAgent"));
		columns.add(request.getParameter("serialNumType"));
		columns.add(request.getParameter("isUseInterface"));
		columns.add(request.getParameter("isUseOldDevice"));
		columns.add(request.getParameter("deviceType"));
		columns.add(request.getParameter("voiceType"));
		columns.add(request.getParameter("readID"));
		columns.add(request.getParameter("isSignature"));
		columns.add(request.getParameter("isCamera"));
		columns.add(request.getParameter("winMode"));
		String numberSet = request.getParameter("numberSet").trim();//每月取号次数默认5次
		if(null == numberSet || numberSet.equals("")){
			numberSet = "5";
		}
		columns.add(numberSet);
		columns.add(request.getParameter("isOpenPhoto"));//取号是否显示身份证照片
		columns.add(request.getParameter("ieRestart"));//是否重启IE的IP地址
		columns.add(request.getParameter("isOpenQueueTV"));//是否重启LED队列屏
		columns.add(request.getParameter("ledQueueShow"));//LED队列屏显示方式
		columns.add(request.getParameter("isOpenInformation"));//是否启用领证信息发屏
		columns.add(request.getParameter("lzxxSendMode"));//领证信息发屏方式
		columns.add(request.getParameter("isForcedToNumber"));//是否启用强制叫号
		String forcedToNumberTime = request.getParameter("forcedToNumberTime");//强制叫号时间，默认为60秒
		if(null == forcedToNumberTime || "".equals(forcedToNumberTime)){
			forcedToNumberTime="60";
		}
		columns.add(forcedToNumberTime);//强制叫号时间
		columns.add(request.getParameter("isOpenJm"));//是否启用静脉验证
		columns.add(request.getParameter("isOpenIndexCamera"));//是否启用取号拍照 0是 1 否
		columns.add(request.getParameter("autoOrManualCapture"));//取号拍照方(手动和自动) 0自动 1手动
//		columns.add(request.getParameter("calloutSQL"));//六合一Callout表SQL
//		columns.add(request.getParameter("valuerecordSQL"));//六合一Valuerecord表SQL
		columns.add(request.getParameter("pjtype"));//评价方式
		columns.add(request.getParameter("hthf"));//启用后台恢复
		columns.add(request.getParameter("isOpenJgts"));//是否启用警告提示
		String swkssz=request.getParameter("swkssz");
		if(null == swkssz || "".equals(swkssz)){
			swkssz="0";
		}
		columns.add(swkssz);
		String swksfz=request.getParameter("swksfz");
		if(null == swksfz || "".equals(swksfz)){
			swksfz="0";
		}
		columns.add(swksfz);
		String swjssz=request.getParameter("swjssz");
		if(null == swjssz || "".equals(swjssz)){
			swjssz="0";
		}
		columns.add(swjssz);
		String swjsfz=request.getParameter("swjsfz");
		if(null == swjsfz || "".equals(swjsfz)){
			swjsfz="0";
		}
		columns.add(swjsfz);
		String xwkssz=request.getParameter("xwkssz");
		if(null == xwkssz || "".equals(xwkssz)){
			xwkssz="0";
		}
		columns.add(xwkssz);
		String xwksfz=request.getParameter("xwksfz");
		if(null == xwksfz || "".equals(xwksfz)){
			xwksfz="0";
		}
		columns.add(xwksfz);
		String xwjssz=request.getParameter("xwjssz");
		if(null == xwjssz || "".equals(xwjssz)){
			xwjssz="0";
		}
		columns.add(xwjssz);
		String xwjsfz=request.getParameter("xwjsfz");
		if(null == xwjsfz || "".equals(xwjsfz)){
			xwjsfz="0";
		}
		columns.add(xwjsfz);
//		columns.add(request.getParameter("swkssz"));//上午开始时钟
//		columns.add(request.getParameter("swksfz"));//上午开始分钟
//		columns.add(request.getParameter("swjssz"));//上午结束时钟
//		columns.add(request.getParameter("swjsfz"));//上午结束分钟
//		columns.add(request.getParameter("xwkssz"));//下午开始时钟
//		columns.add(request.getParameter("xwksfz"));//下午开始分钟
//		columns.add(request.getParameter("xwjssz"));//下午结束时钟
//		columns.add(request.getParameter("xwjsfz"));//下午结束分钟
		
		String jgtsTime = request.getParameter("jgtsTime");//强制叫号时间，默认为60秒
		if(null == jgtsTime || "".equals(jgtsTime)){
			jgtsTime="0";
		}
		columns.add(jgtsTime);//警告时间
		String rNumber = request.getParameter("rNumber");//数据抽取百分比
		if ("".equals(rNumber) || null==rNumber || Integer.parseInt(rNumber)>=100) {
			rNumber = "99";
		}
		columns.add(rNumber);
		columns.add(request.getParameter("isOpenForceEnvalue"));//是否启用强制评价
		columns.add(request.getParameter("waitingNum"));//等待人数统计方式
		columns.add(request.getParameter("isOpenTztd"));//是否启用通知提档
		columns.add(request.getParameter("autoLzxx"));//领证信息自动发屏
		columns.add(request.getParameter("isopenScreen"));//是否启用窗口号滚动
		String tbnum = request.getParameter("tbnum");//退办审核天数
		if ("".equals(tbnum) || tbnum == null) {
			tbnum="0";
		}
		columns.add(tbnum);
		columns.add(request.getParameter("iscywf"));//是否查验违法
		columns.add(request.getParameter("cywffs"));//检验违法方式
		columns.add(request.getParameter("isyzcfqh"));//是否允许重复取号
		columns.add(request.getParameter("ledxh"));//led窗口型号
		columns.add(request.getParameter("qrpjtype"));//是否确认评价
		columns.add(request.getParameter("leddlpxh"));//led队列屏型号
		String lzcknum = request.getParameter("lzcknum");//领证窗口滚动个数
		if ("".equals(lzcknum) || lzcknum == null) {
			lzcknum="0";
		}
		columns.add(lzcknum);
		columns.add(request.getParameter("islimitqhtime"));//是否限制取号时间段
		columns.add(request.getParameter("qhtime1").trim());//是否限制取号时间段
		columns.add(request.getParameter("ismessage"));
		columns.add(request.getParameter("yyjkmode"));//预约接口调用模式  例如：珠海模式   南宁模式等
		columns.add(request.getParameter("isOpenHmd"));
		columns.add(request.getParameter("HmdCs").trim());
		columns.add(request.getParameter("bdip"));//需要打印表单ip
		columns.add(request.getParameter("pjTime"));
		String rzdbckz = request.getParameter("rzdbckz");//人证对比参考值
		if ("".equals(rzdbckz) || null==rzdbckz || Integer.parseInt(rzdbckz)>100) {
			rzdbckz = "60";
		}
		columns.add(rzdbckz);
		columns.add(request.getParameter("xhnum"));//选号等待时间
		columns.add(request.getParameter("ghnum"));//过号等待时间
		columns.add(request.getParameter("isOpenYj"));
		columns.add(request.getParameter("cps").trim());
		columns.add(request.getParameter("gzl").trim());
		columns.add(request.getParameter("myd").trim());
		columns.add(request.getParameter("hfhmrzdb"));//恢复号码人证对比
		columns.add(request.getParameter("sfkqcyyw"));//是否开启查验业务
		columns.add(request.getParameter("xzywmewm"));//选择一维码或二维码
		columns.add(request.getParameter("rzdb"));//代理人人证对比
		columns.add(request.getParameter("isOpenwxqh"));//是否启用微信取号
		String jklx = request.getParameter("jklx");//接口类型
		if ("1".equals(request.getParameter("isUseInterface"))) {
			jklx = "0";
		}
		columns.add(jklx);
		Employee user = (Employee) request.getSession().getAttribute("user");
		String deptCode;
		String deptHall;
		String role = user.getRole();
		if(role.equals("0")){
			deptCode = request.getParameter("deptCode");
			deptHall = request.getParameter("deptHall");
		}else {
			CacheManager cacheManager = CacheManager.getInstance();
			deptCode = cacheManager.getOfDeptCache("deptCode");
			deptHall = cacheManager.getOfDeptCache("deptHall");
		}
		
		
		List<System> datas = new ArrayList<System>();
		for (int i = 0, len = columns.size(); i < len; i++) {
			System system = new System();
			system.setId((i + 1) + "");
			system.setDeptCode(deptCode);
			system.setDeptHall(deptHall);
			system.setContent(columns.get(i));
			datas.add(system);
		}
		//獲取當前部門所有設置
		String content="";
		List<System> datasAll = systemService.getAllSystem(deptCode, deptHall);
		if (null != datasAll && !datasAll.isEmpty()) {
			for (System sysall : datasAll) {
				for (System sys : datas) {
					if(sysall.getId().equals(sys.getId())){
						if(!sysall.getContent().equals(sys.getContent())){
							content+="系统参数【"+sysall.getDescription()
							+"】修改为【"+sys.getContent()+"】---";
						}
					}
				}
			}
		}
		//獲取當前部門所有設置end
		boolean result = false;
		try {
			systemService.batchUpdate(datas);
			result = true;
			
			// 完成 修改操作进入日志功能 开始
			
			String event="修改系统参数";
			String module="基础设置";
			String moduleType="系统设置";
			String eventType="改";
			String coreBusiness="0";
			String result1="0";
			String resultDepict="修改系统参数成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			Employee user1 = (Employee) session.getAttribute("user");
			String userName =user1.getName();
			
			OperateLog operateLog = new OperateLog();
			Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result1, resultDepict);
			operate.setResult(result1);
			operate.setOpName(userName);
			if(content.length()<=0){
				content="没有修改系统参数";
			}
			operate.setContent(content.getBytes());
			try {
				iSystemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 修改操作进入日志功能代码块结束
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getResponse().getWriter().print(result);
		return null;
	}
	
	public String updateClearCache() throws Exception{
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String isOpenIndexCamera = request.getParameter("isOpenIndexCamera");
		String autoOrManualCapture = request.getParameter("autoOrManualCapture");
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptCode", deptCode);
		map.put("deptHall", deptHall);
		map.put("isOpenIndexCamera", isOpenIndexCamera);
		map.put("autoOrManualCapture", autoOrManualCapture);
		boolean result = false;
		try {
			systemService.updateClearCache(map);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getResponse().getWriter().print(result);
		return null;
	}
	
	public String initLED() throws Exception {
		int total = 0;
		ServletRequest request = getRequest();
		String val = request.getParameter("val");
		if ("1".equals(val)) {//val 1 初始化发屏 2 清理窗口叫号缓存
			CacheManager cacheManager = CacheManager.getInstance();
			String deptCode = cacheManager.getOfDeptCache("deptCode");
			String deptHall = cacheManager.getOfDeptCache("deptHall");
			InitCacheManager initCacheManager = new InitCacheManager(ctx);
			List<Screen> windows = initCacheManager.getAllWindow();
			List<LED> list = new ArrayList<LED>();
			LED led = cacheManager.getLedCache(deptCode + deptHall);
			
			for (Screen screen : windows) {
				List<LED> listLED = ledService.getLedInfo(deptCode, deptHall);
				LED ledVo = listLED.get(0);
				if (StringUtils.isEmpty(screen.getAddress())) {
					continue;
				}
				if ("1".equals(screen.getShowNum())) {
					screen.setContent2(screen.getContent());
					screen.setContent("");
				} else {
					if (screen.getWindowGDContent() != null) {
						screen.setContent2(screen.getWindowGDContent());
					}else{
						screen.setContent2(" ");
					}
				}
				screen.setLed(ledVo);
				if(null != screen.getLedWindowHeight() && !"0".equals(screen.getLedWindowHeight())){
					screen.getLed().setHeight(screen.getLedWindowHeight());
//					led.setHeight(screen.getLedWindowHeight());
				}else{
					screen.getLed().setHeight(ledVo.getHeight());
				}
				if(null != screen.getLedWindowWidth() && !"0".equals(screen.getLedWindowWidth())){
//					led.setWidth(screen.getLedWindowWidth());
					screen.getLed().setWidth(screen.getLedWindowWidth());
				}else {
					screen.getLed().setWidth(ledVo.getWidth());
				}
//				screen.setLed(cacheManager.getLedCache(deptCode + deptHall));
				String result = "";
				if("0".equals(screen.getIsgd()) && null != screen.getWindowGDContent()){
					result = LEDUtils.sendGunDongContent(screen);//发送滚动信息
					java.lang.System.out.println("System手动初始化滚动字幕发送结果："+result);
				}else{
				java.lang.System.out.println("LED设置高=="+screen.getLed().getHeight()+"LED设置宽=="+screen.getLed().getWidth()+"窗口设置高=="+screen.getLedWindowHeight()+"窗口设置宽=="+screen.getLedWindowWidth());
				//初始化窗口发屏
					result = LEDUtils.sendText(screen, null, null, false);
				}
				java.lang.System.out.println("System手动初始化窗口信息---窗口号：" + screen.getAddress()+"----点阵数："+screen.getLattice()+"----com口："+screen.getComnum()+"发送数据结果：" + result);
				//判断单双行，双行发送滚动信息
				//if("2".equals(screen.getShowNum())){
				
				//}
				if ("1".equals(result)) total ++;
				Thread.sleep(50);
			}
			//初始化LED队列发屏
			if("0".equals(cacheManager.getSystemConfig("isOpenQueueTV"))){//判断是否开启LED发屏
				//叫号队列屏发屏
				LED ledDuilie = new LED();
				ledDuilie.setDeptCode(deptCode);
				ledDuilie.setDeptHall(deptHall);
				
				NumberServiceImpl.cleanSendContent();//清空全局变量内容
				LzckAction.sendLzxxContent = "";//清空队列屏变量内容
				list = ledService.getLedInfo_TV(ledDuilie);
				if (null != list && !list.isEmpty()) {
					for(LED ledAll : list){
						//LED led_tv = cacheManager.getLed_tvCache(deptCode+deptHall+ledAll.getAddress());
						String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
						String result = LEDUtils.sendLED_TVText(ledAll, ledQueueShow, " ");
						java.lang.System.out.println("初始化队列屏信息---窗口号：" + ledAll.getAddress() +"----点阵数："+ledAll.getLattice()+"----com口："+ledAll.getCom1()+"发送数据结果：" + result);
						if ("1".equals(result)) total ++;
						Thread.sleep(50);
					}
				}
			}
			if (total == (windows.size()+list.size())) {
				total = -1;
			}
		} else if ("2".equals(val)) {
			String operNum = request.getParameter("operNum");
			Number number = NumberManager.getInstance().fetchCallNumber(operNum);
			if (null != number) {
				number.setStatus("2");
			}
			total = -1;
		}
		getResponse().getWriter().print(total);
		
		return null;
	}
	
	

	public static ApplicationContext getApplicationContext() {
	if (ctx == null)
		throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
	return ctx;
	}

	public static Object getBean(String beanName) {
	getApplicationContext().getBean(beanName);

	return getApplicationContext().getBean(beanName);
	}

	public static Map getBeansOfType(Class beanClass) {
	return getApplicationContext().getBeansOfType(beanClass);
	}


	public static boolean containsBean(String beanName) {
	return getApplicationContext().containsBean(beanName);
	}
	
}