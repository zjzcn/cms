package com.zjzcn.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.dao.CommonDao;
import com.zjzcn.entity.Role;
import com.zjzcn.entity.User;
import com.zjzcn.helper.config.ConfigHelper;
import com.zjzcn.helper.config.MenuNode;
import com.zjzcn.helper.query.QueryFilter;

/**
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Service("userManager")
public class UserManager {
	
	@Autowired
	private CommonDao<User> userDao;

	public String getUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null) {
			return null;
		}
		return (String) subject.getPrincipal();
	}

	public User getUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return findByUsername((String) subject.getPrincipal());
		}
		return null;
	}

	public User findByUsername(String username) {
		QueryFilter filter = QueryFilter.newFilter();
		filter.eq("username", username);

		return userDao.findByFilter(filter, User.class);
	}

	public Set<String> getStringPermissions() {
		Set<String> perms = new HashSet<String>();
		User user = getUser();
		if (user != null) {
			for (Role role : user.getRoles()) {
				if (role.getIsSuper() == 1) {
					perms = ConfigHelper.getAllPerms();
					break;
				} else {
					perms.addAll(role.getPermissions());
				}
			}
		}
		return perms;
	}

	public MenuNode getMenuTree() {
		return createMenuTree(ConfigHelper.getMenuTree(),
				getStringPermissions());
	}

	private MenuNode createMenuTree(MenuNode menu, Collection<String> authzPerms) {
		MenuNode newMenu = new MenuNode();
		newMenu.setId(menu.getId());
		newMenu.setName(menu.getName());
		newMenu.setUrl(menu.getUrl());
		newMenu.setPerm(menu.isPerm());

		for (MenuNode child : menu.getChildren()) {
			if (child.isPerm()) {
				if (authzPerms.contains(child.getUrl())) {
					MenuNode newChild = new MenuNode();
					newChild.setId(child.getId());
					newChild.setName(child.getName());
					newChild.setUrl(child.getUrl());
					newChild.setPerm(child.isPerm());

					newMenu.getChildren().add(newChild);
				}
			} else {
				MenuNode cMenu = createMenuTree(child, authzPerms);

				if (cMenu.getChildren().size() > 0) {
					newMenu.getChildren().add(cMenu);
				}
			}
		}

		return newMenu;
	}
}
