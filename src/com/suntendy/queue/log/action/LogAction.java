package com.suntendy.queue.log.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.log.service.ILogService;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.service.impl.NumberServiceImpl;
import com.suntendy.queue.util.SpringUtil;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.WarningMessageEvent;
import com.suntendy.queue.window.domain.Screen;

public class LogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILogService logService;
	
	public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	
	/*
	 * 查询警告人员警告次数
	 */
	public String getAllWarnCount() throws Exception{
		HttpServletRequest request = getRequest();
		String flag = request.getParameter("flag");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		
		LogVo logvo = new LogVo();
		logvo.setLogflag(flag);
		logvo.setKsrq(ksrq);
		logvo.setJsrq(jsrq);
		logvo.setTjms(tjms);
		List<LogVo> countList = logService.getAllWarnCount(logvo);
		if (!countList.isEmpty()) {
			for (int i = 0; i < countList.size(); i++) {
				LogVo logvos = countList.get(i);
				if(!"".equals(logvos.getSl()) && !"0".equals(logvos.getSl())){
					logvos.setOperate("<a href='getAllWarn.action?code="+logvos.getCode()+"&flag=2&ksrq="+ksrq+"&jsrq="+jsrq+"&tjms="+tjms+"'><img src=/queue/images/button_ck.jpg /></a>");
				}
			}
		}
		request.setAttribute("ksrq", ksrq);
		request.setAttribute("jsrq", jsrq);
		request.setAttribute("countList", countList);
		return SUCCESS;
	}
	/*
	 * 查询警告人员
	 */
	public String getAllWarn() throws Exception{
		HttpServletRequest request = getRequest();
		String flag = request.getParameter("flag");
		String code = request.getParameter("code");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		
		LogVo logvo = new LogVo();
		logvo.setLogflag(flag);
		logvo.setCode(code);
		logvo.setKsrq(ksrq);
		logvo.setJsrq(jsrq);
		logvo.setTjms(tjms);
		List<LogVo> list = logService.searchLog(logvo);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				LogVo logvos = list.get(i);
				if("1".equals(logvos.getStatus())){logvos.setStatus("未叫号");}
				if("2".equals(logvos.getStatus())){logvos.setStatus("办理中");}
				if("3".equals(logvos.getStatus())){logvos.setStatus("已过号");}
				if("4".equals(logvos.getStatus())){logvos.setStatus("办理完成");}
				if("5".equals(logvos.getStatus())){logvos.setStatus("评价完成");}
				if("6".equals(logvos.getStatus())){logvos.setStatus("评价完成");}
				if("7".equals(logvos.getStatus())){logvos.setStatus("已挂起");}
				
			}
		}
		request.setAttribute("loglist", list);
		return SUCCESS;
	}
	/**
	 * 添加日志信息
	 * @return List
	 */
	public String addLogContent() throws Exception {
		HttpServletRequest request = getRequest();
		String params = request.getParameter("params");
		if(null != params){
			//0初始化失败，1拍照失败
			String okORerror = "";
			if(params.equals(0)){
				okORerror="初始化失败";
			}else{
				okORerror="拍照失败";
			}
			LogVo logVolist = new LogVo();
			List<LogVo> list = logService.searchLog(logVolist);
			try {
				LogVo logVo = new LogVo();
				logVo.setId(String.valueOf(list.size()+1));
				logVo.setLogContent(okORerror);
				logVo.setLogflag("1");
				logService.addLogContent(logVo);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
