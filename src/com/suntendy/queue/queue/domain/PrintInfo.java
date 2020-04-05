package com.suntendy.queue.queue.domain;

/*******************************************************************************
 * 描述: 打印参数信息 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-10-08 09:48:27 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class PrintInfo {

	private String id;
	private String serialNum; // 顺序号
	private String queueName; // 队列名称
	private int peoNum; // 等待人数
	private String countAll; // 总数量
	private boolean isSuccess; // 是否成功
	private String msg; // 错误提示信息
	private String str;//编辑好的打印条信息
	private String IDNumber;//证件号码
	private String waitingarea;//等待区域
	private String resultZT;

	
	
	public String getResultZT() {
		return resultZT;
	}

	public void setResultZT(String resultZT) {
		this.resultZT = resultZT;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getPeoNum() {
		return peoNum;
	}

	public void setPeoNum(int peoNum) {
		this.peoNum = peoNum;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountAll() {
		return countAll;
	}

	public void setCountAll(String countAll) {
		this.countAll = countAll;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getWaitingarea() {
		return waitingarea;
	}

	public void setWaitingarea(String waitingarea) {
		this.waitingarea = waitingarea;
	}
	
}