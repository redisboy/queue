package com.suntendy.queue.util.scriptsession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import com.suntendy.queue.util.scriptsession.event.JspRegisterEvent;

public class Publisher implements ApplicationContextAware {
	private ApplicationContext ctx;
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}
	
	public void publish(ApplicationEvent event) {
		//发布事件
		ctx.publishEvent(event);
	}
	
	public void register(String clientIp, String mark) {
		ctx.publishEvent(new JspRegisterEvent(mark + "@" + clientIp));
	}
}