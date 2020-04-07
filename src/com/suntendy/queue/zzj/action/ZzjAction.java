package com.suntendy.queue.zzj.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.zzj.domain.Zzj;
import com.suntendy.queue.zzj.service.IZzjService;

public class ZzjAction extends BaseAction {

	private IZzjService zzjService;

	public IZzjService getZzjService() {
		return zzjService;
	}

	public void setZzjService(IZzjService zzjService) {
		this.zzjService = zzjService;
	}
	
	public String toAddZzj(){
		return "addZzj";
	}
	
	public String AddZzj(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String qhjip = request.getParameter("qhjip");
		int zdyzs = Integer.parseInt(request.getParameter("zdyzs"));
		int bjzs = Integer.parseInt(request.getParameter("bjzs"));
		
		Zzj zzj = new Zzj();
		zzj.setZdyzs(zdyzs);
		zzj.setBjzs(bjzs);
		zzj.setQhjip(qhjip);
		zzj.setDeptcode(deptCode);
		zzj.setDepthall(deptHall);
		zzj.setCzrq(DateUtils.dateToString("yyyy-MM-dd HH:mm:ss"));
		
		try {
			zzjService.saveZzj(zzj);
			request.setAttribute("msg", "自助机缺纸信息添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "自助机缺纸信息添加失败！");
		}
		request.setAttribute("action", "zzj/ZzjList.action");
		return SUCCESS;
	}
	
	public String delZzj(){
		HttpServletRequest request = this.getRequest();
		String qhjip = request.getParameter("qhjip");
		Zzj zzj = new Zzj();
		zzj.setQhjip(qhjip);
		try {
			zzjService.delZzj(zzj);
			request.setAttribute("msg", "自助机缺纸信息删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "自助机缺纸信息删除失败！");
		}
		request.setAttribute("action", "zzj/ZzjList.action");
		return SUCCESS;
	}
	
	public String czZzj(){
		HttpServletRequest request = this.getRequest();
		String qhjip = request.getParameter("qhjip");
		Zzj zzj = new Zzj();
		zzj.setQhjip(qhjip);
		try {
			zzjService.czZzj(zzj);
			request.setAttribute("msg", "累计打印张数重置成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "累计打印张数重置失败！");
		}
		request.setAttribute("action", "zzj/ZzjList.action");
		return SUCCESS;
	}
	
	public String ZzjList() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String qhjip = request.getParameter("qhjip");
		Zzj zzj = new Zzj();
		zzj.setDeptcode(deptCode);
		zzj.setDepthall(deptHall);
		zzj.setQhjip(qhjip);
		List<Zzj> list = zzjService.queryZzj(zzj);
		for (int i = 0; i < list.size(); i++) {
			Zzj zzjVo = list.get(i);
			String id = zzjVo.getQhjip();
			String operate ="<a onclick=czZzj('" + id
			+ "')><img src='/queue/images/button_cz.jpg' style='cursor:hand'/></a>"+"&nbsp;&nbsp;"+"<a onclick=delZzj('" + id
			+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
			zzjVo.setOperation(operate);
			
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	public String YzZzj() throws Exception{
		String yzdybd = "0";
		HttpServletRequest request = this.getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String bdip = cacheManager.getSystemConfig("bdip");
		String flag = "0";
		JSONObject obj = new JSONObject();
		Zzj zzj = new Zzj();
		zzj.setQhjip(this.clientIp());
		zzj.setDeptcode(deptCode);
		zzj.setDepthall(deptHall);
		try {
			List<Zzj> list = zzjService.queryZzj(zzj);
			if (list.size()>0) {
				if (list.get(0).getZdyzs()-list.get(0).getLjdyzs()>0) {
					if (list.get(0).getBjzs()-list.get(0).getLjdyzs()>0) {
						flag = "0";//正常
					}else {
						flag = "1";//报警
					}
				}else {
					flag = "2";//禁止
				}
			}else {
				flag = "0";//正常
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!"".equals(bdip) && bdip != null) {
			for (int i = 0; i < bdip.split(",").length; i++) {
				if (bdip.split(",")[i].equals(this.clientIp())) {
					yzdybd = "1";
				}
			}
		}
		obj.put("flag", flag);
		obj.put("yzdybd", yzdybd);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	
}
