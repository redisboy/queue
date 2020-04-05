package com.suntendy.queue.util.cache;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.queue.action.BeiAnXinXi;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.service.IQueueService;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.system.domain.System;
import com.suntendy.queue.system.service.ISystemService;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.window.domain.Screen;
import com.suntendy.queue.window.service.ISetWindowService;

/*******************************************************************************
 * 描述: 初始化数据类 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-10-12 08:56:30 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class InitCacheManager extends BaseAction{
	private String deptCode, deptHall;
	private CacheManager cacheManager;
	private ApplicationContext applicationContext;
	

	
	
	{
		cacheManager = CacheManager.getInstance();
		deptCode = cacheManager.getOfDeptCache("deptCode");
		deptHall = cacheManager.getOfDeptCache("deptHall");
	}
	
	public InitCacheManager(ServletContext context) {
		this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
	}
	
	public InitCacheManager(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
	/**
	 * 初始化系统设置
	 */
	public void initSystem() {
		try {
			ISystemService systemService = (ISystemService) applicationContext.getBean("systemService");
			List<System> dataList = systemService.getAllSystem(deptCode, deptHall);
			
			if (null != dataList && !dataList.isEmpty()) {
				for (System sys : dataList) {
					cacheManager.addOfSystemConfig(sys.getName(), sys.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化业务类型信息
	 */
	public void initBusinessType() {
		IBusinessService businessService = (IBusinessService) applicationContext.getBean("businessService");
		try {
			List<Business> dataList = businessService.getBusinessList("", "", "", "", deptCode, deptHall);
			if (null != dataList && !dataList.isEmpty()) {
				for (Business business : dataList) {
					CacheManager.getInstance().addOfBusinessTypeCache(business.getId(), business);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化队列信息
	 */
	public void initQueueCache() {
		try {
			IQueueService queueService = (IQueueService) applicationContext.getBean("queueService");
			List<Queue> queueList = queueService.getAllQueue(deptCode, deptHall);
			
			if (null != queueList && !queueList.isEmpty()) {
				for (Queue queue : queueList) {
					queue.setBeginNum("0");
					cacheManager.addOfQueueCache(queue.getCode(), queue);
					
					TreeSet<Number> selfNumberPool = new TreeSet<Number>(new Comparator<Number>() {
						@Override
						public int compare(Number num1, Number num2) {
							return num1.getEnterTime().compareTo(num2.getEnterTime());
						}
					});
					String key = queue.getCode() + queue.getPrenum();
					NumberManager.getInstance().addQueueNumberPool(key, selfNumberPool);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化未叫号和已叫号
	 */
	public void initNumber() {
		INumberService numberService = (INumberService) applicationContext.getBean("numberService");
		Number numVo = new Number();
		numVo.setDeptCode(deptCode);
		numVo.setDeptHall(deptHall);
		numVo.setOperNum("");
		numVo.setId("");
		numVo.setCode("");
		List<Number> numbers = numberService.searchCurrentDayNumber(numVo);
		
		if (null != numbers && !numbers.isEmpty()) {
			NumberManager numberManager = NumberManager.getInstance();
			int singleIndex = numberManager.getSingleIndex();
			String serialNumType = cacheManager.getSystemConfig("serialNumType");
			
			for (Number number : numbers) {
				String serialNum = number.getSerialNum();
				if ("1".equals(serialNumType) && singleIndex < Integer.parseInt(number.getQueueValue())) {
					singleIndex = Integer.parseInt(number.getQueueValue());
					number.setSerialNum(serialNum.substring(serialNum.length() - 4));
				} else {
					Queue queue = cacheManager.getQueue(number.getQueueCode());
					if(queue!=null){
						if (Integer.parseInt(queue.getBeginNum()) < Integer.parseInt(number.getQueueValue())) {
							queue.setBeginNum(number.getQueueValue());
						}
					}
					
					number.setSerialNum(serialNum.substring(serialNum.length() - 5));
				}
				
				if ("1".equals(number.getStatus())) {
					String key = "", queueCode = number.getQueueCode();
					if (StringUtils.isNotEmpty(queueCode)) {
						key = queueCode + number.getBusinessPrefix();
					}
					numberManager.addNumberToNumberPool(key, number);
				} else if ("2".equals(number.getStatus()) || "4".equals(number.getStatus())) {
					number.setStatus("2");
					numberManager.addOfCallNumberCache(number.getOperNum(), number);
					String jklx = cacheManager.getSystemConfig("jklx");
					if ("1".equals(jklx)) {
						java.lang.System.out.println(number.getOperNum()+"===="+number.getBarIp());
						CacheManager.getInstance().addOfLoginCache(number.getOperNum(), number.getBarIp());
					}
				}
			}
			numberManager.setSingleIndex(singleIndex);
		}
	}
	
	/**
	 * 初始化LED屏初始化参数
	 */
	public void initLED() {
		LedService ledService = (LedService) applicationContext.getBean("ledService");
		try {
			List<LED> list = ledService.getLedInfo(deptCode, deptHall);
			
			if (null != list && !list.isEmpty()) {
				String key = deptCode + deptHall;
				cacheManager.addOfLedCache(key, list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化LED队列屏初始化参数
	 */
	public void initLED_tv() {
		LedService ledService = (LedService) applicationContext.getBean("ledService");
		LED led = new LED();
		led.setDeptCode(deptCode);
		led.setDeptHall(deptHall);
		try {
			List<LED> list = ledService.getLedInfo_TV(led);
			if (null != list && !list.isEmpty()) {
				for(LED ledAll : list){
					
					String key = deptCode + deptHall+ledAll.getAddress();
					cacheManager.addOfLed_tvCache(key, ledAll);
					String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
					String result =  LEDUtils.sendLED_TVText(ledAll, ledQueueShow, " ");
					java.lang.System.out.println("初始化队列屏信息---窗口号：" + ledAll.getAddress() +"----点阵数："+ledAll.getLattice()+"----com口："+ledAll.getCom1()+"发送数据结果：" + result);
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化窗口信息
	 * @param initLED 初始化LED屏
	 */
	public void initWindowCache(boolean initLED) {
		ISetWindowService setWindowService = (ISetWindowService) applicationContext.getBean("setWindowService");
		LedService ledService = (LedService) applicationContext.getBean("ledService");
		try {
			List<Screen> dataList = setWindowService.getWindowList(deptCode, deptHall, "", "", "","","");
			
			if (null != dataList && !dataList.isEmpty()) {
				for (Screen screen : dataList) {
					List<LED> listLED = ledService.getLedInfo(deptCode, deptHall);
					LED ledVo = listLED.get(0);
					if (StringUtils.isNotEmpty(screen.getBarip())) {
						if (StringUtils.isNotEmpty(screen.getMenuAddress())) {
							String windowId = screen.getWindowId();
							screen.setWindowId(screen.getMenuAddress());
							screen.setMenuAddress(windowId);
						}
//						screen.setLed(cacheManager.getLedCache(deptCode + deptHall));
						screen.setLed(ledVo);
						cacheManager.addOfWindowCache(screen.getBarip(), screen);
					} else {
						cacheManager.addOfWindowCache(screen.getWindowId(), screen);
					}
					
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
					//判断窗口是否有高和宽
					if(null != screen.getLedWindowHeight() && !"0".equals(screen.getLedWindowHeight())){
						screen.getLed().setHeight(screen.getLedWindowHeight());
					}else{
						screen.getLed().setHeight(ledVo.getHeight());
					}
					if(null != screen.getLedWindowWidth() && !"0".equals(screen.getLedWindowWidth())){
						screen.getLed().setWidth(screen.getLedWindowWidth());
					}else {
						screen.getLed().setWidth(ledVo.getWidth());
					}
					String result ="";
					if("0".equals(screen.getIsgd()) && null != screen.getWindowGDContent()){
							result = LEDUtils.sendGunDongContent(screen);//发送滚动信息
						java.lang.System.out.println("启动tomcat滚动字幕发送结果："+result);
					}else{
						java.lang.System.out.println("LED设置高=="+screen.getLed().getHeight()+"LED设置宽=="+screen.getLed().getWidth()+"窗口设置高=="+screen.getLedWindowHeight()+"窗口设置宽=="+screen.getLedWindowWidth());
							result = LEDUtils.sendText(screen, null, null, false);
					}
					//判断单双行，双行发送滚动信息
					//if("1".equals(screen.getShowNum())){
					
					//}
					java.lang.System.out.println("初始化窗口信息---窗口号：" + screen.getAddress()+"----点阵数："+screen.getLattice()+"发送数据结果：" + result);
					Thread.sleep(50);
					
				}
				
//				if (initLED) {
//					initLED(dataList);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Screen> getAllWindow() {
		ISetWindowService setWindowService = (ISetWindowService) applicationContext.getBean("setWindowService");
		try {
			return setWindowService.getWindowList(deptCode, deptHall, "", "", "","","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void initBAXX(){
		BeiAnXinXi baxx = (BeiAnXinXi) applicationContext.getBean("BeiAnXinXi");
		baxx.duQuBAXX();
	}
	
	/**
	 * 初始化LED屏
	 * @param dataList
	 */
	@SuppressWarnings("unused")
	private void initLED(List<Screen> dataList) {
		if (0 != dataList.size()) {
			Screen screen = dataList.remove(0);
			if (StringUtils.isNotEmpty(screen.getAddress())) {
				if ("1".equals(screen.getShowNum())) {
					screen.setContent2(screen.getContent());
					screen.setContent("");
				} else {
					screen.setContent2(" ");
				}
				
				String first = LEDUtils.setScreenPara(screen);
				if ("1".equals(first)) {
					java.lang.System.out.println("初始化LED屏---窗口号：" + screen.getAddress()+"点阵数："+screen.getLed().getLattice()+"发送数据结果：" + LEDUtils.sendText(screen, null, null, false));
				} else {
					java.lang.System.out.println("设置屏参:" + first);
				}
			}
			initLED(dataList);
		} else {
			return;
		}
	}
	
	public void clearAll() {
		CacheManager.getInstance().clearAll();
	}
}