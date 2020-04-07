package com.suntendy.urp.job.service;

import java.util.Iterator;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class JobManager {
	
	private Scheduler scheduler;

	/**
	 * 启动一个自定义的job
	 * 
	 * @param schedulingJob
	 *            自定义的job
	 * @param paramsMap
	 *            传递给job执行的数据
	 * @return 成功则返回true，否则返回false
	 */
	public boolean enableSchedule(String jobName,String jobGroup,String jobClass,String jobCron,JobDataMap paramsMap) {
		if (jobName == null) {
			return false;
		}
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(jobName,jobGroup);
			if (null == trigger) {// 如果不存在该trigger则创建一个
				JobDetail jobDetail = null;
				jobDetail = new JobDetail(jobName,jobGroup,Class.forName(jobClass));
				
				jobDetail.setJobDataMap(paramsMap);
				trigger = new CronTrigger(jobName,jobGroup,jobCron);
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				//开始删除JOB
				this.disableSchedule(jobName, jobGroup);
				
				//创建新JOB
				JobDetail jobDetail = null;
				jobDetail = new JobDetail(jobName,jobGroup,Class.forName(jobClass));
				
				jobDetail.setJobDataMap(paramsMap);
				trigger = new CronTrigger(jobName,jobGroup,jobCron);
				scheduler.scheduleJob(jobDetail, trigger);
				
//				trigger.setCronExpression(jobCron);
//				scheduler.rescheduleJob(trigger.getName(),trigger.getGroup(),trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 禁用一个job
	 * 
	 * @param jobId
	 *            需要被禁用的job的ID
	 * @param jobGroupId
	 *            需要被警用的jobGroupId
	 * @return 成功则返回true，否则返回false
	 */
	public boolean disableSchedule(String jobName, String jobGroupId) {
		if (jobName.equals("") || jobGroupId.equals("")) {
			return false;
		}
		try {
			Trigger trigger = getJobTrigger(jobName, jobGroupId);
			if (null != trigger) {
				scheduler.deleteJob(jobName, jobGroupId);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 得到job的详细信息
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的详细信息,如果job不存在则返回null
	 */
	public JobDetail getJobDetail(String jobName, String jobGroupId) {
		if (jobName.equals("") || jobGroupId.equals("") || null == jobName
				|| jobGroupId == null) {
			return null;
		}
		try {
			return scheduler.getJobDetail(jobName, jobGroupId);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到job对应的Trigger
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的Trigger,如果Trigger不存在则返回null
	 */
	public Trigger getJobTrigger(String jobName, String jobGroupId) {
		if (jobName.equals("") || jobGroupId.equals("") || null == jobName
				|| jobGroupId == null) {
			return null;
		}
		try {
			return scheduler.getTrigger(jobName, jobGroupId);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}

    // 得到正在运行的任务
    public boolean getRunningJob(String jobName) throws SchedulerException {
        boolean isRunning = false;
        List execContexts = scheduler.getCurrentlyExecutingJobs();
        for (Iterator iterator = execContexts.iterator(); iterator.hasNext();) {
            JobExecutionContext jec = (JobExecutionContext) iterator.next();
            if (jec.getTrigger().getName().equalsIgnoreCase(jobName)) {
            	isRunning = true;
            	break;
            }
        }
        return isRunning;
    }

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
    
}
