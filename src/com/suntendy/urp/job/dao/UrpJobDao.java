
package com.suntendy.urp.job.dao;

import java.util.HashMap;
import java.util.List;

import com.suntendy.core.ibatis.dao.BaseIbatisDao;
import com.suntendy.core.page.Page;

import com.suntendy.urp.job.model.*;


public class UrpJobDao extends BaseIbatisDao {

	public Class getEntityClass() {
		return UrpJob.class;
	}

	public Page findByPage(Page page) {
		return queryForPage("UrpJob.pageSelect",page);
	}
	
	/**
	 * 根据定时任务名称取的该定时任务对象
	 * @param jobname
	 * @return
	 */
	public UrpJob getByJobname(String jobname){
		HashMap filters = new HashMap();
		filters.put("jobname", jobname);
		return (UrpJob) this.queryForObject("UrpJob.getByProperty",filters);
	}
	
	/**
	 * 根据idsList取定时任务数据
	 * @param idsList
	 * @return
	 */
	public List getUrpJobInId(List idsList){
		HashMap filters = new HashMap();
		filters.put("idsList", idsList);
		return this.queryForList("UrpJob.selectInId",filters);
	}
	
}
