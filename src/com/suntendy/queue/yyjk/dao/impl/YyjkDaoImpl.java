package com.suntendy.queue.yyjk.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.yyjk.dao.IYyjkDao;
import com.suntendy.queue.yyjk.domain.FoShanCLCYXX;
import com.suntendy.queue.yyjk.domain.GuangZhouYYXX;
import com.suntendy.queue.yyjk.domain.NanNingYYXX;
import com.suntendy.queue.yyjk.domain.ZhongShanYYXX;

public class YyjkDaoImpl extends BaseDao<NanNingYYXX, String> implements IYyjkDao{
	/**
	 * 根据 身份证号 或 组织机构代码 查询南宁预约信息
	 */
	public List<NanNingYYXX> findNanNingYYXX(NanNingYYXX nanNingYYXX)throws Exception {
//		HashMap<String, String> map = new HashMap<String,String>();
//		map.put("nationalId", nanNingYYXX.getNationalId());
//		map.put("orgId", nanNingYYXX.getOrgId());
//		map.put("bookingStatusId", nanNingYYXX.getBookingStatusId());
//		map.put("stationCode", nanNingYYXX.getStationCode());
		
		return (List<NanNingYYXX>) this.getSqlMapClientTemplate().queryForList("NanNingYYXX.findNanNingYYXX", nanNingYYXX);
	}

	@Override
	public void updateNanNingYYXX(NanNingYYXX nanNingYYXX) throws Exception {
		this.getSqlMapClientTemplate().update("NanNingYYXX.updateNanNingYYXX", nanNingYYXX);
	}

	@Override
	public List<ZhongShanYYXX> findZhongShanYYXX(ZhongShanYYXX zhongShanYYXX)
			throws Exception {
		return (List<ZhongShanYYXX>) this.getSqlMapClientTemplate().queryForList("NanNingYYXX.findNanNingYYXX", zhongShanYYXX);
	}

	@Override
	public void updateZhongShanYYXX(ZhongShanYYXX zhongShanYYXX)
			throws Exception {
		this.getSqlMapClientTemplate().update("NanNingYYXX.updateNanNingYYXX", zhongShanYYXX);
	}

	@Override
	public List<GuangZhouYYXX> findGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX)
			throws Exception {
		return  (List<GuangZhouYYXX>) this.getSqlMapClientTemplate().queryForList("NanNingYYXX.findGuangZhouYYXX", guangZhouYYXX);
	}

	@Override
	public void updateGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX)
			throws Exception {
		this.getSqlMapClientTemplate().update("NanNingYYXX.updateGuangZhouYYXX", guangZhouYYXX);
	}

	@Override
	public void addGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX) throws Exception {
		this.getSqlMapClientTemplate().insert("NanNingYYXX.insertGuangZhouYYXX", guangZhouYYXX);
	}

	@Override
	public void deleteYYXXWithoutToday() throws Exception {
		this.getSqlMapClientTemplate().delete("NanNingYYXX.deleteGuangZhouYYXX");
	}

	@Override
	public List<FoShanCLCYXX> selectFoShanCLCYXX(FoShanCLCYXX foShanCLCYXX) throws Exception {
		return  (List<FoShanCLCYXX>) this.getSqlMapClientTemplate().queryForList("NanNingYYXX.foShanCLCYXX", foShanCLCYXX);
	}

}
