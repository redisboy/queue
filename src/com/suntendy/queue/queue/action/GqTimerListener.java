package com.suntendy.queue.queue.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.suntendy.queue.util.GqTimerTask;
public class GqTimerListener implements ServletContextListener{
	
	private Timer timer;
	private GqTimerTask gqtimertask;
	private static final long daySpan = 24*60*60*1000;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		timer = new Timer(true);
		gqtimertask = new GqTimerTask();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);
        Date date  = calendar.getTime();
		timer.schedule(gqtimertask, date,daySpan);
	}

}
