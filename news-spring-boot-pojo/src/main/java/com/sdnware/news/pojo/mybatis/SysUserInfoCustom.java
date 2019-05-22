package com.sdnware.news.pojo.mybatis;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>Description: 扩展用户</p>
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 10:33
 * @see com.sdnware.news.pojo.mybatis
 */
public class SysUserInfoCustom extends SysUserInfo implements Serializable {

    @Getter @Setter
    Set<SysRoleInfoCustom> roles;

}
