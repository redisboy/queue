package com.suntendy.queue.passreason.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.deploy.model.DDBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.passreason.domain.PassReason;
import com.suntendy.queue.passreason.service.IPassReasonService;
import com.suntendy.queue.util.cache.CacheManager;

public class PassReasonAction extends BaseAction {
	private IPassReasonService passReasonService;
	private DeptService deptService;
	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	

	public IPassReasonService getPassReasonService() {
		return passReasonService;
	}

	public void setPassReasonService(IPassReasonService passReasonService) {
		this.passReasonService = passReasonService;
	}

	/**
	 * 添加过号原因
	 * @return
	 */
	public String addPassReason()throws Exception{
		String deptCode = getRequest().getParameter("deptCode");
		String deptHall = getRequest().getParameter("deptHall");
		getRequest().setAttribute("deptCode", deptCode);
		getRequest().setAttribute("deptHall", deptHall);
		return "success";
	}
	/**
	 * 保存过号原因
	 */
	public String savePassReason()throws Exception{
		HttpServletRequest request=getRequest();
		String reason=StringUtils.trimToEmpty(request.getParameter("reason"));
		String code=StringUtils.trimToEmpty(request.getParameter("code"));
		String deptCode=StringUtils.trimToEmpty(request.getParameter("deptCode"));
		String deptHall=StringUtils.trimToEmpty(request.getParameter("deptHall"));
		if (deptCode == null||"".equals(deptCode)) {
			CacheManager cacheManager = CacheManager.getInstance();
			deptCode=cacheManager.getOfDeptCache("deptCode");
			deptHall=cacheManager.getOfDeptCache("deptHall");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date date = new Date();
		String tjrq = simpleDateFormat.format(date); //添加日期
		PassReason passReason = new PassReason();
		passReason.setCode(code);
		passReason.setDeptHall(deptHall);
		passReason.setDeptCode(deptCode);
		passReason.setReason(reason);
		passReason.setTjrq(tjrq);
		try {
			passReasonService.savePassReason(passReason);
			request.setAttribute("msg", "过号原因添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "过号原因添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "selectPassReason.action");
		
		
		return "success";
	}
	/**
	 * 查询过号原因
	 * @return
	 * @throws Exception
	 */
	public String selectPassReason() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		HttpSession session=getHttpSession();
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role =user.getRole();
		String deptCode = getRequest().getParameter("deptCode");
		String deptHall = getRequest().getParameter("deptHall");
		if (deptCode==null||deptCode.equals("")) {
			if("0".equals(role)){
				deptCode=request.getParameter("deptCode");
				deptHall=request.getParameter("deptHall");
			}else {
				deptCode=cacheManager.getOfDeptCache("deptCode");
				deptHall=cacheManager.getOfDeptCache("deptHall");
			}
		}
		List<Map<String, String>> list = deptService.findAllDeptcode();
		HashMap<String, String> deptInfo = new HashMap<String, String>();
		//ArrayList<HashMap<String, String>> deptList = new ArrayList<HashMap<String, String>>();
		for(Map<String, String> item:list){
			String codeAndHall = item.get("DEPTCODE")+"-"+item.get("DEPTHALL");
			String hallName = item.get("DEPTNAME");
			deptInfo.put(codeAndHall, hallName);
		}
		
		PassReason passReason = new PassReason();
		passReason.setDeptCode(deptCode);
		passReason.setDeptHall(deptHall);
		List<PassReason> passReasonlist= passReasonService.selectPassReason(passReason);

		String opera;
		String deptHallName;
		if (passReasonlist!=null&&passReasonlist.size()!=0) {
			for(PassReason item : passReasonlist){
				opera = "<a onclick=updatePassReason('" + item.getId()
				+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
				+ "&nbsp;" + "<a onclick=deletePassReason('" + item.getId()
				+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				//opera="<a onclick=update("+item.getId()+")><img src='/queue/images/button_edit.jpg' style='cursor:hand' /></a>"
				//	+ "&nbsp;"+ "<a onclick=delete("+item.getId()+")><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
				item.setOpera(opera);
				deptHallName = deptInfo.get(item.getDeptCode()+"-"+item.getDeptHall());
				item.setDeptHall(deptHallName);
			}
			request.setAttribute("list", passReasonlist);
		}
		
		
		request.setAttribute("role", role);
		request.setAttribute("deptCode", deptCode);
		request.setAttribute("deptHall", deptHall);
		request.setAttribute("currentDept",deptCode+"-"+deptHall);
		if ("0".equals(role)) {
			request.setAttribute("deptInfo", deptInfo);
		}
		
		return "success";
	}
	
	/**
	 * 删除过号原因
	 * @return
	 * @throws Exception
	 */
	public String  deletePassReason()  throws Exception{
		HttpServletRequest request = getRequest();
		String id= StringUtils.trimToEmpty(request.getParameter("id"));
		try {
			PassReason passReason = new PassReason();
			passReason.setId(id);
			passReasonService.deletePassReason(passReason);
			request.setAttribute("msg", "过号原因删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "过号原因删除失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "selectPassReason.action");
		return "success";
	}
	
		/**
		 * 进入修改过号原因页面
		 * @return
		 * @throws Exception
		 */
	public String toUpdatePassReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		PassReason passReason = new PassReason();
		passReason.setId(id);
		List<PassReason> list = passReasonService.selectPassReason(passReason);
		if (list!=null&&list.size()!=0) {
			request.setAttribute("passReason",list.get(0));
		}
		return "success";
	}
	
		/**
		 * 修改过号原因
		 * @return
		 * @throws Exception
		 */
	public String updatePassReasonById() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id=StringUtils.trimToEmpty(request.getParameter("id"));
		String deptHall=StringUtils.trimToEmpty(request.getParameter("deptHall"));
		String deptCode=StringUtils.trimToEmpty(request.getParameter("deptCode"));
		String reason=StringUtils.trimToEmpty(request.getParameter("reason"));
		String code=StringUtils.trimToEmpty(request.getParameter("code"));
		PassReason passReason  =  new PassReason();
		passReason.setId(id);
		passReason.setReason(reason);
		passReason.setCode(code);
		passReason.setDeptCode(deptCode);
		passReason.setDeptHall(deptHall);
		try {
			passReasonService.updatePassReasonById(passReason);
			request.setAttribute("msg", "过号原因修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "过号原因修改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "selectPassReason.action");
		return "success";
	}
}
