package com.suntendy.queue.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;

import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.log.service.ILogService;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.window.domain.Screen;

public class screenGDContetTimerTask{
	
	private TimerTask task;
	Timer timer = new Timer();
	public screenGDContetTimerTask(final Screen screen, final String code){
		
		task = new TimerTask() {
			public void run() {
			    ApplicationContext ac = SpringUtil.getApplicationContext();
			    INumberService numberService = (INumberService)ac.getBean("numberService");
			    ILogService logService = (ILogService)ac.getBean("logService");
        		System.out.println("进入窗口发送滚动字幕");
        		//Date d = new Date();
            	//int hours = d.getHours();
            	//if(hours>8 && hours<23){
	        		try{//查询当前用户数据
	        			Number number =new Number();
	        			number.setOperNum(code);
	        			Number numberStatus = numberService.getValueRecordAllById(number);
		        		System.out.println("当前状态："+numberStatus.getStatus());
		        		
		        		if(null!=numberStatus.getStatus()){
		        			if(numberStatus.getStatus().equals("2")){
		        				//查询已叫号数据(记录日志时间)
			        			Number numberTime = numberService.getCodeByRz(number);
			        			//查询数据库当前时间
			        			String nowTime=logService.searchNowDate();
			        			//将当前时间转换毫秒
			        			Calendar c = Calendar.getInstance();  
			        			c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(nowTime));  
			        			//System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());  
			        			//判断规定时间内是否叫号
		        				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        				long rztime = sdf.parse(numberTime.getRztime().substring(0, 19)).getTime();
		        				if((c.getTimeInMillis()-rztime) >=10000){
			        				try {
			        					
//			        					new Object[] { , screen.getAddress(), screen.getComnum(),
//			    						 screen.getContent(), screen.getWindowGDContent(), screen.getLattice(), screen.getColor(),
//			    						screen.getColor1(),
			        					
			        					Screen screenNew = new Screen();
			        					screenNew.setLed(screen.getLed());
			        					screenNew.setAlign(screen.getAlign());
			        					screenNew.setAddress(screen.getAddress());
			        					screenNew.setBarip(screen.getBarip());
			        					screenNew.setAllownovalue(screen.getAllownovalue());
			        					screenNew.setComnum(screen.getComnum());
			        					screenNew.setCode(screen.getCode());
			        					screenNew.setColor(screen.getColor());
			        					screenNew.setColor1(screen.getColor1());
			        					screenNew.setCom2(screen.getCom2());
			        					screenNew.setContent(screen.getContent());
			        					screenNew.setContent2(screen.getContent2());
			        					screenNew.setBarid(screen.getBarid());
			        					screenNew.setLedWindowHeight(screen.getLedWindowHeight());
			        					screenNew.setLedWindowWidth(screen.getLedWindowWidth());
			        					screenNew.setWindowGDContent(numberStatus.getSerialNum().substring(13)+"号正在办理业务");
			        					screenNew.setLattice(screen.getLattice());
			        					screenNew.setColor(screen.getColor());screenNew.setColor1(screen.getColor1());
			        					System.out.println("定时器窗口滚动screenNew ="+screenNew.getWindowGDContent());
			        					LEDUtils.sendGunDongContent(screenNew);//发送滚动信息
									} catch (Exception e) {
										e.printStackTrace();
									}
		        				}
		        			}
		        		}
	        		}catch(Exception e){
	        			e.printStackTrace();
	        			System.out.println("发送滚动字幕失败");
	        		}
            	//}
        		System.out.println("发送滚动字幕结束");
            	
             }
			
		};
		timer.schedule(task, 10000);//
	}
}
