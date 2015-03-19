package com.zjzcn.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

@Repository
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>
{
    private final int BATCH_COUNT = 20;
    
    @Autowired
    @Qualifier("sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    /*===================保存和批量保存==========================*/
    public Serializable save(T obj)
    {
        return super.getHibernateTemplate().save(obj);
    }
    
    public void saveBatch(List<T> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            super.getHibernateTemplate().save(list.get(i));
            
            // 每超过BATCH_PROCESS_COUNT个对象，刷新缓存
            if (i % BATCH_COUNT == 0)
            {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
        
        // 最后刷新缓存
        super.getHibernateTemplate().flush();
        super.getHibernateTemplate().clear();
        
    }

    /*===================删除和批量删除==========================*/
    public void delete(T obj)
    {
        super.getHibernateTemplate().delete(obj);
        super.getHibernateTemplate().flush();
        super.getHibernateTemplate().evict(obj);
    }
    
    public void deleteById(Class<T> clazz, Serializable id)
    {
        // 根据id获取类实例
        Object obj = super.getHibernateTemplate().get(clazz, id);
        
        if (obj != null)
        {
            // 删除对象
            super.getHibernateTemplate().delete(obj);
            super.getHibernateTemplate().flush();
            super.getHibernateTemplate().evict(obj);
        }
        
    }
    
    public void deleteBatch(List<T> list)
    {
        super.getHibernateTemplate().deleteAll(list);
        super.getHibernateTemplate().flush();
    }
    
    /*===================更新和批量更新==========================*/
    public void update(T obj)
    {
        super.getHibernateTemplate().update(obj);
    }
    
    public void merge(T obj)
    {
        // 对象在数据库存在进行update操作
        this.getHibernateTemplate().merge(obj);
        getHibernateTemplate().flush();
    }
    public void updateBatch(List<T> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            super.getHibernateTemplate().merge(list.get(i));
            
            // 每超过BATCH_PROCESS_COUNT个对象，刷新缓存                
            if (i % BATCH_COUNT == 0)
            {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
        
        // 最后刷新缓存
        super.getHibernateTemplate().flush();
        super.getHibernateTemplate().clear();
    }
    
    public T findById(Class<T> clazz, Serializable id)
    {
        return super.getHibernateTemplate().get(clazz, id);
    }
 
    /*===================通过Condition查询==========================*/
    public T findByCond(Class<T> clazz, Condition cond)
    {
        List<T> list = this.findListByCond(clazz, cond);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<T> findListByCond(Class<T> clazz, Condition cond)
    {
        Object[] params = cond.handleCondition(clazz);
        return this.findPageByHql((String)params[0], (List<?>)params[1], null).getList();
    }

    public PageBean<T> findPageByCond(Class<T> clazz, Condition cond)
    {
        Object[] params = cond.handleCondition(clazz);
        return this.findPageByHql((String)params[0], (List<?>)params[1], (PageBean<T>)params[2]);
    }
    
    public long count(Class<T> clazz, Condition cond)
    {
        Object[] params = cond.handleCondition(clazz);
        final String hql = (String)params[0];
        final List<?> paramList=(List<?>)params[1];
        
        HibernateCallback<?> callback = new HibernateCallback<Object>()
            {
            public Object doInHibernate(Session session)
            {
                int idx1 = hql.toLowerCase().indexOf(" from ");
                int idx2=hql.toLowerCase().indexOf(" order by ");
                String queryString = " select count(*) " + (idx2 > 0 ? hql.substring(idx1,idx2):hql.substring(idx1));
                
                Query query = session.createQuery(queryString);
                
                if (paramList != null && paramList.size() > 0)
                {
                    for (int i = 0; i < paramList.size(); i++)
                    {
                        query.setParameter(i+"", paramList.get(i));
                    }
                }
                System.out.println(paramList.size() + "n/"+hql);
                return query.uniqueResult();
            }
            };
            
            return (Long)super.getHibernateTemplate().execute(callback);
    }
    
    /*===================通过HQL查询==========================*/
    public T findByHql(String hql, List<Object> paramList)
    {
        List<T> list = this.findListByHql(hql, paramList);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<T> findListByHql(String hql, List<Object> paramList)
    {
        List<T> list = this.findPageByHql(hql, paramList, null).getList();
        return list;
    }
    
    
    public PageBean<T> findPageByHql(final String hql, final List<?> paramList, final PageBean<T> pageBean)
    {
    	System.out.println(paramList.size() + "n/"+hql);
        if (pageBean!=null)
        {
            HibernateCallback<?> callback = new HibernateCallback<Object>()
            {
                public Object doInHibernate(Session session)
                {
                    int idx1 = hql.toLowerCase().indexOf(" from ");
                    int idx2=hql.toLowerCase().indexOf(" order by ");
                    String queryString = " select count(*) " + (idx2 > 0 ? hql.substring(idx1,idx2):hql.substring(idx1));
                    
                    Query query = session.createQuery(queryString);
                    
                    if (paramList != null && paramList.size() > 0)
                    {
                        for (int i = 0; i < paramList.size(); i++)
                        {
                            query.setParameter(i+"", paramList.get(i));
                        }
                    }
                    return query.uniqueResult();
                }
            };
            
            long totalCount = (Long)super.getHibernateTemplate().execute(callback);
            pageBean.setTotalCount(totalCount);
        }
        
        HibernateCallback<List<T>> callback = new HibernateCallback<List<T>>()
        {
            public List<T> doInHibernate(Session session)
            {
                Query query = session.createQuery(hql);
                
                if (paramList != null && paramList.size() > 0)
                {
                    for (int i = 0; i < paramList.size(); i++)
                    {
                        query.setParameter(i+"", paramList.get(i));
                    }
                }
                
                if (pageBean!=null)
                {
                    query.setFirstResult((pageBean.getPageNo() - 1) * pageBean.getPageSize());
                    query.setMaxResults(pageBean.getPageSize());
                }
                return query.list();
            }
        };
        
        List<T> list=super.getHibernateTemplate().execute(callback);
        
        if (pageBean==null)
        {
            return new PageBean<T>(list);
        }
        
        pageBean.setList(list);
        return pageBean;
    }
    
    /*===================通过SQL查询==========================*/
    public void executeSql(final String sql, final List<Object> paramList)
    {
        HibernateCallback<?> callback = new HibernateCallback<Object>()
        {
            public Object doInHibernate(Session session)
            {
                Query query = session.createSQLQuery(sql);
                
                if (paramList != null)
                {
                    for (int i = 0; i < paramList.size(); i++)
                    {
                        query.setParameter(i, paramList.get(i));
                    }
                }
                
                return query.executeUpdate();
            }
        };
        
        super.getHibernateTemplate().execute(callback);       
    }
     
    public Object findBySql(String sql, List<Object> paramList)
    {
        List<?> list = this.findListBySql(sql, paramList);
        return list.isEmpty() ? null : list.get(0);     
    }
    
    public List<?> findListBySql(final String sql, final List<Object> paramList)
    {
        HibernateCallback<?> callback = new HibernateCallback<Object>()
        {
            public List<?> doInHibernate(Session session)
            {
                SQLQuery query = session.createSQLQuery(sql);
                if (paramList != null && paramList.size() > 0)
                {
                    for (int i = 0; i < paramList.size(); i++)
                    {
                        query.setParameter(i, paramList.get(i));
                    }
                }
                return query.list();
            }
        };
        
        return (List<?>)super.getHibernateTemplate().execute(callback);
    }
    
    public List<T> findListBySql(final String sql, final List<Object> paramList, final Class<T> clazz)
    {
        HibernateCallback<?> callback = new HibernateCallback<Object>()
        {
            public List<T> doInHibernate(Session session)
            {
                SQLQuery query = session.createSQLQuery(sql);
                if (paramList != null && paramList.size() > 0)
                {
                    for (int i = 0; i < paramList.size(); i++)
                    {
                        query.setParameter(i, paramList.get(i));
                    }
                }
                query.addEntity(clazz);
                return query.list();
            }
        };
        
        return (List<T>)super.getHibernateTemplate().execute(callback);
    }
}
