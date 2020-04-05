package com.suntendy.queue.publicAd.domain;

public class PublicAd {
	private String id;             //编号
	private String message;       //内容
	private String msg_state;     //状态
	private String record_date;  //录入时间
	private String operation;// 操作
	private String deptCode;//部门
	private String deptHall;//大厅
	private String operator;//添加宣传信息的用户角色
	private String isUsing;//是否启用 0-不启用 1-启用
	
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(String isUsing) {
		this.isUsing = isUsing;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsg_state() {
		return msg_state;
	}
	public void setMsg_state(String msg_state) {
		this.msg_state = msg_state;
	}
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptHall() {
		return deptHall;
	}
	public void setDeptHall(String deptHall) {
		this.deptHall = deptHall;
	}
}