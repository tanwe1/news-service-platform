package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by towey on 2018/11/1.
 */
public class SysUserInfo implements Serializable {

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
    private Integer state;
    @Getter
    @Setter
    private Integer pid;
    @Getter
    @Setter
    private Integer level;
    @Getter
    @Setter
    private Date createTime;

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", pid=" + pid +
                ", level=" + level +
                ", createTime=" + createTime +
                '}';
    }
}
