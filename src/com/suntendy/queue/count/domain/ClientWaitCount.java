package com.suntendy.queue.count.domain;

public class ClientWaitCount {
	private String uniqueid; //部门编号
	private String deptname;  //部门名称
	private String deptCodeName;
	private String deptCode;
	private String deptHall;
	private String sum;      //人数
	private String smaller_five; //小于五分钟
	private String five_ten;//五分和十分之间
	private String more_ten;//超过十分钟
	private String avg;  //平均等待时间
	private String ave_waittime;
	private String ave_worktime;
	
	public String getAve_waittime() {
		return ave_waittime;
	}
	public void setAve_waittime(String ave_waittime) {
		this.ave_waittime = ave_waittime;
	}
	public String getAve_worktime() {
		return ave_worktime;
	}
	public void setAve_worktime(String ave_worktime) {
		this.ave_worktime = ave_worktime;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getSmaller_five() {
		return smaller_five;
	}
	public void setSmaller_five(String smaller_five) {
		this.smaller_five = smaller_five;
	}
	public String getFive_ten() {
		return five_ten;
	}
	public void setFive_ten(String five_ten) {
		this.five_ten = five_ten;
	}
	public String getMore_ten() {
		return more_ten;
	}
	public void setMore_ten(String more_ten) {
		this.more_ten = more_ten;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getDeptCodeName() {
		return deptCodeName;
	}
	public void setDeptCodeName(String deptCodeName) {
		this.deptCodeName = deptCodeName;
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
}