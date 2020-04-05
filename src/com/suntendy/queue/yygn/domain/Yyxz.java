package com.suntendy.queue.yygn.domain;

import java.util.Date;

/**
 * 预约须知信息
 * @author jiaxingyun
 *
 */
public class Yyxz {
	
	private String id;
	private String content;
	private Date lrrq;
	
	
	
	public Yyxz() {
		super();
	}

	public Yyxz(String id, String content, Date lrrq) {
		super();
		this.id = id;
		this.content = content;
		this.lrrq = lrrq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getLrrq() {
		return lrrq;
	}
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}

	@Override
	public String toString() {
		return "Yyxz [id=" + id + ", content=" + content + ", lrrq=" + lrrq
				+ "]";
	}
	
	
	
	
}
