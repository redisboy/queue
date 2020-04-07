package com.suntendy.queue.queue.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.opensymphony.webwork.ServletActionContext;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.util.ImageUtils;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.Base64ToPic;
import com.suntendy.queue.util.CompressPic;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.UpdateException;

/*******************************************************************************
 * 描述: 获取证件类型 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-27 13:34:42 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class CredentialsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ICodeService codeService;
	private INumberService numberService;

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	public INumberService getNumberService() {
		return numberService;
	}
	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	@Override
	public String execute() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Code> gredList = codeService.getAllGredentials("0001",deptCode,deptHall);
		
		// 将打印参数信息以JSON格式返回
		JSONObject datas = new JSONObject();
		if (null != gredList && !gredList.isEmpty()) {
			datas.put("isSuccess", true);
			
			JSONArray typeGroup = new JSONArray();
			for (Code code : gredList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("dm", code.getDm());
					obj.put("mc", code.getMc());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				typeGroup.put(obj);
			}
			datas.put("datas", typeGroup);
		} else {
			datas.put("isSuccess", false);
			datas.put("error", "获取证件类型失败");
		}
		this.getResponse("text/html").getWriter().println(datas.toString());

		return null;
	}
	
	/**
	 * 打开摄像头
	 * @return
	 */
	public String openCamera() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String isCamera = cacheManager.getSystemConfig("isCamera");
		String isSignature = cacheManager.getSystemConfig("isSignature");
		String isOpenIndexCamera = cacheManager.getSystemConfig("isOpenIndexCamera");//是否启用取号拍照 0是 1 否
		String autoOrManualCapture = cacheManager.getSystemConfig("autoOrManualCapture");//取号拍照 手动和自动 0自动 1手动
		JSONObject obj = new JSONObject();
			obj.put("isCamera", isCamera);
			obj.put("isSignature", isSignature);
			obj.put("isOpenIndexCamera", isOpenIndexCamera);
			obj.put("autoOrManualCapture", autoOrManualCapture);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	/**
	 * 保存小窗口照片
	 * @return
	 */
	public String savePhoto() throws Exception {
		HttpServletRequest request = getRequest();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String flag = StringUtils.trimToEmpty(request.getParameter("flag"));
		String photoBase64 = StringUtils.trimToEmpty(request.getParameter("photoBase64"));
		String zllx = StringUtils.trimToEmpty(request.getParameter("zllx"));
		Number number = numberService.getNumberAllByClientIP(clientIp);
		String photores ="";
			if(null != number){
				if("0".equals(flag)){//flag标记 0：摄像头 1:手写板 2:高拍仪
					number.setPhotoBase64(new BASE64Decoder().decodeBuffer(photoBase64));
				}else if("1".equals(flag)){
					number.setSignature(new BASE64Decoder().decodeBuffer(photoBase64));
				}else if("2".equals(flag)){
					number.setZllx(zllx);
					if("0".equals(zllx)){number.setGpyPhoto1(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("1".equals(zllx)){number.setGpyPhoto2(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("2".equals(zllx)){number.setGpyPhoto3(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("3".equals(zllx)){number.setGpyPhoto4(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("4".equals(zllx)){number.setGpyPhoto5(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("5".equals(zllx)){number.setGpyPhoto6(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("6".equals(zllx)){number.setGpyPhoto7(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("7".equals(zllx)){number.setGpyPhoto8(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("8".equals(zllx)){number.setGpyPhoto9(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("9".equals(zllx)){number.setGpyPhoto13(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("10".equals(zllx)){number.setGpyPhoto10(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("11".equals(zllx)){number.setGpyPhoto11(new BASE64Decoder().decodeBuffer(photoBase64)); }
					else if("12".equals(zllx)){number.setGpyPhoto12(new BASE64Decoder().decodeBuffer(photoBase64)); }
				}
				photores = numberService.savePhoto(number,flag);
			}else{
				photores ="3";
			}
		this.getResponse("text/html").getWriter().println(photores);
		/*
		String operNum = StringUtils.trimToEmpty(request.getParameter("operNum"));
		String photoBase64 = StringUtils.trimToEmpty(request.getParameter("photoBase64"));
		Number searchNumber = NumberManager.getInstance().fetchCallNumber(operNum);
		Number number = new Number();
		number.setId(searchNumber.getId());
		number.setSerialNum(searchNumber.getSerialNum());
		if (StringUtils.isNotEmpty(photoBase64)) {
			number.setPhotoBase64(new BASE64Decoder().decodeBuffer(photoBase64));
		}
		String photores = numberService.savePhoto(number);
		this.getResponse("text/html").getWriter().prinln(photores);
		*/
		return null;
	}
	
	/**
	 * 人证对比值保存
	 * @return
	 * @throws Exception 
	 */
	public String rzdbz() {
		System.out.println("进入人证对比值保存");
		HttpServletRequest request = getRequest();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String Score = request.getParameter("Score");
		Number number = null;
		try {
			number = numberService.getNumberAllByClientIP(clientIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String flag = "1";//0是成功，1是保存失败，2是未找到对应顺序号
			if(null != number){
				number.setRzdbz(Score);
				try {
					numberService.updateRzdbz(number);
					flag = "0";
				} catch (UpdateException e) {
					flag = "1";
					e.printStackTrace();
				}
			}else {
				flag = "2";
			}
		try {
			this.getResponse("text/html").getWriter().println(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 人证对比验证叫号信息
	 * @return
	 */
	public String rzdbYZ() throws Exception {
		System.out.println("进入人证对比验证");
		JSONObject datas = new JSONObject();
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		System.out.println("sxh="+sxh);
		Number number = numberService.getNumberAllByClientIP(clientIp);
		String yzjh ="";//0代表有数据，1代表为空
		String dbckz = cacheManager.getSystemConfig("rzdbckz");
			if(null != number){
				yzjh = "0";
			}else{
				yzjh ="1";
			}
			datas.put("yzjh", yzjh);
			datas.put("dbckz", dbckz);
		this.getResponse("text/html").getWriter().println(datas);
		return null;
	}
	
	/**
	 * 人证对比验证恢复号码信息
	 * @return
	 */
	public String rzdbHFYZ() throws Exception {
		System.out.println("进入人证对比恢复验证");
		JSONObject datas = new JSONObject();
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		System.out.println("sxh="+sxh);
		Number number = numberService.getNumberAllByClientIPForHF(clientIp,sxh);
		String yzjh ="";//0代表有数据，1代表为空
		String dbckz = cacheManager.getSystemConfig("rzdbckz");
			if(null != number){
				yzjh = "0";
			}else{
				yzjh ="1";
			}
			datas.put("yzjh", yzjh);
			datas.put("dbckz", dbckz);
		this.getResponse("text/html").getWriter().println(datas);
		return null;
	}
	
	/**
	 * 人证对比值保存
	 * @return
	 * @throws Exception 
	 */
	public String rzdbhfz() {
		System.out.println("进入人证对比值恢复保存");
		HttpServletRequest request = getRequest();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		String Score = request.getParameter("Score");
		System.out.println("sxh="+sxh+"````score="+Score+"````clientip="+clientIp);
		Number number = null;
		try {
			number = numberService.getNumberAllByClientIPForHF(clientIp,sxh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String flag = "1";//0是成功，1是保存失败，2是未找到对应顺序号
			if(null != number){
				number.setRzdbz(Score);
				try {
					numberService.updateRzdbz(number);
					flag = "0";
				} catch (UpdateException e) {
					flag = "1";
					e.printStackTrace();
				}
			}else {
				flag = "2";
			}
		try {
			this.getResponse("text/html").getWriter().println(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 人证对比值保存
	 * @return
	 * @throws Exception 
	 */
	public String rzdbqhz() {
		System.out.println("进入人证对比值取号保存");
		HttpServletRequest request = getRequest();
		String clientIp = StringUtils.trimToEmpty(request.getParameter("clientIp"));
		String cardno = StringUtils.trimToEmpty(request.getParameter("cardno"));
		String Score = request.getParameter("Score");
		System.out.println("cardno="+cardno+"````score="+Score+"````clientip="+clientIp);
		Number number = new Number();
		number.setIDNumber(cardno);
		number.setRzdbz(Score);
		String flag = "1";//0是成功，1是保存失败，2是未找到对应顺序号
		try {
			numberService.insertQHrzdbz(number);
			flag = "0";
		} catch (Exception e) {
			e.printStackTrace();
			flag="1";
		}
		try {
			this.getResponse("text/html").getWriter().println(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存取号照片
	 * @return
	 */
	public String saveQuHaoPhoto() throws Exception {
		HttpServletRequest request = getRequest();
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		String sxhid = StringUtils.trimToEmpty(request.getParameter("sxhid"));
		String photoBase64 = StringUtils.trimToEmpty(request.getParameter("photoBase64"));
		String photores ="";
		try{
			ServletContext d=ServletActionContext.getServletContext();
		    String path=d.getRealPath("/");
		    ImageUtils.delAllFile(path+"\\base64ToPic"); //删除完里面所有内容
		//将base64转换成图片
				byte[] bytes = new BASE64Decoder().decodeBuffer(photoBase64);  
				for (int i = 0; i < bytes.length; ++i) {  
				if (bytes[i] < 0) {// 调整异常数据  
				bytes[i] += 256;  
				}  
				}  
				// 生成jpeg图片  
				OutputStream out = new FileOutputStream(path+"\\base64ToPic\\"+sxh+".jpg");  
				out.write(bytes);  
				out.flush();  
				out.close();  
		//压缩图片---begin
		CompressPic mypic = new CompressPic();   
        System.out.println("输入的图片大小：" + mypic.getPicSize(path+"\\base64ToPic\\"+sxh+".jpg")/1024 + "KB");   
         int start = (int) System.currentTimeMillis();   // 开始时间   
         mypic.compressPic(path+"\\base64ToPic\\", path+"\\base64ToPic\\", ""+sxh+".jpg", ""+sxh+".jpg", 600, 600, true);   
         int end = (int) System.currentTimeMillis(); // 结束时间   
       //int re = end-start; // 但图片生成处理时间   
         System.out.println("输出的图片大小：" + mypic.getPicSize(path+"\\base64ToPic\\"+sxh+".jpg")/1024 + "KB");   
       //压缩图片---end
            
		Number number = new Number();
		number.setId(sxhid);
		//number.setSerialNum(sxh);
		number.setQuhaoPhoto(new BASE64Decoder().decodeBuffer(Base64ToPic.GetImageStr(path+"\\base64ToPic\\"+sxh+".jpg")));
		
		
			 numberService.savePhoto(number,"");
			 photores = "0";
		}catch(Exception e){
			 photores = "1";
			e.printStackTrace();
		}
		this.getResponse("text/html").getWriter().println(photores);
		return null;
	}
	
	/**
	 * 保存高拍仪照片
	 * @return
	 */
	public String savaGpyPhoto() throws Exception {
		HttpServletRequest request = getRequest();
		String photo1 = StringUtils.trimToEmpty(request.getParameter("photo1"));
		String photo2 = StringUtils.trimToEmpty(request.getParameter("photo2"));
		String photo3 = StringUtils.trimToEmpty(request.getParameter("photo3"));
		Number number = new Number();
		number.setPhoto1(new BASE64Decoder().decodeBuffer(photo1));
		number.setPhoto2(new BASE64Decoder().decodeBuffer(photo2));
		number.setPhoto3(new BASE64Decoder().decodeBuffer(photo3));
		String photores = numberService.savaGpyPhoto(number);
		
		this.getResponse("text/html").getWriter().println(photores);
		return null;
	}

}