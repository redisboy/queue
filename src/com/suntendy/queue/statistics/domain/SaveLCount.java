package com.suntendy.queue.statistics.domain;

public class SaveLCount {
	/**
	 * 安全日志统计
	 * 
	 * @author lsen 2019年3月23日
	 */
	private String userName;
	private String saveCel;
	private String saveCount;
	public String deptCode;
	public String deptHall;
	public String rTime;
	public String lTime;
	public String originIp;
	
	public String getOriginIp() {
		return originIp;
	}
	public void setOriginIp(String originIp) {
		this.originIp = originIp;
	}
	public String getlTime() {
		return lTime;
	}
	public void setlTime(String lTime) {
		this.lTime = lTime;
	}

	public String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public SaveLCount(){
		
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSaveCel() {
		return saveCel;
	}
	public void setSaveCel(String saveCel) {
		this.saveCel = saveCel;
	}
	public String getSaveCount() {
		return saveCount;
	}
	public void setSaveCount(String saveCount) {
		this.saveCount = saveCount;
	}
	
	public SaveLCount(String userName,String saveCel,String saveCount, String deptCode, String deptHall, String result){
		super();
		this.userName = userName;
		this.saveCel = saveCel;
		this.saveCount = saveCount;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
		this.result = result;
		
	}
	
	@Override
	public String toString() {
		return "SaveLCount [userName=" + userName + ", saveCel=" + saveCel + ", saveCount="
				+ saveCount + ", result=" + result + ", deptCode=" + deptCode + ", deptHall=" + deptHall + "]";
	}

}
