package com.suntendy.queue.led.action;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.queue.domain.Zhpcallnum;
import com.suntendy.queue.queue.service.IZhpcallnumService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.ShowZhpLzxx;
import com.suntendy.queue.util.scriptsession.event.ShowZhxxDataEvent;
import com.suntendy.queue.window.domain.Screen;

public class LedAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private LedService ledService;
	private IBusinessService businessService;
	private IZhpcallnumService zhpcallnumService;
	private Publisher publisher;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}
	
	public IBusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	public void setLedService(LedService ledService) {
		this.ledService = ledService;
	}
	public IZhpcallnumService getZhpcallnumService() {
		return zhpcallnumService;
	}
	public void setZhpcallnumService(IZhpcallnumService zhpcallnumService) {
		this.zhpcallnumService = zhpcallnumService;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	/**
	 * 获取LED设置信息
	 * @return
	 * @throws Exception
	 */
	public String setLED() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<LED> list = ledService.getLedInfo(deptCode, deptHall);
		
		if (null != list && !list.isEmpty()) {
			LED LEDVo = list.get(0);
			request.setAttribute("led", LEDVo);
			request.setAttribute("isExist", "1");
		} else {
			request.setAttribute("isExist", "0");
		}
		return SUCCESS;
	}
	/**
	 * 更改LED设置
	 * @return
	 */
	public String updateLED(){
		HttpServletRequest request = getRequest();
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		ledVo.setBaud(StringUtils.trimToEmpty(request.getParameter("sBaud")));
		ledVo.setWidth(StringUtils.trimToEmpty(request.getParameter("sWidth")));
		ledVo.setHeight(StringUtils.trimToEmpty(request.getParameter("sHeight")));
		ledVo.setDbcolor(StringUtils.trimToEmpty(request.getParameter("dbcolor")));
		ledVo.setTransmode(StringUtils.trimToEmpty(request.getParameter("transmode")));
		//ledVo.setPlaySpeed(StringUtils.trimToEmpty(request.getParameter("playSpeed")));
		//ledVo.setContent(StringUtils.trimToEmpty(request.getParameter("content")));
		//ledVo.setIsOpenGunDong(StringUtils.trimToEmpty(request.getParameter("isOpenGunDong")));
		
		try {
			if ("0".equals(request.getParameter("isExist"))) {
				ledService.add(ledVo);
			} else {
				ledService.updateLED(ledVo);
			}
			request.setAttribute("msg", "LED设置成功！");
			request.setAttribute("action", "setLED.action");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "LED设置失败！");
			request.setAttribute("action", "setLED.action");
		}
		return SUCCESS;
	}
	
	public String setScreenPara() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Map<String, Screen> windowCache = CacheManager.getInstance().getWindowCache();
		for (Screen screen : windowCache.values()) {
			String str = "";
			List<LED> listLED = ledService.getLedInfo(deptCode, deptHall);
			LED ledVo = listLED.get(0);
			
			if(null != screen.getLedWindowHeight() && !"0".equals(screen.getLedWindowHeight())){
				screen.getLed().setHeight(screen.getLedWindowHeight());
//				led.setHeight(screen.getLedWindowHeight());
			}else{
				screen.getLed().setHeight(ledVo.getHeight());
			}
			if(null != screen.getLedWindowWidth() && !"0".equals(screen.getLedWindowWidth())){
//				led.setWidth(screen.getLedWindowWidth());
				screen.getLed().setWidth(screen.getLedWindowWidth());
			}else {
				screen.getLed().setWidth(ledVo.getWidth());
			}
			
			String res = LEDUtils.setScreenPara(screen);
			if ("1".equals(res)) {
				str = "发送成功";
			} else if ("2".equals(res)) {
				str = "通讯失败";
			} else if ("3".equals(res)) {
				str = "发送过程中出错";
			}
			System.out.println("设置屏参" + screen.getAddress() + str);
			Thread.sleep(50);
		}
		return null;
	}
	/**
	 * LED队列屏设置
	 * @return
	 * @throws Exception
	 */
	public String getLED_TV() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		LED led = new LED();
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		List<LED> list = ledService.getLedInfo_TV(led);
		//查询等待大厅
		List<Business> businessList = businessService.getGroupByWaitSrea();
		for (int i = 0; i < list.size(); i++) {
			 LED ledVO = list.get(i);
			if ("1".equals(list.get(i).getFontColor())) {
				ledVO.setFontColor("红色");
			}else if ("2".equals(list.get(i).getFontColor())) {
				ledVO.setFontColor("绿色");
			}
		}
		for (int i = 0; i < list.size(); i++) {
			 LED ledVO = list.get(i);
			if ("2".equals(list.get(i).getTransmode())) {
				ledVO.setTransmode("232通讯");
			}else if ("3".equals(list.get(i).getTransmode())) {
				ledVO.setTransmode("485通讯");
			}
		}
		for (int i = 0; i < list.size(); i++) {
			 LED ledVO = list.get(i);
			if ("1".equals(list.get(i).getDbcolor())) {
				ledVO.setDbcolor("单色");
			}else if ("2".equals(list.get(i).getDbcolor())) {
				ledVO.setDbcolor("双色");
			}
		}
		for (int i = 0; i < list.size(); i++) {
			LED ledVO = list.get(i);
			String id = ledVO.getAddress();
			String operate = "<input name='button' type='button' class='button' value='修改' onClick='updateLED("+id+")'/>&nbsp;"
			+ "<input name='button' type='button' class='button' value='设置屏参' onClick='setLED_tv("+id+")'/>&nbsp;"
			+"<input name='button' type='button' class='button' value='删除' onClick='delLED_tv("+id+")'/>";
			ledVO.setOperation(operate);
		}
		request.setAttribute("list", list);
		request.setAttribute("businessList", businessList);
		return SUCCESS;
	}
	/**
	 * TO LED队列屏修改
	 * @return
	 * @throws Exception
	 */
	public String toUpdateLED_TV() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String address = StringUtils.trimToEmpty(request.getParameter("id"));
		LED led = new LED();
		led.setAddress(address);
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		List<LED> list = ledService.getLedInfo_TV(led);
		//查询等待大厅
		List<Business> businessList = businessService.getGroupByWaitSrea();
		if (null !=list && !list.isEmpty()) {
			LED ledVO = list.get(0);
			request.setAttribute("waitArea", ledVO.getWaitingArea());
			request.setAttribute("led", ledVO);
		}
		request.setAttribute("businessList", businessList);
		return SUCCESS;
	}
	/**
	 * 更改LED队列屏设置
	 * @return
	 */
	public String updateLED_TV(){
		HttpServletRequest request = getRequest();
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		ledVo.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
		ledVo.setCom1(StringUtils.trimToEmpty(request.getParameter("com1")));
		ledVo.setFontColor(StringUtils.trimToEmpty(request.getParameter("fontColor")));
		ledVo.setBaud(StringUtils.trimToEmpty(request.getParameter("sBaud")));
		ledVo.setWidth(StringUtils.trimToEmpty(request.getParameter("sWidth")));
		ledVo.setHeight(StringUtils.trimToEmpty(request.getParameter("sHeight")));
		ledVo.setDbcolor(StringUtils.trimToEmpty(request.getParameter("dbcolor")));
		ledVo.setTransmode(StringUtils.trimToEmpty(request.getParameter("transmode")));
		//ledVo.setStyle(StringUtils.trimToEmpty(request.getParameter("style")));
		ledVo.setStyle("");
		ledVo.setFqsl(StringUtils.trimToEmpty(request.getParameter("fqsl")));
		ledVo.setContent(StringUtils.trimToEmpty(request.getParameter("content")));
		ledVo.setSpace(StringUtils.trimToEmpty(request.getParameter("space")));
		ledVo.setLattice(StringUtils.trimToEmpty(request.getParameter("lattice")));
		ledVo.setWaitingArea(StringUtils.trimToEmpty(request.getParameter("waitingArea")));
		String flag = request.getParameter("flag1");
		if (!"".equals(flag) || flag!=null) {
			ledVo.setFlag(StringUtils.trimToEmpty(flag));
		}
		String key = cacheManager.getOfDeptCache("deptCode") + cacheManager.getOfDeptCache("deptHall") + StringUtils.trimToEmpty(request.getParameter("address"));
		
		try {
			
			ledService.updateLED_TV(ledVo);
			request.setAttribute("msg", "LED队列屏设置成功！");
			request.setAttribute("action", "getLED_TV.action");
			cacheManager.addOfLed_tvCache(key, ledVo);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "LED队列屏设置失败！");
			request.setAttribute("action", "getLED_TV.action");
		}
		return SUCCESS;
	}
	/**
	 * 增加LED队列屏设置
	 * @return
	 */
	public String insertLED_TV(){
		HttpServletRequest request = getRequest();
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		ledVo.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
		ledVo.setCom1(StringUtils.trimToEmpty(request.getParameter("com1")));
		ledVo.setFontColor(StringUtils.trimToEmpty(request.getParameter("fontColor")));
		ledVo.setBaud(StringUtils.trimToEmpty(request.getParameter("sBaud")));
		ledVo.setWidth(StringUtils.trimToEmpty(request.getParameter("sWidth")));
		ledVo.setHeight(StringUtils.trimToEmpty(request.getParameter("sHeight")));
		ledVo.setDbcolor(StringUtils.trimToEmpty(request.getParameter("dbcolor")));
		ledVo.setTransmode(StringUtils.trimToEmpty(request.getParameter("transmode")));
		//ledVo.setStyle(StringUtils.trimToEmpty(request.getParameter("style")));
		ledVo.setStyle("");
		ledVo.setFqsl(StringUtils.trimToEmpty(request.getParameter("fqsl")));
		ledVo.setContent(StringUtils.trimToEmpty(request.getParameter("content")));
		ledVo.setSpace(StringUtils.trimToEmpty(request.getParameter("space")));
		ledVo.setLattice(StringUtils.trimToEmpty(request.getParameter("lattice")));
		ledVo.setWaitingArea(StringUtils.trimToEmpty(request.getParameter("waitingArea")));
		String flag = request.getParameter("flag1");
		if (!"".equals(flag) || flag!=null) {
			ledVo.setFlag(StringUtils.trimToEmpty(flag));
		}
		String key = cacheManager.getOfDeptCache("deptCode") + cacheManager.getOfDeptCache("deptHall") + StringUtils.trimToEmpty(request.getParameter("address"));
		
		try {
			ledService.addLED_TV(ledVo);
			request.setAttribute("msg", "LED队列屏添加成功！");
			request.setAttribute("action", "getLED_TV.action");
			cacheManager.addOfLed_tvCache(key, ledVo);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "LED队列屏添加失败！");
			request.setAttribute("action", "getLED_TV.action");
		}
		return SUCCESS;
	}
	public String delLED_TV(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		LED ledVo = new LED();
		ledVo.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
		String key = cacheManager.getOfDeptCache("deptCode") + cacheManager.getOfDeptCache("deptHall") + StringUtils.trimToEmpty(request.getParameter("address"));
		
		try {
			ledService.delLED_TV(ledVo);
			request.setAttribute("msg", "LED队列屏删除成功！");
			request.setAttribute("action", "getLED_TV.action");
			cacheManager.removeLedCache_TV(key);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "LED队列屏删除失败！");
			request.setAttribute("action", "getLED_TV.action");
		}
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 发送LED信息
	 * @return
	 * @throws Exception 
	 */
	public String showLEDqueue() throws Exception{
		HttpServletRequest request = getRequest();
		String flag = "";
		String pjxxp = "";       //评价信息屏
		if (request.getParameter("flag") != null) {
			flag = request.getParameter("flag");
		}
		if (request.getParameter("pjxxp") != null) {
			pjxxp = request.getParameter("pjxxp");
		}
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		LED led1 =new LED();
		List<LED> list1 = ledService.getLED_Content(ledVo);
		if (flag != null && !"".equals(flag)) {
			if(null != list1 && list1.size()>0){
				led1 = list1.get(Integer.parseInt(flag));
			}
		}else {
			led1 = list1.get(0);
		}
		System.out.println("led1.getShowType()="+led1.getShowType());
		if (led1.getFckContent() != null) {
			String res = new String(led1.getFckContent());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(res);
			res = m.replaceAll("");
			request.setAttribute("wbbjqnr", res.replace("\\s", ""));//文本编辑器内容(使数据保持一行输出)
		}
		if (null != led1.getTitle1content()) {
			String res = new String(led1.getTitle1content());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(res);
			res = m.replaceAll("");
			request.setAttribute("content1", res.replace("\\s", ""));//文本编辑器内容(使数据保持一行输出)
		}
		if (null != led1.getTitle2content()) {
			String res = new String(led1.getTitle2content());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(res);
			res = m.replaceAll("");
			request.setAttribute("content2", res.replace("\\s", ""));//文本编辑器内容(使数据保持一行输出)
		}
		if (null != led1.getTitle3content()) {
			String res = new String(led1.getTitle3content());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(res);
			res = m.replaceAll("");
			request.setAttribute("content3", res.replace("\\s", ""));//文本编辑器内容(使数据保持一行输出)
		}
		if (null != led1.getTitle4content()) {
			String res = new String(led1.getTitle4content());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(res);
			res = m.replaceAll("");
			request.setAttribute("content4", res.replace("\\s", ""));//文本编辑器内容(使数据保持一行输出)
		}
		request.setAttribute("flag", flag);
		request.setAttribute("showtype", led1.getShowType());
		request.setAttribute("width", led1.getWindowwidth());
		request.setAttribute("height", led1.getWindowheight());
		String hallname ="";
		if(null != led1.getHallname()){hallname = led1.getHallname();}
		request.setAttribute("hallname", hallname);
		String gdxx ="";
		if(null != led1.getGdxx()){gdxx = led1.getGdxx();}
		request.setAttribute("gdxx",gdxx);
		String gdxx1 ="";
		if(null != led1.getGdxx1()){gdxx1 = led1.getGdxx1();}
		request.setAttribute("gdxx1", gdxx1);
		String title1 ="";
		if(null != led1.getTitle1()){title1 = led1.getTitle1();}
		request.setAttribute("title1", title1);
//		String content1 ="";
//		if(null != led1.getTitle1content() && "排队信息" != title1 && "领证信息" != title1){content1 = led1.getTitle1content();}
//		request.setAttribute("content1", content1);
		String title2 ="";
		if(null != led1.getTitle2()){title2 = led1.getTitle2();}
		request.setAttribute("title2", title2);
//		String content2 ="";
//		if(null != led1.getTitle2content() && "排队信息" != title2 && "领证信息" != title2){content2 = led1.getTitle2content();}
//		request.setAttribute("content2", content2);
		String title3 ="";
		if(null != led1.getTitle3()){title3 = led1.getTitle3();}
		request.setAttribute("title3", title3);
//		String content3 ="";
//		if(null != led1.getTitle3content() && "排队信息" != title3 && "领证信息" != title3){content3 = led1.getTitle3content();}
//		request.setAttribute("content3", content3);
		String title4 ="";
		if(null != led1.getTitle4()){title4 = led1.getTitle4();}
		request.setAttribute("title4", title4);
//		String content4 ="";
//		if(null != led1.getTitle4content() && "排队信息" != title4 && "领证信息" != title4){content4 = led1.getTitle4content();}
//		request.setAttribute("content4", content4);
		
		
	//第二种方案
		//查询zhpcallnum
		List<Zhpcallnum> listCallnum =  zhpcallnumService.queryAllcallnum();
		String duilie = "";
		if (listCallnum.size()<=5) {
			for (int i = 0; i < listCallnum.size(); i++) {
				Zhpcallnum callnum = listCallnum.get(i);
				String clientno = callnum.getClientno().substring(13, callnum.getClientno().length());
				if (callnum.getBarId().length()==1) {
					callnum.setBarId("0"+callnum.getBarId());
				}
				duilie += "<li id='" + callnum.getId() + "' style='width:100%;list-style:none;border-bottom: none;'>"+clientno+"号请到"+callnum.getBarId()+"号窗口办理业务</li>";
			}
		}else{
			for (int i = listCallnum.size()-5; i < listCallnum.size(); i++) {
				Zhpcallnum callnum = listCallnum.get(i);
				String clientno = callnum.getClientno().substring(13, callnum.getClientno().length());
				if (callnum.getBarId().length()==1) {
					callnum.setBarId("0"+callnum.getBarId());
				}
				duilie += "<li id='" + callnum.getId() + "' style='width:100%;list-style:none;border-bottom: none;'>"+clientno+"号请到"+callnum.getBarId()+"号窗口办理业务</li>";
			}
		}
		request.setAttribute("duilie", duilie);
		
		if("liuz".equals(pjxxp)){
			request.setAttribute("width","1088");
			request.setAttribute("height","416");
			request.setAttribute("showtype","lzpjxxp");   //柳州评价信息屏 信息屏上显示员工评价信息
		}
		return SUCCESS;			
	}
	
	public String ShowZHP() throws Exception{
		HttpServletRequest request = getRequest();
		String flag = request.getParameter("flag");
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		LED led1 =new LED();
		List<LED> list1 = ledService.getLED_Content(ledVo);
		System.out.println(list1);
		if(null != list1 && list1.size()>0){
			led1 = list1.get(Integer.parseInt(flag));
		}
		String res = new String(led1.getFckContent());
		Pattern p = Pattern.compile("\r|\n");
        Matcher m = p.matcher(res);
        res = m.replaceAll("");
        res = res.replace("\\s", "");
		res = new String(res.getBytes("utf-8"),"ISO-8859-1");
		getResponse().getWriter().write(res);
		return null;
	}
	
	
	
	/**
	 * LED队列发屏
	 * @return
	 * @throws Exception
	 */
	public String setLED_tv() throws Exception {
		Map<String, LED> ledCache = CacheManager.getInstance().getLed_tvCache();
		for (LED led : ledCache.values()) {
			String str = "";
			String res = LEDUtils.setLED_tv(led);
			if ("1".equals(res)) {
				str = "发送成功";
			} else if ("2".equals(res)) {
				str = "通讯失败";
			} else if ("3".equals(res)) {
				str = "发送过程中出错";
			}
			System.out.println("设置屏参" + led.getAddress() + str);
			Thread.sleep(50);
		}
		return null;
	}
	
	/**
	 * LED队列发屏1
	 * @return
	 * @throws Exception
	 */
	public String setLED_tv1() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String address = StringUtils.trimToEmpty(request.getParameter("id"));
		System.out.println("LED地址为="+address);
		LED led = new LED();
		led.setAddress(address);
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		List<LED> list = ledService.getLedInfo_TV(led);
			String str = "";
			String res = LEDUtils.setLED_tv(list.get(0));
			if ("1".equals(res)) {
				str = "发送成功";
			} else if ("2".equals(res)) {
				str = "通讯失败";
			} else if ("3".equals(res)) {
				str = "发送过程中出错";
			}
			System.out.println("设置屏参" + led.getAddress() + str);
			Thread.sleep(50);
		return null;
	}
	
	/**
	 * LED队列屏内容设置
	 * @return
	 * @throws Exception
	 */
	public String getLED_Content() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		LED led = new LED();
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		List<LED> list = ledService.getLED_Content(led);
		
		if (null != list && !list.isEmpty()) {
			request.setAttribute("list", list);
			for(int i=0;i<list.size();i++){
				LED LEDVo = list.get(i);
				System.out.println("LEDVo.getShowType()="+LEDVo.getShowType());
				String operate = "<a onclick=updatefckContent('"+ LEDVo.getId() + "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;"+ "<a onclick=delfckContent('"+ LEDVo.getId() + "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>&nbsp;";
				LEDVo.setOperation(operate);
			}
		} else {
			request.setAttribute("isExist", "0");
		}
		return SUCCESS;
	}
	
	/**
	 * 进入修改页面
	 * @return
	 * @throws Exception
	 */
	public String to_updateLED_Content() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String id=  request.getParameter("id");
		LED led = new LED();
		led.setId(id);
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		List<LED> list = ledService.getLED_Content(led);
		
		if (null != list && !list.isEmpty()) {
			for(int i=0;i<list.size();i++){
				LED LEDVo = list.get(i);
				String res ="";
				String res1 ="";
				if(null != LEDVo.getFckContent())
				res = new String(LEDVo.getFckContent());
//				if(null != LEDVo.getFckContent1())
//				res1 = new String(LEDVo.getFckContent1());
				String title1content="";
				if (null != LEDVo.getTitle1content()) {
					title1content = new String(LEDVo.getTitle1content());
				}
				String title2content="";
				if (null != LEDVo.getTitle2content()) {
					title2content = new String(LEDVo.getTitle2content());
				}
				String title3content="";
				if (null != LEDVo.getTitle3content()) {
					title3content = new String(LEDVo.getTitle3content());
				}
				String title4content="";
				if (null != LEDVo.getTitle4content()) {
					title4content = new String(LEDVo.getTitle4content());
				}
				
				request.setAttribute("fckContent", res);
				request.setAttribute("title1content", title1content);
				request.setAttribute("title2content", title2content);
				request.setAttribute("title3content", title3content);
				request.setAttribute("title4content", title4content);
//				request.setAttribute("fckContent1", res1);
				request.setAttribute("led", LEDVo);
				request.setAttribute("isExist", "1");
				request.setAttribute("id", LEDVo.getId());
			}
		} else {
			request.setAttribute("isExist", "0");
		}
		return SUCCESS;
	}
	/**
	 * 进入添加页面
	 * @return
	 * @throws Exception
	 */
	public String to_addLED_Content() throws Exception {
		HttpServletRequest request = getRequest();
		//查询最大ID
		LED led = ledService.getMaxId();
		request.setAttribute("id", led.getId());
		request.setAttribute("isExist", "0");
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delLED_Content() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		LED ledVo = new LED();
		ledVo.setId(id);
		try {
			ledService.delLED_Content(ledVo);
			request.setAttribute("msg", "删除成功！");
			
			// 完成 删除操作进入日志功能 开始
			
			String event="删除";
			String module="基础设置";
			String moduleType="综合屏信息设置";
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
			
			
			
			request.setAttribute("action", "getLED_Content.action");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除成功失败！");
			request.setAttribute("action", "getLED_Content.action");
		}
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 更改LED队列屏内容设置
	 * @return
	 */
	public String updateLED_Content(){
		HttpServletRequest request = getRequest();
		LED ledVo = new LED();
		CacheManager cacheManager = CacheManager.getInstance();
		ledVo.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		ledVo.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		String id = request.getParameter("id");
		String zhpContent = request.getParameter("fcknr");
//		String zhpContent1 = request.getParameter("fcknr1");
		ledVo.setId(id);
		if (zhpContent.length()>0) {
			ledVo.setFckContent(zhpContent.getBytes());
		}
//		ledVo.setFckContent1(zhpContent1.getBytes());
		ledVo.setShowType(StringUtils.trimToEmpty(request.getParameter("showType")));
		ledVo.setWindowwidth(StringUtils.trimToEmpty(request.getParameter("windowwidth")));
		ledVo.setWindowheight(StringUtils.trimToEmpty(request.getParameter("windowheight")));
		ledVo.setHallname(StringUtils.trimToEmpty(request.getParameter("hallname")));
		ledVo.setGdxx(StringUtils.trimToEmpty(request.getParameter("gdxx")));
		System.out.println(request.getParameter("gdxx"));
		ledVo.setGdxx1(StringUtils.trimToEmpty(request.getParameter("gdxx1")));
		ledVo.setTitle1(StringUtils.trimToEmpty(request.getParameter("title1")));
		String title1content = request.getParameter("title1content");
		if (title1content.length()>0) {
			ledVo.setTitle1content(title1content.getBytes());
		}
		ledVo.setTitle2(StringUtils.trimToEmpty(request.getParameter("title2")));
		String title2content = request.getParameter("title2content");
		if (title2content.length()>0) {
			ledVo.setTitle2content(title2content.getBytes());
		}
		ledVo.setTitle3(StringUtils.trimToEmpty(request.getParameter("title3")));
		String title3content = request.getParameter("title3content");
		if (title3content.length()>0) {
			ledVo.setTitle3content(title3content.getBytes());
		}
		ledVo.setTitle4(StringUtils.trimToEmpty(request.getParameter("title4")));
		String title4content = request.getParameter("title4content");
		if (title4content.length()>0) {
			ledVo.setTitle4content(title4content.getBytes());
		}
		
		try {
			if ("0".equals(request.getParameter("isExist"))) {
				ledService.addLED_Content(ledVo);
				
				// 完成 新增操作进入日志功能 开始
				
				String event="新增";
				String module="基础设置";
				String moduleType="综合屏信息设置";
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
				
				
				
			} else {
				ledService.updateLED_Content(ledVo);
				
				// 完成 修改操作进入日志功能 开始
				
				String event="修改";
				String module="基础设置";
				String moduleType="综合屏信息设置";
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
				
			}
			request.setAttribute("msg", "LED队列屏内容设置成功！");
			request.setAttribute("action", "getLED_Content.action");
			
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "LED队列屏内容设置失败！");
			request.setAttribute("action", "getLED_Content.action");
		}
		String datas = (Integer.parseInt(id)-1)+"&"+1;
		if (!"0".equals(request.getParameter("isExist"))) {
			publisher.publish(new ShowZhxxDataEvent(datas));
		}
		return SUCCESS;
	}
	/**
	 * 领证信息发送综合屏
	 * @return
	 */
	public String sendLzxx_zph() throws Exception {
		HttpServletRequest request = getRequest();
		String hphm = request.getParameter("hphm");
		String res = "0";
		try {
			String datas = hphm+"&";
			publisher.publish(new ShowZhpLzxx(datas));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res = "1";
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	
}
