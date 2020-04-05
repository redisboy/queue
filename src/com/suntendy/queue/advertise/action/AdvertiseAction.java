package com.suntendy.queue.advertise.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.service.IAdvertiseService;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.util.cache.CacheManager;

public class AdvertiseAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private IAdvertiseService advertiseService;
	/*
	 * 广告设置
	 */
	public String ggsz(){
		HttpServletRequest request = getRequest();
		String id=null;
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Advertise> list1 = advertiseService.getAlladvertise(id, "",deptCode, deptHall);
		if (!list1.isEmpty()) {
			for (int i = 0; i < list1.size(); i++) {
				Advertise advertiseVo = list1.get(i);
				String ID =advertiseVo.getId();
				String state=advertiseVo.getMsg_state();
				if(state.equals("1")){
					advertiseVo.setMsg_state("有效");
				}else{
					advertiseVo.setMsg_state("无效");
				}
				String operate = "<a onclick=updateAdvertise('"
						+ ID
						+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
						+ "&nbsp;"
						+ "<a onclick=delAdvertise('"
						+ ID
						+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>"
						+ "&nbsp;";
				advertiseVo.setOperation(operate);
			}
		}
		request.setAttribute("list1", list1);
		return SUCCESS;
	}
	/*
	 * 添加广告
	 */
	public String into_addAdvertise(){
		return SUCCESS;
	}
	/*
	 * 添加广告
	 */
	public String addAdvertise(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		Advertise advertiseVo= new Advertise();
		advertiseVo.setTvUse("0");
		advertiseVo.setMsg_state(request.getParameter("state"));
		advertiseVo.setMessage(request.getParameter("message").trim());
		advertiseVo.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		advertiseVo.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			advertiseService.addAdvertise(advertiseVo);
			request.setAttribute("msg", "广告添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "广告添加失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "ggsz.action");		
		return SUCCESS;
	}
	/*
	 * 修改广告 初始化页面
	 */
	public String updateAdvertise(){
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Advertise> Advertiselist = advertiseService.getAlladvertise(id, "",deptCode, deptHall);
		if(!Advertiselist.isEmpty()){
			Advertise advertiseVo = Advertiselist.get(0);
			String message = advertiseVo.getMessage();
			String state = advertiseVo.getMsg_state();
			String ID =advertiseVo.getId();
			request.setAttribute("message", message);
			request.setAttribute("state", state);
			request.setAttribute("id", ID);
		}
		return SUCCESS;
	}
	
	/*
	 * 修改广告
	 */
	public String updateByCode(){
		HttpServletRequest request = getRequest();
		String id=request.getParameter("id");
		String message = request.getParameter("message");
		String msg_state = request.getParameter("state");
		
		try {
			advertiseService.updateByCode(id,message.trim(),msg_state);
			request.setAttribute("msg", "广告信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "广告信息修改失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "ggsz.action");		
		return SUCCESS;
	}
	/*
	 * 删除广告
	 */
	public String delAdvertise(){
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		int updateflag = advertiseService.delAdvertise(id);
		if(updateflag==1){
			//队列信息修改成功
			request.setAttribute("msg", "广告删除成功！");
		}else{ 
			request.setAttribute("msg", "广告删除失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "ggsz.action");		
		return SUCCESS;
	}
	/*
	 * 综合屏查询
	 */
	public String getComprehensiveScreen() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Advertise advertise = new Advertise();
		List<Advertise> list = advertiseService.getComprehensiveScreen(advertise,deptCode, deptHall);
		if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Advertise advertiseVo = list.get(i);
				String ID =advertiseVo.getId();
				String state=advertiseVo.getMsg_state();
				if(state.equals("1")){
					advertiseVo.setMsg_state("有效");
				}else{
					advertiseVo.setMsg_state("无效");
				}
				String operate = "<a onclick=updateAdvertise('"
						+ ID
						+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
						+ "&nbsp;"
						+ "<a onclick=delAdvertise('"
						+ ID
						+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>"
						+ "&nbsp;";
				advertiseVo.setOperation(operate);
			}
			
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	/*
	 * 大厅综合屏显示
	 */
	public String getAllcomprehensiveScreen() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Advertise advertise = new Advertise();
		advertise.setMsg_state("1");
		List<Advertise> list = advertiseService.getComprehensiveScreen(advertise,deptCode, deptHall);
		// 将打印参数信息以JSON格式返回
		JSONObject datas = new JSONObject();
		if (null != list && !list.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (Advertise adv : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("message", adv.getMessage());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "获取大厅信息失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	/*
	 * 进入添加综合屏
	 */
	public String to_addComprehensiveScreen(){
		return SUCCESS;
	}
	/*
	 * 添加综合屏
	 */
	public String addComprehensiveScreen(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		Advertise advertiseVo= new Advertise();
		advertiseVo.setTvUse("0");
		advertiseVo.setMsg_state(request.getParameter("state"));
		advertiseVo.setMessage(request.getParameter("code").trim());
		advertiseVo.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		advertiseVo.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			advertiseService.addComprehensiveScreen(advertiseVo);
			request.setAttribute("msg", "综合屏信息添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "综合屏信息添加失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "getComprehensiveScreen.action");		
		return SUCCESS;
	}
	/*
	 * 进入修改综合屏
	 */
	public String to_updateComprehensiveScreenById() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Advertise advertise = new Advertise();
		List<Advertise> list = advertiseService.getComprehensiveScreen(advertise,deptCode, deptHall);
		if(!list.isEmpty()){
			Advertise advertiseVo = list.get(0);
			String message = advertiseVo.getMessage();
			String state = advertiseVo.getMsg_state();
			String ID =advertiseVo.getId();
			request.setAttribute("message", message);
			request.setAttribute("state", state);
			request.setAttribute("id", ID);
		}
		return SUCCESS;
	}
	/*
	 * 修改综合屏
	 */
	public String updateComprehensiveScreenById(){
		HttpServletRequest request = getRequest();
		String id=request.getParameter("id");
		String message = request.getParameter("code");
		String msg_state = request.getParameter("state");
		
		try {
			advertiseService.updateComprehensiveScreenById(id,message.trim(),msg_state);
			request.setAttribute("msg", "综合屏信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "综合屏信息修改失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "getComprehensiveScreen.action");		
		return SUCCESS;
	}

	/*
	 * 删除综合屏
	 */
	public String delComprehensiveScreen() throws Exception{
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		int updateflag = advertiseService.delComprehensiveScreen(id);
		if(updateflag==1){
			//综合屏信息修改成功
			request.setAttribute("msg", "综合屏信息删除成功！");
		}else{ 
			request.setAttribute("msg", "综合屏信息删除失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "getComprehensiveScreen.action");		
		return SUCCESS;
	}
	
	public IAdvertiseService getAdvertiseService() {
		return advertiseService;
	}
	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
}