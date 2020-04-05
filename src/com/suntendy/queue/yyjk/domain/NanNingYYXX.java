package com.suntendy.queue.yyjk.domain;

import java.util.Date;

/**
 * 南宁预约信息实体类
 * @author yijinping
 *
 */
public class NanNingYYXX {
	private Integer id; //id
	private Integer bookingId; //主键
	private Integer bookingType; //预约类型 1：机动车，2：驾驶证，3：考试，4：机动车年检
	private boolean isOrg; //预约性质  单位：true  个人:false
	//个人预约填写信息
	private String customerName; //姓名   
	private String nationalId; //身份证号
	private String customerTelNo; //本人电话
	//单位预约填写。组织机构代码今后变成”统一社会信用代码”。18bit
	private String orgName; //单位名称
	private String orgId; //组织结构代码
	private String orgTelNo; //单位电话
	//委托人
	private String agentName; //委托人姓名
	private String agentId; //委托人证件号
	private String agentTelNo; //委托人电话
	
	private String plateNo; //车牌号码 例：00001 只能查询当地如: 桂A
	private String stationCode; //工作站点编码 例：010101(注2)
	private String serviceTypeCode; //业务类型编码 例：A001(注3)
	private String bookingStatusId; //预约状态 详见注1
	private String soureCode; //预约数据来源 App:0 电话预约:1 便 民网：2
	private Date dateBooked; //预约办理日期 例：2017-01-01 00:00:00.000
	private String timeSlot; //预约时间段 例：13:00 - 14:00
	private String noPlateType;//车辆类型
	private String noPlatePrefix;//车牌号前缀
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getBookingType() {
		return bookingType;
	}
	public void setBookingType(int bookingType) {
		this.bookingType = bookingType;
	}
	public boolean isOrg() {
		return isOrg;
	}
	public void setOrg(boolean isOrg) {
		this.isOrg = isOrg;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getCustomerTelNo() {
		return customerTelNo;
	}
	public void setCustomerTelNo(String customerTelNo) {
		this.customerTelNo = customerTelNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgTelNo() {
		return orgTelNo;
	}
	public void setOrgTelNo(String orgTelNo) {
		this.orgTelNo = orgTelNo;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentTelNo() {
		return agentTelNo;
	}
	public void setAgentTelNo(String agentTelNo) {
		this.agentTelNo = agentTelNo;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
	public String getBookingStatusId() {
		return bookingStatusId;
	}
	public void setBookingStatusId(String bookingStatusId) {
		this.bookingStatusId = bookingStatusId;
	}
	public String getSoureCode() {
		return soureCode;
	}
	public void setSoureCode(String soureCode) {
		this.soureCode = soureCode;
	}
	public Date getDateBooked() {
		return dateBooked;
	}
	public void setDateBooked(Date dateBooked) {
		this.dateBooked = dateBooked;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getNoPlateType() {
		return noPlateType;
	}
	public void setNoPlateType(String noPlateType) {
		this.noPlateType = noPlateType;
	}
	public String getNoPlatePrefix() {
		return noPlatePrefix;
	}
	public void setNoPlatePrefix(String noPlatePrefix) {
		this.noPlatePrefix = noPlatePrefix;
	}
	
	
	

	
}
