package com.suntendy.queue.window.domain;

import com.suntendy.queue.led.domain.LED;

public class Screen {
	private Integer id;
	private String comnum; // com号码
	private String address; // 地址(屏号)
	private String color; // 颜色
	private String color1; // 颜色
	private String align;
	private String barip; // 窗口IP
	private String ckip;//窗口屏ip
	private String oldIP;
	private String valuemust; // 默认评价值
	private String barid;
	private String queueId; // 可办理的业务_名称
	private String businessid;// 可办理的业务_id
	private String envaluevalue;// 默认评价名称
	private String priority; // 优先级
	private String menuAddress; // 父窗口地址
	private String windowId; // 窗口编号
	private String nextWindow; // 下一步提示
	private String content; // 第一行内容
	private String content2; // 第二行内容
	private boolean isPause; // 是否暂停
	private String com2; //硬件评价器地址
	private String deptCode;
	private String deptHall;
	private String showNum;
	private String lattice; // 使用的字库 值：16,32
	private String openInterFace; // 是否开启接口
	private String isOpenOldDevice; // 是否开启旧评价器
	private String isOpenInformation;//是否开启流转功能
	private String ledWindowHeight;//窗口高
	private String ledWindowWidth;//窗口宽
	private String jhsl;//叫号数量
	private String qhrs;//取号数量
	private String wjhsl;//未叫号数量
	private String guoh;//过号数量
	private String guaq;//挂起号数量
	private String pjsl;//评价号数量
	private String code;//员工号
	private String xm;//员工姓名
	private byte[] photo;
	private String comments;
	private String windowGDContent;//窗口滚动内容
	private String isgd;//窗口滚动信息是否滚动
	private String content3;//第三行内容
	private String content4;//第四行内容
	private String color2;//第三行颜色
	private String color3;//第四行颜色
	private String threegd;//第三行滚动标记
	private String fourgd;//第四行滚动标记
	
	private String interval; // 间隔
	private String sxhIsDundong;//窗口顺序号是否滚动
	private String stoptime;// 停顿
	private String word; // 字数
	private String speed; // 速度
	private String allownovalue; // 强制评价
	private String operation; // 操作
	private String jbr;//经办人
	private String kbywlb;//可办业务类别
	
	private LED led; //LED屏初始化参数

	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCkip() {
		return ckip;
	}

	public void setCkip(String ckip) {
		this.ckip = ckip;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getKbywlb() {
		return kbywlb;
	}

	public void setKbywlb(String kbywlb) {
		this.kbywlb = kbywlb;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComnum() {
		return comnum;
	}

	public void setComnum(String comnum) {
		this.comnum = comnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLattice() {
		return lattice;
	}

	public void setLattice(String lattice) {
		this.lattice = lattice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getBarip() {
		return barip;
	}

	public void setBarip(String barip) {
		this.barip = barip;
	}

	public String getOldIP() {
		return oldIP;
	}

	public void setOldIP(String oldIP) {
		this.oldIP = oldIP;
	}

	public String getValuemust() {
		return valuemust;
	}

	public void setValuemust(String valuemust) {
		this.valuemust = valuemust;
	}

	public String getBarid() {
		return barid;
	}

	public void setBarid(String barid) {
		this.barid = barid;
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getEnvaluevalue() {
		return envaluevalue;
	}

	public void setEnvaluevalue(String envaluevalue) {
		this.envaluevalue = envaluevalue;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMenuAddress() {
		return menuAddress;
	}

	public void setMenuAddress(String menuAddress) {
		this.menuAddress = menuAddress;
	}

	public String getWindowId() {
		return windowId;
	}

	public void setWindowId(String windowId) {
		this.windowId = windowId;
	}

	public String getNextWindow() {
		return nextWindow;
	}

	public void setNextWindow(String nextWindow) {
		this.nextWindow = nextWindow;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getAllownovalue() {
		return allownovalue;
	}

	public void setAllownovalue(String allownovalue) {
		this.allownovalue = allownovalue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCom2() {
		return com2;
	}

	public void setCom2(String com2) {
		this.com2 = com2;
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

	public String getShowNum() {
		return showNum;
	}

	public void setShowNum(String showNum) {
		this.showNum = showNum;
	}

	public LED getLed() {
		return led;
	}

	public void setLed(LED led) {
		this.led = led;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getOpenInterFace() {
		return openInterFace;
	}

	public void setOpenInterFace(String openInterFace) {
		this.openInterFace = openInterFace;
	}

	public String getIsOpenOldDevice() {
		return isOpenOldDevice;
	}

	public void setIsOpenOldDevice(String isOpenOldDevice) {
		this.isOpenOldDevice = isOpenOldDevice;
	}

	public String getIsOpenInformation() {
		return isOpenInformation;
	}

	public void setIsOpenInformation(String isOpenInformation) {
		this.isOpenInformation = isOpenInformation;
	}

	public String getLedWindowHeight() {
		return ledWindowHeight;
	}

	public void setLedWindowHeight(String ledWindowHeight) {
		this.ledWindowHeight = ledWindowHeight;
	}

	public String getLedWindowWidth() {
		return ledWindowWidth;
	}

	public void setLedWindowWidth(String ledWindowWidth) {
		this.ledWindowWidth = ledWindowWidth;
	}

	public String getJhsl() {
		return jhsl;
	}

	public void setJhsl(String jhsl) {
		this.jhsl = jhsl;
	}

	public String getQhrs() {
		return qhrs;
	}

	public void setQhrs(String qhrs) {
		this.qhrs = qhrs;
	}

	public String getWjhsl() {
		return wjhsl;
	}

	public void setWjhsl(String wjhsl) {
		this.wjhsl = wjhsl;
	}

	public String getGuoh() {
		return guoh;
	}

	public void setGuoh(String guoh) {
		this.guoh = guoh;
	}

	public String getGuaq() {
		return guaq;
	}

	public void setGuaq(String guaq) {
		this.guaq = guaq;
	}

	public String getPjsl() {
		return pjsl;
	}

	public void setPjsl(String pjsl) {
		this.pjsl = pjsl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getWindowGDContent() {
		return windowGDContent;
	}

	public void setWindowGDContent(String windowGDContent) {
		this.windowGDContent = windowGDContent;
	}

	public String getSxhIsDundong() {
		return sxhIsDundong;
	}

	public void setSxhIsDundong(String sxhIsDundong) {
		this.sxhIsDundong = sxhIsDundong;
	}

	public String getIsgd() {
		return isgd;
	}
	public void setIsgd(String isgd) {
		this.isgd = isgd;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getContent4() {
		return content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getThreegd() {
		return threegd;
	}

	public void setThreegd(String threegd) {
		this.threegd = threegd;
	}

	public String getFourgd() {
		return fourgd;
	}

	public void setFourgd(String fourgd) {
		this.fourgd = fourgd;
	}
	
}