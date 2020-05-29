package com.qc.shangou.controller.pages.back.role;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/29 15:49
 */
@Controller
@RequestMapping("/pages/back/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @RequestMapping("list")
    String list(){
        return "pages/back/role/role-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(RoleQuery query) {

        return roleService.ajaxList(query);
    }
}
