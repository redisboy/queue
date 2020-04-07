package com.suntendy.queue.agent.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.ecside.table.limit.Limit;
import org.ecside.util.RequestUtils;

import sun.misc.BASE64Decoder;

import com.suntendy.core.util.lang.StringUtil;
import com.suntendy.queue.agent.services.IAgentService;
import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.ErrorVO;
import com.suntendy.queue.agent.vo.MessageVO;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.trff.TrffUtils;

/**
 * 代理人管理
 */
public class AgentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File excelfile;
	private IAgentService agentService;
	private IBusinessService businessService;
	private DeptService deptService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}
	
	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public File getExcelfile() {
		return excelfile;
	}

	public void setExcelfile(File excelfile) {
		this.excelfile = excelfile;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public void setAgentService(IAgentService agentService) {
		this.agentService = agentService;
	}

	/**
	 * 代理人信息查询
	 */
	public String selAgent() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		String deptCode = "";
		if("0".equals(role)){
			deptCode=request.getParameter("deptCode");
		}else if("1".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
			
		}else if ("2".equals(role)) {
			deptCode=cacheManager.getOfDeptCache("deptCode");
		}
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		String name = StringUtils.trimToEmpty(request.getParameter("name"));
		String unit = StringUtils.trimToEmpty(request.getParameter("unit"));// 单位
		String pxzd = StringUtils.trimToEmpty(request.getParameter("pxzd"));// 排序字段
		String pxfs = StringUtils.trimToEmpty(request.getParameter("pxfs"));// 排序方式
		String idCard = StringUtils.trimToEmpty(request.getParameter("idCard"));
		String status = StringUtils.trimToEmpty(request.getParameter("status"));// 状态
		Integer count = agentService.countAgent(name,idCard,id,status,unit,pxzd,pxfs,deptCode);
		int pageSize = RequestUtils.getCurrentRowsDisplayed(request);
		int pageNum = RequestUtils.getPageNo(request);
		Limit limit = RequestUtils.getLimit(request);
		if(pageSize==0){
			pageSize=15;
		}
		int pagestart = (pageNum-1)*pageSize;
		int pageEnd = pageNum*pageSize;
		limit.setRowAttributes(count, pageSize);
		MessageVO messageVO = new MessageVO(name, idCard, id, status, unit, pxzd, pxfs,deptCode,Integer.toString(pagestart),Integer.toString(pageEnd));
		List<AgentVO> list = agentService.searchAgent(messageVO);
		if (null != list && !list.isEmpty()) {
			for (AgentVO agent : list) {
				if ("0".equals(agent.getStatus())) {
					agent.setStatus("注销");
				} else if ("1".equals(agent.getStatus())) {
					agent.setStatus("正常");
				} else if ("2".equals(agent.getStatus())) {
					agent.setStatus("暂停");
				}
				agent.setUpd("<a href=agentseleUpd.action?id=" + agent.getId() + "><img src=/queue/images/button_edit.jpg /></a>");
				if (StringUtils.isEmpty(agent.getLogout_date())) {
					agent.setDel("<img src='/queue/images/button_del.jpg' style='cursor:pointer' onclick='delteAgent(" + agent.getId() + ")'/>");
				}
				// 代理人年检
				agent.setUpdnj("<a href=agentseleUpdnj.action?id=" + agent.getId() + "><img src=/queue/images/button_sp.jpg /></a>");
				agent.setAgentmsg("<a href=agentseleMsg.action?id=" + agent.getId() + "><img src=/queue/images/button_ck.jpg /></a>");
			}
		}
		request.setAttribute("deptCode",deptCode);
		request.setAttribute("list", list);
		request.setAttribute("totalRows", new Integer(list.size()));
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="代理人管理";
		String moduleType="代理人登记";
		String eventType="查";
		String coreBusiness="0";
		String result="0";
		String resultDepict="查询成功";
		//用户名 
		//String userName = request.getParameter("yhdh");  --这种方式取不到
		session = getHttpSession();
		Employee employee = new Employee();
		user = (Employee) session.getAttribute("user");
		String userName =user.getCode();
		
		OperateLog operateLog = new OperateLog();
		Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
		operate.setResult(result);
		try {
			iSystemLogService.addOperate(operate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 查询操作进入日志功能代码块结束
		
		
		
		return SUCCESS;
	}

	/**
	 * 代理人删除
	 */
	public String delAgent() {
		HttpServletRequest request = getRequest();
		
		try {
			if (1 == agentService.deleteAgent(request.getParameter("id"))) {
				request.setAttribute("msg", "代理人信息删除成功！");
				
				
				// 完成 删除操作进入日志功能 开始
				
				String event="删除";
				String module="代理人管理";
				String moduleType="代理人登记";
				String eventType="删";
				String coreBusiness="0";
				String result="0";
				String resultDepict="删除成功";
				//用户名 
				//String userName = request.getParameter("yhdh");  --这种方式取不到
				HttpSession session = getHttpSession();
				Employee employee = new Employee();
				Employee user = (Employee) session.getAttribute("user");
				String userName =user.getCode();
				
				OperateLog operateLog = new OperateLog();
				Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
				operate.setResult(result);
				try {
					iSystemLogService.addOperate(operate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 删除操作进入日志功能代码块结束

				
			} else {
				request.setAttribute("msg", "代理人信息删除失败！");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "在执行过程中发生异常，异常信息：<br/>" + e.getMessage());
		}
		
		request.setAttribute("action", "dlrdj.action");
		return SUCCESS;
	}

	/**
	 * 代理人修改查询
	 */
	public String updseleAgent() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String deptCodeName;
		String deptHallName;
		String deptCodeAndHallName;
		String id = request.getParameter("id");
		AgentVO agent = agentService.searchRepeatById(id,deptCode);
		request.setAttribute("agent", agent);

		//String stype = agent.getStype();
		//String stroke = agent.getYwbs();
		//request.setAttribute("stype", stype);
		//request.setAttribute("stroke", stroke);
		HashMap<String, String> info = new HashMap<String, String>();
		String[] stype = StringUtil.split(agent.getStype(),",");
		String[] stroke = StringUtil.split(agent.getYwbs(),",");
		for(int i=0;i<stype.length;i++){
			info.put(stype[i],stroke[i]);
		}
		List<Business> qlist = businessService.getBusinessList("", "", "","",null,null);
		HashMap<String,ArrayList<Business>> result = new HashMap<String,ArrayList<Business>>();
		List<Map<String, String>> list = deptService.findAllDeptcode();
		for(Map<String, String> itemDeptcode:list){
			deptCode=itemDeptcode.get("DEPTCODE");
			deptCodeName=itemDeptcode.get("DEPTCODENAME");
			deptHall=itemDeptcode.get("DEPTHALL");
			deptHallName=itemDeptcode.get("DEPTNAME");
			info.put(deptCode+deptHall, deptHallName);
			result.put(deptHallName,new ArrayList<Business>());
		}
		for(Business business:qlist){
			//business.setDeptCode(info.get(business.getDeptCode()));
			//business.setDeptHall(info.get(business.getDeptHall()));
			deptCodeAndHallName=info.get(business.getDeptCode()+business.getDeptHall());
			if (info.get(business.getId())==null||"".equals(info.get(business.getId()))) {
				business.setYwbs("0");
			}else {
				business.setYwbs(info.get(business.getId()));
			}
			result.get(deptCodeAndHallName).add(business);
		}
		request.setAttribute("result",result);

		return SUCCESS;
	}

	/**
	 * 代理人信息修改
	 */
	public String updAgent() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String validity = request.getParameter("validity");
		String status = request.getParameter("status");
		String dlrqhcs = request.getParameter("dlrqhcs");
		if ("".equals(dlrqhcs) || null == dlrqhcs) {
			dlrqhcs = "1000";
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String jyw = id + "," + name + "," + idCard + ",,," + validity + ",,,,,,,,,,,,,,,";
		
		AgentVO agent = new AgentVO();
		agent.setId(id);
		agent.setJyw(jyw);
		agent.setName(name);
		agent.setIdCard(idCard);
		agent.setStatus(status);
		agent.setUnit(request.getParameter("unit"));
		agent.setRemark(request.getParameter("remark"));
		agent.setDlrqhcs(dlrqhcs);
		agent.setMax_lshs("10");
		agent.setCode(request.getParameterValues("code"));
		agent.setStroke(request.getParameterValues("stroke"));
		agent.setCellphone(request.getParameter("cellphone"));
		agent.setUnit_phone(request.getParameter("unit_phone"));
		agent.setFingerprint(request.getParameter("base64Code2"));// 指纹
		agent.setIncorporator(request.getParameter("incorporator"));
		agent.setId_photo(getByte(request.getParameter("base64Code")));
		agent.setMax_times_byday("100");
		agent.setDeptCode(deptCode);
		
		try {
			if (1 == agentService.updateDlr(agent)) {
				request.setAttribute("msg", "代理人信息修改成功！");
				
				// 完成 修改操作进入日志功能 开始
				
				String event="修改";
				String module="代理人管理";
				String moduleType="代理人登记";
				String eventType="改";
				String coreBusiness="0";
				String result="0";
				String resultDepict="修改成功";
				//用户名 
				//String userName = request.getParameter("yhdh");  --这种方式取不到
				HttpSession session = getHttpSession();
				Employee employee = new Employee();
				Employee user = (Employee) session.getAttribute("user");
				String userName =user.getCode();
				
				OperateLog operateLog = new OperateLog();
				Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
				operate.setResult(result);
				try {
					iSystemLogService.addOperate(operate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 修改操作进入日志功能代码块结束
				
				
				
				
				
			} else {
				request.setAttribute("msg", "代理人信息修改失败！");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "在执行过程中发生异常，异常信息：<br/>" + e.getMessage());
		}

		request.setAttribute("action", "dlrdj.action");
		return SUCCESS;
	}

	/**
	 * 代理人年检状态修改
	 */
	public String updnjAgent() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String validity = request.getParameter("validity");
		String[] code = request.getParameterValues("code");
		String[] stroke = request.getParameterValues("stroke");
		int id = Integer.parseInt(request.getParameter("id"));
		String jyw = id + "," + name + "," + idCard + ",,," + validity + ",,,,,,,,,,,,,,,";
		
		// 代理人
		AgentVO agent = new AgentVO();
		agent.setId(id);
		agent.setJyw(jyw);
		agent.setIdCard(idCard);
		agent.setValidity(validity);
		agent.setStatus(request.getParameter("status"));
		agent.setCode(code);
		agent.setStroke(stroke);
		agent.setUser(((Employee) getHttpSession().getAttribute("user")).getCode());
		agent.setDeptCode(deptCode);
		try {
			agentService.updateAgent(agent);
			request.setAttribute("msg", "代理人状态修改成功！");
		} catch (Exception e) {
			request.setAttribute("msg", "在执行过程中发生异常，异常信息：<br/>" + e.getMessage());
		}
		request.setAttribute("action", "dlrnj.action");
		return SUCCESS;
	}

	/**
	 * 代理人登记重复查询
	 */
	public void dlrselect() throws Exception {
		String id = getRequest().getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		int flag = 0;
		if (null != agentService.searchRepeatById(id,deptCode)) {
			flag = 1;
		}
		getResponse().getWriter().print(flag);
	}

	public void dlrselect2() throws Exception {
		String idCard = getRequest().getParameter("idCard");
		String flag = agentService.searchRepeatByIDCard(idCard);
		getResponse().getWriter().print(flag);
	}

	/**
	 * 代理人登记 页面跳转
	 */
	public String dlidjAddAgent() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptCodeName;
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String deptHallName;
		String deptCodeAndName;
		HashMap<String, String> info = new HashMap<String, String>();
		HashMap<String,ArrayList<Business>> result = new HashMap<String, ArrayList<Business>>();
		MessageVO messageVO = new MessageVO();
		messageVO.setDeptCode(deptCode);
		
		//List<AgentVO> agentList = agentService.searchAgent(messageVO);//获取代理人最大id
		List<Business> qlist = businessService.getBusinessList("", "", "", "", "","");
		List<Map<String, String>> list = deptService.findAllDeptcode();
		
		for(Map<String, String> itemDeptcode:list){
			deptCode=itemDeptcode.get("DEPTCODE");
			deptCodeName=itemDeptcode.get("DEPTCODENAME");
			deptHall=itemDeptcode.get("DEPTHALL");
			deptHallName=itemDeptcode.get("DEPTNAME");
			info.put(deptCode+deptHall,deptHallName);
			result.put(deptHallName,new ArrayList<Business>());
		}
		for(Business business:qlist){
//			business.setDeptCode(info.get(business.getDeptCode()));
//			business.setDeptHall(info.get(business.getDeptHall()));
			deptCodeAndName = business.getDeptCode()+business.getDeptHall();
			result.get(info.get(deptCodeAndName)).add(business);
		}
		request.setAttribute("result",result);    
		//request.setAttribute("agentMaxId", agentList.size()+1);
		return SUCCESS;
	}

	/**
	 * 代理人登记
	 */
	public String addDlr() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String validity = request.getParameter("validity");
		int id = Integer.parseInt(request.getParameter("id"));
		String dlrqhcs = request.getParameter("dlrqhcs");
		if ("".equals(dlrqhcs) || null == dlrqhcs) {
			dlrqhcs = "1000";
		}
		String jyw = id + "," + name + "," + idCard + ",,," + validity + ",,,,,,,,,,,,,,,";
		Employee employee = (Employee) getHttpSession().getAttribute("user");
		
		AgentVO agent = new AgentVO();
		agent.setId(id);
		agent.setJyw(jyw);
		agent.setName(name);
		agent.setIdCard(idCard);
		agent.setValidity(validity);
		agent.setUser(employee.getCode());// 操作员编号
		agent.setFzjg(employee.getDepartment());
		agent.setUnit(request.getParameter("unit"));
		agent.setStatus(request.getParameter("status"));
		agent.setRemark(request.getParameter("remark"));
		agent.setDlrqhcs(dlrqhcs);
		agent.setCode(request.getParameterValues("code"));
		agent.setStroke(request.getParameterValues("stroke"));
		agent.setMax_lshs("10");
		agent.setCellphone(request.getParameter("cellphone"));
		agent.setUnit_phone(request.getParameter("unit_phone"));
		agent.setFingerprint(request.getParameter("base64Code2"));// 指纹
		agent.setAgent_level(request.getParameter("agent_level"));
		agent.setIncorporator(request.getParameter("incorporator"));
		agent.setId_photo(getByte(request.getParameter("base64Code")));// 照片
		agent.setMax_times_byday("100");
		agent.setDeptCode(deptCode);
		try {
			agentService.addDlr(agent);
			request.setAttribute("msg", "代理人信息录入成功！");
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="代理人管理";
			String moduleType="代理人登记";
			String eventType="增";
			String coreBusiness="0";
			String result="0";
			String resultDepict="新增成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			employee = new Employee();
			Employee user = (Employee) session.getAttribute("user");
			String userName =user.getCode();
			
			OperateLog operateLog = new OperateLog();
			Operate operate = operateLog.operate_log(userName,event, module, moduleType, eventType, coreBusiness, result, resultDepict);
			operate.setResult(result);
			try {
				iSystemLogService.addOperate(operate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 新增操作进入日志功能代码块结束
			
			
			
			
			
		} catch (Exception e) {
			request.setAttribute("msg", "在执行过程中发生异常，异常信息：<br/>" + e.getMessage());
		}

		request.setAttribute("action", "dlrdj.action");
		return SUCCESS;
	}

	public String agentgetphoto() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		AgentVO agent = agentService.searchRepeatById(id,deptCode);
		HttpServletResponse response = getResponse("image/jpeg");
		// 获取照片
		if (agent.getId_photo() != null) {
			ServletOutputStream out = response.getOutputStream();
			out.write(agent.getId_photo());
			out.flush();
			out.close();
		}
		return null;
	}

	// String转换byte[]
	public static byte[] getByte(String base64) {
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = null;
		// 将base64转换为byte[]
		try {
			b = decode.decodeBuffer(base64);
			System.out.println("String转换byte[]成功！");
		} catch (IOException e) {
			System.out.println("String转换byte[]失败！");
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 代理人批量录入
	 */
	public String agentexcel() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		// 需要添加业务，照片，指纹的数据
		List<AgentVO> listVO = agentService.searchNullAgent();
		request.setAttribute("listVO", listVO);

		String filename = request.getParameter("filename");
		String flgggg = request.getParameter("flg");
		if (flgggg == null) {
			getHttpSession().setAttribute("listaddexcel", null);
		}
		if (filename != null && excelfile != null) {
			String absopath = excelfile.getAbsolutePath();
			String newpath = absopath.substring(0, absopath.lastIndexOf(".")) + ".xls";
			File file = new File(newpath);
			boolean renameTo = excelfile.renameTo(file); // 重命名文件 for 读取录入

			if (renameTo) {
				String flag = "1";
				// 获得模板
				String moban = getHttpSession().getServletContext().getRealPath("/agent/代理人信息模板.xls");
				File f = new File(moban);
				Workbook workbook = Workbook.getWorkbook(f);
				Sheet sheet1 = workbook.getSheet(0);
				String[] h = new String[sheet1.getColumns()];
				for (int m = 0, columns = sheet1.getColumns(); m < columns; m++) {
					h[m] = sheet1.getCell(m, 0).getContents();
				}
				
				// 获得工作表对象
				Workbook book = Workbook.getWorkbook(file);
				Sheet sheet = book.getSheet(0);
				int row = sheet.getRows();
				// 检验EXCEL是否是模板格式
				if (row <= 1) {
					flag = "0";
				} else {
					for (int kk = 0, len = h.length; kk < len; kk++) {
						Cell cell1 = sheet.getCell(kk, 0); // 得到单元格
						if (!cell1.getContents().equals(h[kk])) {
							flag = "0";
						}
					}
				}

				if ("0".equals(flag)) {
					request.setAttribute("msg", "EXCEL文件与模板文件不符或文件内容为空");
				} else {
					// 验证数据存数据
					int rows = sheet.getRows();
					String yearreg = "(\\d{4}|\\d{2})-((1[0-2])|(0?[1-9]))-(([12][0-9])|(3[01])|(0?[1-9]))";
					String timereg = "^[0-9]*$";
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					StringBuffer sb = new StringBuffer();
					List<AgentVO> datas = new ArrayList<AgentVO>();
					for (int j = 1; j < rows; j++) {
						String flg = "0";
						String id = sheet.getCell(0, j).getContents();
						String name = sheet.getCell(1, j).getContents();
						String idcard = sheet.getCell(2, j).getContents();
						String unit = sheet.getCell(3, j).getContents();
						String register_date = "";
						if (sheet.getCell(4, j).getType() == CellType.DATE) {// 日期格式处理
							DateCell dc = (DateCell) sheet.getCell(4, j);
							Date date = dc.getDate();
							register_date = format2.format(date);
						} else {
							register_date = sheet.getCell(4, j).getContents();
						}
						String validity = "";
						if (sheet.getCell(5, j).getType() == CellType.DATE) {// 日期格式处理
							DateCell dc = (DateCell) sheet.getCell(5, j);
							Date date = dc.getDate();
							validity = format2.format(date);
						} else {
							validity = sheet.getCell(5, j).getContents();
						}
						String logout_date = "";
						if (sheet.getCell(6, j).getType() == CellType.DATE) {// 日期格式处理
							DateCell dc = (DateCell) sheet.getCell(6, j);
							Date date = dc.getDate();
							logout_date = format2.format(date);
						} else {
							logout_date = sheet.getCell(6, j).getContents();
						}
						String status = sheet.getCell(7, j).getContents();
						String cellphone = sheet.getCell(8, j).getContents();
						String unit_phone = sheet.getCell(9, j).getContents();
						String incorporator = sheet.getCell(10, j).getContents();
						String max_lshs = "10";// sheet.getCell(11, j).getContents();
						String max_times_byday = "100";//sheet.getCell(12, j).getContents();
						String agent_level = sheet.getCell(13, j).getContents();
						String check_date = "";
						if (sheet.getCell(14, j).getType() == CellType.DATE) {// 日期格式处理
							DateCell dc = (DateCell) sheet.getCell(14, j);
							Date date = dc.getDate();
							check_date = format2.format(date);
						} else {
							check_date = sheet.getCell(14, j).getContents();
						}
						String remark = sheet.getCell(15, j).getContents();
						String fzjg = sheet.getCell(16, j).getContents();
						String dlrqhcs = sheet.getCell(17, j).getContents();;
						
						// 验证数据
						sb.append("第" + j + "条数据：");
						if (StringUtils.isEmpty(id)) {
							sb.append("【编号】没有值,");
							flg = "1";
						} else if (!id.matches(timereg)) {
							sb.append("【编号】值不是数字,");
							flg = "1";
						} else if (id.length() > 30) {
							sb.append("【编号】超出规定长度,");
							flg = "1";
						} else if (null != agentService.searchRepeatById(id,deptCode)) {
							sb.append("数据库中已经存在此【编号】,");
							flg = "1";
						}
						if (StringUtils.isEmpty(name)) {
							sb.append("【姓名】没有值,");
							flg = "1";
						} else if (name.length() > 10) {
							sb.append("【姓名】超出规定长度,");
							flg = "1";
						}
						if (StringUtils.isEmpty(idcard)) {
							sb.append("【身份证】没有值,");
							flg = "1";
						} else if (idcard.length() != 15 && idcard.length() != 18) {
							sb.append("【身份证】不是15或18位,");
							flg = "1";
						} else if ("1".equals(agentService.searchRepeatByIDCard(idcard))) {
							sb.append("数据库中已经存在此【身份证】,");
							flg = "1";
						}
						if (StringUtils.isEmpty(register_date)) {
							sb.append("【登记日期】没有值,");
							flg = "1";
						} else if (!register_date.matches(yearreg)) {
							sb.append("【登记日期】不符合日期格式(yyyy-mm-dd),");
							flg = "1";
						}
						if (StringUtils.isEmpty(validity)) {
							sb.append("【有效期止】没有值,");
							flg = "1";
						} else if (!validity.matches(yearreg)) {
							sb.append("【有效期止】不符合日期格式(yyyy-mm-dd),");
							flg = "1";
						}
						// logout_date
						if (StringUtils.isNotEmpty(logout_date) && !logout_date.matches(yearreg)) {
							sb.append("【注销日期】不符合日期格式(yyyy-mm-dd),");
							flg = "1";
						}
						if (StringUtils.isEmpty(status)) {
							sb.append("【状态】没有值 ,");
							flg = "1";
						} else if (!status.matches(timereg)) {
							sb.append("【状态】不是数字,");
							flg = "1";
						} else if (Integer.parseInt(status) > 2) {
							sb.append("【状态】值不正确,");
							flg = "1";
						}
						if (cellphone.length() > 11) {
							sb.append("【手机】号码长度不正确 ,");
							flg = "1";
						}
						if (StringUtils.isEmpty(max_lshs)) {
							sb.append("【单个小票办理流水上限】没有值,");
							flg = "1";
						} else if (!max_lshs.matches(timereg)) {
							sb.append("【单个小票办理流水上限】不是数字,");
							flg = "1";
						} else if (max_lshs.length() > 6) {
							sb.append("【单个小票办理流水上限】超出规定长度,");
							flg = "1";
						}
						if (StringUtils.isEmpty(max_times_byday)) {
							sb.append("【当天取号次数上限】没有值,");
							flg = "1";
						} else if (!max_times_byday.matches(timereg)) {
							sb.append("【当天取号次数上限】不是数字,");
							flg = "1";
						} else if (max_times_byday.length() > 6) {
							sb.append("【当天取号次数上限】超出规定长度,");
							flg = "1";
						}
						if (agent_level.length() > 3) {
							sb.append("【代理级别】超出规定长度,");
							flg = "1";
						}
						if (StringUtils.isNotEmpty(check_date) && !check_date.matches(yearreg)) {
							sb.append("【审核时间】不符合日期格式(yyyy-mm-dd),");
							flg = "1";
						}
						if (fzjg.length() > 12) {
							sb.append("【发证机关】超出规定长度,");
							flg = "1";
						}
						if ("".equals(dlrqhcs) ||  null == dlrqhcs || StringUtils.isEmpty(dlrqhcs)) {
							dlrqhcs = "1000";
						}
						sb.append(";");

						String s = sb.charAt(sb.length() - 2) + "";
						if (",".equals(s)) {
							sb.delete(sb.length() - 2, sb.length() - 1);
						} else {
							sb.insert(sb.length() - 1, "正常存储");
						}
						List<ErrorVO> list = new ArrayList<ErrorVO>();
						String[] str = sb.toString().split(";");
						for (String st : str) {
							ErrorVO e = new ErrorVO();
							e.setError(st);
							list.add(e);
						}
						getHttpSession().setAttribute("listaddexcel", list);
						//批量导入添加部门
						if ("0".equals(flg)) {
							AgentVO agent = new AgentVO(Integer.parseInt(id), name, idcard, unit,
								register_date, validity, logout_date, status, cellphone, unit_phone,
								incorporator, max_lshs, max_times_byday, agent_level, check_date, remark, fzjg,deptCode,dlrqhcs);
							String jyw = id + "," + name + "," + idcard + ",,," + validity + ",,,,,,,,,,,,,,,";
							agent.setJyw(jyw);
							datas.add(agent);
						}
					}
					try {
						agentService.addAllAgent(datas);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String downExcel() throws Exception {
		HttpServletResponse response = getResponse();
		String name = "代理人信息模板.xls";
		// web绝对路径
		String savePath = getHttpSession().getServletContext().getRealPath("/agent");
		// 设置为下载application/x-download
		response.setContentType("application/x-download");
		// 即将下载的文件在服务器上的绝对路径
		String filenamedownload = savePath + "/" + name;
		// 下载文件时显示的文件保存名称
		String filenamedisplay = name;
		// 中文编码转换
		filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
		try {
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(filenamedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 代理人批量录入修改查询
	 */
	public String agentExcelUpd()throws Exception{
		HttpServletRequest request=getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String id=request.getParameter("id");
		List<AgentVO> list=agentService.selectExcel(id);
		AgentVO agent=list.get(0);
		request.setAttribute("agent", agent);
		String stype=agent.getStype();
		request.setAttribute("stype",stype);
		List<Business> qlist=businessService.getBusinessList("","","","",deptCode,deptHall);
		request.setAttribute("qlist",qlist);
		
		
		return SUCCESS;
	}
	
	public String agentExcelUp()throws Exception{
		HttpServletRequest request=getRequest();
		String pid=((Employee) getHttpSession().getAttribute("user")).getCode();
		int id=Integer.parseInt(request.getParameter("id"));
		String validity=request.getParameter("validity");
		String []code=request.getParameterValues("code");
		String idCard=request.getParameter("idCard");
		AgentVO agent=new AgentVO();
		agent.setUser(pid);
		agent.setId(id);
		agent.setValidity(validity);
		agent.setCode(code);
		agent.setStroke(request.getParameterValues("stroke"));
		agent.setIdCard(idCard);
		try {
			agentService.updateAgent2(agent);
			request.setAttribute("msg", "代理人业务添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "代理人业务添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action","agentexcel.action");
		
		return SUCCESS;
	}
	/*
	 * 审核所有
	 */
	public String agentExcelUpAll()throws Exception{
		HttpServletRequest request=getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String id=request.getParameter("id");
		List<AgentVO> list=agentService.selectExcel(id);
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				AgentVO agent=list.get(i);
				request.setAttribute("agent", agent);
				String stype=agent.getStype();
				request.setAttribute("stype",stype);
				List<Business> qlist=businessService.getBusinessList("","","","",deptCode,deptHall);
				
				if(null != qlist && qlist.size()>0){
					String[] code = new String[qlist.size()];
					String[] stroke = new String[qlist.size()];
					for(int j =0 ;j<qlist.size();j++){
						Business Business=(Business)qlist.get(j);
						code[j]=Business.getId();
						stroke[j]="5";
					}
					agent.setCode(code);
					agent.setStroke(stroke);
				}
				try {
					agentService.updateAgent2(agent);
					if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
						String jyw = agent.getId() + "," + agent.getName() + "," + agent.getIdCard() + ",,," + agent.getValidity() + ",,,,,,,,,,,,,,,";
						agent.setJyw(jyw);agent.setBj("1");
						agent.setJyw(agentService.toMD5byJyw(agent.getJyw()));//MD5加密jyw
						String[] result1 = TrffUtils.updateAgent(agent);
						if (!"1".equals(result1[0])) {
							throw new Exception("[代理人数据批量上传->添加]：" + result1[1]);
						}
					}
					request.setAttribute("msg", "代理人业务添加成功！");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "代理人业务添加失败，<br>在执行过程中发生异常！");
				}
			}
		}else{
			request.setAttribute("msg", "没有代理人数据！");
		}
		request.setAttribute("action","agentexcel.action");
		
		return SUCCESS;
	}
	
	public String printAgent() throws Exception{
		HttpServletRequest request = getRequest();
		String id=StringUtils.trimToEmpty(request.getParameter("id"));
		MessageVO messageVO = new MessageVO();
		messageVO.setId(id);
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		AgentVO agentVO = agentService.searchRepeatById(id, deptCode);
		request.setAttribute("agentInfo",agentVO);
		request.setAttribute("id",id);
		return SUCCESS;
	}
}