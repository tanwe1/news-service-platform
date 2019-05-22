package com.sdnware.news.shiro;

import com.sdnware.news.api.UserService;
import com.sdnware.news.common.SpringBeanFactory;
import com.sdnware.news.pojo.mybatis.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserService<SysUserInfo> userService = SpringBeanFactory.getBean("userService", UserService.class);
        if (authenticationToken instanceof LoginUsernamePasswordToken) {
            LoginUsernamePasswordToken loginUsernamePasswordToken = (LoginUsernamePasswordToken) authenticationToken;
            int level = loginUsernamePasswordToken.getLevel();
            String username = loginUsernamePasswordToken.getUsername();
            SysUserInfo sysUserInfo = userService.queryUserByName(username, level);
            if (null != sysUserInfo) {
                //ByteSource salt = ByteSource.Util.bytes(sysUserInfo.getSalt());
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        sysUserInfo, sysUserInfo.getPassword(), getName());
                return simpleAuthenticationInfo;
            }
        }
        throw new UnknownAccountException();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        if (primaryPrincipal instanceof SysUserInfo) {
            SysUserInfo sysUserInfo = (SysUserInfo) primaryPrincipal;
            UserService<SysUserInfoCustom> userService = SpringBeanFactory.getBean("userService", UserService.class);
            SysUserInfoCustom sysUserInfoCustom = userService.queryUserCustom(sysUserInfo.getUsername(), sysUserInfo.getLevel());
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            Set<SysRoleInfoCustom> roleInfos = sysUserInfoCustom.getRoles();
            roleInfos.stream().forEach(role -> {
                roles.add(role.getRoleCode());
                Set<SysPermissionInfo> permissionInfos = role.getPermissions();
                permissionInfos.forEach(permission -> permissions.add(permission.getExpression()));
            });

            simpleAuthorizationInfo.setRoles(roles);
            simpleAuthorizationInfo.setStringPermissions(permissions);
            log.info(sysUserInfo.getPassword());
        }
        return simpleAuthorizationInfo;
    }
}
