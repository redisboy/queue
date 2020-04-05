package com.suntendy.queue.evaluation.domain;

public class ValueRecord {
	private String id;        //评价id
	private String name;   //评价描述
	private String value;    //评价值
	private String state;  //状态
	private String evalueclass; //class样式
	
	private String code;//员工编号
	private String valuetime;//评价时间
	private String entertime;//取号时间
	private String idnumber;//证件编号
	private byte[] evaluphoto;//评价人照片
	private byte[] signature;//办理人签名
	private byte[] gpyphoto;//高拍仪
	private String pztime;//评价拍照时间
	private String lsh;//流水号
	private String operation;//操作
	private String deptCode;//部门
	private String deptHall;//大厅
	private String ksrq;
	private String jsrq;
	private String tjms;
	
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvalueclass() {
		return evalueclass;
	}
	public void setEvalueclass(String evalueclass) {
		this.evalueclass = evalueclass;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValuetime() {
		return valuetime;
	}
	public void setValuetime(String valuetime) {
		this.valuetime = valuetime;
	}
	public String getEntertime() {
		return entertime;
	}
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public byte[] getEvaluphoto() {
		return evaluphoto;
	}
	public void setEvaluphoto(byte[] evaluphoto) {
		this.evaluphoto = evaluphoto;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getTjms() {
		return tjms;
	}
	public void setTjms(String tjms) {
		this.tjms = tjms;
	}
	public String getPztime() {
		return pztime;
	}
	public void setPztime(String pztime) {
		this.pztime = pztime;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public byte[] getGpyphoto() {
		return gpyphoto;
	}
	public void setGpyphoto(byte[] gpyphoto) {
		this.gpyphoto = gpyphoto;
	}
	
}