package com.sdnware.news.controller;

import com.sdnware.news.pojo.mybatis.SysUserInfo;
import com.sdnware.news.shiro.LoginUsernamePasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
    //public String logon(@Validated SysUserInfo sysUserInfo, ModelMap modelMap) {
    public String logon(SysUserInfo sysUserInfo, ModelMap modelMap) {

        String username = sysUserInfo.getUsername();
        String password = sysUserInfo.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            modelMap.addAttribute("errMsg", "请输入用户名或密码");
        } else {
            Integer level = sysUserInfo.getLevel();

            LoginUsernamePasswordToken loginUsernamePasswordToken =
                    new LoginUsernamePasswordToken(username, password, level);

            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(loginUsernamePasswordToken);
                if (subject.isAuthenticated()) {
                    Session session = subject.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("level", level);
                    return "redirect:/index";
                }
            } catch (UnknownAccountException | IncorrectCredentialsException e) {
                modelMap.addAttribute("errMsg", "用户名或密码错误");
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("errMsg", e.getMessage());
            }
        }
        return "system/login";
    }

    @GetMapping("logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}
