package com.suntendy.core.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.suntendy.core.page.Page;
import com.suntendy.core.util.lang.ObjectUtil;


public abstract class BaseIbatisDao extends SqlMapClientDaoSupport{
	
	protected SqlMapClientTemplate sqlMapClientTemplate = this.getSqlMapClientTemplate();

	protected final Log log = LogFactory.getLog(getClass());

	public abstract Class getEntityClass();
	
	public String namespace = getEntityClass().getName().substring(getEntityClass().getName().lastIndexOf(".")+1) ;

	/**
	 * 保存对象
	 */
	public Object save(Object entity) {
		//System.out.println("getInsertQuery()="+getInsertQuery()+"\n  save:"+entity.toString());
		return this.sqlMapClientTemplate.insert(getInsertQuery(), entity);
	}
	
	/**
	 * 批量保存
	 * @param entityList
	 */
	public int batchSave(final List entityList){
		Object o = sqlMapClientTemplate.execute(new SqlMapClientCallback() { 
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException { 
               executor.startBatch(); 
               for (int i = 0, n = entityList.size(); i < n; i++) { 
           		   //System.out.println(getInsertQuery() + "\n" + entityList.get(i).toString());
                   executor.insert(getInsertQuery(), entityList.get(i)); 
               } 
               return new Integer(executor.executeBatch()); 
            }
        }); 
		return Integer.parseInt(o.toString());
	}
	/**
	 * 更新对象
	 */
	public int update(Object entity) {

		//System.out.println("getUpdateQuery()="+getUpdateQuery()+" \n  param:"+entity.toString());
		return this.sqlMapClientTemplate.update(getUpdateQuery(), entity);
	}
	
	/**
	 * 更新对象
	 */
	public int update(String statementName,  Object entity) {
		//System.out.println("statementName="+statementName+"\n update:"+entity.toString());
		return this.sqlMapClientTemplate.update(statementName, entity);
	}
	
	/**
	 * 批量更新
	 * @param entityList
	 */
	public int batchUpdate(final List entityList){
		Object o = sqlMapClientTemplate.execute(new SqlMapClientCallback() { 
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException { 
               executor.startBatch(); 
               for (int i = 0, n = entityList.size(); i < n; i++) { 
           		   //System.out.println("getUpdateQuery="+getUpdateQuery()+"\n update:"+entityList.get(i).toString());
                   executor.update(getUpdateQuery(), entityList.get(i)); 
               } 
               return new Integer(executor.executeBatch()); 
            }
        }); 
		return Integer.parseInt(o.toString());
	}

	/**
	 * 根据主键进行删除
	 */
	public int deleteById(String primaryKeyId) {
		//System.out.println("getDeleteQuery="+getDeleteQuery()+"\n primaryKeyId:"+primaryKeyId);

		return this.sqlMapClientTemplate.delete(getDeleteQuery(), primaryKeyId);
	}
	
	/**
	 * 根据主键进行删除
	 */
	public int deleteByProperty(String delQuery,Object parameter) {
		//System.out.println("delQuery="+delQuery+"\n parameter:"+parameter.toString());
		return this.sqlMapClientTemplate.delete(delQuery, parameter);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public int batchDelete(final String[] ids){
		final int size = ids.length;
		Object o = sqlMapClientTemplate.execute(new SqlMapClientCallback() { 
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException { 
               executor.startBatch(); 
               for(int i=0; i<size; i++) {
					String id = ids[i];
					//System.out.println("getDeleteQuery="+getDeleteQuery()+"\n id:"+id);
					executor.delete(getDeleteQuery(), id);
               }
               return new Integer(executor.executeBatch()); 
            }
        }); 
		return Integer.parseInt(o.toString());
	}
	
	/**
	 * 根据主键获取一列数据行
	 * @param primaryKeyId 主键
	 * @return
	 */
	public Object getById(String primaryKeyId) {

		//System.out.println("getFindByPrimaryKeyQuery="+getFindByPrimaryKeyQuery()+"\n primaryKeyId:"+primaryKeyId);
		return this.sqlMapClientTemplate.queryForObject(getFindByPrimaryKeyQuery(),primaryKeyId);
	}
	
	/**
	 * 获取所有数据
	 * @return List
	 */
	public List findAll() {

		//System.out.println("getAllQuery="+getAllQuery());
		return this.sqlMapClientTemplate.queryForList(getAllQuery());
	}

	/**
	 * 获取所有数据
	 * @return List
	 */
	public List findAll(Object obj) {
		//System.out.println("getAllQuery="+getAllQuery());
		return this.sqlMapClientTemplate.queryForList(getAllQuery(),obj);
	}
	/**
	 * 根据查询语句获取数据列表
	 * @param querySql 查询语句
	 * @return
	 */
	public List queryForList(String querySql) {
		//System.out.println("querySql="+querySql);
		
		return this.sqlMapClientTemplate.queryForList(querySql);
	}

	/**
	 * 根据查询语句及参数获取数据列表
	 * @param querySql 查询语句
	 * @param obj  参数
	 * @return
	 */
	public List queryForList(String querySql, Object obj) {

		//System.out.println("querySql="+querySql+"\n obj="+obj.toString());
		return this.sqlMapClientTemplate.queryForList(querySql, obj);
	}

	/**
	 * 根据参数获取数据列表
	 * @param filters	Map参数
	 * @return
	 */
	public List queryForList(HashMap filters){
		//System.out.println("getByProperty="+getByProperty()+"\n obj="+filters.toString());
		return this.queryForList(getByProperty(),filters);
	}
	
	/**
	 * 根据查询语句及参数获取一段数据
	 * @param querySql	语句
	 * @param parameter	参数
	 * @param firstnum	第一条记录的位置
	 * @param pagesize  每页记录数
	 * @return
	 */
	public List queryForList(final String querySql, HashMap parameter, int firstnum, int pagesize) {

		//System.out.println("querySql="+querySql+"\n parameter="+parameter.toString()+"\n firstnum="+firstnum+"\n pagesize="+pagesize);
		return this.sqlMapClientTemplate.queryForList(querySql, parameter, firstnum, pagesize);
	}

	/**
	 * 根据查询语句获取一行数据
	 * @param querySql
	 * @return
	 */
	public Object queryForObject(String querySql) {
		//System.out.println("querySql="+querySql);
		return this.sqlMapClientTemplate.queryForObject(querySql);
	}
	
	/**
	 * 根据查询语句及参数获取一行数据
	 * @param querySql
	 * @param obj
	 * @return
	 */
	public Object queryForObject(String querySql, Object obj) {
		//System.out.println("querySql="+querySql+"\n obj="+obj.toString());
		return this.sqlMapClientTemplate.queryForObject(querySql, obj);
	}

	private String getFindByPrimaryKeyQuery() {
		return namespace + ".getById";
	}

	private String getInsertQuery() {
		return namespace + ".insert";
	}

	private String getUpdateQuery() {
		return namespace + ".update";
	}

	private String getDeleteQuery() {
		return namespace + ".delete";
	}

	private String getAllQuery() {
		return namespace + ".getAll";
	}

	private String getByProperty() {
		return namespace + ".getByProperty";
	}
	
	public String getCountQuery() {
		return namespace + ".count";
	}
	/**
	 * 分页查询
	 * @param statementName 查询语句
	 * @param page 参数
	 * @return
	 */
	public Page queryForPage(String statementName, Page page,String countStatementName) {

		HashMap filters = new HashMap();
		
		//取对象中的查询参数
		if(!ObjectUtil.isNullOrEmptyString(page.getFilters())){
			HashMap parameterObject = new HashMap();
			try {
				parameterObject = (HashMap) BeanUtilsBean.getInstance().describe(page.getFilters());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filters.putAll(parameterObject);
		}
		
		// 取对象外的参数
		if(!ObjectUtil.isNullOrEmptyString(page.getMapFilters())){
			filters.putAll(page.getMapFilters());
		}
		
		Number totalCount = (Number) this.queryForObject(countStatementName, filters);
		
		page.setTotalCount(totalCount.intValue());
		filters.put("sortColumns", page.getSortColumns());

		List list = this.queryForList(statementName, filters, page.getFirstResult(), page.getPageSize());
		
		page.setResult(list);
		
		return page;
	}
	
	/**
	 * 分页查询
	 * @param statementName 查询语句
	 * @param page 参数
	 * @return
	 */
	public Page queryForPage(String statementName, Page page) {

		HashMap filters = new HashMap();
		
		//取对象中的查询参数
		if(!ObjectUtil.isNullOrEmptyString(page.getFilters())){
			HashMap parameterObject = new HashMap();
			try {
				parameterObject = (HashMap) BeanUtilsBean.getInstance().describe(page.getFilters());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filters.putAll(parameterObject);
		}
		
		// 取对象外的参数
		if(!ObjectUtil.isNullOrEmptyString(page.getMapFilters())){
			filters.putAll(page.getMapFilters());
		}
		
		Number totalCount = (Number) this.queryForObject(getCountQuery(), filters);
		
		page.setTotalCount(totalCount.intValue());
		filters.put("sortColumns", page.getSortColumns());

		List list = this.queryForList(statementName, filters, page.getFirstResult(), page.getPageSize());
		
		page.setResult(list);
		
		return page;
	}

	/**
	 * 分页查询(树形结构)
	 * @param statementName 查询语句
	 * @param page 参数
	 * @return
	 */
	public Page queryForPagetree(String statementName, Page page) {

		HashMap filters = new HashMap();
		
		//取对象中的查询参数
		if(!ObjectUtil.isNullOrEmptyString(page.getFilters())){
			HashMap parameterObject = new HashMap();
			try {
				parameterObject = (HashMap) BeanUtilsBean.getInstance().describe(page.getFilters());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filters.putAll(parameterObject);
		}
		
		// 取对象外的参数
		if(!ObjectUtil.isNullOrEmptyString(page.getMapFilters())){
			filters.putAll(page.getMapFilters());
		}
		
		Number totalCount = (Number) this.queryForObject(namespace + ".treeCount", filters);
		
		page.setTotalCount(totalCount.intValue());
		
		List list = this.queryForList(statementName, filters, page.getFirstResult(), page.getPageSize());
		
		page.setResult(list);
		
		return page;
	}


}
