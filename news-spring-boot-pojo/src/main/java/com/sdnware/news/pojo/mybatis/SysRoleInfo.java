package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 用户角色实体</p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 10:39
 * @see com.sdnware.news.dao.mybatis
 */
public class SysRoleInfo implements Serializable {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String roleCode;
    @Getter @Setter
    private String roleComment;
    @Getter @Setter
    private Date createTime;


    @Override
    public String toString() {
        return "SysRoleInfo{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleComment='" + roleComment + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
