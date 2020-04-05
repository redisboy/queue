package com.suntendy.queue.count.domain;

public class GetNumberInfoCount {
	private String serverip;      //取号机ip
	private String codequeue;     //队列编号
	private String entertime;      //取号时间
	private String clientno;     //取号值
	private String idtype;       //证件类型
	private String idnumber;     //证件号码
	private String businesscount;  //业务笔数
	private String isagent;   //是否代理人
	private String dtnm;
	private String xm;//经办人
	private String serialNum;//顺序号
	private String lsh;//流水号
	private String operation; 	// 操作
	
	
	
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getServerip() {
		return serverip;
	}
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	public String getCodequeue() {
		return codequeue;
	}
	public void setCodequeue(String codequeue) {
		this.codequeue = codequeue;
	}
	public String getEntertime() {
		return entertime;
	}
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getClientno() {
		return clientno;
	}
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getBusinesscount() {
		return businesscount;
	}
	public void setBusinesscount(String businesscount) {
		this.businesscount = businesscount;
	}
	public String getIsagent() {
		return isagent;
	}
	public void setIsagent(String isagent) {
		this.isagent = isagent;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDtnm() {
		return dtnm;
	}
	public void setDtnm(String dtnm) {
		this.dtnm = dtnm;
	}
	
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
}