package com.suntendy.queue.statistics.domain;

public class UserLCount {

	/**
	 * 用户登录统计
	 * 
	 * @author lsen 2019年3月23日
	 */
	public String userName;
	public String loginIP;
	public String loginCount;
	public String counts;
	public String deptCode;
	public String deptHall;
	public String rTime;
	public String lTime;
	public String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public UserLCount(){
		
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
	public UserLCount(String userName,String loginIP,String loginCount,String counts, String deptCode, String deptHall, String result){
		super();
		this.userName = userName;
		this.loginIP = loginIP;
		this.loginCount = loginCount;
		this.counts = counts;
		this.result = result;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
	}
	
	@Override
	public String toString() {
		return "UserLCount [userName=" + userName + ", loginIP=" + loginIP + ", loginCount="
				+ loginCount + ", counts=" + counts + ", result=" + result + ", deptCode=" + deptCode + ", deptHall=" + deptHall + "]";
	}
	
}
