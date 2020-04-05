package com.suntendy.queue.evaluation.domain;

public class AllDetail {
	private String id;           //编号
	private String department;    //部门名称
	private String barid;       //柜台名称
	private String name;        //姓名
	private String code;        //编号
	private String queuename;   //队列名称
	private String entertime;   //取票时间
	private String begintime;   //开始时间
	private String endtime;     //结束时间
	private String valuetime;   //评价时间
	private String value;       //评价结果   
	private String core;        // 得分
	private String waits;   // 等待时间
	private String works;   // 办理时间
	private String fund;        // 账号
	private String valueName; //评价结果
	private String idnumber; //证件号码
	private String dmsm1;  // 证件类型
	private byte[] photo;
	private String businesscount; // 业务笔数
	private String clientno;    //取号值
	public String getClientno() {
		return clientno;
	}
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}
	public String getBusinesscount() {
		return businesscount;
	}
	public void setBusinesscount(String businesscount) {
		this.businesscount = businesscount;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getDmsm1() {
		return dmsm1;
	}
	public void setDmsm1(String dmsm1) {
		this.dmsm1 = dmsm1;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBarid() {
		return barid;
	}
	public void setBarid(String barid) {
		this.barid = barid;
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
	public String getQueuename() {
		return queuename;
	}
	public void setQueuename(String queuename) {
		this.queuename = queuename;
	}
	public String getEntertime() {
		return entertime;
	}
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getValuetime() {
		return valuetime;
	}
	public void setValuetime(String valuetime) {
		this.valuetime = valuetime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getWaits() {
		return waits;
	}
	public void setWaits(String waits) {
		this.waits = waits;
	}
	public String getWorks() {
		return works;
	}
	public void setWorks(String works) {
		this.works = works;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
}