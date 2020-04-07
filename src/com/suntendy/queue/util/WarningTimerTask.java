package com.suntendy.queue.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;

import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.log.service.ILogService;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.WarningMessageEvent;

public class WarningTimerTask{
	
	private TimerTask task;
	Timer timer = new Timer();
	public WarningTimerTask(final String deptCode, final String deptHall, final String code,final String loginIP,final String jgtsTime,final Publisher publisher){
		
		task = new TimerTask() {
			public void run() {
			    ApplicationContext ac = SpringUtil.getApplicationContext();
			    INumberService numberService = (INumberService)ac.getBean("numberService");
			    ILogService logService = (ILogService)ac.getBean("logService");
        		System.out.println("进入警告定时器");
        		Date d = new Date();
            	int hours = d.getHours();
            	if(hours>8 && hours<18){
	        		try{//查询当前用户数据
	        			Number number =new Number();
	        			number.setOperNum(code);
	        			Number numberStatus = numberService.getValueRecordAllById(number);
		        		System.out.println("当前状态："+numberStatus.getStatus());
		        		
		        		if(null!=numberStatus.getStatus()){
		        			if(numberStatus.getStatus().equals("3") || numberStatus.getStatus().equals("5") || numberStatus.getStatus().equals("6") || numberStatus.getStatus().equals("7")){
		        				//查询已叫号数据(记录日志时间)
			        			Number numberTime = numberService.getCodeByRz(number);
			        			//查询数据库当前时间
			        			String nowTime=logService.searchNowDate();
			        			//将当前时间转换毫秒
			        			Calendar c = Calendar.getInstance();  
			        			c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(nowTime));  
			        			System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());  
			        			//判断规定时间内是否叫号
		        				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        				long rztime = sdf.parse(numberTime.getRztime().substring(0, 19)).getTime();
		        				//if(Integer.parseInt(numberStatus.getBeginTime().substring(14,16))>=Integer.parseInt(numberTime.getRztime().substring(14,16))){
		        				if((c.getTimeInMillis()-rztime) >=Integer.parseInt(jgtsTime)*59*1000){
			        				try {
			        					publisher.publish(new WarningMessageEvent("您的休息时间已到,请进行相关业务办理工作！谢谢！" + "@"));//推送到取号外屏
			        					//写入日志
			        					
				        				LogVo log = new LogVo();
				        				log.setCode(numberStatus.getOperNum());
				        				log.setLogflag("2");
				        				log.setEndTime(numberStatus.getEndtime());//
				        				log.setLoginIP(loginIP);
				        				log.setStatus(numberStatus.getStatus());
				        				log.setBarId(numberStatus.getBarId());
				        				logService.addUserLoginLog(log);
				        				System.out.println("警告定时器更新状态成功");
									} catch (Exception e) {
										e.printStackTrace();
										//写入日志
				        				LogVo log = new LogVo();
				        				log.setCode(numberStatus.getOperNum());
				        				log.setLogflag("2");
				        				log.setEndTime(numberStatus.getEndtime());//
				        				log.setLoginIP(loginIP);
				        				log.setStatus(numberStatus.getStatus());
				        				log.setBarId(numberStatus.getBarId());
				        				logService.addUserLoginLog(log);
				        				System.out.println("在异常情况警告定时器更新状态");
									}
	//		        				JOptionPane.showMessageDialog(null, "<html><font color=red  size=16>您的休息时间已到,请进行相关业务办理工作！谢谢！", "工作提醒", JOptionPane.INFORMATION_MESSAGE);
		        				}
		        			}
		        		}
	        		}catch(Exception e){
	        			e.printStackTrace();
	        			System.out.println("警告定时器状态失败");
	        		}
            	}
        		System.out.println("警告定时器状态结束");
            	
             }
			
		};
		timer.schedule(task, Integer.parseInt(jgtsTime)*1000*60);//
	}
}
