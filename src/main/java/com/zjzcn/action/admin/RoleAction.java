package com.zjzcn.action.admin;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjzcn.Constants;
import com.zjzcn.bean.Menu;
import com.zjzcn.common.ConfigManager;
import com.zjzcn.entity.Role;
import com.zjzcn.service.RoleService;
import com.zjzcn.util.StringUtils;
import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

/**
 * @className:RoleManageAction.java
 * @classDescription:角色管理Action
 * @author:zjz
 * @createTime:2010-7-16
 */
@Controller
@RequestMapping("admin")
public class RoleAction{
    @Autowired
    private RoleService roleService;
    
    /**
     * 查找所有的角色
     */
    @RequestMapping("role_list")
    public String list(Role role, PageBean<Role> pageBean, HttpServletRequest request, ModelMap model)
    {
        Condition cond=new Condition();
        cond.likeAnywhere("name", role.getName());
        cond.eq("isSuper", role.getIsSuper());
        cond.page(pageBean);
        
        //查询所有权限，并放入会话
        pageBean = roleService.findPageByCond(cond);
        model.addAttribute("pageBean", pageBean);
        
        //设置页面搜索初始值
        model.addAttribute("role", role);
        
        return "admin/role/role_list";
    }
    
    @RequestMapping("role_add")
    public String role_add(HttpServletRequest request, ModelMap model)
    {
        String json=createJsonTree(ConfigManager.getMenuList(),null);
        
        model.addAttribute("menus", json);
        return "admin/role/role_add";
    }

    /**
     * 保存菜单
     * @throws IOException 
     */
    @RequestMapping("role_save")
    public void role_save(Role role, String urls, Writer writer) throws IOException{
    	if(StringUtils.isNotBlank(urls)){
    		String[] strArr = urls.split(",");
    		Set<String> set = new HashSet<String>(Arrays.asList(strArr));
    		role.setPermissions(set);
    	}

    	roleService.save(role);

    	writer.write(Constants.OK_FLAG);
    }

    @RequestMapping("role_edit")
    public String role_edit(Long id, ModelMap model)
    {
        Role role=roleService.findById(id);       
        
        String json=createJsonTree(ConfigManager.getMenuList(),role.getPermissions());
        
        model.addAttribute("menus", json);
        model.addAttribute("role",role);
        return "admin/role/role_edit";
    }
    
    /**
     * 修改权限
     * @throws IOException 
     */
    @RequestMapping("role_update")
    public void role_update(Role role, String urls, Writer writer) throws IOException
    {
    	//修改菜单
    	Role qrole = roleService.findById(role.getId());

    	qrole.setName(role.getName());
    	qrole.setRemark(role.getRemark());

    	qrole.getPermissions().clear();

    	if(StringUtils.isNotBlank(urls))
    	{
    		String[] strArr = urls.split(",");

    		for (String s : strArr)
    		{
    			qrole.getPermissions().add(s);
    		}
    	}
    	roleService.update(qrole);

    	writer.write(Constants.OK_FLAG);
    }


    /**
     * 删除权限
     * @throws IOException 
     */
    @RequestMapping("role_delete")
    public void delete(Long id, Writer writer) throws IOException
    {
    	roleService.deleteById(id);
    	writer.write(Constants.OK_FLAG);
    }
    
    
    /**
     * 设置菜单权限列表
     * @author zhangjz
     * @param this.getRequest()
     */
    private String createJsonTree(List<Menu> menus, Set<String> authzSet)
    {
        JSONArray jsArr = new JSONArray(); 
        
        Set<Menu> permSet=new HashSet<Menu>();
        if(authzSet!=null)
        {
        	for(String perm:authzSet)
        	{
        		for(Menu menu:menus)
        		{
        			if(menu.isPerm() && perm.equals(menu.getUrl()))
        			{
        				permSet.add(menu);
        			}
        		}
        	}
        }
        
        Set<String> menuCodes=new HashSet<String>();
        for(Menu perm:permSet)
        {
        	menuCodes.add(perm.getId());
        	
        	Menu pMenu = perm.getParent();
        	while(pMenu!=null)
        	{
        		menuCodes.add(pMenu.getId());
        		pMenu=pMenu.getParent();
        	}
        }
        
        for (Menu menu : menus)
        {
            try
            {
                JSONObject jsObj = new JSONObject();
                jsObj.put("id", menu.getId());
                jsObj.put("name", menu.getName());
                jsObj.put("url", menu.getUrl());
                if(menu.getParent()!=null)
                {
                	jsObj.put("pId", menu.getParent().getId());
                }
                if(menuCodes.contains(menu.getId()))
                {
                    jsObj.put("checked", true);
                    jsObj.put("open", true);
                }
                
                jsArr.add(jsObj);
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
        
        return jsArr.toString();
    }
    
 
}
