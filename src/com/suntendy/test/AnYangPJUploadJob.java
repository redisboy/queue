package com.suntendy.test;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.queue.lzxx.action.AutoSendLzxxAction;
import com.suntendy.queue.lzxx.action.LzxxAction;
import com.suntendy.queue.queue.action.QueryCallNumber;
import com.suntendy.queue.system.action.SystemAction;
import com.suntendy.queue.util.RSAUtil;
import com.suntendy.urp.job.dao.UrpJoblogDao;
import com.suntendy.urp.job.model.UrpJob;
import com.suntendy.urp.job.model.UrpJoblog;

/*
 * 1、发送购车指标资格审核结果数据发送到数据交换系统
*/
public class AnYangPJUploadJob extends StatefulMethodInvokingJob {
	@Test
	public  void RSAUtilTest() throws Exception{
	  //  Map<String, Object> keyMap = RSAUtil.genKeyPair();
	  //  String privateKey = RSAUtil.getPrivateKey(keyMap);
	  //  String publicKey = RSAUtil.getPublicKey(keyMap);
		//加密key
		String privateKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKG4q2i4ZjA5IR/9J9sXQ6tEM2xwaWWNX1aiGramaBvdAaC27GHgsg/Z+IrvYmJAHn8akjo+KxK+mvtFF589T7CeKHg3/7uDMEDimrVbeR04vqjFRMkYwt0EjMbBKtSFPUeOub3zaaC0DIscT95eihcVLrcxPqYBvUNSkCKSpfH1AgMBAAECgYAwYWS2IWRAzPaB8WQ5AQ63b+HKcR62yMJa0ogXWFUQ8N8Jy2+QIH5ki5r1RYQzTGdTMwnH7s1IAEmxHgxhESCDhi7lUbfIRWlhvNKrWqUIq3Q4pgAgZjvGwV1PLlrZ8pBrjquRQntFIBPxgXitu0GOYOAes1Szvs23zOtCtNWeiQJBAMwZDewIiO6EW7R43bA7Xbfy0nux1XdQejg6o33/0H/31BacyUcdCqv98s3sDXifnwoZuM2deeEhGlRpjva9AYMCQQDK2N5eEx00GtB+sKJMx7g8pujuIx/WdPVWacpSKEUNn4SqFNx23LDVWtrGb4aTxOUeI5jjs+Hj9ABTAkGbv70nAkAWeB+nP51LnPi3mqLVVwPPT4VKpt5YX1zFOfdr1LDwlakcwDAMiy70lmWXtAgbon8Qzpog0NhtJ4bNij15/n7fAkANtich2Mzqjr11Mt9lrUJo5ydroXgveOx0kOYaM5qnVxcSoCJe7oANi/yp2TQRnQeXx/Q3wBsO25mCAu8IZieVAkB5ffOCCUWdcSB7m5SLVApvS0g0RpMIEJKPHa0t4RuL9d07jlVzBxBi1okQA6q7sDwOAXP+z52hBBBmqz8TbxjA";
		//解密key
		String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChuKtouGYwOSEf/SfbF0OrRDNscGlljV9Wohq2pmgb3QGgtuxh4LIP2fiK72JiQB5/GpI6PisSvpr7RRefPU+wnih4N/+7gzBA4pq1W3kdOL6oxUTJGMLdBIzGwSrUhT1Hjrm982mgtAyLHE/eXooXFS63MT6mAb1DUpAikqXx9QIDAQAB";
	    //要加密的字符串
	    String data = "queueadmin";
	    //加密方法
	    String dataE = RSAUtil.encryptedData(data, publicKey);
	    System.out.println("这是已加密的字符串："+dataE);
	    //解密方法
	    String dataD = RSAUtil.decryptData(dataE, privateKey);
	    
	    System.out.println("dataD"+dataD);
	    
	}
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		QueryCallNumber queryCallNumber = (QueryCallNumber) SystemAction.getBean("queryCallNumber");
        
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
			queryCallNumber.anYangPJUpload();
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
