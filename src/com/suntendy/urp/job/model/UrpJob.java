
package com.suntendy.urp.job.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.suntendy.core.ibatis.entity.BaseEntity;

public class UrpJob extends BaseEntity {

	//date formats
	/** 创建时间(yyyy-mm-dd hh24:mi:ss) **/
	public static final String FORMAT_CREATETIME = DATE_TIME_FORMAT;
	/** 更新时间(yyyy-mm-dd hh24:mi:ss) **/
	public static final String FORMAT_UPDATETIME = DATE_TIME_FORMAT;
	
	//columns START
	/** 主键 **/
	private java.lang.String id;
	/** 任务名称 **/
	private java.lang.String jobname;
	/** 任务组 **/
	private java.lang.String jobgroup;
	/** 任务描述 **/
	private java.lang.String jobdesc;
	/** 调度规则 **/
	private java.lang.String cron;
	/** 任务执行主体 **/
	private java.lang.String jobclass;//同步的执行类，需要从StatefulMethodInvokingJob继承,异步的执行类，需要从MethodInvokingJob继承
	/** 任务类型(数据字典:同步异步) **/
	private java.lang.String jobtype;//任务的类型,0:同步;1:异步
	/** 状态(数据字典) **/
	private java.lang.String status;//任务的状态，0：启用；1：停用；
	/** 创建时间(yyyy-mm-dd hh24:mi:ss) **/
	private java.util.Date createtime;
	private java.lang.String createtimeString;
	private java.lang.String createtimeStartString;
	private java.lang.String createtimeEndString;
	/** 更新时间(yyyy-mm-dd hh24:mi:ss) **/
	private java.util.Date updatetime;
	private java.lang.String updatetimeString;
	private java.lang.String updatetimeStartString;
	private java.lang.String updatetimeEndString;
	private java.lang.String deptCode;
	private java.lang.String deptHall;
	
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
	
	public void setJobname(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobname = value;
	}
	
	public java.lang.String getJobname() {
		return this.jobname;
	}
	
	public void setJobgroup(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobgroup = value;
	}
	
	public java.lang.String getJobgroup() {
		return this.jobgroup;
	}
	
	public void setJobdesc(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobdesc = value;
	}
	
	public java.lang.String getJobdesc() {
		return this.jobdesc;
	}
	
	public void setCron(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.cron = value;
	}
	
	public java.lang.String getCron() {
		return this.cron;
	}
	
	public void setJobclass(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobclass = value;
	}
	
	public java.lang.String getJobclass() {
		return this.jobclass;
	}
	
	public void setJobtype(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.jobtype = value;
	}
	
	public java.lang.String getJobtype() {
		return this.jobtype;
	}
	
	public void setStatus(java.lang.String value) {
		if(value != null){
			value = value.trim();
		}
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public String getCreatetimeString() {
		if(this.createtimeString!=null){
			return this.createtimeString.trim();
		}
		this.createtimeString = date2String(getCreatetime(), FORMAT_CREATETIME);
		return createtimeString;
	}
	
	public void setCreatetimeString(String createtimeString) {
		if(createtimeString != null){
			createtimeString = createtimeString.trim();
		}
		this.createtimeString = createtimeString;
		setCreatetime(string2Date(createtimeString, FORMAT_CREATETIME,java.util.Date.class));	
	}
	
	public void setCreatetimeStartString(String createtimeStartString) {
		if(createtimeStartString != null){
			createtimeStartString = createtimeStartString.trim();
		}
		this.createtimeStartString = createtimeStartString;
	}
	
	public String getCreatetimeStartString() {
		return this.createtimeStartString;
	}
	
	public void setCreatetimeEndString(String createtimeEndString) {
		if(createtimeEndString != null){
			createtimeEndString = createtimeEndString.trim();
		}
		this.createtimeEndString = createtimeEndString;
	}
	
	public String getCreatetimeEndString() {
		return this.createtimeEndString;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public String getUpdatetimeString() {
		if(this.updatetimeString!=null){
			return this.updatetimeString;
		}
		this.updatetimeString = date2String(getUpdatetime(), FORMAT_UPDATETIME);
		return updatetimeString;
	}
	
	public void setUpdatetimeString(String updatetimeString) {
		if(updatetimeString != null){
			updatetimeString = updatetimeString.trim();
		}
		this.updatetimeString = updatetimeString;
		setUpdatetime(string2Date(updatetimeString, FORMAT_UPDATETIME,java.util.Date.class));	
	}
	
	public void setUpdatetimeStartString(String updatetimeStartString) {
		if(updatetimeStartString != null){
			updatetimeStartString = updatetimeStartString.trim();
		}
		this.updatetimeStartString = updatetimeStartString;
	}
	
	public String getUpdatetimeStartString() {
		return this.updatetimeStartString;
	}
	
	public void setUpdatetimeEndString(String updatetimeEndString) {
		if(updatetimeEndString != null){
			updatetimeEndString = updatetimeEndString.trim();
		}
		this.updatetimeEndString = updatetimeEndString;
	}
	
	public String getUpdatetimeEndString() {
		return this.updatetimeEndString;
	}
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public java.lang.String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(java.lang.String deptCode) {
		this.deptCode = deptCode;
	}

	public java.lang.String getDeptHall() {
		return deptHall;
	}

	public void setDeptHall(java.lang.String deptHall) {
		this.deptHall = deptHall;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Jobname",getJobname())
			.append("Jobgroup",getJobgroup())
			.append("Jobdesc",getJobdesc())
			.append("Cron",getCron())
			.append("Jobclass",getJobclass())
			.append("Jobtype",getJobtype())
			.append("Status",getStatus())
			.append("Createtime",getCreatetime())
			.append("CreatetimeString",getCreatetimeString())
			.append("Updatetime",getUpdatetime())
			.append("UpdatetimeString",getUpdatetimeString())
			.append("UpdatetimeString",getUpdatetimeString())
			.append("deptCode",getDeptCode())
			.append("deptHall",getDeptHall())
			.toString();
	}
	
}

