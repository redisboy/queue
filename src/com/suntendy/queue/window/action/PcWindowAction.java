package com.suntendy.queue.window.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.service.IAdvertiseService;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.evaluation.domain.ValueRecord;
import com.suntendy.queue.evaluation.service.ISetEvaluationService;
import com.suntendy.queue.publicAd.domain.PublicAd;
import com.suntendy.queue.publicAd.service.IPublicAdService;
import com.suntendy.queue.util.cache.CacheManager;

/*****************************************************************
 * 描述: 用户办理业务PC端信息展示 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘希臣 <br>
 * 创建日期: 2013-09-25 14:53:08 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
******************************************************************/

public class PcWindowAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private IPublicAdService publicAdService;
	private IAdvertiseService advertiseService;
	private ISetEvaluationService setEvaluationService;
	
	public void setPublicAdService(IPublicAdService publicAdService) {
		this.publicAdService = publicAdService;
	}
	
	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	public void setSetEvaluationService(ISetEvaluationService setEvaluationService) {
		this.setEvaluationService = setEvaluationService;
	}
	
	/**
	 * 业务办理窗口PC端窗口显示
	 * @return
	 */
	public String pcwindow() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		//广告信息列表
		List<Advertise> msgList = advertiseService.getAlladvertise("","1",deptCode,deptHall);
		//宣传材料
		String id =null;
		List<PublicAd> publicList = publicAdService.getPublicAd(id, "",null,null);
		HashMap<String,PublicAd> showPublicAd = new HashMap<String, PublicAd>();
		showPublicAd.put("0",null);
		showPublicAd.put("1",null);
		for(PublicAd publicAd:publicList){
			if (publicAd.getOperator().equals("0")&&publicAd.getIsUsing().equals("1")) {
				showPublicAd.put("0", publicAd);
			}else if((!publicAd.getOperator().equals("0"))&&publicAd.getDeptCode().equals(deptCode)&&publicAd.getDeptHall().equals(deptHall)){
				showPublicAd.put("1", publicAd);
			}
		}
		PublicAd publicAd0 = showPublicAd.get("0");
		PublicAd publicAd1 = showPublicAd.get("1");
		if (publicAd0!=null) {
			request.setAttribute("publicAd", publicAd0); //将查询到宣传材料返回页面
		}else if (publicAd0==null&&publicAd1!=null) {
			request.setAttribute("publicAd", publicAd1); //将查询到宣传材料返回页面
		}
		
		
		//读取有效评价按钮
		List<ValueRecord> evalueList = setEvaluationService.getValidPj(deptCode,deptHall);
		String pjtype = cacheManager.getSystemConfig("pjtype");
		String qrpjtype=cacheManager.getSystemConfig("qrpjtype");
		request.setAttribute("qrpjtype", qrpjtype);
		//System.out.println("pjtype=="+pjtype);
			if("1".equals(pjtype)){//判断评价类型
				for(ValueRecord recode:evalueList){
					recode.setEvalueclass(recode.getEvalueclass()+"_1");
				}
			}
		/**
		 * 广告信息处理
		 */
		String allMsg = "";
		for(int i=0;i<msgList.size();i++){
			Advertise advertise = msgList.get(i);
			if("1".equals(advertise.getMsg_state())){
				String strMsg = advertise.getMessage();
				allMsg = strMsg +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ allMsg;
			}
		}
//		for(int i=0;i<publicList.size();i++){
//			PublicAd publicAd = publicList.get(i);
//			request.setAttribute("publicAd", publicAd); //将查询到宣传材料返回页面
//		}
		
		if(allMsg=="") allMsg = "欢迎光临";
		request.setAttribute("allMsg", allMsg); //将查询到广告信息list返回页面
		request.setAttribute("pjtype", pjtype);
		request.setAttribute("evalueList", evalueList);
		return SUCCESS;
	}
}