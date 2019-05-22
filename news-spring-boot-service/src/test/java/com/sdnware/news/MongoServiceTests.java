package com.sdnware.news;

import bootstrap.NewsServiceRunner;
import com.sdnware.news.api.ProbeService;
import com.sdnware.news.api.UserService;
import com.sdnware.news.pojo.mybatis.SysUserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by towey on 2018/11/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsServiceRunner.class)
public class MongoServiceTests {

    private static final Logger LOG = LoggerFactory.getLogger(MongoServiceTests.class);

    @Autowired
    ProbeService probeService;

    @Test
    public void countTest() {
        long count = probeService.count("");
        System.out.println(count);
    }

}
