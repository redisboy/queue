package com.suntendy.queue.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.suntendy.queue.queue.dao.INumberDao;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.util.cache.CacheManager;


@WebService(endpointInterface = "com.suntendy.queue.webservice.IQueueForImage")
public class QueueForImageImpl implements IQueueForImage {

	
	private INumberDao numberDao;
	
	
	public void setNumberDao(INumberDao numberDao) {
		this.numberDao = numberDao;
	}
	public INumberDao getNumberDao() {
		return numberDao;
	}
	
	public String queryNumbersById(String pdh) {
//		System.out.println("xmls="+xmls);
//		String hqxml = "<?xml version='1.0' encoding='GBK'?><root><QueryCondition><ip>127.0.0.1</ip><glbm>371300000400</glbm></QueryCondition></root>";
//		HashMap<String, String> map = new HashMap<String, String>();
//		map = XMLUtils.readXMLForQueueOut(xmls);
//		
		
//		String hqip = map.get("ip");
//		String hqglbm = map.get("glbm");
		
		System.out.println("接收到pdh="+pdh);
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String xml = "";
		xml += "<?xml version='1.0' encoding='GBK'?><root><head>";
		Number numbervo = new Number();
		Number number = new Number();
		numbervo.setDeptCode(deptCode);
		numbervo.setDeptHall(deptHall);
		pdh = deptCode+deptHall+pdh;
		numbervo.setClientno(pdh);
//		if (deptCode.equals(glbm)) {
				List<Number> list = new ArrayList<Number>();
				try {
//					list = numberDao.getNumberAllByClientIP(ip);
					list = numberDao.queryAllForImageById(numbervo);
					if (null != list && list.size()>0) {
						number = list.get(0);
						xml +="<code>1</code><message>数据查询成功</message><rownum>"+list.size()+"</rownum></head>";
						xml +="<body><queue><pdh>"+number.getSerialNum()+"</pdh>";
						xml +="<sfz>"+number.getIDNumber()+"</sfz>";
						xml +="<dlrsfz>"+number.getIDNumberB()+"</dlrsfz>";
						xml +="<ywlx>"+number.getBusinessType()+"</ywlx>";
						xml +="<qhsj>"+number.getEnterTime()+"</qhsj>";
						xml +="<jhsj>"+number.getBeginTime()+"</jhsj>";
						xml +="<zt>"+number.getStatus()+"</zt>";
						xml +="<gh>"+number.getCode()+"</gh>";
						xml +="<xm>"+number.getXm()+"</xm>";
						xml +="<dyw>"+number.getQueueCode()+"</dyw>";
						xml +="<serverip>"+number.getServerIp()+"</serverip></queue></body></root>";
					}else{
						xml +="<code>1</code><message>无数据信息</message><rownum>0</rownum></head></root>";
					}
				} catch (Exception e) {
					xml +="<code>2</code><message>数据查询失败</message><rownum>0</rownum></head></root>";
					e.printStackTrace();
				}
//		}else{
//			xml +="<code>2</code><message>此调用未经授权,请联系相关人员</message><rownum>0</rownum></head></root>";
//		}
		return xml;
	}
	@Override
	public String queryNumbersByIp(String serverip) {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String xml = "";
		xml += "<?xml version='1.0' encoding='GBK'?><root><head>";
		Number numbervo = new Number();
		Number number = new Number();
		numbervo.setDeptCode(deptCode);
		numbervo.setDeptHall(deptHall);
		numbervo.setServerIp(serverip);
//		if (deptCode.equals(glbm)) {
				List<Number> list = new ArrayList<Number>();
				try {
//					list = numberDao.getNumberAllByClientIP(ip);
					list = numberDao.queryAllForImageById(numbervo);
					if (null != list && list.size()>0) {
						number = list.get(0);
						xml +="<code>1</code><message>数据查询成功</message><rownum>"+list.size()+"</rownum></head>";
						xml +="<body><queue><pdh>"+number.getSerialNum()+"</pdh>";
						xml +="<serverip>"+number.getServerIp()+"</serverip></queue></body></root>";
					}else{
						xml +="<code>1</code><message>无数据信息</message><rownum>0</rownum></head></root>";
					}
				} catch (Exception e) {
					xml +="<code>2</code><message>数据查询失败</message><rownum>0</rownum></head></root>";
					e.printStackTrace();
				}
//		}else{
//			xml +="<code>2</code><message>此调用未经授权,请联系相关人员</message><rownum>0</rownum></head></root>";
//		}
		return xml;
	}

}
