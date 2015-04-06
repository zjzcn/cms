package com.zjzcn.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjzcn.dao.CommonDao;
import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.QueryFilter;
import com.zjzcn.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDao<T> commonDao;

	@SuppressWarnings("unchecked")
	private Class<T> clazz = (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	/* ===================保存和批量保存========================== */
	public Serializable save(T obj) {
		return commonDao.save(obj);
	}

	public void saveBatch(List<T> list) {
		commonDao.saveBatch(list);

	}

	/* ===================删除和批量删除========================== */
	public void delete(T obj) {
		commonDao.delete(obj);
	}

	public void deleteById(Serializable id) {
		commonDao.deleteById(id, clazz);

	}

	public void deleteBatch(List<T> list) {
		commonDao.deleteBatch(list);
	}

	/* ===================更新和批量更新========================== */
	public void update(T obj) {
		commonDao.update(obj);
	}

	public void merge(T obj) {
		commonDao.merge(obj);
	}

	public void updateBatch(List<T> list) {
		commonDao.updateBatch(list);
	}

	public T findById(Serializable id) {
		return commonDao.findById(id, clazz);
	}

	/* ===================通过Filterition查询========================== */
	public T findByFilter(QueryFilter filter) {
		return commonDao.findByFilter(filter, clazz);
	}

	public List<T> findListByFilter(QueryFilter filter) {
		return commonDao.findListByFilter(filter, clazz);
	}

	public Page<T> findPageByFilter(QueryFilter filter) {
		return commonDao.findPageByFilter(filter, clazz);
	}

	public long count(QueryFilter filter) {
		return commonDao.count(filter, clazz);
	}

	/* ===================通过HQL查询========================== */
	public T findByHql(String hql, List<Object> paramList) {
		return commonDao.findByHql(hql, paramList);
	}

	public List<T> findListByHql(String hql, List<Object> paramList) {
		return commonDao.findListByHql(hql, paramList);
	}

	public Page<T> findPageByHql(final String hql, final List<?> paramList,
			final Page<T> pageBean) {
		return commonDao.findPageByHql(hql, paramList, pageBean);
	}

	/* ===================通过SQL查询========================== */
	public void executeSql(final String sql, final List<Object> paramList) {
		commonDao.executeSql(sql, paramList);
	}

	public Object findBySql(String sql, List<Object> paramList) {
		return commonDao.findBySql(sql, paramList);
	}

	public List<?> findListBySql(final String sql, final List<Object> paramList) {
		return commonDao.findListBySql(sql, paramList);
	}

	public List<T> findListBySql(final String sql,
			final List<Object> paramList, final Class<T> clazz) {
		return commonDao.findListBySql(sql, paramList, clazz);
	}
}
