package com.zjzcn.service;

import java.util.Set;

import com.zjzcn.bean.Menu;
import com.zjzcn.entity.User;

public interface UserService extends BaseService<User>
{

    String getCurrentUsername();
    
    User getCurrentUser();
    
    User findByUsername(String username);
    
    Set<String> getCurrentStringPermissions();
    
    Menu getCurrentMenuTree();
}