package com.imustacm.shiro;

import com.imustacm.dao.UserDao;
import com.imustacm.daomain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: wangJianBo
 * Date: 2019/9/8 10:29
 * Content:
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;
    @Override
    // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        Set<String> role = getRoleByName(username);
        Set<String> permissions = getPermissionsByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByName(String username) {
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:add");
        permissions.add("user:del");
        return permissions;
    }

    private Set<String> getRoleByName(String username) {

        List<String> list = userDao.getRoleByName(username);
        Set<String> user = new HashSet<String>(list);
        return user;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String)authenticationToken.getPrincipal();

        String password = getPasswordByName(username);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"CustomRealm");

//        // 将盐设置进去
//        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return simpleAuthenticationInfo;
    }

    private String getPasswordByName(String username) {
        User user = userDao.getUserByUserName(username);
        if(user != null){
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString());
    }
}
