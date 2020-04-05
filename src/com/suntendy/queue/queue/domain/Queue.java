package com.suntendy.queue.queue.domain;

/*******************************************************************************
 * 描述: 队列信息 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-24 14:51:27 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class Queue {

	private String id; // 队列id
	private String code; // 队列编号
	private String name; // 队列名称
	private String beginNum; // 开始数字
	private String prenum; // 前缀
	private String callname; // 呼叫名称
	private String flag;//标记：01:机动车 02：驾驶人 04：违法
	private String operation;// 操作
	private String uniqueid; // id前缀
	private String nextQueueName;//下一级队列名称
	private String nextType;//下一级业务类型
	private String deptcode;//部门
	private String depthall;//大厅

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCallname() {
		return callname;
	}

	public void setCallname(String callname) {
		this.callname = callname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(String beginNum) {
		this.beginNum = beginNum;
	}


	public String getPrenum() {
		return prenum;
	}

	public void setPrenum(String prenum) {
		this.prenum = prenum;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getNextQueueName() {
		return nextQueueName;
	}

	public void setNextQueueName(String nextQueueName) {
		this.nextQueueName = nextQueueName;
	}

	public String getNextType() {
		return nextType;
	}

	public void setNextType(String nextType) {
		this.nextType = nextType;
	}
}