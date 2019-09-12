package com.imustacm.controller;

import com.imustacm.daomain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: wangJianBo
 * Date: 2019/9/10 23:08
 * Content:
 */
@Controller
public class UserController {
    @RequestMapping(value = "/sublogin", produces="application/json;charset=utf-8")

    public String sublogin(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        return "welcome";
    }


    // admin权限
    @RequiresPermissions("admin")
    @RequestMapping(value = "/adminShiro", method = RequestMethod.GET)
    public String adminShiro(){
        return "admin";
    }


    //literature_user权限
    @RequiresRoles("literature_user")
    @RequestMapping(value = "/literatureUser", method = RequestMethod.GET)
    public String literature_userShiro(){
        return "literatureUser";
    }

    //electronics_user权限
    @RequiresRoles("electronics_user")
    @RequestMapping(value = "/electronicsUser", method = RequestMethod.GET)
    public String electronics_user(){
        return "electronicsUser";
    }

}
