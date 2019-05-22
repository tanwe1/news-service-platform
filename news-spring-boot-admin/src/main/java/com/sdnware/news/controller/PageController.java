package com.sdnware.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("index")
    public String index() {
        return "system/index";
    }

    @GetMapping("403")
    public String error403(){
        return "error/403";
    }
}
