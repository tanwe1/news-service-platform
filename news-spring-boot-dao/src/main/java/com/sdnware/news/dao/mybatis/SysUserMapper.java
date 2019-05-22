package com.sdnware.news.dao.mybatis;

import com.sdnware.news.pojo.mybatis.SysUserInfo;
import com.sdnware.news.pojo.mybatis.SysUserInfoCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by towey on 2018/11/1.
 */
public interface SysUserMapper {

    /**
     * @Description:
     *  基于用户名查询用户列表
     *  由于用户有级别区分，故用户名可能重复，
     *  查询结果非唯一
     * @param
     * @return
     * @create 2019/5/20 9:32
     * @throws
     */
    List<SysUserInfo> findUserByName(@Param("username") String username);

    /**
     * @Description:
     *  根据用户名及用户级别查询用户信息
     * @param
     * @return
     * @create 2019/5/20 10:22
     * @throws
     */
    SysUserInfo queryUserByName(@Param("username") String username, @Param("level") int level);

    /**
     * @Description:
     *  新增用户数据
     * @param
     * @return
     * @create 2019/5/20 9:34
     * @throws
     */
    void addUser(SysUserInfo sysUserInfo);

    /**
     * @Description:
     *  查询用户角色及权限信息
     * @param
     * @return
     * @create 2019/5/21 15:55
     * @throws
     */
    SysUserInfoCustom queryUserCustom(@Param("username") String username, @Param("level") int level);
}
