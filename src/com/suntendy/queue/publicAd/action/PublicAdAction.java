package com.suntendy.queue.publicAd.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.publicAd.domain.PublicAd;
import com.suntendy.queue.publicAd.service.IPublicAdService;
import com.suntendy.queue.util.cache.CacheManager;

public class PublicAdAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private IPublicAdService publicAdService;
	private DeptService deptService;
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 *根据id修改宣传材料的启用状态
	 * @return
	 * @throws Exception
	 */
	public void updateAdbyId() throws Exception{
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		String deptCode = request.getParameter("deptCode");
		String deptHall = request.getParameter("deptHall");
		String isUsingFlag = request.getParameter("isUsing");
		String role = request.getParameter("role");
		PublicAd publicAd = new PublicAd();
		publicAd.setId(id);
		//publicAd.setDeptCode(deptCode);
		//publicAd.setDeptHall(deptHall);
		publicAd.setIsUsing(isUsingFlag);
		try {
			publicAdService.updateByCode(publicAd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getResponse().sendRedirect("/queue/xccl.action?redirectFlag=1&deptCode="+deptCode+"&deptHall="+deptHall);

	}
	/*
	 * 宣传材料设置
	 */
	public String publicAd() throws Exception{
		HttpServletRequest request = getRequest();
		HttpSession session =request.getSession();
		String deptCode = "";
		String deptHall = "";
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) session.getAttribute("user");
		String role = user.getRole();
		
		//重定向标记,判断是否是通过点击按钮重定向 进行访问的,
		String redirectFlag = request.getParameter("redirectFlag");
		if (redirectFlag==null) {
			redirectFlag="";
		}
		//下拉框选择标记,判断是否是用过下拉框点击 进行访问的,
		String selectFlag = request.getParameter("selectFlag");
		if (selectFlag==null) {
			selectFlag="";
		}
		//根据标记方式 设置deptCode 和deptHall
		if (redirectFlag.equals("1")||selectFlag.equals("1")) {
			deptCode = request.getParameter("deptCode");
			deptHall = request.getParameter("deptHall");
			if (deptCode==null||"".equals(deptCode)) {
				request.setAttribute("currentDept","all");
			}else {
				request.setAttribute("currentDept",deptCode+"-"+deptHall);
			}
		}else{
			if (role.equals("0")) {
				deptCode = request.getParameter("deptCode");
				deptHall = request.getParameter("deptHall");
				request.setAttribute("currentDept","all");
			}else {
				CacheManager cacheManager = CacheManager.getInstance();
				deptCode = cacheManager.getOfDeptCache("deptCode");
				deptHall = cacheManager.getOfDeptCache("deptHall");
				request.setAttribute("currentDept",deptCode+"-"+deptHall);
			}
		}
		String id1=null;
		
		//flag=1表示 有超级管理员设置的宣传材料并且在启用中
		List<PublicAd> list1 = publicAdService.getPublicAd(id1, "",deptCode,deptHall);
		String flag = "0";//判断是否有角色0设置并启用的宣传信息
		if(!list1.isEmpty()){
			for(PublicAd publicAd:list1){
				if (publicAd.getIsUsing().equals("1")&&publicAd.getOperator().equals("0")) {
					flag = "1";
				}
			}
			
		}
		
		//取大厅编号对应的名称放入 map 并判断角色是否存入 request
		HashMap<String, String> deptInfo = new HashMap<String, String>();
		try {
			List<Map<String, String>> list = deptService.findAllDeptcode();
			
			//ArrayList<HashMap<String, String>> deptList = new ArrayList<HashMap<String, String>>();
			for(Map<String, String> item:list){
				String codeAndHall = item.get("DEPTCODE")+"-"+item.get("DEPTHALL");
				String hallName = item.get("DEPTNAME");
				deptInfo.put(codeAndHall, hallName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block-+
			e.printStackTrace();
		}
		if(role.equals("0")){
			request.setAttribute("deptInfo",deptInfo);	
		}
		
		//根据flag 设置页面显示的数据内容
		if (!list1.isEmpty()) {
			for (int i = 0; i < list1.size(); i++) {
				PublicAd publicAdVo = list1.get(i);
				String ID =publicAdVo.getId();
				String state=publicAdVo.getMsg_state();
				String operator = publicAdVo.getOperator();
				String isUsing = publicAdVo.getIsUsing();
				String msg = publicAdVo.getMessage();
				String button="";
				String codeAndHall2 = publicAdVo.getDeptCode()+"-"+publicAdVo.getDeptHall();
				publicAdVo.setDeptHall(deptInfo.get(codeAndHall2));
				if (msg.length()>25) {
					publicAdVo.setMessage(msg.substring(0,25)+"...");
				}
				if(state.equals("1")){
					publicAdVo.setMsg_state("滚动");
				}else{
					publicAdVo.setMsg_state("不滚动");
				}
				
				if (flag.equals("1")) {
					if (publicAdVo.getIsUsing().equals("1")) {
						if (operator.equals("0")&&role.equals("0")) {
							publicAdVo.setIsUsing("<div style='color:green'>启用中</div>");
							//操作者和角色相对应正常停用
							button = "<input value='停用' onclick='change("+ID+","+role+",0)' style='width:60px;text-align:center;font-weight:bold;color:red'/>";
						}else if(operator.equals("0")&&(!role.equals("0"))){
							publicAdVo.setIsUsing("<div style='color:green'>启用中</div>");
							//操作者权限不够 无法停用
							button = "<input value='停用' onclick='msg(1)' style='width:60px;text-align:center;font-weight:bold;color:red'/>";
						}else {
							publicAdVo.setIsUsing("<div style='color:red'>未启用</div>");
							//大厅设置的内容 任何角色都可以停用
							button = "<input value='启用' onclick='msg(3)' style='width:60px;text-align:center;font-weight:bold;color:green'/>";
						}
					}else {
						publicAdVo.setIsUsing("<div style='color:red'>未启用</div>");
						if(operator.equals("0")&&role.equals("0")){
							//操作者和角色相对应 提示停止:请先停止启动中的宣传材料
							button = "<input value='启用' onclick='msg(3)' style='width:60px;text-align:center;font-weight:bold;color:green' />";
						}else if(operator.equals("0")&&(!role.equals("0"))){
							//权限不足无法启用
							button = "<input value='启用' onclick='msg(2)' style='width:60px;text-align:center;font-weight:bold;color:green' />";
						}else if((!operator.equals("0"))&&role.equals("0")){
							//大厅设置的内容 角色0都可以启用  提示停止:请先停止启动中的宣传材料
							button = "<input value='启用' onclick='msg(3)' style='width:60px;text-align:center;font-weight:bold;color:green' />";
						}else {
							//大厅设置的内容 角色!=0都可以不可以启用  提示停止:权限不够
							button = "<input value='启用' onclick='msg(1)' style='width:60px;text-align:center;font-weight:bold;color:green' />";
						}
					}
				}else {
					if (isUsing.equals("1")) {
						publicAdVo.setIsUsing("<div style='color:green'>启用中</div>");
						button = "<input value='停用' onclick='change("+ID+","+role+",0)' style='width:60px;text-align:center;font-weight:bold;color:red'/>";
					}else {
						publicAdVo.setIsUsing("<div style='color:red'>未启用</div>");
						if (operator.equals("0")&&role.equals("0")) {
							//操作者和角色相对应正常启用
							button = "<input value='启用' onclick='change("+ID+","+role+",1)'  style='width:60px;text-align:center;font-weight:bold;color:green'/>";
							
						}else if(operator.equals("0")&&(!role.equals("0"))){
							//权限不足无法启用
							button = "<input value='启用' onclick='msg(2)' style='width:30px;text-align:center;font-weight:bold;color:green' />";
						}else {
							//大厅设置的内容 任何角色都可以启用
							button = "<input value='启用' onclick='change("+ID+","+role+",1)'  style='width:60px;text-align:center;font-weight:bold;color:green'/>";
						}
					}
				}
				String operate = "<a onclick=updatePublicAd("
					+ ID+","+operator+","+role
					+ ")><img src='/queue/images/button_edit.jpg' style='cursor:hand' ></a>"  
					+ "&nbsp;"
					+ "<a onclick=delPublicAd("
					+ ID+","+operator+","+role
					+ ")><img src='/queue/images/button_del.jpg' style='cursor:hand'></a>"
					+ "&nbsp;"
					+button;
				publicAdVo.setOperation(operate);
				if (operator.equals("0")) {
					publicAdVo.setOperator("总队管理员");
				}else {
					publicAdVo.setOperator("普通管理员");
				}
				
			}
		}
		request.setAttribute("role", role);
		request.setAttribute("list1", list1);
		return SUCCESS;
	}
	/*
	 * 添加宣传材料
	 */
	public String into_addPublicAd(){
		return SUCCESS;
	}
	/*
	 * 添加宣传材料
	 */
	public String addPublicAd() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String message = request.getParameter("code");
		String msg_state = request.getParameter("state");
		String isUsing = request.getParameter("isUsing");
		//role 0:超级管理员 1:部门系统管理员 2:普通大厅管理员
		Employee user=(Employee) getHttpSession().getAttribute("user");
		String role = user.getRole();
		PublicAd publicAdVo= new PublicAd();
		publicAdVo.setMessage(message.trim());
		publicAdVo.setMsg_state(msg_state);
		publicAdVo.setDeptCode(deptCode);
		publicAdVo.setDeptHall(deptHall);
		publicAdVo.setOperator(role);
		publicAdVo.setIsUsing(isUsing);
		if (isUsing.equals("1")&&role.equals("0")) {
			publicAdService.updateAdAllUsing();  //把所有operator=0的都设置为不启用
		}
		Integer updateflag = publicAdService.addPublicAd(publicAdVo);
		if(updateflag==null){
			//队列信息修改成功
			request.setAttribute("msg", "宣传材料添加成功！");
		}else{ 
			request.setAttribute("msg", "宣传材料添加失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "xccl.action");		
		return SUCCESS;
	}
	/*
	 * 修改宣传材料 初始化页面
	 */
	public String updatePublicAd() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String id = request.getParameter("id");
		List<PublicAd> Advertiselist = publicAdService.getPublicAd(id, "",null,null);
		if(!Advertiselist.isEmpty()){
			PublicAd publicAdVo = Advertiselist.get(0);
			String message = publicAdVo.getMessage();
			String state = publicAdVo.getMsg_state();
			String ID =publicAdVo.getId();
			request.setAttribute("message", message);
			request.setAttribute("state", state);
			request.setAttribute("id", ID);
		}
		return SUCCESS;
	}
	
	/*
	 * 修改宣传材料
	 */
	public String updateByCode()throws Exception{
		HttpServletRequest request = getRequest();
		String id=request.getParameter("id");
		String message = request.getParameter("code");
		String msg_state = request.getParameter("state");
	    int updateflag = publicAdService.updateByCode(id,message.trim(),msg_state);
		if(updateflag==1){
			//队列信息修改成功
			request.setAttribute("msg", "宣传材料信息修改成功！");
		}else{
			request.setAttribute("msg", "宣传材料信息修改失败，<br>在执行过程中发生异常！");
		}
		
		request.setAttribute("action", "xccl.action");		
		
		return SUCCESS;
	}
	/*
	 * 删除宣传材料
	 */
	public String delPublicAd()throws Exception{
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");
		int updateflag = publicAdService.delPublicAd(id);
		if(updateflag==1){
			//队列信息修改成功
			request.setAttribute("msg", "宣传材料删除成功！");
		}else{ 
			request.setAttribute("msg", "宣传材料删除失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "xccl.action");		
		return SUCCESS;
	}
	public IPublicAdService getPublicAdService() {
		return publicAdService;
	}
	public void setPublicAdService(IPublicAdService publicAdService) {
		this.publicAdService = publicAdService;
	}


}