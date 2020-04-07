package com.suntendy.queue.system.domain;

public class System {
	private String id;
	private String name;
	private String content;
	private String isUseOldDevice;
	private String readID;
	private String isSignature;
	private String isOpenQueueTV;
	private String ledQueueShow;
	private String deptCode;
	private String deptHall;
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getIsSignature() {
		return isSignature;
	}

	public void setIsSignature(String isSignature) {
		this.isSignature = isSignature;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsUseOldDevice() {
		return isUseOldDevice;
	}

	public void setIsUseOldDevice(String isUseOldDevice) {
		this.isUseOldDevice = isUseOldDevice;
	}
	
	public String getReadID() {
		return readID;
	}

	public void setReadID(String readID) {
		this.readID = readID;
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

	public String getLedQueueShow() {
		return ledQueueShow;
	}

	public void setLedQueueShow(String ledQueueShow) {
		this.ledQueueShow = ledQueueShow;
	}

	public String getIsOpenQueueTV() {
		return isOpenQueueTV;
	}

	public void setIsOpenQueueTV(String isOpenQueueTV) {
		this.isOpenQueueTV = isOpenQueueTV;
	}
}