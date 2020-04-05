package com.suntendy.queue.count.domain;

public class GuaQiCount {
	
	private String code;//员工编号
	private String xm;//员工编号
	private String gqcount;//挂起数量
	private String gqyccount;//挂起异常数量
	private String ave;//挂起平均时间
	private String operation;//操作
	private String  deptname;
	private String deptCodeName;
	private String deptCode;
	private String deptHall;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGqcount() {
		return gqcount;
	}
	public void setGqcount(String gqcount) {
		this.gqcount = gqcount;
	}
	public String getGqyccount() {
		return gqyccount;
	}
	public void setGqyccount(String gqyccount) {
		this.gqyccount = gqyccount;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
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
	public String getAve() {
		return ave;
	}
	public void setAve(String ave) {
		this.ave = ave;
	}
	
	

}
