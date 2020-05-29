package com.qc.shangou.controller.pages.back.role;

import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
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
    int deteleRoleByRoleId(Integer roleId){

        System.out.println("delete");
        roleDao.deleteByPrimaryKey(roleId);
        return 1;
    }

    @RequestMapping("add")
    ResponseDTO addRole(Role role){

       return ResponseDTO.get(roleDao.insertSelective(role)==1);
    }
}
