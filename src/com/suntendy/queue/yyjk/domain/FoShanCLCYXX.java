package com.suntendy.queue.yyjk.domain;

import java.util.Date;

/**
 * 佛山车辆查验信息
 * @author yijinping
 *
 */
public class FoShanCLCYXX {
	private String syr; //所有人 个人 或者公司
	private Date sqrq;  //申请日期
	private String clsbdh; //车辆识别代号
	public String getClsbdh() {
		return clsbdh;
	}
	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public Date getSqrq() {
		return sqrq;
	}
	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
	}
}
