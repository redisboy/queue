package com.suntendy.queue.count.domain;
/**
 * 代理人年检查询统计
 * */
public class AgentNjCount {
	
	private String idcard;//代理人身份证
	private String name;//代理人姓名
	private String validity;//有效期止
	private String pid;//经办人警号
	private String code;//代理人业务
	private String checkdate;//年检时间
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
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
}