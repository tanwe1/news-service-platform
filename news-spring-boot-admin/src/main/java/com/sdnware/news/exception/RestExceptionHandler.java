package com.sdnware.news.exception;

import com.google.gson.Gson;
import com.sdnware.news.common.SpringBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/17 12:38
 * @see com.sdnware.news.exception
 */
@RestControllerAdvice
public class RestExceptionHandler {


    /**
     * @Description:
     *  处理Controller参数绑定异常(实体通过@Validated修饰，实体字段以@NotNull、@NotEmpty...修饰绑定)
     *  example: SysUserInfo.class
     * @param
     * @return
     * @create 2019/5/24 18:46
     * @throws
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bindException(Exception e) {
        Result result = new Result("3800019");
        if (e instanceof MethodArgumentNotValidException) {
            convertError(((MethodArgumentNotValidException) e).getBindingResult(), result);
        } else if (e instanceof BindException) {
            convertError(((BindException) e).getBindingResult(), result);
        }
        return result.toString();
    }

    class Result {
        private String status;
        private Map<String, Object> message;

        public Result(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }

    public static void convertError(Errors error, Result result) {
        if (error.hasErrors()) {
            Map<String, Object> errMap = new HashMap<>();
            if (error.hasGlobalErrors()) {
                List<String> msgList = new ArrayList<>();
                for (ObjectError err : error.getGlobalErrors()) {
                    msgList.add(err.getDefaultMessage());
                }
                errMap.put("_global_msg", msgList);
            }
            if (error.hasFieldErrors()) {
                for (FieldError err : error.getFieldErrors()) {
                    errMap.put(err.getField(), err.getDefaultMessage());
                }
            }
            result.message = errMap;
        }
    }
}
