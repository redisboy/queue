package com.suntendy.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.queue.queue.action.BeiAnXinXi;
import com.suntendy.queue.system.action.SystemAction;
import com.suntendy.urp.job.dao.UrpJoblogDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.model.UrpJoblog;

/*
 * 1、发送购车指标资格审核结果数据发送到数据交换系统
*/
public class BeiAnXinXiAutoJob extends StatefulMethodInvokingJob {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		BeiAnXinXi baxx = (BeiAnXinXi) SystemAction.getBean("BeiAnXinXi");
        
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
			System.out.println("--------------第二个定时任务----------" + new Date() + "开始领证信息信息");
			baxx.duQuBAXX();
			System.out.println("结束第二个定时任务领证信息");
			
			urpJoblog.setEndstatus("S");
		} catch(Exception e) {
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
