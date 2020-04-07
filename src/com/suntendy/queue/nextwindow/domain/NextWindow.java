package com.suntendy.queue.nextwindow.domain;

public class NextWindow {

	private String id;				//顺序号
	private String dmlb;		//一级菜单编号
	private String nextwindowval;//窗口提示信息
	private String dmz;			//业务类型编号
	private String dmsm1;		//业务类型名称
	private String ywyybh;		//业务原因编号
	private String ywmc;		//业务原因名
	private String operation; 	// 操作
	private String deptCode;//部门编码
	private String deptHall;//大厅
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDmlb() {
		return dmlb;
	}
	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}
	public String getDmz() {
		return dmz;
	}
	public void setDmz(String dmz) {
		this.dmz = dmz;
	}
	public String getNextwindowval() {
		return nextwindowval;
	}
	public void setNextwindowval(String nextwindowval) {
		this.nextwindowval = nextwindowval;
	}
	public String getDmsm1() {
		return dmsm1;
	}
	public void setDmsm1(String dmsm1) {
		this.dmsm1 = dmsm1;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getYwyybh() {
		return ywyybh;
	}
	public void setYwyybh(String ywyybh) {
		this.ywyybh = ywyybh;
	}
	public String getYwmc() {
		return ywmc;
	}
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	
}
