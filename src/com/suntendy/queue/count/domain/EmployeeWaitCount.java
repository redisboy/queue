package com.suntendy.queue.count.domain;

public class EmployeeWaitCount {
	private String name; //员工姓名
	private String code;  //员工编号
	private String useruniqueid;     
	private String deptname; //小于五分钟
	private String deptCodeName;
	private String deptCode;
	private String deptHall;
	private String total;   //人数
	private String ave_waittime;//平均等待时间
	private String ave_worktime;  //平均办理时间
	private String businesscount;//业务笔数
	private String windowid;//窗口编号
	private String jiaohaocount;//叫号量
	private String gongzuocount;//工作量
	private String manyicount;//满意量
	private String guohaocount;  //过号量 
	
	
	public String getGuohaocount() {
		return guohaocount;
	}
	public void setGuohaocount(String guohaocount) {
		this.guohaocount = guohaocount;
	}
	public String getBusinesscount() {
		return businesscount;
	}
	public void setBusinesscount(String businesscount) {
		this.businesscount = businesscount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUseruniqueid() {
		return useruniqueid;
	}
	public void setUseruniqueid(String useruniqueid) {
		this.useruniqueid = useruniqueid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAve_waittime() {
		return ave_waittime;
	}
	public void setAve_waittime(String ave_waittime) {
		this.ave_waittime = ave_waittime;
	}
	public String getAve_worktime() {
		return ave_worktime;
	}
	public void setAve_worktime(String ave_worktime) {
		this.ave_worktime = ave_worktime;
	}
	public String getWindowid() {
		return windowid;
	}
	public void setWindowid(String windowid) {
		this.windowid = windowid;
	}
	public String getJiaohaocount() {
		return jiaohaocount;
	}
	public void setJiaohaocount(String jiaohaocount) {
		this.jiaohaocount = jiaohaocount;
	}
	public String getGongzuocount() {
		return gongzuocount;
	}
	public void setGongzuocount(String gongzuocount) {
		this.gongzuocount = gongzuocount;
	}
	public String getManyicount() {
		return manyicount;
	}
	public void setManyicount(String manyicount) {
		this.manyicount = manyicount;
	}
	public String getDeptCodeName() {
		return deptCodeName;
	}
	public void setDeptCodeName(String deptCodeName) {
		this.deptCodeName = deptCodeName;
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