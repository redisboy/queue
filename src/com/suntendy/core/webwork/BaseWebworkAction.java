package com.suntendy.core.webwork;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ResourceBundleMessageSource;
import com.opensymphony.webwork.interceptor.ServletRequestAware;
import com.opensymphony.webwork.interceptor.ServletResponseAware;
import com.opensymphony.xwork.ActionSupport;
import com.suntendy.core.page.Page;
import com.suntendy.core.util.lang.ObjectUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BaseWebworkAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static Log log = LogFactory.getLog(BaseWebworkAction.class);
	// 列表页面
	public static final String LIST_PAGE = "listPage";
	// 编辑页面
	public static final String EDIT_PAGE = "editPage";
	// 新增页面
	public static final String ADD_PAGE = "addPage";
	// 浏览页面
	public static final String VIEW_PAGE = "viewPage";
	// 成功页面
	public static final String SUCCESS_PAGE = "successPage";
	// 失败页面
	public static final String ERROR_PAGE = "errorPage";
	// 登录失败页面
	public static final String LOGINERROR_PAGE = "loginErrorPage";
	
	private HttpServletRequest request;

	private HttpServletResponse respose;

	private HttpSession session;


	//spring消息资源，若取struts2消息资源，直接使用getText("");
	private ResourceBundleMessageSource messageSource;

	/**
	 * 将总记录数以及page对象保存到request请求中
	 * @param page
	 */
	public void savePage(Page page) {
		//总记录数
		setRequestAttribute("totalRows",String.valueOf(page.getTotalCount()));
		//分页时的单页数据
		setRequestAttribute("pageRequest", page);
	}
	
	/**
	 * 对象是否为空
	 * @param o
	 * @return
	 */
	public boolean isNullOrEmptyString(Object o) {
		return ObjectUtil.isNullOrEmptyString(o);
	}
	
	/**
	 * 取得国际化消息
	 * @param key
	 * @param args
	 * @return
	 */
	public String getMessage(String key, Object[] args) {
		String message = "";
		if (key == null || key.length() == 0) {
			return null;
		}
		if (this.getMessageSource() == null) {
			return null;
		}
		Locale locale = Locale.CHINA;
		if (args != null && args.length>0) {
			message = messageSource.getMessage(key, args, locale);
		} else {
			message = messageSource.getMessage(key, null, locale);
		}
		
		return message;
	}

	/**
	 * 直接输出内容的简便函数.
	 * @param text 内容
	 * @param contentType 输出类型
	 * @return
	 */
	protected String render(String text, String contentType) {
		try {
			this.getRespose().setContentType(contentType);
			this.getRespose().getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 在页面上保存执行结果
	 * @param messageStr
	 */
	public void saveMessage(String messageStr){
		this.setRequestAttribute("message", messageStr);
	}
	
	/**
	 * 在页面上保存返回的URL
	 * @param returnUrlStr
	 */
	public void saveReturnUrl(String returnUrlStr){
		this.setRequestAttribute("returnUrl", returnUrlStr);
	}
	
	/**
	 * 在页面上保存执行结果以及返回的URL
	 * @param messageStr
	 * @param returnUrlStr
	 */
	public void saveResult(String messageStr,String returnUrlStr){
		saveMessage(messageStr);
		saveReturnUrl(returnUrlStr);
	}
	/**
	 * 在页面上保存错误的执行结果以及返回的URL
	 * @param messageStr
	 * @param returnUrlStr
	 */
	public void saveErrorResult(String messageStr,String errorMessage){
		saveMessage(messageStr);
		this.setRequestAttribute("errorMessage", errorMessage);
	}
	/**
	 * 取保存到session中的用户对象
	 * @return
	 */
//	public UrpUser getSessionUser(){
//		return (UrpUser) this.getSessionAttribute(Constants.getUserkey());
//	}
	
	/**
	 * 取得uri
	 * @return
	 */
	public String getExecuteURI() {
		return this.getRequest().getRequestURI();
	}

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	public void setSessionAttribute(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}

	public void removeSessionAttribute(String key) {
		getRequest().getSession().removeAttribute(key);
	}
	
	public Object getSessionAttribute(String key) {
		return getRequest().getSession().getAttribute(key);
	}
	
	public void setServletRequest(HttpServletRequest req) {
		this.setRequest(req);
		setSession(req.isRequestedSessionIdValid() ? req.getSession(false): req.getSession(true));
	}

	public void setServletResponse(HttpServletResponse res) {
		this.setRespose(res);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getRespose() {
		return respose;
	}

	public void setRespose(HttpServletResponse respose) {
		this.respose = respose;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
}
