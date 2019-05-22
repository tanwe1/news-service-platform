package com.sdnware.news.controller;

import com.sdnware.news.pojo.mybatis.SysUserInfo;
import com.sdnware.news.shiro.LoginUsernamePasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/16 19:48
 * @see com.sdnware.news.controller
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "system/login";
    }

    @PostMapping("login")
    public String logon(SysUserInfo sysUserInfo, ModelMap modelMap) {
        String username = sysUserInfo.getUsername();
        String password = sysUserInfo.getPassword();
        int level = sysUserInfo.getLevel();

        LoginUsernamePasswordToken loginUsernamePasswordToken =
                new LoginUsernamePasswordToken(username, password, level);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginUsernamePasswordToken);
            if (subject.isAuthenticated()) {
                Session session = subject.getSession();
                session.setAttribute("username", username);
                session.setAttribute("level", level);
            }
        } catch (Exception e) {
            /**
             IncorrectCredentialsException 密码不匹配
             UnknownAccountException 账号不匹配
             **/
            e.printStackTrace();
            modelMap.addAttribute("err_msg", e.getMessage());
            return "system/login";
        }
        return "redirect:/index";
    }

    @GetMapping("logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}
