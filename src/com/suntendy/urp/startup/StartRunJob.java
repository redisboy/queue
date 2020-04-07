package com.suntendy.urp.startup;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;

import com.suntendy.urp.job.dao.UrpJobDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.service.JobManager;

public class StartRunJob {

	protected final Log log = LogFactory.getLog(getClass());

	private JobManager jobManager;
	private UrpJobDao urpJobDao;
	
	public void start() throws Exception {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("spring/jdbc-oracle.properties");   
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String deptCode = p.getProperty("dept.code");
		String deptHall = p.getProperty("dept.hall");
		
		log.debug("开始运行定时任务！"); 
		//查找状态为启用的任务
		HashMap filters = new HashMap();
		filters.put("status", "0");
		filters.put("deptCode", deptCode);
		filters.put("deptHall", deptHall);
		List jobList = urpJobDao.queryForList(filters);
		for(int i=0;i<jobList.size();i++){
			UrpJob urpJob = (UrpJob) jobList.get(i);
			JobDataMap paramsMap = new JobDataMap();
			paramsMap.put("urpJob", urpJob);
			jobManager.enableSchedule(urpJob.getId(), urpJob.getJobgroup(), urpJob.getJobclass(), urpJob.getCron(), paramsMap);
		}
    }
   
	
    public void end() throws Exception {
    	
		log.debug("结束运行定时任务！"); 
		//查找状态为启用的任务  
		HashMap filters = new HashMap();
		filters.put("status", "0");
		List jobList = urpJobDao.queryForList(filters);
		for(int i=0;i<jobList.size();i++){
			UrpJob urpJob = (UrpJob) jobList.get(i);
			//只有需要在该服务器上运行的JOB才会停止
			jobManager.disableSchedule(urpJob.getId(), urpJob.getJobgroup());
		} 
    }


	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

	public UrpJobDao getUrpJobDao() {
		return urpJobDao;
	}

	public void setUrpJobDao(UrpJobDao urpJobDao) {
		this.urpJobDao = urpJobDao;
	}

}
