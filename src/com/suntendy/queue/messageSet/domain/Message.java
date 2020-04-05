package com.suntendy.queue.messageSet.domain;

public class Message {
	private String id;
	private String context; //短信内容
	private int nowaitcount;//无需等待人数
	private String deptCode;
	private String deptHall;
	
	
	public int getNowaitcount() {
		return nowaitcount;
	}
	public void setNowaitcount(int nowaitcount) {
		this.nowaitcount = nowaitcount;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

}
