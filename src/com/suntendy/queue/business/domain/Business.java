package com.suntendy.queue.business.domain;

public class Business {
	private String id;      //id 
	private String name;     //业务名称
	private String queueCode;   //队列编号
	private String flag;  //前缀
	private String operation;  //操作
	private String queueName;
	private String preNum;
	private String isOpenTztd;//是否启用通知提档
	private String isOpenZhiWen;//是否启用指纹采集
	private String ismessage; //是否启用短信通知
	private String twotype;//二级菜单
	private String waitingarea;//等待区域
	private String bkbl;//不可办理业务
	private String outflag;//接口专用标记
	private String deptCode;//部门
	private String deptHall;//大厅
	private String ywbs; //业务笔数
	private String ywl; //业务量
	private String help_info; //帮助信息
	private String managemin; //预计办理时长
	private String biaodan;//需要打印的表单
	private String yyywmc;//包含预约业务的名称
	private String bdywmc;//表单业务名称
	private String liuzhuan;//流转对应业务类型ID
	private String isautolz;//是否自动流转
	private String sfkqcyyw;//是否开启查验业务
	private String xzywmewm;//选择一维码或二维码
	
	
	
	
	public String getXzywmewm() {
		return xzywmewm;
	}
	public void setXzywmewm(String xzywmewm) {
		this.xzywmewm = xzywmewm;
	}
	public String getSfkqcyyw() {
		return sfkqcyyw;
	}
	public void setSfkqcyyw(String sfkqcyyw) {
		this.sfkqcyyw = sfkqcyyw;
	}
	public String getIsautolz() {
		return isautolz;
	}
	public void setIsautolz(String isautolz) {
		this.isautolz = isautolz;
	}
	public String getLiuzhuan() {
		return liuzhuan;
	}
	public void setLiuzhuan(String liuzhuan) {
		this.liuzhuan = liuzhuan;
	}
	public String getBdywmc() {
		return bdywmc;
	}
	public void setBdywmc(String bdywmc) {
		this.bdywmc = bdywmc;
	}
	public String getYyywmc() {
		return yyywmc;
	}
	public void setYyywmc(String yyywmc) {
		this.yyywmc = yyywmc;
	}
	public String getIsmessage() {
		return ismessage;
	}
	public void setIsmessage(String ismessage) {
		this.ismessage = ismessage;
	}
	public String getManagemin() {
		return managemin;
	}
	public void setManagemin(String managemin) {
		this.managemin = managemin;
	}
	public String getHelp_info() {
		return help_info;
	}
	public void setHelp_info(String helpInfo) {
		help_info = helpInfo;
	}
	public String getYwl() {
		return ywl;
	}
	public void setYwl(String ywl) {
		this.ywl = ywl;
	}
	public String getYwbs() {
		return ywbs;
	}
	public void setYwbs(String ywbs) {
		this.ywbs = ywbs;
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
	public String getTwotype() {
		return twotype;
	}
	public void setTwotype(String twotype) {
		this.twotype = twotype;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
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
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getPreNum() {
		return preNum;
	}
	public void setPreNum(String preNum) {
		this.preNum = preNum;
	}
	public String getWaitingarea() {
		return waitingarea;
	}
	public void setWaitingarea(String waitingarea) {
		this.waitingarea = waitingarea;
	}
	public String getIsOpenTztd() {
		return isOpenTztd;
	}
	public void setIsOpenTztd(String isOpenTztd) {
		this.isOpenTztd = isOpenTztd;
	}
	public String getIsOpenZhiWen() {
		return isOpenZhiWen;
	}
	public void setIsOpenZhiWen(String isOpenZhiWen) {
		this.isOpenZhiWen = isOpenZhiWen;
	}
	public String getBkbl() {
		return bkbl;
	}
	public void setBkbl(String bkbl) {
		this.bkbl = bkbl;
	}
	public String getOutflag() {
		return outflag;
	}
	public void setOutflag(String outflag) {
		this.outflag = outflag;
	}
	public String getBiaodan() {
		return biaodan;
	}
	public void setBiaodan(String biaodan) {
		this.biaodan = biaodan;
	}
	public Business() {
		super();
	}
	
	public Business(String id, String name, String queueCode, String flag,
			String operation, String queueName, String preNum,
			String isOpenTztd, String isOpenZhiWen, String ismessage,
			String twotype, String waitingarea, String bkbl, String outflag,
			String deptCode, String deptHall, String ywbs, String ywl,
			String help_info, String managemin, String biaodan, String yyywmc,
			String bdywmc, String liuzhuan, String isautolz, String sfkqcyyw,
			String xzywmewm) {
		super();
		this.id = id;
		this.name = name;
		this.queueCode = queueCode;
		this.flag = flag;
		this.operation = operation;
		this.queueName = queueName;
		this.preNum = preNum;
		this.isOpenTztd = isOpenTztd;
		this.isOpenZhiWen = isOpenZhiWen;
		this.ismessage = ismessage;
		this.twotype = twotype;
		this.waitingarea = waitingarea;
		this.bkbl = bkbl;
		this.outflag = outflag;
		this.deptCode = deptCode;
		this.deptHall = deptHall;
		this.ywbs = ywbs;
		this.ywl = ywl;
		this.help_info = help_info;
		this.managemin = managemin;
		this.biaodan = biaodan;
		this.yyywmc = yyywmc;
		this.bdywmc = bdywmc;
		this.liuzhuan = liuzhuan;
		this.isautolz = isautolz;
		this.sfkqcyyw = sfkqcyyw;
		this.xzywmewm = xzywmewm;
	}
	@Override
	public String toString() {
		return "Business [id=" + id + ", name=" + name + ", queueCode="
				+ queueCode + ", flag=" + flag + ", operation=" + operation
				+ ", queueName=" + queueName + ", preNum=" + preNum
				+ ", isOpenTztd=" + isOpenTztd + ", isOpenZhiWen="
				+ isOpenZhiWen + ", ismessage=" + ismessage + ", twotype="
				+ twotype + ", waitingarea=" + waitingarea + ", bkbl=" + bkbl
				+ ", outflag=" + outflag + ", deptCode=" + deptCode
				+ ", deptHall=" + deptHall + ", ywbs=" + ywbs + ", ywl=" + ywl
				+ ", help_info=" + help_info + ", managemin=" + managemin
				+ ", biaodan=" + biaodan + ", yyywmc=" + yyywmc + ", bdywmc="
				+ bdywmc + ", liuzhuan=" + liuzhuan + ", isautolz=" + isautolz
				+ ", sfkqcyyw=" + sfkqcyyw + ", xzywmewm=" + xzywmewm + "]";
	}
	
	
	
	
	
}