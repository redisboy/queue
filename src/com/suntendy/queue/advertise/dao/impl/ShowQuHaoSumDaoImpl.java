package com.suntendy.queue.advertise.dao.impl;

import java.util.List;
import com.suntendy.queue.advertise.dao.IShowQuHaoSumDao;
import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.domain.QuHaoMsg;
import com.suntendy.queue.base.BaseDao;


public class ShowQuHaoSumDaoImpl extends BaseDao<QuHaoMsg, Integer>  implements IShowQuHaoSumDao{

	public Integer addSHowQuHaoContent(QuHaoMsg qhVo) throws Exception {
		return this.insert(qhVo, "addSHowQuHaoContent");
	}
	public int updageSHowQuHaoContent(QuHaoMsg qhVo) throws Exception {
		String[] properties = { "id", "message", "msg_state","gdMsg" };
		String[] propertyValues = { qhVo.getId(), qhVo.getMessage(), qhVo.getMsg_state(), qhVo.getGdMsg() };
		return this.updateByMap(Integer.valueOf(qhVo.getId()), properties, propertyValues, "updageSHowQuHaoContent");
	}
	@Override
	public List<QuHaoMsg> getAllQuHaoMsg(QuHaoMsg qhVo) throws Exception {
		String[] properties = { "id", "status","deptcode","depthall"};
		String[] propertyValues = { qhVo.getId(), qhVo.getMsg_state(),qhVo.getDeptcode(),qhVo.getDepthall() };
		return this.findByMap(properties, propertyValues, "", "", "getAllQuHaoMsg");
	}

}
