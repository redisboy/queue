package com.suntendy.queue.agent.vo;

public class WorkVO {
	private String id;
	private String agent_id;
	private String work_id;
	private String agent_workl_status;
	private String creat_date;
	private String jyw;
	private String modified_date;
	private String user;
	private String stroke;//业务笔数
	
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agentId) {
		agent_id = agentId;
	}
	public String getWork_id() {
		return work_id;
	}
	public void setWork_id(String workId) {
		work_id = workId;
	}
	public String getAgent_workl_status() {
		return agent_workl_status;
	}
	public void setAgent_workl_status(String agentWorklStatus) {
		agent_workl_status = agentWorklStatus;
	}
	
	public String getCreat_date() {
		return creat_date;
	}
	public void setCreat_date(String creatDate) {
		creat_date = creatDate;
	}
	public String getJyw() {
		return jyw;
	}
	public void setJyw(String jyw) {
		this.jyw = jyw;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modifiedDate) {
		modified_date = modifiedDate;
	}
}
