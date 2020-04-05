package com.suntendy.queue.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;

import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.trff.TrffClient;
import com.suntendy.queue.util.trff.XMLUtils;

public class GqTimerTask extends TimerTask {
	CacheManager cacheManager = CacheManager.getInstance();
	private INumberService  numberservice;
	
	public GqTimerTask(){
		ApplicationContext ac = SpringUtil.getApplicationContext();
		numberservice = (INumberService)ac.getBean("numberService");
	}
	
	public void run() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if ("0".equals(cacheManager.getSystemConfig("isUseInterface"))){
			System.out.println("开始执行挂起信息核对："+sdf.format(new Date()));
//			try {
//				//获取挂起信息
//				List<Number> listGQ = numberservice.getValueRecordGq();
//				if (null != listGQ) {
//					for (int i = 0; i < listGQ.size(); i++) {
//						Number number = listGQ.get(i);
//						//业务类型为机动车-1时调用接口
//						if (number.getQueueCode().equals("1")) {
//							String rows[][] = new String[1][2];
//							rows[0][0] = "lsh";
//							rows[0][1] = number.getLsh();
//							String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
//							String[] result =  XMLUtils.readXML(TrffClient.write("01C22", strXML));
//							if (!number.getIDNumber().equals(result[0])) {
//								numberservice.updateValueRecordGqStatus(number.getId(),result[0]);
//							}
//							//业务类型为驾驶人-2时调用接口
//						}else if (number.getQueueCode().equals("2")) {
//							String rows[][] = new String[1][2];
//							rows[0][0] = "lsh";
//							rows[0][1] = number.getLsh();
//							String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
//							String[] result =  XMLUtils.readXML(TrffClient.write("02C15", strXML));
//							if (!number.getIDNumber().equals(result[0])) {
//								numberservice.updateValueRecordGqStatus(number.getId(),result[0]);
//							}
//						}
//					}
//				}
//			System.out.println("结束执行挂起信息核对："+sdf.format(new Date()));
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("执行挂起信息核对失败："+sdf.format(new Date()));
//			}
		}else{
			System.out.println("接口未打开，不能执行挂起信息核对："+sdf.format(new Date()));	
		}

	}

}
