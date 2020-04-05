package com.suntendy.queue.warn.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.warn.domain.Warn;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.window.service.ISetWindowService;
import com.suntendy.queue.window.service.impl.SetWindowServiceImpl;
import com.suntendy.queue.util.SpringUtil;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.warn.service.WarnService;
import com.suntendy.queue.window.domain.Screen;

public class WarnAction extends BaseAction{
	
	private WarnService warnService;
	private ISetWindowService setWindowService;
	private INumberService numberService;
	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	public WarnService getWarnService() {
		return warnService;
	}

	public void setWarnService(WarnService warnService) {
		this.warnService = warnService;
	}
	public ISetWindowService getSetWindowService() {
		return setWindowService;
	}
	public void setSetWindowService(ISetWindowService setWindowService) {
		this.setWindowService = setWindowService;
	}
	
	public String WarnList() throws Exception{
		HttpServletRequest request = getRequest();
		return null;
	}
	
	public String saveWarn() throws Exception{
		
		//System.out.println("开始警告");
		HttpServletRequest request = getRequest();
		Calendar cal=Calendar.getInstance();
		//cal.setTime(new Date());
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String isOpenJgts = cacheManager.getSystemConfig("isOpenJgts");
		String jgtsTime = cacheManager.getSystemConfig("jgtsTime");
		String swkssz=cacheManager.getSystemConfig("swkssz");
		String swksfz=cacheManager.getSystemConfig("swksfz");
		String swjssz=cacheManager.getSystemConfig("swjssz");
		String swjsfz=cacheManager.getSystemConfig("swjsfz");
		String xwkssz=cacheManager.getSystemConfig("xwkssz");
		String xwksfz=cacheManager.getSystemConfig("xwksfz");
		String xwjssz=cacheManager.getSystemConfig("xwjssz");
		String xwjsfz=cacheManager.getSystemConfig("xwjsfz");
		if("0".equals(isOpenJgts)){
			  if((hour==Integer.parseInt(swkssz)&&minute>=Integer.parseInt(swksfz))||(hour>8&&hour<12)||(hour==Integer.parseInt(swjssz)&&minute==Integer.parseInt(swjsfz))||
		    		   (hour>=Integer.parseInt(xwkssz)&&minute==Integer.parseInt(xwksfz))||(hour>=13&&hour<24)||(hour==Integer.parseInt(xwjssz)&&minute==Integer.parseInt(xwjsfz))){
				  Screen screen = new Screen();
				  String windowId="";
				  screen.setWindowId(windowId);
				  List<Screen> screenStatus = setWindowService.searchBar(screen);
				  for(Screen screens:screenStatus){
					  Number num = new Number();
					  num.setBarId(screens.getAddress());
					  List<Number> numberStatus=numberService.selectValuecord(num);
					  if(numberStatus!=null){
						  System.out.println(numberStatus.size());
//						  if(numberStatus.size()==1){
							  Warn warn =new Warn();
							  //System.out.println("获取间隔时间");
							  Number numberStatu = numberStatus.get(0);
							  String sjc =warnService.searchNowTime().get(0).getJgsj();
							  String nowDate=warnService.searchNowTime().get(0).getNowDate();
							  warn.setBarid(screens.getWindowId());
			   				 // System.out.println("添加获取数据");
							  if(Integer.parseInt(sjc)>=Integer.parseInt(jgtsTime)){
								  warn.setCode(numberStatu.getCode());
								  warn.setBegintime(numberStatu.getEndtime());
								  warn.setEndtime(nowDate);
								  warn.setJgsj(sjc);
								  warn.setBarid(numberStatu.getBarId());
								  warn.setDeptcode(numberStatu.getDeptCode());
								  warn.setDepthall(numberStatu.getDeptHall());
								  warnService.saveWarn(warn);
						  }
//						  }else{
//							  Warn warn =new Warn();
//							  warn.setBarid(screens.getWindowId());
//			   				  System.out.println("获取间隔时间");
//			   				  List<Warn> warnInsert = warnService.getWarnList(warn);
//			   				  if(warnInsert.size()!=0){
//			   					  Warn w = warnInsert.get(0);
//				   				  if(w.getJgsj()!=null&&Integer.parseInt(w.getJgsj())>=Integer.parseInt(jgtsTime)){
//				   						System.out.println("添加获取的数据");
//			   						   warnService.saveWarn(w);
//				   				  }
//			   				  }
//						  }
					  }
				  }
			}
		}
		//request.setAttribute("action", "warn/saveWarn.action");
		return "success";
	}
	
	public String searchWarn() throws Exception{
		HttpServletRequest request = getRequest();
		String depthall = StringUtils.trimToEmpty(request.getParameter("depthall"));
		String deptcode = StringUtils.trimToEmpty(request.getParameter("deptcode"));
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		Warn warn = new Warn();
		warn.setKsrq(ksrq);
		warn.setJsrq(jsrq);
		warn.setTjms(tjms);
		List<Warn> list = warnService.selectWarn(warn);
//		for(int i=0;i<list.size();i++){
//			Warn warn1=list.get(i);
//		}
		for(Warn item:list){
			item.setJgyy(item.getBegintime()+"至"+item.getEndtime()+"未办理业务");
		}
		request.setAttribute("list", list);
		return "success";
	}
	
}