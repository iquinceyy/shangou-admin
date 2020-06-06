package com.qc.shangou.controller.pages.back.user;


import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;

import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/6 15:03
 */
@Controller
@RequestMapping("/pages/back/user")
public class UserController {

    @Resource
    UserService userService;

    //用户列表url
    @RequestMapping("list")
    String list(){
        return "pages/back/user/user-list";
    }

    @RequestMapping("ajaxUserList")
    @ResponseBody
    PageDTO ajaxList(UserQuery query)
    {
        return userService.ajaxUserList(query);
    }

    //增加用户列表url
    @RequestMapping("add")
    String addPre(){
        return "pages/back/user/user-add";
    }

    @RequestMapping("addPre")
    @ResponseBody
    ResponseDTO addAjxaUser(User user) {
        return userService.addAjxaUser(user);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO editUser(User user) {
        return userService.editUser(user);
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseDTO deleteUser(User user) {
        return userService.deleteUser(user);
    }


}
