package com.zjzcn.service;

import java.io.Serializable;
import java.util.List;

import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.QueryFilter;

public interface BaseService<T> {

	/* ===================保存和批量保存========================== */
	Serializable save(T obj);

	void saveBatch(List<T> list);

	/* ===================删除和批量删除========================== */
	void delete(T obj);

	void deleteById(Serializable id);

	void deleteBatch(List<T> list);

	/* ===================更新和批量更新========================== */
	void update(T obj);

	void updateBatch(List<T> list);

	void merge(T obj);

	/* ===================通过主键查询========================== */
	T findById(Serializable id);

	/* ===================通过filter查询========================== */
	T findByFilter(QueryFilter filter);

	List<T> findListByFilter(QueryFilter filter);

	Page<T> findPageByFilter(QueryFilter filter);

	public long count(QueryFilter filter);

	/* ===================通过HQL查询========================== */
	public T findByHql(String hql, List<Object> paramList);

	public List<T> findListByHql(String hql, List<Object> paramList);

	public Page<T> findPageByHql(String hql, List<?> paramList, Page<T> page);

	/* ===================通过SQL查询和执行========================== */
	public void executeSql(final String sql, final List<Object> paramList);

	public Object findBySql(String sql, List<Object> paramList);

	public List<?> findListBySql(final String sql, final List<Object> paramList);

	public List<T> findListBySql(final String sql, final List<Object> paramList, final Class<T> clazz);

}