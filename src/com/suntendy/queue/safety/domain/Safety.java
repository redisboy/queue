package com.suntendy.queue.safety.domain;
/***
 * 安全策略设置
 * @author mx
 *
 */
public class Safety {
	private String id;//id
	private String gdhour; //规定时
	private String gdminute;//规定分
	private String gdhour1; //规定时
	private String gdminute1;//规定分
	private String timelimit;//用户长期未使用时间
	private String visits;//方式频率（次/时）
	private String ipSum;//ip登录错误次数
	private String userSum;//用户登录次数
	private String sessionSum;//最大在线人数
	private String logSum;//日志保留时间
	
	public String getSessionSum() {
		return sessionSum;
	}
	public void setSessionSum(String sessionSum) {
		this.sessionSum = sessionSum;
	}
	public String getLogSum() {
		return logSum;
	}
	public void setLogSum(String logSum) {
		this.logSum = logSum;
	}
	public String getIpSum() {
		return ipSum;
	}
	public void setIpSum(String ipSum) {
		this.ipSum = ipSum;
	}
	public String getUserSum() {
		return userSum;
	}
	public void setUserSum(String userSum) {
		this.userSum = userSum;
	}
	public String getGdhour1() {
		return gdhour1;
	}
	public void setGdhour1(String gdhour1) {
		this.gdhour1 = gdhour1;
	}
	public String getGdminute1() {
		return gdminute1;
	}
	public void setGdminute1(String gdminute1) {
		this.gdminute1 = gdminute1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGdhour() {
		return gdhour;
	}
	public void setGdhour(String gdhour) {
		this.gdhour = gdhour;
	}
	public String getGdminute() {
		return gdminute;
	}
	public void setGdminute(String gdminute) {
		this.gdminute = gdminute;
	}
	public String getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}
	public String getVisits() {
		return visits;
	}
	public void setVisits(String visits) {
		this.visits = visits;
	}
	
}
