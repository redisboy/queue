package com.suntendy.queue.evaluation.domain;

/**
 * 评价信息
 */
public class Evaluation {

	private String id; //取号信息记录id
	private String operNum; //操作员编号
	private String operName; //操作员姓名
	private String loginIP; //操作员登录IP
	private String serialNum; //顺序号
	private String evaluResult; //评价结果

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperNum() {
		return operNum;
	}

	public void setOperNum(String operNum) {
		this.operNum = operNum;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getEvaluResult() {
		return evaluResult;
	}

	public void setEvaluResult(String evaluResult) {
		this.evaluResult = evaluResult;
	}
}