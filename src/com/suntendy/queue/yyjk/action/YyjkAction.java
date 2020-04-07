package com.suntendy.queue.yyjk.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

































































import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ibatis.sqlmap.engine.mapping.sql.dynamic.elements.IsEmptyTagHandler;
import com.opensymphony.webwork.ServletActionContext;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.log.domain.LogVo;
import com.suntendy.queue.printSet.domain.Print;
import com.suntendy.queue.printSet.service.IPrintSetService;
import com.suntendy.queue.queue.domain.Code;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.PrintInfo;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.service.INumberService;
import com.suntendy.queue.tuiban.domain.TuiBan;
import com.suntendy.queue.tuiban.service.ITuiBanService;
import com.suntendy.queue.util.HttpRequestUtil.HttpRequestUtil;
import com.suntendy.queue.util.barcode.BarCode;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.SaveException;
import com.suntendy.queue.util.exception.TrffException;
import com.suntendy.queue.util.trff.TrffClient;
import com.suntendy.queue.util.trff.XMLUtils;
import com.suntendy.queue.yyjk.domain.FoShanCLCYXX;
import com.suntendy.queue.yyjk.domain.GuangZhouYYXX;
import com.suntendy.queue.yyjk.domain.NanNingYYXX;
import com.suntendy.queue.yyjk.domain.ZhongShanYYXX;
import com.suntendy.queue.yyjk.service.IYyjkService;
import com.suntendy.queue.yyjk.service.impl.YyjkServiceImpl;
import com.suntendy.queue.zzj.domain.Zzj;
import com.suntendy.queue.zzj.service.IZzjService;

public class YyjkAction extends BaseAction{
	DeptService deptService;
	
	IBusinessService businessService;
	
	INumberService numberService;
	
	IZzjService zzjService;
	
	ITuiBanService tuibanService;
	
	IPrintSetService printSetService;
	
	IYyjkService yyjkService;
	
	private ICodeService codeService;
	
	public ICodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

	public IYyjkService getYyjkService() {
		return yyjkService;
	}

	public void setYyjkService(IYyjkService yyjkService) {
		this.yyjkService = yyjkService;
	}

	public ITuiBanService getTuibanService() {
		return tuibanService;
	}

	public void setTuibanService(ITuiBanService tuibanService) {
		this.tuibanService = tuibanService;
	}

	public IZzjService getZzjService() {
		return zzjService;
	}

	public void setZzjService(IZzjService zzjService) {
		this.zzjService = zzjService;
	}

	public IPrintSetService getPrintSetService() {
		return printSetService;
	}

	public void setPrintSetService(IPrintSetService printSetService) {
		this.printSetService = printSetService;
	}

	
	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 获取所有预约信息
	 * @throws Exception
	 */
	public void readAllYYXX() throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String yyjkmode = cacheManager.getSystemConfig("yyjkmode"); // 预约模式 0-没有 1-珠海 2-南宁...
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		Dept dept = new Dept();
		dept.setDeptcode(deptCode);
		dept.setDepthall(deptHall);
		List<Dept> deptList = deptService.getDeptList(dept);
		net.sf.json.JSONObject yyJson = null;
		if("4".equals(yyjkmode)){
			JSONObject json = new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhssmm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			json.put("time", sdf.format(new Date()));
			JSONObject requestJson = new JSONObject();
			requestJson.put("zbbh", "");//指标编号
			requestJson.put("yyrq", sdf2.format(new Date()));//预约日期
			requestJson.put("fsbh", "");//分所编号
			json.put("body", requestJson);
			json.put("signInfo", "");
			json.put("systemNo", "");
			json.put("id", "");
			json.put("clientId", "");
			//yyJson = HttpRequestUtil.httpRequest(url, "POST", json);
			yyJson = net.sf.json.JSONObject.fromObject("{\"systemNo\":\"01\",\"returnCode\":\"1\",\"returnMessage\":\"查询成功。\",\"time\":\"20150202152830\",\"signInfo\":\"B9F8B8CE0DAC2A827A252EBDA20FC720\",\"id\":\"ID20131209094323123\",\"body\":[{\"sfzmhm\":\"440111198604244819\",\"yylsh\":\"1501300300001\",\"jdclx\":\"\",\"lshplx\":\"02\",\"syr\":\"\",\"zbbh\":\"20150124001\",\"fsbh\":\"440100000403\",\"lshp\":\"粤AJM050\",\"dbr\":\"\",\"yyrq\":\"2015-02-02\",\"yydjsj\":\"2015-01-30 16:14:33.0\",\"dbrsfzmhm\":\"\",\"yysjxh\":\"2\",\"zt\":\"0\",\"yysj\":\"10:30:00~12:00:00\",\"yylx\":\"1\",\"yyly\":\"1\",\"sfzmmc\":\"A\"}]}");
		} else if ("5".equals(yyjkmode)){
			//查询接口
			
		}
		if(yyJson == null){
			System.out.println("车辆预约信息查询接口调用失败");
		}else if("0".equals(yyJson.get("code"))){
			System.out.println(yyJson.get("msg"));
		}
		else{
			yyjkService.readAllYYXX(yyJson);
			System.out.println("车辆预约信息查询接口调用完成");
		}
		
	}
	
	/**
	 * 根据身份证号查询预约信息并返回取号结果
	 * @throws Exception
	 */
	public void queryYYXXzh()throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String yyjkmode = cacheManager.getSystemConfig("yyjkmode"); // 预约模式 0-没有 1-珠海 2-南宁
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String sfzmhm=StringUtils.trimToEmpty(request.getParameter("sfzmhm")); //身份证号码
		String zjlx=StringUtils.trimToEmpty(request.getParameter("IDType")); //证件类型
		String yybefore=StringUtils.trimToEmpty(request.getParameter("yybefore")); //预约取号提前时间 可以再js中临时调整 以分钟为单位
		String yyafter=StringUtils.trimToEmpty(request.getParameter("yyafter")); //预约取号延后时间 可以再js中临时调整 以分钟为单位
		String SFZNameA= "";//身份证姓名
		if(!"".equals(request.getParameter("SFZNameA")) && null != request.getParameter("SFZNameA")){
			SFZNameA=StringUtils.trimToEmpty(request.getParameter("SFZNameA"));
		}
		Dept dept = new Dept();
		dept.setDeptcode(deptCode);
		dept.setDepthall(deptHall);
		JSONObject result = new JSONObject();
		List<Dept> deptList = deptService.getDeptList(dept);
		if("1".equals(yyjkmode)){
			result = zhmode(sfzmhm, yybefore, yyafter, deptList.get(0)); //珠海模式
		}else if("2".equals(yyjkmode)){
			result = nanningMode(sfzmhm, zjlx, deptList.get(0)); //南宁模式
		}else if("3".equals(yyjkmode)){
			result = zhongshanMode(sfzmhm, deptList.get(0)); //中山模式
		}else if("4".equals(yyjkmode)){
			result = guangZhouMode(sfzmhm, deptList.get(0)); //广州模式
		}else if("5".equals(yyjkmode)){
			result = foshanMode(sfzmhm,yybefore, yyafter, deptList.get(0),SFZNameA); //佛山模式
		}else if("6".equals(yyjkmode)){
			result = weixinyuyueMode(sfzmhm, deptList.get(0)); //微信预约模式
			result.put("modelNumber", "6");
		}else{
			result.put("code", "0");
			result.put("msg", "还未支持预约取号功能!");
		}
		this.getResponse("text/html").getWriter().println(result.toString());
	}
	

	public void queryYYXXzh2()throws Exception{
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String sfzmhm=StringUtils.trimToEmpty(request.getParameter("sfzmhm"));
		String yybefore=StringUtils.trimToEmpty(request.getParameter("yybefore")); //预约取号提前时间 可以再js中临时调整 以分钟为单位
		String yyafter=StringUtils.trimToEmpty(request.getParameter("yyafter")); //预约取号延后时间 可以再js中临时调整 以分钟为单位
		Dept dept = new Dept();
		dept.setDeptcode(deptCode);
		dept.setDepthall(deptHall);
		JSONObject result = new JSONObject();
		List<Dept> deptList = deptService.getDeptList(dept);
		if(null!=deptList&&deptList.size()!=0){
			Dept deptInfo = deptList.get(0);
			String ak = deptInfo.getAk();
			String yydd = deptInfo.getYydd();
			String outputStr = "ak="+ak+"&yydd="+yydd+"&sfzmhm="+sfzmhm;
			System.out.println("传入参数："+outputStr.toString());
			ArrayList<JSONObject> requestResult = HttpRequestUtil.httpRequest("http://10.42.158.219:14001/ReserveData/QueryReserveData", "POST", outputStr);
			//模拟
			//ArrayList<JSONObject> requestResult = new ArrayList<JSONObject>();
			//JSONObject httpRequestResult1 = new JSONObject("{ 'Error':0, 'Address':珠海市车管所, 'BusinessName':'变更所有人联系方式', 'IDCode':'440402193302032435', 'PlateNo':'粤CABCD' ,  'PlateType' :'小型汽车', 'ReserveDTEnd':'\\/Date(863384832400)\\/', 'ReserveDTStart':'\\/Date(863384832400)\\/', 'ReserveNo':'20160405A0001','UName':'测试' }");
			//httpRequestResult1.put("code", "1");
			//requestResult.add(httpRequestResult1);
			//System.out.println(httpRequestResult.toString());
			System.out.println("返回预约结果条数："+requestResult.size());
			String dateAlertMsg = "" ;
			for(JSONObject httpRequestResult:requestResult){
				if("0".equals(httpRequestResult.get("code"))){ //没查询到预约信息
					result.put("code", "0");
					result.put("msg", "未查询到该身份证号对应的预约信息");
				}else if("2".equals(httpRequestResult.get("code"))){//查询发生异常
					result.put("code", "0");
					result.put("msg", httpRequestResult.get("msg"));
					
				}else if("1".equals(httpRequestResult.get("code"))){ //查询正常
					if((Integer)httpRequestResult.get("Error")==0){ //查询错误结果0 查询正常
						String BusinessName = (String) httpRequestResult.get("BusinessName");
						String beginTime = (String) httpRequestResult.get("ReserveDTStart");
						String EndTime = (String) httpRequestResult.get("ReserveDTEnd");
						beginTime =beginTime.replace("\\", "").replace("/Date(", "").replace(")/", "");
						EndTime =EndTime.replace("\\", "").replace("/Date(", "").replace(")/", "");
						//System.out.println("beginTime"+beginTime+":"+"EndTime"+EndTime);
						Long longBegin = Long.parseLong(beginTime)-Long.parseLong(yybefore)*1000*60;
						Long longEnd = Long.parseLong(EndTime)+Long.parseLong(yyafter)*1000*60;
						Date datebegin = new Date();
						datebegin.setTime(longBegin);
						Date dateEnd = new Date();
						dateEnd.setTime(longEnd);
						Date now = new Date();
						//if(true){ //判断是否在时间段内
						if(now.before(dateEnd)&&now.after(datebegin)){ //判断是否在时间段内
							List<Business> businessList = businessService.selectByYYYWMC(BusinessName,deptCode,deptHall);
							if(null!=businessList&&businessList.size()!=0){//取号
								
							}else if(null==businessList||businessList.size()==0){
								result.put("code", "0");
								result.put("msg", "叫号系统业务类型未包含该预约业务类型："+BusinessName);
							}
							
						}else{
							SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
							String begin = s.format(datebegin);
							String End = s.format(dateEnd);
							result.put("code", "0");
							if("".equals(dateAlertMsg)){
								dateAlertMsg = "不在预约时间段内不能取号  您预约的时间为："+begin+" 至 "+End;
								result.put("msg", "不在预约时间段内不能取号  您预约的时间为："+begin+" 至 "+End);
							}else{
								dateAlertMsg += "  和   "+begin+" 至 "+End;
								result.put("msg", dateAlertMsg);
							}
							
						}
						
					}else if((Integer)httpRequestResult.get("Error")==-1){ //查询结果error -1 调用预约查询的AK值不正确
						result.put("code", "0");
						result.put("msg", "调用预约查询的AK值不正确");
					}else if((Integer)httpRequestResult.get("Error")==-2){ //查询结果error -2 调用预约查询传入的参数不正确
						result.put("code", "0");
						result.put("msg", "调用预约查询传入的参数不正确");
					}
				}
				
			}
		}else {
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
		}
		
		this.getResponse("text/html").getWriter().println(result.toString());
	}
	
	/**
	 * 珠海模式
	 * @param sfzmhm 身份证明号码
	 * @param yybefore 预约时间提前(单位分钟)
	 * @param yyafter 预约时间延后(单位分钟)
	 * @param dept 大厅表信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject zhmode(String sfzmhm,String yybefore,String yyafter,Dept dept) throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		//判断大厅信息是否为空
		if(null==dept){
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
			return result; 
		}
		String ak = dept.getAk();
		String yydd = dept.getYydd();
		String outputStr = "ak="+ak+"&yydd="+yydd+"&sfzmhm="+sfzmhm;
		System.out.println("传入参数："+outputStr.toString());
		ArrayList<JSONObject> requestResult = HttpRequestUtil.httpRequest("http://68.89.166.19:14001/ReserveData/QueryReserveData", "POST", outputStr);
		//模拟
		//ArrayList<JSONObject> requestResult = new ArrayList<JSONObject>();
		//JSONObject httpRequestResult1 = new JSONObject("{ 'Error':0, 'Address':珠海市车管所, 'BusinessName':'变更所有人联系方式', 'IDCode':'440402193302032435', 'PlateNo':'粤CABCD' ,  'PlateType' :'小型汽车', 'ReserveDTEnd':'\\/Date(863384832400)\\/', 'ReserveDTStart':'\\/Date(863384832400)\\/', 'ReserveNo':'20160405A0001','UName':'测试' }");
		//httpRequestResult1.put("code", "1");
		//requestResult.add(httpRequestResult1);
		//System.out.println(httpRequestResult.toString());
		System.out.println("返回预约结果条数："+requestResult.size());
		String dateAlertMsg = "" ;
		for(JSONObject httpRequestResult:requestResult){
			if("0".equals(httpRequestResult.get("code"))){ //没查询到预约信息
				result.put("code", "0");
				result.put("msg", "未查询到该身份证号对应的预约信息");
				return result;
			}else if("2".equals(httpRequestResult.get("code"))){//查询发生异常
				result.put("code", "0");
				result.put("msg", httpRequestResult.get("msg"));
				return result;
			}else if("1".equals(httpRequestResult.get("code"))){ //查询正常
				if((Integer)httpRequestResult.get("Error")==0){ //查询错误结果0 查询正常
					String BusinessName = (String) httpRequestResult.get("BusinessName");
					String beginTime = (String) httpRequestResult.get("ReserveDTStart");
					String EndTime = (String) httpRequestResult.get("ReserveDTEnd");
					beginTime =beginTime.replace("\\", "").replace("/Date(", "").replace(")/", "");
					EndTime =EndTime.replace("\\", "").replace("/Date(", "").replace(")/", "");
					//System.out.println("beginTime"+beginTime+":"+"EndTime"+EndTime);
					Long longBegin = Long.parseLong(beginTime)-Long.parseLong(yybefore)*1000*60;
					Long longEnd = Long.parseLong(EndTime)+Long.parseLong(yyafter)*1000*60;
					Date datebegin = new Date();
					datebegin.setTime(longBegin);
					Date dateEnd = new Date();
					dateEnd.setTime(longEnd);
					Date now = new Date();
					//if(true){ //判断是否在时间段内
					if(now.before(dateEnd)&&now.after(datebegin)){ //判断是否在时间段内
						List<Business> businessList = businessService.selectByYYYWMC(BusinessName,deptCode,deptHall);
						if(null!=businessList&&businessList.size()!=0){//取号
							//quhao(businessList.get(0),sfzmhm,"A");
							//返回取号所需的business的信息  参照BusinessTypeAction来写的  execute 方法来写的
							result = quhao(businessList.get(0), deptCode, deptHall,null);
						}else if(null==businessList||businessList.size()==0){
							result.put("code", "0");
							result.put("msg", "叫号系统业务类型未包含该预约业务类型："+BusinessName);
							return result;
						}
						
					}else{
						SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
						String begin = s.format(datebegin);
						String End = s.format(dateEnd);
						result.put("code", "0");
						if("".equals(dateAlertMsg)){
							dateAlertMsg = "不在预约时间段内不能取号  您预约的时间为："+begin+" 至 "+End;
							result.put("msg", "不在预约时间段内不能取号  您预约的时间为："+begin+" 至 "+End);
						}else{
							dateAlertMsg += "  和   "+begin+" 至 "+End;
							result.put("msg", dateAlertMsg);
						}
						
					}
					
				}else if((Integer)httpRequestResult.get("Error")==-1){ //查询结果error -1 调用预约查询的AK值不正确
					result.put("code", "0");
					result.put("msg", "调用预约查询的AK值不正确");
					return result;
				}else if((Integer)httpRequestResult.get("Error")==-2){ //查询结果error -2 调用预约查询传入的参数不正确
					result.put("code", "0");
					result.put("msg", "调用预约查询传入的参数不正确");
					return result;
				}
			}
			
		}
		return result;
	}
	
	
	/**
	 * 南宁模式
	 * @param zjhm 证件号码
	 * @param zjlx 证件类型
	 * @param dept 大厅信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject nanningMode(String zjhm,String zjlx,Dept dept)throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		//判断大厅信息是否为空
		if(null==dept){
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
			return result; 
		}
		
		//根据sfzmhm读取数据库中的预约信息
		NanNingYYXX nanNingYYXX = new NanNingYYXX();
		if("A".equals(zjlx)){
			nanNingYYXX.setNationalId(zjhm);
		}else {
			nanNingYYXX.setOrgId(zjhm);
		}
		//nanNingYYXX.setStationCode(dept.getYydd()); //约束预约地点
		nanNingYYXX.setBookingStatusId("2"); //约束预约状态
		List<NanNingYYXX> yyxxList = yyjkService.findNanNingYYXX(nanNingYYXX);
		//判断是否查到预约信息
		if(null==yyxxList||yyxxList.size()==0){
			result.put("code", "0");
			result.put("msg", "未查到该证件的预约信息");
			return result; 
		}
		NanNingYYXX yyxx = yyxxList.get(0);
		//判断预约地点是否正确
		if(!yyxx.getStationCode().equals(dept.getYydd())){
			result.put("code", "0");
			result.put("msg", "您预约的地点不是本大厅");
			return result; 
		}
		//判断是否已经设置了预约业务相对应的取号业务
		List<Business> businessList = businessService.selectByYYYWMC(yyxx.getServiceTypeCode(),dept.getDeptcode(),dept.getDepthall());
		if(null==businessList||businessList.size()==0){
			result.put("code", "0");
			result.put("msg", "您所预约的业务未关联对应的取号业务,请联系管理员");
			return result; 
		}
		Business business = businessList.get(0);//获取对应取号业务信息
		//判断预约是否再时间段内
		String yysjd = yyxx.getTimeSlot(); //获取预约时间段信息
		if(yysjd.split("-").length==2){
			String begin = yysjd.split("-")[0];
			String end = yysjd.split("-")[1];
			if(!isInTime(begin, end)){
				result.put("code", "0");
				result.put("msg", "不在预约时间段内不能取号  您预约的时间为："+begin+" 至 "+end);
				return result; 
			}
		}
		//根据sfzmhm或者hphm查询是否有违法未处理
		String iscywf = cacheManager.getSystemConfig("iscywf");
		if((1==yyxx.getBookingType()||2==yyxx.getBookingType())&&"0".equals(iscywf)){
			JSONObject temp = isWF(yyxx,business);
			if("0".equals(temp.get("code"))){
				return temp;
			}
		}
		//返回取号所需的business的信息  参照BusinessTypeAction来写的  execute 方法来写的
		result = quhao(business, deptCode, deptHall,null);
		
		return result;
	}
	/**
	 * 中山模式
	 * @param sfzmhm 身份证号码
	 * @param dept 大厅信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject zhongshanMode(String sfzmhm,Dept dept) throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		
		if(null==dept){
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
			return result; 
		}
		
		String outputStr = sfzmhm;
		JSONObject json = new JSONObject();
		json.put("service", "getRecord");
		json.put("acode", dept.getYydd());	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		json.put("date", now);
		json.put("id_number", sfzmhm);
//		json.put("acode", "ZSCGS0001");
//		json.put("date", "2017-12-06");
//		json.put("id_number", "440882199411160510");
		
		outputStr = json.toString();
		System.out.println("传出参数："+outputStr);
		net.sf.json.JSONObject yyJson = HttpRequestUtil.httpRequest("http://10.44.142.68/yuyue/api/Bespeak",  "POST", json);
		//net.sf.json.JSONObject yyJson = net.sf.json.JSONObject.fromObject("{\"code\":1,\"msg\":\"Success\",\"result\":[{\"yuyuelsh\":\"1512460887aNSukePAvF\",\"entrylsh\":\"\",\"name\":\"\u9648\u667a\u6587\",\"id_number\":\"440882199411160510\",\"id_number2\":\"\",\"date\":\"2017-12-06\",\"time\":\"08:00-23:00\",\"acode\":\"ZSCGS0001\",\"bcode\":\"0001\"}]}");
		//回传的 json {"code":1,"msg":"Success","result":[{"yuyuelsh":"1512460887aNSukePAvF","entrylsh":"","name":"\u9648\u667a\u6587","id_number":"440882199411160510","id_number2":"","date":"2017-12-06","time":"08:00-23:00","acode":"ZSCGS0001","bcode":"0001"},{"yuyuelsh":"1512460948mqm8crwRXz","entrylsh":"","name":"\u9648\u667a\u6587","id_number":"440882199411160510","id_number2":"","date":"2017-12-06","time":"08:00-23:00","acode":"ZSCGS0001","bcode":"0001"},{"yuyuelsh":"1512461055eO8mBfQoxz","entrylsh":"","name":"\u9648\u667a\u6587","id_number":"440882199411160510","id_number2":"","date":"2017-12-06","time":"08:00-23:00","acode":"ZSCGS0001","bcode":"0001"}]}
		
		if(yyJson ==null){
			result.put("code", "0");
			result.put("msg", "接口调用发生异常,请联系管理员查看日志");
			return result;
		}
		
		if(yyJson.isEmpty()){
			result.put("code", "0");
			result.put("msg", "查询失败");
			return result;
		}
		
		int code = (Integer) yyJson.get("code");
		if(1!=code){
			result.put("code", "0");
			result.put("msg", "未查到该身份证的预约信息或预约地点不是本大厅");
			return result;
		}
		
		net.sf.json.JSONArray jsonArray = yyJson.getJSONArray("result");
		int size = jsonArray.size();
		int i =0;
		String yyywmc = ""; //预约业务
		String yysjd = "取号失败 您预约的时间段为:";
		String yuyueId =""; //预约查询返回的主键id
		while(i<size){
			net.sf.json.JSONObject item = (net.sf.json.JSONObject) jsonArray.get(i);
			String time = (String) item.get("time");
			String begin = time.split("-")[0];
			String end = time.split("-")[1];
			if(!isInTime(begin,end)){
				i++;
				yysjd+= time;
				continue;
			}
			yyywmc = (String) item.get("bcode");
			yuyueId = (String) item.get("yuyuelsh");
			break;
		}
		if(i==size){
			result.put("code", "0");
			result.put("msg", yysjd);
			return result;
		}
		//判断是否已经设置了预约业务相对应的取号业务
		List<Business> businessList = businessService.selectByYYYWMC(yyywmc,dept.getDeptcode(),dept.getDepthall());
		if(null==businessList||businessList.size()==0){
			result.put("code", "0");
			result.put("msg", "您所预约的业务未关联对应的取号业务,请联系管理员");
			return result; 
		}
		Business business = businessList.get(0);//获取对应取号业务信息
		cacheManager.addYuYueToCache(sfzmhm, yuyueId); //临时将返回的预约id放到缓存
		result = quhao(business, deptCode, deptHall,null);
		return result;
	}
	
	/**
	 * 佛山模式
	 * @param sfzmhm 身份证号码
	 * @param yybefore 预约时间提前(单位分钟)
	 * @param yyafter 预约时间延后(单位分钟)
	 * @param dept 大厅信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject foshanMode(String sfzmhm,String yybefore, String yyafter,Dept dept, String SFZNameA) throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		
		if(null==dept){
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
			return result; 
		}
//		String outputStr = "";
//		StringBuffer sb = new StringBuffer("{\"code\": \"0\",\"msg\": \"请求成功\",\"data\": [{\"idNoMD5\": \"a337f565940d1135c28d30bf6e44506f\",\"statusName\": null,\"seqNo\": \"A1-001\",\"form\": {\"为他人预约\":\"是\",\"他人证件号码\":\"123213\",\"他人姓名\":\"阿斯顿2\",\"车架号\":\"123456\"},\"reservationNumber\": \"C0701201804091242246846\",\"handleLocationId\": \"8492C964AC6944C88C90D958DAAB177C\",\"handleLocationName\": \"市直车管所业务大厅\",\"reservationDate\": \"2018-04-10\",\"timeQuantum\": \"09:30-18:00\",\"reservationUserName\": \"黄沛文\",\"mphone\": \"13630004344\",\"idNo\": \"441827******136587\",\"businessDefId\": \"34BFEDC9E3044642960BB66CF00C4F36\",\"businessDefName\": \"境外/军警 换证\",\"status\": \"4\"},{\"idNoMD5\": \"a337f565940d1135c28d30bf6e44506f\",\"statusName\": null,\"seqNo\": \"A1-001\",\"form\": {\"为他人预约\":\"是\",\"他人证件号码\":\"123213\",\"他人姓名\":\"阿斯顿2\",\"车架号\":\"123456\"},\"reservationNumber\": \"C0701201804091242246846\",\"handleLocationId\": \"8492C964AC6944C88C90D958DAAB177C\",\"handleLocationName\": \"市直车管所业务大厅\",\"reservationDate\": \"2018-04-10\",\"timeQuantum\": \"09:30-18:00\",\"reservationUserName\": \"黄沛文aaa\",\"mphone\": \"13630004344\",\"idNo\": \"441827******136587\",\"businessDefId\": \"34BFEDC9E3044642960BB66CF00C4F36\",\"businessDefName\": \"境外/军警 换证\",\"status\": \"1\"}]}");
//		net.sf.json.JSONObject yyJson = net.sf.json.JSONObject.fromObject(sb.toString().replace("\"{", "{").replace("}\"", "}"));
		String outputStr = sfzmhm;
		net.sf.json.JSONObject yyJson = HttpRequestUtil.httpsRequest("https://10.45.131.130:80/ram-api/reservation-location?auth_code="+dept.getAk(),  "GET", null);
		if(yyJson ==null){
			result.put("code", "0");
			result.put("msg", "网络不通,预约接口调用失败!");
			return result;
		}
		if(yyJson.isEmpty()){
			result.put("code", "0");
			result.put("msg", "查询失败");
			return result;
		}
		int code = Integer.parseInt((String) yyJson.get("code"));
		if(0!=code){
			result.put("code", "0");
			result.put("msg", yyJson.get("msg"));
			return result;
		}
		net.sf.json.JSONArray resultJson2 = yyJson.getJSONArray("data");
		if(resultJson2.isEmpty()){
			result.put("code", "0");
			result.put("msg", "根据授权码查询到预约地点为空,请核对授权码:"+dept.getAk()+" 是否填写正确!");
			return result;
		}
		
		String yydd_ids = "";
		for(int i = 0;i< resultJson2.size();i++){
			net.sf.json.JSONObject tempJsonObj = (net.sf.json.JSONObject)resultJson2.get(i);
			if(i==0){
				yydd_ids += tempJsonObj.get("id"); 
			}else{
				yydd_ids += ","+ tempJsonObj.get("id"); 
			}
		}
		
		JSONObject json = new JSONObject();
		json.put("auth_code", "4973387A1CA84A3DBFB385AFA53F63C6");
		json.put("hi_ids", dept.getYydd());	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		json.put("rese_date", now);
		json.put("idNo", sfzmhm);
		
//		sb = new StringBuffer("{\"code\": \"0\",\"msg\": \"请求成功\",\"data\": [{\"idNoMD5\": \"a337f565940d1135c28d30bf6e44506f\",\"statusName\": null,\"seqNo\": \"A1-001\",\"form\": {\"为他人预约\":\"是\",\"他人证件号码\":\"123213\",\"他人姓名\":\"XXX\",\"车架号\":\"123456\"},\"reservationNumber\": \"C0701201804091242246846\",\"handleLocationId\": \"8492C964AC6944C88C90D958DAAB177C\",\"handleLocationName\": \"市直车管所业务大厅\",\"reservationDate\": \"2018-04-10\",\"timeQuantum\": \"09:30-18:00\",\"reservationUserName\": \"王五\",\"mphone\": \"13630004344\",\"idNo\": \"441827******123123\",\"businessDefId\": \"00001\",\"businessDefName\": \"境外/军警 换证\",\"status\": \"2\",\"confirmTime\": \"2018-6-29 14:39:00\"},"
//				+ "{\"idNoMD5\": \"a337f565940d1135c28d30bf6e44506f\",\"statusName\": null,\"seqNo\": \"A1-001\",\"form\": {\"为他人预约\":\"是\",\"他人证件号码\":\"123213\",\"他人姓名\":\"阿斯顿2\",\"车架号\":\"123456\"},\"reservationNumber\": \"C0701201804091242246846\",\"handleLocationId\": \"8492C964AC6944C88C90D958DAAB177C\",\"handleLocationName\": \"市直车管所业务大厅\",\"reservationDate\": \"2018-04-10\",\"timeQuantum\": \"09:30-15:00\",\"reservationUserName\": \"李四\",\"mphone\": \"13630004344\",\"idNo\": \"441827******123123\",\"businessDefId\": \"0D125A93A61D484D9F6712B2203608B4\",\"businessDefName\": \"车辆过户/变更转出\",\"status\": \"2\",\"confirmTime\": \"2018-6-29 08:39:00\"},"
//				+ "{\"idNoMD5\": \"a337f565940d1135c28d30bf6e44506f\",\"statusName\": null,\"seqNo\": \"A1-001\",\"form\": {\"为他人预约\":\"是\",\"他人证件号码\":\"123213\",\"车架号\":\"123456\"},\"reservationNumber\": \"C0701201804091242246846\",\"handleLocationId\": \"8492C964AC6944C88C90D958DAAB177C\",\"handleLocationName\": \"市直车管所业务大厅\",\"reservationDate\": \"2018-04-10\",\"timeQuantum\": \"15:30-18:00\",\"reservationUserName\": \"张三\",\"mphone\": \"13630004344\",\"idNo\": \"441827******123123\",\"businessDefId\": \"0D125A93A61D484D9F6712B2203608B4\",\"businessDefName\": \"境外/军警 换证\",\"status\": \"1\",\"confirmTime\": null}]}");
//		yyJson = net.sf.json.JSONObject.fromObject(sb.toString().replace("\"{", "{").replace("}\"", "}"));
		outputStr = "auth_code="+dept.getAk()+"&hi_id="+yydd_ids+"&rese_date="+now+"&idNo="+sfzmhm;
		System.out.println("get url：https://10.45.131.130:80/ram-api/cgs-rese-info2?"+outputStr);
		yyJson = HttpRequestUtil.httpsRequest("https://10.45.131.130:80/ram-api/cgs-rese-info2?"+outputStr,  "GET", null);
		if(null ==yyJson){
			result.put("code", "0");
			result.put("msg", "网络不通,预约接口调用失败!");
			return result;
		}
		if(yyJson.isEmpty()){
			result.put("code", "0");
			result.put("msg", "查询失败");
			return result;
		}
		int code2 = Integer.parseInt((String) yyJson.get("code"));
		if(0!=code2){
			result.put("code", "0");
			result.put("msg", yyJson.get("msg"));
			return result;
		}
		
		net.sf.json.JSONArray resultJson = yyJson.getJSONArray("data");
		if(resultJson.isEmpty()|| null==resultJson){
			result.put("code", "0");
			result.put("msg", "未查询到该身份证的预约信息,或该身份证预约的不是本大厅!");
			return result;
		}
		
		//需要查验的业务
		String[] compareString = {"0D125A93A61D484D9F6712B2203608B4","7E34F465FA7E47DA978D7DF189F2E651",
				"CF99D933F6BC4F48A8F181C149EB9A6D","A4CECE700AE54AB5990E20541EB78677","F5DF66FD10064C27BA0E3DF130D038BE",
				"F44DEE1DBEF445E58FC6113EC522249X","DB1E87058E7E4316820124AFA491704X","B1A7D8F735804D50816AA5C898B8D784",
				"650FB40E591E460A91934FC0811E28FB","D6458ADFCA9E4FDFB44C6F12333FCDBE","391ACFCC2DCA4A8D8EC9121B30E1D2AE"};
		ArrayList<String> cyyw = new ArrayList<String>(); //查验业务	
		for (int i = 0; i < compareString.length; i++) {
			cyyw.add(compareString[i]);
		}
		StringBuffer resultTimeMsg = null;
		String CYMsg =	"";
		String YYNOMsg = "";
		boolean isInTime =true; //是否在取号时间段
		boolean isCY = true; //是否查验
		boolean isCYYW = false; //是否为查验业务
		boolean isYYNO = true; //是否为预约身份证
		String isQd ="";//查验是否签到 0 已签到 1 否 2 已查到查验信息或非查验业务
		String fid = "";
		String yid = "";
		net.sf.json.JSONObject inTimeJson = null;
		SimpleDateFormat sdfhm = new SimpleDateFormat("HH:mm");
		for(int i=0 ;i<resultJson.size(); i++){
			resultTimeMsg = new StringBuffer("您预约的时间为：");
			net.sf.json.JSONObject itemJson = (net.sf.json.JSONObject) resultJson.get(i);
			inTimeJson = itemJson;
			if(sfzmhm.length()>6){
				fid = sfzmhm.substring(sfzmhm.length()-6);
			}else{
				fid = sfzmhm;
			}
			String yyname = (String)itemJson.get("reservationUserName");
			if(null!=(String)itemJson.get("idNo")){
				yid = (String)itemJson.get("idNo");
				yid = yid.substring(yid.length()-6);
				if(!fid.equals(yid)){
					isYYNO = false;
					YYNOMsg = "请放入预约人的身份证进行取号并办理相关业务！";
				}else{
					isYYNO = true;
					YYNOMsg = "";
					if(null != SFZNameA && ""!= SFZNameA){
						if(!SFZNameA.equals(yyname)){
							isYYNO = false;
							YYNOMsg = "预约证件和姓名不匹配，请到相关柜台进行实名认证!";
						}else{
							isYYNO = true;
							YYNOMsg = "";
						}
					}
				}
			}
			String yysjd= (String) itemJson.get("timeQuantum");
			String begin = "";
			String begin2 = "";
			String end = "";
			String end2 = "";
			if(yysjd.split("-").length==2){
				begin = yysjd.split("-")[0];
				begin2 = yysjd.split("-")[0];
				end = yysjd.split("-")[1];
				end2 = yysjd.split("-")[1];
			}else{
				System.out.println("预约时间段为"+yysjd);
			}
			Long longBegin = sdfhm.parse(begin).getTime()-Long.parseLong(yybefore)*1000*60;
			Long longEnd = sdfhm.parse(end).getTime()+Long.parseLong(yyafter)*1000*60;
			Date datebegin = new Date();
			datebegin.setTime(longBegin);
			begin = sdfhm.format(datebegin);
			Date dateEnd = new Date();
			dateEnd.setTime(longEnd);
			end = sdfhm.format(dateEnd);
			//如果时需要验证查验的业务 不需要判断是否在取号时间段内
			if(cyyw.contains((String)itemJson.get("businessDefId"))){
				//判断是否查验完成的业务
				isCYYW = true;
				FoShanCLCYXX foShanCLCYXX = new FoShanCLCYXX();
				net.sf.json.JSONObject formJson = (net.sf.json.JSONObject) itemJson.get("form");
				foShanCLCYXX.setClsbdh((String) formJson.get("车架号"));
				if("否".equals((String) formJson.get("为他人预约"))){
					foShanCLCYXX.setSyr((String) itemJson.get("reservationUserName"));
				}else{
					foShanCLCYXX.setSyr((String) formJson.get("他人姓名"));
				}
				
				List<FoShanCLCYXX> FoShanCLCYXXList = null;
				try {
					FoShanCLCYXXList = yyjkService.selectFoShanCLCYXX(foShanCLCYXX);
					
				} catch (Exception e) {
					e.printStackTrace();
					result.put("code", "0");
					result.put("msg", "网络故障,无法查询到六合一分发库数据,请到民警处咨询!");
					return result; 
				}
				if(FoShanCLCYXXList!= null&& !FoShanCLCYXXList.isEmpty()){
					Date cydate = new Date();
					isQd = "2";
					//判断时间段的业务
					if(!isInTime(begin, end)){
						if((cydate.getTime()-FoShanCLCYXXList.get(0).getSqrq().getTime())<65*60*1000){
							isCY =true;
							isInTime = true;
							if("1".equals((String)itemJson.get("status")) || "2".equals((String)itemJson.get("status"))){
								break;
							}
						}else{
							isCY = false;
							isInTime = false;
							resultTimeMsg.append(" "+begin2+" 至 "+end2);
							CYMsg = "您已超过取号时间,请在微信上重新预约(登记业务)!";
						}
					}else if(isInTime(begin, end)){
						isCY =true;
						isInTime = true;
						if("1".equals((String)itemJson.get("status")) || "2".equals((String)itemJson.get("status"))){
							break;
						}
					}
				}else{
					Date confirmTime = null; //预约签到时间
					isInTime = true;
					if(null!=itemJson.getString("confirmTime") && !"".equals(itemJson.getString("confirmTime")) && !"null".equals(itemJson.getString("confirmTime"))){
						Date nowd = new Date();
						SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						confirmTime = fm.parse(itemJson.getString("confirmTime"));
						if((nowd.getTime()-confirmTime.getTime())>30*60*1000 && (nowd.getTime()-confirmTime.getTime())<90*60*1000){
							isQd = "0";
							isCY = true;
							if("2".equals((String)itemJson.get("status"))||"1".equals((String)itemJson.get("status"))){
								break;
							}
						}else{
							isQd = "1";
							isCY = false;
							CYMsg = "未查询到车辆查验信息,请先进行车辆查验再取排队号!";
						}
					}else{
						isQd = "1";
						isCY = false;
						CYMsg = "未查询到车辆查验信息,请先进行车辆查验再取排队号!";
					}
				}
			}else{
				//判断时间段的非查验业务
				isQd = "2";
				isCY = true;
				isCYYW = false;
				if(isInTime(begin, end)){
					isInTime = true;
					if("1".equals((String)itemJson.get("status"))){
						break;
					}
				}else{
					isInTime = false;
					resultTimeMsg.append(" "+begin2+" 至 "+end2);
				}
			}
		}
		
		if(!"1".equals((String)inTimeJson.get("status")) && !isCYYW){
			if("22".equals((String)inTimeJson.get("status"))){
				result.put("code", "0");
				result.put("msg", "请勿重复取号，若未出号请联系工作人员!");
				return result; 
			}else{
				System.out.println("预约状态为"+(String)inTimeJson.get("status"));
			}
		}
		if(!isCY && ("1".equals(isQd)||"2".equals(isQd))){
			result.put("code", "0");
			result.put("msg", CYMsg);
			return result; 
		}
		if(!isInTime){
			resultTimeMsg.append(" 不在预约时间段内不能取号!");
			result.put("code", "0");
			result.put("msg", resultTimeMsg.toString());
			return result; 
		}
		if(!isYYNO){
			result.put("code", "0");
			result.put("msg", YYNOMsg);
			return result; 
		}
		
		//判断是否已经设置了预约业务相对应的取号业务
		List<Business> businessList = businessService.selectByYYYWMC((String) inTimeJson.get("businessDefId"),dept.getDeptcode(),dept.getDepthall());
		if(null==businessList||0==businessList.size()){
			result.put("code", "0");
			result.put("msg", "您所预约的业务未关联对应的取号业务,请联系管理员");
			return result; 
		}
		Business business = businessList.get(0);//获取对应取号业务信息
		cacheManager.addYuYueToCache(sfzmhm, (String) inTimeJson.get("reservationNumber")); //临时将返回的预约id放到缓存
		HashMap<String, String> orderShowInPrintInfo = new HashMap<String, String>();//回传到页面用于打印表显示该内容
		orderShowInPrintInfo.put("timeQuantum", (String)inTimeJson.get("timeQuantum")); //预约时间段
		orderShowInPrintInfo.put("businessDefName", (String)inTimeJson.get("businessDefName")); //预约业务名称
		orderShowInPrintInfo.put("reservationUserName", (String)inTimeJson.get("reservationUserName")); //预约人姓名
		orderShowInPrintInfo.put("idNo", (String)inTimeJson.get("idNo")); //预约人证件号
		net.sf.json.JSONObject inTimeFormJson = (net.sf.json.JSONObject) inTimeJson.get("form");
		if (null != (String) inTimeFormJson.get("为他人预约")) {
			orderShowInPrintInfo.put("orderForOther", (String)inTimeFormJson.get("为他人预约"));
		}
		if ("是".equals((String) inTimeFormJson.get("为他人预约"))) {
			orderShowInPrintInfo.put("otherIdNo", (String)inTimeFormJson.get("他人证件号码"));
			if (null != (String) inTimeFormJson.get("他人姓名")) {
				orderShowInPrintInfo.put("otherName", (String) inTimeFormJson.get("他人姓名"));
			}
		}
		if("0".equals(isQd) && isCYYW){
			orderShowInPrintInfo.put("isQd", "0");//已签到
		}
		result = quhao(business, deptCode, deptHall,orderShowInPrintInfo);
		return result;
		
	}
	/**
	 * 广州模式
	 * @param sfzmhm 身份证号码
	 * @param dept 大厅信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject guangZhouMode(String sfzmhm,Dept dept) throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		//判断大厅信息是否为空
		if(null==dept){
			result.put("code", "0");
			result.put("msg", "未设置大厅信息");
			return result; 
		}
		
		//根据sfzmhm读取数据库中的预约信息
		GuangZhouYYXX guangZhouYYXX = new GuangZhouYYXX();
		guangZhouYYXX.setSfzmhm(sfzmhm);
		List<GuangZhouYYXX> yyxxList = yyjkService.findGuangZhouYYXX(guangZhouYYXX);
		//判断是否查到预约信息
		if(null==yyxxList||yyxxList.size()==0){
			result.put("code", "0");
			result.put("msg", "未查到该证件的预约信息");
			return result; 
		}
		GuangZhouYYXX yyxx = yyxxList.get(0);
		//判断预约地点是否正确
		if(!yyxx.getAddress().equals(dept.getYydd())){
			result.put("code", "0");
			result.put("msg", "您预约的地点不是本大厅");
			return result; 
		}
		//判断是否已经设置了预约业务相对应的取号业务
		List<Business> businessList = businessService.selectByYYYWMC(yyxx.getYyyw(),dept.getDeptcode(),dept.getDepthall());
		if(null==businessList||businessList.size()==0){
			result.put("code", "0");
			result.put("msg", "您所预约的业务未关联对应的取号业务,请联系管理员");
			return result; 
		}
		Business business = businessList.get(0);//获取对应取号业务信息
		//判断预约是否再时间段内
		String yysjd = yyxx.getYysjd(); //获取预约时间段信息
		if(yysjd.split("~").length==2){
			String begin = yysjd.split("~")[0].substring(0, 4);
			String end = yysjd.split("~")[1].substring(0, 4);
			if(!isInTime(begin, end)){
				result.put("code", "0");
				result.put("msg", "不在预约时间段内不能取号  您预约的时间为："+yysjd.split("~")[0]+" 至 "+yysjd.split("~")[1]);
				return result; 
			}
		}
		//根据sfzmhm或者hphm查询是否有违法未处理
//		String iscywf = cacheManager.getSystemConfig("iscywf");
//		if((1==yyxx.getBookingType()||2==yyxx.getBookingType())&&"0".equals(iscywf)){
//			JSONObject temp = isWF(yyxx,business);
//			if("0".equals(temp.get("code"))){
//				return temp;
//			}
//		}
		//返回取号所需的business的信息  参照BusinessTypeAction来写的  execute 方法来写的
		result = quhao(business, deptCode, deptHall, null);
		
		return result;
	}
	/**
	 * 传入开始时间和结束时间
	 * @param StartTime 例如：13：00
	 * @param EndTime 例如：14：00
	 * @return true:在时间段内  false:不在时间段内
	 * @throws ParseException 
	 */
	public static boolean isInTime(String StartTime,String EndTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String now = sdf.format(new Date());
		if(sdf.parse(StartTime).before(sdf.parse(now))&&sdf.parse(EndTime).after(sdf.parse(now))){
			return true;
		}
		return false;
	}
	/**
	 * 查询违法信息
	 * @param yyxx 查询到的预约信息
	 * @param business 业务类型信息
	 * @return
	 * @throws Exception
	 */
	public JSONObject isWF(NanNingYYXX yyxx,Business business) throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		result.put("code", "1");
		int flag = yyxx.getBookingType();//预约类型 1：机动车，2：驾驶证，3：考试，4：机动车年检
		//查询业务对应不可办理业务
		String[] bkblyw = null;//不可办理业务
		if (!"".equals(business.getBkbl()) && null != business.getBkbl()) {
			bkblyw = business.getBkbl().split(",");
		}
		
		//判断办理机动车或驾驶人业务 进行相应的违法查询
		if(1==flag&&null!=yyxx.getPlateNo()&&!"".equals(yyxx.getPlateNo())&&"桂".equals(yyxx.getNoPlatePrefix())){
			//将南宁预约公司给的车辆类型数据转换成 六合一定义的车辆类型号码 
			
			
			//进行机动车违法查询
			System.out.println("预约取号调用违法查询--机动车");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = yyxx.getNoPlateType()+"#"+yyxx.getPlateNo();
			rows[1][0] = "zllx";
			rows[1][1] = "5";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
			if("".equals(lzXML)){
				//网络不通,违法信息查询接口调用失败
				result.put("code", "0");
				result.put("msg", "网络不通,违法信息查询接口调用失败!");
				return result;
			}
			System.out.println("自动违法查询--机动车XML="+lzXML);
//			String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>WVWVY73D388005230#AB</message></head></root>";
			String[] info =  XMLUtils.readXML(lzXML);
			
			if ("1".equals(info[0])) {
				//查询成功
				String[] result1 = info[1].split("#");//返回结果信息数组
				System.out.println("违法查询(接口)结果"+info[0]+"---车辆识别代码="+result1[0]+"状态="+result1[1]);
				System.out.println("违法查询接口返回信息长度:"+result1.length);
				//判断违法信息返回结果是否包含不可办理业务
				if (result1.length==2 && bkblyw != null) {
					String ztvo = "";//违法信息提示内容
					for (int j = 0; j < result1[1].length(); j++) {
						for (int i = 0; i < bkblyw.length; i++) {
							List<Code> listcode = codeService.getCodeByDmlbAndSxh("2002", bkblyw[i], "1", deptCode, deptHall);
							if (null!=listcode&&listcode.size()>0) {
								if (result1[1].substring(j, j+1).equals(listcode.get(0).getDm())) {
									if ("A".equals(result1[1].substring(j, j+1))) {
										ztvo +="正常.";
									}else if ("B".equals(result1[1].substring(j, j+1))) {
										ztvo +="转出.";
									}else if ("C".equals(result1[1].substring(j, j+1))) {
										ztvo +="被盗抢.";
									}else if ("D".equals(result1[1].substring(j, j+1))) {
										ztvo +="停驶.";
									}else if ("E".equals(result1[1].substring(j, j+1))) {
										ztvo +="注销.";
									}else if ("G".equals(result1[1].substring(j, j+1))) {
										ztvo +="违法未处理.";
									}else if ("H".equals(result1[1].substring(j, j+1))) {
										ztvo +="海关监管.";
									}else if ("I".equals(result1[1].substring(j, j+1))) {
										ztvo +="事故未处理.";
									}else if ("J".equals(result1[1].substring(j, j+1))) {
										ztvo +="嫌疑车.";
									}else if ("K".equals(result1[1].substring(j, j+1))) {
										ztvo +="查封.";
									}else if ("L".equals(result1[1].substring(j, j+1))) {
										ztvo +="扣留.";
									}else if ("M".equals(result1[1].substring(j, j+1))) {
										ztvo +="强制注销.";
									}else if ("N".equals(result1[1].substring(j, j+1))) {
										ztvo +="事故逃逸.";
									}else if ("O".equals(result1[1].substring(j, j+1))) {
										ztvo +="锁定.";
									}
								}
							}
						}
					}
					//判断有不可办理对应的违法信息内容
					if(ztvo.length()!=0){
						result.put("code", "0");
						result.put("msg", ztvo);
						return result;
					}
				}
			}
		}else if(2==flag&&null!=yyxx.getNationalId()&&!"".equals(yyxx.getNationalId())){
			//驾驶证违法查询
			System.out.println("预约取号调用违法查询--驾驶证");
			String rows[][] = new String[2][2];
			rows[0][0] = "lsh";
			rows[0][1] = yyxx.getNationalId();
			rows[1][0] = "zllx";
			rows[1][1] = "6";
			String strXML = XMLUtils.createXML(XMLUtils.TYPE_WRITE, rows);
			String lzXML = TrffClient.write_nbjk("02C43", strXML);
			if("".equals(lzXML)){
				//网络不通,违法信息查询接口调用失败
				result.put("code", "0");
				result.put("msg", "网络不通,违法信息查询接口调用失败!");
				return result;
			}
			System.out.println("自动违法查询--驾驶人XML="+lzXML);
//			String lzXML = "<?xml version='1.0' encoding='GBK'?><root><head><code>1</code><message>640100878988#C1#2020-04-08#2014-04-08#1#2014-04-08#2020-04-08#0#ABCL#A#王双龙#1#1985-12-04#156#330000#甘肃省环县合道乡陶洼子行政村小王河畔队#640105#宁夏银川市西夏区朔方路散居69号#750021##13895308918#505289</message></head></root>";
			String[] info =  XMLUtils.readXML(lzXML);
//			String[] result =  {"1","未去到身份证明号码为520123198509105816的驾驶证信息"}; 
			
			if ("1".equals(info[0])) {
				//查询成功
				String[] result1 = info[1].split("#");
				if(result1.length>1){
					System.out.println("违法查询(接口)结果"+info[0]+"---档案编号="+result1[0]+"状态="+result1[8]);
				}else {
					System.out.println(info[1]);
				}
				System.out.println("违法查询接口返回信息长度:"+result1.length);
				if (result1.length>=9 && bkblyw != null) {
					String ztvo = "";
					for (int j = 0; j < result1[8].length(); j++) {
						for (int i = 0; i < bkblyw.length; i++) {
							List<Code> listcode = codeService.getCodeByDmlbAndSxh("2003", bkblyw[i], "1", deptCode, deptHall);
							if (null!=listcode&&listcode.size()>0) {
								if (result1[8].substring(j, j+1).equals(listcode.get(0).getDm())) {
									if ("A".equals(result1[8].substring(j, j+1))) {
										ztvo +="正常.";
									}else if ("B".equals(result1[8].substring(j, j+1))) {
										ztvo +="超分.";
									}else if ("C".equals(result1[8].substring(j, j+1))) {
										ztvo +="转出.";
									}else if ("D".equals(result1[8].substring(j, j+1))) {
										ztvo +="暂扣.";
									}else if ("E".equals(result1[8].substring(j, j+1))) {
										ztvo +="撤销.";
									}else if ("F".equals(result1[8].substring(j, j+1))) {
										ztvo +="吊销.";
									}else if ("G".equals(result1[8].substring(j, j+1))) {
										ztvo +="注销.";
									}else if ("H".equals(result1[8].substring(j, j+1))) {
										ztvo +="违法未处理.";
									}else if ("I".equals(result1[8].substring(j, j+1))) {
										ztvo +="事故未处理.";
									}else if ("J".equals(result1[8].substring(j, j+1))) {
										ztvo +="停止使用.";
									}else if ("K".equals(result1[8].substring(j, j+1))) {
										ztvo +="扣押.";
									}else if ("L".equals(result1[8].substring(j, j+1))) {
										ztvo +="锁定.";
									}else if ("M".equals(result1[8].substring(j, j+1))) {
										ztvo +="逾期未换证.";
									}else if ("N".equals(result1[8].substring(j, j+1))) {
										ztvo +="延期换证.";
									}else if ("P".equals(result1[8].substring(j, j+1))) {
										ztvo +="延期体检.";
									}else if ("R".equals(result1[8].substring(j, j+1))) {
										ztvo +="注销可恢复.";
									}else if ("S".equals(result1[8].substring(j, j+1))) {
										ztvo +="逾期未审验.";
									}else if ("T".equals(result1[8].substring(j, j+1))) {
										ztvo +="延期审验.";
									}else if ("U".equals(result1[8].substring(j, j+1))) {
										ztvo +="扣留.";
									}
								}
							}
						}
					}
					//判断有不可办理对应的违法信息内容
					if(ztvo.length()!=0){
						result.put("code", "0");
						result.put("msg", ztvo);
						return result;
					}
				}
			}
		}else{
			//信息不全暂不进行违法查询
			
		}
		
		return result;
	}
	/** 构建取号需要的json内容
	 */
	public JSONObject quhao(Business business,String deptCode,String deptHall,HashMap<String, String> orderShowInPrintInfo)throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		Number countnum = new Number();
		countnum.setDeptCode(deptCode);
		countnum.setDeptHall(deptHall);
		List<Number> numbersCountList = numberService.showWaitRs(countnum);//查询等待人数 
		
		String helpInfo=business.getHelp_info();
		String nameAndShul=business.getName()+"@0人";
		for (Number nu : numbersCountList) {
			if(nu.getId().equals(business.getId())){
				String shul ="0";
				if(null != nu.getTypeCount()){
					shul = nu.getTypeCount();
				}
				nameAndShul= business.getName()+"@"+shul+"人";
				break;
			}
		}
		JSONObject obj = new JSONObject();
		JSONObject result = new JSONObject();
		
		String ismessage = cacheManager.getSystemConfig("ismessage");//是否启用短信通知
		String iscywf = cacheManager.getSystemConfig("iscywf");
		String cywffs = cacheManager.getSystemConfig("cywffs");
		String isOpenTztd = cacheManager.getSystemConfig("isOpenTztd");//是否启用通知提档
		String sfkqcyyw = cacheManager.getSystemConfig("sfkqcyyw");//是否开启查验业务
		String xzywmewm = cacheManager.getSystemConfig("xzywmewm");//选择一维码或二维码
		result.put("isSuccess", true);
		result.put("iscywf", iscywf);
		result.put("cywffs", cywffs);
		result.put("isOpenTztd", isOpenTztd);
		result.put("ismessage", ismessage);
		result.put("sfkqcyyw", sfkqcyyw);//是否开启查验业务
		result.put("xzywmewm", xzywmewm);//选择一维码或二维码
		obj.put("helps", helpInfo);
		obj.put("name", nameAndShul);
		obj.put("id", business.getId());//业务类型编号
		obj.put("names",business.getName());
		obj.put("flag", business.getFlag());//一级菜单编号
		obj.put("prefix", business.getPreNum());//前缀
		obj.put("code", business.getQueueCode());//队列编号
		obj.put("isOpenTztd", business.getIsOpenTztd());
		
		obj.put("ismessage", ismessage);
		obj.put("sfkqcyyw", sfkqcyyw);//是否开启查验业务
		obj.put("xzywmewm", xzywmewm);//选择一维码或二维码
		obj.put("isOpenZhiWen", business.getIsOpenZhiWen());
		obj.put("twotype", business.getTwotype());
		obj.put("waitingarea", business.getWaitingarea());
		obj.put("dybd", business.getBiaodan());
		if (!"".equals(business.getBdywmc()) && business.getBdywmc() != null && business.getBdywmc().split("@").length>1) {
			obj.put("bdywmc", business.getBdywmc().split("@")[1]);
			System.out.println("表单业务名称1="+business.getBdywmc().split("@")[1]);
		}else {
			obj.put("bdywms", "no");
		}
		result.put("code", "1");
		result.put("business", obj);
		JSONArray typeBsns = new JSONArray();
		typeBsns.put(obj);
		result.put("datas", typeBsns);
		if(orderShowInPrintInfo!=null){
			for (String key:orderShowInPrintInfo.keySet()) {
				result.put(key, orderShowInPrintInfo.get(key));
			}
			System.out.println("打印的预约业务为:"+orderShowInPrintInfo.get("businessDefName"));
		}else{
			System.out.println("orderShowInPrintInfo :打印内容为空");
		}
		return result;
	}
	
	public JSONObject weixinyuyueMode(String sfzmhm, Dept dept)  throws Exception{
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		JSONObject result = new JSONObject();
		
		
		
		//判断是否已经设置了预约业务相对应的取号业务
		/* 修改 过List<Business> businessList = 
		 * Business business = businessList.get(0);//获取对应取号业务信息
		 * */
		String ismessage = cacheManager.getSystemConfig("ismessage");//是否启用短信通知
		String iscywf = cacheManager.getSystemConfig("iscywf");
		String cywffs = cacheManager.getSystemConfig("cywffs");
		String isOpenTztd = cacheManager.getSystemConfig("isOpenTztd");//是否启用通知提档
		String sfkqcyyw = cacheManager.getSystemConfig("sfkqcyyw");//是否开启查验业务
		String xzywmewm = cacheManager.getSystemConfig("xzywmewm");//选择一维码或二维码
		Business business = new  Business("1", "微信预约", "1", "01", "", "", "A", "1", "1", ismessage, "01", "", "", "1", deptCode, deptHall, "", "1000", "", "", "", "", "", "0", "0", sfkqcyyw, xzywmewm);
		
		cacheManager.addYuYueToCache(sfzmhm, "A"); //临时将返回的预约id放到缓存
		HashMap<String, String> orderShowInPrintInfo = new HashMap<String, String>();//回传到页面用于打印表显示该内容
		orderShowInPrintInfo.put("timeQuantum", "9.30-10.30"); //预约时间段
		orderShowInPrintInfo.put("businessDefName", "微信预约"); //预约业务名称
		orderShowInPrintInfo.put("reservationUserName", "贾星云"); //预约人姓名
		orderShowInPrintInfo.put("idNo", sfzmhm); //预约人证件号
		if (null != "1") {
			orderShowInPrintInfo.put("orderForOther", "1");
		}
		if ("是".equals("是")) {
			orderShowInPrintInfo.put("otherIdNo", "123");
			if (null !="aaa") {
				orderShowInPrintInfo.put("otherName", "aaa");
			}
		}
		result = quhao(business, deptCode, deptHall,orderShowInPrintInfo);
		return result;
	}
}
