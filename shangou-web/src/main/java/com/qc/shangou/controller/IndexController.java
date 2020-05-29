package com.qc.shangou.controller;

import com.qc.shangou.dao.RoleDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/29 11:31
 */
@Controller
public class IndexController {
    @Resource
    RoleDao roleDao;

    @RequestMapping("index")
    String index()
    {
        return "index";
    }

}
