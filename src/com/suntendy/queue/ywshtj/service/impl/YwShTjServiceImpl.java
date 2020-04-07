package com.suntendy.queue.ywshtj.service.impl;

import java.util.List;

import com.suntendy.queue.ywshtj.dao.IYwShTjDao;
import com.suntendy.queue.ywshtj.domain.YwShTj;
import com.suntendy.queue.ywshtj.service.IYwShTjService;

public class YwShTjServiceImpl implements IYwShTjService{
	private IYwShTjDao ywShTjDao;


	public IYwShTjDao getYwShTjDao() {
		return ywShTjDao;
	}

	public void setYwShTjDao(IYwShTjDao ywShTjDao) {
		this.ywShTjDao = ywShTjDao;
	}
	/**
	 * 对业务人员(管理部门)平均办理时间排名统计
	 */
	public List<YwShTj> getEmplooyTransactTimeOrder(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyTransactTimeOrder(ywShTj);
		for (YwShTj ywShTj1 : list) {
			if (ywShTj1.getAve_waittime().startsWith(".")) {
				ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
			}
			if (ywShTj1.getAve_worktime().startsWith(".")) {
				ywShTj1.setAve_worktime("0" + ywShTj1.getAve_worktime());
			}
		}
		return list;
	}

	/**
	 * 对业务人员(管理部门)差评排名统计
	 */
	public List<YwShTj> getEmplooyBadReviewOrder(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyBadReviewOrder(ywShTj);
		for (YwShTj ywShTj1 : list) {
			if (ywShTj1.getAvg_badReview().startsWith(".")) {
				ywShTj1.setAvg_badReview("0" + ywShTj1.getAvg_badReview());
			}
			if (ywShTj1.getAvg_badReview().startsWith(".")) {
				ywShTj1.setAvg_badReview("0" + ywShTj1.getAvg_badReview());
			}
				ywShTj1.setAvg_badReview(ywShTj1.getAvg_badReview()+"%");
		}
		return list;
	}

	/**
	 * 对业务人员(管理部门)暂停时间排名统计
	 */
	public List<YwShTj> getEmplooyPauseTimeOrder(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyPauseTimeOrder(ywShTj);
		for (YwShTj ywShTj1 : list) {
			if (ywShTj1.getPausetime().startsWith(".")) {
				ywShTj1.setPausetime("0" + ywShTj1.getPausetime());
			}
			if (ywShTj1.getPausetime().startsWith(".")) {
				ywShTj1.setPausetime("0" + ywShTj1.getPausetime());
			}
		}
		return list;
	}

	/**
	 * 对管理部门平均等候时间统计
	 */
	public List<YwShTj> getDepartWaitTimeOrder(YwShTj ywShTj)throws Exception {
		List<YwShTj> list = ywShTjDao.getDepartWaitTimeOrder(ywShTj);
		for (YwShTj ywShTj1 : list) {
			if (ywShTj1.getAve_waittime().startsWith(".")) {
				ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
			}
			if (ywShTj1.getAve_waittime().startsWith(".")) {
				ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
			}
		}
		return list;
	}
	
	
	/**
	 * 对业务人员(管理部门)平均办理时间预警
	 */
	public List<YwShTj> getEmplooyTransactTimeWarning(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyTransactTimeOrder(ywShTj);
		float all_Avg_time=0;//总体平均时间
		for (YwShTj ywShTj1 : list) {
			all_Avg_time += Float.parseFloat(ywShTj1.getAve_worktime());
		}
		if(list.size()>0){
			all_Avg_time = all_Avg_time/list.size();
			for (YwShTj ywShTj1 : list) {
				if (ywShTj1.getAve_worktime().startsWith(".")) {
					ywShTj1.setAve_worktime("0" + ywShTj1.getAve_worktime());
				}
				if (ywShTj1.getAve_waittime().startsWith(".")) {
					ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
				}
				ywShTj1.setSumAvg(String.valueOf(all_Avg_time));
			}
		}
		return list;
	}
	
	/**
	 * 对业务人员(管理部门)差评预警
	 */
	public List<YwShTj> getEmplooyBadReviewWarning(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyBadReviewOrder(ywShTj);
		float all_Avg_badReview=0;//总体差评数
		for (YwShTj ywShTj1 : list) {
			all_Avg_badReview += Float.parseFloat(ywShTj1.getA4());
			if (ywShTj1.getAvg_badReview().startsWith(".")) {
				ywShTj1.setAvg_badReview("0" + ywShTj1.getAvg_badReview());
			}
			if (ywShTj1.getAvg_badReview().startsWith(".")) {
				ywShTj1.setAvg_badReview("0" + ywShTj1.getAvg_badReview());
			}
			ywShTj1.setAvg_badReview(ywShTj1.getAvg_badReview()+"%");
		}
			//差评平均时间
			if(list.size()>0){
				all_Avg_badReview = all_Avg_badReview/list.size();
				for (YwShTj ywShTj1 : list) {
					ywShTj1.setSumAvg(String.valueOf(all_Avg_badReview));
				}
			}
		
		return list;
	}
	
	/**
	 * 对业务人员(管理部门)暂停时间预警
	 */
	public List<YwShTj> getEmplooyPauseTimeWarning(YwShTj ywShTj) throws Exception {
		List<YwShTj> list = ywShTjDao.getEmplooyPauseTimeOrder(ywShTj);
		float all_Avg_PauseTime=0;//总体暂停时间
		for (YwShTj ywShTj1 : list) {
			all_Avg_PauseTime += Float.parseFloat(ywShTj1.getPausetime());
			if (ywShTj1.getPausetime().startsWith(".")) {
				ywShTj1.setPausetime("0" + ywShTj1.getPausetime());
			}
			if (ywShTj1.getPausetime().startsWith(".")) {
				ywShTj1.setPausetime("0" + ywShTj1.getPausetime());
			}
		}
		//判断是否有需要预警
		if(list.size()>0){
			all_Avg_PauseTime = all_Avg_PauseTime/list.size();
			for (YwShTj ywShTj1 : list) {
				ywShTj1.setSumAvg(String.valueOf(all_Avg_PauseTime));
			}
		}
		return list;
	}
	
	/**
	 * 对管理部门平均等候时间预警
	 */
	public List<YwShTj> getDepartWaitTimeWarning(YwShTj ywShTj)throws Exception {
		List<YwShTj> list = ywShTjDao.getDepartWaitTimeOrder(ywShTj);
		float all_Avg_waitTime=0;//总体等候时间
		for (YwShTj ywShTj1 : list) {
			all_Avg_waitTime += Float.parseFloat(ywShTj1.getAve_waittime());
			if (ywShTj1.getAve_waittime().startsWith(".")) {
				ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
			}
			if (ywShTj1.getAve_waittime().startsWith(".")) {
				ywShTj1.setAve_waittime("0" + ywShTj1.getAve_waittime());
			}
		}
		//判断是否有需要预警
		if(list.size()>0){
			all_Avg_waitTime = all_Avg_waitTime/list.size();
			for (YwShTj ywShTj1 : list) {
				ywShTj1.setSumAvg(String.valueOf(all_Avg_waitTime));
			}
		}
		return list;
	}

}