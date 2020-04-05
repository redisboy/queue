package com.suntendy.queue.hmd.domain;

public class Hmd {

	private String name;	//姓名
	private String id;		//身份证号
	private String company;	//单位
	private String quhaos;  //限制取号次数
	private String reason;	//原因
	private String operation;//操作
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getQuhaos() {
		return quhaos;
	}
	public void setQuhaos(String quhaos) {
		this.quhaos = quhaos;
	}
	
	
}
