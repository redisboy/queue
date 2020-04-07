
package com.suntendy.urp.job.action;

import java.util.*;

import com.suntendy.core.page.Page;
import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.core.webwork.BaseWebworkAction;


import com.suntendy.urp.job.model.*;
import com.suntendy.urp.job.dao.*;



public class UrpJoblogAction extends BaseWebworkAction {
	//默认多列排序,example: username desc
	protected static final String DEFAULT_SORT_COLUMN = "enddate"; 
	protected static final String DEFAULT_SORT_ORDER = "DESC"; 
	
	private UrpJoblogDao urpJoblogDao;
	private UrpJoblog urpJoblog;
	
	private String id = null;
	private String[] ids;
	private int pageNumber;
	private int pageSize;
	private String sortColumn;
	private String sortOrder;
	
	/*** 初始化页面参数 ***/
	public void initData() {
		if(urpJoblog == null)
			urpJoblog = new UrpJoblog();
		//保存到页面参数中
		this.setRequestAttribute("urpJoblog", urpJoblog);
		//任务结束状态列表下拉框
//		String endstatusOption = TranslateUtil.optionUrpDicCode("JOB_ENDSTATUS",urpJoblog.getEndstatus());
//		this.setRequestAttribute("endstatusOption", endstatusOption);
//		
//		//任务下拉框
//		String jobOption = TranslateUtil.optionJob(urpJoblog.getJobid());
//		
//		this.setRequestAttribute("jobOption", jobOption);
	}
	
	/** 列表显示 */
	public String list() {
		
		//初始化分页参数
		Page page = new Page();
		page.setPageNumber(this.getPageNumber());
		page.setPageSize(this.getPageSize());
		page.setSortColumns(this.getSortColumn()+" "+getSortOrder()); 
		HashMap filterMap = new HashMap();
		//保存非对象中的参数
		page.setMapFilters(filterMap);
		
		if(null == urpJoblog)
			urpJoblog = new UrpJoblog();

		//保存对象中的查询参数
		page.setFilters(urpJoblog);
		
		Page resultPage = this.getUrpJoblogDao().findByPage(page);
		
		//初始化数据
		initData();
		
		this.savePage(resultPage);
		
		return LIST_PAGE;
	}
	
	/** 查看对象*/
	public String view() {
		if (!isNullOrEmptyString(id)){
			urpJoblog = (UrpJoblog) urpJoblogDao.getById(id);
			if(urpJoblog == null){
				//在页面上保存对象为空错误信息
				saveErrorResult(getText("objectNotFound"),"");
				return ERROR_PAGE;
			}
		}else{
			//在页面上保存对象为空错误信息
			saveErrorResult(getText("objectNullError"),"");
			return ERROR_PAGE;
		}
		//初始化页面参数
		initData();
		
		//将对象进行翻译
		
		return VIEW_PAGE;
	}
	
	/** 进入新增页面*/
	public String add() {
		//初始化页面参数
		initData();
		
		
		//返回新增页面
		return ADD_PAGE;
	}
	
	/** 保存新增对象 */
	public String save() {
		boolean saveResult = false;
		if (!isNullOrEmptyString(urpJoblog)){
			try{
				//生成主键
				urpJoblog.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJoblogDao.save(urpJoblog);
				saveResult = true;
				//在页面上保存成功信息
				saveResult(getText("saveSuccess"),"/urpJoblog/c_list.action");
			}catch(Exception e){
				//在页面上保存失败信息
				saveErrorResult(getText("saveError"),e.toString());
			}
		}else{
			//在页面上保存对象为空错误信息
			saveErrorResult(getText("objectNullError"),"");
		}
		if(saveResult)
			return SUCCESS_PAGE;
		else
			return ERROR_PAGE;
	}
	
	/**进入修改页面*/
	public String edit() {
		if (!isNullOrEmptyString(id)){
			urpJoblog = (UrpJoblog) urpJoblogDao.getById(id);
			if(null == urpJoblog){
				//在页面上保存对象为空错误信息
				saveErrorResult(getText("objectNullError"),"");
				return ERROR_PAGE;
			}
		}
		//初始化页面参数
		initData();
		
		//将对象进行翻译
		
		//返回修改页面
		return EDIT_PAGE;
	}
	
	/**保存更新对象*/
	public String update() {
		boolean saveResult = false;
		if (!isNullOrEmptyString(urpJoblog)){
			try{
				urpJoblogDao.update(urpJoblog);
				saveResult = true;
				//在页面上保存成功信息
				saveResult(getText("updateSuccess"),"/urpJoblog/c_list.action");
			}catch(Exception e){
				//在页面上保存失败信息
				saveErrorResult(getText("updateError"),e.toString());
			}
		}else{
			//在页面上保存对象为空错误信息
			saveErrorResult(getText("objectNullError"),"");
		}
		if(saveResult)
			return SUCCESS_PAGE;
		else
			return ERROR_PAGE;
	}
	
	/**删除多个对象**/
	public String deleteByIds() {
		boolean deleteResult = false;
		if (!isNullOrEmptyString(ids)){
			try{
				urpJoblogDao.batchDelete(ids);
				deleteResult = true;
				//在页面上保存成功信息
				saveResult(getText("deleteSuccess"),"/urpJoblog/c_list.action");
			}catch(Exception e){
				//在页面上保存失败信息
				saveErrorResult(getText("deleteError"),e.toString());
			}
		}else{
			//在页面上保存对象为空错误信息
			saveErrorResult(getText("objectNullError"),"");
		}
		if(deleteResult)
			return SUCCESS_PAGE;
		else
			return ERROR_PAGE;
	}
	
	public int getPageNumber() {
		if (pageNumber==0)
			pageNumber = 1;
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		if (pageSize==0)
			pageSize = Integer.parseInt("15");
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumn() {
		if(isNullOrEmptyString(sortColumn))
			sortColumn= DEFAULT_SORT_COLUMN;
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		if(isNullOrEmptyString(sortOrder))
			sortOrder= DEFAULT_SORT_ORDER;
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public void setUrpJoblog (UrpJoblog urpJoblog) {
		this.urpJoblog = urpJoblog;
	}
	
	public UrpJoblog getUrpJoblog() {
		return urpJoblog;
	}
	
	public void setUrpJoblogDao (UrpJoblogDao urpJoblogDao) {
		this.urpJoblogDao = urpJoblogDao;
	}	
	
	public UrpJoblogDao getUrpJoblogDao() {
		return urpJoblogDao;
	}

}
