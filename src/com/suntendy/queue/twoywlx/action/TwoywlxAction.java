package com.suntendy.queue.twoywlx.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.oneywlx.domain.OneYwlx;
import com.suntendy.queue.oneywlx.service.IOneywlxService;
import com.suntendy.queue.twoywlx.domain.Twoywlx;
import com.suntendy.queue.twoywlx.service.ITwoywlxService;
import com.suntendy.queue.yydt.domain.Yydt;
import com.suntendy.queue.yydt.service.IYydtService;
import com.suntendy.queue.yyywzl.domain.Yyywzl;
import com.suntendy.queue.yyywzl.service.IYyywzlService;

public class TwoywlxAction extends BaseAction {
	
	private ITwoywlxService twoywlxService;
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

	public void setTwoywlxService(ITwoywlxService twoywlxService) {
		this.twoywlxService = twoywlxService;
	}
	
	public String twoywlxManage() throws Exception {
		HttpServletRequest request = getRequest();
		List<Twoywlx> list = twoywlxService.getTwoywlxList();
		
		if (null != list && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Twoywlx twoywlxVo = list.get(i);
				String oneywlxId = twoywlxVo.getOneywlxId();
				
				//通过一级ID获得一级Name
				List<OneYwlx> oneywlxList = oneywlxService.getFormTwo(oneywlxId);
				OneYwlx oneYwlxVo = oneywlxList.get(0);
				String oneYwlxName = oneYwlxVo.getName();
				twoywlxVo.setOneywlxName(oneYwlxName);
				
				String id = twoywlxVo.getId();
				String operate = "<a onclick=updateTwoywlx('" + id
					+ "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;" + "<a onclick=delTwoywlx('" + id
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				twoywlxVo.setOperation(operate);
			}
		}
		request.setAttribute("list", list);
		
		return SUCCESS;
	}
	
	
	public String fromAddTwoywlx() throws Exception {
		HttpServletRequest request = getRequest();
		
		List<OneYwlx> oneywlxList = oneywlxService.getOneywlxListByEjywzt();
		request.setAttribute("list", oneywlxList);
		
		List<Yyywzl> yyywzlList = yyywzlService.getYyywzlList();
		request.setAttribute("yyywzlList", yyywzlList);
		
		List<Yydt> yydtList = yydtService.getYydtList();
		request.setAttribute("yydtList", yydtList);
		return SUCCESS;
	}
	
	public String addTwoywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String oneywlxId = request.getParameter("oneywlxName");
		String name = request.getParameter("name");
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
		if(dtIdTest!=null&&dtIdTest!=""){
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
		Twoywlx twoywlx = new Twoywlx(oneywlxId, name, sxjbzl, dtyysl, date);
		Integer result = twoywlxService.addTwoywlx(twoywlx);
		if(result==0){
			request.setAttribute("msg", "预约二级业务类型添加成功！");
		}else {
			request.setAttribute("msg", "添加失败！");
		}
		request.setAttribute("action", "twoywlx.action");
		
		
		return SUCCESS;
	}
	
	public String fromUpdateTwoywlx() throws Exception {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		List<Twoywlx> twoywlxListById = twoywlxService.getTwoywlxListById(id);
		Twoywlx twoywlx = twoywlxListById.get(0);
		request.setAttribute("twoywlx", twoywlx);

		
		List<OneYwlx> oneywlxList = oneywlxService.getOneywlxListByEjywzt();
		request.setAttribute("list", oneywlxList);
		
		List<Yyywzl> yyywzlList = yyywzlService.getYyywzlList();
		request.setAttribute("yyywzlList", yyywzlList);
		
		List<Yydt> yydtList = yydtService.getYydtList();
		request.setAttribute("yydtList", yydtList);
		
		return SUCCESS;
	}
	
		public String updateTwoywlx() throws Exception {
		
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String oneywlxId = request.getParameter("oneywlxName");
		String name = request.getParameter("name");
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
		if(dtIdTest!=null&&dtIdTest!=""){
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
		Twoywlx twoywlx = new Twoywlx(oneywlxId, name, sxjbzl, dtyysl, date);
		twoywlx.setId(id);
		Integer result = twoywlxService.updateTwoywlx(twoywlx);
		if(result==0){
			request.setAttribute("msg", "预约二级业务类型修改成功！");
		}else {
			request.setAttribute("msg", "修改失败！");
		}
		request.setAttribute("action", "twoywlx.action");
		
		return SUCCESS;
	}
		
		
		
		public String delTwoywlx() throws Exception {
			HttpServletRequest request = getRequest();
			String id = request.getParameter("id");
			Integer result = twoywlxService.delTwoywlx(id);
					if(result!=0){
						request.setAttribute("msg", "预约二级业务类型删除成功！");
					}else {
						request.setAttribute("msg", "删除失败！");
					}
					request.setAttribute("action", "twoywlx.action");
			
			return SUCCESS;
			
		}
	
	
	
	
}
