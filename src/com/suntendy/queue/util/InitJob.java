package com.suntendy.queue.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.suntendy.queue.queue.service.impl.NumberServiceImpl;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.util.cache.InitCacheManager;

public class InitJob implements ServletContextAware {
	private ServletContext ctx;
	
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
	}
	
	/**
	 * 初始化LED屏
	 */
	public void initLED() {
		System.out.println("初始化LED屏开始：" + DateUtils.dateToString());
		InitCacheManager initCacheManager = new InitCacheManager(ctx);
		initCacheManager.initWindowCache(true);
		initCacheManager.initLED_tv();
		System.out.println("清理队列屏公共变量begin");
		NumberServiceImpl.cleanSendContent();
		System.out.println("清理队列屏公共变量end");
		System.out.println("初始化LED屏结束：" + DateUtils.dateToString());
	}
	
	/**
	 * 清除号码缓存信息
	 */
	public void clearCacheNumber() {
		System.out.println("清除号码缓存开始：" + DateUtils.dateToString());
		NumberManager.getInstance().clear();
		System.out.println("clearCacheNumber中清理队列屏公共变量begin");
		NumberServiceImpl.cleanSendContent();
		System.out.println("clearCacheNumber中清理队列屏公共变量end");
		System.out.println("清除号码缓存结束：" + DateUtils.dateToString());
	}
}
