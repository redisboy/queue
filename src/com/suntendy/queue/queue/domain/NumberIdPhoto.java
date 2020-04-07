package com.suntendy.queue.queue.domain;

public class NumberIdPhoto {
	
	private String numberId;//身份证号
	private byte[] sfzphoto;//照片
	private String lrsj;//录入时间
	private String base64;
	
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public byte[] getSfzphoto() {
		return sfzphoto;
	}
	public void setSfzphoto(byte[] sfzphoto) {
		this.sfzphoto = sfzphoto;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	
	

}
