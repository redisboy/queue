package com.suntendy.queue.led.domain;

public class LED {

	private String id;
	private String transmode; // 通讯模式 2、232通讯 3、485通讯
	private String dbcolor; // 单双色 单色为1,双色为2
	private String baud; // 波特率
	private String comno; // 波特率
	private String fontColor; // 颜色
	private String height; // 高
	private String width; // 宽
	private String lattice; // 字库
	private String style;//字体样式（电视映射）
	private String windowheight;
	private String windowwidth;
	private String address;//LED屏地址
	private String com1;//COM口
	private String fqsl;//分区数量
	private String qswz;//起始坐标
	private String showHang;//显示行数
	private String showType;//显示方式
	private String content;//内容模板
	private String space;//间隔数量
	private String waitingArea;//等待区域
	private String playSpeed;//运行速度(0-255) 值越大,速度越慢
	private String isOpenGunDong;//是否滚动
	private byte[] fckContent; // 编辑器内容
//	private byte[] fckContent1; // 编辑器内容
	private String flag;//标记
	private String contentlen;//内容模板长度
	
	//设置LED队列屏内容
	private String hallname;// 大厅名称
	private String gdxx;//滚动信息
	private String gdxx1;//滚动信息1
	private String title1;//标题1
	private byte[] title1content;//标题内容1
	private String title2;//标题2
	private byte[] title2content;//标题内容2
	private String title3;//标题3
	private byte[] title3content;//标题内容3
	private String title4;//标题4
	private byte[] title4content;//标题内容4
	
	private String deptCode;
	private String deptHall;
	
	private String operation;//操作

	public String getTransmode() {
		return transmode;
	}

	public void setTransmode(String transmode) {
		this.transmode = transmode;
	}

	public String getLattice() {
		return lattice;
	}

	public void setLattice(String lattice) {
		this.lattice = lattice;
	}

	public String getDbcolor() {
		return dbcolor;
	}

	public void setDbcolor(String dbcolor) {
		this.dbcolor = dbcolor;
	}

	public String getBaud() {
		return baud;
	}

	public void setBaud(String baud) {
		this.baud = baud;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWindowheight() {
		return windowheight;
	}

	public void setWindowheight(String windowheight) {
		this.windowheight = windowheight;
	}

	public String getWindowwidth() {
		return windowwidth;
	}

	public void setWindowwidth(String windowwidth) {
		this.windowwidth = windowwidth;
	}

	public String getFqsl() {
		return fqsl;
	}

	public void setFqsl(String fqsl) {
		this.fqsl = fqsl;
	}

	public String getQswz() {
		return qswz;
	}

	public void setQswz(String qswz) {
		this.qswz = qswz;
	}

	public String getShowHang() {
		return showHang;
	}

	public void setShowHang(String showHang) {
		this.showHang = showHang;
	}

	public String getComno() {
		return comno;
	}

	public void setComno(String comno) {
		this.comno = comno;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCom1() {
		return com1;
	}

	public void setCom1(String com1) {
		this.com1 = com1;
	}

	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public String getGdxx() {
		return gdxx;
	}

	public void setGdxx(String gdxx) {
		this.gdxx = gdxx;
	}

	public String getGdxx1() {
		return gdxx1;
	}

	public void setGdxx1(String gdxx1) {
		this.gdxx1 = gdxx1;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public byte[] getTitle4content() {
		return title4content;
	}

	public void setTitle4content(byte[] title4content) {
		this.title4content = title4content;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getWaitingArea() {
		return waitingArea;
	}

	public void setWaitingArea(String waitingArea) {
		this.waitingArea = waitingArea;
	}

	public byte[] getFckContent() {
		return fckContent;
	}

	public void setFckContent(byte[] fckContent) {
		this.fckContent = fckContent;
	}

//	public byte[] getFckContent1() {
//		return fckContent1;
//	}
//
//	public void setFckContent1(byte[] fckContent1) {
//		this.fckContent1 = fckContent1;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaySpeed() {
		return playSpeed;
	}

	public void setPlaySpeed(String playSpeed) {
		this.playSpeed = playSpeed;
	}

	public String getIsOpenGunDong() {
		return isOpenGunDong;
	}

	public void setIsOpenGunDong(String isOpenGunDong) {
		this.isOpenGunDong = isOpenGunDong;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContentlen() {
		return contentlen;
	}

	public void setContentlen(String contentlen) {
		this.contentlen = contentlen;
	}

	public byte[] getTitle1content() {
		return title1content;
	}

	public void setTitle1content(byte[] title1content) {
		this.title1content = title1content;
	}

	public byte[] getTitle2content() {
		return title2content;
	}

	public void setTitle2content(byte[] title2content) {
		this.title2content = title2content;
	}

	public byte[] getTitle3content() {
		return title3content;
	}

	public void setTitle3content(byte[] title3content) {
		this.title3content = title3content;
	}
	
}
