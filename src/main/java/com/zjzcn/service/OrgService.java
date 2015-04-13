package com.zjzcn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjzcn.dao.CommonDao;
import com.zjzcn.entity.Org;
import com.zjzcn.entity.User;

@Service
public class OrgService extends BaseService<Org> {

	@Autowired
	private CommonDao<User> userDao;
	
	public Long findLeader(Long userId) {
		User user = userDao.findById(userId, User.class);
		if(Org.TYPE_POSITION.equals(user.getOrg().getParentOrg().getType())) {
			Org org = user.getOrg().getParentOrg();
		} else {
			Org org = user.getOrg().getParentOrg().getParentOrg();
		}
		
		return null;
	}
}
