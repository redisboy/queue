package com.suntendy.queue.nextwindow.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.nextwindow.service.InextWindowService;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.util.cache.CacheManager;

public class nextWindowAction extends BaseAction {

	private InextWindowService nextWindowService;
	
	public InextWindowService getNextWindowService() {
		return nextWindowService;
	}

	public void setNextWindowService(InextWindowService nextWindowService) {
		this.nextWindowService = nextWindowService;
	}

	public String toAddNWindow(){
		return "toAddNWindow";
	}
	/*
	 * 跳转到添加下一步提示信息窗口
	 */
	public String toAddNWindowAjax() throws Exception{
		String nwindowDmlbVal = getRequest().getParameter("nwindowDmlbVal");
		List<Code> codeList = nextWindowService.getAllGredentials(nwindowDmlbVal);
		JSONObject datas = new JSONObject();
		if (null != codeList && !codeList.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (Code code : codeList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("dm", code.getDm());
					obj.put("mc", code.getMc());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "获取业务类型失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	/*
	 * 为添加页面查询业务原因
	 */
public String queryYwyyForAdd() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Code codeVO = new Code();
		String nwindowDmzVal = getRequest().getParameter("nwindowDmzVal");
		String nwindowDmlbVal = getRequest().getParameter("nwindowDmlbVal");
		codeVO.setDmz(nwindowDmzVal);
		codeVO.setDmlb(nwindowDmlbVal);
		codeVO.setDeptcode(deptCode);
		codeVO.setDepthall(deptHall);
		List<Code> codeList = nextWindowService.queryForYwyy(codeVO);
		JSONObject datas = new JSONObject();
		if (null != codeList && !codeList.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (Code code : codeList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("ywyy", code.getYwyy());
					obj.put("ywmc", code.getYwmc());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "获取业务类型失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	/*
	 * 添加下一步提示信息
	 */
	public String addNwindow() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		try {
			NextWindow nwindow = new NextWindow();
			nwindow.setDmlb(request.getParameter("dmlb"));
			nwindow.setDmz(request.getParameter("dmz"));
			nwindow.setYwyybh(request.getParameter("ywyybh"));
			nwindow.setNextwindowval(request.getParameter("nextwindowval"));
			nwindow.setDeptCode(deptCode);
			nwindow.setDeptHall(deptHall);
			String aString = nextWindowService.saveNWindow(nwindow);
			getRequest().setAttribute("msg", "提示信息添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			getRequest().setAttribute("msg", "提示信息添加失败，<br>在执行过程中发生异常！");
		}
			getRequest().setAttribute("action", "nextwindow/nWindowList.action");
		return "success";
	}
	
	/*
	 * 修改下一步提示信息
	 */
	public String updateNwindow() throws Exception {
		HttpServletRequest request = getRequest();
		try {
			NextWindow nwindow = new NextWindow();
			nwindow.setId(request.getParameter("id"));
			nwindow.setNextwindowval(request.getParameter("nextwindowval"));
			nextWindowService.updateById(nwindow);
			getRequest().setAttribute("msg", "提示信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			getRequest().setAttribute("msg", "提示信息修改失败，<br>在执行过程中发生异常！");
		}
			getRequest().setAttribute("action", "nextwindow/nWindowList.action");
		return "success";
	}
	
	/*
	 * 查询下一步提示信息集合
	 */
	public String nWindowList() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		NextWindow nWindow = new NextWindow();
		nWindow.setDeptCode(deptCode);
		nWindow.setDeptHall(deptHall);
		List<NextWindow> nWindowList = nextWindowService.queryAllNWindow(nWindow);
		for (int i = 0; i < nWindowList.size(); i++) {
			NextWindow nwindowVO = nWindowList.get(i);
			String id = nwindowVO.getId();
			String operate = "<a onclick=updateNWindow('" + id
			+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand'></a>&nbsp;"
			+ "<a onclick=delNWindow('" + id
			+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
			nwindowVO.setOperation(operate);
		}
		request.setAttribute("nWindowList", nWindowList);
		return "list";
	}
	
	/*
	 * 根据ID查询下一步提示信息
	 */
	public String queryById()throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		NextWindow nwindow = new NextWindow();
		nwindow.setId(request.getParameter("id"));
		nwindow.setDeptCode(deptCode);
		nwindow.setDeptHall(deptHall);
		nwindow = nextWindowService.queryById(nwindow);
		String dmlbString="";
		if("0080".equals(nwindow.getDmlb())){
			dmlbString="驾驶证";
		}
		if("0060".equals(nwindow.getDmlb())){
			dmlbString="机动车";
		}
		request.setAttribute("id", nwindow.getId());
		request.setAttribute("dmlb", dmlbString);
		request.setAttribute("dmsm1", nwindow.getDmsm1());
		request.setAttribute("ywmc", nwindow.getYwyybh());
		request.setAttribute("nextwindowval", nwindow.getNextwindowval());
		return "queryById";
	}
	
	/*
	 * 根据ID删除下一步提示信息
	 */
	public String deleteById()throws Exception{
		HttpServletRequest request = getRequest();
		try {
			NextWindow nwindow = new NextWindow();
			nwindow.setId(request.getParameter("id"));
			nextWindowService.deleteById(nwindow);
			getRequest().setAttribute("msg", "提示信息删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			getRequest().setAttribute("msg", "提示信息删除失败，<br>在执行过程中发生异常！");
		}
			getRequest().setAttribute("action", "nextwindow/nWindowList.action");
		return "success";
	}
	
	/*
	 * 查询添加的下一步提示信息是否重复
	 */
	public String checkDMZ() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String nwindowDmlb = this.getRequest().getParameter("nwindowDmlb");
		String nwindowDmz = this.getRequest().getParameter("nwindowDmz");
		String nwindowywyybh = this.getRequest().getParameter("nwindowywyybh");
		NextWindow nwindow1 = new NextWindow();
		nwindow1.setDmlb(nwindowDmlb);
		nwindow1.setDmz(nwindowDmz);
		nwindow1.setYwyybh(nwindowywyybh);
		nwindow1.setDeptCode(deptCode);
		nwindow1.setDeptHall(deptHall);
		NextWindow nwindow2 = nextWindowService.queryForCheck(nwindow1);
		boolean falg = false;
		if (nwindow2 != null) {
			falg = true;
		}
		getResponse().getWriter().print(falg);
		return null;
	}
}
