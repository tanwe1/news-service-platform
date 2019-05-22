package com.sdnware.news.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdnware.news.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdnware on 18-8-15.
 */
@RestController
public class DemoController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.reference.url}")
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        System.out.println(name);
        return demoService.sayHello(name);
    }
}
