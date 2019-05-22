package com.sdnware.news;

import bootstrap.NewsServiceRunner;
import com.sdnware.news.api.UserService;
import com.sdnware.news.dao.mybatis.SysUserMapper;
import com.sdnware.news.pojo.mybatis.SysRoleInfo;
import com.sdnware.news.pojo.mybatis.SysRoleInfoCustom;
import com.sdnware.news.pojo.mybatis.SysUserInfo;
import com.sdnware.news.pojo.mybatis.SysUserInfoCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;


/**
 * Created by towey on 2018/11/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsServiceRunner.class)
public class MybatisDaoTests {

    @Autowired
    UserService<SysUserInfo> userService;

    @Test
    public void findUser(){
        SysUserInfo sysUserInfo = userService.queryUserByName("tanwei", 1);
        System.out.println(sysUserInfo);
    }

    @Test
    public void findUserCustom(){
        SysUserInfoCustom sysUserInfo = (SysUserInfoCustom)userService.queryUserCustom("tanwei", 1);
        Set<SysRoleInfoCustom> roles = sysUserInfo.getRoles();
        roles.stream().forEach( role -> {
            String roleCode = role.getRoleCode();
            System.out.println(roleCode);
        });
    }

}
