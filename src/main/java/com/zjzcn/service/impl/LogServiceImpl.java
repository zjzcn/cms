package com.zjzcn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.dao.BaseDao;
import com.zjzcn.entity.Log;
import com.zjzcn.service.LogService;
import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

/**
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Service("logService")
public class LogServiceImpl implements LogService
{
    @Autowired
    private BaseDao<Log> logDao;

    /*===================保存和批量保存==========================*/
    public Serializable save(Log log)
    {
        return logDao.save(log);
    }
    
    public void saveBatch(List<Log> list)
    {
        logDao.saveBatch(list);
    }
    
    /*===================删除和批量删除==========================*/
    public void delete(Log log)
    {
        logDao.delete(log);
    }
    
    public void deleteById(Serializable id)
    {
        logDao.deleteById(Log.class, id);
    }
    
    public void deleteBatch(List<Log> list)
    {
        logDao.deleteBatch(list);
    }
    
    /*===================更新和批量更新==========================*/
    public void merge(Log log)
    {
        logDao.merge(log);
    }
    
    public void update(Log log)
    {
        logDao.update(log);
    }
    
    public void updateBatch(List<Log> list)
    {
        logDao.updateBatch(list);
    }
    
    
    /*===================通过主键查询==========================*/
    public Log findById(Serializable id)
    {
        return logDao.findById(Log.class, id);
    }
    
    /*===================通过Conditions查询==========================*/
    public Log findByCond(Condition cond)
    {
        return logDao.findByCond(Log.class, cond);
    }
    
    public PageBean<Log> findPageByCond(Condition cond)
    {
        return logDao.findPageByCond(Log.class, cond);
    }


    public List<Log> findListByCond(Condition cond)
    {
        return logDao.findListByCond(Log.class, cond);
    }

    public long count(Condition cond)
    {
        return logDao.count(Log.class, cond);
    }
    
    /*===================通过HQL查询==========================*/
    public Log findByHql(String hql, List<Object> paramList)
    {
        return logDao.findByHql(hql, paramList);
    }

    public List<Log> findListByHql(String hql, List<Object> paramList)
    {
        return logDao.findListByHql(hql, paramList);
    }

    public PageBean<Log> findPageByHql(String hql, List<?> paramList, PageBean<Log> pageBean)
    {
        return logDao.findPageByHql(hql, paramList, pageBean);
    }
    
    /*===================通过SQL查询和执行==========================*/
    public void executeSql(String sql, List<Object> paramList)
    {
        logDao.executeSql(sql, paramList);
    }

    public Object findBySql(String sql, List<Object> paramList)
    {
        return logDao.findBySql(sql, paramList);
    }

    public List<?> findListBySql(String sql, List<Object> paramList)
    {
        return logDao.findListBySql(sql, paramList);
    }

}
