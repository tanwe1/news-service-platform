package com.sdnware.news.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/20 11:16
 * @see com.sdnware.news.shiro
 */
public class LoginUsernamePasswordToken extends UsernamePasswordToken {

    private int level;

    public LoginUsernamePasswordToken(String username, String password, int level) {
        super(username, password);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
