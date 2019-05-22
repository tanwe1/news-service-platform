package com.sdnware.news.pojo.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>Description: 角色扩展实体</p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 15:48
 * @see com.sdnware.news.pojo.mybatis
 */
public class SysRoleInfoCustom extends SysRoleInfo implements Serializable {

    @Getter @Setter
    Set<SysPermissionInfo> permissions;

}
