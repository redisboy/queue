package com.suntendy.queue.oneywlx.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.oneywlx.service.IOneywlxService;
import com.suntendy.queue.yydt.domain.Yydt;
import com.suntendy.queue.yydt.service.IYydtService;
import com.suntendy.queue.yyywzl.domain.Yyywzl;
import com.suntendy.queue.yyywzl.service.IYyywzlService;

public class OneYwlxAction extends BaseAction {
	private IOneywlxService oneywlxService;
	
	private IYyywzlService yyywzlService;
	
	private IYydtService yydtService;

	public void setYydtService(IYydtService yydtService) {
		this.yydtService = yydtService;
	}

	public void setYyywzlService(IYyywzlService yyywzlService) {
		this.yyywzlService = yyywzlService;
	}

	public void setOneywlxService(IOneywlxService oneywlxService) {
		this.oneywlxService = oneywlxService;
	}
	
	public String oneYwlxManage() throws Exception {
		HttpServletRequest request = getRequest();
		List<OneYwlx> list = oneywlxService.getOneYwlxList();
		
		if (null != list && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				OneYwlx oneYwlxVo = list.get(i);
				String id = oneYwlxVo.getId();
				String operate = "<a onclick=updateOneywlx('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delOneywlx('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				oneYwlxVo.setOperation(operate);
			}
		}
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	
	
	public String fromAddOneywlx() throws Exception {
		HttpServletRequest request = getRequest();
		
		List<Yyywzl> yyywzlList = yyywzlService.getYyywzlList();
		request.setAttribute("yyywzlList", yyywzlList);
		
		List<Yydt> yydtList = yydtService.getYydtList();
		request.setAttribute("yydtList", yydtList);
		
		return SUCCESS;
	}
	
	
	public String addOneywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String name = request.getParameter("name");
		String xtlb = request.getParameter("xtlb");
		String ejywzt = request.getParameter("ejywzt");
		// 还未对其进行格式处理的所需基本资料信息
		String sxjbzlTest = request.getParameter("sxjbzlTest");
		//大厅ID
		String dtIdTest = request.getParameter("dtId");
		//预约数量
		String yyslTest = request.getParameter("yysl");
		
		String sxjbzl=null;
		if(sxjbzlTest!=null&&sxjbzlTest!=""){
			//所需基本资料信息数据格式处理
			sxjbzl=sxjbzlTest.replace(",", "#").substring(0, sxjbzlTest.length()-1);
		}
		
		 /*boss 只是拼接字符串使用
		   对大厅id和预约数量进行格式处理
		 */
		String boss="";
		//最终需要的值
		String dtyysl="";
		if(dtIdTest!=null&&dtIdTest!=""&&yyslTest!=null&&yyslTest!=""){
			String [] dtIdTestArr = dtIdTest.split(",");
			String [] yyslTestArr = yyslTest.split(",");
			for (int i = 0; i < dtIdTestArr.length; i++) {
				boss=dtIdTestArr[i]+":"+yyslTestArr[i];
				if(i<dtIdTestArr.length-1){
					boss+="#";
				}
				dtyysl+=boss;
			}	
		}

		
		
		Date date = new Date();
		if(ejywzt.equals("0")){
			//说明包含二级业务
			sxjbzl="";
			dtyysl="";
		}
		
		OneYwlx oneYwlx = new OneYwlx(name, xtlb, ejywzt, sxjbzl,dtyysl, date);
		Integer result = oneywlxService.addOneywlx(oneYwlx);
		if(result==0){
			request.setAttribute("msg", "预约一级业务类型添加成功！");
		}else {
			request.setAttribute("msg", "添加失败！");
		}
		request.setAttribute("action", "oneywlx.action");
		
		
		return SUCCESS;
	}
	

	public String fromUpdateOneywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		List<OneYwlx> oneywlxListById = oneywlxService.getOneywlxListById(id);
		OneYwlx oneYwlx = oneywlxListById.get(0);
		request.setAttribute("list", oneYwlx);
		
		List<Yyywzl> yyywzlList = yyywzlService.getYyywzlList();
		request.setAttribute("yyywzlList", yyywzlList);
		
		List<Yydt> yydtList = yydtService.getYydtList();
		request.setAttribute("yydtList", yydtList);
		
		return SUCCESS;
	}
	
	
	
	public String updateOneywlx() throws Exception {
		
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String xtlb = request.getParameter("xtlb");
		String ejywzt = request.getParameter("ejywzt");
		// 还未对其进行格式处理的所需基本资料信息
		String sxjbzlTest = request.getParameter("sxjbzlTest");
		//大厅ID
		String dtIdTest = request.getParameter("dtId");
		//预约数量
		String yyslTest = request.getParameter("yysl");
		
		String sxjbzl=null;
		if(sxjbzlTest!=null&&sxjbzlTest!=""){
			//所需基本资料信息数据格式处理
			sxjbzl=sxjbzlTest.replace(",", "#").substring(0, sxjbzlTest.length()-1);
		}
		
		 /*boss 只是拼接字符串使用
		   对大厅id和预约数量进行格式处理
		 */
		String boss="";
		//最终需要的值
		String dtyysl="";
		if(dtIdTest!=null&&dtIdTest!=""&&yyslTest!=null&&yyslTest!=""){
			String [] dtIdTestArr = dtIdTest.split(",");
			String [] yyslTestArr = yyslTest.split(",");
			for (int i = 0; i < dtIdTestArr.length; i++) {
				boss=dtIdTestArr[i]+":"+yyslTestArr[i];
				if(i<dtIdTestArr.length-1){
					boss+="#";
				}
				dtyysl+=boss;
			}	
		}
		Date date = new Date();
		
		if(ejywzt.equals("0")){
			//说明包含二级业务
			sxjbzl="";
			dtyysl="";
		}
		OneYwlx oneYwlx = new OneYwlx(name, xtlb, ejywzt, sxjbzl,dtyysl, date);
		oneYwlx.setId(id);
		Integer result = oneywlxService.updateOneywlx(oneYwlx);
		if(result==0){
			request.setAttribute("msg", "预约一级业务类型修改成功！");
		}else {
			request.setAttribute("msg", "修改失败！");
		}
		request.setAttribute("action", "oneywlx.action");
		
		return SUCCESS;
	}
	
	
	public String delOneywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		Integer result = oneywlxService.delOneywlx(id);
				if(result!=0){
					request.setAttribute("msg", "预约一级业务类型删除成功！");
				}else {
					request.setAttribute("msg", "删除失败！");
				}
				request.setAttribute("action", "oneywlx.action");
		
		return SUCCESS;
	}
	
	
	public void checkTwo() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		int result=-1;
		String id = request.getParameter("id");
		List<OneYwlx> oneywlxListById = oneywlxService.getOneywlxListById(id);
		if(oneywlxListById.get(0).getEjywzt().equals("0")){
			result=0;
		}else{
			result=1;
		}
		response.getWriter().print(result);
	}
	
	
	public String delAllFormOneTwoywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		Integer result1 = oneywlxService.delAllFormOneTwoywlx(id);
		Integer result2 = oneywlxService.delOneywlx(id);
		if(result1!=0&&result2!=0){
			request.setAttribute("msg", "预约一级业务类型删除成功！");
		}else {
			request.setAttribute("msg", "删除失败！");
		}
		request.setAttribute("action", "oneywlx.action");
		
		return SUCCESS;
	}
	
	
}
