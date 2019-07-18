package com.sdnware.news;

import bootstrap.NewsServiceRunner;
import com.sdnware.news.api.CloudUserService;
import com.sdnware.news.pojo.mybatis.CloudUserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 16:38
 * @see com.sdnware.news
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsServiceRunner.class)
public class DataSourceTests {

    @Autowired
    CloudUserService<CloudUserInfo> cloudUserService;

    @Test
    public void switchDataSource() {

        CloudUserInfo cloudUserInfo = cloudUserService.queryUserById(129);
        System.out.println(cloudUserInfo);
    }
}
