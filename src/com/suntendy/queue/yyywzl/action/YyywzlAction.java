package com.suntendy.queue.yyywzl.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.yyywzl.domain.Yyywzl;
import com.suntendy.queue.yyywzl.service.IYyywzlService;

public class YyywzlAction extends BaseAction  {
		
	private IYyywzlService yyywzlService;

	public void setYyywzlService(IYyywzlService yyywzlService) {
		this.yyywzlService = yyywzlService;
	}
	
	public String yyywzlManage() throws Exception {
		HttpServletRequest request = getRequest();
		List<Yyywzl> list = yyywzlService.getYyywzlList();
		
		if (null != list && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Yyywzl yyywzl = list.get(i);
				String id = yyywzl.getId();
				String operate = "<a onclick=updateYyywzl('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delYyywzl('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				yyywzl.setOperation(operate);
			}
		}
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	public String fromAddYyywzl() throws Exception {
		return SUCCESS;
	}
	
	
	public String addYyywzl() throws Exception {
	
		HttpServletRequest request = getRequest();
		String zlmc = request.getParameter("zlmc");
		String sm = request.getParameter("sm");
		Date date=new Date();
		Yyywzl yyywzl = new Yyywzl(zlmc,sm,date);
		Integer result = yyywzlService.addYyywzl(yyywzl);
		if(result==0){
			request.setAttribute("msg", "预约业务所需资料信息添加成功！");
		}else {
			request.setAttribute("msg", "添加失败！");
		}
		request.setAttribute("action", "yyywzl.action");
		return SUCCESS;
	}
	
	public String fromUpdateYyywzl() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		List<Yyywzl> yyywzlListByIdList = yyywzlService.getYyywzlListById(id);
		Yyywzl yyywzl = yyywzlListByIdList.get(0);
		request.setAttribute("list", yyywzl);
		return SUCCESS;
	}
	public String updateYyywzl() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String zlmc = request.getParameter("zlmc");
		String sm = request.getParameter("sm");
		Date date=new Date();
		Yyywzl yyywzl = new Yyywzl(zlmc,sm,date);
		yyywzl.setId(id);
		Integer result = yyywzlService.updateYyywzl(yyywzl);
		if(result==0){
			request.setAttribute("msg", "预约业务所需资料信息修改成功！");
		}else {
			request.setAttribute("msg", "修改失败！");
		}
		request.setAttribute("action", "yyywzl.action");
		return SUCCESS;
	}
	
	
	public String delYyywzl() throws Exception {
		
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		Integer result = yyywzlService.delYyywzl(id);
		if(result!=0){
			request.setAttribute("msg", "预约业务所需资料信息删除成功！");
		}else {
			request.setAttribute("msg", "删除失败！");
		}
		
		request.setAttribute("action", "yyywzl.action");
		return SUCCESS;
	}
	
}	
