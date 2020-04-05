
package com.suntendy.urp.job.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


import org.quartz.JobDataMap;

import com.suntendy.core.page.Page;
import com.suntendy.core.util.key.PrimaryKeyUtil;
import com.suntendy.core.webwork.BaseWebworkAction;


import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.urp.job.model.*;
import com.suntendy.urp.job.service.JobManager;
import com.suntendy.urp.job.dao.*;



public class UrpJobAction extends BaseWebworkAction {
	//默认多列排序,example: username desc
	protected static final String DEFAULT_SORT_COLUMN = "jobgroup"; 
	protected static final String DEFAULT_SORT_ORDER = "ASC"; 

	private JobManager jobManager;
	private UrpJobDao urpJobDao;
	private UrpJob urpJob;
	
	private String id = null;
	private String[] ids;
	private int pageNumber;
	private int pageSize;
	private String sortColumn;
	private String sortOrder;

	private UrpJoblogDao urpJoblogDao;
	
	/*** 初始化页面参数 ***/
	public void initData() {
		if(urpJob == null)
			urpJob = new UrpJob();
		
		//保存到页面参数中
		this.setRequestAttribute("urpJob", urpJob);

//		//初始化任务状态下拉框
//		String jobstatusOption = TranslateUtil.optionUrpDicCode("JOB_STATUS",urpJob.getStatus());
//		//初始化任务类别下拉框
//		String jobtypeOption = TranslateUtil.optionUrpDicCode("JOB_TYPE",urpJob.getJobtype());
//		//初始化任务组下拉框
//		String jobgroupOption = TranslateUtil.optionUrpDicCode("JOB_GROUP",urpJob.getJobgroup());
//		
//		this.setRequestAttribute("jobstatusOption", jobstatusOption);
//		this.setRequestAttribute("jobtypeOption", jobtypeOption);
//		this.setRequestAttribute("jobgroupOption", jobgroupOption);
	}
	
	/** 列表显示 */
	public String list() {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		//初始化分页参数
		Page page = new Page();
		page.setPageNumber(this.getPageNumber());
		page.setPageSize(this.getPageSize());
		page.setSortColumns(this.getSortColumn()+" "+getSortOrder()); 
		HashMap filterMap = new HashMap();
		//保存非对象中的参数
		page.setMapFilters(filterMap);
		
		if(null == urpJob)
			urpJob = new UrpJob();

		//保存对象中的查询参数
		urpJob.setDeptCode(deptCode);
		urpJob.setDeptHall(deptHall);
		page.setFilters(urpJob);
		
		Page resultPage = this.getUrpJobDao().findByPage(page);
		
		//初始化数据
		initData();
		
		this.savePage(resultPage);
		
		return LIST_PAGE;
	}
	
	

	
	/** 查看对象*/
	public String view() {
		if (!isNullOrEmptyString(id)){
			urpJob = (UrpJob) urpJobDao.getById(id);
			if(urpJob == null){
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
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		boolean saveResult = false;
		if (!isNullOrEmptyString(urpJob)){
			try{
				//生成主键
				urpJob.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJob.setCreatetime(new Date());
				urpJob.setUpdatetime(new Date());
				urpJob.setStatus("0");
				urpJob.setDeptCode(deptCode);
				urpJob.setDeptHall(deptHall);
				urpJobDao.save(urpJob);
				saveResult = true;
				JobDataMap paramsMap = new JobDataMap();
				paramsMap.put("urpJob", urpJob);
				

				UrpJoblog urpJoblog = new UrpJoblog();
				urpJoblog.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJoblog.setJobid(urpJob.getId());
				urpJoblog.setStartdate(new Date());
				
				//开始运行JOB
				jobManager.enableSchedule(urpJob.getId(),urpJob.getJobgroup(),urpJob.getJobclass(),urpJob.getCron(), paramsMap);

				urpJoblog.setEndstatus("S");
				urpJoblog.setEnddate(new Date());
				urpJoblog.setOpertype("RUN");
				urpJoblogDao.save(urpJoblog);
				
				//在页面上保存成功信息
				saveResult(getText("saveSuccess"),"/urpjob/c_list.action");
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
			urpJob = (UrpJob) urpJobDao.getById(id);
			if(null == urpJob){
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
		if (!isNullOrEmptyString(urpJob)){
			try{
				urpJob.setUpdatetime(new Date());
				urpJobDao.update(urpJob);
				saveResult = true;

				UrpJoblog urpJoblog = new UrpJoblog();
				urpJoblog.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJoblog.setJobid(urpJob.getId());
				urpJoblog.setStartdate(new Date());
				JobDataMap paramsMap = new JobDataMap();
				paramsMap.put("urpJob", urpJob);
				//开始运行JOB
				jobManager.enableSchedule(urpJob.getId(),urpJob.getJobgroup(),urpJob.getJobclass(),urpJob.getCron(), paramsMap);

				urpJoblog.setEndstatus("S");
				urpJoblog.setEnddate(new Date());
				urpJoblog.setOpertype("RESTART");
				urpJoblogDao.save(urpJoblog);
				
				//在页面上保存成功信息
				saveResult(getText("saveSuccess"),"/urpjob/c_list.action");
			}catch(Exception e){
				//在页面上保存失败信息
				saveErrorResult(getText("updateError"),e.toString());
			}
		}else{
			//在页面上保存对象为空错误信息
			saveErrorResult(getText("objectNullError"),"数据为空");
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
				
				List idsList = Arrays.asList(ids);
				
				if(idsList.size()>0){
					List urpJobList = urpJobDao.getUrpJobInId(idsList);
					for(int i=0;i<urpJobList.size();i++){
						UrpJob _urpJob = (UrpJob) urpJobList.get(i);
						urpJoblogDao.deleteByJobid(_urpJob.getId());
					}
				}
				
				//停止JOB
				for(int i=0;i<ids.length;i++){
					UrpJob deletedUrpjob = (UrpJob) urpJobDao.getById(ids[i]);

					JobDataMap paramsMap = new JobDataMap();
					paramsMap.put("urpJob", urpJob);
					//开始删除JOB
					jobManager.disableSchedule(deletedUrpjob.getId(), deletedUrpjob.getJobgroup());
										
				}
				
				//删除UrpJob对象
				urpJobDao.batchDelete(ids);
				deleteResult = true;

				//在页面上保存成功信息
				saveResult(getText("deleteSuccess"),"");
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

	/**ajax校验定时任务名称是否重复 返回true，则没有该定时任务名**/
	public void verifyJobname() throws IOException{
		getRespose().setContentType("text/html;");  
		getRespose().setCharacterEncoding("utf-8");		
		getRespose().setHeader("Cache-Control", "no-cache");
		String returnstr = "";
		if (null != urpJob ){
			UrpJob _urpJob = urpJobDao.getByJobname(urpJob.getJobname());
			if(_urpJob==null){
				returnstr ="true";
			}else{
				returnstr = "false";
			}
		}					
        PrintWriter pw = getRespose().getWriter();
        pw.print(returnstr);    
        pw.flush();    
        pw.close();
	}

	/**启动停用定时任务**/
	public void doSchedule() throws IOException{
		getRespose().setContentType("text/html;");  
		getRespose().setCharacterEncoding("utf-8");		
		getRespose().setHeader("Cache-Control", "no-cache");
		String returnstr = "";
		if (null != urpJob ){
			int rst = urpJobDao.update(urpJob);
			if(rst==1){

				UrpJob _urpJob = (UrpJob) urpJobDao.getById(urpJob.getId());
				
				UrpJoblog urpJoblog = new UrpJoblog();
				urpJoblog.setId(PrimaryKeyUtil.getUuidPrimaryKey());
				urpJoblog.setJobid(_urpJob.getId());
				urpJoblog.setStartdate(new Date());

				JobDataMap paramsMap = new JobDataMap();
				paramsMap.put("urpJob", _urpJob);
				
				if(_urpJob.getStatus().equals("0")){//启用
					//开始运行JOB
					jobManager.enableSchedule(_urpJob.getId(),_urpJob.getJobgroup(),_urpJob.getJobclass(),_urpJob.getCron(), paramsMap);

					urpJoblog.setEnddate(new Date());
					urpJoblog.setOpertype("RESTART");
				}else{//停用
					//开始删除JOB
					jobManager.disableSchedule(_urpJob.getId(), _urpJob.getJobgroup());
					
					urpJoblog.setEnddate(new Date());
					urpJoblog.setOpertype("PAUSE");
				}

				urpJoblog.setEndstatus("S");
				urpJoblogDao.save(urpJoblog);
				returnstr ="true";
			}else{
				returnstr = "false";
			}
		}					
        PrintWriter pw = getRespose().getWriter();
        pw.print(returnstr);    
        pw.flush();    
        pw.close();
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
	
	public void setUrpJob (UrpJob urpJob) {
		this.urpJob = urpJob;
	}
	
	public UrpJob getUrpJob() {
		return urpJob;
	}
	
	public void setUrpJobDao (UrpJobDao urpJobDao) {
		this.urpJobDao = urpJobDao;
	}	
	
	public UrpJobDao getUrpJobDao() {
		return urpJobDao;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

	public UrpJoblogDao getUrpJoblogDao() {
		return urpJoblogDao;
	}

	public void setUrpJoblogDao(UrpJoblogDao urpJoblogDao) {
		this.urpJoblogDao = urpJoblogDao;
	}

}
