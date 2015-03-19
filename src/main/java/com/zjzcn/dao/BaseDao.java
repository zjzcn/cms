package com.zjzcn.dao;

import java.io.Serializable;
import java.util.List;

import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

public interface BaseDao<T>
{
    
    /*===================保存和批量保存==========================*/
    Serializable save(T obj);
    
    void saveBatch(List<T> list);
    
    /*===================删除和批量删除==========================*/
    void delete(T obj);
    
    void deleteById(Class<T> clazz, Serializable id);
    
    void deleteBatch(List<T> list);
    
    /*===================更新和批量更新==========================*/
    void update(T obj);
    
    void updateBatch(List<T> list);
    
    void merge(T obj);
    
    /*===================通过主键查询==========================*/
    T findById(Class<T> clazz, Serializable id);
    
    /*===================通过Conditions查询==========================*/
    T findByCond(Class<T> clazz, Condition cond);
    
    List<T> findListByCond(Class<T> clazz, Condition cond);   
    
    PageBean<T> findPageByCond(Class<T> clazz, Condition cond);
    
    public long count(Class<T> clazz, Condition cond);
    
    /*===================通过HQL查询==========================*/
    public T findByHql(String hql, List<Object> paramList);
    
    public List<T> findListByHql(String hql, List<Object> paramList);
    
    public PageBean<T> findPageByHql(final String hql, final List<?> paramList, final PageBean<T> pageBean);
    
    /*===================通过SQL查询和执行==========================*/
    public void executeSql(final String sql, final List<Object> paramList);
    
    public Object findBySql(String sql, List<Object> paramList);
    
    public List<?> findListBySql(final String sql, final List<Object> paramList);
    
    public List<T> findListBySql(final String sql, final List<Object> paramList, final Class<T> clazz);
    
}