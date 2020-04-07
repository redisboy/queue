
package com.suntendy.urp.job.dao;

import java.util.*;

import com.suntendy.core.ibatis.dao.BaseIbatisDao;
import com.suntendy.core.page.Page;

import com.suntendy.urp.job.model.*;


public class UrpJoblogDao extends BaseIbatisDao {

	public Class getEntityClass() {
		return UrpJoblog.class;
	}

	public Page findByPage(Page page) {
		return queryForPage("UrpJoblog.pageSelect",page);
	}
	
	/**
	 * 根据关联任务ID删除UrpJoblog对象
	 * @param dictype 代码类型
	 */
	public void deleteByJobid(String jobid){
		HashMap filters = new HashMap();
		filters.put("jobid", jobid);
		this.deleteByProperty("UrpJoblog.deleteByProperty",filters);
	}
}
