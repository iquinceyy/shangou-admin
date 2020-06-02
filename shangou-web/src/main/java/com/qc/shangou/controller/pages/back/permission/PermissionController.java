package com.qc.shangou.controller.pages.back.permission;

import com.alibaba.fastjson.JSON;
import com.qc.shangou.dao.PermissionDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    ResponseDTO detelePermissionByPermission(@RequestBody List<Permission> permissions){
        System.out.println("delete");
        return permissionService.deletePermissions(permissions);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO editPermission(Permission permission){
        System.out.println("edit");
        return permissionService.editPermission(permission);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO addPermissionToRole(String str){
        System.out.println("add");
        //把String变成对象
        RoleVO roleVO = JSON.parseObject(str, RoleVO.class);
        return permissionService.addPermissionToRole(roleVO);
    }

    @RequestMapping("removePermissionFromRole")
    @ResponseBody
    //把权限从角色里删除
    ResponseDTO removePermissionFromRole(String str){
        RoleVO roleVO = JSON.parseObject(str, RoleVO.class);
        return permissionService.removePermissionFromRole(roleVO);
    }
}
