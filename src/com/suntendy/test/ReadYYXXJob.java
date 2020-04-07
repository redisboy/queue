package com.suntendy.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.evaluation.action.EvaluationAction;
import com.suntendy.queue.lzxx.action.AutoSendLzxxAction;
import com.suntendy.queue.lzxx.action.LzxxAction;
import com.suntendy.queue.system.action.SystemAction;
import com.suntendy.queue.yyjk.action.YyjkAction;
import com.suntendy.urp.job.dao.UrpJoblogDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.model.UrpJoblog;

public class ReadYYXXJob extends StatefulMethodInvokingJob {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		YyjkAction yyjkAction = (YyjkAction) SystemAction.getBean("yyjkAction");
        
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
			System.out.println("进入获取预约信息定时器");
			yyjkAction.readAllYYXX();
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
