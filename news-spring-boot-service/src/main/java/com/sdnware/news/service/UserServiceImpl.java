package com.sdnware.news.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdnware.news.api.UserService;
import com.sdnware.news.dao.mybatis.SysUserMapper;
import com.sdnware.news.pojo.mybatis.SysUserInfo;
import com.sdnware.news.pojo.mybatis.SysUserInfoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by towey on 2018/11/1.
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserServiceImpl implements UserService<SysUserInfo>{


    @Autowired
    SysUserMapper sysUserMapper;

    @Transactional(readOnly = true)
    @Override
    public List<SysUserInfo> findUserByName(String username) {
        return sysUserMapper.findUserByName(username);
    }

    @Override
    public SysUserInfo queryUserByName(String username, int level) {
        return sysUserMapper.queryUserByName(username, level);
    }

    @Override
    public SysUserInfoCustom queryUserCustom(String username, int level) {
        return sysUserMapper.queryUserCustom(username, level);
    }
}
