package com.suntendy.queue.systemlog.domain;

import java.util.Date;

/**
 * 操作日志
 * 
 * @author lsen 2019年3月21日
 */
public class Operate {
	public String userName;
	public String event;
	public String originIp;
	public String module;
	public String moduleType;
	public String eventType;
	public Date operateTime;
	public String coreBusiness;
	public String result;
	public String resultDepict;
	public String deptCode;
	public String deptHall;
	public String oTime;
	public String RSACheck;
	public String opName;
	private byte[] content;
	public int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getRSACheck() {
		return RSACheck;
	}

	public void setRSACheck(String rSACheck) {
		RSACheck = rSACheck;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getOriginIp() {
		return originIp;
	}

	public void setOriginIp(String originIp) {
		this.originIp = originIp;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getCoreBusiness() {
		return coreBusiness;
	}

	public void setCoreBusiness(String coreBusiness) {
		this.coreBusiness = coreBusiness;
	}

	public String getResultDepict() {
		return resultDepict;
	}

	public void setResultDepict(String resultDepict) {
		this.resultDepict = resultDepict;
	}

	public String getoTime() {
		return oTime;
	}

	public void setoTime(String oTime) {
		this.oTime = oTime;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptHall() {
		return deptHall;
	}

	public void setDeptHall(String deptHall) {
		this.deptHall = deptHall;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Operate [userName=" + userName + ", event=" + event
				+ ", originIp=" + originIp + ", module=" + module
				+ ", moduleType=" + moduleType + ", eventType=" + eventType
				+ ", operateTime=" + operateTime + ", coreBusiness="
				+ coreBusiness + ", resultDepict=" + resultDepict
				+ ", deptCode=" + deptCode + ", deptHall=" + deptHall + "]";
	}

	public Operate() {
		super();
	}

	public Operate(String userName, String event, String originIp,
			String module, String moduleType, String eventType,
			Date operateTime, String coreBusiness, String resultDepict,
			String RSACheck,String deptCode, String deptHall) {
		super();
		this.userName = userName;
		this.event = event;
		this.originIp = originIp;
		this.module = module;
		this.moduleType = moduleType;
		this.eventType = eventType;
		this.operateTime = operateTime;
		this.coreBusiness = coreBusiness;
		this.resultDepict = resultDepict;
		this.RSACheck = RSACheck;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
	}

	public Operate(String userName, String originIp, String module,
			String eventType, String coreBusiness, String deptCode,
			String deptHall) {
		super();
		this.userName = userName;
		this.originIp = originIp;
		this.module = module;
		this.eventType = eventType;
		this.coreBusiness = coreBusiness;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
	}

}
