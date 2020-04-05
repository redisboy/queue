package com.suntendy.queue.evaluation.domain;

public class EmployeeEvaluation {
	private String name;
	private String uniqueid;
	private String deptname;
	private String deptCodeName;
	private String deptCode;
	private String deptHall;
	private String code;
	private String a1;
	private String a2;
	private String a3;
	private String a4;
	private String a5;
	private String countall;
	private String avgcore;
	private String sumcore;
	private String barid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	public String getA4() {
		return a4;
	}
	public void setA4(String a4) {
		this.a4 = a4;
	}
	public String getA5() {
		return a5;
	}
	public void setA5(String a5) {
		this.a5 = a5;
	}
	public String getCountall() {
		return countall;
	}
	public void setCountall(String countall) {
		this.countall = countall;
	}
	public String getAvgcore() {
		return avgcore;
	}
	public void setAvgcore(String avgcore) {
		this.avgcore = avgcore;
	}
	public String getSumcore() {
		return sumcore;
	}
	public void setSumcore(String sumcore) {
		this.sumcore = sumcore;
	}
	public String getBarid() {
		return barid;
	}
	public void setBarid(String barid) {
		this.barid = barid;
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
	
	
//	
//	SELECT name,code , uniqueid, deptname, " +
//	"sum( case  when  a.value='非常满意' then 1 else 0 end) AS a1, " +
//	"sum( case  when  a.value='满意' then 1 else 0 end) AS a2," +
//	"sum( case  when  a.value='一般' then 1 else 0 end) AS a3," +
//	"sum( case  when  a.value='不满意' then 1 else 0 end) AS a4," +
//	"sum( case  when  a.value='未评价' then 1 else 0 end) AS a5," +
//	"count(*) countall," +
//	"sum(core) sumcore ," +
//	"(case when count(*) > 0 then sum(core)/count(*) else 0 end) avgcore " +
//	"FROM  dbo.v_usercore AS a";

}
