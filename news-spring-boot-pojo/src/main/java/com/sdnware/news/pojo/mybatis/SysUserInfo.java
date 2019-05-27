package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @Getter
    @Setter
    @NotEmpty(message = "密码不能为空")
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
