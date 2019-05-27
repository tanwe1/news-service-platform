package com.sdnware.news.controller;

import com.sdnware.news.common.CommonUtils;
import com.sdnware.news.pojo.mybatis.SysUserInfoCustom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/20 18:11
 * @see com.sdnware.news.controller
 */
@Controller
public class PageController {

    /**
    @ModelAttribute
    private void userModel(ModelMap modelMap) {
        SysUserInfoCustom currentLoginUser = CommonUtils.getCurrentLoginUser();
        modelMap.addAttribute("currentLoginUser", currentLoginUser);
    }**/

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        SysUserInfoCustom currentLoginUser = CommonUtils.getCurrentLoginUser();
        modelMap.addAttribute("currentLoginUser", currentLoginUser);
        return "system/index";
    }

    @GetMapping("403")
    public String error403(){
        return "error/403";
    }
}
