package com.suntendy.queue.reservation.domain;

public class Reservation {

	private String zjlx;//证件类型 
	private String sfzmhm;//身份证明号码
	private String ywlx;//预约业务类型
	private String yykssj;//预约开始时间
	private String yyjssj;//预约结束时间
	private String deptCode;//管理部门
	private String deptHall;//大厅编号
	private String flag;//标记
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public String getYykssj() {
		return yykssj;
	}
	public void setYykssj(String yykssj) {
		this.yykssj = yykssj;
	}
	public String getYyjssj() {
		return yyjssj;
	}
	public void setYyjssj(String yyjssj) {
		this.yyjssj = yyjssj;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
