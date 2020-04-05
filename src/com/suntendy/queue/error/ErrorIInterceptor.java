package com.suntendy.queue.error;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.suntendy.queue.agent.action.AgentAction;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.login.action.LoginAction;
import com.suntendy.queue.tztd.action.TztdAction;

//sgz
public class ErrorIInterceptor implements Interceptor{

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// 获取信息
      /*  String namespace = ai.getProxy().getNamespace();
        String actionName = ai.getProxy().getActionName();
        String privUrl = namespace + actionName; // 对应的权限URL，用户要访问的URL
        System.out.println(privUrl);
        System.out.println(ai.getClass()+"---"+ai.getResultCode()+"---"+ai.getProxy().getAction());
		System.out.println(ai.getAction().getClass()+"---"+TztdAction.class+"---");*/
		//拦截所有请求，并对抛出异常的进行处理（要求Action抛出异常）
		try{
			return ai.invoke();
		}catch (Exception e) {
			e.printStackTrace();
			//输出错误信息
			ServletActionContext.getRequest().setAttribute("eList", e.getStackTrace());
			System.out.println(ai.getResultCode());
		}
		return "epage";
	}
	
}
