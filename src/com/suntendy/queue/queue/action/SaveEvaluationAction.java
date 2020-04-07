package com.suntendy.queue.queue.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.nextwindow.domain.NextWindow;
import com.suntendy.queue.nextwindow.service.InextWindowService;
import com.suntendy.queue.queue.dao.impl.NumberDaoImpl;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.TrffException;
import com.suntendy.queue.util.exception.UpdateException;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.EvaluateCompleteEvent;
import com.suntendy.queue.util.trff.TrffUtils;
import com.suntendy.queue.window.domain.Screen;

/*******************************************************************************
 * 描述: 保存评价信息及照片 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-10-15 10:51:56 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class SaveEvaluationAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Publisher publisher;
	private INumberService numberService;
	private ICodeService codeService;
	private InextWindowService nextWindowService;

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = getRequest();
		String operNum = StringUtils.trimToEmpty(request.getParameter("operNum"));
		Number searchNumber = NumberManager.getInstance().fetchCallNumber(operNum);
		Number numberVO = new Number();
		numberVO.setId(searchNumber.getId());
		Number numberVOo = numberService.getValueRecordAllById(numberVO);
		if (null != searchNumber) {
			String evaluTime = DateUtils.dateToString();
			// 标记 1.手动 2.自动
			String flag = StringUtils.trimToEmpty(request.getParameter("flag"));
			String reason = StringUtils.trimToEmpty(request.getParameter("reason"));
			String loginIP = StringUtils.trimToEmpty(request.getParameter("loginIP"));
			String signature = StringUtils.trimToEmpty(request.getParameter("stream"));
			String photoBase64 = StringUtils.trimToEmpty(request.getParameter("photoBase64"));
			String evaluResult = StringUtils.trimToEmpty(request.getParameter("evaluResult"));
			
			Number number = new Number();
			number.setReason(reason);
			number.setOperNum(operNum);
			number.setEndTime(evaluTime);
			number.setEvaluTime(evaluTime);
			number.setId(searchNumber.getId());
			number.setEvaluResult(evaluResult);
			number.setStatus(String.valueOf(flag));
			number.setSerialNum(searchNumber.getSerialNum());
			if (StringUtils.isNotEmpty(signature)) {
				number.setSignature(new BASE64Decoder().decodeBuffer(signature));
			}
			if (StringUtils.isNotEmpty(photoBase64)) {
				number.setPhotoBase64(new BASE64Decoder().decodeBuffer(photoBase64));
			}
			
			String resultMsg = "";
			try {
				if ("1".equals(searchNumber.getOutFlag())) {
					numberService.evaluationForQueueOut(number, loginIP);
				}else {
					numberService.evaluation(number, loginIP);
				}
				CacheManager cacheManager = CacheManager.getInstance();
				String deviceType = cacheManager.getSystemConfig("deviceType");//评价器类型 0新评价器 1旧评价器
				String strResult = "1".equals(evaluResult) ? "非常满意" : "2".equals(evaluResult) ? "满意"
						: "3".equals(evaluResult) ? "一般" : "4".equals(evaluResult) ? "不满意" : "5".equals(evaluResult) ? "不满意":"";
				if("1".equals(deviceType)){
					strResult = "1".equals(evaluResult) ? "非常满意" : "2".equals(evaluResult) ? "满意"
							: "3".equals(evaluResult) ? "一般" : "4".equals(evaluResult) ? "不满意" : "";
				}
				resultMsg = "完成评价，结果：" + strResult;
			} catch (Exception e) {
				if (e instanceof UpdateException) {
					resultMsg = "更新号码信息状态[评价]失败，<br/>请查看当天日志";
					e.printStackTrace();
				} else if (e instanceof RemoteException || e instanceof TrffException) {
					resultMsg = e.getMessage();
				} else {
					resultMsg = "在执行过程中发生异常，请查看当天日志";
					e.printStackTrace();
				}
				
				try {
					numberService.updateNumberByID(searchNumber.getId(), operNum);
				} catch (UpdateException e1) {
					resultMsg += "<br/>更新号码信息状态[评价]失败，请查看当天日志";
					e1.printStackTrace();
				}
			}
			
			publisher.publish(new EvaluateCompleteEvent(loginIP + "@" + resultMsg));
			//点击评价完成后提示下一步去哪个窗口办理业务
			Screen screen = CacheManager.getInstance().getWindow(loginIP);
			CacheManager cacheManager = CacheManager.getInstance();
			NextWindow  nextWindow = new NextWindow();
			String dmlbString="";
			String queueString="";
			String ywyybhString = "";
			String deptCode = cacheManager.getOfDeptCache("deptCode");
			String deptHall = cacheManager.getOfDeptCache("deptHall");
			if ("0".equals(cacheManager.getSystemConfig("isUseInterface")) 
					&& "0".equals(screen.getOpenInterFace())
					&& "0".equals(cacheManager.getSystemConfig("jklx"))) {
				String serialNum = deptCode + deptHall + searchNumber.getSerialNum();
				//评价成功调用过号
				String jym = serialNum + deptCode.substring(0, 6) + "#0#"+ DateUtils.dateToString("yyyyMMdd") + deptCode.substring(6);
				String[] result = TrffUtils.saveEvaluationInfo(serialNum, "0", codeService.jm(jym));
				System.out.println("上传过号信息六合一返回结果result1[0]="+result[0]);
				if ("2".equals(result[0])) {
					throw new TrffException(result[1]);
				} else if (!"1".equals(result[0]) && !"2".equals(result[0])) {
					throw new Exception(result[1]);
				}
				queueString="";
				queueString="";
				ywyybhString="";
				//上传评价信息
				/*
				String jym = serialNum + deptCode.substring(0, 6) + "#0#"
					+ DateUtils.dateToString("yyyyMMdd") + deptCode.substring(6);
				String[] result = TrffUtils.saveEvaluationInfo(serialNum, number.getEvaluResult(), codeService.jm(jym));
				String result1[] = result[1].split("#");
				if(result1[0]==null){result1[0]="";}
				if(result1[1]==null){result1[1]="";}
				if(result1[2]==null){result1[2]="";}
				if (result1[0].equals("02")) {dmlbString = "0060";} 
				if (result1[0].equals("04")) {dmlbString = "0008";}
				queueString = result1[1];
				ywyybhString =String.valueOf(result1[2].charAt(0));
				*/
			}else{
//				String[] result = {"1","2","3","4"};
//				numberVO.setId(number.getId());
//				numberVO.setLsh(result[3]);
//				numberService.updatelsh(numberVO);
				String result1[] = {"0060","B","ABC"};
				if(result1[0]==null){result1[0]="";}else{dmlbString = result1[0];}
				if(result1[1]==null){result1[1]="";}else{queueString = result1[1];}
				if (result1[2]==null) {
					result1[2]="";
				}else {
					ywyybhString =String.valueOf(result1[2].charAt(0));
				}
			}
			nextWindow.setDmlb(dmlbString);
			nextWindow.setDmz(queueString);
			nextWindow.setYwyybh(ywyybhString);
			nextWindow.setDeptCode(deptCode);
			nextWindow.setDeptHall(deptHall);
			NextWindow nwindow =  nextWindowService.queryForCheckResult(nextWindow);
			if(nwindow != null){
				System.out.println(nwindow.getDmlb()+":"+nwindow.getDmz()+":"+nwindow.getYwyybh()+":"+nwindow.getNextwindowval());
				String str = nwindow.getNextwindowval();
				str = new String(str.getBytes("utf-8"),"ISO-8859-1");
				getResponse().getWriter().write(str);
			}
		}

		
		return null;
	}

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

	public InextWindowService getNextWindowService() {
		return nextWindowService;
	}

	public void setNextWindowService(InextWindowService nextWindowService) {
		this.nextWindowService = nextWindowService;
	}

}