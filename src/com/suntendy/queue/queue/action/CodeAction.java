package com.suntendy.queue.queue.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.lzxx.domain.Lzxx;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.impl.CodeServiceImpl;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.util.scriptsession.event.passLzxx;
import com.suntendy.queue.window.domain.Screen;

public class CodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ICodeService codeService;
	
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	
	/**
	 * 查询差评原因
	 * @return List
	 */
	public String getAllGredentialsEvaluaReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list=codeService.getCodeByDmlbAndDmz("0003","","1",deptCode,deptHall);//0003为代码类别，中间为dmz，1为可用不可用
		String dmsm1="";
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Code code = list.get(i);
				String id =code.getDm();
				String dmlb=code.getDmlb();
				dmsm1=code.getMc();//,'"+dmlb+"' ,'"+dmsm1+"'
				String operate = "<a onclick=updateReason('"
						+ id + "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"
				+ "&nbsp;"
				+ "<a onclick=delReason('"+ id + "','"+ dmlb + "','"+ dmsm1 + "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				code.setOperation(operate);
			}
		}
		request.setAttribute("mc", dmsm1);
		request.setAttribute("maxDmz", list.size()+1);
		request.setAttribute("list", list);
		return "getAll";
	}
	/**
	 * 添加窗口差评原因
	 * @return List
	 */
	public String addGredentialsEvaluaReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list=codeService.getCodeByDmlbAndDmz("0003","","",deptCode,deptHall);
		String dmz = request.getParameter("maxid");
		String dmsm1 = request.getParameter("content");
		Code code = new Code();
		code.setDmlb("0003");
		code.setDmz(String.valueOf(list.size()+1));
		code.setDmsm1(dmsm1);
		code.setSxh(dmz);
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		try {
			codeService.addGredentialsEvaluaReason(code);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "getAllGredentialsEvaluaReason.action");
		return SUCCESS;
	}
	/**
	 * 进入差评原因修改页面
	 * @return
	 * @throws Exception
	 */
	public String toGredentialsEvaluaReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String dmz = request.getParameter("id");
		Code code = new Code();
		code.setDmlb("0003");
		code.setDmz(dmz);
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		request.setAttribute("code", codeService.toGredentialsEvaluaReason(code));
		return "toEditReason";
	}
	
	/**
	 * 修改窗口差评原因
	 * @return
	 * @throws Exception
	 */
	public String editGredentialsEvaluaReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String dmz = request.getParameter("id");
		String dmlb = request.getParameter("dmlb");
		String mc= request.getParameter("name");
		Code code = new Code();
		code.setDmlb(dmlb);
		code.setDmz(dmz);
		code.setDmsm1(mc);
		code.setZt("1");
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		try {
			codeService.editGredentialsEvaluaReason(code);
			request.setAttribute("msg", "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "getAllGredentialsEvaluaReason.action");
		return SUCCESS;
	}
	/**
	 * 删除窗口差评原因
	 * @return
	 * @throws Exception
	 */
	public String delGredentialsEvaluaReason() throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String dmz = request.getParameter("id");
		String dmlb = request.getParameter("lx");
		String mc= request.getParameter("mc");
		Code code = new Code();
		code.setDmlb(dmlb);
		code.setDmz(dmz);
		code.setDmsm1(mc);
		code.setZt("0");
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		try {
			codeService.editGredentialsEvaluaReason(code);
			request.setAttribute("msg", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "getAllGredentialsEvaluaReason.action");
		return SUCCESS;
	}
	/**
	 * 跳转添加大厅
	 * @return
	 */
	public String toAddDepthall(){
		return "toAddDepthall";
	}
	
	/**
	 * 添加大厅
	 * @throws Exception 
	 */
	
	public String addDepthall() throws Exception{
		HttpServletRequest request = this.getRequest();
//		CacheManager cacheManager = CacheManager.getInstance();
		Code code = new Code();
//		String deptCode = cacheManager.getOfDeptCache("deptCode");
//		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String dmz = request.getParameter("dmz");
		String dmsm1 = request.getParameter("dmsm1");
		code.setDmz(dmz);
		code.setDmsm1(dmsm1);
		code.setDmlb("0002");
		try {
			codeService.addDepthall(code);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "depthallList.action");
		
		
		
		return "addDepthall";
	}
	/**
	 * 大厅列表
	 * @return
	 */
	public String depthallList(){
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list = codeService.getAllGredentials("0002",deptCode,deptHall);
		request.setAttribute("list", list);
		return "depthallList";
	}
	/**
	 * 检查大厅编号
	 * @throws Exception 
	 */
	public String checkDepthall() throws Exception{
		HttpServletRequest request = this.getRequest();
		boolean falg = false;
		String dmz = request.getParameter("dmz");
		List<Code> list = codeService.getCodeByDmlbAndDmz("0002", dmz, null, null, null);
		if (list.size()>0) {
			falg = true;
		}
		getResponse().getWriter().print(falg);
		return null;
	}
	
	
	/**
	 * ajax查询高拍仪资料类型
	 * @return
	 */
	public String getAllZllx_Ajax()throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list = codeService.getAllGredentials("0009",deptCode,deptHall);
		JSONObject datas = new JSONObject();
		if (null != list && !list.isEmpty()) {
			//datas.put("isSuccess", true);
			
			for (Code code : list) {
				JSONObject obj = new JSONObject();
				//判断是否存在上一级
				obj.put("dm", code.getDm());
				obj.put("mc", code.getMc());
				datas.put(code.getDm(), obj);
			}
		} else {
			//datas.put("isSuccess", false);
			datas.put("error", "没有业务类型");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	//验证双屏页面状态
	public String spyzxx() throws Exception {
		System.out.println("codeAction");
		CodeServiceImpl.YZFLAG = "0";
		String flag = "";
		flag = codeService.yzspxx("1");
		HttpServletRequest request = getRequest();
		JSONObject obj = new JSONObject();
			obj.put("flag", flag);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return flag;
	}
	
	/**
	 * 查询高拍仪资料类型
	 * @return
	 */
	public String getAllZllx()throws Exception {
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list = codeService.getAllGredentials("0009",deptCode,deptHall);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Code code = list.get(i);
				String id =code.getDm();
				String operate = "<a onclick=updateZllx('"
						+ id + "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"
				+ "&nbsp;"
				+ "<a onclick=delZllx('"+ id + "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				code.setOperation(operate);
			}
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	/**
	 * 进入资料类型添加
	 * @return
	 */
	public String to_addZllx() throws Exception {
		/*
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list = codeService.getAllGredentials("0009",deptCode,deptHall);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Code code = list.get(i);
				String id =code.getDm();
				String dmlb=code.getDmlb();
				String operate = "<a onclick=updateReason('"
						+ id + "')><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"
				+ "&nbsp;"
				+ "<a onclick=delReason('"+ id + "','"+ dmlb + "')><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>";
				code.setOperation(operate);
			}
		}
		request.setAttribute("list", list);*/
		return SUCCESS;
	}
	
	/**
	 * 进入资料类型修改
	 * @return
	 */
	public String to_updateZllx(){
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> list = codeService.getAllGredentials("0009",deptCode,deptHall);
		if (!list.isEmpty()) {
			Code code = list.get(0);
			request.setAttribute("code", code);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 保存资料类型信息
	 * @return
	 */
	public String saveZllx() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String zllxbh = StringUtils.trimToEmpty(request.getParameter("zllxbh"));
		String flag = StringUtils.trimToEmpty(request.getParameter("flag"));//标记 0添加，1修改, 2 删除
		String zllxmc = StringUtils.trimToEmpty(request.getParameter("zllxmc"));
		Code code = new Code();
		code.setDmlb("0009");
		code.setDmz(zllxbh);
		code.setDmsm1(zllxmc);
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		
		try {
			if("0".equals(flag)){
				codeService.addGredentialsEvaluaReason(code);
				request.setAttribute("msg", "保存成功！");
			}else if("1".equals(flag)){
				code.setZt("1");
				codeService.editGredentialsEvaluaReason(code);
				request.setAttribute("msg", "修改成功！");
			}else if("2".equals(flag)){
				codeService.delCode(zllxbh);
				request.setAttribute("msg", "删除成功！");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "处理失败！");
			e.printStackTrace();
		}
		
		request.setAttribute("action", "getAllZllx.action");
		return SUCCESS;
	}
	
	/**
	 * 验证资料类型编号（dmz）
	 * @return
	 * @throws Exception
	 */
	public String checkZllx() throws Exception{
		String ok = "0";
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String zllxbh = StringUtils.trimToEmpty(request.getParameter("zllxbh"));
		List<Code> list = codeService.getCodeByDmlbAndDmz("0009",zllxbh,"",deptCode,deptHall);
		if(null!=list && !list.isEmpty()){
			ok = "1";  //存在business不可用
		}
		this.getResponse().getWriter().print(ok);
		return ok;
	}
	
	/**
	 * 验证资料类型编号（dmz）
	 * @return
	 * @throws Exception
	 */
	public String changeZllx() throws Exception{
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String zllx = StringUtils.trimToEmpty(request.getParameter("zllx"));
		List<Code> list = codeService.getCodeByDmlbAndDmz(zllx,"","",deptCode,deptHall);
		JSONObject datas = new JSONObject();
		if(null!=list && !list.isEmpty()){
			datas.put("isSuccess", true);
			JSONArray typeGroup = new JSONArray();
			for (Code cod : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("dm", cod.getDm());
					obj.put("mc", cod.getMc());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		}else {
			datas.put("isSuccess", false);
			datas.put("error", "获取资料类型失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	public String addkhdsq(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String khdip = request.getParameter("khdip");
		String sqxx = request.getParameter("sqxx");
		String jym = deptCode+"#"+deptHall+"#"+khdip;
		String sqm = "";
		try {
			sqm = codeService.jm(jym);
		} catch (Exception e1) {
			request.setAttribute("msg", "授权信息生成失败，<br>在执行过程中发生异常！");
			e1.printStackTrace();
		}
		
		Code code = new Code();
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		code.setIp(khdip);
		code.setSqm(sqm);
		code.setSqxx(sqxx);
		try {
			codeService.addkhdsq(code);
		} catch (Exception e) {
			request.setAttribute("msg", "授权信息添加失败，<br>在执行过程中发生异常！");
			e.printStackTrace();
		}
		request.setAttribute("msg", "授权信息添加成功!");
		request.setAttribute("action", "queryAllkhdsq.action");
		return "khdsq";
	}
	
	public String queryAllkhdsq() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Code code = new Code();
		code.setDeptcode(deptCode);
		code.setDepthall(deptHall);
		List<Code> list = codeService.querySQXX(code);
		
		request.setAttribute("list", list);
		return SUCCESS;
	}
}