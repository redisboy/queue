package com.suntendy.queue.count.domain;
/**
 * 代理人排队情况统计
 * */
public class AgentWaitCount {
	
	private String idcard;//身份证
	private String name;//姓名
	private String status;//状态
	private String begintime;
	private String endtime;
	private String x1;//未呼叫
	private String x2;//正在办理
	private String x3;//过号
	private String x6;//已经评价（结束）
	private String count;//总排队数
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getX1() {
		return x1;
	}
	public void setX1(String x1) {
		this.x1 = x1;
	}
	public String getX2() {
		return x2;
	}
	public void setX2(String x2) {
		this.x2 = x2;
	}
	public String getX3() {
		return x3;
	}
	public void setX3(String x3) {
		this.x3 = x3;
	}
	public String getX6() {
		return x6;
	}
	public void setX6(String x6) {
		this.x6 = x6;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}