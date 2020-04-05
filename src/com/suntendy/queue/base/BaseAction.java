package com.suntendy.queue.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

public class BaseAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public HttpServletResponse getResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + "; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		return response;
	}
	
	public HttpSession getHttpSession() {
		return getRequest().getSession();
	}
	
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
	public String clientIp() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.getRequest().getHeader("Proxy-Client-IP");
		}
		if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.getRequest().getRemoteAddr();
		}
		return ip;
	}
}
