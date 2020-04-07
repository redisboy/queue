package com.suntendy.queue.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

public class BaseDao<T, PK extends Serializable> extends SqlMapClientDaoSupport {
	public static final String SELECTBYID = "findById";
	public static final String SELECTBYMAP = "findByMap";
	public static final String INSERT = "insert";
	public static final String DELETEBYID = "deleteById";
	public static final String UPDATE = "update";
	public static final String UPDATEBYMAP = "updateByMap";
	protected String className;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Class<T> objClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		className = objClass.getSimpleName();
	}

	/**
	 * 通过id得到实体对象
	 * @param id 编号
	 * @param idName 查询语句id
	 * @return 查询后的结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T findById(PK id, String idName) throws Exception {
		return (T) getSqlMapClientTemplate().queryForObject(className + "." + idName, id);
	}

	/**
	 * 根据条件查询结果
	 * @param properties 查询条件名
	 * @param propertyValues 查询条件值
	 * @param orderBy 排序的字段
	 * @param order ASC OR DESC
	 * @param idName 查询语句id
	 * @return 查询后的结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order, String idName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
		
		if (StringUtils.isNotEmpty(orderBy)) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}

		return (List<T>) getSqlMapClientTemplate().queryForList(className + "." + idName, map);
	}
	
	/**
	 * 根据条件查询结果
	 * @param properties 查询条件名
	 * @param propertyValues 查询条件值
	 * @param orderBy 排序的字段
	 * @param order ASC OR DESC
	 * @param idName 查询语句id
	 * @return 查询后的结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object findByObject(String[] properties, Object[] propertyValues,
			String orderBy, String order, String idName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != properties) {
			int size = properties.length;
			for (int i = 0; i < size; i++) {
				map.put(properties[i], propertyValues[i]);
			}
		}
		
		if (StringUtils.isNotEmpty(orderBy)) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}

		return getSqlMapClientTemplate().queryForObject(className + "." + idName, map);
	}

	/**
	 * 新增对象
	 * @param o 插入的数据
	 * @param idName 插入语句ID
	 * @return 1：null 2：保存到数据库后返回的ID
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PK insert(T o, String idName) throws Exception {
		return (PK) getSqlMapClientTemplate().insert(className + "." + idName, o);
	}
	
	/**
	 * 批量插入
	 * @param datas 插入的数据
	 * @param idName 插入语句ID
	 * @throws Exception
	 */
	public void batchInsert(final List<T> datas, final String idName) throws Exception {
		if (null != datas && !datas.isEmpty()) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (T o : datas) {
						executor.insert(className + "." + idName, o);
					}
					executor.executeBatch();
					return null;
				}
			});
		}
	}

	/**
	 * 更新对象
	 * @param o 更新的数据
	 * @param idName 更新语句ID
	 * @return 修改数据影响的条数
	 * @throws Exception
	 */
	public Integer update(T o, String idName) throws Exception {
		return getSqlMapClientTemplate().update(className + "." + idName, o);
	}
	
	/**
	 * 更新对象的部分属性
	 * @param id 记录id
	 * @param properties 更新属性名
	 * @param propertyValues 更新属性值
	 * @param idName 更新语句ID
	 * @return 修改数据影响的条数
	 * @throws Exception
	 */
	public Integer updateByMap(PK id, String[] properties, Object[] propertyValues, String idName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		map.put("id", id);
		return getSqlMapClientTemplate().update(className + "." + idName, map);
	}
	
	/**
	 * 批量更新
	 * @param datas 更新的数据
	 * @param idName 更新语句ID
	 * @throws Exception
	 */
	public void batchUpdate(final List<T> datas, final String idName) throws Exception {
		if (null != datas && !datas.isEmpty()) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (T o : datas) {
						executor.update(className + "." + idName, o);
					}
					executor.executeBatch();
					return null;
				}
			});
		}
	}

	/**
	 * 根据ID删除对象
	 * @param id 记录id
	 * @param idName 更新语句ID
	 * @return 删除数据影响的条数
	 * @throws Exception
	 */
	public Integer deleteById(PK id, String idName) throws Exception {
		return getSqlMapClientTemplate().delete(className + "." + idName, id);
	}
}