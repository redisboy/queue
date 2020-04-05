package com.suntendy.queue.queue.service.impl;

import java.util.List;

import com.suntendy.queue.queue.dao.ICallOutDao;
import com.suntendy.queue.queue.dao.INumberDao;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.service.ICallOutService;
import com.suntendy.queue.queue.service.ICodeService;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.exception.TrffException;
import com.suntendy.queue.util.trff.TrffUtils;

public class CallOutServiceImpl implements ICallOutService {

	private ICallOutDao calloutDao;
	private INumberDao numberDao;
	private ICodeService codeService;
	
	public ICallOutDao getCalloutDao() {
		return calloutDao;
	}
	public void setCalloutDao(ICallOutDao calloutDao) {
		this.calloutDao = calloutDao;
	}

	/**
	 * 设置评价卡号状态
	 */
	public int updateCallOutStatus(String yhdh) throws Exception {
		return calloutDao.updateCallOutStatus(yhdh);
	}
	
	/**
	 * 异常业务处理
	 */
	public String exceptionHanDling(Number searchNumber) {
		CacheManager cacheManager = CacheManager.getInstance();
		NumberManager numberManager = NumberManager.getInstance();
		
		String res="处理完成";
		//根据用户编号获取人员信息
		try {
			String serialNum = "";
				if ("0".equals(cacheManager.getSystemConfig("isUseInterface"))) {
					//上传过号信息
					String deptCode = cacheManager.getOfDeptCache("deptCode");
					String deptHall = cacheManager.getOfDeptCache("deptHall");
					System.out.println("处理异常业务号码："+searchNumber.getClientno());
					serialNum = deptCode + deptHall + searchNumber.getClientno().substring(13,searchNumber.getClientno().length());
					String jym = serialNum + deptCode.substring(0, 6) + "#0#"
						+ DateUtils.dateToString("yyyyMMdd") + deptCode.substring(6);
					String[] result = TrffUtils.saveEvaluationInfo(serialNum, "0", codeService.jm(jym));
					System.out.println("处理异常业务信息结果：[" + searchNumber.getCode() + "][" + searchNumber.getClientno() + "][" + result[1] + "]");
					if ("2".equals(result[0])) {
						throw new TrffException(result[1]);
					} else if (!"1".equals(result[0]) && !"2".equals(result[0])) {
						throw new Exception(result[1]);
					}
				}
				//写入修改记录表
				numberDao.saveExceptdata(searchNumber);
				//修改本地信息
				Number numberLocal = new Number();
				numberLocal.setId(searchNumber.getId());
				numberLocal.setStatus("3");
				numberDao.updateNumber(numberLocal);
				
				//更新内存
				numberManager.removeCallNumber(searchNumber.getCode());
				//cacheManager.updateLoginIpStatus(searchNumber.getCode());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res="处理失败";
			e.printStackTrace();
		}
		
		return res;
	}
	public INumberDao getNumberDao() {
		return numberDao;
	}
	public void setNumberDao(INumberDao numberDao) {
		this.numberDao = numberDao;
	}
	public ICodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

	public List<Number> query6he1Callout(String serialNum) throws Exception {
		return numberDao.query6he1Callout(serialNum);
	}

	public List<Number> query6he1Valuerecord(String serialNum) throws Exception {
		return numberDao.query6he1Valuerecord(serialNum);
	}

	public int updateValuerecordStatus(String num, String serialNum)
			throws Exception {
		return numberDao.updateValuerecordStatus(num, serialNum);
	}
	public List<Number> getBarIdBySerialNum(Number number) throws Exception {
		return numberDao.getBarIdBySerialNum(number);
	}
	@Override
	public List<Number> getValueRecordAllById(Number number) throws Exception {
		return numberDao.getValueRecordAllById(number);
	}
	@Override
	public List<Number> getvaluerecordByClientno(Number number)
			throws Exception {
		return numberDao.getvaluerecordByClientno(number);
	}
	
}
