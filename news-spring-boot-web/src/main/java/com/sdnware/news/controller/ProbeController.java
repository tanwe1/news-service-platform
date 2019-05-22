package com.sdnware.news.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdnware.news.api.ProbeService;
import com.sdnware.news.pojo.mongodb.ProbeInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProbeController {


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.reference.url}")
    private ProbeService<ProbeInfo> probeService;

    @GetMapping("count")
    public Long count(@RequestParam String collectionName) {
        return probeService.count(collectionName);
    }

    @GetMapping("probes")
    public List<ProbeInfo> getProbes(@RequestParam String collectionName) {
        List<ProbeInfo> probes = probeService.findAll(collectionName);
        return probes;
    }
}
