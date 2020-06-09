package com.qc.shangou.controller.pages.back.user;


import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;

import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.RoleService;
import com.qc.shangou.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/6 15:03
 */
@Controller
@RequestMapping("/pages/back/user")
public class UserController extends BaseController {

    @Resource
    UserService userService;
    @Resource
    RoleService roleService;

    //用户列表url
    @RequestMapping("list")
    String list(Model model){
        PageDTO systemRoles = roleService.getSystemRoles();
        model.addAttribute("systemRoles",systemRoles.getData());
        System.out.println(systemRoles.getData());
        return "pages/back/user/user-list";
    }

    @RequestMapping("ajaxUserList")
    @ResponseBody
    PageDTO ajaxList(UserQuery query)
    {
        return userService.ajaxUserList(query);
    }

    //增加用户列表url (不需要)
    @RequestMapping("add")
    String addPre(){
        return "pages/back/user/user-add";
    }

    //(不需要)
    @RequestMapping("addPre")
    @ResponseBody
    ResponseDTO addAjxaUser(User user) {
        return userService.addAjxaUser(user);
    }

    // 获取用户的角色
    @RequestMapping("getUserRoles/{phone}")
    @ResponseBody
    List<RoleVO> getUserRoles(@PathVariable String phone) {
        return userService.selectHisRolesByPhone(phone);
    }

    // 对用户进行角色和权限分配
    @RequestMapping("dispatchUserPermission/{userId}")
    @ResponseBody
    ResponseDTO dispatchUserPermission(@PathVariable Long userId, @RequestBody List<Role> roles) {
        return userService.dispatchUserPermission(userId,roles);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO editUser(User user) {
        ResponseDTO res = userService.editUser(user);
        if (user.getUserId().equals(getUserId())){
            //当前用户是修改用户
            //修改后session里的信息改变了  session也要修改
            User u = new User();
            getSession().setAttribute("nickName",u.getNickName());
            getSession().setAttribute("realName",u.getRealName());
            getSession().setAttribute("photo",u.getPhoto());
        }
        return res;
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseDTO deleteUser(User user) {
        return userService.deleteUser(user);
    }


}
