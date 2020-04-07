package com.suntendy.queue.hmd.action;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;






import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.ErrorVO;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.hmd.domain.Hmd;
import com.suntendy.queue.hmd.domain.HmdLog;
import com.suntendy.queue.hmd.service.IhmdService;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.operateLog.OperateLog;
import com.suntendy.queue.systemlog.service.ISystemLogService;
import com.suntendy.queue.util.cache.CacheManager;

public class HmdAction extends BaseAction {

	private File excelfile;
	private IhmdService hmdService;
	private ISystemLogService iSystemLogService;
	
	public void setiSystemLogService(ISystemLogService iSystemLogService) {
		this.iSystemLogService = iSystemLogService;
	}

	public IhmdService getHmdService() {
		return hmdService;
	}
	public void setHmdService(IhmdService hmdService) {
		this.hmdService = hmdService;
	}
	
	public File getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(File excelfile) {
		this.excelfile = excelfile;
	}
	public String toAddHmd(){
		return "toAddHmd";
	}
	/*
	 * 添加黑名单
	 */
	public String addHmd(){
		HttpServletRequest request = getRequest();
		String name = StringUtils.trimToEmpty(request.getParameter("name"));
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		String company = StringUtils.trimToEmpty(request.getParameter("company"));
//		String quhaos = StringUtils.trimToEmpty(request.getParameter("quhaos"));
		String reason = StringUtils.trimToEmpty(request.getParameter("reason"));
		Employee user = (Employee) this.getHttpSession().getAttribute("user");
		Hmd hmd = new Hmd();
		HmdLog hlog = new HmdLog();
		hmd.setName(name);
		hmd.setId(id);
		hmd.setCompany(company);
//		hmd.setQuhaos(quhaos);
		hmd.setReason(reason);
		hlog.setId(id);
		hlog.setUsername(user.getName());
		hlog.setSta("0");
		try {
			hmdService.saveHmd(hmd);
			hmdService.saveHmdLog(hlog);
			request.setAttribute("msg", "黑名单添加成功！");
			
			// 完成 新增操作进入日志功能 开始
			
			String event="新增";
			String module="黑名单管理";
			String moduleType="黑名单设置";
			String eventType="增";
			String coreBusiness="0";
			String result="0";
			String resultDepict="新增成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			user = (Employee) session.getAttribute("user");
			String userName =user.getName();
			
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
			e.printStackTrace();
			request.setAttribute("msg", "黑名单添加失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "hmd/hmdList.action");
		return "success";
	}
	
	/*
	 * 黑名单列表
	 */
	public String hmdList() throws Exception{
		HttpServletRequest request = getRequest();
		String name = StringUtils.trimToEmpty(request.getParameter("name"));
		String idCard = StringUtils.trimToEmpty(request.getParameter("idCard"));
		Hmd hmd = new Hmd();
		hmd.setName(name);
		hmd.setId(idCard);
		List<Hmd> list = hmdService.queryAllHmd(hmd);
		for (int i = 0; i < list.size(); i++) {
			Hmd hmdVO = list.get(i);
			String id = hmdVO.getId();
			String operate ="<a onclick=delHmd('" + id
			+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
			hmdVO.setOperation(operate);
			request.setAttribute("name", hmdVO.getName());
			request.setAttribute("id", id);
		}
		request.setAttribute("list", list);
		
		// 完成 查询操作进入日志功能 开始
		
		String event="查询";
		String module="黑名单管理";
		String moduleType="黑名单设置";
		String eventType="查";
		String coreBusiness="0";
		String result="0";
		String resultDepict="查询成功";
		//用户名 
		//String userName = request.getParameter("yhdh");  --这种方式取不到
		HttpSession session = getHttpSession();
		Employee employee = new Employee();
		Employee user = (Employee) session.getAttribute("user");
		String userName =user.getName();
		
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
		
		
		
		
		
		
		
		return "list";
	}
	
	/*
	 * 删除黑名单
	 */
	public String delHmd(){
		HttpServletRequest request = getRequest();
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		Employee user = (Employee) this.getHttpSession().getAttribute("user");
		HmdLog hlog = new HmdLog();
		hlog.setId(id);
		hlog.setUsername(user.getName());
		hlog.setSta("1");
		try {
			hmdService.delHmd(id);
			hmdService.saveHmdLog(hlog);
			request.setAttribute("msg", "黑名单删除成功！");
			
			// 完成 删除操作进入日志功能 开始
			
			String event="删除";
			String module="黑名单管理";
			String moduleType="黑名单设置";
			String eventType="删";
			String coreBusiness="0";
			String result="0";
			String resultDepict="删除成功";
			//用户名 
			//String userName = request.getParameter("yhdh");  --这种方式取不到
			HttpSession session = getHttpSession();
			Employee employee = new Employee();
			user = (Employee) session.getAttribute("user");
			String userName =user.getName();
			
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
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "黑名单删除失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "hmd/hmdList.action");
		return "success";
	}
	
	/*
	 * 根据身份证ID查询是否在黑名单
	 */
	public String queryForCheck() throws Exception{
		HttpServletRequest request = getRequest();
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		Hmd hmd = hmdService.queryById(id);
		boolean falg = false;
		if (hmd != null) {
			falg = true;
		}
		getResponse().getWriter().print(falg);
		return null;
	}
	
	/*
	 * 黑名单模板下载
	 */
	public String downHmdExcel() throws Exception {
		HttpServletResponse response = getResponse();
		String name = "黑名单信息模板.xls";
		// web绝对路径
		String savePath = getHttpSession().getServletContext().getRealPath("/hmd");
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
	
	/*
	 * 黑名单批量导入
	 */
	
	public String hmdexcel() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");

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
				String moban = getHttpSession().getServletContext().getRealPath("/hmd/黑名单信息模板.xls");
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
					List<Hmd> datas = new ArrayList<Hmd>();
					for (int j = 1; j < rows; j++) {
						String flg = "0";
						String name = sheet.getCell(0, j).getContents();
						String id = sheet.getCell(1, j).getContents();
						String company = sheet.getCell(2, j).getContents();
						String reason = sheet.getCell(3, j).getContents();
						
						// 验证数据
						sb.append("第" + j + "条数据：");
						
						if (StringUtils.isEmpty(name)) {
							sb.append("【姓名】没有值,");
							flg = "1";
						} else if (name.length() > 10) {
							sb.append("【姓名】超出规定长度,");
							flg = "1";
						}
						if (StringUtils.isEmpty(id)) {
							sb.append("【身份证】没有值,");
							flg = "1";
						} else if (id.length() != 15 && id.length() != 18) {
							sb.append("【身份证】不是15或18位,");
							flg = "1";
						} else if (null != hmdService.queryById(id)) {
							sb.append("数据库中已经存在此【身份证】,");
							flg = "1";
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
							Hmd hmdVo = new Hmd();
							hmdVo.setName(name);
							hmdVo.setId(id);
							hmdVo.setCompany(company);
							hmdVo.setReason(reason);
							datas.add(hmdVo);
						}
					}
					try {
						hmdService.addAllHmd(datas);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/*
	 * 自动添加黑名单
	 */
	public void addHmdZ(){
		HttpServletRequest request = getRequest();
		String name = "已加密";
		String id = StringUtils.trimToEmpty(request.getParameter("IDNumber"));;
		String company = "已加密";
//		String quhaos = StringUtils.trimToEmpty(request.getParameter("quhaos"));
		String reason = "当月取号次数过多";
		Hmd hmd = new Hmd();
		HmdLog hlog = new HmdLog();
		hmd.setName(name);
		hmd.setId(id);
		hmd.setCompany(company);
//		hmd.setQuhaos(quhaos);
		hmd.setReason(reason);
		hlog.setId(id);
		hlog.setUsername("系统添加");
		hlog.setSta("0");
		try {
			hmdService.saveHmd(hmd);
			hmdService.saveHmdLog(hlog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
