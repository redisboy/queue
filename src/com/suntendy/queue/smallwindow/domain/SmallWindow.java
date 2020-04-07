package com.suntendy.queue.smallwindow.domain;

public class SmallWindow {

	private String id;//顺序号
	private String btnName;//按钮名称
	private String status;//状态
	private String event;//事件
	private String[] sxh;//顺序号
	private String[] mc;//名称
	private String[] code;
	private String[] stroke;//状态值
	private String[] eventAll;//状态值
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBtnName() {
		return btnName;
	}
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getCode() {
		return code;
	}
	public void setCode(String[] code) {
		this.code = code;
	}
	public String[] getSxh() {
		return sxh;
	}
	public void setSxh(String[] sxh) {
		this.sxh = sxh;
	}
	public String[] getMc() {
		return mc;
	}
	public void setMc(String[] mc) {
		this.mc = mc;
	}
	public String[] getStroke() {
		return stroke;
	}
	public void setStroke(String[] stroke) {
		this.stroke = stroke;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String[] getEventAll() {
		return eventAll;
	}
	public void setEventAll(String[] eventAll) {
		this.eventAll = eventAll;
	}
	
	
	
}
