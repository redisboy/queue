package com.suntendy.queue.agent.vo;

import java.io.Serializable;

//批量导入错误VO
public class ErrorVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}