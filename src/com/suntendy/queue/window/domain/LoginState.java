package com.suntendy.queue.window.domain;

/**
 * 登录状态
 */
public class LoginState {
	private String id; // 编号
	private String code; // 登录人员编号
	private String ip; // ip地址
	private String login_date; // 登录时间
	private String read_state; // 是否登录读取

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogin_date() {
		return login_date;
	}

	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}

	public String getRead_state() {
		return read_state;
	}

	public void setRead_state(String read_state) {
		this.read_state = read_state;
	}
}
