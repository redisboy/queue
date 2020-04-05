package com.suntendy.queue.yyjk.domain;
/**
 * 中山预约信息实体类
 * @author yijinping
 *
 */
public class ZhongShanYYXX {
	private int id;
	private String name; 
	private String sfzmhm; //身份证号
	private String address; //预约地点
	private String yy_date; //预约日期
	private String yy_time; //预约时间段
	private String phone; //手机号
	private int bid; //预约业务类型id
	private String yyyw; //预约业务
	private String code; //取号号码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getYy_date() {
		return yy_date;
	}
	public void setYy_date(String yy_date) {
		this.yy_date = yy_date;
	}
	public String getYy_time() {
		return yy_time;
	}
	public void setYy_time(String yy_time) {
		this.yy_time = yy_time;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getYyyw() {
		return yyyw;
	}
	public void setYyyw(String yyyw) {
		this.yyyw = yyyw;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
