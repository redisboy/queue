package com.suntendy.queue.lzxx.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.lzxx.domain.Lzxx;
import com.suntendy.queue.lzxx.service.ILzxxService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.passLzxx;

public class AutoSendLzxxAction extends BaseAction{

	CacheManager cacheManager = CacheManager.getInstance();
//	private ApplicationContext applicationContext;
	private ILzxxService lzxxService;
	private LedService ledService;
	private INumberService numberService;
	private Publisher publisher;
	
//	public AutoSendLzxxAction(ServletContext context) {
//		this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
//	}
//	
//	public AutoSendLzxxAction(ApplicationContext applicationContext) {
//		this.applicationContext = applicationContext;
//	}
	
//	Publisher publisher = (Publisher) applicationContext.getBean("publisher");
	
	public ILzxxService getLzxxService() {
		return lzxxService;
	}
	public void setLzxxService(ILzxxService lzxxService) {
		this.lzxxService = lzxxService;
	}
	public LedService getLedService() {
		return ledService;
	}
	public void setLedService(LedService ledService) {
		this.ledService = ledService;
	}
	public INumberService getNumberService() {
		return numberService;
	}
	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	//	public static int sendNum = 0;
	public String autoSendLzxx(){
		int sNum = LzckAction.sendNum;
		System.out.println("sendNum="+sNum);
		
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		
		Lzxx lzxx1 = new Lzxx();
		if ("0".equals(cacheManager.getSystemConfig("autoLzxx"))) {
			System.out.println("开始自动发屏");
			lzxx1.setDeptHall(deptHall);
		}else{
			lzxx1.setStatus("3");
		}
		try {
			int lzcknum = Integer.parseInt(cacheManager.getSystemConfig("lzcknum"));
		List<Lzxx> list = lzxxService.queryAllLzxx(lzxx1);
		if (list.size()>lzcknum && "0".equals(cacheManager.getSystemConfig("isOpenInformation"))) {
				if (sNum+1>list.size()) {
					sNum = 0;
					LzckAction.sendNum = 0;
				}
			if ("0".equals(cacheManager.getSystemConfig("lzxxSendMode"))) {//异步
//				for (int k = 0; k < list.size(); k++) {
					
					//叫号队列屏发屏
					LED led = new LED();
					String sendContenttemp="";
					led.setDeptCode(deptCode);
					led.setDeptHall(deptHall);
					led.setFlag("1");
					//获取LED屏信息
					
					List<LED> led_TVlist = ledService.getLedInfo_TV(led);
					
					for(int i=0;i<led_TVlist.size();i++){
						sendContenttemp = "";
						sendContenttemp = LzckAction.sendLzxxContent;
						
						String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
						//窗口LED屏号
//			List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx1);
						List<Lzxx> listLzck =new ArrayList<Lzxx>();
						String content = led_TVlist.get(i).getContent();
						int mubanchangdu = content.length();
						System.out.println("模板="+content);
						//个位数窗口屏补位
						
						listLzck = lzxxService.queryLzckByZllx(list.get(sNum));
						for (int j = 0; j < listLzck.size(); j++) {
							Lzxx lzxxVo = listLzck.get(j);
							if(10>Integer.parseInt(lzxxVo.getBarid())){
								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
							}
							
						}
						
						System.out.println("姓名="+list.get(sNum).getXm());
						System.out.println("窗口号="+list.get(sNum).getLzckh());
//			sendLzxxContent += list.get(k).getXm()+"到"+listLzck.get(0).getBarid()+"号窗口领证 ";
						//空格数量转换空格
						String kongge = "";
						if(null!=led_TVlist.get(i).getSpace() && !"".equals(led_TVlist.get(i).getSpace())&& !"0".equals(led_TVlist.get(i).getSpace())){
							for(int x=0;x<Integer.parseInt(led_TVlist.get(i).getSpace());x++){
								kongge += " ";
							}
						}
						//模板替换
//						String xm = list.get(sNum).getXm();
//						if (xm.length()<3) {
//							for(int x=0;x<3-xm.length();x++){
//								xm +="、";
//							}
//						}
//						content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid())+kongge;
						
						
						
						Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
						String xm ="";
							xm = list.get(sNum).getXm();
						Matcher m = p.matcher(xm);
						if (m.find()) {
							System.out.println("进入中文");
							if (xm.length()<3) {
								for(int x=0;x<3-xm.length();x++){
									xm +="、";
								}
							}else if (xm.length()>6) {
								xm = xm.substring(0, 6);
							}
								if (!"".equals(list.get(sNum).getLzckh()) && list.get(sNum).getLzckh() != null) {
									content = content.replaceAll("@", xm).replaceAll("#", list.get(sNum).getLzckh());
								}else {
									if (listLzck.size()>0) {
										content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid());
									}else {
										content = content.replaceAll("@", xm).replaceAll("#", "  ");
									}
								}
							if (xm.length()>3) {
								content = content.substring(0, content.length()-(xm.length()-3));
								System.out.println("截取的content="+content);
							}
							
						}else{
							System.out.println("进入英文");
							if (xm.length()<6) {
								int changdu = 6-xm.length();
								for (int mm = 0; mm < changdu; mm++) {
									xm += " ";
								}
							}else if (xm.length()>6) {
								xm = xm.substring(xm.length()-6,xm.length());
							}
							if (xm.length()>6 && xm.length()%2==1) {
								xm +=" ";
							}

								if (!"".equals(list.get(sNum).getLzckh()) && list.get(sNum).getLzckh() != null) {
									content = content.replaceAll("@", xm).replaceAll("#", list.get(sNum).getLzckh());
								}else {
									if (listLzck.size()>0) {
										content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid());
									}else {
										content = content.replaceAll("@", xm).replaceAll("#", "  ");
									}
								}
							if (xm.length()>6 && xm.length()%2==0) {
								content = content.substring(0, content.length()-(xm.length()-6)/2);
								System.out.println("截取的content="+content);
							}
						}
						content = content+kongge;
						
						
						
						
						System.out.println("模板(替换后)="+content);
//						if (list.get(sNum).getXm().length()>3) {
//							content = content.substring(0, content.length()-(list.get(sNum).getXm().length()-3));
//							System.out.println("截取的content="+content);
//						}
						sendContenttemp += content;
						
						//if(null != led_TVlist.get(i).getWaitingArea() && null != businessList.get(0).getWaitingarea()){
						//System.out.println("businessList.get(0).getWaitingarea()="+businessList.get(0).getWaitingarea());
//						sendContenttemp = sendLzxxContent;
						//System.out.println("sendContenttemp="+sendContenttemp);
						//总字符长度
						int contentLength =0;
						//拼接字符串的字符长度
						int len = sendContenttemp.replaceAll("[^\\x00-\\xff]", "**").length();
						if(null != led_TVlist.get(i).getContent()){
							contentLength = Integer.parseInt(led_TVlist.get(i).getWidth())*Integer.parseInt(led_TVlist.get(i).getHeight())/((Integer.parseInt(led_TVlist.get(i).getLattice())/2)*(Integer.parseInt(led_TVlist.get(i).getLattice())/2))/2;
//								(led_TVlist.get(i).getContent()+kongge).replaceAll("[^\\x00-\\xff]", "**").length() * Integer.parseInt(led_TVlist.get(i).getLattice()) /2;
						}
						//行数(高度/点阵数)
						int hangshu = Integer.parseInt(led_TVlist.get(i).getHeight())/Integer.parseInt(led_TVlist.get(i).getLattice());
						//列数(屏宽/字符长度)
//						int lieshu = (int) Math.floor(Integer.parseInt(led_TVlist.get(i).getWidth())/contentLength);
						int lieshu = Integer.parseInt(led_TVlist.get(i).getWidth())*2/Integer.parseInt(led_TVlist.get(i).getLattice())/(content).replaceAll("[^\\x00-\\xff]", "**").length();
						System.out.println("当前字符长度="+len);
						System.out.println("列数="+lieshu);
						System.out.println("行数="+hangshu);
						System.out.println("宽="+Integer.parseInt(led_TVlist.get(i).getWidth())+"长="+Integer.parseInt(led_TVlist.get(i).getHeight())+""+"总长度="+contentLength);
						System.out.println("未替换当前字符长度="+sendContenttemp.length());
						System.out.println("未替换总字符长度="+((content).length()*hangshu*lieshu));
//						if("0".equals(ledQueueShow)){
//							sendContenttemp+=kongge+" @";
//						}else{
//							sendContenttemp+=kongge+"@";
//						}
						//System.out.println("sendContenttemp="+sendContenttemp);
						
						//判断内容是否超过总长度
						if(len  >  contentLength){
							System.out.println("开始字符="+(sendContenttemp.length()-(content).length()*hangshu*lieshu));
							System.out.println("结束字符="+sendContenttemp.length());
							String[] result = sendContenttemp.split("到");
							System.out.println(sendContenttemp.length());
							System.out.println(result[0].length()+mubanchangdu);
							int jiequkaishi = sendContenttemp.length()-(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
//							sendContenttemp = sendContenttemp.substring(sendContenttemp.length()-(content).length()*hangshu*lieshu,sendContenttemp.length());
							sendContenttemp = sendContenttemp.substring(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()),sendContenttemp.length());	
//							sendContenttemp = sendContenttemp.substring(len-contentLength,len);
						}
						
						if("".equals(sendContenttemp)||null==sendContenttemp){
							LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow," ");
						}else{
							LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow,sendContenttemp);
							System.out.println("领证屏结果="+sendContenttemp);
						}
						
					}
					LzckAction.sendLzxxContent = sendContenttemp ;
//				System.out.println(sendLzxxContent);
//				lzxxService.updateStatus(list.get(k));
					Thread.sleep(2000);
					
//				}
			}else{
//				for (int k = 0; k < list.size(); k++) {
					
					List<Lzxx> listLzck = lzxxService.queryLzckByZllx(list.get(sNum));
						for (int j = 0; j < listLzck.size(); j++) {
							Lzxx lzxxVo = listLzck.get(j);
							if(10>Integer.parseInt(lzxxVo.getBarid())){
								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
							}
						}	
						System.out.println("姓名="+list.get(sNum).getXm());
						System.out.println("窗口号="+list.get(sNum).getLzckh());
						String datas = "";
						if (!"".equals(list.get(sNum).getLzckh()) && list.get(sNum).getLzckh() != null) {
							datas = list.get(sNum).getXm()+"@"+list.get(sNum).getLzckh()+"@"+"0"+"@"+list.get(sNum).getLsh();
						}else {
							datas = list.get(sNum).getXm()+"@"+listLzck.get(0).getBarid()+"@"+"0"+"@"+list.get(sNum).getLsh();
						}
						System.out.println(datas);
						publisher.publish(new passLzxx(datas));
						Thread.sleep(2000);
//					}
				}
			LzckAction.sendNum++;
			System.out.println("LzckAction.sendNum="+LzckAction.sendNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束自动发屏");
	
		
		return null;
	}
	
}
