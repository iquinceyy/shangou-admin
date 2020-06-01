package com.qc.shangou.controller.pages.back.permission;

import com.qc.shangou.dao.PermissionDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
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
    @Resource
    PermissionDao permissionDao;

    @RequestMapping("list")
    String list(){
        return "pages/back/permission/permission-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(PermissionQuery query) {
        return permissionService.ajaxList(query);
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseDTO detelePermissionByPermission(Integer permissionId){

        System.out.println("delete");

        return ResponseDTO.get(permissionDao.deleteByPrimaryKey(permissionId)==1);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO addPermission(Permission permission){
        System.out.println("add");

        return ResponseDTO.get(permissionDao.insertSelective(permission)==1);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO editPermission(Permission permission){
        System.out.println("edit");
        return permissionService.editPermission(permission);
    }
}
