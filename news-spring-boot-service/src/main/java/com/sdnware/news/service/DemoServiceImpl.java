package com.sdnware.news.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdnware.news.api.DemoService;

/**
 * Created by sdnware on 18-8-15.
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "hello, " + name;
    }
}
