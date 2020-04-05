package com.suntendy.queue.lzxx.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.lzxx.domain.Lzxx;
import com.suntendy.queue.lzxx.service.ILzxxService;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.util.cache.VoiceManager;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.led.LEDUtils;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.passLzxx;
import com.suntendy.queue.util.trff.TrffClient;
import com.suntendy.queue.util.trff.XMLUtils;
import com.suntendy.queue.window.domain.Screen;

public class LzckAction extends BaseAction {
	
	private ILzxxService lzxxService;
	private LedService ledService;
	private Publisher publisher;
	private ICodeService codeService;
	private INumberService numberService;

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	public String queryAllLzck() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxx = new Lzxx();
		lzxx.setDeptCode(deptCode);
		lzxx.setDeptHall(deptHall);
		List<Lzxx> list = lzxxService.queryLzckByZllx(lzxx);
		List<Code> listCode = new ArrayList<Code>();
		for (int i = 0; i < list.size(); i++) {
			Lzxx lzxxVo = list.get(i);
			if ("".equals(lzxxVo.getBarid()) || lzxxVo.getBarid()== null) {
				if ("01".equals(lzxxVo.getZllx().substring(0,2))) {
					String operate ="<a onclick=delpzlz('" + lzxxVo.getZllx()
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
					lzxxVo.setOperation(operate);
					listCode = codeService.getCodeByDmlbAndDmz("0060","","",deptCode,deptHall);
					for (int j = 0; j < listCode.size(); j++) {
						if (listCode.get(j).getDm().equals(lzxxVo.getZllx().substring(2,3))) {
							lzxxVo.setZllx("机动车-"+listCode.get(j).getMc()+"-"+lzxxVo.getZllx().substring(3));
						}
					}
				}
				if ("02".equals(lzxxVo.getZllx().substring(0,2))) {
					String operate ="<a onclick=delpzlz('" + lzxxVo.getZllx()
					+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
					lzxxVo.setOperation(operate);
					listCode = codeService.getCodeByDmlbAndDmz("0008","","",deptCode,deptHall);
					for (int j = 0; j < listCode.size(); j++) {
						if (listCode.get(j).getDm().equals(lzxxVo.getZllx().substring(2,3))) {
							lzxxVo.setZllx("驾驶证-"+listCode.get(j).getMc()+"-"+lzxxVo.getZllx().substring(3));
						}
					}
				}
			}else {
				String operate ="<a onclick=delLzck('" + lzxxVo.getZllx()
				+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
				lzxxVo.setOperation(operate);
				if ("01".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("驾驶证");
				}
				if ("02".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("行驶证");
				}
				if ("03".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("登记证书");
				}
				if ("04".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("号牌");
				}
				if ("05".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("临时号牌");
				}
				if ("06".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("检验合格标志");
				}
				if ("07".equals(lzxxVo.getZllx())) {
					lzxxVo.setZllx("人工输入");
				}
			}
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	public String delLzck(){
		HttpServletRequest request = getRequest();
		Lzxx lzxx = new Lzxx();
		String zllx = request.getParameter("zllx");
		System.out.println(zllx);
		lzxx.setZllx(zllx);
		try {
			lzxxService.delLzck(lzxx);
			request.setAttribute("msg", "领证窗口删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证窗口删除失败，<br>在执行过程中发生异常！");
		}
			request.setAttribute("action", "lzxx/queryAllLzck.action");
		return "delLzck";
	}
	
	public String delpzlz(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxx = new Lzxx();
		String zllx = request.getParameter("zllx");
		System.out.println(zllx);
		lzxx.setZllx(zllx);
		try {
			//调用领证信息查询接口
			System.out.println("进入"+zllx+"领证删除接口");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
//			rows[0][1] = "131000000400#01#C#10.35.115.137";
			rows[0][1] = deptCode+"#"+zllx.substring(0,2)+"#"+zllx.substring(2,3)+"#"+zllx.substring(3);
			rows[1][0] = "zllx";
			rows[1][1] = "10";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
//			String lzXML = "<?xml version="+"\"1.0\""+" encoding="+"\"GBK\""+"?><root><head><code>1</code><message></message></head></root>";
			System.out.println("返回10接口XML="+lzXML);
			String result = new String();
			if (lzXML.length()>0) {
				Document document = DocumentHelper.parseText(lzXML);
				Element root = document.getRootElement();
				result = ((Node)root.selectNodes("//code").get(0)).getText();
				if ("1".equals(result)) {
					try {
						lzxxService.delLzck(lzxx);
						request.setAttribute("msg", "配置领证信息删除成功！");
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("msg", "配置领证信息删除失败，<br>在执行过程中发生异常！");
					}
				}else {
					request.setAttribute("msg", "配置领证信息删除失败，<br>在执行过程中发生异常！");
				}
			}
		} catch (Exception e) {
			System.out.println("配置领证信息删除失败");
			e.printStackTrace();
		}
			request.setAttribute("action", "lzxx/queryAllLzck.action");
		return "delpzlz";
	}

	public String addLzck(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxx = new Lzxx();
		Lzxx lzxxVO = new Lzxx();
		String zllx = request.getParameter("zllx");
		String dmsm = request.getParameter("dmsm");
		String ip = request.getParameter("ip");
		String barid = request.getParameter("barid");
		if ("".equals(barid) || barid == null) {
			if ("0060".equals(zllx)) {
				zllx = "01"+dmsm+ip;
			}else if ("0008".equals(zllx)) {
				zllx = "02"+dmsm+ip;
			}
		}
		lzxx.setZllx(zllx);
		lzxx.setBarid(barid);
		lzxx.setIp(ip);
		lzxx.setDeptCode(deptCode);
		lzxx.setDeptHall(deptHall);
		if ("".equals(barid) || barid == null) {
			if (zllx.substring(3).split(",").length >0) {
				for (int i = 0; i < zllx.substring(3).split(",").length; i++) {
					try {
						//调用领证信息查询接口
						System.out.println("进入"+zllx.substring(0,3) + zllx.substring(3).split(",")[i]+"领证接口");
						String rows[][] = new String[2][2];
						rows[0][0] = "lsh";
						//			rows[0][1] = "131000000400#01#C#10.35.115.137";
						rows[0][1] = deptCode+"#"+zllx.substring(0,2)+"#"+zllx.substring(2,3)+"#"+zllx.substring(3).split(",")[i];
						rows[1][0] = "zllx";
						rows[1][1] = "9";
						String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
						String lzXML = TrffClient.write_nbjk("02C43", strXML);
//				String lzXML = "<?xml version="+"\"1.0\""+" encoding="+"\"GBK\""+"?><root><head><code>1</code><message></message></head></root>";
						System.out.println("返回9接口XML="+lzXML);
						String result = new String();
						if (lzXML.length()>0) {
							Document document = DocumentHelper.parseText(lzXML);
							Element root = document.getRootElement();
							result = ((Node)root.selectNodes("//code").get(0)).getText();
							if ("1".equals(result)) {
								try {
									String zllxVO = new String();
									String ipVO = new String();
									zllxVO = zllx.substring(0,3) + zllx.substring(3).split(",")[i];
									ipVO = zllx.substring(3).split(",")[i];
									lzxx.setZllx(zllxVO);
									lzxx.setIp(ipVO);
									lzxxService.insertLzck(lzxx);
									System.out.println("调用完成");
									request.setAttribute("msg", "配置领证信息添加成功！");
								} catch (Exception e) {
									e.printStackTrace();
									request.setAttribute("msg", "配置领证信息添加添加失败，<br>在执行过程中发生异常！");
								}
							}else {
								request.setAttribute("msg", "配置领证信息添加失败，<br>在执行过程中发生异常！");
							}
						}
					} catch (Exception e) {
						System.out.println("领证接口(注册登记)调用失败");
						e.printStackTrace();
					}
				}
			}
		}else {
			try {
				lzxxService.insertLzck(lzxx);
				request.setAttribute("msg", "领证窗口添加成功！");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证窗口添加失败，<br>在执行过程中发生异常！");
			}
		}
		request.setAttribute("action", "lzxx/queryAllLzck.action");
		return "addLzck";
	}
	
	public String queryAllLzxx() throws Exception{
		HttpServletRequest request = getRequest();
		Lzxx lzxx = new Lzxx();
		CacheManager cacheManager = CacheManager.getInstance();
		String xm = StringUtils.trimToEmpty(request.getParameter("xm"));
		String zllx = StringUtils.trimToEmpty(request.getParameter("zllx"));
		String rksj = StringUtils.trimToEmpty(request.getParameter("rksj"));
		String lsh = StringUtils.trimToEmpty(request.getParameter("lsh"));
		String idnumber = StringUtils.trimToEmpty(request.getParameter("idnumber"));
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		lzxx.setXm(xm);
		lzxx.setZllx(zllx);
		lzxx.setRksj(rksj);
		lzxx.setLsh(lsh);
		lzxx.setIdnumber(idnumber);
		List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
		for (int i = 0; i < listLzxx.size(); i++) {
			Lzxx lzxxVo = listLzxx.get(i);
			String id = lzxxVo.getLsh();
//			String operate ="<a onclick=sendLzxx('" + id
//			+ "')><img src='/queue/images/button_del.jpg' style='cursor:hand'/></a>";
			String operate ="<input type='button' class='button' value='发声' onclick='voicelzxx("+id+")'>"+"&nbsp;&nbsp;"+"<input type='button' class='button' value='已领' onclick='updatelzxx("+id+")'>"+"&nbsp;&nbsp;"+"<input type='button' class='button' value='上屏' onclick='sendLzxx("+id+")'>";
			lzxxVo.setOperation(operate);
			if ("01".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("驾驶证");
			}else if ("02".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("行驶证");
			}else if ("03".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("登记证书");
			}else if ("04".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("号牌");
			}else if ("05".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("临时号牌");
			}else if ("06".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("检验合格标志");
			}else if ("07".equals(lzxxVo.getZllx())) {
				lzxxVo.setZllx("人工输入");
			}
		}
		request.setAttribute("list", listLzxx);
		return "list";
		
	}
	
	public String queryAllLzxxBysxh() throws Exception{
		HttpServletRequest request = getRequest();
		Lzxx lzxxVO = new Lzxx();
		CacheManager cacheManager = CacheManager.getInstance();
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		System.out.println("sxh ="+sxh);
		String clientIp = request.getParameter("clientIp");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		lzxxVO.setSxh(deptCode+deptHall+sxh);
		List<Lzxx> listLzxxVo = lzxxService.queryAllLzxxBysxh(lzxxVO);
		if (listLzxxVo.size() >0) {
			Lzxx lzxx = new Lzxx();
			String lsh = listLzxxVo.get(0).getLsh();

			String lsxm = request.getParameter("lsxm");
			System.out.println("clientIp="+clientIp);
			System.out.println("lsxm="+lsxm);
			Screen screen = new Screen();
			screen.setBarip(clientIp);
			lzxx.setLsh(lsh);
			int s = new Random().nextInt(9999999)%(9999999-1001)+1000;
			String lzckh = "";
			try {
				if("0".equals(cacheManager.getSystemConfig("isOpenInformation"))){//判断是否领证发屏
					if ("0".equals(cacheManager.getSystemConfig("lzxxSendMode"))) {
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
							sendContenttemp = sendLzxxContent;
							
							String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
							//窗口LED屏号
							List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
							List<Lzxx> listLzck =new ArrayList<Lzxx>();
							List<Screen> listScreen = new ArrayList<Screen>();
							String content = led_TVlist.get(i).getContent();
							int mubanchangdu = content.length();
							System.out.println("模板="+content+"····模板长度="+mubanchangdu);
							//个位数窗口屏补位
//				if (listLzxx.size()>0) {
//					listLzxx.get(0).setDeptHall(deptHall);
//					listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
							listScreen = lzxxService.querybarid(screen);
							
							for (int j = 0; j < listScreen.size(); j++) {
								Screen screenVO = listScreen.get(j);
								if(10>Integer.parseInt(screenVO.getWindowId())){
									screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
								}
								
							}
							
//				}
//				sendLzxxContent += listLzxx.get(0).getXm()+"到"+listLzck.get(0).getBarid()+"号窗口领证 ";
							//空格数量转换空格
							String kongge = "";
							if(null!=led_TVlist.get(i).getSpace() && !"".equals(led_TVlist.get(i).getSpace())&& !"0".equals(led_TVlist.get(i).getSpace())){
								for(int x=0;x<Integer.parseInt(led_TVlist.get(i).getSpace());x++){
									kongge += " ";
								}
							}
							//模板替换
							Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
							String xm ="";
							if (null != lsxm && !"".equals(lsxm)) {
								xm = lsxm;
							}else if (listLzxx.size()>0) {
								xm = listLzxx.get(0).getXm();
							}
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
								if (listScreen.size()>0) {
									content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
									lzckh = listScreen.get(0).getWindowId();
								}else {
									content = content.replaceAll("@", xm).replaceAll("#", "  ");
									lzckh = "  ";
								}
								if (xm.length()>3) {
									content = content.substring(0, content.length()-(xm.length()-3));
									System.out.println("截取的content="+content);
								}
								
							}else{
								System.out.println("进入英文xm="+xm);
								if (xm.length()<=6) {
									int changdu = 6-xm.length();
									for (int mm = 0; mm < changdu; mm++) {
										xm += " ";
									}
								}else if (xm.length()>6) {
									System.out.println("截取");
									xm = xm.substring(xm.length()-6,xm.length());
									System.out.println(xm);
								}
								if (xm.length()>6 && xm.length()%2==1) {
									xm +=" ";
								}
								if (listScreen.size()>0) {
									content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
									lzckh = listScreen.get(0).getWindowId();
								}else {
									content = content.replaceAll("@", xm).replaceAll("#", "  ");
									lzckh = "  ";
								}
								if (xm.length()>6 && xm.length()%2==0) {
									content = content.substring(0, content.length()-(xm.length()-6)/2);
									System.out.println("截取的content="+content);
								}
							}
//				content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid())+kongge;
//				if (listLzxx.get(0).getXm().length()>3) {
//					content = content.substring(0, content.length()-(listLzxx.get(0).getXm().length()-3));
//					System.out.println("截取的content="+content);
//				}
							content = content+kongge;
							System.out.println("模板(替换后)="+content);
							sendContenttemp += content;
							
							//if(null != led_TVlist.get(i).getWaitingArea() && null != businessList.get(0).getWaitingarea()){
							//System.out.println("businessList.get(0).getWaitingarea()="+businessList.get(0).getWaitingarea());
//							sendContenttemp = sendLzxxContent;
							//System.out.println("sendContenttemp="+sendContenttemp);
							//总字符长度
							int contentLength =0;
							//拼接字符串的字符长度
							int len = sendContenttemp.replaceAll("[^\\x00-\\xff]", "**").length();
							if(null != led_TVlist.get(i).getContent()){
								contentLength = Integer.parseInt(led_TVlist.get(i).getWidth())*Integer.parseInt(led_TVlist.get(i).getHeight())/((Integer.parseInt(led_TVlist.get(i).getLattice())/2)*(Integer.parseInt(led_TVlist.get(i).getLattice())/2))/2;
//									(led_TVlist.get(i).getContent()+kongge).replaceAll("[^\\x00-\\xff]", "**").length() * Integer.parseInt(led_TVlist.get(i).getLattice()) /2;
							}
							//行数(高度/点阵数)
							int hangshu = Integer.parseInt(led_TVlist.get(i).getHeight())/Integer.parseInt(led_TVlist.get(i).getLattice());
							//列数(屏宽/字符长度)
//							int lieshu = (int) Math.floor(Integer.parseInt(led_TVlist.get(i).getWidth())/contentLength);
							int lieshu = Integer.parseInt(led_TVlist.get(i).getWidth())*2/Integer.parseInt(led_TVlist.get(i).getLattice())/(content).replaceAll("[^\\x00-\\xff]", "**").length();
							System.out.println("全部转换*号后字符串的长度="+len);
							System.out.println("列数="+lieshu);
							System.out.println("行数="+hangshu);
							System.out.println("宽="+Integer.parseInt(led_TVlist.get(i).getWidth())+"长="+Integer.parseInt(led_TVlist.get(i).getHeight())+""+"总长度="+contentLength);
							System.out.println("未替换当前字符长度="+sendContenttemp.length());
							System.out.println("未替换总字符长度="+((content).length()*hangshu*lieshu));
//							if("0".equals(ledQueueShow)){
//								sendContenttemp+=kongge+" @";
//							}else{
//								sendContenttemp+=kongge+"@";
//							}
							//System.out.println("sendContenttemp="+sendContenttemp);
							
							//判断内容是否超过总长度
							if(len  >  contentLength){
								System.out.println("开始字符="+(sendContenttemp.length()-(content).length()*hangshu*lieshu));
								System.out.println("结束字符="+sendContenttemp.length());
								String[] result = sendContenttemp.split("到");
								System.out.println(sendContenttemp.length());
								System.out.println(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
								int jiequkaishi = sendContenttemp.length()-(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
//									sendContenttemp = sendContenttemp.substring(sendContenttemp.length()-(content).length()*hangshu*lieshu,sendContenttemp.length());
								sendContenttemp = sendContenttemp.substring(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()),sendContenttemp.length());
//									sendContenttemp = sendContenttemp.substring(len-contentLength,len);
							}
							
							if("".equals(sendContenttemp)||null==sendContenttemp){
								LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow," ");
							}else{
								LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow,sendContenttemp);
								System.out.println("领证屏结果="+sendContenttemp);
							}
							Thread.sleep(100);	
						}
						sendLzxxContent = sendContenttemp ;
//					System.out.println(sendLzxxContent);
						
					}else{
						System.out.println("同步开始推送领证信息");
						List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
						List<Lzxx> listLzck = new ArrayList<Lzxx>();
						List<Screen> listScreen = new ArrayList<Screen>();
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
//						for (int j = 0; j < listLzck.size(); j++) {
//							Lzxx lzxxVo = listLzck.get(j);
//							if(10>Integer.parseInt(lzxxVo.getBarid())){
//								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
//							}
//							
//						}
//					
//					}
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
						listScreen = lzxxService.querybarid(screen);
						
						for (int j = 0; j < listScreen.size(); j++) {
							Screen screenVO = listScreen.get(j);
							if(10>Integer.parseInt(screenVO.getWindowId())){
								screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
							}
							
						}
						
//					}
						
						if (listScreen.size()>0) {
							lzckh = listScreen.get(0).getWindowId();
						}else {
							lzckh = "  ";
						}
						
						String xm ="";
						if (null != lsxm && !"".equals(lsxm)) {
							xm = lsxm;
						}else {
							xm = listLzxx.get(0).getXm();
						}
						String datas = "";
						if (null != lsxm && !"".equals(lsxm)) {
							datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+s;
						}else{
							datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+listLzxx.get(0).getLsh();
						}
						System.out.println("datas="+datas);
						publisher.publish(new passLzxx(datas));
						
					}
				}
//			lzxxService.updateStatus(lzxx);
				request.setAttribute("msg", "流水号:"+lsh+"已上屏!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息上传失败，<br>在执行过程中发生异常！");
			}
			lzxx.setStatus("3");
			lzxx.setDeptHall(deptHall);
			lzxx.setLzckh(lzckh);
			try {
				lzxxService.updateStatus(lzxx);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息更新状态3失败，<br>在执行过程中发生异常！");
			}

			lsh = listLzxxVo.get(0).getLsh();
			Lzxx lzxxVVO = new Lzxx();
			lzxxVVO.setLsh(lsh);
			try {
				lzxxService.updateStatus(lzxxVVO);
				request.setAttribute("msg", "流水号:"+lsh+"已上屏!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息标记失败，<br>在执行过程中发生异常！");
			}
			
		}else {
			request.setAttribute("msg", "未找到 "+sxh+" 对应的证件信息!");
		}
		request.setAttribute("action", "lzxx/queryAllLzxx.action");
		return "sendLzxx";
	}
	public String querynameLzxxBysxh() throws Exception{
		HttpServletRequest request = getRequest();
		Lzxx lzxxVO = new Lzxx();
		Number number = new Number();
		CacheManager cacheManager = CacheManager.getInstance();
		String sxh = StringUtils.trimToEmpty(request.getParameter("sxh"));
		System.out.println("sxh ="+sxh);
		String clientIp = request.getParameter("clientIp");
		String fsxm = null;
		fsxm = request.getParameter("fsxm");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		lzxxVO.setSxh(deptCode+deptHall+sxh);
		number.setDeptCode(deptCode);
		number.setDeptHall(deptHall);
		number.setClientno(deptCode+deptHall+sxh);
		List<Number> listNumber =numberService.getNameBySXH(number);
//		List<Lzxx> listLzxxVo = lzxxService.queryAllLzxxBysxh(lzxxVO);
		if (listNumber.size() >0) {
//			Lzxx lzxx = new Lzxx();
//			String lsh = listLzxxVo.get(0).getLsh();

			String lsxm = request.getParameter("lsxm");
			System.out.println("clientIp="+clientIp);
			System.out.println("lsxm="+lsxm);
			Screen screen = new Screen();
			screen.setBarip(clientIp);
//			lzxx.setLsh(lsh);
			int s = new Random().nextInt(9999999)%(9999999-1001)+1000;
			String lzckh = "";
			try {
				if("0".equals(cacheManager.getSystemConfig("isOpenInformation"))){//判断是否领证发屏
					if ("0".equals(cacheManager.getSystemConfig("lzxxSendMode"))) {
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
							sendContenttemp = sendLzxxContent;
							
							String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
							//窗口LED屏号
//							List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
							List<Lzxx> listLzck =new ArrayList<Lzxx>();
							List<Screen> listScreen = new ArrayList<Screen>();
							String content = led_TVlist.get(i).getContent();
							int mubanchangdu = content.length();
							System.out.println("模板="+content+"····模板长度="+mubanchangdu);
							//个位数窗口屏补位
//				if (listLzxx.size()>0) {
//					listLzxx.get(0).setDeptHall(deptHall);
//					listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
							listScreen = lzxxService.querybarid(screen);
							
							for (int j = 0; j < listScreen.size(); j++) {
								Screen screenVO = listScreen.get(j);
								if(10>Integer.parseInt(screenVO.getWindowId())){
									screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
								}
								
							}
							
//				}
//				sendLzxxContent += listLzxx.get(0).getXm()+"到"+listLzck.get(0).getBarid()+"号窗口领证 ";
							//空格数量转换空格
							String kongge = "";
							if(null!=led_TVlist.get(i).getSpace() && !"".equals(led_TVlist.get(i).getSpace())&& !"0".equals(led_TVlist.get(i).getSpace())){
								for(int x=0;x<Integer.parseInt(led_TVlist.get(i).getSpace());x++){
									kongge += " ";
								}
							}
							//模板替换
							Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
							String xm ="";
							if (null != lsxm && !"".equals(lsxm)) {
								xm = lsxm;
							}else if (listNumber.size()>0) {
								
								if (!"".equals(listNumber.get(0).getNameB()) && null != listNumber.get(0).getNameB()) {
									xm = listNumber.get(0).getNameB();
								}else if (!"".equals(listNumber.get(0).getNameA()) && null != listNumber.get(0).getNameA()) {
									xm = listNumber.get(0).getNameA();
								}else if (!"".equals(listNumber.get(0).getIDNumberB()) && null != listNumber.get(0).getIDNumberB()) {
									xm = listNumber.get(0).getIDNumberB();
								}else {
									xm = listNumber.get(0).getIDNumber();
								}
							}else {
								xm = sxh;
							}
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
								if (listScreen.size()>0) {
									content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
									lzckh = listScreen.get(0).getWindowId();
								}else {
									content = content.replaceAll("@", xm).replaceAll("#", "  ");
									lzckh = "  ";
								}
								if (xm.length()>3) {
									content = content.substring(0, content.length()-(xm.length()-3));
									System.out.println("截取的content="+content);
								}
								
							}else{
								System.out.println("进入英文xm="+xm);
								if (xm.length()<=6) {
									int changdu = 6-xm.length();
									for (int mm = 0; mm < changdu; mm++) {
										xm += " ";
									}
								}else if (xm.length()>6) {
									System.out.println("截取");
									xm = xm.substring(xm.length()-6,xm.length());
									System.out.println(xm);
								}
								if (xm.length()>6 && xm.length()%2==1) {
									xm +=" ";
								}
								if (listScreen.size()>0) {
									content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
									lzckh = listScreen.get(0).getWindowId();
								}else {
									content = content.replaceAll("@", xm).replaceAll("#", "  ");
									lzckh = "  ";
								}
								if (xm.length()>6 && xm.length()%2==0) {
									content = content.substring(0, content.length()-(xm.length()-6)/2);
									System.out.println("截取的content="+content);
								}
							}
//				content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid())+kongge;
//				if (listLzxx.get(0).getXm().length()>3) {
//					content = content.substring(0, content.length()-(listLzxx.get(0).getXm().length()-3));
//					System.out.println("截取的content="+content);
//				}
							content = content+kongge;
							System.out.println("模板(替换后)="+content);
							sendContenttemp += content;
							
							//if(null != led_TVlist.get(i).getWaitingArea() && null != businessList.get(0).getWaitingarea()){
							//System.out.println("businessList.get(0).getWaitingarea()="+businessList.get(0).getWaitingarea());
//							sendContenttemp = sendLzxxContent;
							//System.out.println("sendContenttemp="+sendContenttemp);
							//总字符长度
							int contentLength =0;
							//拼接字符串的字符长度
							int len = sendContenttemp.replaceAll("[^\\x00-\\xff]", "**").length();
							if(null != led_TVlist.get(i).getContent()){
								contentLength = Integer.parseInt(led_TVlist.get(i).getWidth())*Integer.parseInt(led_TVlist.get(i).getHeight())/((Integer.parseInt(led_TVlist.get(i).getLattice())/2)*(Integer.parseInt(led_TVlist.get(i).getLattice())/2))/2;
//									(led_TVlist.get(i).getContent()+kongge).replaceAll("[^\\x00-\\xff]", "**").length() * Integer.parseInt(led_TVlist.get(i).getLattice()) /2;
							}
							//行数(高度/点阵数)
							int hangshu = Integer.parseInt(led_TVlist.get(i).getHeight())/Integer.parseInt(led_TVlist.get(i).getLattice());
							//列数(屏宽/字符长度)
//							int lieshu = (int) Math.floor(Integer.parseInt(led_TVlist.get(i).getWidth())/contentLength);
							int lieshu = Integer.parseInt(led_TVlist.get(i).getWidth())*2/Integer.parseInt(led_TVlist.get(i).getLattice())/(content).replaceAll("[^\\x00-\\xff]", "**").length();
							System.out.println("全部转换*号后字符串的长度="+len);
							System.out.println("列数="+lieshu);
							System.out.println("行数="+hangshu);
							System.out.println("宽="+Integer.parseInt(led_TVlist.get(i).getWidth())+"长="+Integer.parseInt(led_TVlist.get(i).getHeight())+""+"总长度="+contentLength);
							System.out.println("未替换当前字符长度="+sendContenttemp.length());
							System.out.println("未替换总字符长度="+((content).length()*hangshu*lieshu));
//							if("0".equals(ledQueueShow)){
//								sendContenttemp+=kongge+" @";
//							}else{
//								sendContenttemp+=kongge+"@";
//							}
							//System.out.println("sendContenttemp="+sendContenttemp);
							
							//判断内容是否超过总长度
							if(len  >  contentLength){
								System.out.println("开始字符="+(sendContenttemp.length()-(content).length()*hangshu*lieshu));
								System.out.println("结束字符="+sendContenttemp.length());
								String[] result = sendContenttemp.split("到");
								System.out.println(sendContenttemp.length());
								System.out.println(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
								int jiequkaishi = sendContenttemp.length()-(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
//									sendContenttemp = sendContenttemp.substring(sendContenttemp.length()-(content).length()*hangshu*lieshu,sendContenttemp.length());
								sendContenttemp = sendContenttemp.substring(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()),sendContenttemp.length());
//									sendContenttemp = sendContenttemp.substring(len-contentLength,len);
							}
							
							if("".equals(sendContenttemp)||null==sendContenttemp){
								LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow," ");
							}else{
								LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow,sendContenttemp);
								System.out.println("领证屏结果="+sendContenttemp);
							}
							Thread.sleep(100);	
						}
						sendLzxxContent = sendContenttemp ;
//					System.out.println(sendLzxxContent);
						
					}else{
						System.out.println("同步开始推送领证信息");
//						List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
						List<Lzxx> listLzck = new ArrayList<Lzxx>();
						List<Screen> listScreen = new ArrayList<Screen>();
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
//						for (int j = 0; j < listLzck.size(); j++) {
//							Lzxx lzxxVo = listLzck.get(j);
//							if(10>Integer.parseInt(lzxxVo.getBarid())){
//								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
//							}
//							
//						}
//					
//					}
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
						listScreen = lzxxService.querybarid(screen);
						
						for (int j = 0; j < listScreen.size(); j++) {
							Screen screenVO = listScreen.get(j);
							if(10>Integer.parseInt(screenVO.getWindowId())){
								screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
							}
							
						}
						
//					}
						
						if (listScreen.size()>0) {
							lzckh = listScreen.get(0).getWindowId();
						}else {
							lzckh = "  ";
						}
						
						String xm ="";
						if (null != lsxm && !"".equals(lsxm)) {
							xm = lsxm;
						}else if (listNumber.size() >0) {
							if (!"".equals(listNumber.get(0).getNameB()) && null != listNumber.get(0).getNameB()) {
								xm = listNumber.get(0).getNameB();
							}else if (!"".equals(listNumber.get(0).getNameA()) && null != listNumber.get(0).getNameA()) {
								xm = listNumber.get(0).getNameA();
							}else if (!"".equals(listNumber.get(0).getIDNumberB()) && null != listNumber.get(0).getIDNumberB()) {
								xm = listNumber.get(0).getIDNumberB();
							}else {
								xm = listNumber.get(0).getIDNumber();
							}
						}else {
							xm = sxh;
						}
						String datas = "";
						if (null != lsxm && !"".equals(lsxm)) {
							datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+s;
						}else{
							datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+listNumber.get(0).getClientno();
						}
						System.out.println("datas="+datas);
						publisher.publish(new passLzxx(datas));
						
					}
				}
//			lzxxService.updateStatus(lzxx);
				request.setAttribute("msg", "已上屏!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息上传失败，<br>在执行过程中发生异常！");
			}
//			lzxx.setStatus("3");
//			lzxx.setDeptHall(deptHall);
//			lzxx.setLzckh(lzckh);
//			try {
//				lzxxService.updateStatus(lzxx);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "领证信息更新状态3失败，<br>在执行过程中发生异常！");
//			}
//
//			lsh = listLzxxVo.get(0).getLsh();
//			Lzxx lzxxVVO = new Lzxx();
//			lzxxVVO.setLsh(lsh);
//			try {
//				lzxxService.updateStatus(lzxxVVO);
//				request.setAttribute("msg", "流水号:"+lsh+"已上屏!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "领证信息标记失败，<br>在执行过程中发生异常！");
//			}
			
		}else {
			request.setAttribute("msg", "未找到顺序号对应的信息!");
		}
		request.setAttribute("action", "lzxx/lzxxList_nanning.jsp");
		return "sendLzxx";
	}
	
	public static String sendLzxxContent = "";
	public static int sendNum = 0;
	public String sendLzxx(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxx = new Lzxx();
		String lsh = request.getParameter("lsh");
		String clientIp = request.getParameter("clientIp");
		String lsxm = request.getParameter("lsxm");
		System.out.println("clientIp="+clientIp);
		System.out.println("lsxm="+lsxm);
		Screen screen = new Screen();
		screen.setBarip(clientIp);
		lzxx.setLsh(lsh);
		int s = new Random().nextInt(9999999)%(9999999-1001)+1000;
		String lzckh = "";
		try {
			if("0".equals(cacheManager.getSystemConfig("isOpenInformation"))){//判断是否领证发屏
				if ("0".equals(cacheManager.getSystemConfig("lzxxSendMode"))) {
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
					sendContenttemp = sendLzxxContent;
				
				String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
				//窗口LED屏号
				List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
				List<Lzxx> listLzck =new ArrayList<Lzxx>();
				List<Screen> listScreen = new ArrayList<Screen>();
				String content = led_TVlist.get(i).getContent();
				int mubanchangdu = content.length();
				System.out.println("模板="+content+"····模板长度="+mubanchangdu);
				//个位数窗口屏补位
//				if (listLzxx.size()>0) {
//					listLzxx.get(0).setDeptHall(deptHall);
//					listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
					listScreen = lzxxService.querybarid(screen);
					
					for (int j = 0; j < listScreen.size(); j++) {
						Screen screenVO = listScreen.get(j);
						if(10>Integer.parseInt(screenVO.getWindowId())){
							screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
						}
						
					}
				
//				}
//				sendLzxxContent += listLzxx.get(0).getXm()+"到"+listLzck.get(0).getBarid()+"号窗口领证 ";
				//空格数量转换空格
				String kongge = "";
				if(null!=led_TVlist.get(i).getSpace() && !"".equals(led_TVlist.get(i).getSpace())&& !"0".equals(led_TVlist.get(i).getSpace())){
					for(int x=0;x<Integer.parseInt(led_TVlist.get(i).getSpace());x++){
						kongge += " ";
					}
				}
				//模板替换
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				String xm ="";
				if (null != lsxm && !"".equals(lsxm)) {
						xm = lsxm;
				}else if (listLzxx.size()>0) {
					xm = listLzxx.get(0).getXm();
				}
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
					if (listScreen.size()>0) {
						content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
						lzckh = listScreen.get(0).getWindowId();
					}else {
						content = content.replaceAll("@", xm).replaceAll("#", "  ");
						lzckh = "  ";
					}
					if (xm.length()>3) {
						content = content.substring(0, content.length()-(xm.length()-3));
						System.out.println("截取的content="+content);
					}
					
				}else{
					System.out.println("进入英文xm="+xm);
					if (xm.length()<=6) {
						int changdu = 6-xm.length();
						for (int mm = 0; mm < changdu; mm++) {
							xm += " ";
						}
					}else if (xm.length()>6) {
						System.out.println("截取");
						xm = xm.substring(xm.length()-6,xm.length());
						System.out.println(xm);
					}
					if (xm.length()>6 && xm.length()%2==1) {
						xm +=" ";
					}
					if (listScreen.size()>0) {
						content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
						lzckh = listScreen.get(0).getWindowId();
					}else {
						content = content.replaceAll("@", xm).replaceAll("#", "  ");
						lzckh = "  ";
					}
					if (xm.length()>6 && xm.length()%2==0) {
						content = content.substring(0, content.length()-(xm.length()-6)/2);
						System.out.println("截取的content="+content);
					}
				}
//				content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid())+kongge;
//				if (listLzxx.get(0).getXm().length()>3) {
//					content = content.substring(0, content.length()-(listLzxx.get(0).getXm().length()-3));
//					System.out.println("截取的content="+content);
//				}
				content = content+kongge;
				System.out.println("模板(替换后)="+content);
				sendContenttemp += content;
				
						//if(null != led_TVlist.get(i).getWaitingArea() && null != businessList.get(0).getWaitingarea()){
							//System.out.println("businessList.get(0).getWaitingarea()="+businessList.get(0).getWaitingarea());
//							sendContenttemp = sendLzxxContent;
							//System.out.println("sendContenttemp="+sendContenttemp);
							//总字符长度
							int contentLength =0;
							//拼接字符串的字符长度
							int len = sendContenttemp.replaceAll("[^\\x00-\\xff]", "**").length();
							if(null != led_TVlist.get(i).getContent()){
								contentLength = Integer.parseInt(led_TVlist.get(i).getWidth())*Integer.parseInt(led_TVlist.get(i).getHeight())/((Integer.parseInt(led_TVlist.get(i).getLattice())/2)*(Integer.parseInt(led_TVlist.get(i).getLattice())/2))/2;
//									(led_TVlist.get(i).getContent()+kongge).replaceAll("[^\\x00-\\xff]", "**").length() * Integer.parseInt(led_TVlist.get(i).getLattice()) /2;
							}
							//行数(高度/点阵数)
							int hangshu = Integer.parseInt(led_TVlist.get(i).getHeight())/Integer.parseInt(led_TVlist.get(i).getLattice());
							//列数(屏宽/字符长度)
//							int lieshu = (int) Math.floor(Integer.parseInt(led_TVlist.get(i).getWidth())/contentLength);
							int lieshu = Integer.parseInt(led_TVlist.get(i).getWidth())*2/Integer.parseInt(led_TVlist.get(i).getLattice())/(content).replaceAll("[^\\x00-\\xff]", "**").length();
							System.out.println("全部转换*号后字符串的长度="+len);
							System.out.println("列数="+lieshu);
							System.out.println("行数="+hangshu);
							System.out.println("宽="+Integer.parseInt(led_TVlist.get(i).getWidth())+"长="+Integer.parseInt(led_TVlist.get(i).getHeight())+""+"总长度="+contentLength);
							System.out.println("未替换当前字符长度="+sendContenttemp.length());
							System.out.println("未替换总字符长度="+((content).length()*hangshu*lieshu));
//							if("0".equals(ledQueueShow)){
//								sendContenttemp+=kongge+" @";
//							}else{
//								sendContenttemp+=kongge+"@";
//							}
							//System.out.println("sendContenttemp="+sendContenttemp);
						
								//判断内容是否超过总长度
								if(len  >  contentLength){
									System.out.println("开始字符="+(sendContenttemp.length()-(content).length()*hangshu*lieshu));
									System.out.println("结束字符="+sendContenttemp.length());
									String[] result = sendContenttemp.split("到");
									System.out.println(sendContenttemp.length());
									System.out.println(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
									int jiequkaishi = sendContenttemp.length()-(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
//									sendContenttemp = sendContenttemp.substring(sendContenttemp.length()-(content).length()*hangshu*lieshu,sendContenttemp.length());
									sendContenttemp = sendContenttemp.substring(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()),sendContenttemp.length());
//									sendContenttemp = sendContenttemp.substring(len-contentLength,len);
								}
							
								if("".equals(sendContenttemp)||null==sendContenttemp){
									LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow," ");
								}else{
									LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow,sendContenttemp);
									System.out.println("领证屏结果="+sendContenttemp);
								}
								Thread.sleep(100);	
				        }
					sendLzxxContent = sendContenttemp ;
//					System.out.println(sendLzxxContent);
					
				}else{
					System.out.println("同步开始推送领证信息");
					List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
					List<Lzxx> listLzck = new ArrayList<Lzxx>();
					List<Screen> listScreen = new ArrayList<Screen>();
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
//						for (int j = 0; j < listLzck.size(); j++) {
//							Lzxx lzxxVo = listLzck.get(j);
//							if(10>Integer.parseInt(lzxxVo.getBarid())){
//								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
//							}
//							
//						}
//					
//					}
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
						listScreen = lzxxService.querybarid(screen);
						
						for (int j = 0; j < listScreen.size(); j++) {
							Screen screenVO = listScreen.get(j);
							if(10>Integer.parseInt(screenVO.getWindowId())){
								screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
							}
							
						}
					
//					}
						
						if (listScreen.size()>0) {
							lzckh = listScreen.get(0).getWindowId();
						}else {
							lzckh = "  ";
						}
						
					String xm ="";
					if (null != lsxm && !"".equals(lsxm)) {
							xm = lsxm;
					}else {
						xm = listLzxx.get(0).getXm();
					}
					String datas = "";
					if (null != lsxm && !"".equals(lsxm)) {
						datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+s;
					}else{
						datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+listLzxx.get(0).getLsh();
					}
					System.out.println("datas="+datas);
					publisher.publish(new passLzxx(datas));

				}
				}
//			lzxxService.updateStatus(lzxx);
			request.setAttribute("msg", "领证信息上传成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息上传失败，<br>在执行过程中发生异常！");
		}
		lzxx.setStatus("3");
		lzxx.setDeptHall(deptHall);
		lzxx.setLzckh(lzckh);
		try {
			lzxxService.updateStatus(lzxx);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息更新状态3失败，<br>在执行过程中发生异常！");
		}
		if (!"".equals(lsxm) && lsxm !=null) {
			lzxx.setXm(lsxm);
			lzxx.setZllx("07");
			lzxx.setLsh(s+"");
			lzxx.setIdnumber(lsxm);
			lzxx.setLzckh(lzckh);
			try {
				lzxxService.insertLzxxLS(lzxx);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息临时添加失败，<br>在执行过程中发生异常！");
			}
			
		}else {
			
		}
		request.setAttribute("action", "lzxx/queryAllLzxx.action");
		return "sendLzxx";
	}
	
	
	public String sendLzxx_new(String lsh,String clientIp){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxx = new Lzxx();
//		lsh = request.getParameter("lsh");
//		String clientIp = request.getParameter("clientIp");
		String lsxm = request.getParameter("lsxm");
		System.out.println("clientIp="+clientIp);
		System.out.println("lsxm="+lsxm);
		Screen screen = new Screen();
		screen.setBarip(clientIp);
		lzxx.setLsh(lsh);
		int s = new Random().nextInt(9999999)%(9999999-1001)+1000;
		String lzckh = "";
		try {
			if("0".equals(cacheManager.getSystemConfig("isOpenInformation"))){//判断是否领证发屏
				if ("0".equals(cacheManager.getSystemConfig("lzxxSendMode"))) {
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
					sendContenttemp = sendLzxxContent;
				
				String ledQueueShow = cacheManager.getSystemConfig("ledQueueShow");//获取发屏方式（纵向和横向）
				//窗口LED屏号
				List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
				List<Lzxx> listLzck =new ArrayList<Lzxx>();
				List<Screen> listScreen = new ArrayList<Screen>();
				String content = led_TVlist.get(i).getContent();
				int mubanchangdu = content.length();
				System.out.println("模板="+content+"····模板长度="+mubanchangdu);
				//个位数窗口屏补位
//				if (listLzxx.size()>0) {
//					listLzxx.get(0).setDeptHall(deptHall);
//					listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
					listScreen = lzxxService.querybarid(screen);
					
					for (int j = 0; j < listScreen.size(); j++) {
						Screen screenVO = listScreen.get(j);
						if(10>Integer.parseInt(screenVO.getWindowId())){
							screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
						}
						
					}
				
//				}
//				sendLzxxContent += listLzxx.get(0).getXm()+"到"+listLzck.get(0).getBarid()+"号窗口领证 ";
				//空格数量转换空格
				String kongge = "";
				if(null!=led_TVlist.get(i).getSpace() && !"".equals(led_TVlist.get(i).getSpace())&& !"0".equals(led_TVlist.get(i).getSpace())){
					for(int x=0;x<Integer.parseInt(led_TVlist.get(i).getSpace());x++){
						kongge += " ";
					}
				}
				//模板替换
				Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
				String xm ="";
				if (null != lsxm && !"".equals(lsxm)) {
						xm = lsxm;
				}else if (listLzxx.size()>0) {
					xm = listLzxx.get(0).getXm();
				}
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
					if (listScreen.size()>0) {
						content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
						lzckh = listScreen.get(0).getWindowId();
					}else {
						content = content.replaceAll("@", xm).replaceAll("#", "  ");
						lzckh = "  ";
					}
					if (xm.length()>3) {
						content = content.substring(0, content.length()-(xm.length()-3));
						System.out.println("截取的content="+content);
					}
					
				}else{
					System.out.println("进入英文xm="+xm);
					if (xm.length()<=6) {
						int changdu = 6-xm.length();
						for (int mm = 0; mm < changdu; mm++) {
							xm += " ";
						}
					}else if (xm.length()>6) {
						System.out.println("截取");
						xm = xm.substring(xm.length()-6,xm.length());
						System.out.println(xm);
					}
					if (xm.length()>6 && xm.length()%2==1) {
						xm +=" ";
					}
					if (listScreen.size()>0) {
						content = content.replaceAll("@", xm).replaceAll("#", listScreen.get(0).getWindowId());
						lzckh = listScreen.get(0).getWindowId();
					}else {
						content = content.replaceAll("@", xm).replaceAll("#", "  ");
						lzckh = "  ";
					}
					if (xm.length()>6 && xm.length()%2==0) {
						content = content.substring(0, content.length()-(xm.length()-6)/2);
						System.out.println("截取的content="+content);
					}
				}
//				content = content.replaceAll("@", xm).replaceAll("#", listLzck.get(0).getBarid())+kongge;
//				if (listLzxx.get(0).getXm().length()>3) {
//					content = content.substring(0, content.length()-(listLzxx.get(0).getXm().length()-3));
//					System.out.println("截取的content="+content);
//				}
				content = content+kongge;
				System.out.println("模板(替换后)="+content);
				sendContenttemp += content;
				
						//if(null != led_TVlist.get(i).getWaitingArea() && null != businessList.get(0).getWaitingarea()){
							//System.out.println("businessList.get(0).getWaitingarea()="+businessList.get(0).getWaitingarea());
//							sendContenttemp = sendLzxxContent;
							//System.out.println("sendContenttemp="+sendContenttemp);
							//总字符长度
							int contentLength =0;
							//拼接字符串的字符长度
							int len = sendContenttemp.replaceAll("[^\\x00-\\xff]", "**").length();
							if(null != led_TVlist.get(i).getContent()){
								contentLength = Integer.parseInt(led_TVlist.get(i).getWidth())*Integer.parseInt(led_TVlist.get(i).getHeight())/((Integer.parseInt(led_TVlist.get(i).getLattice())/2)*(Integer.parseInt(led_TVlist.get(i).getLattice())/2))/2;
//									(led_TVlist.get(i).getContent()+kongge).replaceAll("[^\\x00-\\xff]", "**").length() * Integer.parseInt(led_TVlist.get(i).getLattice()) /2;
							}
							//行数(高度/点阵数)
							int hangshu = Integer.parseInt(led_TVlist.get(i).getHeight())/Integer.parseInt(led_TVlist.get(i).getLattice());
							//列数(屏宽/字符长度)
//							int lieshu = (int) Math.floor(Integer.parseInt(led_TVlist.get(i).getWidth())/contentLength);
							int lieshu = Integer.parseInt(led_TVlist.get(i).getWidth())*2/Integer.parseInt(led_TVlist.get(i).getLattice())/(content).replaceAll("[^\\x00-\\xff]", "**").length();
							System.out.println("全部转换*号后字符串的长度="+len);
							System.out.println("列数="+lieshu);
							System.out.println("行数="+hangshu);
							System.out.println("宽="+Integer.parseInt(led_TVlist.get(i).getWidth())+"长="+Integer.parseInt(led_TVlist.get(i).getHeight())+""+"总长度="+contentLength);
							System.out.println("未替换当前字符长度="+sendContenttemp.length());
							System.out.println("未替换总字符长度="+((content).length()*hangshu*lieshu));
//							if("0".equals(ledQueueShow)){
//								sendContenttemp+=kongge+" @";
//							}else{
//								sendContenttemp+=kongge+"@";
//							}
							//System.out.println("sendContenttemp="+sendContenttemp);
						
								//判断内容是否超过总长度
								if(len  >  contentLength){
									System.out.println("开始字符="+(sendContenttemp.length()-(content).length()*hangshu*lieshu));
									System.out.println("结束字符="+sendContenttemp.length());
									String[] result = sendContenttemp.split("到");
									System.out.println(sendContenttemp.length());
									System.out.println(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
									int jiequkaishi = sendContenttemp.length()-(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()));
//									sendContenttemp = sendContenttemp.substring(sendContenttemp.length()-(content).length()*hangshu*lieshu,sendContenttemp.length());
									sendContenttemp = sendContenttemp.substring(result[0].length()+mubanchangdu+Integer.parseInt(led_TVlist.get(i).getSpace()),sendContenttemp.length());
//									sendContenttemp = sendContenttemp.substring(len-contentLength,len);
								}
							
								if("".equals(sendContenttemp)||null==sendContenttemp){
									LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow," ");
								}else{
									LEDUtils.sendLED_TVTextTY(led_TVlist.get(i), ledQueueShow,sendContenttemp);
									System.out.println("领证屏结果="+sendContenttemp);
								}
								Thread.sleep(100);	
				        }
					sendLzxxContent = sendContenttemp ;
//					System.out.println(sendLzxxContent);
					
				}else{
					System.out.println("同步开始推送领证信息");
					List<Lzxx> listLzxx = lzxxService.queryAllLzxx(lzxx);
					List<Lzxx> listLzck = new ArrayList<Lzxx>();
					List<Screen> listScreen = new ArrayList<Screen>();
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
//						for (int j = 0; j < listLzck.size(); j++) {
//							Lzxx lzxxVo = listLzck.get(j);
//							if(10>Integer.parseInt(lzxxVo.getBarid())){
//								lzxxVo.setBarid("0"+Integer.parseInt(lzxxVo.getBarid()));
//							}
//							
//						}
//					
//					}
//					if (listLzxx.size()>0) {
//						listLzxx.get(0).setDeptHall(deptHall);
//						listLzck = lzxxService.queryLzckByZllx(listLzxx.get(0));
						listScreen = lzxxService.querybarid(screen);
						
						for (int j = 0; j < listScreen.size(); j++) {
							Screen screenVO = listScreen.get(j);
							if(10>Integer.parseInt(screenVO.getWindowId())){
								screenVO.setWindowId("0"+Integer.parseInt(screenVO.getWindowId()));
							}
							
						}
					
//					}
						
						if (listScreen.size()>0) {
							lzckh = listScreen.get(0).getWindowId();
						}else {
							lzckh = "  ";
						}
						
					String xm ="";
					if (null != lsxm && !"".equals(lsxm)) {
							xm = lsxm;
					}else {
						xm = listLzxx.get(0).getXm();
					}
					String datas = "";
					if (null != lsxm && !"".equals(lsxm)) {
						datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+s;
					}else{
						datas = xm+"@"+listScreen.get(0).getWindowId()+"@"+"0"+"@"+listLzxx.get(0).getLsh();
					}
					System.out.println("datas="+datas);
					publisher.publish(new passLzxx(datas));

				}
				}
//			lzxxService.updateStatus(lzxx);
			request.setAttribute("msg", "领证信息上传成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息上传失败，<br>在执行过程中发生异常！");
		}
		lzxx.setStatus("3");
		lzxx.setDeptHall(deptHall);
		lzxx.setLzckh(lzckh);
		try {
			lzxxService.updateStatus(lzxx);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息更新状态3失败，<br>在执行过程中发生异常！");
		}
		if (!"".equals(lsxm) && lsxm !=null) {
			lzxx.setXm(lsxm);
			lzxx.setZllx("07");
			lzxx.setLsh(s+"");
			lzxx.setIdnumber(lsxm);
			lzxx.setLzckh(lzckh);
			try {
				lzxxService.insertLzxxLS(lzxx);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "领证信息临时添加失败，<br>在执行过程中发生异常！");
			}
			
		}else {
			
		}
		request.setAttribute("action", "lzxx/queryAllLzxx.action");
		return "sendLzxx";
	}
	
	

	public String updatelzxx(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String lsh = request.getParameter("lsh");
		Lzxx lzxx = new Lzxx();
		lzxx.setLsh(lsh);
		try {
			lzxxService.updateStatus(lzxx);
			String datas = " "+"@"+" "+"@"+"1"+"@"+lsh;
			System.out.println("datas="+datas);
			request.setAttribute("msg", "领证信息标记成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息标记失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "lzxx/queryAllLzxx.action");
		return "updatelzxx";
	}
	
	public String voicelzxx(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String lsh = request.getParameter("lsh");
		String clientIp = request.getParameter("clientIp");
		System.out.println("clientIp="+clientIp);
		Screen screen = new Screen();
		screen.setBarip(clientIp);
		Lzxx lzxx = new Lzxx();
		lzxx.setLsh(lsh);
		lzxx.setDeptHall(deptHall);
		List<Lzxx> lzxxlist = new ArrayList<Lzxx>();
		List<Screen> listScreen = new ArrayList<Screen>();
		try {
			lzxxlist = lzxxService.querySxhByLzxx(lzxx);
			listScreen = lzxxService.querybarid(screen);
			String datas = " "+"@"+" "+"@"+"1"+"@"+lsh;
			System.out.println("datas="+datas);
			String pattern = "yyyy-MM-dd HH:mm:ss SSS";
			String orderDate = DateUtils.dateToString(pattern);
			if (lzxxlist.size()>0 && !"".equals(lzxxlist.get(0).getSxh()) && null != lzxxlist.get(0).getSxh()) {
				if (listScreen.size() >0 && !"".equals(listScreen.get(0).getWindowId()) && null != listScreen.get(0).getWindowId()) {
					String strVoice =lzxxlist.get(0).getSxh().substring(13)+"@"+listScreen.get(0).getWindowId()+"@"+orderDate;
//				if (StringUtils.isNotEmpty("子窗口")) {
//					strVoice += "@" + "子窗口地址";
//				}
					VoiceManager.getInstance().add(strVoice);
					request.setAttribute("msg", "领证信息已成功发声!");
				}else {
					request.setAttribute("msg", "没有对应窗口!");
				}
			}else {
				request.setAttribute("msg", "没有对应顺序号!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "领证信息发声失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "lzxx/queryAllLzxx.action");
		return "voicelzxx";
	}
	
	public String weiHuIp(){
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serviceIp = request.getParameter("serviceIp");
		String clientIp = request.getParameter("clientIp");
		try {
			Lzxx lzxx = new Lzxx();
			//调用领证信息查询接口
			System.out.println("进入维护客户端ip接口");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = deptCode+"#"+serviceIp+"#"+clientIp;
			rows[1][0] = "zllx";
			rows[1][1] = "7";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
			if (lzXML.length()>0) {
				System.out.println("维护客户端ip接口XML="+lzXML);
			}
			System.out.println("维护客户端ip接口结束");
			request.setAttribute("msg", "维护客户端ip成功!");
		} catch (Exception e) {
			System.out.println("维护客户端ip接口调用失败");
			e.printStackTrace();
			request.setAttribute("msg", "维护客户端ip失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "queue/weiHuIP.jsp");
		return "weihuip";
	}
	
	
}
