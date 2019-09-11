package com.imustacm.controller;

import com.imustacm.daomain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
        @RequestMapping(value = "/sublogin.html", method = RequestMethod.POST)
        @ResponseBody
        public String sublogin(User user){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                return e.getMessage();
            }
            return "登录成功";
        }

}
