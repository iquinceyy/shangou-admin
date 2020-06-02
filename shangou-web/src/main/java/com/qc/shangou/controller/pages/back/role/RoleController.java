package com.qc.shangou.controller.pages.back.role;

import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 15:49
 */
@Controller
@RequestMapping("/pages/back/role")
public class RoleController {

    @Resource
    RoleService roleService;
    @Resource
    RoleDao roleDao;

    @RequestMapping("list")
    String list(){
        return "pages/back/role/role-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(RoleQuery query) {

        return roleService.ajaxList(query);
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseDTO deteleRoleByRoleId(Integer roleId){

        System.out.println("delete");

        return ResponseDTO.get(roleDao.deleteByPrimaryKey(roleId)==1);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO addRole(Role role){
        System.out.println("add");

       return ResponseDTO.get(roleDao.insertSelective(role)==1);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO editRole(Role role){
        System.out.println("edit");
        return roleService.editRole(role);
    }
    @RequestMapping("deleteRoles")
    @ResponseBody
    ResponseDTO deleteRoles(@RequestBody List<Role> roles) {

        return roleService.deleteRoles(roles);
    }
    @RequestMapping("getSystemRoles")
    @ResponseBody
    PageDTO  getSystemRoles(){
        return roleService.getSystemRoles();
    }
}
