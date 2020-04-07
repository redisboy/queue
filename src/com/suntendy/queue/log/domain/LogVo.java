package com.suntendy.queue.log.domain;

public class LogVo {

	private String id;
	private String createTime;//创建时间
	private String logContent;//内容
	private String logflag;//标记
	private String code;//编号
	private String xm;  //姓名
	private String sxh;//顺序号
	private String sl;//数量
	private String loginIP;
	private String status;
	private String endTime;
	private String barId;
	private String operate;//操作
	private String tjms;  //统计模式
	private String ksrq;  //开始时间
	private String jsrq;  //结束时间
	private String nowDate;  //结束时间
	
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBarId() {
		return barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLogflag() {
		return logflag;
	}
	public void setLogflag(String logflag) {
		this.logflag = logflag;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	
	
}
