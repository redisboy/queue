package com.suntendy.queue.agent.vo;

/**
 * 代理人VO
 */
public class AgentVO {
	private int id;// 编号
	private String name;// 姓名
	private String idCard;// 身份证
	private String unit;// 单位
	private String fingerprint;// 指纹
	private byte[] id_photo;// 身份证照片
	private String register_date;// 登记日期
	private String validity;// 有效期止
	private String logout_date;// 注销日期
	private String status;// 状态 0:注销 1:正常 2暂停
	private String cellphone;// 手机
	private String unit_phone;// 单位电话
	private String incorporator;// 法人姓名
	private String max_lshs;// 单个小票办理流水上限
	private String max_times_byday;// 当天取号次数上限
	private String agent_level;// 代理级别
	private String check_date;// 审核时间
	private String jyw;// 效验位
	private String remark;// 备注
	private String del;// 代理人注销
	private String upd;// 代理人登记修改
	private String updnj;// 代理人年检修改
	private String stype;// 代理人业务代码
	private String sname;// 代理人业务名称
	private String ywbs;// 业务笔数
	private String agentmsg;// 代理人信息查询
	private String fzjg; // 发证机关
	private String[] code;// 办理业务
	private String[] stroke ;// 业务笔数
	private String user;// 警员编号
	private String modified_date;// 修改时间
	private String njsj;// 年检时间
	private String selexcel;// 代理人批量录入按钮
	private String bj;
	private String workid;
	private String deptCode;//部门
	private String dlrqhcs;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public AgentVO() {
	}

	public AgentVO(int id, String name, String idCard, String unit,
			String registerDate, String validity, String logoutDate,
			String status, String cellphone, String unitPhone,
			String incorporator, String maxLshs, String maxTimesByday,
			String agentLevel, String checkDate, String remark, String fzjg,String deptCode,String dlrqhcs) {
		this.id = id;
		this.name = name;
		this.idCard = idCard;
		this.unit = unit;
		this.register_date = registerDate;
		this.validity = validity;
		this.logout_date = logoutDate;
		this.status = status;
		this.cellphone = cellphone;
		this.unit_phone = unitPhone;
		this.incorporator = incorporator;
		this.max_lshs = maxLshs;
		this.max_times_byday = maxTimesByday;
		this.agent_level = agentLevel;
		this.check_date = checkDate;
		this.remark = remark;
		this.fzjg = fzjg;
		this.deptCode = deptCode;
		this.dlrqhcs = dlrqhcs;
	}

	public String getSelexcel() {
		return selexcel;
	}

	public void setSelexcel(String selexcel) {
		this.selexcel = selexcel;
	}

	public String getNjsj() {
		return njsj;
	}

	public void setNjsj(String njsj) {
		this.njsj = njsj;
	}

	public String getAgentmsg() {
		return agentmsg;
	}

	public void setAgentmsg(String agentmsg) {
		this.agentmsg = agentmsg;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modifiedDate) {
		modified_date = modifiedDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String[] getCode() {
		return code;
	}

	public void setCode(String[] code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMax_lshs() {
		return max_lshs;
	}

	public void setMax_lshs(String maxLshs) {
		max_lshs = maxLshs;
	}

	public String getMax_times_byday() {
		return max_times_byday;
	}

	public void setMax_times_byday(String maxTimesByday) {
		max_times_byday = maxTimesByday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public byte[] getId_photo() {
		return id_photo;
	}

	public void setId_photo(byte[] idPhoto) {
		id_photo = idPhoto;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getLogout_date() {
		return logout_date;
	}

	public void setLogout_date(String logout_date) {
		this.logout_date = logout_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUnit_phone() {
		return unit_phone;
	}

	public void setUnit_phone(String unit_phone) {
		this.unit_phone = unit_phone;
	}

	public String getIncorporator() {
		return incorporator;
	}

	public void setIncorporator(String incorporator) {
		this.incorporator = incorporator;
	}

	public String getAgent_level() {
		return agent_level;
	}

	public void setAgent_level(String agent_level) {
		this.agent_level = agent_level;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getJyw() {
		return jyw;
	}

	public void setJyw(String jyw) {
		this.jyw = jyw;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getUpd() {
		return upd;
	}

	public void setUpd(String upd) {
		this.upd = upd;
	}

	public String getUpdnj() {
		return updnj;
	}

	public void setUpdnj(String updnj) {
		this.updnj = updnj;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getFzjg() {
		return fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String[] getStroke() {
		return stroke;
	}

	public void setStroke(String[] stroke) {
		this.stroke = stroke;
	}

	public String getYwbs() {
		return ywbs;
	}

	public void setYwbs(String ywbs) {
		this.ywbs = ywbs;
	}

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public String getDlrqhcs() {
		return dlrqhcs;
	}

	public void setDlrqhcs(String dlrqhcs) {
		this.dlrqhcs = dlrqhcs;
	}
	
}