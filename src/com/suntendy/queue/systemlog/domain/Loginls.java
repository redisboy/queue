package com.suntendy.queue.systemlog.domain;

import java.util.Date;

/**
 * 登录日志
 * 
 * @author lsen 2019年3月21日
 */
public class Loginls {
	public String userName;
	public String name;
	public String event;
	public String originIp;
	public String eventType;
	public Date recordTime;
	public Date leaveTime;
	public String result;
	public String resultDepict;
	public String deptCode;
	public String deptHall;
	public String rTime;
	public String lTime;
	public String RSACheck;
	public String recTime;
	public String leaTime;
	
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}
	public String getLeaTime() {
		return leaTime;
	}
	public void setLeaTime(String leaTime) {
		this.leaTime = leaTime;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
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
	public String getlTime() {
		return lTime;
	}
	public void setlTime(String lTime) {
		this.lTime = lTime;
	}
	public Loginls(String userName, String name, String event, String originIp,
			String eventType, Date recordTime, Date leavetTime, String result,
			String resultDepict, String deptCode, String deptHall) {
		super();
		this.userName = userName;
		this.name = name;
		this.event = event;
		this.originIp = originIp;
		this.eventType = eventType;
		this.recordTime = recordTime;
		this.leaveTime = leaveTime;
		this.result = result;
		this.resultDepict = resultDepict;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
	}
	public Loginls() {
		super();
	}
	@Override
	public String toString() {
		return "Loginls [userName=" + userName + ", name=" + name + ", event="
				+ event + ", originIp=" + originIp + ", eventType=" + eventType
				+ ", recordTime=" + recordTime + ", leaveTime=" + leaveTime
				+ ", result=" + result + ", resultDepict=" + resultDepict
				+ ", deptCode=" + deptCode + ", deptHall=" + deptHall + "]";
	}
	
	
}
