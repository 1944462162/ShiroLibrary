package com.imustacm.dao.impl;

import com.imustacm.dao.UserDao;
import com.imustacm.daomain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: wangJianBo
 * Date: 2019/9/10 20:01
 * Content:
 */
public class UserDaoImpl implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    // 通过用户名获取角色信息
    public List<String> getRolesByName(String username) {

        String sql = "select role_name from roles where user_name =  ?";

        return jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }


    // 通过用户名获取用户
    public User getUserByUserName(String username) {

        String sql = "select username,password from user where username = ?";

        List<User> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setName(resultSet.getString("username"));
                user.setName(resultSet.getString("password"));
                return user;
            }
        });
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
