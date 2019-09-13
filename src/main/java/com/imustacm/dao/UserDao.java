package com.imustacm.dao;

import com.imustacm.daomain.User;


import java.util.List;

/**
 * Author: wangJianBo
 * Date: 2019/9/8 21:20
 * Content:
 */
public interface UserDao {
    User getUserByUserName(String username);

    List<String> getRoleByName(String username);

    List<String> getPermisssionByName(String username);
}
