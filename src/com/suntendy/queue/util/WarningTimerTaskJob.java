package com.suntendy.queue.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.queue.system.action.SystemAction;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.warn.action.WarnAction;
import com.suntendy.urp.job.dao.UrpJoblogDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.model.UrpJoblog;

public class WarningTimerTaskJob  extends StatefulMethodInvokingJob {
	
		private Logger logger = Logger.getLogger(this.getClass().getName());
		
		protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
			WarnAction warnAction = (WarnAction) SystemAction.getBean("warnAction");
	        
			UrpJoblogDao urpJoblogDao = (UrpJoblogDao) SystemAction.getBean("urpJoblogDao");
			//记录日志
			UrpJoblog urpJoblog = new UrpJoblog();
			try {
				
				JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
				UrpJob urpJob = (UrpJob) jobDataMap.get("urpJob");
				
				urpJoblog.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJoblog.setStartdate(new Date());
				urpJoblog.setJobid(urpJob.getId());
				urpJoblog.setOpertype("RUN");
				logger.debug("------------------------" + new Date() + "开始信息");
				System.out.println("--------------警告定时任务----------" + new Date() + "警告人员添加记录信息");
				warnAction.saveWarn();
				System.out.println("结束定时任务");
				
				urpJoblog.setEndstatus("S");
			} catch(Exception e) {
				e.printStackTrace();
				urpJoblog.setEndstatus("F");
				if(e.toString().length()<512)
					urpJoblog.setRemark(e.toString());
				else
					urpJoblog.setRemark(e.toString().substring(0,512));
			} finally {
				urpJoblog.setEnddate(new Date());
				urpJoblogDao.save(urpJoblog);
			}
		
		}
}
