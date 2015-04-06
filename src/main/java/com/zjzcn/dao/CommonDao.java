package com.zjzcn.dao;

import java.io.Serializable;
import java.util.List;

import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.QueryFilter;

public interface CommonDao<T> {

	/* ===================保存和批量保存========================== */
	Serializable save(T obj);

	void saveBatch(List<T> list);

	/* ===================删除和批量删除========================== */
	void delete(T obj);

	void deleteById(Serializable id, Class<T> clazz);

	void deleteBatch(List<T> list);

	/* ===================更新和批量更新========================== */
	void update(T obj);

	void updateBatch(List<T> list);

	void merge(T obj);

	/* ===================通过主键查询========================== */
	T findById(Serializable id, Class<T> clazz);

	/* ===================通过filter查询========================== */
	T findByFilter(QueryFilter filter, Class<T> clazz);

	List<T> findListByFilter(QueryFilter filter, Class<T> clazz);

	Page<T> findPageByFilter(QueryFilter filter, Class<T> clazz);

	public long count(QueryFilter filter, Class<T> clazz);

	/* ===================通过HQL查询========================== */
	public T findByHql(String hql, List<Object> paramList);

	public List<T> findListByHql(String hql, List<Object> paramList);

	public Page<T> findPageByHql(final String hql, final List<?> paramList, final Page<T> page);

	/* ===================通过SQL查询和执行========================== */
	public void executeSql(final String sql, final List<Object> paramList);

	public Object findBySql(String sql, List<Object> paramList);

	public List<?> findListBySql(final String sql, final List<Object> paramList);

	public List<T> findListBySql(final String sql, final List<Object> paramList, final Class<T> clazz);

}