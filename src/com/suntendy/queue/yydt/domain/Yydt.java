package com.suntendy.queue.yydt.domain;

public class Yydt {

	private String id;
	private String name; //大厅名称
	private String blddz;  //大厅地址
	private String yyts;	//可预约天数
	private String yysd; //每天可预约时段（多个时段用#分隔）
	private String operation;  //操作
	
	public Yydt() {
		super();
	}
	public Yydt( String name, String blddz, String yyts, String yysd) {
		super();
		this.name = name;
		this.blddz = blddz;
		this.yyts = yyts;
		this.yysd = yysd;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlddz() {
		return blddz;
	}
	public void setBlddz(String blddz) {
		this.blddz = blddz;
	}
	public String getYyts() {
		return yyts;
	}
	public void setYyts(String yyts) {
		this.yyts = yyts;
	}
	public String getYysd() {
		return yysd;
	}
	public void setYysd(String yysd) {
		this.yysd = yysd;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	
	@Override
	public String toString() {
		return "Yydt [id=" + id + ", name=" + name + ", blddz=" + blddz
				+ ", yyts=" + yyts + ", yysd=" + yysd + ", operation="
				+ operation + "]";
	}
	
	
	
}
