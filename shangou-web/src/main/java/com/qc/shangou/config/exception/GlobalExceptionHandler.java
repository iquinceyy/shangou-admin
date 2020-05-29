package com.qc.shangou.config.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author quincey
 * Date 2020/5/29 11:11
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthorizationException.class)
    String handUnAuthException(Exception e, Model model) {
        model.addAttribute("errorMsg", e.getMessage());
        model.addAttribute("qwer","1234");
        return "unAuthPage";
    }
}
