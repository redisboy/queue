package com.suntendy.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.queue.evaluation.action.EvaluationAction;
import com.suntendy.queue.lzxx.action.AutoSendLzxxAction;
import com.suntendy.queue.lzxx.action.LzxxAction;
import com.suntendy.queue.messageSet.action.MessageSetAction;
import com.suntendy.queue.system.action.SystemAction;
import com.suntendy.urp.job.dao.UrpJoblogDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.model.UrpJoblog;

public class MessageAutoJob extends StatefulMethodInvokingJob {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		MessageSetAction messageSetAction = (MessageSetAction) SystemAction.getBean("messageSetAction");
		
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
			logger.debug("------------------------" + new Date() + "准备重置短信数据");
			System.out.println("--------------第三个定时任务开始----------" + new Date() + "开始重置短信数据");
			messageSetAction.MessageDate();
			System.out.println("---------结束第三个定时任务---------信息重置结束");
			
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
