package com.suntendy.queue.tuiban.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.tuiban.domain.TuiBan;
import com.suntendy.queue.tuiban.service.ITuiBanService;

public class TuibanAction extends BaseAction {
	
	private ITuiBanService tuibanService;

	public ITuiBanService getTuibanService() {
		return tuibanService;
	}

	public void setTuibanService(ITuiBanService tuibanService) {
		this.tuibanService = tuibanService;
	}
	
	
	
	public String insertTuiban(){
		HttpServletRequest request = getRequest();
		String clientIp = request.getParameter("clientIp");
		String idnumber = request.getParameter("idnumber");
		String yuanyin = request.getParameter("reason");
		Employee user = (Employee) this.getHttpSession().getAttribute("user");
		TuiBan tuiban = new TuiBan();
		tuiban.setIdnumber(idnumber);
		tuiban.setJiluip(clientIp);
		tuiban.setJiluren(user.getName());
		tuiban.setYuanyin(yuanyin);
		tuiban.setZt("1");
		try {
			tuibanService.insertTuiBan(tuiban);
			request.setAttribute("msg", "退办信息添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "退办信息添加失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "queryTuiban.action");
		return "success";
		
	}
	
	public String queryTuiban() throws Exception{
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		TuiBan tuiban = new TuiBan();
		tuiban.setIdnumber(idnumber);
		List<TuiBan> list = tuibanService.queryTuiBan(tuiban);
		for (int i = 0; i < list.size(); i++) {
			TuiBan tb = list.get(i);
			String id = tb.getIdnumber();
			String operate ="<input name='button' type='button' class='button' value='查询' onClick=\"chakan('"+id+"')\"/>&nbsp;"
			+ "&nbsp;"+"<input name='button' type='button' class='button' value='打印' onClick=\"print('"+id+"')\"/>&nbsp;" ;
			tb.setOperation(operate);
			
		}
		request.setAttribute("list", list);
		return "list";
	}
	
	public String toAddTuiban(){
		return "addTuiban";
	}
	
	public String toupdateTuiban(){
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		
		return "addTuiban";
	}
	
	public String detailTuiban() throws Exception{
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		System.out.println(idnumber);
		TuiBan tuiban = new TuiBan();
		tuiban.setIdnumber(idnumber);
		List<TuiBan> list = tuibanService.queryTuiBan(tuiban);
		if (list.size()>0) {
			request.setAttribute("tuiban", list.get(0));
		}
		return "detail";
	}
	
	public String printTuiban() throws Exception{
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		System.out.println(idnumber);
		TuiBan tuiban = new TuiBan();
		tuiban.setIdnumber(idnumber);
		List<TuiBan> list = tuibanService.queryTuiBan(tuiban);
		if (list.size()>0) {
			request.setAttribute("tuiban", list.get(0));
		}
		return "print";
	}
}
