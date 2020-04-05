package com.suntendy.queue.warn.domain;

public class Warn {
	
	private String code;
	private String name;
	private String begintime;
	private String endtime;
	private String jgsj;
	private String barid;
	private String tjms;  //统计模式
	private String ksrq;  //开始时间
	private String jsrq;  //结束时间
	private String depthall;
	private String deptcode;
	private String nowDate;  //系统时间
	private String jgyy; //警告原因
	public String getJgyy() {
		return jgyy;
	}
	public void setJgyy(String jgyy) {
		this.jgyy = jgyy;
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
	public String getTjms() {
		return tjms;
	}
	public void setTjms(String tjms) {
		this.tjms = tjms;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getBarid() {
		return barid;
	}
	public void setBarid(String barid) {
		this.barid = barid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getJgsj() {
		return jgsj;
	}
	public void setJgsj(String jgsj) {
		this.jgsj = jgsj;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
}
