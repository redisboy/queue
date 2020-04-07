package com.suntendy.queue.yygn.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.yygn.domain.Yyxz;
import com.suntendy.queue.yygn.service.IYyxzService;

public class YyxzAction extends BaseAction {
	
	private IYyxzService yyxzService;

	public void setYyxzService(IYyxzService yyxzService) {
		this.yyxzService = yyxzService;
	}
	

	
	public String addOrUpdateYyxz(){
		HttpServletRequest request = getRequest();
		String content = request.getParameter("code");
		Date date=new Date();
		Yyxz yyxz = new Yyxz();
		yyxz.setContent(content);
		yyxz.setLrrq(date);
		Integer result=1;  //默认是修改失败
		try {
			if(checkContentExist()){
				// 说明已经存在数据 ，调用修改方法
				result = yyxzService.updateYyxz(yyxz);	
			}else{
				// 说明不存在数据 ，调用新增方法
				result = yyxzService.addYyxz(yyxz);
			}
			
			
			if(result==0){
				request.setAttribute("msg", "预约须知信息修改成功！");
			}else {
				request.setAttribute("msg", "修改失败！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("action", "yyxz.action");
		return SUCCESS;
	}
	
	
	
	public String getYyxz(){
		HttpServletRequest request = getRequest();
		try {
			Yyxz yyxz=new Yyxz();
			List<Yyxz> yyxzList = yyxzService.getYyxzList();
			if(null != yyxzList && !yyxzList.isEmpty()){
				yyxz=yyxzList.get(0);
				
			}
			request.setAttribute("yyxz", yyxz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "yyxz";
	}
	
	public boolean checkContentExist(){
		HttpServletResponse response=getResponse();
		try {
 			List<Yyxz> yyxzList = yyxzService.getYyxzList();
			if(null != yyxzList && !yyxzList.isEmpty()){
				// 说明数据库中已存在数据，不能继续添加
				return true;
			}else {
				// 说明数据库中没有数据
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
