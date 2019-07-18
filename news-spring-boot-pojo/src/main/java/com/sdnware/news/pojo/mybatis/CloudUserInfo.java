package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 16:41
 * @see com.sdnware.news.pojo.mybatis
 */
public class CloudUserInfo {

    @Getter @Setter private Integer id;
    @Getter @Setter private String  address;
    @Getter @Setter private String  password;
    @Getter @Setter private String  phone;
    @Getter @Setter private String  username;
    @Getter @Setter private Integer usertype;
    @Getter @Setter private Integer state;
    @Getter @Setter private Integer pid;
    @Getter @Setter private String  username_zh;
    @Getter @Setter private Integer level;
    @Getter @Setter private String  region;
    @Getter @Setter private String  region_zh;
    @Getter @Setter private String  content;
    @Getter @Setter private Date    create_time;
    @Getter @Setter private String  ticket;

    @Override
    public String toString() {
        return "CloudUserInfo{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", usertype=" + usertype +
                ", state=" + state +
                ", pid=" + pid +
                ", username_zh='" + username_zh + '\'' +
                ", level=" + level +
                ", region='" + region + '\'' +
                ", region_zh='" + region_zh + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
