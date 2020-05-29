package com.qc.shangou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Author quincey
 * Date 2020/5/29 11:31
 */
@Controller
public class ErrorController {
    @RequestMapping("/showErrorPage")
    String showErrorPage(Model model, HttpServletRequest request) {
        model.addAttribute("msg", request.getAttribute("msg"));
        model.addAttribute("qwer", request.getAttribute("msg"));
        System.out.println(123+"错误controller");
        return "errorPage";
    }
}
