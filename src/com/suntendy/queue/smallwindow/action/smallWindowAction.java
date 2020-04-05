package com.suntendy.queue.smallwindow.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.smallwindow.domain.SmallWindow;
import com.suntendy.queue.smallwindow.service.ISmallWindowService;

public class smallWindowAction extends BaseAction {

	private ISmallWindowService smallWindowService;
	
	public ISmallWindowService getSmallWindowService() {
		return smallWindowService;
	}

	public void setSmallWindowService(ISmallWindowService smallWindowService) {
		this.smallWindowService = smallWindowService;
	}

	/*
	 * 跳转到添加下一步提示信息窗口
	 */
	public String showBtn() throws Exception{
		
		HttpServletRequest request = getRequest();
		SmallWindow smallWindow=new SmallWindow();
		List<SmallWindow> smallList = smallWindowService.showBtn(smallWindow);
		
		request.setAttribute("smallList", smallList);
//		JSONObject datas = new JSONObject();
//		if (null != smallList && !smallList.isEmpty()) {
//			datas.put("isSuccess", true);
//			
//			JSONArray typeGroup = new JSONArray();
//			for (SmallWindow samll : smallList) {
//				JSONObject obj = new JSONObject();
//				try {
//					obj.put("dm", samll.getId());
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				typeGroup.put(obj);
//			}
//			datas.put("datas", typeGroup);
//		} else {
//			datas.put("isSuccess", false);
//			datas.put("error", "获取业务类型失败");
//		}
//		this.getResponse("text/html").getWriter().println(datas.toString());
		return SUCCESS;
	}
	
	/*
	 * 修改
	 */
	public String updateBtn(){
		HttpServletRequest request = getRequest();
		SmallWindow smallWindow=new SmallWindow();
		smallWindow.setSxh(request.getParameterValues("smallid"));
		smallWindow.setMc(request.getParameterValues("btnName"));
		smallWindow.setCode(request.getParameterValues("stroke"));
		smallWindow.setEventAll(request.getParameterValues("event"));
		try {
			smallWindowService.updateBtnById(smallWindow);
			getRequest().setAttribute("msg", "提示信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			getRequest().setAttribute("msg", "提示信息修改失败，<br>在执行过程中发生异常！");
		}
			getRequest().setAttribute("action", "smallwindow/showBtn.action");
		return "success";
	}
	
	/**
	 * 获取小窗口要显示的按钮
	 * @return 暂停原因
	 * @throws Exception
	 */
	public String shwoBtn_status() throws Exception {

		SmallWindow smallWindow=new SmallWindow();
		List<SmallWindow> smallList = smallWindowService.showBtn(smallWindow);
		
		
		// 将打印参数信息以JSON格式返回
		JSONObject datas = new JSONObject();
		if (null != smallList && !smallList.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (SmallWindow smallWin : smallList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("names", smallWin.getBtnName());
					obj.put("status", smallWin.getStatus());
					obj.put("event", smallWin.getEvent());
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

}
