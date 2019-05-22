package com.sdnware.news.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class UserController {

    @RequiresPermissions("user:list")
    @GetMapping("user")
    public String index() {
        return "user/user";
    }
}
