package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 用户资源实体</p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 10:39
 * @see com.sdnware.news.dao.mybatis
 */
public class SysResourceInfo implements Serializable {

    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String  resUrl;
    @Getter @Setter
    private String  resComment;
    @Getter @Setter
    private Integer resPid;
    @Getter @Setter
    private String  resIcon;
    @Getter @Setter
    private Integer resSortNum;
    @Getter @Setter
    private Date    createTime;

    @Override
    public String toString() {
        return "SysResourceInfo{" +
                "id=" + id +
                ", resUrl='" + resUrl + '\'' +
                ", resComment='" + resComment + '\'' +
                ", resPid=" + resPid +
                ", resIcon='" + resIcon + '\'' +
                ", resSortNum=" + resSortNum +
                ", createTime=" + createTime +
                '}';
    }
}
