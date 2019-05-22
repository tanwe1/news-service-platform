package com.sdnware.news.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/21 10:15
 * @see com.sdnware.news.exception
 */
@Slf4j
@ControllerAdvice
public class SimpleExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String unauthorizedHandler(UnauthorizedException exception) {
        log.info(exception.getMessage());
        return "redirect:/403";
    }
}
