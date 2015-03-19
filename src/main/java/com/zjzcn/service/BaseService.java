package com.zjzcn.service;

import java.io.Serializable;
import java.util.List;

import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

public interface BaseService<T>
{
    /*===================保存和批量保存==========================*/
    Serializable save(T obj);
    
    void saveBatch(List<T> list);
    
    /*===================删除和批量删除==========================*/
    void delete(T obj);
    
    void deleteById(Serializable id);
    
    void deleteBatch(List<T> list);
    
    /*===================更新和批量更新==========================*/
    void update(T obj);
    
    void updateBatch(List<T> list);
    
    void merge(T obj);
    
    /*===================通过主键查询==========================*/
    T findById(Serializable id);
    
    /*===================通过Conditions查询==========================*/
    T findByCond(Condition cond);
    
    List<T> findListByCond(Condition cond);   
    
    PageBean<T> findPageByCond(Condition cond);
    
    public long count(Condition cond);
    
    /*===================通过HQL查询==========================*/
    public T findByHql(String hql, List<Object> paramList);
    
    public List<T> findListByHql(String hql, List<Object> paramList);
    
    public PageBean<T> findPageByHql(String hql, List<?> paramList, PageBean<T> pageBean);
    
    /*===================通过SQL查询和执行==========================*/
    public void executeSql(final String sql, final List<Object> paramList);
    
    public Object findBySql(String sql, List<Object> paramList);
    
    public List<?> findListBySql(final String sql, final List<Object> paramList);
    
}