package com.sdnware.news.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdnware.news.api.ProbeService;
import com.sdnware.news.dao.mongodb.ProbeMongoRepository;
import com.sdnware.news.pojo.mongodb.ProbeInfo;
import com.sdnware.news.utils.DateTimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class ProbeServiceImpl implements ProbeService<ProbeInfo> {

    private static final Logger LOG = LoggerFactory.getLogger(ProbeServiceImpl.class);

    @Autowired
    ProbeMongoRepository mongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long count(String collectionName) {

        Set<String> macSet = new HashSet<>();
        macSet.add("38:3a:21:e0:19:46");
        macSet.add("38:3a:21:e0:19:72");
        macSet.add("38:3a:21:e0:19:36");
        macSet.add("38:3a:21:e0:19:2a");
        macSet.add("38:3a:21:e0:14:42");
        /**
        macSet.add("38:3a:21:e0:0d:b4");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        LocalDateTime gteDateTime = LocalDateTime.parse("2019-03-25 05:11:55.501Z", dateTimeFormatter);
        gteDateTime = DateTimeHelper.convertToMongoDbDateTime(gteDateTime);**/

        LocalDateTime nowDateTime = DateTimeHelper.convertToMongoDbDateTime(LocalDateTime.now());
        LocalDateTime gteDateTime = nowDateTime.plusMinutes(-5); //前5分钟数据

        System.out.println("nowDateTime:" + nowDateTime);
        System.out.println("gteDateTime:" + gteDateTime);

        Query query = new Query().addCriteria(Criteria.where("apmac").in(macSet)
                .and("recvtime").gte(gteDateTime).lte(nowDateTime));
        long count = mongoTemplate.count(query, ProbeInfo.class, collectionName);
        return count;
    }

    @Override
    public List<ProbeInfo> findAll(String collectionName) {
        return mongoTemplate.findAll(ProbeInfo.class, collectionName);
    }
}
