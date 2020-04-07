package com.suntendy.queue.employee.domain;

public class Role {
	public String id;
	public String code;
	public String content;
	public String levelid;
	public String depthall;
	public String deptcode;
	public String operate;
	public String RoleID;
	
	
	public String getRoleID() {
		return RoleID;
	}

	public void setRoleID(String roleID) {
		RoleID = roleID;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getDepthall() {
		return depthall;
	}

	public void setDepthall(String depthall) {
		this.depthall = depthall;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevelid() {
		return levelid;
	}

	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}
	
	public Role() {
		super();
	}

	public Role(String code, String content, String levelid,
			String depthall, String deptcode) {
		super();
		this.code = code;
		this.content = content;
		this.levelid = levelid;
		this.depthall = depthall;
		this.deptcode = deptcode;
	}

}
