package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 用户权限实体</p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 10:40
 * @see com.sdnware.news.dao.mybatis
 */
public class SysPermissionInfo implements Serializable {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String  expression;
    @Getter @Setter
    private String  desc;
    @Getter @Setter
    private Date    createTime;

    @Override
    public String toString() {
        return "SysPermissionInfo{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                ", desc='" + desc + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
