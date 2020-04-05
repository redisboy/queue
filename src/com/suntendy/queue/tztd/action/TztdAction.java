package com.suntendy.queue.tztd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.domain.QuHaoMsg;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.tztd.domain.TztdVO;
import com.suntendy.queue.tztd.service.ItztdService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.trff.TrffUtils;

public class TztdAction extends BaseAction {

	private ItztdService tztdService;

	public ItztdService getTztdService() {
		return tztdService;
	}
	public void setTztdService(ItztdService tztdService) {
		this.tztdService = tztdService;
	}
	
	/*
	 * 查询所有
	 */
	public String getTztdAll(){
		HttpServletRequest request = getRequest();
		String lrrq = request.getParameter("lrrq");//标记 1机动车 2驾驶人
		String sfzmhm = request.getParameter("idnumber");
		TztdVO tztd = new TztdVO();
		tztd.setLrrq(lrrq);
		tztd.setIdnumber(sfzmhm);
		try {
			List<TztdVO> list = tztdService.queryAllTztd(tztd);
			if(null != list && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					TztdVO tztdVO = list.get(i);
					
					if ("01".equals(tztdVO.getBj())) {
						tztdVO.setBj("大型汽车");
					}else if ("02".equals(tztdVO.getBj())) {
						tztdVO.setBj("小型汽车");
					}else if ("15".equals(tztdVO.getBj())) {
						tztdVO.setBj("挂车");
					}else if ("16".equals(tztdVO.getBj())) {
						tztdVO.setBj("教练汽车");
					}else if ("23".equals(tztdVO.getBj())) {
						tztdVO.setBj("警用汽车");
					}
					
					
					String operate ="<a onclick=delTztd('" + tztdVO.getId()
					+ "')><img src='/queue/images/button_sp.jpg' style='cursor:hand'/></a>";
					tztdVO.setOperate(operate);
				}
			}
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 是否开启通知提档
	 * @return
	 * @throws Exception
	 */
	public String getIsOpenTztd() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String isOpenTztd = cacheManager.getSystemConfig("isOpenTztd");//是否启用通知提档
		JSONObject obj = new JSONObject();
		obj.put("isOpenTztd", isOpenTztd);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	/**
	 * 调用接口查询机动车信息
	 * @return
	 * @throws Exception
	 */
	public String getinterFaceTztd() throws Exception {
		HttpServletRequest request = getRequest();
		String isUseInterface = CacheManager.getInstance().getSystemConfig("isUseInterface");
		String sfzmhm = request.getParameter("IDNumber");
		//String[] result1 = TrffUtils.isNumberByHphm(sfzmhm);
		
		JSONObject datas = new JSONObject();
		//if (null != list && !list.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (int i=0;i<3;i++) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("hphm", "第"+i+"条");
					obj.put("hpzl", "测试数据");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
			datas.put("isUseInterface", isUseInterface);
		//} else {
		//	datas.put("isSuccess", false);
		//	datas.put("error", "获取信息失败");
		//}
		this.getResponse("text/html").getWriter().println(datas.toString());
		return null;
	}
	
	/*
	 * 添加
	 */
	public String saveTztd() throws Exception{
		HttpServletRequest request = getRequest();
		String flag = request.getParameter("flag");//标记 1机动车 2驾驶人
		String sfzmhm = request.getParameter("IDNumber");
		
		TztdVO tztd = new TztdVO();
		//查询最大ID
		String id = tztdService.queryMaxId(tztd).getId();
		tztd.setBj(flag);
		tztd.setIdnumber(sfzmhm);
		tztd.setId(id);
		String msg= "true";
		try {
			tztdService.saveTztd(tztd);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "false";
		}
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	/*
	 * 删除
	 */
	public String delTztd() throws Exception{
		HttpServletRequest request = getRequest();
		String id = request.getParameter("id");//标记 1机动车 2驾驶人
		TztdVO tztd = new TztdVO();
		tztd.setId(id);
		try {
			tztdService.delTztd(tztd);
			request.setAttribute("msg", "审核成功！");
		} catch (Exception e) {
			request.setAttribute("msg", "在执行过程中发生异常，异常信息：<br/>" + e.getMessage());
		}
		request.setAttribute("action", "tztd/getTztdAll.action");
		return SUCCESS;
	}
	
	/*
	 * 查询已提档
	 */
	public String getTztd(){
		HttpServletRequest request = getRequest();
		String lrrq = request.getParameter("lrrq");//标记 1机动车 2驾驶人
		String sfzmhm = request.getParameter("idnumber");
		TztdVO tztd = new TztdVO();
		tztd.setLrrq(lrrq);
		tztd.setIdnumber(sfzmhm);
		try {
			List<TztdVO> list = tztdService.queryTztd(tztd);
			if(null != list && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					TztdVO tztdVO = list.get(i);
					
					if ("01".equals(tztdVO.getBj())) {
						tztdVO.setBj("大型汽车");
					}else if ("02".equals(tztdVO.getBj())) {
						tztdVO.setBj("小型汽车");
					}else if ("15".equals(tztdVO.getBj())) {
						tztdVO.setBj("挂车");
					}else if ("16".equals(tztdVO.getBj())) {
						tztdVO.setBj("教练汽车");
					}else if ("23".equals(tztdVO.getBj())) {
						tztdVO.setBj("警用汽车");
					}
					
					
//					String operate ="<a onclick=delTztd('" + tztdVO.getId()
//					+ "')><img src='/queue/images/button_sp.jpg' style='cursor:hand'/></a>";
//					tztdVO.setOperate(operate);
				}
			}
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
