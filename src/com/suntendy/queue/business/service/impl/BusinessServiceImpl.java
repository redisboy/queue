package com.suntendy.queue.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.business.dao.IBusinessDao;
import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.business.service.IBusinessService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.dao.ISetWindowDao;
import com.suntendy.queue.window.domain.Screen;

public class BusinessServiceImpl implements IBusinessService {
	private IBusinessDao businessDao;
	private ISetWindowDao setWindowDao;
	
	public ISetWindowDao getSetWindowDao() {
		return setWindowDao;
	}

	public void setSetWindowDao(ISetWindowDao setWindowDao) {
		this.setWindowDao = setWindowDao;
	}

	public List<Business> getBusinessList(String id,String preIndex,String business,String queueCode,String deptCode,String deptHall) throws Exception {
		return businessDao.getBusinessList(id,preIndex,business,queueCode,deptCode,deptHall);
	}
	
	public String addBusiness(String business, String queueCode,
			String flag,String isOpenTztd,String isOpenZhiWen,
			String bkbl,String outflag,String deptCode,String deptHall,
			String ywl,String help_info,String managemin,String biaodan,
			String yyywmc,String bdywmc,String liuzhuan,String isautolz) throws Exception {
		String res = businessDao.addBusiness(business, queueCode, flag, isOpenTztd, isOpenZhiWen,bkbl,outflag,
				deptCode, deptHall,ywl,help_info,managemin,biaodan,yyywmc,bdywmc,liuzhuan,isautolz);

		// 更新内存
		CacheManager cacheManager = CacheManager.getInstance();
		List<Business> datas = getBusinessList(String.valueOf(res), "", "", "", deptCode, deptHall);
		if (null != datas && !datas.isEmpty()) {
			Business bsns = datas.get(0);
			cacheManager.addOfBusinessTypeCache(bsns.getId(), bsns);
		}
		return res;
	}
	
	public int delBusiness(String id) throws Exception {
		int res = businessDao.delBusiness(id);

		// 更新内存
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.removeBusiness(id);
		
		//更新业务类型
		List<Screen> datas = setWindowDao.getWindowList("", "", "", "", "","","");
		if (null != datas && !datas.isEmpty()) {
			
			for(Screen scr : datas){
				Screen screen =new Screen();
				screen.setId(scr.getId());
				
				String busIdo=scr.getBusinessid();
				if(null != busIdo){
					if(busIdo.contains(id)){
						int busOne=busIdo.lastIndexOf(",");
					String busTest=busIdo.substring((busOne+1),busIdo.length());
						
							if(busIdo.indexOf(",")>0){
								if(busTest.equals(id)){
									screen.setBusinessid(busIdo.replace(","+id, ""));
									}else{
									screen.setBusinessid(busIdo.replace(id+",", ""));
									}
							}else{
								screen.setBusinessid(busIdo.replace(id, ""));
							}
					}else{
						screen.setBusinessid(busIdo);
					}
					
				}
				
				String queueBus=scr.getPriority();
				if(null != queueBus){
					if(queueBus.contains(id)){
						if(queueBus.contains(";")){
							int busO=queueBus.lastIndexOf(";");
							String queb=queueBus.substring(0,busO);
							String queub=queueBus.substring((busO+1),queueBus.length());
								if(queb.contains(id)){
									//判断优先级部分
									int busTa=queb.lastIndexOf(";");
									String busTw=queb.substring((busTa+1),queb.length());
										if(queb.indexOf(";")>0){
											if(busTw.equals(id)){
												queb=queb.replace(";"+id, "");
												}else{
													queb=queb.replace(id+";", "");
												}
										}else{
											queb=queb.replace(id, "");
										}
								}else{
									//判断无优先级部分
									int busTwo=queub.lastIndexOf(",");
									String busTw=queub.substring((busTwo+1),queub.length());
										if(queub.indexOf(",")>0){
											if(busTw.equals(id)){
												queub=queub.replace(","+id, "");
												}else{
													queub=queub.replace(id+",", "");
												}
										}else{
											queub=queub.replace(id, "");
										}
								}
								if(!queb.isEmpty() && !queub.isEmpty()){
									queueBus=queb+";"+queub;
								}else{
									if(queb.isEmpty()){
										queueBus=queub;
									}else{
										queueBus=queb;
									}
								}
								screen.setPriority(queueBus);
						}
					}else{
						screen.setPriority(queueBus);
					}
				}
					setWindowDao.updateWindowById(screen);
			}
			
		}
		//更新窗口信息缓存
		List<Screen> scrRes = setWindowDao.getWindowList("", "", "", "", "","","");
		if (null != datas && !datas.isEmpty()) {
			
			for(Screen scr : scrRes){
				if (StringUtils.isNotEmpty(scr.getBarip())) {
					if (StringUtils.isNotEmpty(scr.getMenuAddress())) {
						String windowId = scr.getWindowId();
						scr.setWindowId(scr.getMenuAddress());
						scr.setMenuAddress(windowId);
					}
					String deptCode = cacheManager.getOfDeptCache("deptCode");
					String deptHall = cacheManager.getOfDeptCache("deptHall");
					scr.setLed(cacheManager.getLedCache(deptCode + deptHall));
					cacheManager.addOfWindowCache(scr.getBarip(), scr);
				} else {
					cacheManager.addOfWindowCache(scr.getWindowId(), scr);
				}
				if ("1".equals(scr.getShowNum())) {
					scr.setContent2(scr.getContent());
					scr.setContent("");
				} else {
					scr.setContent2(" ");
				}
			}
			
		}
		
		return res;
	}
	
	public int updateBusinessByID(String id, String business, String queueCode,
			String flag, String isOpenTztd, String isOpenZhiWen,String bkbl,
			String outflag,String ywl,String help_info,String managemin,
			String biaodan,String yyywmc,String bdywmc,String liuzhuan,String isautolz) throws Exception {
		int busine=businessDao.updateBusinessByID(id,business,queueCode,flag,isOpenTztd,isOpenZhiWen,bkbl,outflag,ywl,help_info,managemin,biaodan,yyywmc,bdywmc,liuzhuan,isautolz);
		
		//更新内存
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Business> datas = getBusinessList(id, "", "", "", deptCode, deptHall);
		if (null != datas && !datas.isEmpty()) {
			Business bsns = datas.get(0);
			cacheManager.addOfBusinessTypeCache(bsns.getId(), bsns);
		}
		return busine;
	}

	public void setBusinessDao(IBusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	//更改等待区域
	public int updatewaitingareaByID(String id, String waitingarea)
			throws Exception {
		return businessDao.updatewaitingareaByID(id, waitingarea);
	}

	@Override
	public List<Business> getGroupByWaitSrea() throws Exception {
		// TODO Auto-generated method stub
		return businessDao.getGroupByWaitSrea();
	}

	@Override
	public List<Business> selectByYYYWMC(String yyywmc,String deptCode,String deptHall)throws Exception {
		// TODO Auto-generated method stub
		return businessDao.selectByYYYWMC(yyywmc,deptCode,deptHall);
	}

	@Override
	public Business queryBusiness(String id, String deptCode, String deptHall) throws Exception{
		return businessDao.queryBusiness(id,deptCode,deptHall);
	}
}