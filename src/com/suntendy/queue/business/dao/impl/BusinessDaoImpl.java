package com.suntendy.queue.business.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.business.dao.IBusinessDao;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.queue.domain.Queue;

public class BusinessDaoImpl extends BaseDao<Business, String> implements IBusinessDao{

	public List<Business> getBusinessList(String id,String preIndex,String business,String queueCode,String deptCode,String deptHall) throws Exception {
		String[] properties = { "id","preIndex","business","queueCode","deptCode","deptHall"};
		String[] propertyValues = { id,preIndex ,business,queueCode,deptCode,deptHall};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public String addBusiness(String business, String queueCode,
			String flag, String isOpenTztd,String isOpenZhiWen,String bkbl,String outflag,
			String deptCode,String deptHall,String ywl,String help_info,String managemin,
			String biaodan,String yyywmc,String bdywmc,String liuzhuan,String isautolz) throws Exception {
		Business businessVo = new Business();
		businessVo.setName(business);
		businessVo.setQueueCode(queueCode);
		businessVo.setTwotype(flag);
		businessVo.setFlag(flag);
		businessVo.setIsOpenTztd(isOpenTztd);
		businessVo.setIsOpenZhiWen(isOpenZhiWen);
		businessVo.setBkbl(bkbl);
		businessVo.setOutflag(outflag);
		businessVo.setDeptCode(deptCode);
		businessVo.setDeptHall(deptHall);
		businessVo.setYwl(ywl);
		businessVo.setHelp_info(help_info);
		businessVo.setManagemin(managemin);
		businessVo.setBiaodan(biaodan);
		businessVo.setYyywmc(yyywmc);
		businessVo.setBdywmc(bdywmc);
		businessVo.setLiuzhuan(liuzhuan);
		businessVo.setIsautolz(isautolz);
		return insert(businessVo, BaseDao.INSERT);
	}

	public int delBusiness(String id) throws NumberFormatException, Exception    {
		return this.deleteById(id, BaseDao.DELETEBYID);
	}

	public int updateBusinessByID(String id, String business, String queueCode,
			String flag, String isOpenTztd, String isOpenZhiWen,String bkbl,
			String outflag,String ywl,String help_info,String managemin,
			String biaodan,String yyywmc,String bdywmc,String liuzhuan,String isautolz) throws Exception, Exception {	
		String[] properties = { "business", "queueCode","flag","isOpenTztd","isOpenZhiWen","bkbl","outflag","ywl","help_info","managemin","biaodan","yyywmc","bdywmc","liuzhuan","isautolz"};
		String[] propertyValues = { business,queueCode,flag,isOpenTztd,isOpenZhiWen,bkbl,outflag,ywl,help_info,managemin,biaodan,yyywmc,bdywmc,liuzhuan,isautolz};
		return this.updateByMap(id, properties, propertyValues, BaseDao.UPDATEBYMAP);
	}

	@Override
	public int updatewaitingareaByID(String id, String waitingarea)
			throws Exception {
		String[] properties = {"waitingarea"};
		String[] propertyValues = {waitingarea};
		return this.updateByMap(id, properties, propertyValues, "updateWaitingarea");
	}

	@Override
	public List<Business> getGroupByWaitSrea() throws Exception {
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", "getGroupByWaitSrea");
	}

	public List<Business> queryWaitingareaByID(Business business)
			throws Exception {
		String[] properties = {"id"};
		String[] propertyValues = {business.getId()};
		return this.findByMap(properties, propertyValues, "", "", "queryWaitingarea");
	}

	@Override
	public String queryYWNoCallNumber(String businessType) {
		return (String) this.getSqlMapClientTemplate().queryForObject("Business.queryYWNoCallNumber", businessType);
	}

	@Override
	public List<Business> selectByYYYWMC(String yyywmc,String deptCode,String deptHall) throws Exception {
//		HashMap<String, String> map = new HashMap<String,String>();
//		map.put("deptCode", deptCode);
//		map.put("deptHall", deptHall);
//		map.put("yyywmc", yyywmc);
//		return this.getSqlMapClientTemplate().queryForList("Business.selectByYYYWMC",map);
		String[] properties = { "yyywmc","deptCode","deptHall"};
		String[] propertyValues = {yyywmc,deptCode,deptHall};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public Business queryBusiness(String id, String deptCode, String deptHall)
			throws Exception {
		String[] properties = {"id", "deptCode", "deptHall" };
		String[] propertyValues = { id, deptCode, deptHall};
		List<Business> list = findByMap(properties, propertyValues, "", "", "queryBus");
		Business business=null;
		if(null != list && list.size()>0){
			business = list.get(0);
		}
		return business;
	}
	
	@Override
	public String queryDLNoCallNumber(String queueCode) {
		return (String) this.getSqlMapClientTemplate().queryForObject("Business.queryDLNoCallNumber", queueCode);
	}

	@Override
	public List<Business> queryWaitingareaByNo(String number) throws Exception {
		String[] properties = {"number"};
		String[] propertyValues = {number};
		return this.findByMap(properties, propertyValues, "", "", "queryWaitingareaByNo");
	}
}