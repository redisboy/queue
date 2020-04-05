package com.suntendy.queue.login.action;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.suntendy.queue.employee.domain.Employee;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	public void destroy() {}

	public void init() {}

	public String intercept(ActionInvocation invocation) throws Exception {
		if (LoginAction.class != invocation.getAction().getClass()) {
			Employee employee = (Employee)ActionContext.getContext().getSession().get("user");
			if (null == employee) {
				return "login";
			}
		}
		return invocation.invoke();
	}
	
}