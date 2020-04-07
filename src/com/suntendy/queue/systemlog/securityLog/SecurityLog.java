package com.suntendy.queue.systemlog.securityLog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;
import com.suntendy.queue.util.RSAUtilOperate;
import com.suntendy.queue.util.cache.CacheManager;

public class SecurityLog extends BaseAction {
		
/*安全 日志功能统一实现方法*/
	
	public Security security_log(String userName,String event,String eventType,String result,String resultDepict) throws Exception{
		
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		
		//管理部门
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		//大厅编号
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		/*用户名
		String userName = request.getParameter("yhdh");*/
		//来源ip
		String originIp = request.getRemoteAddr();
		//操作时间
		Date recordTime=new Date();
		String context=userName+originIp;
		String RSA=RSAUtilOperate.RSAOperate(context, 0);//校验位加密
		Security security = new Security(userName, event, originIp, eventType, recordTime,result, resultDepict,RSA, deptCode, deptHall);
		return security;
	}
	
}
