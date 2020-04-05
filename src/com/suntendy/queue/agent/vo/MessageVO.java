package com.suntendy.queue.agent.vo;

/**
 * 查询信息VO
 */
public class MessageVO {
	private String name;// 姓名
	private String idCard;// 身份证编号
	private String id;// 编号
	private String status;// 状态
	private String unit;// 单位
	private String pxzd;// 排序字段
	private String pxfs;// 排序方式
	private String deptCode;// 排序方式
	private String pageStart;//分页起始页
	private String pageEnd;//分页结束

	public String getPageStart() {
		return pageStart;
	}

	public void setPageStart(String pageStart) {
		this.pageStart = pageStart;
	}

	public String getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(String pageEnd) {
		this.pageEnd = pageEnd;
	}

	public MessageVO() {
		super();
	}

	public MessageVO(String name, String idCard, String id, String status,
			String unit, String pxzd, String pxfs,String deptCode,String pageStart,String pageEnd) {
		super();
		this.name = name;
		this.idCard = idCard;
		this.id = id;
		this.status = status;
		this.unit = unit;
		this.pxzd = pxzd;
		this.pxfs = pxfs;
		this.deptCode = deptCode;
		this.pageStart=pageStart;
		this.pageEnd = pageEnd;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPxzd() {
		return pxzd;
	}

	public void setPxzd(String pxzd) {
		this.pxzd = pxzd;
	}

	public String getPxfs() {
		return pxfs;
	}

	public void setPxfs(String pxfs) {
		this.pxfs = pxfs;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}