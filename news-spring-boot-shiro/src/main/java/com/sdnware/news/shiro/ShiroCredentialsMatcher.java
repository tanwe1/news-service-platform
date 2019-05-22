package com.sdnware.news.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

@Slf4j
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (token instanceof LoginUsernamePasswordToken) {
            LoginUsernamePasswordToken loginUsernamePasswordToken = (LoginUsernamePasswordToken) token;
            char[] password = loginUsernamePasswordToken.getPassword();
            String inputPassword = String.valueOf(password);
            String credentials = info.getCredentials().toString();
            return this.equals(DigestUtils.md5Hex(inputPassword), credentials);
        }
        return super.doCredentialsMatch(token, info);
    }
}
