package com.suntendy.queue.count.domain;

public class QueueWaitCount {
	private String name; //队列名称
	private String code;  //队列编号
	private String total;      //人数
	private String ave_waittime; //平均等待时间
	private String ave_worktime;//平均办理时间
	private String  deptname;
	private String deptCodeName;
	private String deptCode;
	private String deptHall;
	private String windowid;
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
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
	public String getWindowid() {
		return windowid;
	}
	public void setWindowid(String windowid) {
		this.windowid = windowid;
	}
	
}