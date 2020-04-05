package com.suntendy.queue.evaluation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.evaluation.domain.ValueRecord;
import com.suntendy.queue.evaluation.service.ISetEvaluationService;
import com.suntendy.queue.util.cache.CacheManager;

public class SetEvaluationAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISetEvaluationService setEvaluationService;

	/*
	 * 评价设置
	 */
	public String pjsz() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String isUseOldDevice = cacheManager.getSystemConfig("isUseOldDevice");
		List<ValueRecord> list = setEvaluationService.getPj(deptCode, deptHall);
		
		if (null != list && !list.isEmpty()) {
			for (int i = 0, len = list.size(); i < len; i++) {
				ValueRecord valueRecordVo = list.get(i);
				String value = valueRecordVo.getValue();
				String state = valueRecordVo.getState();
				request.setAttribute("value" + i + "", value); // 给<select>送值
				request.setAttribute("state" + i + "", state);
			}
			request.setAttribute("isExist", "1");
		} else {
			request.setAttribute("isExist", "0");
		}
		request.setAttribute("isUseOldDevice", isUseOldDevice);
		return "pjsz";
	}

	/*
	 * 更改评价值
	 */
	public String updateEvaluat() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		List<ValueRecord> datas = new ArrayList<ValueRecord>();
		for (int i = 1; i <= 4; ++ i) {
			ValueRecord vo = new ValueRecord();
			vo.setId(i + "");
			switch (i) {
				case 1:
					vo.setName("非常满意");
					break;
				case 2:
					vo.setName("满意");
					break;
				case 3:
					vo.setName("一般");
					break;
				case 4:
					vo.setName("不满意");
					break;
			}
			vo.setDeptCode(deptCode);
			vo.setDeptHall(deptHall);
			vo.setEvalueclass("pj_" + i);
			vo.setValue(request.getParameter("pj" + i));
			vo.setState(request.getParameter("state" + i));
			datas.add(vo);
		}
		
		try {
			if ("0".equals(request.getParameter("isExist"))) {
				setEvaluationService.add(datas);
			} else {
				setEvaluationService.updatePj(datas);
			}
			request.setAttribute("msg", "评价修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "评价修改失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "setEvaluation/setEvaluation!pjsz.action");
		return "updateEvaluat";
	}
	
	/**
	 * 查看摄像头及手写板图片
	 * @return List
	 */
	public String getPhotoAll() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String idnumber = StringUtils.trimToEmpty(request.getParameter("sfzmhm"));
		ValueRecord valueRecord = new ValueRecord();
		valueRecord.setIdnumber(idnumber);
		valueRecord.setDeptCode(deptCode);
		valueRecord.setDeptHall(deptHall);
		valueRecord.setKsrq(ksrq);
		valueRecord.setJsrq(jsrq);
		valueRecord.setTjms(tjms);
		List<ValueRecord> photoList=setEvaluationService.getPhotoAll(valueRecord);
		if (!photoList.isEmpty()) {
			for (int i = 0; i < photoList.size(); i++) {
				ValueRecord records = photoList.get(i);
				String operate = "<a onclick=toDetail('"+ records.getId() + "')><img src='/queue/images/button_ck.jpg' style='cursor:hand'></a>";
				records.setOperation(operate);
			}
		}
		request.setAttribute("photoList", photoList);
		return "togetphoto";
	}
	/**
	 * 查看摄像头及手写板图片详细
	 * @return List
	 */
	public String toPhotoDetails() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		ValueRecord valueRecord = new ValueRecord();
		valueRecord.setId(id);
		valueRecord.setDeptCode(deptCode);
		valueRecord.setDeptHall(deptHall);
		valueRecord.setKsrq(ksrq);
		valueRecord.setJsrq(jsrq);
		valueRecord.setTjms(tjms);
		List<ValueRecord> photoList=setEvaluationService.getPhotoAll(valueRecord);
		ValueRecord record = photoList.get(0);
			request.setAttribute("record", record);
		return "tophotoDetails";
	}
	/**
	 * 显示摄像头图片
	 * @return
	 * @throws Exception
	 */
	public String getCameraImg() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		ValueRecord valueRecord = new ValueRecord();
		valueRecord.setId(id);
		valueRecord.setDeptCode(deptCode);
		valueRecord.setDeptHall(deptHall);
		valueRecord.setKsrq(ksrq);
		valueRecord.setJsrq(jsrq);
		valueRecord.setTjms(tjms);
		
		List<ValueRecord> photoList = setEvaluationService.getPhotoAll(valueRecord);
		if (null != photoList && !photoList.isEmpty()) {
			ValueRecord record = photoList.get(0);
			if (record.getEvaluphoto() != null) {
				ServletOutputStream out = getResponse("image/jpeg").getOutputStream();
				out.write(record.getEvaluphoto());
				out.flush();
				out.close();
			}
		}
		return null;
	}
	/**
	 * 显示办理人签字图片
	 * @return
	 * @throws Exception
	 */
	public String getSignatureImg() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		HttpServletResponse response=getResponse("image/jpeg");
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		ValueRecord valueRecord = new ValueRecord();
		valueRecord.setId(id);
		valueRecord.setDeptCode(deptCode);
		valueRecord.setDeptHall(deptHall);
		valueRecord.setKsrq(ksrq);
		valueRecord.setJsrq(jsrq);
		valueRecord.setTjms(tjms);
		List<ValueRecord> photoList=setEvaluationService.getPhotoAll(valueRecord);
		if(null!=photoList && !photoList.isEmpty()){
			ValueRecord record = photoList.get(0);
			if(record.getSignature()!=null){
				ServletOutputStream out=response.getOutputStream();
				out.write(record.getSignature());
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 显示高拍仪图片
	 * @return
	 * @throws Exception
	 */
	public String getGpyImg() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		HttpServletResponse response=getResponse("image/jpeg");
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		ValueRecord valueRecord = new ValueRecord();
		valueRecord.setId(id);
		valueRecord.setDeptCode(deptCode);
		valueRecord.setDeptHall(deptHall);
		valueRecord.setKsrq(ksrq);
		valueRecord.setJsrq(jsrq);
		valueRecord.setTjms(tjms);
		List<ValueRecord> photoList=setEvaluationService.getPhotoAll(valueRecord);
		if(null!=photoList && !photoList.isEmpty()){
			ValueRecord record = photoList.get(0);
			if(record.getGpyphoto()!=null){
				ServletOutputStream out=response.getOutputStream();
				out.write(record.getGpyphoto());
				out.flush();
				out.close();
			}
		}
		return null;
	}

	public void setSetEvaluationService(ISetEvaluationService setEvaluationService) {
		this.setEvaluationService = setEvaluationService;
	}
}