package com.qc.shangou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author quincey
 * Date 2020/5/29 11:31
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    String index()
    {
        return "index";
    }

    @RequestMapping("login2")
    String login() {

        return "login2";
    }
}
