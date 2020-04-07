package com.suntendy.queue.yydt.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.yydt.domain.Yydt;
import com.suntendy.queue.yydt.service.IYydtService;
import com.suntendy.queue.yyywzl.domain.Yyywzl;

public class YydtAction extends BaseAction  {

	private IYydtService yydtService;

	public void setYydtService(IYydtService yydtService) {
		this.yydtService = yydtService;
	}
	
	
	
	public String yydtManage() throws Exception {
		HttpServletRequest request = getRequest();
		List<Yydt> list = yydtService.getYydtList();
		
		if (null != list && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Yydt yydt = list.get(i);
				String id = yydt.getId();
				String operate = "<a onclick=updateYydt('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delYydt('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				yydt.setOperation(operate);
			}
		}
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	public String fromAddYydt() throws Exception {
		return SUCCESS;
	}
	
	public String addYydt() throws Exception {
		
		HttpServletRequest request = getRequest();
		String name = request.getParameter("name");
		String blddz = request.getParameter("blddz");
		String yyts = request.getParameter("yyts");
		String yysd = request.getParameter("yysds");
		
		//数据格式处理
		String yysds=yysd.replace(",", "#").substring(0, yysd.length());
		System.out.println(yysd);
		
		Yydt yydt = new Yydt(name, blddz, yyts, yysds);
		Integer result = yydtService.addYydt(yydt);
		if(result==0){
			request.setAttribute("msg", "预约大厅信息添加成功！");
		}else {
			request.setAttribute("msg", "添加失败！");
		}
		request.setAttribute("action", "yydt.action");
		return SUCCESS;
	}
	
	
	public String fromUpdateYydt() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		List<Yydt> YydtListByIdList = yydtService.getYydtListById(id);
		Yydt yydt = YydtListByIdList.get(0);
		request.setAttribute("list", yydt);
		return SUCCESS;
	}
	
	
	public String updateYydt() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String blddz = request.getParameter("blddz");
		String yyts = request.getParameter("yyts");
		String yysd = request.getParameter("yysds");
		
		//数据格式处理
		String yysds=yysd.replace(",", "#").substring(0, yysd.length());
		System.out.println(yysd);
		
		Yydt yydt = new Yydt(name, blddz, yyts, yysds);
		yydt.setId(id);
		Integer result = yydtService.updateYydt(yydt);
		if(result==0){
			request.setAttribute("msg", "预约大厅信息修改成功！");
		}else {
			request.setAttribute("msg", "修改失败！");
		}
		request.setAttribute("action", "yydt.action");
		return SUCCESS;
	}
	
	
	public String delYydt() throws Exception {
		
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		Integer result = yydtService.delYydt(id);
		if(result!=0){
			request.setAttribute("msg", "预约大厅信息删除成功！");
		}else {
			request.setAttribute("msg", "删除失败！");
		}
		
		request.setAttribute("action", "yydt.action");
		return SUCCESS;
	}
	
	
}
