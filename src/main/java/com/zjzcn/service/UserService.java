package com.zjzcn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.dao.CommonDao;
import com.zjzcn.entity.Org;
import com.zjzcn.entity.User;
import com.zjzcn.helper.query.QueryFilter;
import com.zjzcn.service.UserService;

@Service
public class UserService extends BaseService<User> {

	@Autowired
	private CommonDao<User> userDao;
	
	public User findLeader(Long userId) {
		User user = userDao.findById(userId, User.class);
		Org org = null;
		if(Org.TYPE_POSITION.equals(user.getOrg().getParentOrg().getType())) {
			org = user.getOrg().getParentOrg();
		} else {
			org = user.getOrg().getParentOrg().getParentOrg();
		}
		
		QueryFilter filter = QueryFilter.newFilter().eq("org.id", org.getId());
		return userDao.findByFilter(filter, User.class);
	}
}
