package com.imustacm.dao.impl;

import com.imustacm.dao.UserDao;
import com.imustacm.daomain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: wangJianBo
 * Date: 2019/9/8 21:20
 * Content:
 */
@Component
public class UserDaoImpl implements UserDao {


    @Resource
    private JdbcTemplate jdbcTemplate;
    public User getUserByUserName(String userName) {

        String sql = "Select username,password from users where username = ?";

        List<User> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    public List<String> getRoleByName(String username) {
        String sql = "Select role_name from roles where user_name = ?";
         return  jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String    mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }
}
