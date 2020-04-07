
package com.suntendy.urp.job.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.suntendy.core.ibatis.entity.BaseEntity;

public class UrpJoblog extends BaseEntity {

	//date formats
	/** 任务的开始时间 **/
	public static final String FORMAT_STARTDATE = DATE_TIME_FORMAT;
	/** 任务的结束时间 **/
	public static final String FORMAT_ENDDATE = DATE_TIME_FORMAT;
	
	//columns START
	/** 主键 **/
	private java.lang.String id;
	/** 关联的定时任务的主键 **/
	private java.lang.String jobid;
	/** 任务的开始时间 **/
	private java.util.Date startdate;
	private java.lang.String startdateString;
	private java.lang.String startdateStartString;
	private java.lang.String startdateEndString;
	/** 任务的结束时间 **/
	private java.util.Date enddate;
	private java.lang.String enddateString;
	private java.lang.String enddateStartString;
	private java.lang.String enddateEndString;
	/** 任务的结束状态，共两种（S代表成功，F代表失败） **/
	private java.lang.String endstatus;
	/** 备注 **/
	private java.lang.String remark;
	/** 操作类型 **/
	private java.lang.String opertype;
	//columns END

	public void setId(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setJobid(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobid = value;
	}
	
	public java.lang.String getJobid() {
		return this.jobid;
	}
	
	public String getStartdateString() {
		if(this.startdateString!=null){
			return this.startdateString.trim();
		}
		this.startdateString = date2String(getStartdate(), FORMAT_STARTDATE);
		return startdateString;
	}
	
	public void setStartdateString(String startdateString) {
		if(startdateString != null){
			startdateString = startdateString.trim();
		}
		this.startdateString = startdateString;
		setStartdate(string2Date(startdateString, FORMAT_STARTDATE,java.util.Date.class));	
	}
	
	public void setStartdateStartString(String startdateStartString) {
		if(startdateStartString != null){
			startdateStartString = startdateStartString.trim();
		}
		this.startdateStartString = startdateStartString;
	}
	
	public String getStartdateStartString() {
		return this.startdateStartString;
	}
	
	public void setStartdateEndString(String startdateEndString) {
		if(startdateEndString != null){
			startdateEndString = startdateEndString.trim();
		}
		this.startdateEndString = startdateEndString;
	}
	
	public String getStartdateEndString() {
		return this.startdateEndString;
	}
	public void setStartdate(java.util.Date value) {
		this.startdate = value;
	}
	
	public java.util.Date getStartdate() {
		return this.startdate;
	}
	
	public String getEnddateString() {
		if(this.enddateString!=null){
			return this.enddateString.trim();
		}
		this.enddateString = date2String(getEnddate(), FORMAT_ENDDATE);
		return enddateString;
	}
	
	public void setEnddateString(String enddateString) {
		if(enddateString != null){
			enddateString = enddateString.trim();
		}
		this.enddateString = enddateString;
		setEnddate(string2Date(enddateString, FORMAT_ENDDATE,java.util.Date.class));	
	}
	
	public void setEnddateStartString(String enddateStartString) {
		if(enddateStartString != null){
			enddateStartString = enddateStartString.trim();
		}
		this.enddateStartString = enddateStartString;
	}
	
	public String getEnddateStartString() {
		return this.enddateStartString;
	}
	
	public void setEnddateEndString(String enddateEndString) {
		if(enddateEndString != null){
			enddateEndString = enddateEndString.trim();
		}
		this.enddateEndString = enddateEndString;
	}
	
	public String getEnddateEndString() {
		return this.enddateEndString;
	}
	public void setEnddate(java.util.Date value) {
		this.enddate = value;
	}
	
	public java.util.Date getEnddate() {
		return this.enddate;
	}
	
	public void setEndstatus(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.endstatus = value;
	}
	
	public java.lang.String getEndstatus() {
		return this.endstatus;
	}
	
	public void setRemark(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public java.lang.String getOpertype() {
		return opertype;
	}

	public void setOpertype(java.lang.String opertype) {
		if(opertype != null){
			opertype = opertype.trim();
		}
		this.opertype = opertype;
	}
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Jobid",getJobid())
			.append("Startdate",getStartdate())
			.append("StartdateString",getStartdateString())
			.append("Enddate",getEnddate())
			.append("EnddateString",getEnddateString())
			.append("Endstatus",getEndstatus())
			.append("Opertype",getOpertype())
			.append("Remark",getRemark())
			.toString();
	}

}

