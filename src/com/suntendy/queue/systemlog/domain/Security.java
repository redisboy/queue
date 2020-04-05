package com.suntendy.queue.systemlog.domain;

import java.util.Date;

/**
 * 安全日志
 * 
 * @author lsen 2019年3月21日
 */
public class Security {
	public String userName;
	public String event;
	public String originIp;
	public String eventType;
	public Date recordTime;
	public String result;
	public String resultDepict;
	public String deptCode;
	public String deptHall;
	public String rTime;
	public String RSACheck;
	public int id;
	public byte[] content;
	public String opName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDepict() {
		return resultDepict;
	}

	public void setResultDepict(String resultDepict) {
		this.resultDepict = resultDepict;
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

	public String getrTime() {
		return rTime;
	}

	public void setrTime(String rTime) {
		this.rTime = rTime;
	}

	public Security(String userName, String event, String originIp,
			String eventType, Date recordTime, String result,
			String resultDepict, String RSACheck,String deptCode, String deptHall) {
		super();
		this.userName = userName;
		this.event = event;
		this.originIp = originIp;
		this.eventType = eventType;
		this.recordTime = recordTime;
		this.result = result;
		this.resultDepict = resultDepict;
		this.RSACheck=RSACheck;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
	}

	public Security() {
		super();
	}

	@Override
	public String toString() {
		return "Security [userName=" + userName + ", event=" + event
				+ ", originIp=" + originIp + ", eventType=" + eventType
				+ ", recordTime=" + recordTime + ", result=" + result
				+ ", resultDepict=" + resultDepict + ", deptCode=" + deptCode
				+ ", deptHall=" + deptHall + "]";
	}

}
