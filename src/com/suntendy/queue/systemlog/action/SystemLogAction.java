package com.suntendy.queue.systemlog.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.opensymphony.util.Data;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.safety.domain.Safety;
import com.suntendy.queue.safety.service.ISafetyService;
import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;
import com.suntendy.queue.systemlog.securityLog.SecurityLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.RSAUtilOperate;
import com.suntendy.queue.util.TimeUtilFormat;
import com.suntendy.queue.util.Unlawful;
import com.suntendy.queue.util.cache.CacheManager;

public class SystemLogAction extends BaseAction {
	private int sum=0;
	private ISystemLogService iSystemLogService;
	private ISafetyService safetyService;

	public void setSafetyService(ISafetyService safetyService) {
		this.safetyService = safetyService;
	}

	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	/**
	 * 查询登录日志
	 */
	public String queryLoginls() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");

		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String result = StringUtils.trimToEmpty(request.getParameter("res"));
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));

		Loginls loginls = new Loginls();
		loginls.setDeptCode(deptCode);
		loginls.setDeptHall(deptHall);
		loginls.setResult(result);
		loginls.setUserName(userName);
		List<Loginls> list = iSystemLogService.querylogin(tjms, ksrq, jsrq, loginls);

		String RSA = "";
		String RSAKey = "";
		for (Loginls logls : list) {
			if (null != logls.getResult() && logls.getResult().equals("0")) {
				logls.setResult("成功");
			} else {
				logls.setResult("失败");
			}
			RSA = logls.getUserName() + logls.getOriginIp();
			RSAKey = RSAUtilOperate.RSAOperate(logls.getRSACheck(), 1);
			logls.setRecTime(timeType(logls.getRecordTime()));
			logls.setLeaTime(timeType(logls.getLeaveTime()));
			if (RSA != null && !RSA.equals(RSAKey)) {
				logls = Unlawful.unlawfulLoginls(logls);// 发生非法篡改
			}

		}
		request.setAttribute("list", list);
		return "success";
	}
	
	public void queryOperatemx()throws Exception{
		HttpServletRequest request = getRequest();
		String id =request.getParameter("id");
		JSONObject objPrint = new JSONObject();
		Operate operate = new Operate();
		String core="未定义";
		String content="暂无明细内容!";
		List<Operate> list = iSystemLogService.qureyDetailed(id);
		operate = list.get(0);
		objPrint.put("code", operate.getUserName());
		objPrint.put("opName", operate.getOpName());
		if (null != operate.getContent()) {
			content = new String(operate.getContent());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(content);
			content = m.replaceAll("");
		}
		objPrint.put("content", content.replace("\\s", ""));
		if( operate.getCoreBusiness().equals("0")){
			core="是";
		}else {
			core="否";
		}
		objPrint.put("core", core);
		objPrint.put("opTime", TimeUtilFormat.timeType(operate.getOperateTime(), 0));
		objPrint.put("event", operate.getEvent());
		this.getResponse("text/html").getWriter().println(objPrint.toString());
	}
	
	/**
	 * 查询操作日志
	 */
	public String queryOperate() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String originIp = StringUtils.trimToEmpty(request
				.getParameter("originIp"));
		String module = request.getParameter("module");
		String eventType = request.getParameter("eventType");
		String coreBusiness = request.getParameter("core");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));

		Operate operate = new Operate(userName, originIp, module, eventType,
				coreBusiness, deptCode, deptHall);
		List<Operate> list = iSystemLogService.queryOperate(tjms, ksrq, jsrq,
				operate);
		String RSA = "";
		String RSAKey = "";
		for (Operate oper : list) {
			if (null != oper.getCoreBusiness()
					&& oper.getCoreBusiness().equals("0")) {
				oper.setCoreBusiness("是");
			} else {
				oper.setCoreBusiness("否");
			}
			oper.setoTime(timeType(oper.getOperateTime()));
			oper.setResultDepict("<a href=\"#\" style=\"color:blue;\" onclick=\"qureyDetailed('"+ oper.getId() +"');return false;\">查看明细</a>");
			String a=String.valueOf( oper.getUserName().length());
			RSA = a + oper.getOriginIp();
			RSAKey = RSAUtilOperate.RSAOperate(oper.getRSACheck(), 1);
			if (RSA != null && !RSA.equals(RSAKey)) {
				oper.setoTime(timeType(oper.getOperateTime()));
				oper = Unlawful.unlawfulOpertate(oper);// 发生非法篡改
			}

		}
		request.setAttribute("list", list);
		return "success";
	}
	
	
	/**
	 * 查询安全日志
	 */
	public String querySecurity() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		HttpServletRequest request = getRequest();
		String userName = request.getParameter("userName");
		String originIp = StringUtils.trimToEmpty(request
				.getParameter("originIp"));
		String flag = StringUtils.trimToEmpty(request.getParameter("flag"));
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		sum+=1;
		if(sum==5||sum==10||sum==20){
			queryLoginS();// 插入安全日志
			if(sum==25){
				sum=0;
			}
		}
		Security security = new Security();
		security.setDeptCode(deptCode);
		security.setDeptHall(deptHall);
		security.setOriginIp(originIp);
		security.setUserName(userName);
		List<Security> list = iSystemLogService.querySecurity(tjms, ksrq, jsrq,
				security);
			String RSA = "";
			String RSAKey = "";
			for (Security sec : list) {
				sec.setrTime(timeType(sec.getRecordTime()));
				sec.setResultDepict("<a href=\"#\" style=\"color:blue;\" onclick=\"qureyDetailed('"+ sec.getId() +"');return false;\">查看明细</a>");
				if(flag!=null&&flag.equals("1")){
					Unlawful.unlawfulAudit(sec);
				}else {
					RSA = sec.getUserName() + sec.getOriginIp();
					RSAKey = RSAUtilOperate.RSAOperate(sec.getRSACheck(), 1);
					if (RSA != null && !RSA.equals(RSAKey)) {
						sec.setrTime(timeType(sec.getRecordTime()));
						sec = Unlawful.unlawfulSecurity(sec);// 发生非法篡改
					}
				}
			}
		request.setAttribute("list", list);
		return "success";
	}
	
	public void querySecuritymx()throws Exception{
		HttpServletRequest request = getRequest();
		String id =request.getParameter("id");
		JSONObject objPrint = new JSONObject();
		Security security = new Security();
		String content="暂无明细内容!";
		List<Security> list = iSystemLogService.qureyDetailedSe(id);
		security = list.get(0);
		objPrint.put("code", security.getUserName());
		objPrint.put("opName", security.getOpName());
		if (null != security.getContent()) {
			content = new String(security.getContent());
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(content);
			content = m.replaceAll("");
		}
		objPrint.put("content", content.replace("\\s", ""));
		objPrint.put("opTime", TimeUtilFormat.timeType(security.getRecordTime(), 0));
		objPrint.put("event", security.getEvent());
		this.getResponse("text/html").getWriter().println(objPrint.toString());
	}
	// 转换时间格式
	public String timeType(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null && !date.equals("")) {
			return sdf.format(date);
		} else {
			return "";
		}
	}

	// 查詢長期未登錄用戶插入安全日志
	public void queryLoginS() {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Loginls loginls = new Loginls();
		loginls.setDeptCode(deptCode);
		loginls.setDeptHall(deptHall);
		List<Loginls> loginList;
		try {
			loginList = iSystemLogService.querylogin("", "", "", loginls);

			if (loginList.size() > 0) {
				for (Loginls list : loginList) {
					// 获取上次登录成功的数据
					List<Loginls> loginl = iSystemLogService
							.queryLoginSuu(list);

					if (loginl != null && !loginl.isEmpty()) {
						loginl.get(0).setrTime(
								TimeUtilFormat.timeType(
										loginl.get(0).recordTime, 0));
						String date = TimeUtilFormat.timeType(new Date(), 1);
						long time = TimeUtilFormat.timeLong(loginl.get(0)
								.getrTime(),date);
						int longTime = Integer.parseInt(String.valueOf(time));
						List<Safety> safetyList = safetyService.searchSafety();
						int loginTime = 30;
						if (safetyList.size() > 0) {
							Safety safety = safetyList.get(0);
							loginTime = Integer.parseInt(safety.getTimelimit());
						}
						if (longTime >= loginTime) {// 长时间没登录
							String event = "该用户账号长时间未使用";
							String eventType = "查";
							String result = "0";
							String resultDepict = "该用户账号长时间未使用";
							String userName = list.getUserName();

							SecurityLog securityLog = new SecurityLog();

							Security security = securityLog.security_log(
									userName, event, eventType, result,
									resultDepict);
							security.setResult(result);
							iSystemLogService.addSecurity(security);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
