package com.suntendy.queue.tztd.domain;

public class TztdVO {

	private String id;
	private String bj;//标记 1机动车 2驾驶人
	private String idnumber;//身份证号
	private String lrrq;//录入日期
	private String operate;//操作

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
}
