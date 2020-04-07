package com.suntendy.queue.queue.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.dao.IBusinessDao;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.queue.dao.INumberDao;
import com.suntendy.queue.queue.domain.CallOut;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.ICallOutService;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.cache.InitCacheManager;
import com.suntendy.queue.util.exception.TrffException;
import com.suntendy.queue.util.trff.TrffClient;
import com.suntendy.queue.util.trff.TrffUtils;
import com.suntendy.queue.util.trff.XMLUtils;

public class CallOutStatusAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ICallOutService calloutService;
	private ICodeService codeService;
	private IBusinessDao businessDao;
	private INumberDao numberDao;

	public void setNumberDao(INumberDao numberDao) {
		this.numberDao = numberDao;
	}
	public IBusinessDao getBusinessDao() {
		return businessDao;
	}
	public void setBusinessDao(IBusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	public ICallOutService getCalloutService() {
		return calloutService;
	}
	public void setCalloutService(ICallOutService calloutService) {
		this.calloutService = calloutService;
	}
	public ICodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}
	
	public String toCallout(){
		return "toCallout";
	}
	/**
	 * 设置评价卡号状态
	 * （修改远程表状态默认为3）
	 * @return
	 */
	public String updateCalloutStatus(){
		String yhdh = StringUtils.trimToEmpty(this.getRequest().getParameter("yhdh"));
			try {
				calloutService.updateCallOutStatus(yhdh);
				getRequest().setAttribute("msg", "处理完成！");
			} catch (Exception e) {
				e.printStackTrace();
				getRequest().setAttribute("msg", "处理失败！");
			}
		getRequest().setAttribute("action", "callout/toCallout.action");
		return "success";
	}
	/**
	 * 异常业务处理
	 * （修改远程表状态默认为3）
	 * @return
	 */
	public String exceptionHanDling(){
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String yhdh = StringUtils.trimToEmpty(this.getRequest().getParameter("yhdh"));
		yhdh = deptCode + deptHall + yhdh;
		String res="";
			try {
				Number number = new Number();
				number.setClientno(yhdh);
				List<Number> list = calloutService.getvaluerecordByClientno(number);
				if (list.size()>0) {
					res = calloutService.exceptionHanDling(list.get(0));
				}
				getRequest().setAttribute("msg", res);
			} catch (Exception e) {
				e.printStackTrace();
				getRequest().setAttribute("msg", "处理失败！");
			}
		getRequest().setAttribute("action", "queue/exceptionHanDling.jsp");
		return "success";
	}
	/**
	 * 异常数据恢复
	 * （根据6合1表状态修改本地表状态）
	 * @return
	 * @throws Exception
	 */
	public String yiChangCL() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serialNum = request.getParameter("sjhm");
		String number = deptCode+deptHall+serialNum;
		Number numbers = new Number();
		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		List<Number> listCallout = calloutService.query6he1Callout(number);
		List<Number> listValuerecord = calloutService.query6he1Valuerecord(number);
		try {
			System.out.println("异常处理号码："+serialNum);
			//6合1状态为：未叫号
			if (listCallout.size()==0 && list.size()>0) {
				String jym = number + deptCode.substring(0, 6) + "#" + list.get(0).getOperNum() + "#01#"
				+ DateUtils.dateToString("yyyyMMdd") + deptCode.substring(6);
			String[] result = TrffUtils.saveCallInfo(number, list.get(0).getOperNum(), "01", codeService.jm(jym));
			getRequest().setAttribute("msg", "数据未同步，请稍后再试！");
			//6合1状态为：正在办理，未评价
			} else if (listCallout.get(0).getYwbj().equals("1") || 
					listCallout.get(0).getYwbj().equals("2")) {
				String num = "2";
				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，<br>请重新登录并过号！");
				//6合1状态为：已评价
			}else if (listCallout.get(0).getYwbj().equals("3")) {
				if (listValuerecord.get(0).getPjjg().equals("0") ||
						listValuerecord.get(0).getPjjg().equals("1") ||
						listValuerecord.get(0).getPjjg().equals("2") ||
						listValuerecord.get(0).getPjjg().equals("3") ||
						listValuerecord.get(0).getPjjg().equals("4")) {
					String num = "6";
					calloutService.updateValuerecordStatus(num, number);
					numberManager.removeCallNumber(listCallout.get(0).getYhdh());
					getRequest().setAttribute("msg", "处理完成，请直接叫号！");
				}
				//6合1状态为：过号
			}else if (listCallout.get(0).getYwbj().equals("4")) {
				String num = "3";
				calloutService.updateValuerecordStatus(num, number);
				numberManager.removeCallNumber(listCallout.get(0).getYhdh());
				getRequest().setAttribute("msg", "处理完成，请直接叫号！");
				//6合1状态为：挂起
			}else if (listCallout.get(0).getYwbj().equals("5")) {
				String num = "7";
				calloutService.updateValuerecordStatus(num, number);
				numberManager.removeCallNumber(listCallout.get(0).getYhdh());
				getRequest().setAttribute("msg", "处理完成，该号已挂起！");
			}
//			getRequest().setAttribute("msg", "处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String res = calloutService.exceptionHanDling(list.get(0));
				getRequest().setAttribute("msg", res);
			} catch (Exception e2) {
				e.printStackTrace();
				getRequest().setAttribute("msg", "处理失败！");
			}
		}
		getRequest().setAttribute("action", "queue/yiChangHuiFu.jsp");
		return "yccl";
	}
	
	/**
	 * 异常数据恢复(接口)
	 * （根据6合1接口返回状态修改本地表状态）
	 * @return
	 * @throws Exception
	 */
	public String yiChangJKCL() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serialNum = request.getParameter("sjhm");
		String number = deptCode+deptHall+serialNum;
		Number numbers = new Number();
		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		String[] result = null;
		String[] result1 = null;
		try {
			//调用领证信息查询接口
			System.out.println("进入异常处理(接口)");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = "QUEUE_CALLOUT#"+DateUtils.dateToString("yyMMdd")+number;
			rows[1][0] = "zllx";
			rows[1][1] = "8";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
//			String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>150417640100000400A10001#640100000400A10001#YX0692#20150417 10:16:47#4#1</message></head></root>";
			result =  XMLUtils.readXML(lzXML);
			if ("1".equals(result[0])) {
				result1 = result[1].split("#");
			}
			
			System.out.println("异常处理(接口)结果"+result[0]+"---办理员工编号="+result1[2]+"状态="+result1[4]);
		} catch (Exception e) {
			System.out.println("异常处理(接口)调用失败");
			e.printStackTrace();
		}
		
		/*
		 * result1[4]为此号的当前状态  
		 * result1[2]为此号的业务办理员工编号 或者  list.get(0).getCode();
		 */
		
		try {
			System.out.println("异常处理号码："+serialNum);
			//6合1状态为：未叫号
			if (list.size() == 0) {
				getRequest().setAttribute("msg", serialNum+"顺序号还未产生！");
				//6合1状态为：正在办理，未评价
			}else if ("1".equals(result1[4]) || "2".equals(result1[4])) {
				String num = "2";
				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，<br>请重新登录并过号！");
				//6合1状态为：已评价
			}else if ("3".equals(result1[4])) {
					String num = "6";
					calloutService.updateValuerecordStatus(num, number);
					numberManager.removeCallNumber(result1[2]);//输入用户代号  list.get(0).getCode();
					getRequest().setAttribute("msg", serialNum+"处理完成，请直接叫号！");
				//6合1状态为：过号
			}else if ("4".equals(result1[4])) {
				String num = "3";
				calloutService.updateValuerecordStatus(num, number);
				numberManager.removeCallNumber(result1[2]);//输入用户代号  list.get(0).getCode();
				getRequest().setAttribute("msg", serialNum+"处理完成，请直接叫号！");
				//6合1状态为：挂起
			}else if ("5".equals(result1[4])) {
				String num = "7";
				calloutService.updateValuerecordStatus(num, number);
				numberManager.removeCallNumber(result1[2]);//输入用户代号  list.get(0).getCode();
				getRequest().setAttribute("msg", serialNum+"处理完成，该号已挂起！");
			}
//			getRequest().setAttribute("msg", "处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String res = calloutService.exceptionHanDling(list.get(0));
				getRequest().setAttribute("msg", res);
			} catch (Exception e2) {
				e.printStackTrace();
				getRequest().setAttribute("msg", serialNum+"处理失败！");
			}
		}
		getRequest().setAttribute("action", "queue/yiChangHuiFuJK.jsp");
		return "ycjkcl";
	}
	
	public String yiChangJKCLByCode() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String ygbh = request.getParameter("ygbh");
//		String number = deptCode+deptHall+serialNum;
		Number numbers = new Number();
//		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		
		String[] result = null;
		String result1 = null;
		try {
			//调用领证信息查询接口
			System.out.println("进入异常处理code(接口)");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = ygbh;
			rows[1][0] = "zllx";
			rows[1][1] = "12";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
			System.out.println("返回12接口liuwx的XML="+lzXML);
//			String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>371300000400AC0005</message></head></root>";
			result =  XMLUtils.readXML(lzXML);
			if ("1".equals(result[0])) {
				result1 = result[1];
			}else {
				System.out.println("根据员工编号("+ygbh+")未查询到状态为1或者2的顺序号");
			}
			
			System.out.println("异常处理(接口)结果"+result[0]+"---办理员工编号="+ygbh+"顺序号="+result1);
		} catch (Exception e) {
			System.out.println("异常处理(接口)调用失败");
			e.printStackTrace();
		}
		
		/*
		 * result1[4]为此号的当前状态  
		 * result1[2]为此号的业务办理员工编号 或者  list.get(0).getCode();
		 */
		numbers.setSerialNum(result1);
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		try {
			System.out.println("异常处理号码："+result1);
			//6合1状态为：未叫号
			if (list.size() == 0) {
				getRequest().setAttribute("msg", result1+"顺序号还未产生！");
				//6合1状态为：正在办理，未评价
			}else {
				String num = "2";
				calloutService.updateValuerecordStatus(num, result1);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", result1+"处理完成，<br>请重新登录并过号！");
				//6合1状态为：已评价
			}
//			getRequest().setAttribute("msg", "处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String res = calloutService.exceptionHanDling(list.get(0));
				getRequest().setAttribute("msg", res);
			} catch (Exception e2) {
				e.printStackTrace();
				getRequest().setAttribute("msg", result1+"处理失败！");
			}
		}
		getRequest().setAttribute("action", "queue/yiChangHuiFuJK.jsp");
		return "ycjkclcode";
	}
	
	/**
	 * 异常数据恢复(丢号)
	 * （根据6合1接口返回状态修改本地表状态）
	 * @return
	 * @throws Exception
	 */
	public String yiChangDH() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		String isUseInterface = cacheManager.getSystemConfig("isUseInterface");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serialNum = request.getParameter("sjhm");
		String number = deptCode+deptHall+serialNum;
		Number numbers = new Number();
		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		String[] result = null;
		String[] result1 = null;
//		if ("0".equals(isUseInterface)) {
//			try {
//				//调用领证信息查询接口
//				System.out.println("进入异常处理(接口)");
//				String rows[][] = new String[2][2];
//				rows[0][0] = "lsh";
//				rows[0][1] = "QUEUE_CALLOUT#"+DateUtils.dateToString("yyMMdd")+number;
//				rows[1][0] = "zllx";
//				rows[1][1] = "8";
//				String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
//				String lzXML = TrffClient.write_nbjk("02C43", strXML);
////			String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>150417640100000400A10001#640100000400A10001#YX0692#20150417 10:16:47#4#1</message></head></root>";
//				result =  XMLUtils.readXML(lzXML);
//				if ("1".equals(result[0])) {
//					result1 = result[1].split("#");
//				}
//				
//				System.out.println("异常处理(接口)结果"+result[0]+"---办理员工编号="+result1[2]+"状态="+result1[4]);
//			} catch (Exception e) {
//				System.out.println("异常处理(接口)调用失败");
//				e.printStackTrace();
//			}
//		}
		
		/*
		 * result1[4]为此号的当前状态  
		 * result1[2]为此号的业务办理员工编号 或者  list.get(0).getCode();
		 */
		
		try {
			System.out.println("异常处理号码："+serialNum);
			//6合1状态为：未叫号
			if (list.size() == 0) {
				getRequest().setAttribute("msg", serialNum+"此顺序号还未产生！");
				//6合1状态为：正在办理，未评价
			}else if("1".equals(list.get(0).getStatus())){
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，该号已添加到队列中！");
			}else if ("2".equals(list.get(0).getStatus())) {
//				String num = "2";
//				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，该号已添加到您的窗口！");
				//6合1状态为：已评价
			}else if ("3".equals(list.get(0).getStatus())) {
//					String num = "6";
//					calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，请直接叫号！");
				//6合1状态为：过号
			}else if ("4".equals(list.get(0).getStatus())) {
				String num = "3";
				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，请直接叫号！");
				//6合1状态为：挂起
			}else if ("7".equals(list.get(0).getStatus())) {
//				String num = "7";
//				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				getRequest().setAttribute("msg", serialNum+"处理完成，该号已挂起！");
			}
//			getRequest().setAttribute("msg", "处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String res = calloutService.exceptionHanDling(list.get(0));
				getRequest().setAttribute("msg", res);
			} catch (Exception e2) {
				e.printStackTrace();
				getRequest().setAttribute("msg", serialNum+"处理失败！");
			}
		}
		getRequest().setAttribute("action", "queue/yiChangHuiFuJK.jsp");
		return "ycjkcl";
	}
	
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public  void yiChangDHCL() throws Exception{
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		initcachemanger.initNumber();
	}
	/**
	 * 手动流转
	 * @return
	 * @throws Exception
	 */
	public String shouDongLiuZhuan() throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String serialNum = request.getParameter("sjhm");
		String number = deptCode+deptHall+serialNum;
		Number numbers = new Number();
		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		
		if (list.size()>0) {
		
			//流转功能
			Number numLZ = new Number();
			List<Business> busList = businessDao.getBusinessList(list.get(0).getBusinessType(), null, null, null, deptCode, deptHall);
			if (busList.size() >0 
					&& busList.get(0).getLiuzhuan() != null 
					&& !"".equals(busList.get(0).getLiuzhuan()) 
					&& !"0".equals(busList.get(0).getLiuzhuan()) 
//					&& "1".equals(busList.get(0).getIsautolz())
					){
				if ("1".equals(busList.get(0).getIsautolz())) {
					List<Business> busVO = businessDao.getBusinessList(busList.get(0).getLiuzhuan(), null, null, null, deptCode, deptHall);
					if (busVO.size() > 0) {
						numLZ.setBusinessType(busVO.get(0).getId());
						numLZ.setQueueCode(busVO.get(0).getQueueCode());
						numLZ.setStatus("1");
						numLZ.setId(list.get(0).getId());
						String patternVo = "yyyy-MM-dd HH:mm:ss SSS";
						numLZ.setEnterTime(DateUtils.dateToString(patternVo));
						numberDao.updateNumber(numLZ);
						initcachemanger.initNumber();
						getRequest().setAttribute("msg", "处理完成,请等待叫号");
					}
				}else {
					getRequest().setAttribute("msg", "此顺序号所在业务类型为自动流转!");
				}
			}else {
				getRequest().setAttribute("msg", "此顺序号所在业务类型没有下一步流转!");
			}
		}else {
			getRequest().setAttribute("msg", "此顺序号为产生!");
		}
		getRequest().setAttribute("action", "queue/shouDongLiuZhuan.jsp");
		return "yccl";
	}
	
	/**
	 * 异常数据恢复(丢号)
	 * （根据6合1接口返回状态修改本地表状态）
	 * @return
	 * @throws Exception
	 */
	public String yiChangDHHC() throws Exception{
		HttpServletRequest request = getRequest();
		String sxh = "E0006";
		String value = new String();
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		InitCacheManager initcachemanger = new InitCacheManager(this.getServletContext());
		String isUseInterface = cacheManager.getSystemConfig("isUseInterface");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String yhdh = request.getParameter("yhdh");
		String barid = request.getParameter("barid");
		sxh = request.getParameter("sxh");
		System.out.println("yhdh:"+yhdh);
		System.out.println("barid:"+barid);
		System.out.println("sxh:"+sxh);
		List<Business> listbus = new ArrayList<Business>();
		try {
			listbus = businessDao.getBusinessList(null, null, null, null, deptCode, deptHall);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String number = deptCode+deptHall+sxh;
		
		Number nu = new Number();
		nu.setCode(yhdh);
		nu.setBarId(barid);
		nu.setQueueCode(listbus.get(0).getQueueCode());
		nu.setBusinessType(listbus.get(0).getId());
		nu.setClientno(deptCode+deptHall+sxh);
		nu.setDeptCode(deptCode);
		nu.setDeptHall(deptHall);
		numberDao.saveSxh(nu);
		
		
		
		
		
		Number numbers = new Number();
		numbers.setSerialNum(number);
		numbers.setEnterTime(sdf.format(new Date()));
		List<Number> list = calloutService.getBarIdBySerialNum(numbers);
		String[] result = null;
		String[] result1 = null;
		
		try {
			System.out.println("异常处理号码："+sxh);
			//6合1状态为：未叫号
			if (list.size() == 0) {
				value = sxh+"此顺序号还未产生！";
				//6合1状态为：正在办理，未评价
			}else if("1".equals(list.get(0).getStatus())){
				initcachemanger.initNumber();
				value = sxh+"处理完成，该号已添加到队列中！";
			}else if ("2".equals(list.get(0).getStatus())) {
//				String num = "2";
//				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				value = sxh+"处理完成，该号已添加到您的窗口！";
				//6合1状态为：已评价
			}else if ("3".equals(list.get(0).getStatus())) {
//					String num = "6";
//					calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				value = sxh+"处理完成，请直接叫号！";
				//6合1状态为：过号
			}else if ("4".equals(list.get(0).getStatus())) {
				String num = "3";
				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				value = sxh+"处理完成，请直接叫号！";
				//6合1状态为：挂起
			}else if ("7".equals(list.get(0).getStatus())) {
//				String num = "7";
//				calloutService.updateValuerecordStatus(num, number);
				initcachemanger.initNumber();
				value = sxh+"处理完成，该号已挂起！";
			}
//			getRequest().setAttribute("msg", "处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getRequest().setAttribute("msg", "成功");
		return null;
	}
	
}
