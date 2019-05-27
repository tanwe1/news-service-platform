package com.sdnware.news.common;

import com.sdnware.news.api.UserService;
import com.sdnware.news.pojo.mybatis.SysUserInfoCustom;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.Objects;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/24 10:10
 * @see com.sdnware.news.common
 */
public class CommonUtils {

    public static SysUserInfoCustom getCurrentLoginUser() {
        SysUserInfoCustom sysUserInfo = null;
        Session session = SecurityUtils.getSubject().getSession();

        if (Objects.nonNull(session)) {
            String username = (String) session.getAttribute("username");
            Integer level = (Integer) session.getAttribute("level");

            UserService<SysUserInfoCustom> userService = SpringBeanFactory.getBean("userService", UserService.class);
            sysUserInfo = userService.queryUserCustom(username, level);
        }
        return sysUserInfo;
    }
}
