package com.suntendy.queue.queue.dao.impl;


import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.queue.dao.ICallOutDao;
import com.suntendy.queue.queue.domain.CallOut;

public class CallOutDaoImpl extends BaseDao<CallOut, String> implements ICallOutDao {

	/**
	 * 设置评价卡号状态
	 */
	public int updateCallOutStatus(String yhdh) throws Exception {
		CallOut callout = new CallOut();
		callout.setYhdh(yhdh);
		return this.update(callout, "updateStatus");
	}

}
