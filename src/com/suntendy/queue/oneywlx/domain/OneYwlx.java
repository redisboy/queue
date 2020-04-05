package com.suntendy.queue.oneywlx.domain;

import java.util.Date;

public class OneYwlx {
	
	private String id;
	private String name;	//一级业务类型名称
	private String xtlb;  //系统类别
	private String ejywzt;  //是否包含二级业务
	private String sxjbzl;  //所需基本资料
	private String dtyysl;  //预约数量
	private Date tjrq;  //添加日期
	private String operation;  //操作

	public OneYwlx() {
		super();
	}

	public OneYwlx(String name, String xtlb, String ejywzt,
			String sxjbzl, String dtyysl, Date tjrq) {
		super();
		this.name = name;
		this.xtlb = xtlb;
		this.ejywzt = ejywzt;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXtlb() {
		return xtlb;
	}

	public void setXtlb(String xtlb) {
		this.xtlb = xtlb;
	}

	public String getEjywzt() {
		return ejywzt;
	}

	public void setEjywzt(String ejywzt) {
		this.ejywzt = ejywzt;
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
		return "OneYwlx [id=" + id + ", name=" + name + ", xtlb=" + xtlb
				+ ", ejywzt=" + ejywzt + ", sxjbzl=" + sxjbzl + ", dtyysl="
				+ dtyysl + ", tjrq=" + tjrq + ", operation=" + operation + "]";
	}
	
	
	


}
