package com.sdnware.news.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdnware.news.api.CloudUserService;
import com.sdnware.news.dao.mybatis.CloudUserMapper;
import com.sdnware.news.datasource.DataSource;
import com.sdnware.news.pojo.mybatis.CloudUserInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 16:57
 * @see com.sdnware.news.service
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class CloudUserServiceImpl implements CloudUserService<CloudUserInfo> {

    @Autowired
    private CloudUserMapper cloudUserMapper;

    @DataSource(name = "cloudap")
    @Override
    public CloudUserInfo queryUserById(Integer id) {
        return cloudUserMapper.queryUserById(id);
    }
}
