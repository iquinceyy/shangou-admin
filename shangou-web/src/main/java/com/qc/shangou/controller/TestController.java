package com.qc.shangou.controller;

import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/30 11:35
 */
@Controller
public class TestController {
    @Resource
    RoleDao roleDao;


    @RequestMapping("add")
    ResponseDTO addRole(Role role){
        System.out.println(role);
        roleDao.insertSelective(role);
        return ResponseDTO.ok("成功");
    }
}
