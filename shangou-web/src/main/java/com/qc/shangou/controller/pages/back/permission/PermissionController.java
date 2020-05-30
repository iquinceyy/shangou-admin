package com.qc.shangou.controller.pages.back.permission;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/30 15:49
 */
@Controller
@RequestMapping("/pages/back/permission")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    @RequestMapping("list")
    String list(){
        return "pages/back/permission/permission-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(PermissionQuery query) {
        return permissionService.ajaxList(query);
    }
}
