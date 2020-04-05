package com.suntendy.queue.yyywzl.domain;

import java.util.Date;

public class Yyywzl {
	
	private String id;
	private String zlmc; //资料名称
	private String sm; //资料说明
	private Date tjrq; //添加日期
	private String operation;  //操作
	

	
	
	@Override
	public String toString() {
		return "Yyywzl [id=" + id + ", zlmc=" + zlmc + ", sm=" + sm + ", tjrq="
				+ tjrq + ", operation=" + operation + "]";
	}




	public Yyywzl( String zlmc, String sm, Date tjrq) {
		super();
		this.zlmc = zlmc;
		this.sm = sm;
		this.tjrq = tjrq;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getZlmc() {
		return zlmc;
	}




	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}




	public String getSm() {
		return sm;
	}




	public void setSm(String sm) {
		this.sm = sm;
	}




	public Date getTjrq() {
		return tjrq;
	}




	public void setTjrq(Date tjrq) {
		this.tjrq = tjrq;
	}




	public String getOperation() {
		return operation;
	}




	public void setOperation(String operation) {
		this.operation = operation;
	}




	public Yyywzl() {
		super();
	}


	
	
	
	
}
