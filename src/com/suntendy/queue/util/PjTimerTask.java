package com.suntendy.queue.util;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.service.impl.NumberServiceImpl;
import com.suntendy.queue.util.cache.CacheManager;

public class PjTimerTask{
	
	private TimerTask task;
	Timer timer = new Timer();
	CacheManager cacheManager = CacheManager.getInstance();
	String pjTime = cacheManager.getSystemConfig("pjTime");
	int ptime = Integer.parseInt(pjTime);
	int pt = ptime*1000;
	public PjTimerTask(final String deptCode, final String deptHall, final String sxhId,final String loginIP){
		
		task = new TimerTask() {
			public void run() {
			    ApplicationContext ac = SpringUtil.getApplicationContext();
			    INumberService numberService = (INumberService)ac.getBean("numberService");
			    
        		System.out.println("进入定时器修改状态");
        		try{
        			Number numVo = new Number();
        			numVo.setDeptCode(deptCode);
        			numVo.setDeptHall(deptHall);
        			numVo.setOperNum("");
        			numVo.setId(sxhId);
        			numVo.setCode("");
	        		List<Number> statuslist = numberService.searchCurrentDayNumber(numVo);
	        		if(null!=statuslist){System.out.println("定时器接收号码状态=="+statuslist.get(0).getStatus());
	        			if(statuslist.get(0).getStatus().equals("4")){
	        				statuslist.get(0).setStatus("2");
	        				numberService.evaluation(statuslist.get(0),loginIP);
	        				System.out.println("进入定时器修改评价状态成功");
	        			}
	        		}
        		}catch(Exception e){
        			e.printStackTrace();
        			System.out.println("进入定时器修改评价状态失败");
        		}
        		System.out.println("进入定时器修改状态完成");
        		System.out.println("当前自动评价时间："+ptime+"秒");
             }
		};	
			timer.schedule(task, pt);
	}
}
