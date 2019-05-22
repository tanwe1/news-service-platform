package com.sdnware.news.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdnware.news.api.UserService;
import com.sdnware.news.pojo.mybatis.SysUserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by towey on 2018/11/2.
 */
@RestController
public class UserController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.reference.url}")
    private UserService<SysUserInfo> userService;

    @GetMapping(value = "user/{username}")
    public List<SysUserInfo> index(@PathVariable("username") String username) {
        List<SysUserInfo> sysUsers = userService.findUserByName(username);
        System.out.println(sysUsers.get(0));
        return sysUsers;
    }
}
