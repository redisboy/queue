package com.suntendy.queue.advertise.domain;

public class QuHaoMsg {
	private String id;             //编号
	private String message;       //广告内容
	private String msg_state;     //广告信息状态
	private String record_date;  //广告信息录入时间
	private String gdMsg;  //滚动条内容
	private String operation;// 操作
	private String deptcode;// 部门
	private String depthall;// 大厅
	
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
	public String getGdMsg() {
		return gdMsg;
	}
	public void setGdMsg(String gdMsg) {
		this.gdMsg = gdMsg;
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
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getDepthall() {
		return depthall;
	}
	public void setDepthall(String depthall) {
		this.depthall = depthall;
	}
}