package com.suntendy.queue.systemlog.operateLog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.util.RSAUtilOperate;
import com.suntendy.queue.util.cache.CacheManager;

public class OperateLog extends BaseAction {
		/*操作 日志功能 增删改查 统一实现方法*/
	
	public Operate operate_log(String userName,String event,String module,String moduleType,String eventType,String coreBusiness,String result,String resultDepict) throws Exception{
		
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
		Date operateTime=new Date();
		String a=String.valueOf(userName.length());
		String context=a+originIp;
		String RSA=RSAUtilOperate.RSAOperate(context, 0);//校验位加密
		Operate operate = new Operate(userName, event, originIp, module, moduleType, eventType, operateTime, coreBusiness, resultDepict, RSA,deptCode, deptHall);
		return operate;
	}
}
