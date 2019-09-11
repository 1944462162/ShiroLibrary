package com.imustacm.dao;

import com.imustacm.daomain.User;

import java.util.List;

/**
 * Author: wangJianBo
 * Date: 2019/9/10 20:00
 * Content:
 */
public interface UserDao {

    List<String> getRolesByName(String name);

    User getUserByUserName(String username);
}
