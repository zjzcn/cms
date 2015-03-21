package com.zjzcn.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.bean.Menu;
import com.zjzcn.common.ConfigManager;
import com.zjzcn.dao.BaseDao;
import com.zjzcn.entity.Role;
import com.zjzcn.entity.User;
import com.zjzcn.service.UserService;
import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

/**
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private BaseDao<User> userDao;
    
    /*===================保存和批量保存==========================*/
    public Serializable save(User user)
    {
        return userDao.save(user);
    }
    
    public void saveBatch(List<User> list)
    {
        userDao.saveBatch(list);
    }
    
    /*===================删除和批量删除==========================*/
    public void delete(User user)
    {
        userDao.delete(user);
    }
    
    public void deleteById(Serializable id)
    {
        userDao.deleteById(User.class, id);
    }
    
    public void deleteBatch(List<User> list)
    {
        userDao.deleteBatch(list);
    }
    
    /*===================更新和批量更新==========================*/
    public void merge(User user)
    {
        userDao.merge(user);
    }
    
    public void update(User user)
    {
        userDao.update(user);
    }
    
    public void updateBatch(List<User> list)
    {
        userDao.updateBatch(list);
    }
    
    
    /*===================通过主键查询==========================*/
    public User findById(Serializable id)
    {
        return userDao.findById(User.class, id);
    }
    
    /*===================通过Conditions查询==========================*/
    public User findByCond(Condition cond)
    {
        return userDao.findByCond(User.class, cond);
    }
    
    public PageBean<User> findPageByCond(Condition cond)
    {
        return userDao.findPageByCond(User.class, cond);
    }


    public List<User> findListByCond(Condition cond)
    {
        return userDao.findListByCond(User.class, cond);
    }

    public long count(Condition cond)
    {
        return userDao.count(User.class, cond);
    }
    
    /*===================通过HQL查询==========================*/
    public User findByHql(String hql, List<Object> paramList)
    {
        return userDao.findByHql(hql, paramList);
    }

    public List<User> findListByHql(String hql, List<Object> paramList)
    {
        return userDao.findListByHql(hql, paramList);
    }

    public PageBean<User> findPageByHql(String hql, List<?> paramList, PageBean<User> pageBean)
    {
        return userDao.findPageByHql(hql, paramList, pageBean);
    }
    
    /*===================通过SQL查询和执行==========================*/
    public void executeSql(String sql, List<Object> paramList)
    {
        userDao.executeSql(sql, paramList);
    }

    public Object findBySql(String sql, List<Object> paramList)
    {
        return userDao.findBySql(sql, paramList);
    }

    public List<?> findListBySql(String sql, List<Object> paramList)
    {
        return userDao.findListBySql(sql, paramList);
    }

    public String getCurrentUsername()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return (String) subject.getPrincipal();
        }
        return null;
    }


    public User getCurrentUser()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return findByUsername((String) subject.getPrincipal());
        }
        return null;
    }
    
    public User findByUsername(String username)
    {
        Condition cond = Condition.newCondition();
        cond.eq("username", username);
        
        return userDao.findByCond(User.class, cond);
    }
    
    public Set<String> getCurrentStringPermissions() {
        Set<String> perms = new HashSet<String>();
        User user = getCurrentUser();
        if (user != null) {
            for (Role role : user.getRoles()) {
            	if(role.getIsSuper()==1)
                {
                    perms = ConfigManager.getAllPerms();
                    break;
                }
            	else
            	{
            		perms.addAll(role.getPermissions());
            	}
            }
        }
        return perms;
    }
    
    
    public Menu getCurrentMenuTree()
    {
        return createMenuTree(ConfigManager.getMenuTree(), getCurrentStringPermissions());
    }
    
    private Menu createMenuTree(Menu menu, Collection<String> authzPerms)
    {
    	Menu newMenu = new Menu();
    	newMenu.setId(menu.getId());
    	newMenu.setName(menu.getName());
    	newMenu.setUrl(menu.getUrl());
    	newMenu.setPerm(menu.isPerm());
    	
    	for(Menu child : menu.getChildren())
    	{
    		if(child.isPerm())
    		{
    			if(authzPerms.contains(child.getUrl()))
    			{
    				Menu newChild = new Menu();
    				newChild.setId(child.getId());
    				newChild.setName(child.getName());
    				newChild.setUrl(child.getUrl());
    				newChild.setPerm(child.isPerm());
    				
    				newMenu.getChildren().add(newChild);
    			}
    		}
    		else
    		{
    			Menu cMenu = createMenuTree(child, authzPerms);
    			
    			if(cMenu.getChildren().size() > 0)
    			{
    				newMenu.getChildren().add(cMenu);
    			}
    		}
    	}
    	
    	return newMenu;
    }	
}
