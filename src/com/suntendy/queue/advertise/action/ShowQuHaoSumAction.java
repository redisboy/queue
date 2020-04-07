package com.suntendy.queue.advertise.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.suntendy.queue.advertise.domain.QuHaoMsg;
import com.suntendy.queue.advertise.service.IShowQuHaoSumService;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.cache.CacheManager;

public class ShowQuHaoSumAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private INumberService numberService;
	private IShowQuHaoSumService showQuHaoSumService;
	
	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}
	
	public IShowQuHaoSumService getShowQuHaoSumService() {
		return showQuHaoSumService;
	}

	public void setShowQuHaoSumService(IShowQuHaoSumService showQuHaoSumService) {
		this.showQuHaoSumService = showQuHaoSumService;
	}

	/**
	 * 取号数量显示
	 * @throws Exception 
	 */
	public String shwoQhCount() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Number countnum = new Number();
		countnum.setDeptCode(deptCode);
		countnum.setDeptHall(deptHall);
		List<Number> numbersCountList = numberService.showWaitRs(countnum);//查询等待人数
		request.setAttribute("list", numbersCountList);
		return null;
	}
	/*
	 * 进入取号数量显示
	 */
	public String into_addSHowQuHaoContent() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		QuHaoMsg qhVo= new QuHaoMsg();
		qhVo.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		qhVo.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		List<QuHaoMsg> list = showQuHaoSumService.getAllQuHaoMsg(qhVo);
		String isHaveValue="0";
		if(list.size()>0){
			QuHaoMsg qhmsg = list.get(0);
			request.setAttribute("id", qhmsg.getId());
			request.setAttribute("message", qhmsg.getMessage());
			request.setAttribute("gdMsg", qhmsg.getGdMsg());
			request.setAttribute("state", qhmsg.getMsg_state());
			isHaveValue="1";
		}
		request.setAttribute("isHaveValue", isHaveValue);
		return SUCCESS;
	}
	/*
	 * 添加内容
	 */
	public String addSHowQuHaoContent(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String isHaveValue = request.getParameter("isHaveValue");
		QuHaoMsg qhVo= new QuHaoMsg();
		qhVo.setId(request.getParameter("id"));
		qhVo.setMsg_state(request.getParameter("state"));
		qhVo.setGdMsg(request.getParameter("gdMsg"));
		qhVo.setMessage(request.getParameter("code"));
		qhVo.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		qhVo.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			if("0".equals(isHaveValue)){
				showQuHaoSumService.addSHowQuHaoContent(qhVo);
			}else{
				showQuHaoSumService.updageSHowQuHaoContent(qhVo);
			}
			
			request.setAttribute("msg", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "保存失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "into_addSHowQuHaoContent.action");		
		return SUCCESS;
	}
	/**
	 * 获取取号页面内容
	 * @return List
	 */
	public String getQuHaoContent() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		QuHaoMsg qhVo= new QuHaoMsg();
		qhVo.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		qhVo.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		List<QuHaoMsg> list = showQuHaoSumService.getAllQuHaoMsg(qhVo);
		JSONObject obj = new JSONObject();
		if(list.size()>0){
			QuHaoMsg qhmsg = list.get(0);
			obj.put("message", qhmsg.getMessage());
			obj.put("gdMsg", qhmsg.getGdMsg());
			
		}
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	
	

}