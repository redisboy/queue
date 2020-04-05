package com.suntendy.queue.util;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.suntendy.queue.util.cache.CacheManager;

/**
 * 自动开关队列机IE
 * @author:liuwj
 * @date:2014-4-19  上午08:57:22
 * @version:1.0
 */
public class RestartIeListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CacheManager cacheManager = CacheManager.getInstance();
		String ieRestartIP = cacheManager.getSystemConfig("ieRestart");
		System.out.println("C:\\服务器上运行的文件\\JhDoublePServer.exe 0#"+ieRestartIP+"#");
		Runtime r = Runtime.getRuntime();
	    try {
	    	r.exec("C:\\服务器上运行的文件\\JhDoublePServer.exe 0#"+ieRestartIP+"#");
			System.out.println("完成IE关闭");
			Thread.sleep(3000);
			r.exec("C:\\服务器上运行的文件\\JhDoublePServer.exe 1#"+ieRestartIP+"#");
			System.out.println("完成IE开启");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("执行过程中发生异常！");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("执行过程中发生异常！");
		}
		
	}
}
