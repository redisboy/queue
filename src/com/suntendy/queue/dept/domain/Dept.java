package com.suntendy.queue.dept.domain;

public class Dept {
	private String id;
	private String deptname;//大厅名称
	private String depthall;//大厅编号
	private String deptcode;//管理部门代码
	private String deptcodename;//管理部门名称
	private String serversip;//应用服务器IP
	private String website;//访问地址
	private String operation;//操作
	private String ak;//调用预约查询的KEY
	private String yydd;//预约地点
	private String sbkzjsjbh;//设备控制计算机编号
	
	
	public String getSbkzjsjbh() {
		return sbkzjsjbh;
	}
	public void setSbkzjsjbh(String sbkzjsjbh) {
		this.sbkzjsjbh = sbkzjsjbh;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getYydd() {
		return yydd;
	}
	public void setYydd(String yydd) {
		this.yydd = yydd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDepthall() {
		return depthall;
	}
	public void setDepthall(String depthall) {
		this.depthall = depthall;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getDeptcodename() {
		return deptcodename;
	}
	public void setDeptcodename(String deptcodename) {
		this.deptcodename = deptcodename;
	}
	public String getServersip() {
		return serversip;
	}
	
	public void setServersip(String serversip) {
		this.serversip = serversip;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
