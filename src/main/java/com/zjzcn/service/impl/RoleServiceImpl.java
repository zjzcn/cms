package com.zjzcn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.dao.BaseDao;
import com.zjzcn.entity.Role;
import com.zjzcn.service.RoleService;
import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

/**
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private BaseDao<Role> roleDao;

    /*===================保存和批量保存==========================*/
    public Serializable save(Role role)
    {
        return roleDao.save(role);
    }
    
    public void saveBatch(List<Role> list)
    {
        roleDao.saveBatch(list);
    }
    
    /*===================删除和批量删除==========================*/
    public void delete(Role role)
    {
        roleDao.delete(role);
    }
    
    public void deleteById(Serializable id)
    {
        roleDao.deleteById(Role.class, id);
    }
    
    public void deleteBatch(List<Role> list)
    {
        roleDao.deleteBatch(list);
    }
    
    /*===================更新和批量更新==========================*/
    public void merge(Role role)
    {
        roleDao.merge(role);
    }
    
    public void update(Role role)
    {
        roleDao.update(role);
    }
    
    public void updateBatch(List<Role> list)
    {
        roleDao.updateBatch(list);
    }
    
    
    /*===================通过主键查询==========================*/
    public Role findById(Serializable id)
    {
        return roleDao.findById(Role.class, id);
    }
    
    /*===================通过Conditions查询==========================*/
    public Role findByCond(Condition cond)
    {
        return roleDao.findByCond(Role.class, cond);
    }
    
    public PageBean<Role> findPageByCond(Condition cond)
    {
        return roleDao.findPageByCond(Role.class, cond);
    }


    public List<Role> findListByCond(Condition cond)
    {
        return roleDao.findListByCond(Role.class, cond);
    }

    public long count(Condition cond)
    {
        return roleDao.count(Role.class, cond);
    }
    
    /*===================通过HQL查询==========================*/
    public Role findByHql(String hql, List<Object> paramList)
    {
        return roleDao.findByHql(hql, paramList);
    }

    public List<Role> findListByHql(String hql, List<Object> paramList)
    {
        return roleDao.findListByHql(hql, paramList);
    }

    public PageBean<Role> findPageByHql(String hql, List<?> paramList, PageBean<Role> pageBean)
    {
        return roleDao.findPageByHql(hql, paramList, pageBean);
    }
    
    /*===================通过SQL查询和执行==========================*/
    public void executeSql(String sql, List<Object> paramList)
    {
        roleDao.executeSql(sql, paramList);
    }

    public Object findBySql(String sql, List<Object> paramList)
    {
        return roleDao.findBySql(sql, paramList);
    }

    public List<?> findListBySql(String sql, List<Object> paramList)
    {
        return roleDao.findListBySql(sql, paramList);
    }

}
