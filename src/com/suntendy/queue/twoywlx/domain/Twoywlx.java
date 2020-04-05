package com.suntendy.queue.twoywlx.domain;

import java.util.Date;

public class Twoywlx {
	
	private String id;
	private String oneywlxId;  //一级业务类型ID
	
	//数据库中没有 ，业务逻辑需要加上的
	private String oneywlxName;  //一级业务类型name
	
	private String name;   //二级业务类型名称
	private String sxjbzl;  //所需基本资料
	private String dtyysl;  //预约数量
	private Date tjrq;  //添加日期
	private String operation;  //操作
	
	
	public Twoywlx() {
		super();
	}
	public Twoywlx(String oneywlxId, String name, String sxjbzl, String dtyysl,
			Date tjrq) {
		super();
		this.oneywlxId = oneywlxId;
		this.name = name;
		this.sxjbzl = sxjbzl;
		this.dtyysl = dtyysl;
		this.tjrq = tjrq;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOneywlxId() {
		return oneywlxId;
	}
	public void setOneywlxId(String oneywlxId) {
		this.oneywlxId = oneywlxId;
	}
	public String getOneywlxName() {
		return oneywlxName;
	}
	public void setOneywlxName(String oneywlxName) {
		this.oneywlxName = oneywlxName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSxjbzl() {
		return sxjbzl;
	}
	public void setSxjbzl(String sxjbzl) {
		this.sxjbzl = sxjbzl;
	}
	public String getDtyysl() {
		return dtyysl;
	}
	public void setDtyysl(String dtyysl) {
		this.dtyysl = dtyysl;
	}
	public Date getTjrq() {
		return tjrq;
	}
	public void setTjrq(Date tjrq) {
		this.tjrq = tjrq;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "Twoywlx [id=" + id + ", oneywlxId=" + oneywlxId
				+ ", oneywlxName=" + oneywlxName + ", name=" + name
				+ ", sxjbzl=" + sxjbzl + ", dtyysl=" + dtyysl + ", tjrq="
				+ tjrq + ", operation=" + operation + "]";
	}
	

	
	
	
}
