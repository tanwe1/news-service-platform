package com.sdnware.news.dao.mybatis;

import com.sdnware.news.pojo.mybatis.CloudUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 16:47
 * @see com.sdnware.news.dao.mybatis
 */
public interface CloudUserMapper {

    CloudUserInfo queryUserById(@Param("id") Integer id);
}
