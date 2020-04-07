package com.suntendy.queue.lzxx.action;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.suntendy.queue.led.service.LedService;
import com.suntendy.queue.lzxx.domain.Lzxx;
import com.suntendy.queue.lzxx.service.ILzxxService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.trff.TrffClient;
import com.suntendy.queue.util.trff.XMLUtils;
import com.suntendy.queue.queue.domain.Number;
public class LzxxAction extends TimerTask {
	
	
	
	CacheManager cacheManager = CacheManager.getInstance();
	private Publisher publisher;
	private ILzxxService lzxxService;
	private LedService ledService;
	private INumberService numberService;
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
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

	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}


	public synchronized void run() {

		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Lzxx lzxxVO = new Lzxx();

	
		
		for (int k = 1; k <= 9; k++) {
			lzxxVO.setZllx("0"+k);
			lzxxVO.setDeptHall(deptHall);
			List<Lzxx> listLZXX = new ArrayList<Lzxx>();
			try {
				listLZXX = lzxxService.queryLzckByZllx(lzxxVO);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (listLZXX.size()>0) {
				if (k == 8) {
					System.out.println("进入六合一开测试");
					try {
						String rows[][] = new String[2][2];
						rows[0][0] = "lsh";
						rows[0][1] = deptCode+"#1";
						rows[1][0] = "zllx";
						rows[1][1] = "13";
						String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
						String lzXML = TrffClient.write_nbjk("02C43", strXML);
//						String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012@2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012</message></head></root>";
						System.out.println("返回13接口XML="+lzXML);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (k==9) {
					System.out.println("进入六合一关测试");
					try {
						String rows[][] = new String[2][2];
						rows[0][0] = "lsh";
						rows[0][1] = deptCode+"#2";
						rows[1][0] = "zllx";
						rows[1][1] = "13";
						String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
						String lzXML = TrffClient.write_nbjk("02C43", strXML);
//						String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012@2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012</message></head></root>";
						System.out.println("返回13接口XML="+lzXML);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
				try {
					String rows[][] = new String[2][2];
					rows[0][0] = "lsh";
//					rows[0][1] = "01#131000000400#10.35.115.137";
					rows[0][1] = lzxxVO.getZllx()+"#"+deptCode+"#"+listLZXX.get(0).getIp();
					rows[1][0] = "zllx";
					rows[1][1] = "11";
					String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
					String lzXML = TrffClient.write_nbjk("02C43", strXML);
//					String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012@2100902042045#刘建冬#01#132801197405272016#09-3月 -17#131000000400#10.35.115.137#110000000000AA0012</message></head></root>";
//					String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>1170511024136#陈华清#03#320202196312233010#2017-05-11 16:37:43#320200000400#10.35.117.49@1170511024063#中源晟朝股份有限公司#03#91320206083157395R#2017-05-11 16:38:08#320200000400#10.35.117.49@1170512013154#江阴市公安局交通警察大队#03#11320281014040919N#2017-05-12 10:47:03#320200000400#10.35.117.49@1170512012994#江阴市公安局高新区分局#03#11320281014040919N#2017-05-12 10:47:21#320200000400#10.35.117.49@1170512013174#江阴市公安局#03#11320281014040919N#2017-05-12 10:47:38#320200000400#10.35.117.49@1170512012812#徐丙全#03#342622198012261396#2017-05-12 10:35:52#320200000400#10.35.117.49</message></head></root>";
					System.out.println("返回11接口XML="+lzXML);
					String result = new String();
					Lzxx lzxx = new Lzxx();
					if (lzXML.length()>0) {
						Document document = DocumentHelper.parseText(lzXML);
						Element root = document.getRootElement();
						result = ((Node)root.selectNodes("//code").get(0)).getText();
						if ("1".equals(result)) {
							String message = ((Node)root.selectNodes("//message").get(0)).getText();
							for (int i = 0; i < message.split("@").length; i++) {
								lzxx.setLsh(message.split("@")[i].split("#")[0]);
								lzxx.setXm(message.split("@")[i].split("#")[1]);
								lzxx.setZllx(message.split("@")[i].split("#")[2]);
								lzxx.setIdnumber(message.split("@")[i].split("#")[3]);
								List<Number> list1 = numberService.getValueRecardbyIdnumber("", message.split("@")[i].split("#")[0]);
								List<Number> list = numberService.getValueRecardbyIdnumber(message.split("@")[i].split("#")[3],"");
								if (list1.size()>0) {
									lzxx.setDeptHall(list1.get(0).getDeptHall());
								}else if (list.size()>0) {
									lzxx.setDeptHall(list.get(0).getDeptHall());
								}
					//			lzxx.setDeptCode(message.split("@")[i].split("#")[5]);
								lzxx.setIp(message.split("@")[i].split("#")[6]);
//								lzxx.setSxh(message.split("@")[i].split("#")[7]);
								lzxx.setSxh(null);
								lzxxService.insertLzxx(lzxx);
								LzxxAction lzxxAction = new LzxxAction();
								lzxxAction.delLzxx(message.split("@")[i].split("#")[0], message.split("@")[i].split("#")[2]);
							}
						}else {
							System.out.println("六合一返回的领证信息code不为1");
							System.out.println();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}		
		}		
	}

	public String delLzxx(String lshVal,String zllxVal){
		try {
//			Lzxx lzxx = new Lzxx();
			//调用领证信息查询接口
			System.out.println("进入领证信息删除接口");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = lshVal;
			rows[1][0] = "zllx";
			rows[1][1] = zllxVal;
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
			if (lzXML.length()>0) {
				System.out.println("删除领证接口(驾驶证)XML="+lzXML);
			}
			System.out.println("领证信息删除接口结束");
		} catch (Exception e) {
			System.out.println("领证接口(驾驶证)调用失败");
			e.printStackTrace();
		}
		
		
		return null;
	}
}
